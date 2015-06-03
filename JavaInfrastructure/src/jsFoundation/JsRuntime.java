package jsFoundation;

import jsAccessories.jsTimer.*;
import jsFoundation.jsScheduler.JsDelegate;
import jsFoundation.jsScheduler.JsFunctionSchedular;
import jsFoundation.jsType.*;

public class JsRuntime 
{
	private JsFunction JsMain;
	private JsClosure precludeClosure;
	
	public JsRuntime(JsFunction mainer)
	{
		JsMain=mainer;
		precludeClosure=new JsClosure(null);
		JsFunctionSchedular.initSchedular();
	}
	public void Run()
	{
		precludeClosure.Set("setTimeout", new TimeOut.SetTimeOut());
		precludeClosure.Set("clearTimeout", new TimeOut.ClearTimeOut());
		precludeClosure.Set("setInterval", new TimeInterval.SetInterval());
		precludeClosure.Set("clearInterval", new TimeInterval.ClearInterval());
		
		JsFunctionSchedular.GetGlobalSchedular().StashOrLaunchTask(
				new JsDelegate(JsMain, new JsUndefined(), new JsList(), precludeClosure));
	}
}
