package jsFoundation.jsType;

import jsFoundation.JsClosure;
import jsFoundation.jsException.*;

public class JsString extends JsValue
{
	private String value;
	public String _getValue()
	{
		return value;
	}
	public JsString(String val)
	{
		value=val;
	}
	
	public JsValue Clone() 
	{
		return new JsString(value);
	}
	public JsString TypeOf() 
	{
		return new JsString("string");
	}
	public JsString ToString() 
	{
		return new JsString(value);
	}
	
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsValue))
			return new JsBoolean(false);
		if (name instanceof JsNumber)
		{
			try
			{
				double x=Double.parseDouble(value);
				if (name instanceof JsIntegral)
					return new JsBoolean(x==(double)((JsIntegral)name)._getValue());
				else if (name instanceof JsFloat)
					return new JsBoolean(x==((JsFloat)name)._getValue());
				else throw new LogicBomb();
			}
			catch (NumberFormatException e)
			{}
		}
		return new JsBoolean(value.equals(name.ToString()._getValue()));
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsString))
			return new JsBoolean(false);
		return new JsBoolean(((JsString)name).value.equals(value));
	}
	public JsBoolean LessThan(JsVar name) throws Exception
	{
		if (!(name instanceof JsValue))
			throw new JsInvalidOperatorException();
		if (name instanceof JsNumber)
		{
			try
			{
				double x=Double.parseDouble(value);
				if (name instanceof JsIntegral)
					return new JsBoolean(x<(double)((JsIntegral)name)._getValue());
				else if (name instanceof JsFloat)
					return new JsBoolean(x<((JsFloat)name)._getValue());
				else throw new LogicBomb();
			}
			catch (NumberFormatException e)
			{}
		}
		return new JsBoolean(value.compareTo(name.ToString()._getValue())<0);
	}
	public JsBoolean GreaterThan(JsVar name) throws Exception
	{
		if (!(name instanceof JsValue))
			throw new JsInvalidOperatorException();
		if (name instanceof JsNumber)
		{
			try
			{
				double x=Double.parseDouble(value);
				if (name instanceof JsIntegral)
					return new JsBoolean(x>(double)((JsIntegral)name)._getValue());
				else if (name instanceof JsFloat)
					return new JsBoolean(x>((JsFloat)name)._getValue());
				else throw new LogicBomb();
			}
			catch (NumberFormatException e)
			{}
		}
		return new JsBoolean(value.compareTo(name.ToString()._getValue())>0);
	}
	public JsVar Plus(JsVar name) throws Exception
	{
		if (!(name instanceof JsValue))
			throw new JsInvalidOperatorException();
		return new JsString(value+name.ToString()._getValue());
	}
	public JsVar BePlus(JsVar name) throws Exception
	{
		if (!(name instanceof JsValue))
			throw new JsInvalidOperatorException();
		return new JsString(name.ToString()._getValue()+value);
	}
	
	public JsVar GetProperty(String name) throws Exception
	{
		if (name.equals("length"))
		{
			return new JsIntegral(value.length());
		}
		else if (name.equals("substr"))
		{
			return _substr;
		}
		else
			return new JsUndefined();
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
			if (id>=0 && id<value.length())
				return new JsString(value.substring(id, id+1));
			else
				return new JsUndefined();
		}
		return GetProperty(name.ToString()._getValue());
	}
	
	//==============================================================
	//==============================================================
	protected static JsString_Substr _substr=new JsString_Substr();
	public static class JsString_Substr extends JsFunction
	{
		public JsVar ExecuteDetail(JsVar _this, JsList para, JsClosure closureInfo) throws Exception 
		{
			if (!(_this instanceof JsString))
				throw new JsWrongThisofNativeFunction();
			int begg,len;
			JsString belStr=(JsString)_this;
			if (para.value.size()<1)
				return new JsUndefined();
			if (para.value.size()==1)
			{
				JsVar p1=para.value.get(0);
				if (!(p1 instanceof JsIntegral))
					throw new JsInvalidParameter();
				begg=(int)((JsIntegral)p1)._getValue();
				len=belStr.value.length()-begg;
			}
			else
			{
				JsVar p1=para.value.get(0);
				JsVar p2=para.value.get(1);
				if (!(p1 instanceof JsIntegral))
					throw new JsInvalidParameter();
				if (!(p2 instanceof JsIntegral))
					throw new JsInvalidParameter();
				begg=(int)((JsIntegral)p1)._getValue();
				len=(int)((JsIntegral)p2)._getValue();
				if (len>belStr.value.length()-begg)
					len=belStr.value.length()-begg;
			}
			if (begg>=belStr.value.length())
				return new JsString("");
			return new JsString(belStr.value.substring(begg, begg+len));
		}
		public String GetCanonicalName() 
		{
			return "Js.String.substr";
		}
	}	
}
