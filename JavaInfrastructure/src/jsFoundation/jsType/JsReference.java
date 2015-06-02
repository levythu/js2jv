package jsFoundation.jsType;

public abstract class JsReference extends JsVar
{
	public JsVar Assign()
	{
		return this;
	}
	public JsBoolean EqualTo(JsVar name) throws Exception
	{
		return new JsBoolean(this==name);
	}
	public JsBoolean IdenticalTo(JsVar name) throws Exception
	{
		return new JsBoolean(this==name);
	}
}
