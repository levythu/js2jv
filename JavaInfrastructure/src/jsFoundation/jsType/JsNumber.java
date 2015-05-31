package jsFoundation.jsType;

public abstract class JsNumber extends JsValue
{
	public JsString TypeOf() 
	{
		return new JsString("number");
	}
}
