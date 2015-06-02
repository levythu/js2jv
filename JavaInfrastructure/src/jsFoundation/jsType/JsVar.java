package jsFoundation.jsType;

import jsFoundation.JsClosure;
import jsFoundation.jsException.*;

public abstract class JsVar 
{
	public abstract JsString TypeOf();
	public abstract JsString ToString();
	@Deprecated
	public abstract JsVar Assign();
	//The following method is used-specified, but to keep weak-type feature we need to provide a default implementation.
	//For function====================
	public JsVar Execute(JsVar _this, JsList para, JsClosure closureInfo) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	//================================
	//For string/object/list==========
	public JsVar GetProperty(JsVar name) throws Exception	//Please use assign artificially.
	{
		throw new JsInvalidOperatorException();
	}
	public void SetProperty(JsVar name, JsVar value) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	//================================
	//Unary operator for everyone====
	public JsBoolean Exclaimation() throws Exception
	{
		return new JsBoolean(!this.ToBoolean()._getValue());
	}
	public JsBoolean ToBoolean() throws Exception
	{
		return new JsBoolean(true);
	}
	//================================
	//Binary operator for everyone====
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		return new JsBoolean(false);
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		return new JsBoolean(false);
	}
	public JsBoolean LessThan(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsBoolean GreaterThan(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Plus(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar Minus(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsVar BeMinus(JsVar name) throws Exception
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
	public JsVar BeSlash(JsVar name) throws Exception
	{
		throw new JsInvalidOperatorException();
	}
	public JsBoolean And(JsVar name) throws Exception
	{
		return new JsBoolean(this.ToBoolean()._getValue() && name.ToBoolean()._getValue());
	}
	public JsBoolean Or(JsVar name) throws Exception
	{
		return new JsBoolean(this.ToBoolean()._getValue() || name.ToBoolean()._getValue());
	}
	public JsBoolean Xor(JsVar name) throws Exception
	{
		return new JsBoolean(this.ToBoolean()._getValue() ^ name.ToBoolean()._getValue());
	}
	
	//==============================================================
	//==============================================================
	protected static Var_ToString _toString=new Var_ToString();
	public static class Var_ToString extends JsFunction
	{
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception 
		{
			return _this.ToString();
		}
		public String GetCanonicalName() 
		{
			return "Js.Var.toString";
		}
	}
}
