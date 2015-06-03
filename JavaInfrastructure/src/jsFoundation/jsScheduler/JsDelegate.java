package jsFoundation.jsScheduler;

import jsFoundation.jsType.*;

public class JsDelegate 
{
	public JsFunction delegateContent;
	public JsVar _this;
	public JsList para;
	public JsDelegate(JsFunction _delegateContent, JsVar __this, JsList _para)
	{
		delegateContent=_delegateContent;
		_this=__this;
		para=_para;
	}
}
