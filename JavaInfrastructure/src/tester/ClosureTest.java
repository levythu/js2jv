package tester;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class ClosureTest extends JsFunction
{
	public String GetCanonicalName()
	{
		return "test.ClosureTest.main";
	}
	public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
	{
		closureInfo=JsClosure.foldClosure(closureInfo);
		{
			closureInfo.FunctionDeclare("GenedFunc", new GenedFunc());
			closureInfo.Declare("i", new JsIntegral(1));
			JsVar constNum4=new JsIntegral(4);
			JsVar constNum1000=new JsIntegral(1000);
			JsVar constNum1=new JsIntegral(1);
			while (closureInfo.Get("i").LessThan(constNum4)._getValue())
			{
				closureInfo.Get("setTimeout").Execute(null, 
						new JsList(closureInfo.Get("GenedFunc"),closureInfo.Get("i").Asterisk(constNum1000))
				);
				closureInfo.Set("i", closureInfo.Get("i").Plus(constNum1));
			}
		}
		closureInfo=JsClosure.unfoldClosure(closureInfo);
		closureInfo=JsClosure.foldClosure(closureInfo);
		{
			closureInfo.FunctionDeclare("Gener", new Gener());
			closureInfo.Declare("i", new JsIntegral(1));
			JsVar constNum4=new JsIntegral(4);
			JsVar constNum1000=new JsIntegral(1000);
			JsVar constNum1=new JsIntegral(1);
			while (closureInfo.Get("i").LessThan(constNum4)._getValue())
			{
				closureInfo.Get("setTimeout").Execute(null, 
						new JsList(closureInfo.Get("Gener").Execute(null, new JsList(closureInfo.Get("i")))
								,closureInfo.Get("i").Asterisk(constNum1000))
				);
				closureInfo.Set("i", closureInfo.Get("i").Plus(constNum1));
			}
		}
		closureInfo=JsClosure.unfoldClosure(closureInfo);
		
		return null;
	}

	static class GenedFunc extends JsFunction
	{
		public String GetCanonicalName()
		{
			return "test.ClosureTest.GenedFunc";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			System.out.println(closureInfo.Get("i").ToString()._getValue());
			
			return new JsUndefined();
		}
	}
	
	static class Gener extends JsFunction
	{
		public String GetCanonicalName()
		{
			return "test.ClosureTest.Gener";
		}
		public JsVar ExecuteDetail(JsClosure cl) throws Exception 
		{	
			cl.Declare("i", cl.Get("arguments").GetProperty(new JsIntegral(0)));
			cl.FunctionDeclare("tmpr", new GenedFunc());
			return cl.Get("tmpr");
		}
	}
}