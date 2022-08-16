segment .data
	sen: db "Please input the shift degree: "
	sen2: db "Please input the string to decode: "
	sen3: db "Decoded text: "
	;stringIn: 	dq 	0
	stringEnc: dq 	""
	newLine: 	dq 	10
	shiftDeg: 	dq 	0
	fortyE:	dq 	48
	Tsix:		dq	26
	garbageNewline:	dq	''
	
section .bss
	stringIn resb 100

section .text 
	global _start
	
_start:
	mov rax, 1	
	mov rdi, 1	
	mov rsi, sen	
	mov rdx, 31
	syscall
	
	;input the stuff
	mov rax, 0	
	mov rdi, 0	
	mov rsi, shiftDeg
	mov rdx, 2	
	syscall
	
	mov rax, 0	
	mov rdi, 0	
	mov rsi, garbageNewline
	mov rdx, 1
	syscall
	
	mov rax, 1	
	mov rdi, 1	
	mov rsi, sen2	
	mov rdx, 35
	syscall
	
	mov rax, 0	
	mov rdi, 0	
	mov rsi, stringIn
	mov rdx, 100
	syscall
	
	
	mov rax, 1	
	mov rdi, 1	
	mov rsi, sen3
	mov rdx, 14
	syscall
	
	;this is where the fun begins...
	;lets convert the shift degree
	xor r9, r9
	xor r8, r8
	xor r10, r10	;the counter
Convert:
	mov r9b, byte[shiftDeg + r10]
	inc r10
	mov r8b, byte[shiftDeg + r10]
	sub r9b, [fortyE]
	sub r8b, [fortyE]
	;mov r9b, r8b
	;now they should have soemthing...
	;number can't go highter than 26...
	imul r9, [newLine]	;I mean it is ten...
	add r8b, r9b	;now i should have the correct num
	mov [shiftDeg], r8b	;maybe not what we wnat to do... variables are weird...
	
	xor r10, r10
	xor r11, r11
	xor rax, rax
Encode:	;now we should add the degree... lets make it easy first
	;xor r11b, r11b
	mov r11b, byte[stringIn+r10]
	cmp byte[stringIn+r10], 10
	je End
	cmp r11b, 32
	je print
		;here we make nested loop
	xor r9, r9	;our counter
	NestedLoop:	;here we add one/increment if it is not equal to z
		cmp r11b, 97
		jle RoundWeGo
		Back:dec r11b
		inc r9b
		cmp r9b, r8b
		jne NestedLoop
	;cmp r11b, 123
	;jg RoundWeGo
	
	
print:
	mov [stringEnc], r11b
	
	mov rax, 1	
	mov rdi, 1	
	mov rsi, stringEnc	
	mov rdx, 1
	syscall
	
	inc r10
	cmp byte[stringIn+r10], 0
	jne Encode
	
End:	mov rax, 60
	mov rdi, 0
	syscall
	
RoundWeGo:
	add r11b, [Tsix]
	jmp Back