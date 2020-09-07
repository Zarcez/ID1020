#include <stdio.h>

/**
 * Program that reads characters until a new line is read and the prints them in reverse order
 */

#define LIMIT 8

/**
 * Reads all the characters onto an array until a new line is reached or the array is filled.
 * Afterwards prints the value in a reverse order
 */
int iteration(){
    int size = 0;
    int ch;
    char array [LIMIT+1];

    while(((ch = getchar()) != '\n') && size < LIMIT ){
        array[size] = ch;
        size++;
    }
    for(int i = size-1; i >= 0; i--){
        putchar(array[i]);
    }
    return 0;
}

/**
 * Read a character, if the character is not a new line, call the function again and repeat.
 * If a new line is read, print out all characters read so far
 */
int recursive(){
    int ch = getchar();
    if(ch != '\n')
        recursive();
    putchar(ch);
    return 0;
}

/**
 * Call both method to try them both
 */
int main() {
    recursive();
    iteration();

    return 0;
}