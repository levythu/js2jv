package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidOperatorException;

public abstract class JsReference extends JsVar
{
	public JsVar Assign()
	{
		return this;
	}
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		return new JsBoolean(this==name);
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		return new JsBoolean(this==name);
	}
	
	public JsBoolean LessThan(JsVar name) throws Exception
	{
		return new JsBoolean(false);
	}
	public JsVar Plus(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Minus(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Asterisk(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Slash(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
}
