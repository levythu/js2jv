package jsAccessories.jsConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import jsFoundation.JsClosure;
import jsFoundation.jsScheduler.JsDelegate;
import jsFoundation.jsScheduler.JsTask;
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
			ans.SetProperty("readline", new consoleReadline());
		}
		catch (Exception e)
		{}
		return ans;
	}
	public static class consoleLog extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new consoleLog();
		}
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
		public JsFunction GetDup()
		{
			return new consoleWarn();
		}
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
	public static class consoleReadline extends JsFunction.JsNativeFunction	//A figurative function, not existed in Node.
	{
		public JsFunction GetDup()
		{
			return new consoleReadline();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.console.readline";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsUndefined();
			JsVar rawArg=para.value.get(0);
			if (!(rawArg instanceof JsFunction))
				return new JsUndefined();
			JsFunction callback=JsFunction.dup((JsFunction)rawArg, new JsUndefined());
			
			ReadStdINTask st=new ReadStdINTask(callback);
			st.start();
			
			return new JsUndefined();
		}
		
		public static class ReadStdINTask extends JsTask
		{		
			private static BufferedReader stdin;
			private JsFunction rawFun;
			public ReadStdINTask(JsFunction _callback)
			{
				rawFun=_callback;
				if (stdin==null)
					stdin=new BufferedReader(new InputStreamReader(System.in));
			}
			protected boolean TaskContent() 
			{
				String result;
				try
				{
					result=stdin.readLine();
				}
				catch (Throwable e)
				{
					return false;
				}
				callback=new JsDelegate(rawFun,new JsList(new JsString(result)));
				return true;
			}
		}
	}
}
