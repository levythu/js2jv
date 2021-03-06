package jsAccessories.jsTimer;

import java.util.HashMap;

import jsFoundation.JsClosure;
import jsFoundation.jsScheduler.JsDelegate;
import jsFoundation.jsScheduler.JsFunctionSchedular;
import jsFoundation.jsScheduler.JsTask;
import jsFoundation.jsType.JsFunction;
import jsFoundation.jsType.JsIntegral;
import jsFoundation.jsType.JsList;
import jsFoundation.jsType.JsNumber;
import jsFoundation.jsType.JsUndefined;
import jsFoundation.jsType.JsVar;

public class TimeInterval 
{
	static HashMap<Integer,Boolean> runnerList=new HashMap<Integer,Boolean>();
	static int intervalCount=19940701;
	
	public static class SetInterval extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new SetInterval();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.setInterval";
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
			
			intervalCount++;
			runnerList.put(intervalCount, true);
			IntervalTask obj=new IntervalTask(new JsDelegate(cb, new JsList()),
					intervalCount, dura);
			obj.start();
			
			return new JsIntegral(intervalCount);
		}
	}
	public static class ClearInterval extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new ClearInterval();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.clearInterval";
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
	public static class IntervalTask extends JsTask
	{
		private int mapID;
		private long triggerTime;
		private JsDelegate selfCaller;
		
		public IntervalTask(JsDelegate _callback, int mID, long timeInms)
		{
			selfCaller=_callback;
			mapID=mID;
			triggerTime=timeInms;
		}
		protected boolean TaskContent() 
		{
			while (true)
			{
				try
				{
					Thread.sleep(triggerTime);
				}
				catch(Throwable e)
				{}
				if (runnerList.get(mapID)==false)
					break;
				JsFunctionSchedular.GetGlobalSchedular().StashOrLaunchTask(selfCaller);
			}
			return false;
		}
	}
}