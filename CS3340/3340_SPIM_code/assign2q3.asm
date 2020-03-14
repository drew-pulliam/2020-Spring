	.text
main: 	li $t1, 10
LOOP: 	slt $t2, $0, $t1
	beq $t2, $0, DONE
	addi $t1, $t1, -1
	addi $s2, $s2, 2
	j LOOP
DONE: 	

end:	sw $t3, 0($t1)
	
	li $v0,10	#end program
	syscall
