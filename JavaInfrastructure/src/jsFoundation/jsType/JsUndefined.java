package jsFoundation.jsType;

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
}
