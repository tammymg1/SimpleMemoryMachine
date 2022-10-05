#include <stdlib.h>
#include <stdio.h>
#include <sys/mman.h>

void *p;

int main() {
    void *v = malloc (1024 * 1024 * 1024);
    printf("%p\n", v);


    
}