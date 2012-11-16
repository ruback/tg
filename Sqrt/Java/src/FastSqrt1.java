
public class FastSqrt1 extends Calculator {

	FastSqrt1(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	float sqrt(float x) {
		float y = x;
		float xhalf =  x*0.5f;
		int i = Float.floatToIntBits(x); 
		i = 0x5f3759d5 - (i >> 1); 
		x = Float.intBitsToFloat(i);
		x = x*(1.5f - xhalf*x*x);
		return y*x;
	}

}
