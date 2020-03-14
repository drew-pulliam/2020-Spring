# Drew Pulliam
# DTP180003
# 3340.004
# HW 2 Question 5
	
	.text
main: 	li $t0, 0		# int num = 0;
	li $t3, 0		# int res; default to 0
	move $t1,$sp 		# save inital stack address
	sub $sp,$sp,4		# increment stack pointer
	sw $t0,0($sp)		# push $t0 to stack
func:	bgt $t0, 10, done	# check if $t0 > 10, if so: done, else: push it to the stack
	sub $sp,$sp,4		# increment stack pointer
	sw $t0,0($sp)		# push $t0 to stack
	addi $t0,$t0,1		# increment $t0
	j func			# jump to func
done:	beq $sp, $t1, print	# finished taking everything off stack? jump to print
	lw $t2,0($sp)		# pop stack
	addi $sp,$sp,4		# decrement stack pointer
	add $t3,$t3,$t2		# add stack to res
	j done			# jump to done
print:	move $a0,$t3		# put res into print register
	li $v0,1		# command to print integer
	syscall			# call system
	li $v0,10		# command to exit code
	syscall			# call system
