
public class FastSqrt2 extends Calculator {

	public FastSqrt2(String name) {
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
		x = x*(1.5f - xhalf*x*x);
		return y*x;	
	}

}
