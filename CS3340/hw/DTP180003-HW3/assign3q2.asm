# Drew Pulliam
# DTP180003
# 3340.004
# HW 3 Question 2


	.data
arr:	.word 1,8,3,9,10,10,2,4,0	# arr declaration, 0 is terminator (not part of arr)
str:	.asciiz "array : \n"	
debug1:	.asciiz "arr[i] = "
debug2: .asciiz " item = "
debug3: .asciiz " pos = "
debug4: .asciiz " arr = "
swap1: 	.asciiz "swap arr[pos]: "
swap2: 	.asciiz " & item: "
swap3: 	.asciiz "\n"
	
	.text
main: 	la $a0,arr		# load &arr[0] into $a0
	move $s0,$0		# initalize $s0 = 0
	jal sizeof		# $s0 = n = num elements in array
	la $a0,arr		# load &arr[0] into $a0
	move $a1,$s0		# $a1 = $s0 = n
	jal myfunc		# call myfunc
	li $v0,4		# command to print string
	la $a0,str		# load string address (str) into $a0
	syscall			# call system
	move $t0,$0		# $t0 = i = 0
print:	bge $t0,$s0,end		# if i >= n, end program
	la $t1,arr		# &arr[0]
	mul $t3,$t0,4		# $t3 = $t0 * 4 (for byte addressing)
	addi $t0,$t0,1		# i++
	add $t1,$t1,$t3		# &arr[i]
	lw $a0,($t1)		# print arr[i]
	li $v0,1		# command to print integer
	syscall
	li $v0,11		# command to print char
	li $a0,32		# ascii 32 = " " = SPACE
	syscall
	j print			# next iteration of print loop
end:	li $v0,10		# end program
	syscall
	
	
sizeof:	lw $t0,($a0)		# set $s0 = 0 and put arr address in $a0 before calling
	addi $s0,$s0,1		# increment counter
	addi $a0,$a0,4		# increment arr index
	bne $t0,0,sizeof	# check if current index is last index "0"
	addi $s0,$s0,-1		# subtract 1 from total because 0 is counted initially
	jr $ra			# jump back to main code
	
	
swap:	lw $s2,($a0)
	sw $a1,($a0)		# swap arr[pos] and LOCAL VAR item, NOT arr[start]
	move $s3,$a1
	jr $ra			# jump back to main code
	
	
	# $a0 = arr[], $a1 = n
myfunc:	move $s4,$a0		# $t8 = arr[]
	move $t9,$a1		# $t9 = n
	move $t0,$0		# $t0 = writes = 0
	move $t1,$0		# $t1 = start = 0
	addi $t2,$t9,-2		# $t2 = n - 2
	addi $t1,$t1,-1		# start -1 (so it starts at 0)
for1:	addi $t1,$t1,1		# start++
	bgt $t1,$t2,endfunc	# if start > n -2, end for1 and the entire function
	mul $t3,$t1,4		# $t3 = $t1 * 4 (for byte addressing)
	add $t8,$s4,$t3		# &arr[start]
	lw $t4,($t8)		# $t4 = item = arr[start]
	move $t5,$t1		# $t5 = pos = start
	addi $t6,$t1,1		# $t6 = i = start + 1
for2:	bge $t6,$t9,endf2	# if i >= n, end for2
	mul $t3,$t6,4		# $t3 = $t6 * 4 (for byte addressing)
	add $t8,$s4,$t3		# &arr[i]
	lw $t7,($t8)		# $t7 = arr[i]
	addi $t6,$t6,1		# i++
	bge $t7,$t4,for2	# if arr[i] >= item, go through for2 again
	addi $t5,$t5,1		# if arr[i] < item, pos++
	j for2			# go through for2 again
endf2:	beq $t5,$t1,for1	# no swap needed, go through for1 again
while1:	mul $t3,$t5,4		# $t3 = $t5 * 4 (for byte addressing)
	add $t8,$s4,$t3		# &arr[pos]
	lw $t7,($t8)		# $t7 = arr[pos]
	bne $t4,$t7,endw1	# if item != arr[pos], end while1
	addi $t5,$t5,1		# if item == arr[pos], pos++
	j while1
endw1:	beq $t5,$t1,for1	# if pos == start, go through for1 again
	mul $t3,$t5,4		# $t3 = $t5 * 4 (for byte addressing)
	add $t8,$s4,$t3		# &arr[pos]
	la $t7,($t8)		# $t7 = &arr[pos]
	move $a0,$t7		# $a0 = &arr[pos]
	move $a1,$t4		# $a1 = item
	lw $t4,($a0)		# set LOCAL VAR item = arr[pos]
	sub $sp,$sp,4		# increment stack pointer
	sw $ra,0($sp)		# push $ra to stack
	jal swap		# swap(item, arr[pos])
	lw $ra,0($sp)		# pop stack
	addi $sp,$sp,4		# decrement stack pointer
	addi $t0,$t0,1		# writes++
while2:	beq $t5,$t1,for1	# if pos == start, go through for1 again
	move $t5,$t1		# $t5 = pos = start
	addi $t6,$t1,1		# $t6 = i = start + 1
for3:	bge $t6,$t9,while3	# if i >= n, end for3
	mul $t3,$t6,4		# $t3 = $t6 * 4 (for byte addressing)
	add $t8,$s4,$t3		# &arr[i]
	lw $t7,($t8)		# $t7 = arr[i]
	addi $t6,$t6,1		# i++
	bge $t7,$t4,for3	# if arr[i] >= item, go through for3 again
	addi $t5,$t5,1		# if arr[i] < item, pos++
	j for3			# go through for3 again
while3:	mul $t3,$t5,4		# $t3 = $t5 * 4 (for byte addressing)
	add $t8,$s4,$t3		# &arr[pos]
	lw $t7,($t8)		# $t7 = arr[pos]
	bne $t4,$t7,endw3	# if item != arr[pos], end while3
	addi $t5,$t5,1		# if item == arr[pos], pos++
	j while3
endw3:	beq $t4,$t7,while2	# if pos == start, go through while2 again
	mul $t3,$t5,4		# $t3 = $t5 * 4 (for byte addressing)
	add $t8,$s4,$t3		# &arr[pos]
	la $t7,($t8)		# $t7 = &arr[pos]
	move $a0,$t7		# $a0 = &arr[pos]
	move $a1,$t4		# $a1 = item
	lw $t4,($a0)		# set LOCAL VAR item = arr[pos]
	sub $sp,$sp,4		# increment stack pointer
	sw $ra,0($sp)		# push $ra to stack
	jal swap		# swap(item, arr[pos])
	lw $ra,0($sp)		# pop stack
	addi $sp,$sp,4		# decrement stack pointer
	addi $t0,$t0,1		# writes++
	j while2
endfunc:jr $ra
	



