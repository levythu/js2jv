package jsFoundation.jsType;

public class JsFloat extends JsNumber
{
	private double value;
	public double _getValue()
	{
		return value;
	}
	public JsFloat(double val)
	{
		value=val;
	}
	public double Evaluate() {
		return value;
	}
	
	public JsValue Clone() {
		return new JsFloat(value);
	}
	public JsString ToString() {
		return new JsString(Double.toString(value));
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		if (!(name instanceof JsFloat))
			return new JsBoolean(false);
		return new JsBoolean(((JsFloat)name).value==value);
	}
}
