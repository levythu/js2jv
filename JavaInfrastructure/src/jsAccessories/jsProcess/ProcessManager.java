package jsAccessories.jsProcess;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class ProcessManager
{	
	public static JsObject require()
	{
		JsObject ans=new JsObject();
		try
		{
			ans.SetProperty("exit", new processExit());
		}
		catch (Exception e)
		{}
		return ans;
	}
	public static class processExit extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new processExit();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.process.exit";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			long retCode;
			if (para.value.size()<1)
				retCode=0;
			else
			{
				if (para.value.get(0) instanceof JsIntegral)
				{
					retCode=((JsIntegral)para.value.get(0))._getValue();
				}
				else
					retCode=0;
			}
			System.exit((int)retCode);
			return JsUndefined.getInstance();
		}
	
	}
}
