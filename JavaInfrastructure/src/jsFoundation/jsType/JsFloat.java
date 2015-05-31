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
	
	public JsValue Clone() {
		return new JsFloat(value);
	}
	public JsString ToString() {
		return new JsString(Double.toString(value));
	}
}
