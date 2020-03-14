	.text
main: 	lw $t0,a
	lw $t1,b
	
	sll $t2, $t0, 4
	or $t2, $t2, $t1
	nor $t2, $t2, $t1
	slt $t0, $t2, $t1
	sltu $t0, $t2, $t1
	srl $t2, $t0, 3
	addi $t2, $t2, 7 
	
	li $v0,10	#end program
	syscall
		
	.data
a:	.word 0xAAAAAAAA
b:	.word 0x87654321
