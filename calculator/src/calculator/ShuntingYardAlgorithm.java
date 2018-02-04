package calculator;

import java.math.*;
import java.util.*;
// for more informations : https://en.wikipedia.org/wiki/Shunting-yard_algorithm
class ShuntingYardAlgorithm
{
	Object[] tokens;
	ArrayList<Object> postFix = new ArrayList<Object>();
	Stack<Object> stack = new Stack<Object>();
	public ShuntingYardAlgorithm(Object[] tokens)
	{
		this.tokens = tokens;
	}
	private void convertToPostFix() throws Exception
	{
		
		for (int i = 0; i < tokens.length; i++)
		{
			Object token = tokens[i];
			if (token instanceof BigDecimal)
				postFix.add(token);
			else if (token instanceof Functions)
				stack.push(token);
			else if (token instanceof Separator)
				handleSeparator();
			else if (token instanceof Operators)
				handleOperator((Operators)token);
			else if (token instanceof LeftBracket)
				stack.push(token);
			else if (token instanceof RightBracket)
				handleRightBracket();
			else
			{
				// TODO: exception handling
				throw new Exception("error");
			}
				
		}
		while(!stack.isEmpty())
		{
			if(!(stack.peek() instanceof LeftBracket))
				postFix.add(stack.pop());
			else 
			{
				// TODO
				throw new Exception("error");
			}
		}
	}
	private void handleSeparator() throws Exception
	{	
		while (!stack.isEmpty() && !(stack.peek() instanceof LeftBracket))
			postFix.add(stack.pop());

		if (stack.isEmpty())
		{
			// TODO: exception handling
			throw new Exception("error");
		}
		stack.pop();
		if(stack.isEmpty() || !(stack.peek() instanceof DoubleFunction))
		{
			// TODO:
			throw new Exception("error");
		}
		stack.push(LeftBracket.openBracket);
	}
	private void handleOperator(Operators token)
	{
		for (;;)
		{
			boolean c1 = !stack.isEmpty() && (stack.peek() instanceof Operators);
			if (c1)
			{
				Operators o2 = (Operators)stack.peek();
				boolean c2 = !token.isRight() && token.getPrecedence() >= o2.getPrecedence();
				boolean c3 = token.isRight() && token.getPrecedence() > o2.getPrecedence();
				if (!c2 && !c3) 
					break;
				postFix.add(stack.pop());
			}
			else 
				break;
		}
		stack.push(token);
	}
	private void handleRightBracket() throws Exception
	{
		while (!stack.isEmpty() && !(stack.peek() instanceof LeftBracket))
			postFix.add(stack.pop());
		
		if (stack.isEmpty())
		{
			// TODO: exception handling
			throw new Exception("error");
		}
		else
		{
			stack.pop();
			if (!stack.isEmpty() && (stack.peek() instanceof Functions))
				postFix.add(stack.pop());
		}
	}
	private BigDecimal calculate() throws Exception
	{
		Stack<BigDecimal> result = new Stack<BigDecimal>();
		for(Object token : postFix)
		{
			if(token instanceof BigDecimal)
				result.add((BigDecimal) token);
			else if(token instanceof SingleFunction && result.size() >= 1)
			{
				BigDecimal x = result.pop();
				result.push(((SingleFunction)token).execute(x));
			}
			else if(token instanceof DoubleFunction && result.size() >= 2)
			{
				BigDecimal y = result.pop();
				BigDecimal x = result.pop();
				result.push(((DoubleFunction)token).execute(x, y));
			}
			else if(token instanceof UnaryOperator && result.size() >= 1)
			{
				BigDecimal x = result.pop();
				result.push(((UnaryOperator)token).execute(x));
			}
			else if(token instanceof BinaryOperator && result.size() >= 2)
			{
				BigDecimal y = result.pop();
				BigDecimal x = result.pop();
				result.push(((BinaryOperator)token).execute(x, y));
			}
			else 
			{
				// TODO:
				throw new Exception("error");
			}
		}
		if(result.size() != 1)
		{
			// TODO;:
			throw new Exception("error");
		}
		return result.pop();
	}
	public BigDecimal execute() throws Exception
	{
		convertToPostFix();
		return calculate();
	}
}
