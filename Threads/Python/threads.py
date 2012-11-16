import time
import threading
import multiprocessing
import math
import random

n = 10000
input = [0 for x in range(n)]
resp_threadless = [0 for x in range(n)]
resp_thread = [0 for x in range(n)]
hCountSemaphore = multiprocessing.Lock()
pos = 0

def buildvector():
	global input
	input = [random.randrange(0,n) for x in range(n)]

def function(x):
	count = 0
	while (x > 1.0):
		#0.97
		x = x*0.995;
		count += 1;
	return count


def test(function):
	print "Threadless"
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		begin = time.clock()
		
		for i in range(n):
			resp_threadless = function(i)
			
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Mean: " + str(mean)
	print ""
	
def test_thread(function, num_threads):
	print "Threaded %d" % num_threads
	tests = 6
	mean = 0
	first_iteration = True

	for j in range(tests):
		pool = multiprocessing.Pool(processes=num_threads)
		begin = time.clock()
		
		pool.map(function, range(n))
		
		#global pos 
		#pos = 0
		#ths = []
		#for i in range(num_threads):
		#	th = multiprocessing.Process(target=function_th, args=(i,))
		#	ths.append(th)
		#	th.start()

		#for i in range(num_threads):
		#	th = ths[i]
		#	th.join()
		
		end = time.clock()
		if (j != 0):
		  mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= (tests - 1)
	print "Mean: " + str(mean)
	print ""	

#Main

def main():
	buildvector()
	test(function)
	test_thread(function,1)
	test_thread(function,2)
	test_thread(function,4)
	test_thread(function,8)
	test_thread(function,16)
	
if __name__ == "__main__":
  main()