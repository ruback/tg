
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Testador();
		new TestadorDesempenho(5,100000000);
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
