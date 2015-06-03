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
	
	public static class SetTimeOut extends JsFunction
	{
		public String GetCanonicalName() 
		{
			return "Js.Prelude.setTimeOut";
		}
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception 
		{
			if (para.value.size()<2)
				return new JsIntegral(0);
			if (!(para.value.get(0) instanceof JsFunction))
				return new JsIntegral(0);
			if (!(para.value.get(1) instanceof JsIntegral))
				return new JsIntegral(0);
			JsFunction cb=(JsFunction)para.value.get(0);
			JsIntegral tm=(JsIntegral)para.value.get(1);
			long dura=tm._getValue();
			if (dura<0) dura=0;
			
			timeoutCount++;
			runnerList.put(timeoutCount, true);
			TimerTask obj=new TimerTask(new JsDelegate(cb, new JsUndefined(), new JsList(), closureInfo),
					timeoutCount, dura);
			obj.start();
			
			return new JsIntegral(timeoutCount);
		}
	}
	public static class ClearTimeOut extends JsFunction
	{
		public String GetCanonicalName() 
		{
			return "Js.Prelude.clearTimeOut";
		}
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception 
		{
			if (para.value.size()<1)
				return new JsUndefined();
			if (!(para.value.get(0) instanceof JsIntegral))
				return new JsUndefined();
		
			JsIntegral num=(JsIntegral)para.value.get(0);
			runnerList.put((int)num._getValue(), false);
			
			return new JsUndefined();
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
