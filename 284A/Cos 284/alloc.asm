segment .data
	struc Trie;i don't know the size value of this....
		T_children resq	26	;this odd tho... 26*8.... so 208 bytes...
		T_isWord resd	26	;no idea when to align....
	endstruc
	trie	dq	0

segment .text
	extern malloc
	global trieAlloc
	
	trieAlloc:
		;now to allocate data for the trie....
		.trie	equ 0
		.whatever	equ	8
		push rbp
		mov rbp, rsp
		sub rsp, 16	;the usual...
		;call malloc and return...
		mov rdi, Trie_size
		call malloc
		;trie saved in rax... return??/
		leave	;why not blue???
		ret		;return rax already....
	
	global trieInit
	trieInit:
		.trie	equ 0
		.whatever	equ	8
		push rbp
		mov rbp, rsp
		sub rsp, 16	;the usual...
		;loop over the pointer array & word array...:
		xor rcx, rcx	;gonna be my index
		;xor r12, r12	;gonna be 8
		;xor r13, r13	;gonna be 1
		;mov r12, 8
		;mov r13, 4	;for the chars???
		pointLoop:
			mov qword[rdi+rcx*8], 0
			mov	byte[rdi+T_isWord+rcx], 0
			inc rcx
			cmp rcx, 25
			jl pointLoop
			
		leave	;why not blue???
		ret
		
		