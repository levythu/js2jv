package jsFoundation.jsType;

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
}
