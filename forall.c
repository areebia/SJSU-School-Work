
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <assert.h>
#include <sys/wait.h>
#include <signal.h>
/* cs149
 * Author: Areeb Yaqub
 * 
 * 
 * Base of code taken from the chapter 5 p4 code 
 *
 * 
 * Help received from: Isabelle Delmas, Trevor Glassey
 */

void cHandler(int signum)
{
    printf(" Signaling %d\n", getpid());   // probably the parent pid
}

void quitHandler(int signum)
{
    printf(" Signaling %d\n", getpid());  // probably the parent pid
    printf("Exiting due to quit signal\n");
    exit(1);
}

int main(int argc, char *argv[])
{
    signal(SIGINT, cHandler);
    signal(SIGQUIT, quitHandler);

    for(int i = 1; i < argc-1; i++)
    {
        // creating the multiple files needed
        char fname[argc];
        sprintf(fname, "%d.txt", i);
        FILE *f = fopen(fname, "w+");
        fprintf(f, "Executing %s: %s\n", argv[1], argv[i+1]);
        
        // the main forking part
        int rc = fork();
        
        if (rc < 0) {
            // fork failed; exit
            fprintf(stderr, "fork failed\n");
            exit(1);
        } 
        else if (rc == 0) {
            // child: redirect standard output to a file
           
            close(STDOUT_FILENO); 

            // now exec "wc"...
            
            execlp(argv[1], argv[1], argv[i+1], NULL);  // runs word count
        
        }
        else {
            // parent goes down this path (original process)
            int wc = wait(NULL);
            assert(wc >= 0);
            fprintf(f, "Finished Executing %s: %s\n", argv[1], argv[i+1]);
            
        }
        fclose(f);
    }
         
    
    return 0;
} 



// notes: sprintf



