package jsFoundation.jsScheduler;

import jsFoundation.jsType.*;

public abstract class JsTask extends Thread
{
	protected abstract boolean TaskContent();
	protected JsFunction callback;
	
	public void run() 
	{
		if (TaskContent())
		{
			JsFunctionSchedular.GetGlobalSchedular().StashOrLaunchTask(callback);
		}
	}
}
