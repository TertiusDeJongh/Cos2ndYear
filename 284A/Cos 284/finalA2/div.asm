segment .data
	sen1: db "Please input the first number: "
	sen2: db "Please input the second number: "
	divi dq 10
	
	num db 0
	num2 db 0
	remainderR db "r"
	
segment .bss
	input1 db 1
	input2 db 1
	newEnter dq 1
	
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
	mov rdx, 1
	syscall
	
	mov rax, 0
	mov rdi, 0
	mov rsi, newEnter
	mov rdx, 1
	syscall
	
	mov rax, 1
	mov rdi, 1
	mov rsi, sen2
	mov rdx, 32
	syscall
	
	mov rax, 0
	mov rdi, 0
	mov rsi, input2
	mov rdx, 1
	syscall
	
	mov rax, 0
	mov rdi, 0
	mov rsi, newEnter
	mov rdx, 1
	syscall
	
	mov ax, 48
	sub [input1], ax
	sub [input2], ax
	;mov rax, 0
	
	movzx ax, byte [input1]
	;mov dx, [input2]
	;mov rax, [input2]
	;add [input1], rax
	mov dx, 0
	;mov bl, [input2]
	div byte [input2]	;why are yu so difficult??
	add ah, 48
	add al, 48 
	
	;al has the qutient and ah has the remainder... remember
	mov [num], al  
	mov [num2], ah
	
	mov rax, 1
	mov rdi, 1
	mov rsi, num
	mov rdx, 1
	syscall
	
	mov rax, 1
	mov rdi, 1
	mov rsi, remainderR
	mov rdx, 1
	syscall
	
	mov rax, 1
	mov rdi, 1
	mov rsi, num2
	mov rdx, 1
	syscall
	;
	mov rax, 60
	mov rdi, 0
	syscall