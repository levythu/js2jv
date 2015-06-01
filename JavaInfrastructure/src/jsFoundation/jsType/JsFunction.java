package jsFoundation.jsType;

import jsFoundation.JsClosure;

public abstract class JsFunction extends JsReference
{
	public JsString TypeOf() 
	{
		return new JsString("function");
	}
	public JsString ToString() 
	{
		return new JsString("function(){...}");
	}
	public abstract JsVar Execute(JsVar _this, JsList para, JsClosure closureInfo) throws Exception;	//Ramain to modify
}
