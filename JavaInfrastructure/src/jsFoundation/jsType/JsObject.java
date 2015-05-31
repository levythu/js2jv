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
	public JsVar GetProperty(JsVar name) throws Exception
	{
		String query=name.ToString()._getValue();
		if (!map.containsKey(query))
			return new JsUndefined();
		return map.get(query);
	}
	public void SetProperty(JsVar name, JsVar value) throws Exception
	{
		String query=name.ToString()._getValue();
		map.put(query, value.Assign());	
	}
}
