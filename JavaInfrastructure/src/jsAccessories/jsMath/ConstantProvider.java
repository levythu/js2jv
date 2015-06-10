package jsAccessories.jsMath;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class ConstantProvider 
{
	public static class MathRandom extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathRandom();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.random";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			return new JsFloat(Math.random());
		}
	}

	public static class MathE extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathE();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.E";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			return new JsFloat(Math.E);
		}
	}

	public static class MathPI extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathPI();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.PI";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			return new JsFloat(Math.PI);
		}
	}
}
