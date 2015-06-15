package jsFoundation.jsScheduler;

import java.util.*;

public class JsFunctionSchedular
{
	private static JsFunctionSchedular GlobalSchedular;
	public static JsFunctionSchedular GetGlobalSchedular()
	{
		return GlobalSchedular;
	}
	public synchronized static void initSchedular()	//Be sure to run it to init.
	{
		if (GlobalSchedular!=null) return;
		GlobalSchedular=new JsFunctionSchedular();
	}

	private LinkedList<JsDelegate> FunctionQueue;
	private JsCarryThread Carrier;

	public JsFunctionSchedular()
	{
		FunctionQueue=new LinkedList<JsDelegate>();
		Carrier=new JsCarryThread(this);
		Carrier.start();
	}
	public synchronized JsDelegate StashOrLaunchTask(JsDelegate funcName)	//null input for launch
	{
		if (funcName==null)
		{
			if (FunctionQueue.isEmpty())
				return null;
			return FunctionQueue.removeFirst();
		}
		else
		{
			boolean ept=FunctionQueue.isEmpty();
			FunctionQueue.addLast(funcName);
			if (ept) Carrier.interrupt();
			return null;
		}
	}
}
