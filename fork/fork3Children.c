/********************************************************************
 Author: Jordan A. Caraballo Vega
 Operating Systems, Prof. Jose Sotero Esteva
 Objective: Use fork to create one parent and three children processes.
 References: A. Silberschatz, Operating Systems Concepts (9th Edition).
             http://www.csl.mtu.edu/cs4411.ck/www/NOTES/process/fork/create.html
             http://pubs.opengroup.org/onlinepubs/009695399/functions/getppid.html
*********************************************************************/
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int main() {

    /* Get the pid from the initial process (dad) */
    printf( "Hola Usuario! Yo soy el padre con pid = %d\n", getpid() );

    /* Define the number of children that you want and call the forks */
    int numChild = 3; // number of children wanted
    
    /* Iterate and fork over the children */
    for (int i = 0; i < numChild; i++) {
        if (fork() == 0) {
            /* Use getpid and getppid to get parent and son pids */
            wait(NULL); // wait is given a NULL attribute
            printf( "Hola Papa con pid %d! Yo soy tu hijo con pid = %d\n", getppid(), getpid() );
            exit(0); // exit from son execution
        }
    }
    return 0;
}
