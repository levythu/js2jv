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
	public JsString ToString() {
		return new JsString(Boolean.toString(value));
	}
	public JsString TypeOf() {
		return new JsString("boolean");
	}
}
