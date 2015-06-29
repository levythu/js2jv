import jsFoundation.JsClosure;
import jsFoundation.JsRuntime;
import jsFoundation.jsType.*;

public class ComTest extends JsFunction
{
	public JsFunction GetDup()
	{
		return new ComTest();
	}
	public String GetCanonicalName()
	{
		return "test.ComTest.main";
	}
	public static void main(String[] args) throws Throwable
	{
		JsRuntime mn=new JsRuntime(new ComTest());
		mn.Run();
		//UnitCaseTest4Type.OperatorTest();
	}

	public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
	{
		closureInfo=JsClosure.foldClosure(closureInfo);
		{
			closureInfo.FunctionDeclare("Gener", new Gener());
			closureInfo.Declare("i", new JsIntegral(10086));
			closureInfo.Get("console").GetProperty("readline").Execute(
					new JsList(closureInfo.Get("Gener").Execute(
							new JsList(closureInfo.Get("i"))
					))
			);

			closureInfo=JsClosure.foldClosure(closureInfo);
			{
				closureInfo.FunctionDeclare("GenedFunc", new GenedFunc());
				closureInfo.Declare("i", new JsIntegral(1));
				JsVar constNum4=new JsIntegral(4);
				JsVar constNum1000=new JsIntegral(1000);
				JsVar constNum1=new JsIntegral(1);
				while (closureInfo.Get("i").LessThan(constNum4)._getValue())
				{
					closureInfo.Get("setTimeout").Execute(
							new JsList(closureInfo.Get("GenedFunc"),closureInfo.Get("i").Asterisk(constNum1000))
					);
					closureInfo.Set("i", closureInfo.Get("i").Plus(constNum1));
				}
			}
			closureInfo=JsClosure.unfoldClosure(closureInfo);

			closureInfo.Declare("i", new JsIntegral(1));
			JsVar constNum4=new JsIntegral(4);
			JsVar constNum1000=new JsIntegral(1000);
			JsVar constNum1=new JsIntegral(1);
			while (closureInfo.Get("i").LessThan(constNum4)._getValue())
			{
				JsVar resF=closureInfo.Get("Gener").Execute(new JsList(closureInfo.Get("i")));
				closureInfo.Get("setTimeout").Execute(
						new JsList(resF,closureInfo.Get("i").Asterisk(constNum1000))
				);
				closureInfo.Set("i", closureInfo.Get("i").Plus(constNum1));
			}
		}
		closureInfo=JsClosure.unfoldClosure(closureInfo);

		return null;
	}

	static class GenedFunc extends JsFunction
	{

		public String GetCanonicalName()
		{
			return "test.ComTest.GenedFunc";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			System.out.println(closureInfo.Get("i").ToString()._getValue());

			return JsUndefined.getInstance();
		}
		public JsFunction GetDup()
		{
			return new GenedFunc();
		}
	}

	static class Gener extends JsFunction
	{
		public String GetCanonicalName()
		{
			return "test.ComTest.Gener";
		}
		public JsVar ExecuteDetail(JsClosure cl) throws Exception
		{
			cl.Declare("i", cl.Get("arguments").GetProperty(new JsIntegral(0)));
			cl.FunctionDeclare("tmpr", new GenedFunc());
			return cl.Get("tmpr");
		}
		public JsFunction GetDup()
		{
			return new Gener();
		}
	}
}
