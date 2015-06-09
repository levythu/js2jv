package jsFoundation.jsType;

//import java.util.HashMap;
import jsFoundation.JsClosure;
import jsFoundation.jsException.JsInvalidIdentifier;
import jsFoundation.jsException.JsNoThis;
import jsFoundation.jsException.JsUndefinedClosure;

public abstract class JsFunction extends JsReference
{
	public static JsFunction dup(JsFunction ori)
	{
		JsFunction res=ori.GetDup();
		res._this=null;
		res.pClosure=ori.pClosure;
		return res;
	}
	public static JsFunction dup(JsFunction ori, JsVar newThis)
	{
		JsFunction res=ori.GetDup();
		res._this=newThis;
		res.pClosure=ori.pClosure;
		return res;
	}
	
	//private static HashMap<String, JsClosure> closureMap=new HashMap<String, JsClosure>();
	private JsClosure pClosure;
	private JsVar _this;
	/**
	public void SetClosure(JsClosure obj)
	{
		String type=GetCanonicalName();
		if (closureMap.containsKey(type))
			System.out.println("*****Fatal error: closure of function <"+type+"> is set twice.*****");
		else
			closureMap.put(type, obj);
	}
	public JsClosure GetClosure() throws JsUndefinedClosure
	{
		if (isNative())
			return null;
		String type=GetCanonicalName();
		if (!closureMap.containsKey(type))
			throw new JsUndefinedClosure();
		return closureMap.get(type);
	}
	**/
	public void SetClosure(JsClosure obj)
	{
		if (pClosure!=null)
			System.err.println("*****Fatal error: closure of function <"+GetCanonicalName()+"> is set twice.*****");
		else
			pClosure=obj;
	}
	public JsClosure GetClosure() throws JsUndefinedClosure
	{
		if (isNative())
			return null;
		if (pClosure==null)
			throw new JsUndefinedClosure();
		return pClosure;
	}
	
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
		
	public JsVar Execute(JsList para) throws Exception
	{
		if (_this==null)
			throw new JsNoThis();
		JsClosure newClosure=new JsClosure(GetClosure());
		newClosure.Declare("this", _this);
		newClosure.Declare("arguments", para);
		
		return ExecuteDetail(newClosure);
	}
	public JsVar GetProperty(String name) throws Exception
	{
		String lookup=name;
		if (lookup.equals("toString"))
		{
			return JsFunction.dup(JsVar._toString,this);
		}
		throw new JsInvalidIdentifier();
	}
	public boolean isNative()	//If true, it does not need a closure to be executed.
	{
		return false;
	}
	
	public abstract String GetCanonicalName();
	public abstract JsVar ExecuteDetail(JsClosure closureInfo) throws Exception;	//Remain to modify
	public abstract JsFunction GetDup();
	
	public static abstract class JsNativeFunction extends JsFunction
	{
		public boolean isNative()
		{
			return true;
		}
	}
}
