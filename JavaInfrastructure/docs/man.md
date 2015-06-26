#JavaInfrastructure Manual
By Levy
##函数实现
###overall
对于js的任何一个函数(匿名、不匿名)，都需要实现一个类，继承jsFoundation.jsType.JsFunction并实现如下几个函数：

- GetDup(): 返回自己的实例
- GetCanonicalName(): 以字符串形式返回一个全局唯一的名字作为该函数的标识
- ExecuteDetail(JsClosure closureInfo): 运行实例代码。其中closureInfo为该代码运行时的闭包变量。(下文细讲)

由于js所有代码都是在函数体里的(其实最外层也是有函数的，隐性默认main函数，如果这一点用现有文法很难处理可以考虑在原文法开始符号前再加个符号拓广之)，所以所有其他函数的“声明”(见下文)都在
主函数(或更子函数中)执行。

下文为例子，sin函数的实现。

	public static class MathSin extends JsFunction.JsFunction
	{		
		public JsFunction GetDup()
		{
			return new MathSin();
		}
		public String GetCanonicalName() 
		{
			return "Js.Prelude.Math.sin";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
		{
			JsList para=(JsList)closureInfo.Get("arguments");
			
			if (para.value.size()<1)
				return new JsFloat(Double.NaN);
			double res;
			try
			{
				res=MathConverter.ExtractValueInDouble(para.value.get(0));
			}
			catch (Throwable e)
			{
				return new JsFloat(Double.NaN);
			}
			
			return new JsFloat(Math.sin(res));
		}
	}

###arguments
需要注意的是，由于js函数调用灵活度超高，参数个数是不定的，故参数以arguments变量的形式在闭包里存着。在编译的时候需要显示在函数头加上显式声明参数的语句，如：

	function(p1,p2)...

在翻译后化为：
	
	closure.Set("p1",closure.Get("arguments").value.get(0));
	closure.Set("p2",closure.Get("arguments").value.get(1));

这样。并不用像sin的实现一样考虑arguments数组的长度，因为不包含时会返回undefined，与js默认行为相同。

###this pointer
另外，在closure里还预制了this变量可用于获取。其作用于js里完全一致，不用做任何额外处理。

###函数的声明
需要注意的一点是，由于闭包的存在，函数声明时是需要考虑其声明地点的，也就是，在哪个大括号里(如果在最外层就默认在main函数)。转换为java后，尽管跟函数实现有关的类另外定义在当前函数类外，但在代码上原函数声明地点的位置需要用closure.FunctionDeclare来给其初始化闭包。

举个例子，js代码如下时：

	var s=3;
	function func(a,b)
	{
	    ...
	}
	s=s+5;

如此翻译：

	class MAINer
	{
	    ...
	    ...
	    public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
	    {
	        ...    
	        closureInfo.Declare("s",new JsIntegral(3));
	        closureInfo.FunctionDeclare("func", new func());
	        closureInfo.Set("s",closureInfo.Get("s").Plus(new JsIntegral(5)));
	        ...
	    }
	}
	class func
	{
	    ...
	    ...
	    public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
	    {
	        ...    
	    }
	}

##闭包
这货好说，就是替代了作用域而作为变量管理器的东西。对于被编译代码可见的所有变量都要活在闭包里，(被编译代码不可见的如临时变量，辅助性常数变量等是可以直接定义成java变量的)。其主要函数有以下几个：

- foldClosure(original): 对应{，深入一层闭包
- unfoldClosure(original)： 对应}，解一层闭包(**需要注意的是，函数最外层的大括号所代表的闭包关系infrastructure会自动处理，只有在函数体中的大括号层次需要手动调用该两个函数**)
- Get(string): 在闭包中取得名为string的变量，这个会按新作用域override旧作用域的原则寻找应该找到的变量。如果找完都没找到，会返回一个undef。
- Set(string,jsVar): 在闭包中设置名为string的变量，如果找不到该变量就在最新闭包里自动声明一个。这一点也和js的行为完全一致，不用做任何判断，直接把赋值语句等价于这个就ok。
- Declare(string,jsVar)：和set类似，不过总在最新闭包里声明该变量。即使有也会被override。和js行为一致，直接把var x=t;这样的语句等价转换就好。
- FunctionDeclare(string,jsVar)：上文以说，不必赘述。注意因为一个函数只会被定义一次，所以对于一种JsFunction类这样的语句只应该被写一次。

##JsVar
高度抽象过的东西，表示所有类型的js变量。其运算符也是universal的，只是不少会有re。直接做从语法规则到java语句的一一映射不难。下为映射表，其中省略了从闭包Get变量的过程。

映射前js语句 | 映射后java
--|--
x(p,q); | x.execute(new JsList(p,q))； //这里的jslist参数有限的，理论上说还是需要事先push好一个jslist再传递。
x.a;或x["a"] | x.GetProperty(new JsString("a"));
x[1] | x.GetProperty(new JsIntegral(1));
x[variable] | x.GetProperty(variable);
x[?]= | x.SetProperty //具体调用方式同上三
!x | x.Exclaimation
x==y | x.EqualTo(y)
x===y | x.IdenticalTo(y)
x<y | x.LessThan(y)
x<=y | x.LessThan(y).Or(x.IdenticalTo(y))
x+y | x.Plus(y)
x-y | x.Minus(y)
x*y | x.Asterisk(y)
x/y | x.Slash(y)
x&&y| x.And(y) //这几个不是位运算！
x&#124;&#124;y|x.Or(y)
x^y | x.Xor(y)

差不多了，需要注意的只是x.a=y和x=y是需要使用不同代码（分别是JsVar.SetProperty和JsClosure.Set）的。我估计这个diverge应该文法是可以做的。
