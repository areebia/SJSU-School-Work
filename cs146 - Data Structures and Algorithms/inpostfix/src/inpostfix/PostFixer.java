package inpostfix;

import java.lang.Character;

public class PostFixer
{
    //small enum for characters 
	public static int precedence(char ch)
	{
		if(ch == '+' || ch == '-')
		{
			return 1;
		}
		else if(ch == '*' || ch == '/')
		{
			return 2;
		}
		else if(ch == '^')
		{
			return 3;
		}
		else
		{
			return -1;
		}
	}

	public static String inToPost(String infix)
	{
		LinkStack opStack = new LinkStack();
		String postfix = new String();
		int charCount = 0;
		Character topOp;
		while (charCount < infix.length())
		{
			Character nextCh = infix.charAt(charCount);

			if (Character.isLetterOrDigit(nextCh))
			{
				postfix += nextCh;
			}
			else
			{
				switch (nextCh)
				{

				case '^':
					opStack.push(nextCh);
					break;
				case '+':
				case '-':
				case '*':
				case '/':
					while (!opStack.isEmpty() && precedence(nextCh) < precedence((Character) opStack.peek()))
					{
						postfix += (Character) opStack.pop();
					}
					opStack.push(nextCh);
					break;
				case '(':
					opStack.push(nextCh);
					break;
				case ')':
					topOp = (Character) opStack.pop();
					while (topOp != '(')
					{
						postfix += topOp;
						topOp = (Character) opStack.pop();
					}
					break;

				default:
					break;
				}
			}

			charCount++;
		}
		while (!opStack.isEmpty())
		{
			topOp = (Character) opStack.pop();
			postfix += topOp;

		}
		return postfix;
	}

	public static void main(String[] args)
	{
		String exp = "a+b*(c^d-e)^(f+g*h)";
		System.out.println(inToPost(exp));
	}
}
