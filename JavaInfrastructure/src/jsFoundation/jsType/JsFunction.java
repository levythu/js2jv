package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidOperatorException;

public abstract class JsFunction extends JsReference
{
	public String TypeOf() 
	{
		return "function";
	}
	
	public String ToString() 
	{
		return "function(){...}";
	}
	
	public abstract JsVar Execute(JsList args) throws JsInvalidOperatorException;
}
