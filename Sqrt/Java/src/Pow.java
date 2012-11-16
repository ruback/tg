
public class Pow extends Calculator {

	public Pow(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	float sqrt(float x) {
		return (float) Math.pow(x, 0.5);
	}

}
