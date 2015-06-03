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
			return JsVar._toString;
		}
		if (!map.containsKey(query))
			return new JsUndefined();
		return map.get(query);
	}
	public void SetProperty(String name, JsVar value) throws Exception
	{
		String query=name;
		map.put(query, value);	
	}
}
