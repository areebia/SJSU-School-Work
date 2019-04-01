#include "memdb.h"
#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <sys/stat.h>
/*
 * Author: Areeb Yaqub
 * 
 * Simple database that takes in a linked list of info, sorts it and puts into a file.
 * 
 * 
 * Help taken from: https://www.geeksforgeeks.org/linked-list-set-1-introduction/
 * 
 * People who helped with concepts: Isabelle Delmas, Sonnan Naeem
 * 
 * 
 * 
 * 
 * 
 */




// node for linked list

// POINTER ARTHMETIC ERRORS IF YOU ADD AN INTEGER TO A POINTER, USE THE ASTERISK. OTHERWISE THE POINTER ADDRESS GETS ADDED TO, NOT THE ITEM OF THE POINTER. 

void add(char str, FILE f ){
    //add then sort by the offsets
    //strcmp
    
    
}

void list(struct entry_s node, FILE f){
      
       // Isabelle helped with this
      while(node.magic == ENTRY_MAGIC_DATA){
         struct entry_s entry_ptr = 
          struct entry_s* nextData_ptr = entry_ptr->next + (char*)file_ptr
          printf("%s\n", struct entry_s str)
          
      }
    
}

int main(int argc, char *argv[]){
    
    // checks for larger arguements
    if (argc < 2) {
        printf("USAGE: %s strings_file [string to add]\n", argv[0]);
        exit(1);
    }

    
    // makes the file and checks for file problems
    int fd = open(argv[1], O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
    if (fd == -1) {
        perror(argv[1]);
        exit(2);
    }

    
    // sets the file header to the mmap location and checks any errors on that as well. 
    struct fhdr_s *fhdr = mmap(NULL, 1024*1024, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if ((void *)fhdr == (void *)-1) {
        perror("mmap");
        exit(3);
    }

    // makes random struct to store information
    struct stat s;
    if (fstat(fd, &s) == -1) {
        perror("fstat");
        exit(4);
    }

    
    // based on the the struct stat s, gets info about the file 
    off_t size = s.st_size;

    // the new file gets increased and the checks for errors
    if (size == 0) {
        // new file
      
        if (ftruncate(fd, 1024) == -1) {
            perror("ftruncate");
            exit(5);
        }
        //sets the new size while also setting the magic number
        size = 1024;
        fhdr->magic = FILE_MAGIC;
        // no strings, so point to the end of the header
        fhdr->free_start = sizeof(*fhdr);
    } 
    else {
        if (fhdr->magic != MAGIC) {
            fprintf(stderr, "bad magic: not a strings file!\n");
            exit(6);
        }
    }
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    while(true)
    {
        char str[100], command[1];
        
        scanf("%s %s", command, str); //"l l" input still required for listing only
        
        if (command[0] == 'a') 
        {
            printf("getting ready to add: %s\n", str);
            for(int i = 0; i < s.st_size ; i++){
                
                
            }
            
        }
        else if(command[0]== 'l' )
        {
            printf("getting ready to list\n");
        }
    }
    
    
}
