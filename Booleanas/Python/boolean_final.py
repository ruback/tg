import time

first_iteration = True

def opt_false():
	print "I'm false"
	return False

def opt_true():
	print "I'm true"
	return True

def true_long_function():
	a = 0
	for i in range(10000):
		a += 1;
	#if (first_iteration):
	#	print "I've Ran! TL"
	if (a > 1):
		return True
		
def false_long_function():
	a = 0
	for i in range(10000):
		a += 1;
	#if (first_iteration):
	#	print "I've Ran! FL"
	if (a > 1):
		return False;

def true_short_function():
  return True;

def false_short_function():
  return False;

def or_f(first, second):
	return (first() or second())

def and_f(first, second):
	return (first() and second())

def and_not_demorgan(first, second):
	return not (first() and second())

def and_demorgan(first, second):
	return not first() or not second()

def or_not_demorgan(first, second):
	return not (first() or second())

def or_demorgan(first, second):
	return not first() and not second()

def mult_not_factored_2(first, second):
	return mult_not_factored(first, first, second)

def mult_factored_2(first, second):
	return mult_factored(first, first, second)
	
def mult_not_factored(first, second, third):
	return (first() or second() or third()) and (first() or second() or third())
	
def mult_factored(first, second, third):
	return (first() and first()) or (first() and second()) or (first() and third()) or (second() and first()) or (second() and second()) or (second() and third()) or (third() and first()) or (third() and second()) or (third() and third())
	
def test(operator, first, second):
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		is_working = False
		begin = time.clock()
		for i in range(1000):
			if operator(first, second):
				is_working = True
			first_iteration = False
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Working?: " + str(is_working)
	print "Mean: " + str(mean)
	print ""
	
def or_test(first, second):
	test(or_f, first, second)
	
def and_test(first, second):
	test(and_f, first, second)

def and_not_demorgan_test(first, second):
	test(and_not_demorgan, first, second)

def and_demorgan_test(first, second):
	test(and_demorgan, first, second)
	
def and_demorgan_test(first, second):
	test(and_demorgan, first, second)
	
def and_demorgan_test(first, second):
	test(and_demorgan, first, second)
	
def mult_not_factored_test(first, second):
	test(mult_not_factored_2, first, second)
	
def mult_factored_test(first, second):
	test(mult_factored_2, first, second)

def optimizations_tests():
	print "OPTIMIZATIONS TESTS"
	temp = False
	print "Or - True/True"
	temp = (opt_true() or opt_true())
	print "Or - True/False"
	temp = (opt_true() or opt_false())
	print "Or - False/True"
	temp = (opt_false() or opt_true())
	print "Or - False/False"
	temp = (opt_false() or opt_false())
	print "And - True/True"
	temp = (opt_true() and opt_true())
	print "And - True/False"
	temp = (opt_true() and opt_false())
	print "And - False/True"
	temp = (opt_false() and opt_true())
	print "And - False/False"
	temp = (opt_false() and opt_false())
	print ""
	print ""
	
def performance_tests():	
	print "Warm Up"
	or_test(true_short_function,true_short_function)
	print "Or - True/True - Short/Long"
	or_test(true_short_function,true_long_function)
	print "Or - True/True - Long/Short"
	or_test(true_long_function,true_short_function)
	print "Or - False/True - Long/Long"
	or_test(false_long_function,true_long_function)
	print "Or - True/False - Long/Long"
	or_test(true_long_function,false_long_function)
	
	print "And - False/False - Short/Long"
	and_test(false_short_function,false_long_function)
	print "And - False/False - Long/Short"
	and_test(false_long_function,false_short_function)
	print "And - False/True - Long/Long"
	and_test(false_long_function,true_long_function)
	print "And - True/False - Long/Long"
	and_test(true_long_function,false_long_function)

	print "And Not De Morgan - False/True - Long/Long"
	and_not_demorgan_test(false_long_function,true_long_function)
	print "And Not De Morgan - True/True - Long/Long"
	and_not_demorgan_test(true_long_function,true_long_function)
	print "And De Morgan - False/True - Long/Long"
	and_demorgan_test(false_long_function,true_long_function)
	print "And De Morgan - True/True - Long/Long"
	and_demorgan_test(true_long_function,true_long_function)
	
	print "Non Factored - False/True - Long/Long"
	mult_not_factored_test(false_long_function,true_long_function)
	print "Factored - False/True - Long/Long"
	mult_factored_test(false_long_function,true_long_function)
	print "Non Factored - True/False - Long/Long"
	mult_not_factored_test(true_long_function,false_long_function)
	print "Factored - True/False - Long/Long"
	mult_factored_test(true_long_function,false_long_function)

def main():
	optimizations_tests()
	performance_tests()

if __name__ == "__main__":
  main()