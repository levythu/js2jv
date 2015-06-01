package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidOperatorException;

public class JsUndefined extends JsVar
{
	public JsString TypeOf() 
	{
		return new JsString("undefined");
	}
	public JsString ToString() 
	{
		return new JsString("undefined");		//WARN: in real JS this behavior leads to error.
	}
	public JsVar Assign() 
	{
		return this;
	}
	
	
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		return new JsBoolean(name instanceof JsUndefined);
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		return new JsBoolean(name instanceof JsUndefined);
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
