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
	
	public JsValue Clone() 
	{
		return new JsIntegral(value);
	}
	public JsString ToString() 
	{
		return new JsString(Long.toString(value));
	}
}
