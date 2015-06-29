package jsFoundation.jsType;

import jsFoundation.jsException.JsInvalidOperatorException;

public abstract class JsNumber extends JsValue
{
	public abstract double Evaluate();
	public abstract long EvaluateInt();
	
	public JsString TypeOf() 
	{
		return new JsString("number");
	}
	public JsBoolean ToBoolean() throws Exception
	{
		return new JsBoolean(this.Evaluate()!=0);
	}
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.EqualTo(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsBoolean(((JsIntegral)this)._getValue()==((JsIntegral)name)._getValue());
		return new JsBoolean(this.Evaluate()==((JsNumber)name).Evaluate());
	}
	public JsBoolean LessThan(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.GreaterThan(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsBoolean(((JsIntegral)this)._getValue()<((JsIntegral)name)._getValue());
		return new JsBoolean(this.Evaluate()<((JsNumber)name).Evaluate());
	}
	public JsBoolean GreaterThan(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.LessThan(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsBoolean(((JsIntegral)this)._getValue()>((JsIntegral)name)._getValue());
		return new JsBoolean(this.Evaluate()>((JsNumber)name).Evaluate());
	}
	public JsVar Plus(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			if (name instanceof JsString)
				return ((JsString)name).BePlus(this);
			else
				return name.Plus(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsIntegral(((JsIntegral)this)._getValue()+((JsIntegral)name)._getValue());
		return new JsFloat(this.Evaluate()+((JsNumber)name).Evaluate());
	}
	public JsVar Minus(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.BeMinus(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsIntegral(((JsIntegral)this)._getValue()-((JsIntegral)name)._getValue());
		return new JsFloat(this.Evaluate()-((JsNumber)name).Evaluate());
	}
	public JsVar BeMinus(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.Minus(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsIntegral(-(((JsIntegral)this)._getValue()-((JsIntegral)name)._getValue()));
		return new JsFloat(-(this.Evaluate()-((JsNumber)name).Evaluate()));
	}
	public JsVar Asterisk(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.Asterisk(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsIntegral(((JsIntegral)this)._getValue()*((JsIntegral)name)._getValue());
		return new JsFloat(this.Evaluate()*((JsNumber)name).Evaluate());
	}
	public JsVar Slash(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.BeSlash(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsIntegral(((JsIntegral)this)._getValue()/((JsIntegral)name)._getValue());
		return new JsFloat(this.Evaluate()/((JsNumber)name).Evaluate());
	}
	public JsVar BeSlash(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			return name.Slash(this);
		if ((this instanceof JsIntegral) && (name instanceof JsIntegral))
			return new JsIntegral(((JsIntegral)name)._getValue()/((JsIntegral)this)._getValue());
		return new JsFloat(((JsNumber)name).Evaluate()/this.Evaluate());
	}
	public JsIntegral Mod(JsVar name) throws Exception
	{
		if (!(name instanceof JsNumber))
			throw new JsInvalidOperatorException();
		return new JsIntegral(EvaluateInt()%((JsNumber)name).EvaluateInt());
	}
}
