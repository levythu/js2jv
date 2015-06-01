package jsFoundation.jsType;

import java.util.ArrayList; 
import jsFoundation.jsException.*;

public class JsList extends JsReference
{
	ArrayList<JsVar> value;
	
	public JsList()
	{
		value=new ArrayList<JsVar>();
	}
	public JsString TypeOf() 
	{
		return new JsString("object");
	}
	public JsString ToString() //WARN: not implemented yet.
	{
		return null;
	}
	
	public JsVar GetProperty(JsVar name) throws Exception
	{
		if (name instanceof JsNumber)
		{
			int id;
			if (name instanceof JsIntegral)
				id=(int)((JsIntegral)name)._getValue();
			else if (name instanceof JsFloat)
				id=(int)((JsFloat)name)._getValue();
			else throw new LogicBomb();
			if (id>=0 && id<value.size())
				return value.get(id);
			else
				return new JsUndefined();
		}
		throw new Exception();
	}
	public void SetProperty(JsVar name, JsVar val) throws Exception
	{
		if (name instanceof JsNumber)
		{
			int id;
			if (name instanceof JsIntegral)
				id=(int)((JsIntegral)name)._getValue();
			else if (name instanceof JsFloat)
				id=(int)((JsFloat)name)._getValue();
			else throw new LogicBomb();
			
			if (id<0) throw new JsInvalidIdentifier();
			int needsToPush=id-value.size()+1;
			while (needsToPush>0)
			{
				needsToPush--;
				value.add(new JsUndefined());
			}
			
			value.set(id, val);
			return;
		}
		throw new JsInvalidIdentifier();
	}
}
