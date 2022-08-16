segment .data
	struc Trie;i don't know the size value of this....
		T_children resq	26	;this odd tho... 26*8.... so 208 bytes...
		T_isWord resd	26	;no idea when to align....
	endstruc
	curr		dq	0
	words	dq	0
	string	dd	""
segment .text
	extern trieAlloc, trieInit
	global trieInsert
	trieInsert:
		;r12-r15 is perserved
		;receive trie in rdi and char array in rsi...
		;count the letter in the word first....
		stringL 	equ 0
		index	equ	8
		push rbp
		mov rbp, rsp
		sub rsp, 16
		
		xor r15, r15
		mov r15, rdi	;move the trie root into the current...
		xor r9, r9
		xor r13, r13	
		mov r14, rsi
		countLoop:
			mov r13b, byte[r14+r9]
			inc r9
			cmp r13b, 0	;compare to null
			jne countLoop
		;r9 has the size now....
		dec r9	;now its the size... for the indexes

		xor r12, r12
		xor r13, r13
		mov r14, rsi	;are you sure????
		insertLoop:
			;mov r10, r12
			;inc r10
			;cmp r12, r9
			;je endLoop
		
			mov r13b, byte[r14+r12]
			sub r13b, 'a'	;this how we sub??? this the index of letter
			
			
			;from scratch
			cmp qword[r15+T_children+r13*8], 0;
			jne next
			;create new thingy...
			mov [rsp+index], r9	;to preserved it...
			call trieAlloc
			mov rdi, rax
			call trieInit
			mov r9, qword[rsp+index]
			mov qword[r15+T_children+r13*8], rax;curr.children[lettrindex] = new trieNode
			next:;we make the curr equal to the new child
			inc r12	;the above line gos one too far...
			cmp r12, r9
			jge endLoop
			mov r15, qword[r15+T_children+r13*8];curr = curr.children[letterindex]
			jmp insertLoop
			
			
		endLoop:
			;now we set somthing to one???
			mov byte[r15+T_isWord+r13], 1
			leave 
			ret
			
