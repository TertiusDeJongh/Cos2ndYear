segment .data
	sen: db "Please input a 5 digit number: "
	sen2: db "This is the number you are looking for: "

section .bss
	num resb 5

section .text 
	global _start
	
_start:
	mov rax, 1	
	mov rdi, 1	
	mov rsi, sen	
	mov rdx, 31	
	syscall
	
	;now get the bloody thing
	mov rax, 0
	mov rdi, 0
	mov rsi, num
	mov rdx, 5
	syscall
	
	;print the second thing on screen
	mov rax, 1	
	mov rdi, 1	
	mov rsi, sen2
	mov rdx, 40	
	syscall
	
	
	mov rax, 1	
	mov rdi, 1	
	mov rsi, num
	mov rdx, 5	
	syscall
	
	mov rax, 60
	mov rdi, 0
	syscall