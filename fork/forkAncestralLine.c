/********************************************************************
 Author: Jordan A. Caraballo Vega
 Operating Systems, Prof. Jose Sotero Esteva
 Objective: Use fork to create a parent and children processes ancestral line.
            Parent, son, younger son, yongest son
 References: A. Silberschatz, Operating Systems Concepts (9th Edition).
             http://www.csl.mtu.edu/cs4411.ck/www/NOTES/process/fork/create.html
             http://pubs.opengroup.org/onlinepubs/009695399/functions/getppid.html
 TODO: Make this program run for N generations (foor loop - did not have time to finish it).
*********************************************************************/
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int main() {

    /* Define the number of ancesters that you want and call the forks */
    int numAnces = 3; // number of children wanted
    int integer  = 5; // value to prove stack local values
    printf( "Se definio variable local tipo entero = 5.\n");
    
    /* Get the pid from the initial process (dad) */
    printf( "Papa     con pid %d. ", getpid() );
    printf("Stack Test: Sumando %d + 0 = %d\n", integer, integer + 0); // stack test
    
    /* Iterate and fork over each children */
    if (fork() == 0) {  /* New son from previous parent */
	    printf("Hijo     con pid %d. Papa con pid %d. ", getpid(), getppid());
	    printf("Stack Test: Sumando %d + 1 = %d\n", integer, integer + 1); // stack test
        if (fork() == 0) {  /* New son from previous parent */
	        printf("Nieto    con pid %d. Papa con pid %d. ", getpid(), getppid());
	        printf("Stack Test: Sumando %d + 2 = %d\n", integer, integer + 2); // stack test
	        if (fork() == 0) {  /* New son from previous parent */
	            printf("Bisnieto con pid %d. Papa con pid %d. ", getpid(), getppid());
	            printf("Stack Test: Sumando %d + 3 = %d\n", integer, integer + 3); // stack test
            }
        }
    }
    return 0;
}
