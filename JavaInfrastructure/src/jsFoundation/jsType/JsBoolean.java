package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidOperatorException;

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
		if (name instanceof JsNumber)
			return new JsBoolean(((JsNumber)name).Evaluate()!=0);
		if (name instanceof JsBoolean)
			return IdenticalTo(name);
		return name.EqualTo(this);
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsBoolean))
			return new JsBoolean(false);
		return new JsBoolean(((JsBoolean)name).value==value);
	}
	public JsBoolean LessThan(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.GreaterThan(this);
		throw new JsInvalidOperatorException();
	}
	public JsBoolean GreaterThan(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.LessThan(this);
		throw new JsInvalidOperatorException();
	}
	public JsVar Plus(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.Plus(this);
		throw new JsInvalidOperatorException();
	}
	public JsVar Minus(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.BeMinus(this);
		throw new JsInvalidOperatorException();
	}
	public JsVar BeMinus(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.Minus(this);
		throw new JsInvalidOperatorException();
	}
	public JsVar Asterisk(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.Asterisk(this);
		throw new JsInvalidOperatorException();
	}
	public JsVar Slash(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.Slash(this);
		throw new JsInvalidOperatorException();
	}
	public JsVar BeSlash(JsVar name) throws Exception
	{
		if ((name instanceof JsString))
			return name.BeSlash(this);
		throw new JsInvalidOperatorException();
	}
}
