
public class Tester {
	protected int repetitions = 100000;
	
	public void run() {
		optimizations_tests();
		performance_tests();
	}
	
	void test(Operation opt, Metodo first, Metodo second) {
		double begin, end;
		int tests = 6;
		double mean = 0;
		boolean is_working = false;
		boolean first_iteration = true;
		for (int k = 0; k < tests; ++k) {
			is_working = false;
			begin = System.nanoTime();
			for (int j = 0; j < repetitions; ++j) {
				if (opt.run(first,second)) {
					is_working = true;
				}
				first_iteration = false;
			}
			end = System.nanoTime();
			double total_time = (end - begin)/1000000;
			if (k != 0) {
				mean += total_time;
			}
			System.out.println("Test " + k + ": " + total_time);
		}
		mean /= (tests - 1) ;
		System.out.println("Working?: " + is_working);
		System.out.println("Mean: " + mean + "\n");
	}
	
	public void optimizations_tests() {
		System.out.println("OPTIMIZATIONS TESTS");
		boolean temp = false;
		System.out.println("Or - True/True");
		
		Opt_true opt_true = new Opt_true();
		Opt_false opt_false = new Opt_false();
		
		temp = (opt_true.run() || opt_true.run());
		System.out.println("Or - True/False");
		temp = (opt_true.run() || opt_false.run());
		System.out.println("Or - False/True");
		temp = (opt_false.run() || opt_true.run());
		System.out.println("Or - False/False");
		temp = (opt_false.run() || opt_false.run());
		System.out.println("And - True/True");
		temp = (opt_true.run() && opt_true.run());
		System.out.println("And - True/False");
		temp = (opt_true.run() && opt_false.run());
		System.out.println("And - False/True");
		temp = (opt_false.run() && opt_true.run());
		System.out.println("And - False/False");
		temp = (opt_false.run() && opt_false.run());
		System.out.println("");
	}
	
	void performance_tests() {
		False_long_function false_long_function = new False_long_function();
		True_long_function true_long_function = new True_long_function();
		False_short_function false_short_function = new False_short_function();
		True_short_function true_short_function = new True_short_function();
		
		Operation and_test = new And_test();
		Operation or_test = new Or_test();
		
		System.out.println("Warm Up");
		test(or_test,true_short_function,true_short_function);
		System.out.println("Or - True/True - Short/Long");
		test(or_test,true_short_function,true_long_function);
		System.out.println("Or - True/True - Long/Short");
		test(or_test,true_long_function,true_short_function);
		System.out.println("Or - False/True - Long/Long");
		test(or_test,false_long_function,true_long_function);
		System.out.println("Or - True/False - Long/Long");
		test(or_test,true_long_function,false_long_function);
		
		System.out.println("And - False/False - Short/Long");
		test(and_test,false_short_function,false_long_function);
		System.out.println("And - False/False - Long/Short");
		test(and_test,false_long_function,false_short_function);
		System.out.println("And - False/True - Long/Long");
		test(and_test,false_long_function,true_long_function);
		System.out.println("And - True/False - Long/Long");
		test(and_test,true_long_function,false_long_function);
		
		Operation triple = new Triple();
		Operation triple_not = new Triple_not();
		
		System.out.println("Triple N - False/True - Long/Long");
		test(triple_not,false_long_function,true_long_function);
		System.out.println("Triple - False/True - Long/Long");
		test(triple,false_long_function,true_long_function);
		System.out.println("Triple N - True/False - Long/Long");
		test(triple_not,true_long_function,false_long_function);
		System.out.println("Triple - True/False - Long/Long");
		test(triple,true_long_function,false_long_function);
	}
}
