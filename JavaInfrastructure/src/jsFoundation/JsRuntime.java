package jsFoundation;

import jsAccessories.jsMath.*;
import jsAccessories.jsProcess.*;
import jsAccessories.jsTimer.*;
import jsAccessories.jsConsole.*;
import jsFoundation.jsScheduler.*;
import jsFoundation.jsType.*;

public class JsRuntime 
{
	private JsFunction JsMain;
	private JsClosure precludeClosure;
	
	public JsRuntime(JsFunction mainer)
	{
		JsMain=JsFunction.dup(mainer, JsUndefined.getInstance());
		precludeClosure=new JsClosure(null);
		JsFunctionSchedular.initSchedular();
	}
	
	private void preparePreClosure()
	{
		precludeClosure.Set("setTimeout", new TimeOut.SetTimeOut());
		precludeClosure.Set("clearTimeout", new TimeOut.ClearTimeOut());
		precludeClosure.Set("setInterval", new TimeInterval.SetInterval());
		precludeClosure.Set("clearInterval", new TimeInterval.ClearInterval());
		
		precludeClosure.Set("console", ConsoleManager.require());
		precludeClosure.Set("process", ProcessManager.require());
		precludeClosure.Set("Math", MathManager.require());
	}
	public void Run()
	{
		preparePreClosure();
		
		JsMain.SetClosure(precludeClosure);
		JsFunctionSchedular.GetGlobalSchedular().StashOrLaunchTask(
				new JsDelegate(JsMain, new JsList()));
	}
}
