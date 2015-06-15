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
					System.out.println("++ Function <"+func.delegateContent.GetCanonicalName()+"> starts to run.");
					func.delegateContent.Execute(func.para);
					System.out.println("++ Function <"+func.delegateContent.GetCanonicalName()+"> exits without exception.");
				}
				catch (Throwable e)
				{
					System.err.println("Js runtime error when executing function <"+func.delegateContent.GetCanonicalName()+">");	//Used for debug.
					e.printStackTrace();
					return;
				}
			}
		}
	}
}
