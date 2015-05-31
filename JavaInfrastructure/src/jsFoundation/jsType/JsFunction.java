package jsFoundation.jsType;

//import jsFoundation.jsException.JsInvalidOperatorException;

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
	public abstract JsVar Execute(JsList args) throws Exception;
}
