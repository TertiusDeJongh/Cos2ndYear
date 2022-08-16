#!/bin/bash

yasm -f elf64 -g dwarf2 -l exit.lst task1.asm
ld -o task1 task1.o
./task1 >> results.txt