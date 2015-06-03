package tester;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class UnitTest4Scheduler extends JsFunction
{
	public String GetCanonicalName() 
	{
		return "Unittest.main";
	}
	
	public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception
	{
		closureInfo.Set("pID", 
				closureInfo.Get("setInterval").Execute(
				null, new JsList(new huahua(), new JsIntegral(1000)), closureInfo));
		closureInfo.Set("pID", 
				closureInfo.Get("setTimeout").Execute(
				null, new JsList(new killit(), new JsIntegral(5000)), closureInfo));
		
		return new JsUndefined();
	}

	public class huahua extends JsFunction
	{
		public String GetCanonicalName() 
		{
			return "Unittest.huahua";
		}
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo)
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
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception
		{
			closureInfo.Get("clearInterval").Execute(
					null, new JsList(closureInfo.Get("pID")), closureInfo);
			return new JsUndefined();
		}
	}
}
