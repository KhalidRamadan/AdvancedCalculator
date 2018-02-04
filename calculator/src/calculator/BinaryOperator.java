package calculator;

import java.math.BigDecimal;
//Class for making types of operators with two parameters (+, -, *, /)
class BinaryOperator extends Operators 
{
	IDoubleParameter op;
	public BinaryOperator(int precedence, boolean right, IDoubleParameter op)
	{
		super(precedence, right);
		this.op = op;
	}
	public BigDecimal execute(BigDecimal x, BigDecimal y) throws Exception
	{
		return op.execute(x, y);
	}
	
	
}
