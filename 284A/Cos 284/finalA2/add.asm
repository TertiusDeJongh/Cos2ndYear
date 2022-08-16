segment .data
	sen1: db "Please input the first number: "
	sen2: db "Please input the second number: "
	divi dq 10
	num dq 0
	num2 dq 0
	
segment .bss
	input1 dq 1
	input2 dq 1
	
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
	
	
	;divide by 10 to get q and remainder... think about it
	mov rax, [input2]
	
	mov rdx, 0
	idiv qword [divi]
	;numbers in  rdx and rax
	;convert back to characters
	add rax, 48
	add rdx, 48 
	
	mov [num], rax
	mov [num2], rdx
	
	mov rax, 1
	mov rdi, 1
	mov rsi, num
	mov rdx, 1
	syscall
	
	mov rax, 1
	mov rdi, 1
	mov rsi, num2
	mov rdx, 1
	syscall
	
	mov rax, 60
	mov rdi, 0
	syscall