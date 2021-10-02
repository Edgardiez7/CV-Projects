/*
EDGAR DIEZ ALONSO
*/
/*
 * Simplified simulation of air flow in a wind tunnel
 *
 * OpemMP version 
 *
 * Computacion Paralela, Grado en Informatica (Universidad de Valladolid)
 * 2020/2021
 *
 * v1.0
 *
 * (c) 2021 Arturo Gonzalez Escribano
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>
#include <sys/time.h>
#include <omp.h>

#define PRECISION 10000
#define STEPS 8

/* Structure to represent a solid particle in the tunnel surface */
typedef struct
{
	int pos_row, pos_col;	  // Position in the grid
	int mass;				  // Particle mass
	int resistance;			  // Resistance to air flow
	int speed_row, speed_col; // Movement direction and speed
	int old_flow;			  // To annotate the flow before applying effects
} Particle;

/*Function to get minimum of two elements*/
int min(int x, int y)
{
	if (x < y)
		return x;
	else
		return y;
}
/* 
 * Function to get wall time
 */
double cp_Wtime()
{
	struct timeval tv;
	gettimeofday(&tv, NULL);
	return tv.tv_sec + 1.0e-6 * tv.tv_usec;
}

/* 
 * Macro function to simplify accessing with two coordinates to a flattened array
 * 	This macro-function can be changed and/or optimized by the students
 *
 */
#define accessMat(arr, exp1, exp2) arr[(int)(exp1)*columns + (int)(exp2)]

/*
 * Function: Update flow in a matrix position
 * 	This function can be changed and/or optimized by the students
 */
int update_flow(int *flow, int *flow_copy, int *particle_locations, int row, int col, int columns, int skip_particles)
{
	// Skip update in particle positions
	if (skip_particles && accessMat(particle_locations, row, col) != 0) 
		return 0;

	// Update if border left
	else if (col == 0)
	{
		accessMat(flow, row, col) =
			(accessMat(flow_copy, row, col) +
			 accessMat(flow_copy, row - 1, col) * 2 +
			 accessMat(flow_copy, row - 1, col + 1)) /
			4;
	}
	// Update if border right
	else if (col == columns - 1)
	{
		accessMat(flow, row, col) =
			(accessMat(flow_copy, row, col) +
			 accessMat(flow_copy, row - 1, col) * 2 +
			 accessMat(flow_copy, row - 1, col - 1)) /
			4;
	}
	// Update in central part
	else //(col > 0 && col < columns - 1)
	{
		accessMat(flow, row, col) =
			(accessMat(flow_copy, row, col) +
			 accessMat(flow_copy, row - 1, col) * 2 +
			 accessMat(flow_copy, row - 1, col - 1) +
			 accessMat(flow_copy, row - 1, col + 1)) /
			5;
	}

	// Return flow variation at this position
	return abs(accessMat(flow_copy, row, col) - accessMat(flow, row, col));
}

/*
 * Function: Move particle
 * 	This function can be changed and/or optimized by the students
 */
void move_particle(int *flow, Particle *particles, int particle, int rows, int columns)
{
	// Compute movement for each step
	int step;
	for (step = 0; step < STEPS; step++)
	{
		// Highly simplified phisical model
		int row = particles[particle].pos_row / PRECISION;
		int col = particles[particle].pos_col / PRECISION;
		int pressure = accessMat(flow, row - 1, col);
		int left, right;
		if (col == 0)
			left = 0;
		else
			left = pressure - accessMat(flow, row - 1, col - 1);
		if (col == columns - 1)
			right = 0;
		else
			right = pressure - accessMat(flow, row - 1, col + 1);

		int flow_row = (int)((float)pressure / particles[particle].mass * PRECISION);
		int flow_col = (int)((float)(right - left) / particles[particle].mass * PRECISION);

		// Speed change
		particles[particle].speed_row =
			(particles[particle].speed_row + flow_row) / 2;
		particles[particle].speed_col =
			(particles[particle].speed_col + flow_col) / 2;

		// Movement
		particles[particle].pos_row =
			particles[particle].pos_row + particles[particle].speed_row / STEPS / 2;
		particles[particle].pos_col =
			particles[particle].pos_col + particles[particle].speed_col / STEPS / 2;

		// Control limits
		if (particles[particle].pos_row > PRECISION * (rows - 1))
			particles[particle].pos_row = PRECISION * (rows - 1);
		else if (particles[particle].pos_col < 0)
			particles[particle].pos_col = 0;
		else if (particles[particle].pos_col > PRECISION * (columns - 1))
			particles[particle].pos_col = PRECISION * (columns - 1);
	}
}

#ifdef DEBUG
/* 
 * Function: Print the current state of the simulation 
 */
void print_status(int iteration, int rows, int columns, int *flow, int num_particles, int *particle_locations, int max_var)
{
	/* 
	 * You don't need to optimize this function, it is only for pretty 
	 * printing and debugging purposes.
	 * It is not compiled in the production versions of the program.
	 * Thus, it is never used when measuring times in the leaderboard
	 */
	int i, j;
	printf("Iteration: %d, max_var: %f\n",
		   iteration,
		   (float)max_var / PRECISION);

	printf("  +");
	for (j = 0; j < columns; j++)
		printf("---");
	printf("+\n");
	for (i = 0; i < rows; i++)
	{
		if (i % STEPS == iteration % STEPS)
			printf("->|");
		else
			printf("  |");

		for (j = 0; j < columns; j++)
		{
			char symbol;
			if (accessMat(flow, i, j) >= 10 * PRECISION)
				symbol = '*';
			else if (accessMat(flow, i, j) >= 1 * PRECISION)
				symbol = '0' + accessMat(flow, i, j) / PRECISION;
			else if (accessMat(flow, i, j) >= 0.5 * PRECISION)
				symbol = '+';
			else if (accessMat(flow, i, j) > 0)
				symbol = '.';
			else
				symbol = ' ';

			if (accessMat(particle_locations, i, j) > 0)
				printf("[%c]", symbol);
			else
				printf(" %c ", symbol);
		}
		printf("|\n");
	}
	printf("  +");
	for (j = 0; j < columns; j++)
		printf("---");
	printf("+\n\n");
}
#endif

/*
 * Function: Print usage line in stderr
 */
void show_usage(char *program_name)
{
	fprintf(stderr, "Usage: %s ", program_name);
	fprintf(stderr, "<rows> <columns> <maxIter> <threshold> <inlet_pos> <inlet_size> <particles_pos> <particles_size> <particles_density> <short_rnd1> <short_rnd2> <short_rnd3> [ <fixed_row> <fixed_col> <fixed_resistance> ... ]\n");
	fprintf(stderr, "\n");
}

/*
 * MAIN PROGRAM
 */
int main(int argc, char *argv[])
{
	int i, j;

	// Simulation data
	int max_iter;	   // Maximum number of simulation steps
	int var_threshold; // Threshold of variability to continue the simulation
	int rows, columns; // Cultivation area sizes

	int *flow;				 // Wind tunnel air-flow
	int *flow_copy;			 // Wind tunnel air-flow (ancillary copy)
	int *particle_locations; // To quickly locate places with particles

	int inlet_pos;			 // First position of the inlet
	int inlet_size;			 // Inlet size
	int particles_band_pos;	 // First position of the band where particles start
	int particles_band_size; // Size of the band where particles start
	float particles_density; // Density of starting particles

	unsigned short random_seq[3]; // Status of the random sequence

	int num_particles;	 // Number of particles
	Particle *particles; // List to store cells information

	/* 1. Read simulation arguments */
	/* 1.1. Check minimum number of arguments */
	if (argc < 13)
	{
		fprintf(stderr, "-- Error: Not enough arguments when reading configuration from the command line\n\n");
		show_usage(argv[0]);
		exit(EXIT_FAILURE);
	}

	/* 1.2. Read simulation area sizes, maximum number of iterations and threshold */
	rows = atoi(argv[1]);
	columns = atoi(argv[2]);
	max_iter = atoi(argv[3]);
	var_threshold = (int)(atof(argv[4]) * PRECISION);

	/* 1.3. Read inlet data and band of moving particles data */
	inlet_pos = atoi(argv[5]);
	inlet_size = atoi(argv[6]);
	particles_band_pos = atoi(argv[7]);
	particles_band_size = atoi(argv[8]);
	particles_density = atof(argv[9]);

	/* 1.4. Read random sequences initializer */
	for (i = 0; i < 3; i++)
	{
		random_seq[i] = (unsigned short)atoi(argv[10 + i]);
	}

	/* 1.5. Allocate particles */
	num_particles = 0;
	// Check correct number of parameters for fixed particles
	if (argc > 13)
	{
		if ((argc - 13) % 3 != 0)
		{
			fprintf(stderr, "-- Error in number of fixed position particles\n\n");
			show_usage(argv[0]);
			exit(EXIT_FAILURE);
		}
		// Get number of fixed particles
		num_particles = (argc - 13) / 3;
	}
	// Add number of moving particles
	num_particles += (int)(particles_band_size * columns * particles_density);

	// Allocate space for particles
	if (num_particles > 0)
	{
		particles = (Particle *)malloc(num_particles * sizeof(Particle));
		if (particles == NULL)
		{
			fprintf(stderr, "-- Error allocating particles structure for size: %d\n", num_particles);
			exit(EXIT_FAILURE);
		}
	}
	else
		particles = NULL;

	/* 1.6. Read fixed particles */
	int particle = 0;
	if (argc > 13)
	{
		int fixed_particles = (argc - 13) / 3;
		for (particle = 0; particle < fixed_particles; particle++)
		{
			particles[particle].pos_row = atoi(argv[13 + particle * 3]) * PRECISION;
			particles[particle].pos_col = atoi(argv[14 + particle * 3]) * PRECISION;
			particles[particle].mass = 0;
			particles[particle].resistance = (int)(atof(argv[15 + particle * 3]) * PRECISION);
			particles[particle].speed_row = 0;
			particles[particle].speed_col = 0;
		}
	}

	/* 1.7. Generate moving particles */
	for (; particle < num_particles; particle++)
	{
		particles[particle].pos_row = (int)(PRECISION * (particles_band_pos + particles_band_size * erand48(random_seq)));
		particles[particle].pos_col = (int)(PRECISION * columns * erand48(random_seq));
		particles[particle].mass = (int)(PRECISION * (1 + 5 * erand48(random_seq)));
		particles[particle].resistance = (int)(PRECISION * erand48(random_seq));
		particles[particle].speed_row = 0;
		particles[particle].speed_col = 0;
	}

#ifdef DEBUG
	// 1.8. Print arguments
	printf("Arguments, Rows: %d, Columns: %d, max_iter: %d, threshold: %f\n", rows, columns, max_iter, (float)var_threshold / PRECISION);
	printf("Arguments, Inlet: %d, %d  Band of particles: %d, %d, %f\n", inlet_pos, inlet_size, particles_band_pos, particles_band_size, (float)particles_density / PRECISION);
	printf("Arguments, Init Random Sequence: %hu,%hu,%hu\n", random_seq[0], random_seq[1], random_seq[2]);
	printf("Particles: %d\n", num_particles);
	for (int particle = 0; particle < num_particles; particle++)
	{
		printf("Particle[%d] = { %d, %d, %d, %d, %d, %d }\n",
			   particle,
			   particles[particle].pos_row,
			   particles[particle].pos_col,
			   particles[particle].mass,
			   particles[particle].resistance,
			   particles[particle].speed_row,
			   particles[particle].speed_col);
	}
	printf("\n");
#endif // DEBUG

	/* 2. Start global timer */
	double ttotal = cp_Wtime();

	/*
 *
 * START HERE: DO NOT CHANGE THE CODE ABOVE THIS POINT
 *
 */
	/* 3. Initialization */
	flow = (int *)malloc(sizeof(int) * (size_t)rows * (size_t)columns);
	flow_copy = (int *)malloc(sizeof(int) * (size_t)rows * (size_t)columns);
	particle_locations = (int *)malloc(sizeof(int) * (size_t)rows * (size_t)columns);
	double noise[inlet_pos + inlet_size];
	
	if (flow == NULL || flow_copy == NULL || particle_locations == NULL)
	{
		fprintf(stderr, "-- Error allocating culture structures for size: %d x %d \n", rows, columns);
		exit(EXIT_FAILURE);
	}

#pragma omp parallel for collapse (2) if(rows > 200 || columns >200)
	for (i = 0; i < rows; i++)
		for (j = 0; j < columns; j++)
		{
			accessMat(flow, i, j) = 0;
			accessMat(flow_copy, i, j) = 0;
			accessMat(particle_locations, i, j) = 0;
		}

	/* 4. Simulation */
	int max_var = INT_MAX;
	int iter;
	
	double phase;
	double phase_step;
	double pressure_level;
	//int particle;
	//double noise;
	for (iter = 1; iter <= max_iter && max_var > var_threshold; iter++)
	{

		// 4.1. Change inlet values each STEP iterations
		if (iter % STEPS == 1)
		{
		
		for (j = inlet_pos; j < inlet_pos + inlet_size; j++)
			noise[j] = 0.5 - erand48(random_seq);
		
		int minVar = min(iter, rows);
		#pragma omp parallel
		{
#pragma omp for nowait private(j,phase,phase_step,pressure_level)
			for (j = inlet_pos; j < inlet_pos + inlet_size; j++)
			{
				// 4.1.1. Change the fans phase
				phase = iter / STEPS * (M_PI / 4);
				phase_step = M_PI / 2 / inlet_size;
				pressure_level = 9 + 2 * sin(phase + (j - inlet_pos) * phase_step);
				// 4.1.2. Add some random noise				
				// 4.1.3. Store level in the first row of the ancillary structure				
				accessMat(flow, 0, j) = (int)(PRECISION * (pressure_level + noise[j]));
			}
			 // End inlet update

			// 4.2. Particles movement each STEPS iterations
			// Clean particle positions
#pragma omp for private(i, j) collapse (2)//schedule (static,8) 
			for (i = 0; i < minVar; i++)
				for (j = 0; j < columns; j++)
					accessMat(particle_locations, i, j) = 0;

			//int particle;
#pragma omp for //schedule (guided,8)
			for (particle = 0; particle < num_particles; particle++)
			{
				if(particles[particle].mass!=0){	
				// Movable particles
				move_particle(flow, particles, particle, rows, columns);
				}
				/*#pragma omp atomic 
				accessMat(particle_locations,
						  particles[particle].pos_row / PRECISION,
						  particles[particle].pos_col / PRECISION) += 1;	*/
			}
#pragma omp for 	
			for( particle = 0; particle < num_particles; particle++ ) {
			#pragma omp atomic
				accessMat( particle_locations, 
					particles[ particle ].pos_row / PRECISION,
					particles[ particle ].pos_col / PRECISION ) += 1;
			}

			int row;
			int col;
#pragma omp for private(particle, row, col) // schedule (guided,8)
			for (particle = 0; particle < num_particles; particle++)
			{
				row = particles[particle].pos_row / PRECISION;
				col = particles[particle].pos_col / PRECISION;
				update_flow(flow, flow_copy, particle_locations, row, col, columns, 0);
				particles[particle].old_flow = accessMat(flow, row, col);
			}
			int back;
			int resistance;
#pragma omp for private(row, col, resistance, back) 
			for (particle = 0; particle < num_particles; particle++)
			{
				row = particles[particle].pos_row / PRECISION;
				col = particles[particle].pos_col / PRECISION;
				resistance = particles[particle].resistance;
				back = (int)((long)particles[particle].old_flow * resistance / PRECISION) / accessMat(particle_locations, row, col);
#pragma omp atomic
				accessMat(flow, row, col) -= back;
#pragma omp atomic
				accessMat(flow, row - 1, col) += back / 2;
				if (col > 0)
#pragma omp atomic
					accessMat(flow, row - 1, col - 1) += back / 4;
				//else
				//	accessMat(flow, row - 1, col) += back / 4;
				if (col < columns - 1)
#pragma omp atomic
					accessMat(flow, row - 1, col + 1) += back / 4;
				else if (col<=0 || col >=columns -1){
#pragma omp atomic 
					accessMat(flow, row - 1, col) += back / 4;
				}
				}
		} // End effects
}
              if (iter % STEPS == 1)
			max_var = 0;

		// 4.5. Propagation stage
		// 4.5.1. Initialize data to detect maximum variability
#pragma omp parallel
		{
		
		// 4.4. Copy data in the ancillary structure
		int minVar = min(iter, rows);
#pragma omp for private(i, j)
		for (i = 0; i < minVar; i++)
			for (j = 0; j < columns; j++)
				accessMat(flow_copy, i, j) = accessMat(flow, i, j);
		// 4.5.2. Execute propagation on the wave fronts
		int wave_front = iter % STEPS;
		if (wave_front == 0)
			wave_front = STEPS;
			
		int wave;
		int col;
		int var;
#pragma omp for reduction(max:max_var) private(wave, col, var)
		for (wave = wave_front; wave < rows; wave += STEPS)
		{
			if (wave > iter)
				continue;
			for (col = 0; col < columns; col++)
			{
				int var = update_flow(flow, flow_copy, particle_locations, wave, col, columns, 1);
				if (var > max_var)
				{
					max_var = var;
				}
			}
		} // End propagation

#ifdef DEBUG
		// 4.7. DEBUG: Print the current state of the simulation at the end of each iteration
		print_status(iter, rows, columns, flow, num_particles, particle_locations, max_var);
#endif
	
	}
} // End iterations
	/*
 *
 * STOP HERE: DO NOT CHANGE THE CODE BELOW THIS POINT
 *
 */

	/* 5. Stop global time */
	ttotal = cp_Wtime() - ttotal;

	/* 6. Output for leaderboard */
	printf("\n");
	/* 6.1. Total computation time */
	printf("Time: %lf\n", ttotal);

	/* 6.2. Results: Statistics */
	printf("Result: %d, %d",
		   iter - 1,
		   max_var);
	int res_row = (iter - 1 < rows - 1) ? iter - 1 : rows - 1;
	int res_col;
	for (res_col = columns / 6; res_col < columns; res_col += columns / 6)
		printf(", %d", accessMat(flow, STEPS - 1, res_col));
	for (res_col = columns / 6; res_col < columns; res_col += columns / 6)
		printf(", %d", accessMat(flow, res_row, res_col));
	printf("\n");

	/* 7. Free resources */
	free(flow);
	free(flow_copy);
	free(particle_locations);
	free(particles);

	/* 8. End */
	return 0;
}
