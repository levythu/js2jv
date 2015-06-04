package jsFoundation;

import java.util.HashMap;
import jsFoundation.jsException.JsRootClosure;
import jsFoundation.jsType.*;

public class JsClosure
{
	private HashMap<String, JsVar> variableList;
	private JsClosure ParentClosure;
	
	public static JsClosure foldClosure(JsClosure ori)
	{
		return new JsClosure(ori);
	}
	public static JsClosure unfoldClosure(JsClosure ori) throws JsRootClosure
	{
		if (ori.ParentClosure==null)
			throw new JsRootClosure();
		return ori.ParentClosure;
	}
	
	public JsClosure(JsClosure par)
	{
		ParentClosure=par;
		variableList=new HashMap<String, JsVar>();
	}
	public JsVar Get(String varName)
	{
		JsClosure current=this;
		while (current!=null)
		{
			if (current.variableList.containsKey(varName))
				return current.variableList.get(varName);
			current=current.ParentClosure;
		}
		return new JsUndefined();
	}
	public void Declare(String varName, JsVar var)
	{
		if (var instanceof JsFunction)
		{
			JsFunction oldvar=(JsFunction)var;
			var=JsFunction.dup(oldvar, new JsUndefined());
		}
		variableList.put(varName, var);
	}
	public void FunctionDeclare(String varName, JsFunction func)	//Must use this for declaring new function!
	{
		JsFunction oldvar=func;
		func=JsFunction.dup(oldvar, new JsUndefined());
		func.SetClosure(this);
		
		Declare(varName,func);
	}
	public void Set(String varName, JsVar var)
	{
		if (var instanceof JsFunction)
		{
			JsFunction oldvar=(JsFunction)var;
			var=JsFunction.dup(oldvar, new JsUndefined());
		}
		JsClosure current=this;
		while (current!=null)
		{
			if (current.variableList.containsKey(varName))
			{
				current.variableList.put(varName, var);
				return;
			}
			current=current.ParentClosure;
		}
		
		variableList.put(varName, var);
	}
}
