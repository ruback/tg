
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tester tester = new Tester();
		tester.run(new VariableInsideFor());
		tester.run(new VariableOutsideFor());
		tester.run_variable(new VariableOutsideFunction());
		tester.run(new VariableGlobal());
		
		tester.run(new ObjectInsideFor());
		tester.run(new ObjectOutsideFor());
		tester.run_object(new ObjectOutsideFunction());
		tester.run(new ObjectGlobal());
	}

}
