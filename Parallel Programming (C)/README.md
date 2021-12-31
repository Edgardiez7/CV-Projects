This project was done for the subject Parallel Programming.

The goal was optimizing the execution of a C program (wind.c) which calculated the movement and propagation of particles in a wind tunnel.

A 3 step optimization method was implemented, using the OpenMP library (wind_openmp.c), then the MPI library (wind_mpi.c) and finally with CUDA programming (wind_cuda.c).

-OpenMp: from 2min 50s to 19.04s ---> 90% optimization

-MPI: from 2min 50s to 18.99s ---> 90% optimization

-CUDA: from 7min to 27.98s ---> 99.3% optimization