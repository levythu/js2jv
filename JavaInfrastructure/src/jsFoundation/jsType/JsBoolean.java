package jsFoundation.jsType;

public class JsBoolean extends JsValue
{
	private boolean value;
	public boolean _getValue()
	{
		return value;
	}
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
	
	public JsBoolean ToBoolean() throws Exception
	{
		return new JsBoolean(value);
	}
	
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		throw new Exception();
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsBoolean))
			return new JsBoolean(false);
		return new JsBoolean(((JsBoolean)name).value==value);
	}
}
