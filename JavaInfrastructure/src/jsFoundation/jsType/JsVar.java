package jsFoundation.jsType;

import java.lang.String;
import jsFoundation.jsException.*;

public abstract class JsVar 
{
	public abstract String TypeOf();
	public abstract String ToString();
	public abstract JsVar Assign();
	//The following method is used-specified, but to keep weak-type feature we need to provide a default implementation.
	public JsVar execute(JsList args) throws JsInvalidOperatorException
	{
		throw new JsInvalidOperatorException();
	}
}
