#include <stdio.h>
#include <stdlib.h>

int main (){
    
    
    void *ptr =malloc(16); // will cause break
    free(ptr);
   
    for (int i = 0; i< 1000; i++)
    {
        ptr = malloc(1000); //does the allocation for 1600 bytes
        free(ptr);
    }
    
    ptr = malloc(1024*1024); //any changes to meory are reflected in file
    free(ptr);
    for (int i = 0; i< 1000; i++)
    {
        ptr = malloc(1000); //does the allocation for 1600 bytes
        free(ptr);
    }
    
    return 0;
    
}


// mmap allocates big chunk of memory.
