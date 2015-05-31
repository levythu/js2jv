package jsFoundation.jsType;

public class JsUndefined extends JsVar
{
	public String TypeOf() 
	{
		return "undefined";
	}

	public String ToString() 
	{
		return "undefined";		//WARN: in real JS this behavior leads to error.
	}

	public JsVar Assign() 
	{
		return this;
	}

}
