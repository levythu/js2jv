package jsFoundation.jsType;

public class JsBoolean extends JsValue
{
	private boolean value;
	public JsBoolean(boolean val)
	{
		value=val;
	}
	
	public JsValue Clone() {
		return new JsBoolean(value);
	}
	
	public String ToString() {
		return Boolean.toString(value);
	}

	public String TypeOf() {
		return "boolean";
	}
}
