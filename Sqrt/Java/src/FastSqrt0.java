
public class FastSqrt0 extends Calculator {

	public FastSqrt0(String name) {
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
		return y*x;	
	}

}
