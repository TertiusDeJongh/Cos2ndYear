anotherLoop:
	xor al, al
	movzx ax, byte[car+r9]
	;cmp ax, 10
	;je End
	inc r9
	div byte[divi]
	add ah, 48
	mov [final], ah
	add al, 48
	mov [final2], al
	
	cmp al, 48
	je Print2 ;take away zero padding for one digit numbers...
	
	mov rax, 1	
	mov rdi, 1	
	mov rsi, final2
	mov rdx, 1
	syscall
	
Print2:
	mov rax, 1	
	mov rdi, 1	
	mov rsi, final
	mov rdx, 1
	syscall
	
	cmp byte[car+r9], 10 
	je End

	cmp byte[car+r9], 0 
	jne anotherLoop
	
	
	
	
PushL:
	movzx rax, [final2]
	cmp rax, 0
	je move_on
	xor rbx, rbx
	mov rbx, 10
	xor rdx, rdx
	div rbx		;literally this fucking line
	add rdx, 48
	push rdx
	mov [final2], rax
	xor rax, rax 	;this should work.....
	inc r9
	jmp PushL
	
	;now move 
move_on:
	mov rax, 1	
	mov rdi, 1	
	mov rsi, sen2
	mov rdx, 18
	syscall
	
PopL:
	pop rdx
	xor rax, rax
	mov rax, rdx
	mov byte[final], al
	
	mov rax, 1
	mov rdi, 1
	mov rsi, final
	mov rdx, 1
	syscall
	
	dec r9
	cmp r9, 0
	jne PopL