package jsFoundation.jsType;

import java.util.ArrayList; 

import jsFoundation.JsClosure;
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
	public JsString ToString()
	{
		String ans="[";
		int des=value.size();
		for (int i=0;i<des;i++)
		{
			ans+=value.get(i).toString();
			if (i!=des-1)
				ans+=",";
		}
		ans+="]";
		return new JsString(ans);
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
		String lookup=name.ToString()._getValue();
		if (lookup.equals("length"))
		{
			return new JsIntegral(value.size());
		}
		else if (lookup.equals("push"))
		{
			return _push;
		}
		else if (lookup.equals("push"))
		{
			return _pop;
		}
		else if (lookup.equals("toString"))
		{
			return JsVar._toString;
		}
		else
			return new JsUndefined();
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
		String lookup=name.ToString()._getValue();
		if (lookup.equals("length"))
		{
			if (val instanceof JsIntegral)
			{
				int len=(int)((JsIntegral)val)._getValue();
				if (len<0)
					throw new JsInvalidValue();
				if (len<value.size())
				{
					int nowlen=value.size();
					while (nowlen>len)
					{	
						value.remove(len-1);
						len--;
					}
				}
				else if (len>value.size())
				{
					SetProperty(new JsIntegral(len-1),new JsUndefined());
				}
			}
			else
				throw new JsInvalidValue();
		}
		else
			throw new JsInvalidIdentifier();
	}

	//==============================================================
	//==============================================================
	protected static JsList_Push _push=new JsList_Push();
	public static class JsList_Push extends JsFunction
	{
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception 
		{
			if (!(_this instanceof JsList))
				throw new JsWrongThisofNativeFunction();
			if (para.value.size()<1)
				return new JsUndefined();
			JsList thisObject=(JsList)_this;
			thisObject.value.add(para.value.get(0));

			return new JsUndefined();
		}
		public String GetCanonicalName() 
		{
			return "Js.List.push";
		}
	}
	
	protected static JsList_Pop _pop=new JsList_Pop();
	public static class JsList_Pop extends JsFunction
	{
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception 
		{
			if (!(_this instanceof JsList))
				throw new JsWrongThisofNativeFunction();
			JsList thisObject=(JsList)_this;
			if (thisObject.value.isEmpty())
				return new JsUndefined();
			else
				return thisObject.value.remove(thisObject.value.size()-1);
		}
		public String GetCanonicalName() 
		{
			return "Js.List.pop";
		}
	}
}
