package jsFoundation.jsType;

//import jsFoundation.jsException.JsInvalidOperatorException;

public abstract class JsFunction extends JsReference
{
	JsList Args;
	public JsString TypeOf() 
	{
		return new JsString("function");
	}
	public JsString ToString() 
	{
		return new JsString("function(){...}");
	}
	public abstract JsVar Execute() throws Exception;
}
