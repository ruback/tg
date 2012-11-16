
public class Testador {
	
	public Testador(){
		testar("Metodo 1 - (a+b)/2", new Metodo1());
		testar("Metodo 2 - (a + b) >> 1", new Metodo2());
		testar("Metodo 3 - (a >> 1) + (b >> 1) + something", new Metodo3());
		testar("Metodo 4 - (a&b) + ((a^b) >> 1)", new Metodo4());
	}
	
	private void testar(String nome, Metodo metodo) {
		System.out.println("Testando: " + nome);
		imprime_se_erro(2147483647,metodo.media(2147483647,2147483647));
		imprime_se_erro(15,metodo.media(20,10));
		imprime_se_erro(16,metodo.media(11,21));
		imprime_se_erro(10,metodo.media(10,11));
		imprime_se_erro(-5,metodo.media(-20,10));
		imprime_se_erro(50,metodo.media(0,100));
		imprime_se_erro(0,metodo.media(0,0));
	}
	
	private void imprime_se_erro(int esperado, int resultado) {
		if (esperado != resultado)
			System.out.println("Esperado: " + esperado + " - Resultado: " + resultado);
	}
}
