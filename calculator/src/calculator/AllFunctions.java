package src.calculator;

import java.math.*;
//class contain all executions of The functions(SingleParameter && DoubleParameter)

class FunAbs implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		return arg.abs();
	}
}

class FunSin implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		double x = arg.remainder(new BigDecimal("360")).doubleValue();
		return new BigDecimal(Math.sin(Math.toRadians(x)));
	}
}

class FunCos implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		double x = arg.remainder(new BigDecimal("360")).doubleValue();
		return new BigDecimal(Math.cos(Math.toRadians(x)));
	}
}
class FunTan implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		double x = arg.remainder(new BigDecimal("360")).doubleValue();
		return new BigDecimal(Math.tan(Math.toRadians(x)));
	}
}
class FunAsin implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		// TODO overflow to -1 , 1
		double x = arg.doubleValue();
		return new BigDecimal(Math.toDegrees(Math.asin(x)));
	}
}
class FunAcos implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		// TODO overflow to -1 , 1
		double x = arg.doubleValue();
		return new BigDecimal(Math.toDegrees(Math.acos(x)));
	}
}
class FunAtan implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		// TODO overflow to -1 , 1
		double x = arg.doubleValue();
		return new BigDecimal(Math.toDegrees(Math.atan(x)));
	}
}

class FunMin implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		return arg1.min(arg2);
	}
}

class FunMax implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		return arg1.max(arg2);
	}
}

class FunP implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) throws Exception{
		BigInteger a1 = arg1.toBigIntegerExact();
		BigInteger a2 = arg2.toBigIntegerExact();
		BigInteger end = a1.subtract(a2);
		BigInteger ans = BigInteger.ONE;
		// TODO: fix exception
		if (end.compareTo(BigInteger.ZERO) == -1)
			throw new Exception("error");
		for(BigInteger i = end.add(BigInteger.ONE); i.compareTo(a1) < 1; i = i.add(BigInteger.ONE))
		{
			ans = ans.multiply(i);
		}
		return new BigDecimal(ans);
	}	
}
class FunC implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) throws Exception{
		BigDecimal p = new FunP().execute(arg1, arg2);
		BigDecimal q = new OpFact().execute(arg2);
		return p.divide(q);
	}
	
}

class FunGcd implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		BigInteger a1 = arg1.toBigIntegerExact();
		BigInteger a2 = arg2.toBigIntegerExact();
		return new BigDecimal(a1.gcd(a2));
	}
}

class FunLcm implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		BigInteger a1 = arg1.toBigIntegerExact();
		BigInteger a2 = arg2.toBigIntegerExact();
		return new BigDecimal((a1.multiply(a2)).divide(a1.gcd(a2)));
	}
}


abstract class AllFunctions {
	// static objects of every function class for fast instantiations 
	public static SingleFunction abs = new SingleFunction(new FunAbs());
	public static SingleFunction sin = new SingleFunction(new FunSin());
	public static SingleFunction cos = new SingleFunction(new FunCos());
	public static SingleFunction tan = new SingleFunction(new FunTan());
	public static SingleFunction asin = new SingleFunction(new FunAsin());
	public static SingleFunction acos = new SingleFunction(new FunAcos());
	public static SingleFunction atan = new SingleFunction(new FunAtan());
	public static DoubleFunction min = new DoubleFunction(new FunMin());
	public static DoubleFunction max = new DoubleFunction(new FunMax());
	public static DoubleFunction p = new DoubleFunction(new FunP());
	public static DoubleFunction c = new DoubleFunction(new FunC());
	public static DoubleFunction gcd = new DoubleFunction(new FunGcd());
	public static DoubleFunction lcm = new DoubleFunction(new FunLcm());
	//public static DoubleFunction log = new DoubleFunction(new FunLog());
}
