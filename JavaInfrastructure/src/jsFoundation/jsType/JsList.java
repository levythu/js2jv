package jsFoundation.jsType;

import java.util.ArrayList;

import jsFoundation.JsClosure;
import jsFoundation.jsException.*;

public class JsList extends JsReference
{
	public ArrayList<JsVar> value;

	public JsList()
	{
		value=new ArrayList<JsVar>();
	}
	public JsList(JsVar e1)
	{
		value=new ArrayList<JsVar>();
		_push(e1);
	}
	public JsList(JsVar e1, JsVar e2)
	{
		value=new ArrayList<JsVar>();
		_push(e1);
		_push(e2);
	}
	public JsList(JsVar e1, JsVar e2, JsVar e3)
	{
		value=new ArrayList<JsVar>();
		_push(e1);
		_push(e2);
		_push(e3);
	}
	public JsList(JsVar e1, JsVar e2, JsVar e3, JsVar e4)
	{
		value=new ArrayList<JsVar>();
		_push(e1);
		_push(e2);
		_push(e3);
		_push(e4);
	}
	public JsList(JsVar e1, JsVar e2, JsVar e3, JsVar e4, JsVar e5)
	{
		value=new ArrayList<JsVar>();
		_push(e1);
		_push(e2);
		_push(e3);
		_push(e4);
		_push(e5);
	}
	public JsList(JsVar e1, JsVar e2, JsVar e3, JsVar e4, JsVar e5, JsVar e6)
	{
		value=new ArrayList<JsVar>();
		_push(e1);
		_push(e2);
		_push(e3);
		_push(e4);
		_push(e5);
		_push(e6);
	}
	public void _push(JsVar var)
	{
		if (var instanceof JsFunction)
		{
			JsFunction oldvar=(JsFunction)var;
			var=JsFunction.dup(oldvar, this);
		}
		value.add(var);
	}
	public JsString TypeOf()
	{
		return new JsString("object");
	}
	public JsString ToString()
	{
		String ans="";
		int des=value.size();
		for (int i=0;i<des;i++)
		{
			ans+=value.get(i).ToString()._getValue();
			if (i!=des-1)
				ans+=",";
		}
		return new JsString(ans);
	}
	public JsVar GetProperty(String name) throws Exception
	{
		String lookup=name;
		if (lookup.equals("length"))
		{
			return new JsIntegral(value.size());
		}
		else if (lookup.equals("push"))
		{
			return JsFunction.dup(_push, this);
		}
		else if (lookup.equals("pop"))
		{
			return JsFunction.dup(_pop,this);
		}
		else if (lookup.equals("shift"))
		{
			return JsFunction.dup(_shift,this);
		}
		else if (lookup.equals("unshift"))
		{
			return JsFunction.dup(_unshift,this);
		}
		else if (lookup.equals("toString"))
		{
			return JsFunction.dup(JsVar._toString,this);
		}
		else
			return new JsUndefined();
	}
	public void SetProperty(String name, JsVar val) throws Exception
	{
		String lookup=name;
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
						value.remove(nowlen-1);
						nowlen--;
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
		return GetProperty(lookup);
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

			if (val instanceof JsFunction)
			{
				JsFunction oldvar=(JsFunction)val;
				val=JsFunction.dup(oldvar, this);
			}
			value.set(id, val);
			return;
		}
		String lookup=name.ToString()._getValue();
		SetProperty(lookup,val);
	}

	//==============================================================
	//==============================================================
	protected static JsList_Push _push=new JsList_Push();
	public static class JsList_Push extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new JsList_Push();
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			JsVar _this=closureInfo.Get("this");
			JsList para=(JsList)closureInfo.Get("arguments");
			if (!(_this instanceof JsList))
				throw new JsWrongThisofNativeFunction();
			JsList thisObject=(JsList)_this;
			if (para.value.size()<1)
				return new JsIntegral(thisObject.value.size());
			thisObject._push(para.value.get(0));

			return new JsIntegral(thisObject.value.size());
		}
		public String GetCanonicalName()
		{
			return "Js.List.push";
		}
	}

	protected static JsList_Pop _pop=new JsList_Pop();
	public static class JsList_Pop extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new JsList_Pop();
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			JsVar _this=closureInfo.Get("this");
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
	
	protected static JsList_UnShift _unshift=new JsList_UnShift();
	public static class JsList_UnShift extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new JsList_UnShift();
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			JsVar _this=closureInfo.Get("this");
			JsList para=(JsList)closureInfo.Get("arguments");
			if (!(_this instanceof JsList))
				throw new JsWrongThisofNativeFunction();
			JsList thisObject=(JsList)_this;
			if (para.value.size()<1)
				return new JsIntegral(thisObject.value.size());
			thisObject.value.add(0, para.value.get(0));
			
			return new JsIntegral(thisObject.value.size());
		}
		public String GetCanonicalName()
		{
			return "Js.List.unshift";
		}
	}

	protected static JsList_Shift _shift=new JsList_Shift();
	public static class JsList_Shift extends JsFunction.JsNativeFunction
	{
		public JsFunction GetDup()
		{
			return new JsList_Shift();
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			JsVar _this=closureInfo.Get("this");
			if (!(_this instanceof JsList))
				throw new JsWrongThisofNativeFunction();
			JsList thisObject=(JsList)_this;
			if (thisObject.value.isEmpty())
				return new JsUndefined();
			else
				return thisObject.value.remove(0);
		}
		public String GetCanonicalName()
		{
			return "Js.List.shift";
		}
	}
}
