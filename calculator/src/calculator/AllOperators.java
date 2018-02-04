package calculator;

import java.math.*;
//class contain all executions of The Operators(UnaryParameter && BinaryParameter)
class OpAdd implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		return arg1.add(arg2);
	}	
}
class OpSub implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		return arg1.subtract(arg2);
	}
}
class OpMulti implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		return arg1.multiply(arg2);
	}
}
class OpDiv implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		return arg1.divide(arg2, 100, RoundingMode.HALF_UP);
	}
}
class OpMod implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		 return arg1.remainder(arg2);
	}
}

class OpPow implements IDoubleParameter // TODO : 
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		int a2 = arg2.intValueExact();
		return arg1.pow(a2);
	}
}
class OpShl implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		BigInteger a1 = arg1.toBigIntegerExact();
		int a2 = arg2.intValueExact();
		return new BigDecimal(a1.shiftLeft(a2));
	}
}
class OpShr implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		BigInteger a1 = arg1.toBigIntegerExact();
		int a2 = arg2.intValueExact();
		return new BigDecimal(a1.shiftRight(a2));
	}
}
class OpBitAnd implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		BigInteger a1 = arg1.toBigIntegerExact();
		BigInteger a2 = arg2.toBigIntegerExact();
		return new BigDecimal(a1.and(a2));
	}
}
class OpBitOr implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		BigInteger a1 = arg1.toBigIntegerExact();
		BigInteger a2 = arg2.toBigIntegerExact();
		return new BigDecimal(a1.or(a2));
	}
}
class OpBitXor implements IDoubleParameter
{
	public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
		BigInteger a1 = arg1.toBigIntegerExact();
		BigInteger a2 = arg2.toBigIntegerExact();
		return new BigDecimal(a1.xor(a2));
	}
}
class OpBitNot implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		BigInteger a1 = arg.toBigIntegerExact();
		return new BigDecimal(a1.not());
	}
}
class OpNeg implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) {
		return arg.negate();
	}
}
class OpFact implements ISingleParameter
{
	public BigDecimal execute(BigDecimal arg) throws Exception{
		BigInteger a1 = arg.toBigIntegerExact();
		BigInteger result = BigInteger.ONE;
		if (a1.compareTo(BigInteger.ZERO) == -1)
			throw new Exception("error");
		for (BigInteger i = new BigInteger("2"); i.compareTo(a1) < 1; i = i.add(BigInteger.ONE))
		{
			result = result.multiply(i);
		}
		return new BigDecimal(result);
	}
}

class AllOperators {
	// static objects of every Operator class for fast instantiations
	public static BinaryOperator add = new BinaryOperator(4, false, new OpAdd());
	public static BinaryOperator sub = new BinaryOperator(4, false, new OpSub());
	public static BinaryOperator multi = new BinaryOperator(3, false, new OpMulti());
	public static BinaryOperator div = new BinaryOperator(3, false, new OpDiv());
	public static BinaryOperator mod = new BinaryOperator(3, false, new OpMod());
	public static BinaryOperator pow = new BinaryOperator(2, true, new OpPow());
	public static BinaryOperator shl = new BinaryOperator(5, false, new OpShl());
	public static BinaryOperator shr = new BinaryOperator(5, false, new OpShr());
	public static BinaryOperator and = new BinaryOperator(8, false, new OpBitAnd());
	public static BinaryOperator or = new BinaryOperator(10, false, new OpBitOr());
	public static BinaryOperator xor = new BinaryOperator(9, false, new OpBitXor());
	public static UnaryOperator not = new UnaryOperator(2, true, true, new OpBitNot());
	public static UnaryOperator neg = new UnaryOperator(2, true, true, new OpNeg());
	public static UnaryOperator fact = new UnaryOperator(2, true, false, new OpFact());
}
