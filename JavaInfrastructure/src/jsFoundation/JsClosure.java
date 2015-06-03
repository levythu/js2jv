package jsFoundation;

import java.util.HashMap;
import jsFoundation.jsType.*;

public class JsClosure
{
	private HashMap<String, JsVar> variableList;
	private JsClosure ParentClosure;
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
	public void Set(String varName, JsVar var)
	{
		variableList.put(varName, var);
	}
}
