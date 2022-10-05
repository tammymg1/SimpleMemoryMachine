.pos 0x100
		ld $g, r0		# r0 = address of g
		ld (r0), r1		# r1 = g
		inc r1			# r1 = r1 + 1
		inca r1			# r1 = r1 + 4
		shl $0x3, r1		# r1 = r1 << 3
		ld (r0), r2		# r2 = g
		and r2, r1		# r1 = r1 & g
		shr $0x2, r1		# r1 = r1 >> 2
		ld $i, r2		# r2 = address of i
		st r1, (r2)		# i = r1
                halt                    # halt
.pos 0x1000
i:               .long 0x00000000       # i
.pos 0x2000
g:               .long 0x00000010       # g