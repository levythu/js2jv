package jsFoundation.jsType;

public class JsIntegral extends JsNumber
{
	private long value;
	public long _getValue()
	{
		return value;
	}
	public JsIntegral(long val)
	{
		value=val;
	}
	public double Evaluate() {
		return (double)value;
	}
	public long EvaluateInt()
	{
		return value;
	}
	
	public JsValue Clone() 
	{
		return new JsIntegral(value);
	}
	public JsString ToString() 
	{
		return new JsString(Long.toString(value));
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsIntegral))
			return new JsBoolean(false);
		return new JsBoolean(((JsIntegral)name).value==value);
	}
}
