package jsFoundation.jsType;

public abstract class JsValue extends JsVar
{
	public abstract JsValue Clone();
	public JsVar Assign()
	{
		return Clone();
	}
}
