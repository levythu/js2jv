package jsAccessories.jsMath;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class MathManager
{	
	public static JsObject require()
	{
		JsObject ans=new JsObject();
		try
		{
			ans.SetProperty("sqrt", new MathSQRT());
			ans.SetProperty("floor", new MathFloor());
			ans.SetProperty("round", new MathRound());
			ans.SetProperty("ceil", new MathCeil());
			ans.SetProperty("abs", new MathABS());
			ans.SetProperty("log", new MathLog());
			
			ans.SetProperty("sin", new TriangleFunctions.MathSin());
			ans.SetProperty("cos", new TriangleFunctions.MathCos());
			ans.SetProperty("tan", new TriangleFunctions.MathTan());
			ans.SetProperty("asin", new TriangleFunctions.MathASIN());
			ans.SetProperty("acos", new TriangleFunctions.MathACOS());
			ans.SetProperty("atan", new TriangleFunctions.MathATAN());
			
			ans.SetProperty("random", new ConstantProvider.MathRandom());
			ans.SetProperty("E", new ConstantProvider.MathE());
			ans.SetProperty("PI", new ConstantProvider.MathPI());
		}
		catch (Exception e)
		{}
		return ans;
	}
	
	public static class MathSQRT extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathSQRT();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.sqrt";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsFloat(Double.NaN);
			double res;
			try
			{
				res=MathConverter.ExtractValueInDouble(para.value.get(0));
			}
			catch (Throwable e)
			{
				return new JsFloat(Double.NaN);
			}
			
			return new JsFloat(Math.sqrt(res));
		}
	}
	
	public static class MathFloor extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathFloor();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.floor";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsFloat(Double.NaN);
			if (para.value.get(0) instanceof JsIntegral)
				return para.value.get(0);
			double res;
			try
			{
				res=MathConverter.ExtractValueInDouble(para.value.get(0));
			}
			catch (Throwable e)
			{
				return new JsFloat(Double.NaN);
			}
			
			return new JsFloat(Math.floor(res));
		}
	}
	
	public static class MathRound extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathRound();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.round";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsFloat(Double.NaN);
			if (para.value.get(0) instanceof JsIntegral)
				return para.value.get(0);
			double res;
			try
			{
				res=MathConverter.ExtractValueInDouble(para.value.get(0));
			}
			catch (Throwable e)
			{
				return new JsFloat(Double.NaN);
			}
			
			return new JsFloat(Math.round(res));
		}
	}
	
	public static class MathCeil extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathCeil();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.ceil";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsFloat(Double.NaN);
			if (para.value.get(0) instanceof JsIntegral)
				return para.value.get(0);
			double res;
			try
			{
				res=MathConverter.ExtractValueInDouble(para.value.get(0));
			}
			catch (Throwable e)
			{
				return new JsFloat(Double.NaN);
			}
			
			return new JsFloat(Math.ceil(res));
		}
	}
	
	public static class MathABS extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathABS();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.abs";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsFloat(Double.NaN);
			double res;
			try
			{
				res=MathConverter.ExtractValueInDouble(para.value.get(0));
			}
			catch (Throwable e)
			{
				return new JsFloat(Double.NaN);
			}
			
			return new JsFloat(Math.abs(res));
		}
	}
	
	public static class MathLog extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathLog();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.log";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsFloat(Double.NaN);
			double res;
			try
			{
				res=MathConverter.ExtractValueInDouble(para.value.get(0));
			}
			catch (Throwable e)
			{
				return new JsFloat(Double.NaN);
			}
			
			return new JsFloat(Math.log(res));
		}
	}
}
