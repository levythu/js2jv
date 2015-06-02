package jsFoundation.jsType;

import jsFoundation.JsClosure;
import jsFoundation.jsException.JsInvalidIdentifier;

public abstract class JsFunction extends JsReference
{
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		return IdenticalTo(name);
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsFunction))
			return new JsBoolean(false);
		return new JsBoolean(GetCanonicalName().equals(((JsFunction)name).GetCanonicalName()));
	}
	
	public JsString TypeOf() 
	{
		return new JsString("function");
	}
	public JsString ToString() 
	{
		return new JsString("function(){...}");
	}
	public JsVar Execute(JsVar _this, JsList para, JsClosure closureInfo) throws Exception
	{
		JsClosure newClosure=new JsClosure(closureInfo);
		return ExecuteDetail(_this,para,newClosure);
	}
	public JsVar GetProperty(JsVar name) throws Exception
	{
		String lookup=name.ToString()._getValue();
		if (lookup.equals("toString"))
		{
			return JsVar._toString;
		}
		throw new JsInvalidIdentifier();
	}
	
	public abstract String GetCanonicalName();
	public abstract JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception;	//Ramain to modify
}
