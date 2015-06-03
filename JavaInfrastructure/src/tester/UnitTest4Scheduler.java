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
		JsVar pID=closureInfo.Get("setTimeout").Execute(
				null, new JsList(new huahua(), new JsIntegral(3000)), closureInfo);
		closureInfo.Get("clearTimeout").Execute(null, new JsList(pID), closureInfo);
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
}
