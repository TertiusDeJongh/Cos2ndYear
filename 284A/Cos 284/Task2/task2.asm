segment .data
	sen1: db "Please input as lowercase character: "
	sen2: db "In uppercase: "
	
segment .bss
	lowUp: db 1
	
segment .text
	global _start
_start:
	mov rax, 1
	mov rdi, 1
	mov rsi, sen1
	mov rdx, 37
	syscall
	
	;enter the thing
	mov rax, 0
	mov rdi, 0
	mov rsi, lowUp
	mov rdx, 1
	syscall
	
	mov rax, 1
	mov rdi, 1
	mov rsi, sen2
	mov rdx, 14
	syscall
	
	;lets subtract from the var
	mov rax, 32
	sub [lowUp], rax 	;subtract the value to get uppercase
	
	mov rax, 1
	mov rdi, 1
	mov rsi, lowUp
	mov rdx, 1
	syscall
	
	mov rax, 60
	mov rdi, 0
	syscall