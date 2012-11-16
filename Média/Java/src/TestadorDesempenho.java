import java.util.Random;


public class TestadorDesempenho {
  int num_testes;
  int num_repeticoes;
  
  public TestadorDesempenho(int num_testes, int num_repeticoes){
	  this.num_testes = num_testes;
	  this.num_repeticoes = num_repeticoes;
	  testar("Base", new MetodoBase());
	  testar("Metodo 1 - (a+b)/2", new Metodo1());
	  testar("Metodo 2 - (a + b) >> 1", new Metodo2());
	  testar("Metodo 3 - (a >> 1) + (b >> 1) + something", new Metodo3());
	  testar("Metodo 4 - (a&b) + ((a^b) >> 1)", new Metodo4());
  }
  
  private void testar(String nome, Metodo metodo){
	  long time_start, time_stop, total_time, sum_times;
	  sum_times = 0;
	  System.out.println("Teste - " + nome);
	  Random randomGenerator = new Random();
	  for (int i = 0; i < num_testes; ++i) {
	      time_start = System.nanoTime(); 
	      for (int j = 0; j < num_repeticoes; ++j) {
	        int temp = metodo.media(randomGenerator.nextInt(),randomGenerator.nextInt());
	      }
	      time_stop = System.nanoTime(); 
	      total_time = time_stop - time_start;
	      System.out.println((i + 1) + "/" + num_testes + " = " + total_time);
	      sum_times += total_time;
	  }
	  System.out.println("Media: " + sum_times/num_testes);
  }
}
