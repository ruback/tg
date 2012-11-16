import java.util.Random;


public class TestadorDesempenho {
  int num_testes;
  int num_repeticoes;
  
  public TestadorDesempenho(int num_testes){
	  this.num_testes = num_testes;
	  
  }
  
  public void testar(String nome, Metodo metodo, int threads){
	  long time_start, time_stop, total_time, sum_times;
	  sum_times = 0;
	  System.out.println("Teste - " + nome);
	  for (int i = 0; i < num_testes; ++i) {
	      time_start = System.nanoTime(); 
	       metodo.run(threads);
	      time_stop = System.nanoTime(); 
	      total_time = (time_stop - time_start)/1000000;
	      System.out.println((i + 1) + "/" + num_testes + " = " + total_time);
	      sum_times += total_time;
	  }
	  System.out.println("Media: " + sum_times/num_testes);
  }
}
