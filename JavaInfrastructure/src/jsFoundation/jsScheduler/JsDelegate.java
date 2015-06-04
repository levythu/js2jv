package jsFoundation.jsScheduler;

import jsFoundation.jsType.*;

public class JsDelegate 
{
	public JsFunction delegateContent;
	public JsList para;
	public JsDelegate(JsFunction _delegateContent, JsList _para)
	{
		delegateContent=_delegateContent;
		para=_para;
	}
}
