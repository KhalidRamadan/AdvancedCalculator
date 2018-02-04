package calculator;

import java.math.BigDecimal;
//class manages driving between classes
class Drive {
	
	public Drive() {
	}
	public BigDecimal execute(String s)
	{
		try
		{
			Parse p = new Parse(s);
			ShuntingYardAlgorithm sya = new ShuntingYardAlgorithm(p.execute());
			return sya.execute();
		}
		catch (Exception e)
		{
//			System.out.println(e.getMessage());
		}
		return null;
	}
	public String toString()
	{
		return "";
	}
	
}
