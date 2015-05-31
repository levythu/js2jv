package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidOperatorException;

public class JsFunction extends JsReference
{
	public String TypeOf() 
	{
		return "function";
	}
	
	public String ToString() 
	{
		return "function(){...}";
	}
	
	public JsVar Execute(JsList args) throws JsInvalidOperatorException
	{
		return new JsUndefined();
	}
}
