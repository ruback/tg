import time, random

def main():
  print "Starting tests"
  num = 5
  reps = 1000000

  unit_tests(metodo1)
  unit_tests(metodo2)
  unit_tests(metodo3)
  unit_tests(metodo4)

  performance_tests(num,reps,"Base", metodo_base)
  performance_tests(num,reps,"Metodo 1", metodo1)
  performance_tests(num,reps,"Metodo 2", metodo2)
  performance_tests(num,reps,"Metodo 3", metodo3)
  performance_tests(num,reps,"Metodo 4", metodo4)

  print "Tests Over"

def unit_tests(func):
  print "Unit Tests - " + str(func)

  result = [];
  expected = [];

  result.append(func(2147483647,2147483647))
  expected.append(2147483647)

  result.append(func(20,10))
  expected.append(15)

  result.append(func(11,21))
  expected.append(16)

  result.append(func(10,11))
  expected.append(10)

  result.append(func(-20,10))
  expected.append(-5)

  result.append(func(0,100))
  expected.append(50)

  result.append(func(0,0))
  expected.append(0)

  for i in range(len(expected)):
    if (result[i] != expected[i]):
      print "Teste " + str(i)
      print "Result: " + str(result[i])
      print "Expected: " + str(expected[i]) 

def performance_tests(num, reps, name, func):

  print name

  for i in range(num):
    inicio = time.clock()
    
    for j in range(reps):
      x = random.randint(-5000,5000)
      y = random.randint(-5000,5000)
      r = func(x,y)
    
    fim = time.clock()
    print "Teste " + str(i) + ": " + str(fim - inicio)

def metodo_base(x,y):
  return 0

def metodo1(x,y):
  return (x+y)/2

def metodo2(x,y):
  return (x+y)>>1;

def metodo3(x,y):
  mean = (x>>1) + (y>>1)
  if ((x^y) >= 0 and (x&y&1)):	
    if (x>=0):
      return mean + 1
    else:
      return mean - 1
  return mean

def metodo4(x,y):
  return ((x&y) + ((x^y)>>1))

if __name__ == "__main__":
  main()