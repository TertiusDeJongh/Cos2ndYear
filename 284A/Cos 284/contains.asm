segment .data
	struc Trie;i don't know the size value of this....
		T_children resq	26	;this odd tho... 26*8.... so 208 bytes...
		T_isWord resd	26	;no idea when to align....
	endstruc
	curr		dq	0
	words	dq	0
	string	dd	""
segment .text
	extern trieAlloc, trieInit, trieInsert;just for incase???
	global trieContains
	trieContains:
		;we start here??
		stringL 	equ 0
		index	equ	8
		push rbp
		mov rbp, rsp
		sub rsp, 16
		
		xor r9, r9
		xor r12, r12;current
		xor r13, r13;word
		xor r14, r14
		mov r12, rdi
		mov r13, rsi;correct sizes??? lets experiment with it then...
		countLoop:
			mov r14b, byte[r13+r9]
			inc r9
			cmp r14b, 0	;compare to null
			jne countLoop
		dec r9
		xor r10, r10;index
		xor r14, r14;temp var
		forEachLet:
			mov r14b, byte[r13+r10]
			sub r14b, 'a'
			
			LLoop:
			inc r10; my counter
			cmp r10, r9; compare to the size of the word
			jge end
			
			cmp qword[r12+T_children+r14*8], 0;don't question the children part
			je elseIf
			
			mov r12, qword[r12+T_children+r14*8]
			jmp forEachLet
			
			end:
			mov rax, 1
			cmp byte[r12+T_isWord+r14], 1
			je next
			mov rax, 0
			;mov al, byte[r12+T_isWord+r14]
			next:;end
			leave
			ret
			
			elseIf:
			mov rax, 0
			leave
			ret
			