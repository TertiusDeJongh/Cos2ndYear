insertLoop:
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
			mov r15, qword[r15+T_children+r13*8];curr = curr.children[letterindex]
			inc r12	;the above line gos one too far...
			cmp r12, r9
			jl insertLoop
			
		endLoop:
			;now we set somthing to one???
			mov byte[r15+T_isWord+r13], 1
			leave 
			ret