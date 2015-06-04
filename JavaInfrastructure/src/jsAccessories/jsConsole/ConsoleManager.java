package jsAccessories.jsConsole;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class ConsoleManager
{	
	public static JsObject require()
	{
		JsObject ans=new JsObject();
		try
		{
			ans.SetProperty("log", new consoleLog());
			ans.SetProperty("warn", new consoleWarn());
		}
		catch (Exception e)
		{}
		return ans;
	}
	public static class consoleLog extends JsFunction.JsNativeFunction
	{
		public String GetCanonicalName() 
		{
			return "Js.Prelude.console.log";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsUndefined();
			System.out.print(para.value.get(0).ToString()._getValue());
			return new JsUndefined();
		}
	}
	public static class consoleWarn extends JsFunction.JsNativeFunction
	{
		public String GetCanonicalName() 
		{
			return "Js.Prelude.console.warn";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsUndefined();
			System.err.print(para.value.get(0).ToString()._getValue());
			return new JsUndefined();
		}
	}
}
