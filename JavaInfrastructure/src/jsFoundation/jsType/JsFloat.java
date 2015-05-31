package jsFoundation.jsType;

public class JsFloat extends JsNumber
{
	private double value;
	public JsFloat(double val)
	{
		value=val;
	}
	
	public JsValue Clone() {
		return new JsFloat(value);
	}
	
	public String ToString() {
		return Double.toString(value);
	}
}
