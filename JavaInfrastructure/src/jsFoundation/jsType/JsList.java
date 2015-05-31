package jsFoundation.jsType;

public class JsList extends JsReference
{
	public JsString TypeOf() 
	{
		return new JsString("object");
	}
	public JsString ToString() //WARN: not implemented yet.
	{
		return null;
	}
}
