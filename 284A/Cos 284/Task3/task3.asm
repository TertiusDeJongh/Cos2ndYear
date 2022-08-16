segment .data
	sen1: db "Please input the first number: "
	sen2: db "Please input the second number: "
	
segment .bss
	input1 db 1
	input2 db 1
	
segment .text
	global _start
	
_start:
	mov rax, 1
	mov rdi, 1
	mov rsi, sen1
	mov rdx, 31
	syscall
	
	;get the input
	mov rax, 0
	mov rdi, 0
	mov rsi, input1
	mov rdx, 2
	syscall
	
	mov rax, 1
	mov rdi, 1
	mov rsi, sen2
	mov rdx, 32
	syscall
	
	mov rax, 0
	mov rdi, 0
	mov rsi, input2
	mov rdx, 2
	syscall
	
	mov rax, 48
	sub [input1], rax
	sub [input2], rax
	
	mov rax, [input1]
	add [input2], rax
	
	;add 48 again to input 2
	mov rax, 48
	add [input2], rax
	
	mov rax, 1
	mov rdi, 1
	mov rsi, input2
	mov rdx, 1
	syscall
	
	mov rax, 60
	mov rdi, 0
	syscall