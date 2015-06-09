package tester;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class UnitTest4Scheduler extends JsFunction
{
	public JsFunction GetDup() 
	{
		return new UnitTest4Scheduler();
	}
	public String GetCanonicalName() 
	{
		return "Unittest.main";
	}
	
	public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
	{
		closureInfo.FunctionDeclare("huahua", new huahua());
		closureInfo.FunctionDeclare("killit", new killit());
		closureInfo.Declare("pID", 
				closureInfo.Get("setInterval").Execute(
				new JsList(closureInfo.Get("huahua"), new JsIntegral(1000))));
		closureInfo.Set("pID", 
				closureInfo.Get("setTimeout").Execute(
				new JsList(new killit(), new JsIntegral(5000))));
		
		return JsUndefined.getInstance();
	}

	public class huahua extends JsFunction
	{
		public JsFunction GetDup() 
		{
			return new huahua();
		}
		public String GetCanonicalName() 
		{
			return "Unittest.huahua";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			System.out.println("huahua");
			return JsUndefined.getInstance();
		}
	}
	public class killit extends JsFunction
	{
		public JsFunction GetDup() 
		{
			return new killit();
		}
		public String GetCanonicalName() 
		{
			return "Unittest.killit";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Get("clearInterval").Execute(
					new JsList(closureInfo.Get("pID")));
			return JsUndefined.getInstance();
		}
	}
}
