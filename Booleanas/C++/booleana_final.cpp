#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>

int repetitions_long = 100; 
int repetitions = 10000000; 

bool opt_false() {
	printf("I'm false\n");
	return false;
}

bool opt_true() {
	printf("I'm true\n");
	return true;
}

bool true_long_function() {
	int a = 0;
	for (int i = 0; i < repetitions_long; ++i) {
		a += 1;
	}
	if (a > 1) {
		return true;
	}
}
		
bool false_long_function() {
	int a = 0;
	for (int i = 0; i < repetitions_long; ++i) {
		a += 1;
	}
	if (a > 1) {
		return false;
	}
}

bool true_short_function() {
  return true;
}
	
bool false_short_function() {
  return false;
}

bool and_test(bool (*first)(), bool (*second)()) {
  return first() && second();
}

bool or_test(bool (*first)(), bool (*second)()) {
  return first() || second();
}

bool triple_non_factored(bool (*first)(), bool (*second)(), bool (*third)()) {
  return (first() || second() || third()) && ((first() || second() || third()));
}

bool triple_factored(bool (*first)(), bool (*second)(), bool (*third)()) {
  return (first() && first()) || (first() && second()) || (first() && third()) || (second() && first()) || (second() && second()) || (second() && third()) || (third() && first()) || (third() && second()) || (third() && third());
}

bool triple_non_factored_2(bool (*first)(), bool (*second)()){
  return (triple_non_factored(first, first, second));
}

bool triple_factored_2(bool (*first)(), bool (*second)()){
  return (triple_factored(first, first, second));
}
	
void test(bool (*oper)(bool (*one)(),bool (*two)()), bool (*first)(), bool (*second)()) {
	double begin, end;
	int tests = 5;
	double mean = 0;
	bool is_working = false;
	bool first_iteration = true;
	for (int k = 0; k < tests; ++k) {
		is_working = false;
		begin = (double)clock()/CLOCKS_PER_SEC;
		for (int j = 0; j < repetitions; ++j) {
			if (oper(first,second)) {
				is_working = true;
			}
			first_iteration = false;
		}
		end = (double)clock()/CLOCKS_PER_SEC;
		mean += (end-begin)*1000;
		printf("Test %d: %f\n",k, (end-begin)*1000);
	}
	mean /= tests;
	printf("Working?: %d\n", is_working);
	printf("Mean: %f\n\n", mean);
}

void optimizations_tests() {
	printf("OPTIMIZATIONS TESTS\n");
	bool temp = false;
	printf("Or - True/True\n");
	temp = (opt_true() || opt_true());
	printf("Or - True/False\n");
	temp = (opt_true() || opt_false());
	printf("Or - False/True\n");
	temp = (opt_false() || opt_true());
	printf("Or - False/False\n");
	temp = (opt_false() || opt_false());
	printf("And - True/True\n");
	temp = (opt_true() && opt_true());
	printf("And - True/False\n");
	temp = (opt_true() && opt_false());
	printf("And - False/True\n");
	temp = (opt_false() && opt_true());
	printf("And - False/False\n");
	temp = (opt_false() && opt_false());
	printf("\n");
}
	
void performance_tests() {
	printf("Warm Up\n");
	test(or_test, true_short_function,true_short_function);
	printf("Or - True/True - Short/Long\n");
	test(or_test, true_short_function,true_long_function);
	printf("Or - True/True - Long/Short\n");
	test(or_test, true_long_function,true_short_function);
	printf("Or - False/True - Long/Long\n");
	test(or_test, false_long_function,true_long_function);
	printf("Or - True/False - Long/Long\n");
	test(or_test, true_long_function,false_long_function);
	
	printf("And - False/False - Short/Long\n");
	test(and_test, false_short_function,false_long_function);
	printf("And - False/False - Long/Short\n");
	test(and_test, false_long_function,false_short_function);
	printf("And - False/True - Long/Long\n");
	test(and_test, false_long_function,true_long_function);
	printf("And - True/False - Long/Long\n");
	test(and_test, true_long_function,false_long_function);
	
	printf("Triple Non- False/True - Long/Long\n");
	test(triple_non_factored_2, false_long_function, true_long_function);
	printf("Triple - False/True - Long/Long\n");
	test(triple_factored_2, false_long_function , true_long_function);
	printf("Triple Non- True/False - Long/Long\n");
	test(triple_non_factored_2, true_long_function, false_long_function);
	printf("Triple - True/False - Long/Long\n");
	test(triple_factored_2, true_long_function , false_long_function);
}

int main() {	
  optimizations_tests();
  performance_tests();
  getch();
}

