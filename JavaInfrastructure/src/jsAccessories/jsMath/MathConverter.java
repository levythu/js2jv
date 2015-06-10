package jsAccessories.jsMath;

import jsFoundation.jsException.*;
import jsFoundation.jsType.*;

public class MathConverter 
{
	public static double ExtractValueInDouble(JsVar th) throws Exception
	{
		if (th instanceof JsNumber)
			return ((JsNumber)th).Evaluate();
		if (th instanceof JsString)
		{
			try
			{
				double t=Double.parseDouble(((JsString)th)._getValue());
				return t;
			}
			catch (Throwable e)
			{}
			throw new JsInvalidValue();
		}
		if (th instanceof JsBoolean)
			return ((JsBoolean)th)._getValue()==true?1:0;
		throw new JsInvalidValue();
	}
}
