
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodo.criaVetor();
		TestadorDesempenho test = new TestadorDesempenho(5);
		test.testar("ThreadLess", new Threadless(), 0);
		test.testar("Threaded 1", new Threaded(), 1);
		test.testar("Threaded 2", new Threaded(), 2);
		test.testar("Threaded 4", new Threaded(), 4);
		test.testar("Threaded 8", new Threaded(), 8);
		test.testar("Threaded 16", new Threaded(), 16);
	}

}
