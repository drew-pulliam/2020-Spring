# Drew Pulliam
# DTP180003
# 3340.004
# HW 5 Question 1

	.data
days:	.asciiz "For how many days do you have sales amounts? "
saleDay:.asciiz "Enter the sales for the day "
totSale:.asciiz "The total sales are $"
colon:	.asciiz ": "
	
	.text
main: 	move $s0,$0		# $s0 = days = 0
	la $a0,days		# print days
	li $v0,4
	syscall
	li $v0,5		# read int into $v0
	syscall
	move $s0,$v0		# $s0 = days = cin
	addi $t0,$0,1		# $t0 = count = 1
for:	bgt $t0,$s0,end		# if count > days, finish program
	la $a0,saleDay		# print saleDay
	li $v0,4
	syscall
	move $a0,$t0		#print count
	li $v0,1
	syscall
	la $a0,colon		# print colon
	li $v0,4
	syscall
	li $v0,6		# read float into $f0 = user input sales
	syscall
	add.s $f1,$f1,$f0	# $f1 = total += sales
	addi $t0,$t0,1		# count++
	j for			# next iteration loop
end:	la $a0,totSale		# print totSale
	li $v0,4
	syscall
	mov.s $f12,$f1		# print float total
	li $v0,2
	syscall
	li $v0,10		# end program
	syscall
