package jsFoundation.jsType;

import jsFoundation.jsException.*;

public class JsString extends JsValue
{
	private String value;
	public String _getValue()
	{
		return value;
	}
	public JsString(String val)
	{
		value=val;
	}
	
	public JsValue Clone() 
	{
		return new JsString(value);
	}
	public JsString TypeOf() 
	{
		return new JsString("string");
	}
	public JsString ToString() 
	{
		return new JsString(value);
	}
	
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		if (name instanceof JsNumber)
		{
			try
			{
				double x=Double.parseDouble(value);
				if (name instanceof JsIntegral)
					return new JsBoolean(((long)x)==((JsIntegral)name)._getValue());
				else if (name instanceof JsFloat)
					return new JsBoolean(x==((JsFloat)name)._getValue());
				else throw new LogicBomb();
			}
			catch (NumberFormatException e)
			{}
		}
		return new JsBoolean(value.equals(name.ToString()._getValue()));
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsString))
			return new JsBoolean(false);
		return new JsBoolean(((JsString)name).value==value);
	}
	public JsBoolean LessThan(JsVar name) throws Exception
	{
		if (name instanceof JsNumber)
		{
			try
			{
				double x=Double.parseDouble(value);
				if (name instanceof JsIntegral)
					return new JsBoolean(x<((JsIntegral)name)._getValue());
				else if (name instanceof JsFloat)
					return new JsBoolean(x<((JsFloat)name)._getValue());
				else throw new LogicBomb();
			}
			catch (NumberFormatException e)
			{}
		}
		return new JsBoolean(value.compareTo(name.ToString()._getValue())<0);
	}
	public JsVar Plus(JsVar name) throws Exception
	{
		return new JsString(value+name.ToString()._getValue());
	}
}
