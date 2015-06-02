package tester;

import jsFoundation.jsType.*;

public class UnitCaseTest4Type 
{
	public static void OperatorTest() throws Throwable
	{
		JsVar num5=new JsIntegral(5);
		JsVar num0dot5=new JsFloat(0.5);
		JsVar result=num5.Plus(num0dot5);
		result=num5.Minus(num0dot5);
		result=num5.Asterisk(num0dot5);
		result=num5.Slash(num0dot5);
	}
}
