import jsFoundation.JsClosure;
import jsFoundation.JsRuntime;
import jsFoundation.jsType.*;
public class Javascript extends JsFunction 
{
	public JsFunction GetDup() 
	{
		return new Javascript();
	}
	public String GetCanonicalName() 
	{
		return "test.Javascript.main";
	}
	public static void main(String[] args) throws Throwable
	{
		JsRuntime mn = new JsRuntime(new Javascript());
		mn.Run();
	}
	public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
	{
		closureInfo.FunctionDeclare("funcGen", new funcGen());
		closureInfo=JsClosure.foldClosure(closureInfo);
		{
			closureInfo.Declare("v10", new JsFloat(0));
			closureInfo.Declare("i", closureInfo.Get("v10"));
			closureInfo.Declare("v13", new JsFloat(5));
			closureInfo.Set("v14" ,closureInfo.Get("i").LessThan(closureInfo.Get("v13")));
			for (;closureInfo.Get("v14").ToBoolean()._getValue() == true;)
			{
				JsList v22 = new JsList();
				closureInfo.FunctionDeclare("v26", new v26());
				v22._push(closureInfo.Get("v26"));
				closureInfo.Declare("v27", new JsFloat(1000));
				closureInfo.Set("v28" ,closureInfo.Get("i").Asterisk(closureInfo.Get("v27")));
				v22._push(closureInfo.Get("v28"));
				closureInfo.Set("v29",closureInfo.Get("setTimeout").Execute(v22));
				closureInfo.Set("i", closureInfo.Get("i").Plus(new JsIntegral(1)));
				closureInfo.Declare("v13", new JsFloat(5));
				closureInfo.Set("v14" ,closureInfo.Get("i").LessThan(closureInfo.Get("v13")));
			}
			
		}
		closureInfo=JsClosure.unfoldClosure(closureInfo);
		closureInfo=JsClosure.foldClosure(closureInfo);
		{
			closureInfo.Declare("v31", new JsFloat(0));
			closureInfo.Declare("i", closureInfo.Get("v31"));
			closureInfo.Declare("v34", new JsFloat(5));
			closureInfo.Set("v35" ,closureInfo.Get("i").LessThan(closureInfo.Get("v34")));
			for (;closureInfo.Get("v35").ToBoolean()._getValue() == true;)
			{
				JsList v42 = new JsList();
				JsList v43 = new JsList();
				v43._push(closureInfo.Get("i"));
				closureInfo.Set("v44",closureInfo.Get("funcGen").Execute(v43));
				v42._push(closureInfo.Get("v44"));
				closureInfo.Declare("v45", new JsFloat(1000));
				closureInfo.Set("v46" ,closureInfo.Get("i").Asterisk(closureInfo.Get("v45")));
				v42._push(closureInfo.Get("v46"));
				closureInfo.Set("v47",closureInfo.Get("setTimeout").Execute(v42));
				closureInfo.Set("i", closureInfo.Get("i").Plus(new JsIntegral(1)));
				closureInfo.Declare("v34", new JsFloat(5));
				closureInfo.Set("v35" ,closureInfo.Get("i").LessThan(closureInfo.Get("v34")));
			}
			
		}
		closureInfo=JsClosure.unfoldClosure(closureInfo);
		return new JsUndefined();
	}
	public static class funcGen extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new funcGen();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.funcGen";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("i", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.FunctionDeclare("v8", new v8());
			closureInfo.Declare("x", closureInfo.Get("v8"));
			return closureInfo.Get("x");
		}
		public static class v8 extends JsFunction
		{
			public JsFunction GetDup()
			{
				return new v8();
			}
			public String GetCanonicalName()
			{
				return "Js.Preclude.v8";
			}
			public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
			{
				closureInfo.Set("v5",closureInfo.Get("console"));
				JsList v6 = new JsList();
				v6._push(closureInfo.Get("i"));
				closureInfo.Set("v7",closureInfo.Get("v5").GetProperty(new JsString("log")).Execute(v6));
				return new JsUndefined();
			}
			
		}
		
	}
	public static class v26 extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new v26();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.v26";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("v23",closureInfo.Get("console"));
			JsList v24 = new JsList();
			v24._push(closureInfo.Get("i"));
			closureInfo.Set("v25",closureInfo.Get("v23").GetProperty(new JsString("log")).Execute(v24));
			return new JsUndefined();
		}
		
	}
	
}
