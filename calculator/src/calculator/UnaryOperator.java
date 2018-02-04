package calculator;

import java.math.BigDecimal;
//Class for making types of operators with one parameters ! \\ -

class UnaryOperator extends Operators 
{
	ISingleParameter op;
	boolean suffix;
	public UnaryOperator(int precedence, boolean right, boolean suffix,ISingleParameter op)
	{
		super(precedence, right);
		this.op = op;
		this.suffix = suffix;
	}
	public BigDecimal execute(BigDecimal x) throws Exception
	{
		return op.execute(x);
	}
	public boolean isSuffix()
	{
		return suffix;
	}
	
}
