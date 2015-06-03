package tester;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class UnitTest4Scheduler extends JsFunction
{
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
				null, new JsList(closureInfo.Get("huahua"), new JsIntegral(1000))));
		closureInfo.Set("pID", 
				closureInfo.Get("setTimeout").Execute(
				null, new JsList(new killit(), new JsIntegral(5000))));
		
		return new JsUndefined();
	}

	public class huahua extends JsFunction
	{
		public String GetCanonicalName() 
		{
			return "Unittest.huahua";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo)
		{
			System.out.println("huahua");
			return new JsUndefined();
		}
	}
	public class killit extends JsFunction
	{
		public String GetCanonicalName() 
		{
			return "Unittest.killit";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Get("clearInterval").Execute(
					null, new JsList(closureInfo.Get("pID")));
			return new JsUndefined();
		}
	}
}
