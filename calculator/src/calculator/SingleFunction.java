package calculator;
//Class for making types of Functions with one parameters (abs)
import java.math.BigDecimal;

public class SingleFunction extends Functions {
	ISingleParameter fun;
	public SingleFunction(ISingleParameter fun) {
		this.fun = fun;
	}
	public BigDecimal execute(BigDecimal x) throws Exception
	{
		return fun.execute(x);
	}
}
