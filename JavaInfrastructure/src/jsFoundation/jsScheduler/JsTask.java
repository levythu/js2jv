package jsFoundation.jsScheduler;

public abstract class JsTask extends Thread
{
	protected abstract boolean TaskContent();
	protected JsDelegate callback;
	
	public void run() 
	{
		if (TaskContent())
		{
			JsFunctionSchedular.GetGlobalSchedular().StashOrLaunchTask(callback);
		}
	}
}
