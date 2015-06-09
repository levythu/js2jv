package jsFoundation.jsType;

import java.util.HashMap;

public class JsObject extends JsReference
{
	private HashMap<String, JsVar> map;
	
	public JsObject()
	{
		map=new HashMap<String, JsVar>();
	}
	public JsString TypeOf() 
	{
		return new JsString("object");
	}
	public JsString ToString() 
	{
		return new JsString("[object Object]");
	}
	public JsVar GetProperty(String name) throws Exception
	{
		String query=name;

		if (query.equals("toString"))
		{
			return JsFunction.dup(JsVar._toString,this);
		}
		if (!map.containsKey(query))
			return JsUndefined.getInstance();
		return map.get(query);
	}
	public void SetProperty(String name, JsVar value) throws Exception
	{
		if (value instanceof JsFunction)
		{
			JsFunction oldvar=(JsFunction)value;
			value=JsFunction.dup(oldvar, this);
		}
		String query=name;
		map.put(query, value);	
	}
	public void SetProperty(JsVar name, JsVar val) throws Exception
	{
		SetProperty(name.ToString()._getValue(),val);
	}
	public JsVar GetProperty(JsVar name) throws Exception
	{
		return GetProperty(name.ToString()._getValue());
	}
}
