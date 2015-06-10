package jsAccessories.jsMath;

import jsFoundation.JsClosure;
import jsFoundation.jsType.*;

public class TriangleFunctions 
{
	public static class MathSin extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathSin();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.sin";
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
			
			return new JsFloat(Math.sin(res));
		}
	}

	public static class MathCos extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathCos();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.cos";
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
			
			return new JsFloat(Math.cos(res));
		}
	}

	public static class MathTan extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathTan();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.tan";
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
			
			return new JsFloat(Math.tan(res));
		}
	}

	public static class MathASIN extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathASIN();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.asin";
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
			
			return new JsFloat(Math.asin(res));
		}
	}

	public static class MathACOS extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathACOS();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.acos";
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
			
			return new JsFloat(Math.acos(res));
		}
	}

	public static class MathATAN extends JsFunction.JsNativeFunction
	{		
		public JsFunction GetDup()
		{
			return new MathATAN();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.atan";
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
			
			return new JsFloat(Math.atan(res));
		}
	}
}
