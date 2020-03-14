	.text
main: 	la $t0,60($s0)
	la $t1,36($s0)
	move $t2,$zero
loop:	bne $t0,$t1,end
	lw $t3,0($t0)
	add $t2,$t2,$t3
	addi $t0,$t0,-4
	j loop	
end:	sw $t3, 0($t1)
	
	li $v0,10	#end program
	syscall