package jsFoundation.jsType;

public class JsIntegral extends JsNumber
{
	private long value;
	public JsIntegral(long val)
	{
		value=val;
	}
	
	public JsValue Clone() {
		return new JsIntegral(value);
	}

	public String ToString() {
		return Long.toString(value);
	}
}
