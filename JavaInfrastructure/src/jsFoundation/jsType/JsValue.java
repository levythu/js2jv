package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidIdentifier;

public abstract class JsValue extends JsVar
{
	public abstract JsValue Clone();
	public JsVar Assign()
	{
		return Clone();
	}
	public JsVar GetProperty(String name) throws Exception
	{
		String lookup=name;
		if (lookup.equals("toString"))
		{
			return JsVar._toString;
		}
		throw new JsInvalidIdentifier();
	}
}
