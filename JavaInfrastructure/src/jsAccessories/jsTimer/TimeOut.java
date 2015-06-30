package jsAccessories.jsTimer;

import java.util.HashMap;

import jsFoundation.JsClosure;
import jsFoundation.jsScheduler.JsDelegate;
import jsFoundation.jsScheduler.JsTask;
import jsFoundation.jsType.*;

public class TimeOut 
{
	static HashMap<Integer,Boolean> runnerList=new HashMap<Integer,Boolean>();
	static int timeoutCount=0;
	
	public static class SetTimeOut extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new SetTimeOut();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.setTimeOut";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<2)
				return new JsIntegral(0);
			if (!(para.value.get(0) instanceof JsFunction))
				return new JsIntegral(0);
			if (!(para.value.get(1) instanceof JsNumber))
				return new JsIntegral(0);
			JsFunction cb=(JsFunction)para.value.get(0);
			JsNumber tm=(JsNumber)para.value.get(1);
			long dura=tm.EvaluateInt();
			if (dura<0) dura=0;
			
			cb=JsFunction.dup(cb, JsUndefined.getInstance());
			
			timeoutCount++;
			runnerList.put(timeoutCount, true);
			TimerTask obj=new TimerTask(new JsDelegate(cb, new JsList()),
					timeoutCount, dura);
			obj.start();
			
			return new JsIntegral(timeoutCount);
		}
	}
	public static class ClearTimeOut extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new ClearTimeOut();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.clearTimeOut";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return JsUndefined.getInstance();
			if (!(para.value.get(0) instanceof JsIntegral))
				return JsUndefined.getInstance();
		
			JsIntegral num=(JsIntegral)para.value.get(0);
			runnerList.put((int)num._getValue(), false);
			
			return JsUndefined.getInstance();
		}
	}
	public static class TimerTask extends JsTask
	{
		private int mapID;
		private long triggerTime;
		
		public TimerTask(JsDelegate _callback, int mID, long timeInms)
		{
			callback=_callback;
			mapID=mID;
			triggerTime=timeInms;
		}
		protected boolean TaskContent() 
		{
			try
			{
				Thread.sleep(triggerTime);
			}
			catch(Throwable e)
			{}
			return runnerList.get(mapID);
		}
	}
}
