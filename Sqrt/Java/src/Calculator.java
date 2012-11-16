
public abstract class Calculator {
	String name;
	
	Calculator(String name){
		this.name = name;
		calc(5,10000000);
	}
	
	void calc(int repeat, int times) {
		System.out.println("Teste: " + name);
		long mean = 0;
		for (int i = 0; i < repeat; ++i) {
			long time_begin, time_end;
			time_begin = System.currentTimeMillis();
			float valor;
			for (int j = 0; j < times; ++j) {
				valor = sqrt(1.312371232f);
				valor = sqrt(3.254536726f);
				valor = sqrt(15.42342343f);
				valor = sqrt(1359.234233f);
				valor = sqrt(13214124.23f);
				valor = sqrt(8989634345.0f); 
			}
			time_end = System.currentTimeMillis();
			long total_time = time_end - time_begin;
			mean += total_time;
			System.out.println("Teste " + (i+1) + ": " + total_time);
		}
		mean /= repeat;
		System.out.println("Mean: " + mean);
		System.out.println("");
	};
	
	abstract float sqrt(float  x);
}

