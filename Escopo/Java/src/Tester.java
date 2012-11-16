
public class Tester {
	private int num_testes = 5;
	
	public void run(Test metodo){
		  long time_start, time_stop, total_time, sum_times;
		  sum_times = 0;
		  System.out.println("Teste - " + metodo.nome);
		  for (int i = 0; i < num_testes; ++i) {
		      time_start = System.nanoTime(); 
		      metodo.run();
		      time_stop = System.nanoTime(); 
		      total_time = (time_stop - time_start)/1000000;
		      System.out.println((i + 1) + "/" + num_testes + " = " + total_time);
		      sum_times += total_time;
		  }
		  System.out.println("Media: " + sum_times/num_testes);
	  }
	
	public void run_variable(Test metodo){
		  long time_start, time_stop, total_time, sum_times;
		  sum_times = 0;
		  System.out.println("Teste - " + metodo.nome);
		  for (int i = 0; i < num_testes; ++i) {
		      time_start = System.nanoTime(); 
		      boolean a = true;
		      metodo.run(a);
		      time_stop = System.nanoTime(); 
		      total_time = (time_stop - time_start)/1000000;
		      System.out.println((i + 1) + "/" + num_testes + " = " + total_time);
		      sum_times += total_time;
		  }
		  System.out.println("Media: " + sum_times/num_testes);
	  }
	
	public void run_object(Test metodo){
		  long time_start, time_stop, total_time, sum_times;
		  sum_times = 0;
		  System.out.println("Teste - " + metodo.nome);
		  for (int i = 0; i < num_testes; ++i) {
		      time_start = System.nanoTime(); 
		      MyClass obj = new MyClass(true,12345);
		      metodo.run(obj);
		      time_stop = System.nanoTime(); 
		      total_time = (time_stop - time_start)/1000000;
		      System.out.println((i + 1) + "/" + num_testes + " = " + total_time);
		      sum_times += total_time;
		  }
		  System.out.println("Media: " + sum_times/num_testes);
	  }
}
