
public class MathSqrt extends Calculator {

	MathSqrt(String name) {
		super(name);
	}
	
	@Override
	float sqrt(float x) {
		return (float) Math.sqrt(x);
	}

}
