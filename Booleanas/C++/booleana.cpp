#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>

int repetitions_long = 10000; 
int repetitions = 100000; 

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
	
void or_test(bool (*first)(), bool (*second)()) {
	double begin, end;
	int tests = 5;
	double mean = 0;
	bool is_working = false;
	bool first_iteration = true;
	for (int k = 0; k < tests; ++k) {
		is_working = false;
		begin = (double)clock()/CLOCKS_PER_SEC;
		for (int j = 0; j < repetitions; ++j) {
			if (first() || second()) {
				is_working = true;
			}
			first_iteration = false;
		}
		end = (double)clock()/CLOCKS_PER_SEC;
		mean += (end-begin);
		printf("Test %d: %f\n",k, (end-begin));
	}
	mean /= tests;
	printf("Working?: %d\n", is_working);
	printf("Mean: %f\n\n", mean);
}
	
void and_test(bool (*first)(), bool (*second)()) {
	double begin, end;
	int tests = 5;
	double mean = 0;
	bool is_working = false;
	bool first_iteration = true;
	for (int k = 0; k < tests; ++k) {
		is_working = false;
		begin = (double)clock()/CLOCKS_PER_SEC;
		for (int j = 0; j < repetitions; ++j) {
			if (first() && second()) {
				is_working = true;
			}
			first_iteration = false;
		}
		end = (double)clock()/CLOCKS_PER_SEC;
		mean += (end-begin);
		printf("Test %d: %f\n",k, (end-begin));
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
	or_test(true_short_function,true_short_function);
	printf("Or - True/True - Short/Long\n");
	or_test(true_short_function,true_long_function);
	printf("Or - True/True - Long/Short\n");
	or_test(true_long_function,true_short_function);
	printf("Or - False/True - Long/Long\n");
	or_test(false_long_function,true_long_function);
	printf("Or - True/False - Long/Long\n");
	or_test(true_long_function,false_long_function);
	
	printf("And - False/False - Short/Long\n");
	and_test(false_short_function,false_long_function);
	printf("And - False/False - Long/Short\n");
	and_test(false_long_function,false_short_function);
	printf("And - False/True - Long/Long\n");
	and_test(false_long_function,true_long_function);
	printf("And - True/False - Long/Long\n");
	and_test(true_long_function,false_long_function);
}

int main() {	
  optimizations_tests();
  performance_tests();
  getch();
}

