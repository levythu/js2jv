package tester;

import jsFoundation.JsRuntime;

public class testerMain 
{
	public static void main(String[] args) throws Throwable
	{
		JsRuntime mn=new JsRuntime(new UnitTest4Scheduler());
		mn.Run();
	}
}
