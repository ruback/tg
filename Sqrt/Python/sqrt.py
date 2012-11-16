import math, time
from ctypes import c_float, c_long, byref, POINTER, cast

def fast_sqrt0(num):
	return fast_sqrt(num,0)

def fast_sqrt1(num):
	return fast_sqrt(num,1)
	
def fast_sqrt2(num):
	return fast_sqrt(num,2)

def fast_sqrt3(num):
	return fast_sqrt(num,3)

def fast_sqrt(num,opt):
	half_x = 0.5*num
	x = c_float(num)
	i = cast(byref(x), POINTER(c_long)).contents.value
	i = c_long(0x5f375a86 - (i>>1))
	x = cast(byref(i), POINTER(c_float)).contents.value

	for j in range(opt):
		x = x*(1.5-half_x*x*x)
	return x * num
	
def mypow(x):
	return pow(x,.5)
	
def asqasq(x):
	return x**.5
	
def performance_test(func,reps):
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		begin = time.clock()
		for i in range(reps):
			func(1.312371232)
			func(3.254536726)
			func(15.42342343)
			func(1359.234233)
			func(13214124.23)
			func(8989634345.0)
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Mean: " + str(mean)
	print ""
	
def performance():
	print "Performance Tests"
	print ""
	print "sqrt"
	performance_test(math.sqrt,100000)
	print "x**.5"
	performance_test(asqasq,100000)
	print "pow"
	performance_test(mypow,100000)
	print "fast_sqrt0"
	performance_test(fast_sqrt0,100000)
	print "fast_sqrt1"
	performance_test(fast_sqrt1,100000)
	print "fast_sqrt2"
	performance_test(fast_sqrt2,100000)
	print "fast_sqrt3"
	performance_test(fast_sqrt3,100000)
	
def precision():
	file = open('numbers.csv', 'w')
	print "Precision test"
	for i in range(10000):
		k = i*0.1
		file.write((str(k) + ";" + str(math.sqrt(k)) + ";" + str(fast_sqrt0(k)) + ";" + str(fast_sqrt1(k)) + ";" + str(fast_sqrt2(k)) + ";" + str(fast_sqrt3(k)) + "\n").replace('.', ','))
	file.close()

def main():
	performance()
	#precision()
	
if __name__ == "__main__":
  main()