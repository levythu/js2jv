package jsFoundation.jsScheduler;

import java.util.*; 
import jsFoundation.jsType.*;

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
	
	private LinkedList<JsFunction> FunctionQueue;
	private JsCarryThread Carrier;
	
	public JsFunctionSchedular()
	{
		FunctionQueue=new LinkedList<JsFunction>();
		Carrier=new JsCarryThread(this);
		Carrier.start();
	}
	public synchronized JsFunction StashOrLaunchTask(JsFunction funcName)	//null input for launch
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
