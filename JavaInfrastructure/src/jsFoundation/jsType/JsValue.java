package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidIdentifier;

public abstract class JsValue extends JsVar
{
	public abstract JsValue Clone();
	public JsVar Assign()
	{
		return Clone();
	}
	public JsVar GetProperty(JsVar name) throws Exception
	{
		String lookup=name.ToString()._getValue();
		if (lookup.equals("toString"))
		{
			return JsVar._toString;
		}
		throw new JsInvalidIdentifier();
	}
}
