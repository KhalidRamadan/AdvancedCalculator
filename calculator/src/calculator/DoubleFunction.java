package calculator;

import java.math.BigDecimal;
//Class for making types of Functions with two parameters (max, min)
public class DoubleFunction extends Functions {
	IDoubleParameter fun;
	public DoubleFunction(IDoubleParameter fun) {
		this.fun = fun;
	}
	public BigDecimal execute(BigDecimal x, BigDecimal y) throws Exception
	{
		return fun.execute(x, y);
	}
}
