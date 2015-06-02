package tester;

import jsFoundation.jsType.*;

public class UnitCaseTest4Type 
{
	public static void OperatorTest() throws Throwable
	{
		JsVar num5=new JsIntegral(5);
		JsVar num5p=new JsIntegral(5);
		JsVar num0dot5=new JsFloat(0.5);
		JsVar num2=new JsIntegral(2);
		JsVar num0=new JsIntegral(0);
		JsVar boolTrue=new JsBoolean(true);
		JsVar boolFalse=new JsBoolean(false);
		
		JsVar result;
		result=num5.Plus(num0dot5);
		result=num5.Minus(num0dot5);
		result=num5.Asterisk(num0dot5);
		result=num5.Slash(num0dot5);
		result=num5.EqualTo(num0dot5);
		result=num5.IdenticalTo(num0dot5);
		result=num5.LessThan(num0dot5);
		result=num5.GreaterThan(num0dot5);
		result=num5.EqualTo(num5p);
		result=num5.IdenticalTo(num5p);
		result=num5.LessThan(num5p);
		result=num5.GreaterThan(num5p);
		result=num5.Plus(num5p);
		result=num5.Slash(num2);
		//result=num5.Slash(num0);
		result=boolTrue.Exclaimation();
		result=result.IdenticalTo(boolFalse);
		result=result.EqualTo(num0dot5);
		result=result.EqualTo(num0);
		//========================
		JsVar strHaha=new JsString("Haha");
		JsVar strZLY=new JsString("ZLY");
		result=num5.Plus(strHaha);
		result=strHaha.Plus(num0dot5);
		result=strHaha.Plus(boolFalse);
		result=boolTrue.Plus(strHaha);
		result=strZLY.Plus(strHaha);
		//========================
		JsVar strUn1=new JsUndefined();
		JsVar strUn2=new JsUndefined();
		result=strUn1.IdenticalTo(strUn2);
		result=strUn1.IdenticalTo(num0dot5);
	}
}
