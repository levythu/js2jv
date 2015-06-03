package jsFoundation;
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
		JsFunctionSchedular.GetGlobalSchedular().StashOrLaunchTask(
				new JsDelegate(JsMain, new JsUndefined(), new JsList(), precludeClosure));
	}
}
