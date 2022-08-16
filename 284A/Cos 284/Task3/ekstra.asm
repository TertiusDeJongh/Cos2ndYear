;now sub the two
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