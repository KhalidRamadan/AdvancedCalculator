package calculator;

import java.math.*;
import java.util.*;
class Parse {
	static final int[][] relations = {
			{1, 1, 0, 1, 0, 1, 0, 0, 1},
			{0, 0, 0, 0, 0, 1, 0, 0, 0},
			{2, 2, 0, 0, 1, 2, 1, 1, 2},
			{1, 1, 0, 1, 0, 1, 0, 0, 1},
			{1, 1, 0, 1, 0, 1, 0, 0, 1},
			{1, 1, 0, 1, 0, 1, 0, 0, 1},
			{2, 2, 1, 2, 1, 2, 1, 1, 2},
			{1, 1, 0, 1, 0, 1, 0, 0, 1},
			{2, 2, 1, 0, 1, 2, 1, 1, 1},
	}; 
	static final Object[][] map = {
			{"asin", AllFunctions.asin},
			{"acos", AllFunctions.acos},
			{"atan", AllFunctions.atan},
			{"sin", AllFunctions.sin},
			{"cos", AllFunctions.cos},
			{"tan", AllFunctions.tan},
			{"abs", AllFunctions.abs},
			{"max", AllFunctions.max},
			{"min", AllFunctions.min},
			{"lcm", AllFunctions.lcm},
			{"gcd", AllFunctions.gcd},
			{"<<", AllOperators.shl},
			{">>", AllOperators.shr},
			{"^=", AllOperators.xor},
			{"p", AllFunctions.p},
			{"c", AllFunctions.c},
			{"+", AllOperators.add},
			{"-", AllOperators.neg},
			{"*", AllOperators.multi},
			{"/", AllOperators.div},
			{"%", AllOperators.mod},
			{"^", AllOperators.pow},
			{"&", AllOperators.and},
			{"|", AllOperators.or},
			{"~", AllOperators.not},
			{"!", AllOperators.fact},
			{"(", LeftBracket.openBracket},
			{")", RightBracket.closeBracket},
			{",", Separator.comma},
			
	};
	String tokens;
	ArrayList<Object> finalTokens = new ArrayList<Object>();
	public Parse(String tokens) {
		this.tokens = tokens.replaceAll("\\s", "").toLowerCase();
	}
	int convertToIndex(Object o) throws Exception
	{
		if (o instanceof SingleFunction)
			return 0;
		else if (o instanceof DoubleFunction)
			return 1;
		else if (o instanceof UnaryOperator)
		{
			if (!((UnaryOperator)o).isSuffix())
				return 2;
			else
				return 3;
		}
		else if (o instanceof BinaryOperator)
			return 4;
		else if (o instanceof LeftBracket)
			return 5;
		else if (o instanceof RightBracket)
			return 6;
		else if (o instanceof Separator)
			return 7;
		else if (o instanceof BigDecimal)
			return 8;
		else
		{
			// TODO:
			throw new Exception("error");
		}
	}
	Object[] execute() throws Exception
	{
		int prev = 5;
		for(int i = 0; i < tokens.length();)
		{
			Object current = null;
			char c = tokens.charAt(i);
			if (c >= '0' && c <= '9' || c == '.')
			{
				boolean dot = false;
				String number = "";
				while (c >= '0' && c <= '9' || c == '.')
				{
					number += c;
					if (c == '.')
					{
						if (dot == false)
							dot = true;
						else
						{
							throw new Exception("error");
							// TODO: 
						}
					}
					i++;
					if(i < tokens.length())
						c = tokens.charAt(i);
					else break;
				}
				current = new BigDecimal(number);
			}
			else
			{
				for (int j = 0; j < map.length; j++)
				{
					if (tokens.startsWith((String)map[j][0], i))
					{
						current = map[j][1];
						i += ((String)map[j][0]).length();
						break;
					}
				}
				if (current == null)
				{
					throw new Exception("error");
					// TODO:
				}
 				if((prev == 2 || prev == 6 || prev == 8) && current.equals(AllOperators.neg))
 					current = AllOperators.sub;
			}
			int cur = convertToIndex(current); 
			int valid = relations[prev][cur];
			if(valid == 1)
				finalTokens.add(current);
			else if(valid == 2)
			{
				finalTokens.add(AllOperators.multi);
				finalTokens.add(current);
			}
			else
			{
				throw new Exception("error");
				// TODO:
			}
			prev = cur;
		}
		return finalTokens.toArray();
	}
}
