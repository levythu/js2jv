package jsFoundation.jsType;

import jsFoundation.JsClosure;

public abstract class JsFunction extends JsReference
{
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		return IdenticalTo(name);
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsFunction))
			return new JsBoolean(false);
		return new JsBoolean(GetCanonicalName().equals(((JsFunction)name).GetCanonicalName()));
	}
	
	public JsString TypeOf() 
	{
		return new JsString("function");
	}
	public JsString ToString() 
	{
		return new JsString("function(){...}");
	}
	
	public abstract String GetCanonicalName();
	public abstract JsVar Execute(JsVar _this, JsList para, JsClosure closureInfo) throws Exception;	//Ramain to modify
}
