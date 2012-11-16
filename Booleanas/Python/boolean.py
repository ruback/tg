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
	
def or_test(first, second):
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		is_working = False
		begin = time.clock()
		for i in range(100):
			if (first() or second()):
				is_working = True
			first_iteration = False
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Working?: " + str(is_working)
	print "Mean: " + str(mean)
	print ""
	
def and_test(first, second):
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		is_working = False
		begin = time.clock()
		for i in range(100):
			if (first() and second()):
				is_working = True
			first_iteration = False
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Working?: " + str(is_working)
	print "Mean: " + str(mean)
	print ""

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

def main():
	optimizations_tests()
	performance_tests()

if __name__ == "__main__":
  main()