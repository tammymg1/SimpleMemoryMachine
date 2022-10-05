.pos 0x100 
         ld $w, r0		# r0 = address of w
		 ld (r0), r1		# r1 = w
		 ld $z, r2		# r2 = address of z[0]
		 ld (r2, r1, 4), r3     # r3 = z[w]
		 inc r1		    # r1 = w + 1
         inc r1         # r1 = w + 2
		 ld (r2, r1, 4), r4     # r4 = z[w + 2]
		 ld $f, r5		# r5 = address of f
		 add r3, r4		# r4 = r3 + r4
		 st r4, (r5)		# f = r4
		 ld $h, r6		# r6 = address of h
		 ld $0xff, r7      # r7 = 0xff
		 and r7, r4		# r4 = r4 & 0xff
		 st r4, (r6)		# h = r4
		 halt			# halt
.pos 0x1000
w:		 .long 0x00000000	# w
.pos 0x1004
h:		 .long 0x00000000	# h
.pos 0x1008
f: 		 .long 0x00000000	# f
.pos 0x2000
z:       .long 0x00000001	# z[0]
		 .long 0x00000000	# z[1]
		 .long 0x00000002	# z[2]
		 .long 0x00000000	# z[3]
		 .long 0x00000000	# z[4]
		 .long 0x00000000	# z[5]
		 .long 0x00000000	# z[6]
		 .long 0x00000000	# z[7]
		 .long 0x00000000	# z[8]
		 .long 0x00000000	# z[9]