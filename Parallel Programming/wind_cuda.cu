/*
 * PRACTICA DE CUDA 2020-2021
 * AUTOR:
 *
 *			EDGAR DIEZ ALONSO
 *
 * UNIVERSIDAD DE VALLADOLID, ETSI      
 */
/*
 * Simplified simulation of air flow in a wind tunnel
 *
 * CUDA version
 *
 * Computacion Paralela, Grado en Informatica (Universidad de Valladolid)
 * 2020/2021
 *
 * v1.4
 *
 * (c) 2021 Arturo Gonzalez Escribano
 */
 #include<stdio.h>
 #include<stdlib.h>
 #include<string.h>
 #include<math.h>
 #include<limits.h>
 #include<sys/time.h>
 
 /* Headers for the CUDA assignment versions */
 #include<cuda.h>
 
 #define	PRECISION	10000
 #define	STEPS		8
 
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
__host__ __device__ int update_flow( int *flow, int *flow_copy, int *particle_locations, int row, int col, int columns, int skip_particles ) {
	 // Skip update in particle positions
	 if ( skip_particles && accessMat( particle_locations, row, col ) != 0 ) return 0;
 
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
	 } 
	 // Update if border right
	 else {
		 accessMat( flow, row, col ) = 
			 ( 
			 accessMat( flow_copy, row, col ) + 
			 accessMat( flow_copy, row-1, col ) * 2 + 
			 accessMat( flow_copy, row-1, col-1 ) 
			 ) / 4;
	 }
	 
 
	 // Return flow variation at this position
	 return abs( accessMat( flow_copy, row, col ) - accessMat( flow, row, col ) );
 }
 
 
 /*
  * Function: Move particle
  * 	This function can be changed and/or optimized by the students
  */
 __host__ __device__ void move_particle( int *flow, Particle *particles, int particle, int rows, int columns ) {   // Compute movement for each step
	
    int step;
    for( step = 0; step < STEPS; step++ ) {
        // Highly simplified phisical model
        int row = particles[ particle ].pos_row / PRECISION;
        int col = particles[ particle ].pos_col / PRECISION;
        int pressure = accessMat( flow, row-1, col );
		/*if (particle == 6){
			printf("particula 6 con row=%d,col=%d,pression=%d.\n",row,col,pressure);			
		};*/
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
	 fprintf(stderr,"<rows> <columns> <maxIter> <threshold> <inlet_pos> <inlet_size> <fixed_particles_pos> <fixed_particles_size> <fixed_particles_density> <moving_particles_pos> <moving_particles_size> <moving_particles_density> <short_rnd1> <short_rnd2> <short_rnd3> [ <fixed_row> <fixed_col> <fixed_resistance> ... ]\n");
	 fprintf(stderr,"\n");
 }
 
 /*
  * KERNELS CUDA
 */
 __global__ void inlet(int* flowCuda, double* presionesCuda, double* noiseCuda, int columns, int inlet_size, int inlet_pos);
 __global__ void limpiar(int* particle_locationsCuda,int iter,int rows,int columns);
 __global__ void moveParticles(int* flowCuda, Particle* particlesCuda, int num_particles, int rows, int columns);
 __global__ void moveParticles2(int* particle_locationsCuda, Particle* particlesCuda, int num_particles, int columns);
 __global__ void updateFlow(int* flowCuda, int* flowcopyCuda, int* particle_locationsCuda, Particle* particlesCuda, int num_particles, int columns);
 __global__ void updateFlow2(int* flowCuda, int* particle_locationsCuda, Particle* particlesCuda, int num_particles, int columns);
 __global__ void almacenaMax(int* maxCuda,int* flowCuda,int* flow_copyCuda,int* particle_locationsCuda,int rows,int columns,int iter, int wave_front);
 __global__ void reductionMax(int* array, int size, int *result);
 __global__ void copiarFlow(int* flow_copyCuda, int* flowCuda, int iter, int rows, int columns);
 __global__ void resetMax(int* maxCuda, int tam);
 
 /*
  * MAIN PROGRAM
  */
 int main(int argc, char *argv[]) {
	 int i,j;
 
	 // Simulation data
	 int max_iter;			// Maximum number of simulation steps
	 int var_threshold;		// Threshold of variability to continue the simulation
	 int rows, columns;		// Cultivation area sizes
 
	 int *flow;			// Wind tunnel air-flow 
	 int *flow_copy;			// Wind tunnel air-flow (ancillary copy)
	 int *particle_locations;	// To quickly locate places with particles
 
	 int inlet_pos;			// First position of the inlet
	 int inlet_size;			// Inlet size
	 int particles_f_band_pos;	// First position of the band where fixed particles start
	 int particles_f_band_size;	// Size of the band where fixed particles start
	 int particles_m_band_pos;	// First position of the band where moving particles start
	 int particles_m_band_size;	// Size of the band where moving particles start
	 float particles_f_density;	// Density of starting fixed particles
	 float particles_m_density;	// Density of starting moving particles
 
	 unsigned short random_seq[3];		// Status of the random sequence
 
	 int		num_particles;		// Number of particles
	 Particle	*particles;		// List to store cells information
 
	 /* 1. Read simulation arguments */
	 /* 1.1. Check minimum number of arguments */
	 if (argc < 16) {
		 fprintf(stderr, "-- Error: Not enough arguments when reading configuration from the command line\n\n");
		 show_usage( argv[0] );
		 exit( EXIT_FAILURE );
	 }
 
	 /* 1.2. Read simulation area sizes, maximum number of iterations and threshold */
	 rows = atoi( argv[1] );
	 columns = atoi( argv[2] );
	 max_iter = atoi( argv[3] );
	 var_threshold = (int)(atof( argv[4] ) * PRECISION);
 
	 /* 1.3. Read inlet data and band of moving particles data */
	 inlet_pos = atoi( argv[5] );
	 inlet_size = atoi( argv[6] );
	 particles_f_band_pos = atoi( argv[7] );
	 particles_f_band_size = atoi( argv[8] );
	 particles_f_density = atof( argv[9] );
	 particles_m_band_pos = atoi( argv[10] );
	 particles_m_band_size = atoi( argv[11] );
	 particles_m_density = atof( argv[12] );
 
	 /* 1.4. Read random sequences initializer */
	 for( i=0; i<3; i++ ) {
		 random_seq[i] = (unsigned short)atoi( argv[13+i] );
	 }
 
	 /* 1.5. Allocate particles */
	 num_particles = 0;
	 // Check correct number of parameters for fixed particles
	 if (argc > 16 ) {
		 if ( (argc - 16) % 3 != 0 ) {
			 fprintf(stderr, "-- Error in number of fixed position particles\n\n");
			 show_usage( argv[0] );
			 exit( EXIT_FAILURE );
		 }
		 // Get number of fixed particles
		 num_particles = (argc - 16) / 3;
	 }
	 // Add number of fixed and moving particles in the bands
	 int num_particles_f_band = (int)( particles_f_band_size * columns * particles_f_density );
	 int num_particles_m_band = (int)( particles_m_band_size * columns * particles_m_density );
	 num_particles += num_particles_f_band;
	 num_particles += num_particles_m_band;
 
	 // Allocate space for particles
	 if ( num_particles > 0 ) {
		 particles = (Particle *)malloc( num_particles * sizeof( Particle ) );
		 if ( particles == NULL ) {
			 fprintf(stderr,"-- Error allocating particles structure for size: %d\n", num_particles );
			 exit( EXIT_FAILURE );
		 }
	 }
	 else particles = NULL;
 
	 /* 1.6.1. Read fixed particles */
	 int particle = 0;
	 if (argc > 16 ) {
		 int fixed_particles = (argc - 16) / 3;
		 for (particle = 0; particle < fixed_particles; particle++) {
			 particles[ particle ].pos_row = atoi( argv[ 16 + particle*3 ] ) * PRECISION;
			 particles[ particle ].pos_col = atoi( argv[ 17 + particle*3 ] ) * PRECISION;
			 particles[ particle ].mass = 0;
			 particles[ particle ].resistance = (int)( atof( argv[ 18 + particle*3 ] ) * PRECISION);
			 particles[ particle ].speed_row = 0;
			 particles[ particle ].speed_col = 0;
		 }
	 }
	 /* 1.6.2. Generate fixed particles in the band */
	 for ( ; particle < num_particles-num_particles_m_band; particle++) {
		 particles[ particle ].pos_row = (int)( PRECISION * ( particles_f_band_pos + particles_f_band_size * erand48( random_seq ) ) );
		 particles[ particle ].pos_col = (int)( PRECISION * columns * erand48( random_seq ) );
		 particles[ particle ].mass = 0;
		 particles[ particle ].resistance = (int)( PRECISION * erand48( random_seq ) );
		 particles[ particle ].speed_row = 0;
		 particles[ particle ].speed_col = 0;
	 }
 
	 /* 1.7. Generate moving particles in the band */
	 for ( ; particle < num_particles; particle++) {
		 particles[ particle ].pos_row = (int)( PRECISION * ( particles_m_band_pos + particles_m_band_size * erand48( random_seq ) ) );
		 particles[ particle ].pos_col = (int)( PRECISION * columns * erand48( random_seq ) );
		 particles[ particle ].mass = (int)( PRECISION * ( 1 + 5 * erand48( random_seq ) ) );
		 particles[ particle ].resistance = (int)( PRECISION * erand48( random_seq ) );
		 particles[ particle ].speed_row = 0;
		 particles[ particle ].speed_col = 0;
	 }
 
 #ifdef DEBUG
	 // 1.8. Print arguments 
	 printf("Arguments, Rows: %d, Columns: %d, max_iter: %d, threshold: %f\n", rows, columns, max_iter, (float)var_threshold/PRECISION );
	 printf("Arguments, Inlet: %d, %d  Band of fixed particles: %d, %d, %f  Band of moving particles: %d, %d, %f\n", inlet_pos, inlet_size, particles_f_band_pos, particles_f_band_size, particles_f_density, particles_m_band_pos, particles_m_band_size, particles_m_density );
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
 #endif // DEBUG
 
 
	 /* 2. Start global timer */
	 cudaSetDevice(0);
	 cudaDeviceSynchronize();
	 double ttotal = cp_Wtime();
 
 /*
  *
  * START HERE: DO NOT CHANGE THE CODE ABOVE THIS POINT
  *
  */	 
	 /* 3. Initialization */
	 int tam = sizeof(int) * (size_t)rows * (size_t)columns;
	 flow = (int *)malloc( tam );
	 flow_copy = (int *)malloc( tam );
	 particle_locations = (int *)malloc( tam );
	 int tamMax;
	 if(rows%STEPS==0)tamMax = rows * columns / STEPS;
	 else tamMax = ((rows/STEPS)+1) * columns;
	 int* max = (int *)malloc( sizeof(int) * tamMax);
	 int* maxVar=(int *)malloc( 1 * sizeof(int));
	 double* presiones = (double *)malloc(sizeof(double)*(size_t)inlet_size*4);
	 double* noise = (double *)malloc(sizeof(double)*(size_t)inlet_size);
 
	 if ( flow == NULL || flow_copy == NULL || particle_locations == NULL || max == NULL|| maxVar == NULL|| noise == NULL || presiones == NULL) {
		 fprintf(stderr,"-- Error allocating culture structures for size: %d x %d \n", rows, columns );
		 exit( EXIT_FAILURE );
	 }
 
	 for( i=0; i<rows; i++ ) {
		 for( j=0; j<columns; j++ ) {
			 accessMat( flow, i, j ) = 0;
			 accessMat( flow_copy, i, j ) = 0;
			 accessMat( particle_locations, i, j ) = 0;			 
		 }
	 }

	 for(i = 0; i < tamMax; i++){
		accessMat( max, 0, i) = 0;
	 }
 
	 int* flowCuda;
	 int* flow_copyCuda;
	 int* particle_locationsCuda;
	 int* maxCuda;
	 int* maxVarCuda;
	 double* presionesCuda;
	 double* noiseCuda;
	 Particle* particlesCuda;	
	 
	 
	 cudaError_t error = cudaSuccess;
	 cudaMalloc(&flowCuda, tam);	
	 cudaMalloc(&flow_copyCuda, tam);	
	 cudaMalloc(&particle_locationsCuda, tam);	
	 cudaMalloc(&maxCuda, sizeof(int)*tamMax);	
	 cudaMalloc(&maxVarCuda, 1 * sizeof(int));
	 cudaMalloc(&noiseCuda, (size_t)inlet_size * sizeof(double));
	 cudaMalloc(&presionesCuda,(size_t)inlet_size * sizeof(double) * 4);
	 int tamParticles = num_particles * sizeof(Particle);
	 cudaMalloc(&particlesCuda, tamParticles);
	 error=cudaGetLastError();
	 if(error != cudaSuccess){
		 printf("Cuda error allocating structures: %s\n", cudaGetErrorString(error));
		 exit( EXIT_FAILURE );
	 }
	// printf("flow Posicion inicial: %p y final: %p.\n", flowCuda, &flowCuda[(tam/sizeof(int))-1]);
	 //printf("particles Posicion inicial: %p y final: %p.", particlesCuda, &particlesCuda[tamParticles-1] );
	 //kernel inlet	
	int gridx1;
	int threadnumber1=512;
	//if(inlet_size%threadnumber1==0 && inlet_size != 0) gridx1=inlet_size/threadnumber1;
	//else 
	gridx1=(inlet_size/threadnumber1)+1;	

	//kernel particulas
	int threadnumber3=512;
	int gridx3;			
	//if (num_particles%threadnumber3==0 && num_particles != 0) gridx3 = num_particles/threadnumber3;
	//else 
	gridx3=(num_particles/threadnumber3)+1;

	//kernel prop
	int gridyM, gridxM;	
	int threadnumber=16;
	//if(rows%threadnumber==0) gridyM=rows/(threadnumber*STEPS);
	//else 
	gridyM=(rows/(threadnumber*STEPS))+1;
	//if(columns%threadnumber==0) gridxM=columns/threadnumber;
	//else 
	gridxM=(columns/threadnumber)+1;	
	dim3 gridM(gridxM, gridyM);
	dim3 blockM(16,16);

	//kernel max
	int gridxmax;
	int threadnumberxmax=512;
	//if((rows*columns/STEPS)%threadnumberxmax==0) gridxmax=(rows*columns/STEPS)/threadnumberxmax;
	//else 
	gridxmax=((rows*columns/STEPS)/threadnumberxmax)+1;	
	
	cudaMemcpy(flowCuda, flow, tam, cudaMemcpyHostToDevice); 	
	cudaMemcpy(particle_locationsCuda, particle_locations, tam, cudaMemcpyHostToDevice); 
	cudaMemcpy(particlesCuda, particles, tamParticles, cudaMemcpyHostToDevice);	
	cudaMemcpy(maxCuda, max, tam / STEPS, cudaMemcpyHostToDevice);
	 
	 /* 4. Simulation */
	 int max_var = INT_MAX;
	 int iter;
	 for( iter=1; iter<=max_iter && max_var > var_threshold; iter++) {
		 //printf("entro en la iteracion. iter nº%d.\n", iter);
		
		 // 4.1. Change inlet values each STEP iterations
		 if ( iter % STEPS == 1 ) {
			double phase = iter / STEPS * ( M_PI / 4 );
			double phase_step = M_PI / 2 / inlet_size;
			 // 4.1.2. Add some random noise
			for(int i = 0; i<inlet_size; i++) noise[i]=  0.5 - erand48( random_seq );
			cudaMemcpy(noiseCuda, noise, inlet_size * sizeof(double), cudaMemcpyHostToDevice);
			// 4.1.1. Change the fans phase
			for(int i = 0; i<inlet_size*4; i++) presiones[i] = 9 + 2 * sin( phase + (i-inlet_pos) * phase_step );			
			cudaMemcpy(presionesCuda, presiones, (size_t)inlet_size * sizeof(double)*4 , cudaMemcpyHostToDevice);
			inlet<<<gridx1, threadnumber1>>>(flowCuda, presionesCuda, noiseCuda, columns, inlet_size, inlet_pos);
			/*error = cudaGetLastError();
			 if(error != cudaSuccess){
				 printf("Error inlet(): %s\n", cudaGetErrorString(error));
				 exit( EXIT_FAILURE );
			 }*/
			//cudaMemcpy(noise, noiseCuda, inlet_size * sizeof(double), cudaMemcpyDeviceToHost);
			//cudaMemcpy(presiones, presionesCuda, (size_t)inlet_size * sizeof(double)*4 , cudaMemcpyDeviceToHost);
			//cudaMemcpy(flow, flowCuda, tam, cudaMemcpyDeviceToHost);		
		 }  // End inlet update
		 	 
			
		 // 4.2. Particles movement each STEPS iterations
		 if ( iter % STEPS == 1 ) {
			 // Clean particle positions
			 int min;
			 (iter <= rows)? min = iter : min = rows;
			 int gridx2, gridy2;
			 int threadnumber2=16;
			 //if(min%threadnumber2==0 && min != 0) gridy2=min/threadnumber2;
			 //else 
			 gridy2=(min/threadnumber2)+1;
			 //if(columns%threadnumber2==0) gridx2=columns/threadnumber2;
			 //else 
			 gridx2=(columns/threadnumber2)+1;	
			 dim3 grid2(gridx2, gridy2);
			 dim3 block2(16,16);		
			limpiar<<<grid2,block2>>>(particle_locationsCuda, iter, rows, columns);
			/*error = cudaGetLastError();
			 if(error != cudaSuccess){
				 printf("Error limpiar(): %s\n", cudaGetErrorString(error));
				 exit( EXIT_FAILURE );
			 }*/
			//cudaMemcpy(particle_locations, particle_locationsCuda, tam, cudaMemcpyDeviceToHost);				 
					 
			//cudaMemcpy(flowCuda, flow, tam, cudaMemcpyHostToDevice);			
			moveParticles<<<gridx3, threadnumber3>>>(flowCuda, particlesCuda, num_particles, rows, columns);
			 /*error = cudaGetLastError();
			 if(error != cudaSuccess){
				 printf("Error moveParticles(): %s\n", cudaGetErrorString(error));
				 exit( EXIT_FAILURE );
			 }	*/		
			 //cudaMemcpy(flow, flowCuda, tam, cudaMemcpyDeviceToHost);
			 //cudaMemcpy(particles, particlesCuda, tamParticles, cudaMemcpyDeviceToHost);
 			 //cudaMemcpy(particlesCuda, particles, tamParticles, cudaMemcpyHostToDevice);	
			 //cudaMemcpy(particle_locationsCuda, particle_locations, tam, cudaMemcpyHostToDevice);	
			 moveParticles2<<<gridx3, threadnumber3>>>(particle_locationsCuda, particlesCuda, num_particles, columns);
			 /*error = cudaGetLastError();
			 if(error != cudaSuccess){
				 printf("Error moveParticles(): %s\n", cudaGetErrorString(error));
				 exit( EXIT_FAILURE );
			 }*/
			// cudaMemcpy( particles, particlesCuda, tamParticles, cudaMemcpyDeviceToHost);	
			//cudaMemcpy( particle_locations, particle_locationsCuda, tam, cudaMemcpyDeviceToHost);			 
		 
		 } // End particles movements
 
		
		 // 4.3. Effects due to particles each STEPS iterations
		 if ( iter % STEPS == 1 ) {

			//cudaMemcpy(flowCuda, flow, tam, cudaMemcpyHostToDevice);
			//cudaMemcpy(flow_copyCuda, flow_copy, tam, cudaMemcpyHostToDevice);
			//cudaMemcpy(particle_locationsCuda, particle_locations, tam, cudaMemcpyHostToDevice);
			//cudaMemcpy(particlesCuda, particles, tamParticles, cudaMemcpyHostToDevice);						
			updateFlow<<<gridx3, threadnumber3>>>(flowCuda, flow_copyCuda, particle_locationsCuda, particlesCuda, num_particles, columns);
			/*error = cudaGetLastError();
			if(error != cudaSuccess){
				printf("Error moveParticles(): %s\n", cudaGetErrorString(error));
				exit( EXIT_FAILURE );
			}	*/			
			//cudaMemcpy(flow, flowCuda, tam, cudaMemcpyDeviceToHost);
			//cudaMemcpy(flow_copy, flow_copyCuda, tam, cudaMemcpyDeviceToHost);
			//cudaMemcpy(particle_locations, particle_locationsCuda, tam, cudaMemcpyDeviceToHost);
			//cudaMemcpy(particles, particlesCuda, tamParticles, cudaMemcpyDeviceToHost);		



			//cudaMemcpy(flowCuda, flow, tam, cudaMemcpyHostToDevice);			
			//cudaMemcpy(particle_locationsCuda, particle_locations, tam, cudaMemcpyHostToDevice);
			//cudaMemcpy(particlesCuda, particles, tamParticles, cudaMemcpyHostToDevice);						
			updateFlow2<<<gridx3, threadnumber3>>>(flowCuda, particle_locationsCuda, particlesCuda, num_particles, columns);
			/*error = cudaGetLastError();
			if(error != cudaSuccess){
				printf("Error moveParticles(): %s\n", cudaGetErrorString(error));
				exit( EXIT_FAILURE );
			}	*/			
			//cudaMemcpy(flow, flowCuda, tam, cudaMemcpyDeviceToHost);			
			//cudaMemcpy(particle_locations, particle_locationsCuda, tam, cudaMemcpyDeviceToHost);
			//cudaMemcpy(particles, particlesCuda, tamParticles, cudaMemcpyDeviceToHost);				 
		 } // End effects
 		
		// 4.4. Copy data in the ancillary structure
		//cudaMemcpy(flowCuda, flow, tam, cudaMemcpyHostToDevice);
		//cudaMemcpy(flow_copyCuda, flow_copy, tam, cudaMemcpyHostToDevice);
		int min;
		(iter <= rows)? min = iter : min = rows;
		int gridy2, gridx2;
		int threadnumber2=16;
		//if(min%threadnumber2==0 && min != 0) gridy2=min/threadnumber2;
		//else 
		gridy2=(min/threadnumber2)+1;
		//if(columns%threadnumber2==0) gridx2=columns/threadnumber2;
		//else 
		gridx2=(columns/threadnumber2)+1;
		dim3 grid2(gridx2, gridy2);
		dim3 block2(16,16);		
		copiarFlow<<<grid2, block2>>>(flow_copyCuda, flowCuda, iter, rows, columns);
		error = cudaGetLastError();
		/*if(error != cudaSuccess){
			printf("Error copiarFlow(): %s\n", cudaGetErrorString(error));
			exit( EXIT_FAILURE );
		}	*/	
		//cudaMemcpy(flow, flowCuda, tam, cudaMemcpyDeviceToHost);			
		//cudaMemcpy(flow_copy, flow_copyCuda, tam, cudaMemcpyDeviceToHost);
 		
		// 4.5. Propagation stage
		// 4.5.1. Initialize data to detect maximum variability
		
		if ( iter % STEPS == 1 ) { 			
			resetMax<<<gridxmax,threadnumberxmax>>>(maxCuda, tamMax);
			error = cudaGetLastError();
			if(error != cudaSuccess){
				printf("Error moveParticles(): %s\n", cudaGetErrorString(error));
				exit( EXIT_FAILURE );
			}		
			maxVar[0]=0;
			max_var = 0;
			cudaMemcpy(maxVarCuda, maxVar, sizeof(int), cudaMemcpyHostToDevice);			
		}		
		// 4.5.2. Execute propagation on the wave fronts
		int wave_front = iter % STEPS;
		if ( wave_front == 0 ) wave_front = STEPS;			

	
		//cudaMemcpy(flowCuda, flow, tam, cudaMemcpyHostToDevice);
		//cudaMemcpy(maxCuda, max, tam, cudaMemcpyHostToDevice);		
		//cudaMemcpy(particle_locationsCuda, particle_locations, tam, cudaMemcpyHostToDevice);
			
		almacenaMax<<<gridM,blockM>>>(maxCuda, flowCuda, flow_copyCuda, particle_locationsCuda, rows, columns, iter,wave_front);
		error = cudaGetLastError();
		/*if(error != cudaSuccess){
			printf("Error moveParticles(): %s\n", cudaGetErrorString(error));
			exit( EXIT_FAILURE );
		}*/
					
		//cudaMemcpy(particle_locations, particle_locationsCuda, tam, cudaMemcpyDeviceToHost);
		//cudaMemcpy(max, maxCuda, tam, cudaMemcpyDeviceToHost);			
		//cudaMemcpy(flow_copy, flow_copyCuda, tam, cudaMemcpyDeviceToHost);


		
		//cudaMemcpy(maxCuda, max, tam, cudaMemcpyHostToDevice);
		reductionMax<<<gridxmax,threadnumberxmax,sizeof(int)*threadnumberxmax>>>(maxCuda, rows*columns/STEPS, maxVarCuda);
		error = cudaGetLastError();
		/*if(error != cudaSuccess){
			printf("Error moveParticles(): %s\n", cudaGetErrorString(error));
			exit( EXIT_FAILURE );
		}*/

		//cudaMemcpy(max, maxCuda, tam, cudaMemcpyDeviceToHost);
		cudaMemcpy(maxVar, maxVarCuda, sizeof(int), cudaMemcpyDeviceToHost);
		
		max_var = maxVar[0];	
		//printf("%d iteracion %d.\n", max_var, iter);		
		 
 #ifdef DEBUG
		 // 4.7. DEBUG: Print the current state of the simulation at the end of each iteration 
		 print_status( iter, rows, columns, flow, num_particles, particle_locations, max_var );
 #endif
 
	 } // End iterations
	
	 cudaMemcpy(flow, flowCuda, tam, cudaMemcpyDeviceToHost);

	 
	free(max);
	free(presiones);
	free(noise);
	cudaFree(flowCuda);
	cudaFree(flow_copyCuda);
	cudaFree(particle_locationsCuda);
	cudaFree(maxCuda);
	cudaFree(maxVarCuda);
	cudaFree(presionesCuda);
	cudaFree(noiseCuda);
	cudaFree(particlesCuda);
	

 /*
  *
  * STOP HERE: DO NOT CHANGE THE CODE BELOW THIS POINT
  *
  */
 
	 /* 5. Stop global timer */
	 cudaDeviceSynchronize();
	 ttotal = cp_Wtime() - ttotal;
 
	 /* 6. Output for leaderboard */
	 printf("\n");
	 /* 6.1. Total computation time */
	 printf("Time: %lf\n", ttotal );
 
	 /* 6.2. Results: Statistics */
	 printf("Result: %d, %d", 
		 iter-1, 
		 max_var
		 );
	 int res_row = ( iter-1 < rows-1 ) ? iter-1 : rows-1;
	 int ind;
	 for ( ind=0; ind<6; ind++ )
		 printf(", %d", accessMat( flow, STEPS-1, ind * columns/6 ) );
	 for ( ind=0; ind<6; ind++ )
		 printf(", %d", accessMat( flow, res_row/2, ind * columns/6 ) );
	 for ( ind=0; ind<6; ind++ )
		 printf(", %d", accessMat( flow, res_row, ind * columns/6 ) );
	 printf("\n");
 
	 /* 7. Free resources */	
	 free( flow );
	 free( flow_copy );
	 free( particle_locations );
	 free( particles );
 
	 /* 8. End */
	 return 0;
 }

 __global__ void inlet(int* flowCuda, double* presionesCuda, double* noiseCuda, int columns, int inlet_size, int inlet_pos){
	int globalPos = threadIdx.x + blockIdx.x * blockDim.x;
	if(globalPos < inlet_size){
		accessMat(flowCuda, 0, globalPos + inlet_pos) = (int)(PRECISION * (presionesCuda[(globalPos + inlet_pos)%(4*inlet_size)] + noiseCuda[globalPos]));
	}
}

__global__ void limpiar(int* particle_locationsCuda,int iter,int rows,int columns){
	int j = threadIdx.x + blockIdx.x * blockDim.x;
	int i = threadIdx.y + blockIdx.y * blockDim.y;			
	if(i<=iter && i<rows && j < columns) accessMat( particle_locationsCuda, i, j ) = 0;
}
 
 __global__ void moveParticles(int* flowCuda, Particle* particlesCuda, int num_particles, int rows, int columns){	 
	 int particle =threadIdx.x + blockIdx.x * blockDim.x;		 
	 if(particle<num_particles){			
		 int mass = particlesCuda[ particle ].mass;		 		
		 // Fixed particles
		 if ( mass != 0 ){				
			move_particle( flowCuda, particlesCuda, particle, rows, columns);

	}} 
 }
 
 __global__ void moveParticles2(int* particle_locationsCuda, Particle* particlesCuda, int num_particles, int columns){
	 int i = blockIdx.x * blockDim.x + threadIdx.x;
	 	if(i<num_particles){			
			atomicAdd(&accessMat( particle_locationsCuda, particlesCuda[ i ].pos_row / PRECISION, particlesCuda[ i ].pos_col / PRECISION ), 1);
	 }
 }
 
 __global__ void updateFlow(int* flowCuda, int* flowcopyCuda, int* particle_locationsCuda, Particle* particlesCuda, int num_particles, int columns){
	int i = blockIdx.x * blockDim.x + threadIdx.x;	
	if(i<num_particles){				
		int row = particlesCuda[ i ].pos_row / PRECISION;
		int col = particlesCuda[ i ].pos_col / PRECISION;
		update_flow( flowCuda, flowcopyCuda, particle_locationsCuda, row, col, columns, 0 );
		particlesCuda[ i ].old_flow = accessMat( flowCuda, row, col );
 	}
}

__global__ void updateFlow2(int* flowCuda, int* particle_locationsCuda, Particle* particlesCuda, int num_particles, int columns){
	int i = blockIdx.x * blockDim.x + threadIdx.x;	
	if(i<num_particles){
		int row = particlesCuda[ i ].pos_row / PRECISION;
		int col = particlesCuda[ i ].pos_col / PRECISION;
		int resistance = particlesCuda[ i ].resistance;

		int back = (int)( (long)particlesCuda[ i ].old_flow * resistance / PRECISION ) / accessMat( particle_locationsCuda, row, col );
		atomicSub(&accessMat( flowCuda, row, col ), back); 
	
		atomicAdd(&accessMat( flowCuda, row-1, col ), back / 2);

		if ( col > 0 )
			atomicAdd(&accessMat( flowCuda, row-1, col-1 ) , back / 4);
		else
			atomicAdd(&accessMat( flowCuda, row-1, col ) , back / 4);
		if ( col < columns-1 )
			atomicAdd(&accessMat( flowCuda, row-1, col+1 ) , back / 4);
		else
			atomicAdd(&accessMat( flowCuda, row-1, col ) , back / 4);
	}	
}

__global__ void copiarFlow(int* flow_copyCuda, int* flowCuda, int iter, int rows, int columns){
	int j = threadIdx.x + blockIdx.x * blockDim.x;
	int i = threadIdx.y + blockIdx.y * blockDim.y;	
	if (i < iter && i < rows && j < columns) accessMat( flow_copyCuda, i, j ) = accessMat( flowCuda, i, j );	
}

__global__ void resetMax(int* maxCuda, int tam){
	int x = threadIdx.x + blockIdx.x * blockDim.x;
	if(x < tam){
		maxCuda[x]=0;
	}
}

//propagacion
 __global__ void almacenaMax(int* maxCuda,int* flowCuda,int* flow_copyCuda,int* particle_locationsCuda,int rows,int columns,int iter, int wave_front){
	
	int j = (threadIdx.x + blockIdx.x * blockDim.x) ;
	int i = (threadIdx.y + blockIdx.y * blockDim.y) * STEPS + wave_front;
	if(i < rows && j<columns&& i >= wave_front){
		//printf("ENTROentro con iter=%d en [%d][%d].\n", iter, x, y);
		int var;
		if(i%STEPS==wave_front%STEPS && i<=iter && i!=0 ){
			//printf("entro");
			var = update_flow( flowCuda, flow_copyCuda, particle_locationsCuda, i, j, columns, 1 );
		//if ( var > maxCuda[x*columns+y] ){
			//printf("detecto nuevo maximo= %d.\n", var);	
			maxCuda[(i/STEPS)*columns+j] = var;
		}
		//}	
	}
}


/*
 * CUDA block reduction
 * Obtain the maximum value of an array of integers
 *
 * Inputs: 
 *	Device pointer to an array of integers of any size
 *	Size of the array
 *	Device pointer to an integer array of 1 element to store the result
 * 
 * Launching parameters (3 parameters):
 *	One-dimensional grid with enough blocks
 *	Any valid block size
 *	Dynamic shared memory size equal to: sizeof(int) * block size
 *
 * Output:
 *	The maximum value of the array is stored in *result
 *
 * (c) 2021, Arturo Gonzalez-Escribano
 * Simplification for an assignment in a Parallel Computing course,
 * Computing Engineering Degree, Universidad de Valladolid
 * Academic year 2020/2021
 */
 __global__ void reductionMax(int* array, int size, int *result){
	// Compute the global position of the thread in the grid
	int globalPos = threadIdx.x + blockIdx.x * blockDim.x;

	// Shared memory: One element per thread in the block
	// Call this kernel with the proper third launching parameter
	extern __shared__ int buffer[ ];

	// Load array values in the shared memory (0 if out of the array)
	if ( globalPos < size ) { 
		buffer[ threadIdx.x ] = array[ globalPos ];
	}
	else buffer[ threadIdx.x ] = 0;

	// Wait for all the threads of the block to finish
	__syncthreads();

	// Reduction tree
	for( int step=blockDim.x/2; step>=1; step /= 2 ) {
		if ( threadIdx.x < step )
			if ( buffer[ threadIdx.x ] < buffer[ threadIdx.x + step ] )
				buffer[ threadIdx.x ] = buffer[ threadIdx.x + step ];
		__syncthreads();
	}

	// The maximum value of this block is on the first position of buffer
	if ( threadIdx.x == 0 )
		atomicMax( result, buffer[0] );
}