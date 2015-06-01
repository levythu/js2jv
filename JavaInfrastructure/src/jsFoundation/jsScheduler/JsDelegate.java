package jsFoundation.jsScheduler;

import jsFoundation.*;
import jsFoundation.jsType.*;

public class JsDelegate 
{
	public JsFunction delegateContent;
	public JsVar _this;
	public JsList para;
	public JsClosure closureInfo;
	public JsDelegate(JsFunction _delegateContent, JsVar __this, JsList _para, JsClosure _closureInfo)
	{
		delegateContent=_delegateContent;
		_this=__this;
		para=_para;
		closureInfo=_closureInfo;
	}
}
