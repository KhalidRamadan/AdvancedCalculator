package calculator;
//Class contains every precedence and direction of execution to operators
abstract class Operators {
	int prec;
	boolean right;
	public Operators (int precedence, boolean right){
		prec = precedence;
		this.right = right;
	}
	public int getPrecedence()
	{
		return prec;
	}
	public boolean isRight()
	{
		return right;
	}
	public void setPrecedence(int x)
	{
		prec = x;
	}
	public void setRight(boolean x)
	{
		right = x;
	}
}
