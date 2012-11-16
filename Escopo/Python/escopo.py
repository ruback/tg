import time

#Classes and global variables

repetetions = 10000000
global_a = True

class MyClass:
	a = True
	i = 12345
		
	def __init__(self, i, a):
		self.i = i
		self.a = a
		
	def func1(self):
		return 'Hello!'

	def func2(self):
		self.i = self.i + 1;
			 
global_obj = MyClass(12345, True)
			 
#Variable Functions
			 
def variable_inside_for():
	aux = 0
	for k in range(repetetions):
		a = True
		if a:
			aux += 1;
			
def variable_outside_for():
	aux = 0
	a = True
	for k in range(repetetions):
		#a = True
		if a:
			aux += 1;

def variable_outside_function(a):
	aux = 0
	for k in range(repetetions):
		#a = True
		if a:
			aux += 1;

def variable_global():
	aux = 0
	global global_a
	for k in range(repetetions):
		#global_a = True
		if global_a:
			aux += 1;

#Object Functions

def object_inside_for():
	aux = 0
	for k in range(repetetions):
		obj = MyClass(12345, True)
		if obj.a:
			aux += 1;

def object_outside_for():
	aux = 0
	obj = MyClass(12345, True)
	for k in range(repetetions):
		#obj = MyClass(12345, True)
		if obj.a:
			aux += 1;

def object_outside_function(obj):
	aux = 0
	for k in range(repetetions):
		#obj = MyClass(12345, True)
		if obj.a:
			aux += 1;

def object_global():
	aux = 0
	global global_obj
	for k in range(repetetions):
		#global_obj = MyClass(12345, True)
		if global_obj.a:
			aux += 1;

#Test Functions
			
def test(function):
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		begin = time.clock()
		function()
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Mean: " + str(mean)
	print ""
	
def test_outside_variable(function):
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		begin = time.clock()
		a = True;
		function(a)
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Mean: " + str(mean)
	print ""

def test_outside_object(function):
	tests = 5
	mean = 0
	first_iteration = True
	for j in range(tests):
		begin = time.clock()
		a = MyClass(12345, True)
		function(a)
		end = time.clock()
		mean += (end-begin)*1000
		print "Test" + str(j) + ": " + str((end-begin)*1000)
	mean /= tests
	print "Mean: " + str(mean)
	print ""

#Main

def main():
	print "Variable Inside For"
	test(variable_inside_for)
	print "Variable Outside For"
	test(variable_outside_for)
	print "Variable Outside Function"
	test_outside_variable(variable_outside_function)
	print "Global Variable"
	test(variable_global)

	print "Object Inside For"
	test(object_inside_for)
	print "Object Outside For"
	test(object_outside_for)
	print "Object Outside Function"
	test_outside_object(object_outside_function)
	print "Global Object"
	test(object_global)
	
if __name__ == "__main__":
  main()