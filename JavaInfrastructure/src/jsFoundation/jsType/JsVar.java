package jsFoundation.jsType;

import java.lang.String;
import jsFoundation.jsException.*;

public abstract class JsVar 
{
	public abstract String TypeOf();
	public abstract String ToString();
	public abstract JsVar Assign();
	//The following method is used-specified, but to keep weak-type feature we need to provide a default implementation.
	//For function====================
	public JsVar Execute(JsList args) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	//================================
	//For string/object/list==========
	public JsVar GetProperty(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public void SetProperty(JsVar name, JsVar value) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	//================================
	//Unary operator for everyone====
	public JsBoolean Exclaimation() throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	//================================
	//Binary operator for everyone====
	public JsBoolean EqualTo(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsBoolean IdenticalTo(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsBoolean LessThan(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Plus(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Minus(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Asterisk(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Slash(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsBoolean And(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsBoolean Or(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
	public JsBoolean Xor(JsVar name) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
}
