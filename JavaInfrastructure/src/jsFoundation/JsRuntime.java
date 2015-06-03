package jsFoundation;

import jsAccessories.jsTimer.TimeOut;
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
		
		JsFunctionSchedular.GetGlobalSchedular().StashOrLaunchTask(
				new JsDelegate(JsMain, new JsUndefined(), new JsList(), precludeClosure));
	}
}
