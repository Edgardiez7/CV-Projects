/* GRUPO: 04
EDGAR DIEZ ALONSO
*/
/*
 * Simplified simulation of air flow in a wind tunnel
 *
 * Reference sequential version (Do not modify this code)
 *
 * Computacion Paralela, Grado en Informatica (Universidad de Valladolid)
 * 2020/2021
 *
 * v1.3.1
 *
 * (c) 2021 Arturo Gonzalez Escribano
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include<limits.h>
#include<sys/time.h>

/* Headers for the MPI assignment versions */
#include<mpi.h>
#include<stddef.h>

#define	PRECISION	10000
#define	STEPS		8
#define min(X, Y) (((X) < (Y)) ? (X) : (Y))

/* Structure to represent a solid particle in the tunnel surface */
typedef struct {
	unsigned char extra;		// Extra field for student's usage
	int pos_row, pos_col;		// Position in the grid
	int mass;			// Particle mass
	int resistance;			// Resistance to air flow
	int speed_row, speed_col;	// Movement direction and speed
	int old_flow;			// To annotate the flow before applying effects
} Particle;


/* 
 * Function to get wall time
 */
double cp_Wtime(){
	struct timeval tv;
	gettimeofday(&tv, NULL);
	return tv.tv_sec + 1.0e-6 * tv.tv_usec;
}

/* 
 * Macro function to simplify accessing with two coordinates to a flattened array
 * 	This macro-function can be changed and/or optimized by the students
 *
 */
#define accessMat( arr, exp1, exp2 )	arr[ (int)(exp1) * columns + (int)(exp2) ]

/*
 * Function: Update flow in a matrix position
 * 	This function can be changed and/or optimized by the students
 */
int update_flow( int *flow, int *flow_copy, int *particle_locations, int row, int col, int columns, int skip_particles ) {
	// Skip update in particle positions
	//No hace falta
	//if ( skip_particles && accessMat( particle_locations, row, col ) != 0 ) return 0;


	// Update in central part
	if ( col > 0 && col < columns-1 ) {
		accessMat( flow, row, col ) = 
			( 
			accessMat( flow_copy, row, col ) + 
			accessMat( flow_copy, row-1, col ) * 2 + 
			accessMat( flow_copy, row-1, col-1 ) +
			accessMat( flow_copy, row-1, col+1 ) 
			) / 5;
	}
	// Update if border left
	 else if ( col == 0 ) {
		accessMat( flow, row, col ) = 
			( 
			accessMat( flow_copy, row, col ) + 
			accessMat( flow_copy, row-1, col ) * 2 + 
			accessMat( flow_copy, row-1, col+1 ) 
			) / 4;
	}  else  {
		accessMat( flow, row, col ) = 
			( 
			accessMat( flow_copy, row, col ) + 
			accessMat( flow_copy, row-1, col ) * 2 + 
			accessMat( flow_copy, row-1, col-1 ) 
			) / 4;
			/*if(rank==1){
			printf("Valor del lado medio update %d \n",accessMat( flow, row, col ));
			printf("Valor del lado FILA ANTERIOR  update %d \n",accessMat( flow, row-1, col ));
			printf("Valor del lado FILA ANTERIOR COL-1 update %d \n",accessMat( flow, row-1, col-1 ));
			}*/
	}

	// Return flow variation at this position
	return abs( accessMat( flow_copy, row, col ) - accessMat( flow, row, col ) );
}


/*
 * Function: Move particle
 * 	This function can be changed and/or optimized by the students
 */
void move_particle( int *flow, Particle *particles, int particle, int rows, int columns ) {
	// Compute movement for each step
	int step;
	for( step = 0; step < STEPS; step++ ) {
		// Highly simplified phisical model
		int row = particles[ particle ].pos_row / PRECISION;
		int col = particles[ particle ].pos_col / PRECISION;
		int pressure = accessMat( flow, row-1, col );
		int left, right;
		if ( col == 0 ) left = 0;
		else left = pressure - accessMat( flow, row-1, col-1 );
		if ( col == columns-1 ) right = 0;
		else right = pressure - accessMat( flow, row-1, col+1 );
				
		int flow_row = (int)( (float)pressure / particles[ particle ].mass * PRECISION );
		int flow_col = (int)( (float)(right - left) / particles[ particle ].mass * PRECISION );

		// Speed change	
		particles[ particle ].speed_row =
			( particles[ particle ].speed_row + flow_row ) / 2;
		particles[ particle ].speed_col =
			( particles[ particle ].speed_col + flow_col ) / 2;
	
		// Movement	
		particles[ particle ].pos_row =
			particles[ particle ].pos_row + particles[ particle ].speed_row / STEPS / 2;
		particles[ particle ].pos_col =
			particles[ particle ].pos_col + particles[ particle ].speed_col / STEPS / 2;

		// Control limits
		if ( particles[ particle ].pos_row >= PRECISION * rows )
			particles[ particle ].pos_row = PRECISION * rows - 1;
		if ( particles[ particle ].pos_col < 0 )
			particles[ particle ].pos_col = 0;
		if ( particles[ particle ].pos_col >= PRECISION * columns )
			particles[ particle ].pos_col = PRECISION * columns - 1;
	}
}


#ifdef DEBUG
/* 
 * Function: Print the current state of the simulation 
 */
void print_status( int iteration, int rows, int columns, int *flow, int num_particles, int *particle_locations, int max_var ) {
	/* 
	 * You don't need to optimize this function, it is only for pretty 
	 * printing and debugging purposes.
	 * It is not compiled in the production versions of the program.
	 * Thus, it is never used when measuring times in the leaderboard
	 */
	int i,j;
	printf("Iteration: %d, max_var: %f\n", 
		iteration, 
		(float)max_var / PRECISION
		);

	printf("  +");
	for( j=0; j<columns; j++ ) printf("---");
	printf("+\n");
	for( i=0; i<rows; i++ ) {
		if ( i % STEPS == iteration % STEPS )
			printf("->|");
		else
			printf("  |");

		for( j=0; j<columns; j++ ) {
			char symbol;
			if ( accessMat( flow, i, j )  >= 10 * PRECISION ) symbol = '*';
			else if ( accessMat( flow, i, j ) >= 1 * PRECISION ) symbol = '0' + accessMat( flow, i, j ) / PRECISION;
			else if ( accessMat( flow, i, j ) >= 0.5 * PRECISION ) symbol = '+';
			else if ( accessMat( flow, i, j ) > 0 ) symbol = '.';
			else symbol = ' ';

			if ( accessMat( particle_locations, i, j ) > 0 ) printf("[%c]", symbol );
			else printf(" %c ", symbol );
		}
		printf("|\n");
	}
	printf("  +");
	for( j=0; j<columns; j++ ) printf("---");
	printf("+\n\n");
}
#endif

/*
 * Function: Print usage line in stderr
 */
void show_usage( char *program_name ) {
	fprintf(stderr,"Usage: %s ", program_name );
	fprintf(stderr,"<rows> <columns> <maxIter> <threshold> <inlet_pos> <inlet_size> <particles_pos> <particles_size> <particles_density> <short_rnd1> <short_rnd2> <short_rnd3> [ <fixed_row> <fixed_col> <fixed_resistance> ... ]\n");
	fprintf(stderr,"\n");
}


/*
 * MAIN PROGRAM
 */
int main(int argc, char *argv[]) {
	int i,j;

	// Simulation data
	int max_iter;			// Maximum number of simulation steps
	int var_threshold;		// Threshold of variability to continue the simulation
	int rows, columns;		// Cultivation area sizes

	int *flow = NULL;		// Wind tunnel air-flow 
	int *flow_copy = NULL;		// Wind tunnel air-flow (ancillary copy)
	int *particle_locations = NULL;	// To quickly locate places with particles

	int inlet_pos;			// First position of the inlet
	int inlet_size;			// Inlet my_size
	int particles_band_pos;		// First position of the band where particles start
	int particles_band_size;	// Size of the band where particles start
	float particles_density;	// Density of starting particles

	unsigned short random_seq[3];		// Status of the random sequence

	int		num_particles;		// Number of particles
	Particle	*particles;		// List to store cells information


	/* 0. Initialize MPI */
	MPI_Init( &argc, &argv );
	int rank;
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );

	/* 1. Read simulation arguments */
	/* 1. Read simulation arguments */
	/* 1.1. Check minimum number of arguments */
	if (argc < 13) {
		fprintf(stderr, "-- Error: Not enough arguments when reading configuration from the command line\n\n");
		show_usage( argv[0] );
		MPI_Abort( MPI_COMM_WORLD, EXIT_FAILURE );
	}

	/* 1.2. Read simulation area sizes, maximum number of iterations and threshold */
	rows = atoi( argv[1] );
	columns = atoi( argv[2] );
	max_iter = atoi( argv[3] );
	var_threshold = (int)(atof( argv[4] ) * PRECISION);

	/* 1.3. Read inlet data and band of moving particles data */
	inlet_pos = atoi( argv[5] );
	inlet_size = atoi( argv[6] );
	particles_band_pos = atoi( argv[7] );
	particles_band_size = atoi( argv[8] );
	particles_density = atof( argv[9] );

	/* 1.4. Read random sequences initializer */
	for( i=0; i<3; i++ ) {
		random_seq[i] = (unsigned short)atoi( argv[10+i] );
	}

	/* 1.5. Allocate particles */
	num_particles = 0;
	// Check correct number of parameters for fixed particles
	if (argc > 13 ) {
		if ( (argc - 13) % 3 != 0 ) {
			fprintf(stderr, "-- Error in number of fixed position particles\n\n");
			show_usage( argv[0] );
			MPI_Abort( MPI_COMM_WORLD, EXIT_FAILURE );
		}
		// Get number of fixed particles
		num_particles = (argc - 13) / 3;
	}
	// Add number of moving particles
	num_particles += (int)( particles_band_size * columns * particles_density );

	// Allocate space for particles
	if ( num_particles > 0 ) {
		particles = (Particle *)malloc( num_particles * sizeof( Particle ) );
		if ( particles == NULL ) {
			fprintf(stderr,"-- Error allocating particles structure for my_size: %d\n", num_particles );
			MPI_Abort( MPI_COMM_WORLD, EXIT_FAILURE );
		}
	}
	else particles = NULL;

	/* 1.6. Read fixed particles */
	int particle = 0;
	if (argc > 13 ) {
		int fixed_particles = (argc - 13) / 3;
		for (particle = 0; particle < fixed_particles; particle++) {
			particles[ particle ].pos_row = atoi( argv[ 13 + particle*3 ] ) * PRECISION;
			particles[ particle ].pos_col = atoi( argv[ 14 + particle*3 ] ) * PRECISION;
			particles[ particle ].mass = 0;
			particles[ particle ].resistance = (int)( atof( argv[ 15 + particle*3 ] ) * PRECISION);
			particles[ particle ].speed_row = 0;
			particles[ particle ].speed_col = 0;
		}
	}

	/* 1.7. Generate moving particles */
	for ( ; particle < num_particles; particle++) {
		particles[ particle ].pos_row = (int)( PRECISION * ( particles_band_pos + particles_band_size * erand48( random_seq ) ) );
		particles[ particle ].pos_col = (int)( PRECISION * columns * erand48( random_seq ) );
		particles[ particle ].mass = (int)( PRECISION * ( 1 + 5 * erand48( random_seq ) ) );
		particles[ particle ].resistance = (int)( PRECISION * erand48( random_seq ) );
		particles[ particle ].speed_row = 0;
		particles[ particle ].speed_col = 0;
	}

#ifdef DEBUG
	// 1.8. Print arguments 
	if ( rank == 0 ) {
		printf("Arguments, Rows: %d, Columns: %d, max_iter: %d, threshold: %f\n", rows, columns, max_iter, (float)var_threshold/PRECISION );
		printf("Arguments, Inlet: %d, %d  Band of particles: %d, %d, %f\n", inlet_pos, inlet_size, particles_band_pos, particles_band_size, (float)particles_density / PRECISION );
		printf("Arguments, Init Random Sequence: %hu,%hu,%hu\n", random_seq[0], random_seq[1], random_seq[2]);
		printf("Particles: %d\n", num_particles );
		for (int particle=0; particle<num_particles; particle++) {
			printf("Particle[%d] = { %d, %d, %d, %d, %d, %d }\n",
					particle,
					particles[particle].pos_row, 
					particles[particle].pos_col, 
					particles[particle].mass, 
					particles[particle].resistance, 
					particles[particle].speed_row, 
					particles[particle].speed_col
					);
		}
		printf("\n");
	}
#endif // DEBUG


	/* 2. Start global timer */
	MPI_Barrier( MPI_COMM_WORLD );
	double ttotal = cp_Wtime();


/*
 *
 * START HERE: DO NOT CHANGE THE CODE ABOVE THIS POINT
 *
 */

	// Declare variables used in later output
	int max_var = INT_MAX;
	int global_max_var;
	
	int resultsA[6];
	int resultsB[6];
	int my_size, my_begin,rest;
	
	int wave, col, wave_front;

	

	MPI_Request request1, request2;

	int nprocs;
	MPI_Comm_size( MPI_COMM_WORLD, &nprocs );

	rest = rows % nprocs;

	my_size = rows / nprocs;
	my_begin = rank * my_size;

	if ( rest != 0 ) {
		if (rank < rest) {
			my_size = my_size+1;
		}
		my_begin = my_begin + min( rank, rest );			
	}	
	
	int iter = 0;
	int var;
	int ind;
	/* 3. Initialization */
	// Añadimos un valor mas a cada posicion para que puede almacenar el halo de cada proceso.
	flow = (int *) malloc( sizeof(int) * (size_t) (my_size + 1) * (size_t) columns );
	flow_copy = (int *) malloc( sizeof(int) * (size_t) (my_size + 1) * (size_t) columns );
	particle_locations = (int *) malloc( sizeof(int) * (size_t) (my_size + 1) * (size_t) columns );

	// Recorremos todas las matrices y las inicializamos en 0.
	for( i=0; i<my_size; i++ ) {
		for( j=0; j<columns; j++ ) {
			accessMat( flow, i+1, j ) = 0;
			accessMat( flow_copy, i+1, j ) = 0;
			accessMat( particle_locations, i+1, j ) = 0;
		}
	}

	/* 4. Simulation */
	for( iter=1; iter<=max_iter && max_var > var_threshold; iter++) {

		// 4.1. Change inlet values each STEP iterations
		if ( iter % STEPS == 1 ) {
			if (rank == 0) {
				double phase = iter / STEPS * ( M_PI / 4 );
				double phase_step = M_PI / 2 / inlet_size;
				for ( j=inlet_pos; j<inlet_pos+inlet_size; j++ ) {
					// 4.1.1. Change the fans phase
				
					double pressure_level = 9 + 2 * sin( phase + (j-inlet_pos) * phase_step );
		
					// 4.1.2. Add some random noise
					double noise = 0.5 - erand48( random_seq );

					// 4.1.3. Store level in the first row of the ancillary structure
					accessMat( flow, 1, j ) = (int)(PRECISION * (pressure_level + noise));
				}
			}
		

			// 4.4. Copy data in the ancillary structure
			for( i = 0; i < my_size && my_begin+i < iter; i++ ) 
				for( j=0; j < columns; j++ )
					accessMat( flow_copy, i+1, j ) = accessMat( flow, i+1, j );

			// 4.5. Propagation stage
			// 4.5.1. Initialize data to detect maximum variability
		} // END EFFECTS

		wave_front = iter % STEPS;
		
			

		// 4.5.2. Execute propagation on the wave fronts
		// controlo que proceso entra
		if ( wave_front == 0 ) wave_front = STEPS;

		if (iter % STEPS == 1){
			max_var = 0;
		}	


		if (rank < nprocs-1 ){
			MPI_Send(&accessMat(flow, my_size, 0), columns, MPI_INT, rank+1, 0, MPI_COMM_WORLD );
		}
		if (rank > 0){
			MPI_Recv(flow_copy, columns, MPI_INT, rank-1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE );
		}
		while (wave_front < my_begin) wave_front = wave_front + STEPS;
		wave_front = wave_front - my_begin;

		for( wave = wave_front; wave < my_size; wave += STEPS ) {	
			if ( my_begin+wave > iter ) continue;

			// printf("[%d] y [%d]"wave_front,rank)
			for( col=0; col < columns; col++ ) {
				var = update_flow( flow, flow_copy,particle_locations, wave+1, col, columns, 1);
				if ( var > max_var ) max_var = var;	
				if(iter % STEPS != 0)
					accessMat(flow_copy, wave+1, col) = accessMat(flow, wave+1, col);
				/*else {
					for( col=0; col<columns; col++) {
					int var = update_flow( flow, flow_copy, particle_locations, wave_original, col, columns, 1 );
						if ( var > max_var ) {
							max_var = var;
						}
					accessMat(flow_copy,wave_original,col)=accessMat(flow,wave_original,col);
					}*/				
			}
		}
		// Obtenemos el valor maximo de cada una de las variables.
		MPI_Allreduce(&max_var, &global_max_var, 1, MPI_INT, MPI_MAX, MPI_COMM_WORLD);							
		max_var=global_max_var;
		/*if(bandera==1){
			MPI_Wait(&request3,MPI_STATUS_IGNORE);
		}*/

	} // End iterations
	
	
	int res_row = ( iter-1 < rows-1 ) ? iter-1 : rows-1;
	int array[6], array2[6];
	if ( my_begin <= STEPS-1 &&  my_begin+my_size > STEPS-1){
		// MPI: Fill result arrays used for later output
		for ( ind=0; ind<6; ind++ )
			array[ ind ] = accessMat( flow, STEPS-my_begin, ind * columns/6 );
		MPI_Isend( array, 6, MPI_INT, 0, 1, MPI_COMM_WORLD, &request1);
		//printf("hola2");
				/*for( i=0; i<my_size+1; i++ ) {		
					for( j=0; j<columns; j++ ){
						printf("[%d]",accessMat( flow, i, j ));
					}
						printf("\n");
				}
				printf("\n");
				printf("------------- ARRIBA ENVIADO --------------- \n");*/
	}

	if ( my_begin+my_size > res_row && my_begin <= res_row) {
		for ( ind=0; ind<6; ind++ )
			array2[ ind ] = accessMat( flow, res_row-my_begin+1, ind * columns/6 );
		MPI_Isend( array2, 6, MPI_INT, 0, 2, MPI_COMM_WORLD, &request2);
		//printf(" [%d] RANK DEL SUJETO \n",rank);
	}
	
	if (rank == 0){
		MPI_Recv(resultsA, 6, MPI_INT, MPI_ANY_SOURCE, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		MPI_Recv(resultsB, 6, MPI_INT, MPI_ANY_SOURCE, 2, MPI_COMM_WORLD, MPI_STATUS_IGNORE);	
	}

	if ( my_begin+my_size > STEPS-1 && my_begin <= STEPS-1){
		//MPI_Wait(&request1,MPI_STATUS_IGNORE);
		
		MPI_Wait(&request1, MPI_STATUS_IGNORE);
	} 
	if (my_begin <= res_row && my_begin+my_size > res_row) {
		//MPI_Wait(&request2,MPI_STATUS_IGNORE);
		MPI_Wait(&request2, MPI_STATUS_IGNORE);
	} 
	
/*
 *
 * STOP HERE: DO NOT CHANGE THE CODE BELOW THIS POINT
 *
 */

	/* 5. Stop global time */
	MPI_Barrier( MPI_COMM_WORLD );
	ttotal = cp_Wtime() - ttotal;
	

	/* 6. Output for leaderboard */
	if ( rank == 0 ) {
		printf("\n");
		/* 6.1. Total computation time */
		printf("Time: %lf\n", ttotal );

		/* 6.2. Results: Statistics */
		printf("Result: %d, %d", 
			iter-1, 
			max_var
			);
		int i;
		for ( i=0; i<6; i++ ) printf(", %d", resultsA[i] );
		for ( i=0; i<6; i++ ) printf(", %d", resultsB[i] );
		printf("\n");
	}

	/* 7. Free resources */	
	free( flow );
	free( flow_copy );
///	free( particle_locations );
	free( particles );

	/* 8. End */
	MPI_Finalize();
	return 0;
}
