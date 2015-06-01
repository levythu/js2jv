package jsFoundation.jsScheduler;

public class JsCarryThread extends Thread
{
	private JsFunctionSchedular schedular;
	
	public JsCarryThread(JsFunctionSchedular ps)
	{
		schedular=ps;
	}
	public void run()
	{
		JsDelegate func;
		while (true)
		{
			func=schedular.StashOrLaunchTask(null);
			if (func==null)
			{
				try
				{
					Thread.sleep(5000);
				}
				catch (Exception e)
				{}
			}
			else
			{
				try
				{
					func.delegateContent.Execute(func._this, func.para, func.closureInfo);
				}
				catch (Throwable e)
				{
					System.out.print("Js runtime error.");	//Used for debug.
					return;
				}
			}
		}
	}
}
