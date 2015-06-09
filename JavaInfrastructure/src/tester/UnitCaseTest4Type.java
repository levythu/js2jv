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
		JsVar strUn1=JsUndefined.getInstance();
		JsVar strUn2=JsUndefined.getInstance();
		result=strUn1.IdenticalTo(strUn2);
		result=strUn1.IdenticalTo(num0dot5);
		//========================
		JsVar list1=new JsList();
		list1.GetProperty("push").Execute(new JsList(num5));
		list1.GetProperty("push").Execute(new JsList(num0dot5));
		list1.GetProperty("push").Execute(new JsList(strZLY));
		result=list1.GetProperty(new JsIntegral(1));
		list1.SetProperty("length",new JsIntegral(30));
		list1.SetProperty(new JsIntegral(10),boolTrue);
		result=list1.GetProperty("length");
		list1.GetProperty("pop").Execute(new JsList());
		result=list1.GetProperty("length");
		list1.SetProperty("length",new JsIntegral(3));
		//========================
		result=strHaha.GetProperty(new JsIntegral(5));
		result=strHaha.GetProperty(new JsIntegral(0));
		//========================
		result=strHaha.GetProperty("substr").Execute(new JsList(num0));
		result=strHaha.GetProperty("substr").Execute(new JsList(num0,num2));
		result=strHaha.GetProperty("substr").Execute(new JsList(num0,num5));
		result=strHaha.GetProperty("substr").Execute(new JsList(num5,num5));
		result=strHaha.GetProperty("substr").Execute(new JsList(num5));
		
		JsVar num2dot5string=new JsString("2.5");
		JsVar num2string=new JsString("2");
		result=num0.Minus(num0.Minus(num2dot5string));
		result=num0.Minus(num0.Minus(num2string));
		//========================
		JsVar ls=new JsList();
		JsVar ls2=new JsList(list1,num5,num2dot5string);
		result=ls2.GetProperty("unshift").Execute(new JsList(strZLY));
		result=ls2.GetProperty("shift").Execute(new JsList());
		result=ls2.GetProperty("pop").Execute(new JsList());
		result=ls2.GetProperty("shift").Execute(new JsList());
		result=ls2.GetProperty("shift").Execute(new JsList());
		result=ls2.GetProperty("pop").Execute(new JsList());
		JsVar mutai=ls.GetProperty("shift").Execute(new JsList());
		result=mutai.IdenticalTo(result);
	}
}
