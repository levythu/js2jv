grammar JavaScript;

options
{
	output=AST;
	backtrack=true;
	memoize=true;
}

@parser::header{
	import java.lang.*;
}

@parser::members{
	public int varCount = 0;

	public String GenerateName()
	{
		varCount++;
		return "v" + Integer.toString(varCount);
	}

	public void OutputTab(int lb)
	{
		for (int k = 0; k < lb; k++)
			System.out.print("\t");
	}

	public void OutputFormattedTargetCode(String str)
	{
		int lb = 0;
		int br = 0;
		for (int i = 0; i < str.length(); i++)
		{
			char c0 = (i > 0) ? str.charAt(i - 1) : ' ';
			char c = str.charAt(i);
			char c1 = (i < str.length() - 1) ? str.charAt(i + 1) : ' ';
			if (c == '(' && !(c0 == '\'' && c1 == '\'')) {
				br++;
			} 
			if (c == ')' && !(c0 == '\'' && c1 == '\'')) {
				br--;
			}
			if (c == '{')
			{
				System.out.println();
				OutputTab(lb);
				System.out.println(c);
				OutputTab(++lb);
			}
			else if (c == '}')
			{
				System.out.println();
				OutputTab(--lb);
				System.out.println(c);
				OutputTab(lb);
			}
			else if (c == ';' && br == 0)
			{
				if (i < str.length() - 1 && str.charAt(i + 1) != '}' && str.charAt(i + 1) != '{')
				{
					System.out.println(c);
					OutputTab(lb);
				}
				else
				{
					System.out.print(c);
				}
			}
			else 
			{
				System.out.print(c);
			}
		}
	}
}

program 
	: LT!* s=sourceElements LT!* EOF!
	{
		String code = "";
		code += "import jsFoundation.JsClosure;import jsFoundation.JsRuntime;import jsFoundation.jsType.*;" +
				"public class Javascript extends JsFunction {" + 
				"public JsFunction GetDup() {return new Javascript();}" + 
				"public String GetCanonicalName() {return \"test.Javascript.main\";}" +
				"public static void main(String[] args) throws Throwable{" + 
				"JsRuntime mn = new JsRuntime(new Javascript());mn.Run();" + "}" +
				"public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception {" + 
				$s.stat + (($s.returned==0)?"return new JsUndefined();":"") +"}" + $s.func + "}" ;
		OutputFormattedTargetCode(code);
	}
	;
	
sourceElements returns [String stat, String func, int returned]
@init{
	$stat=""; $func=""; $returned=0;
}
	: a=sourceElement 
	{
		$stat += $a.stat;
		$func += $a.func;
		$returned = $a.returned;
	} 
	  (LT!* b=sourceElement 
	{
		$stat += $b.stat;
		$func += $b.func;
		$returned = $b.returned;
	}
	  )*
	;
	
sourceElement returns [String stat, String func, int returned]
@init{
	$stat=""; $func=""; $returned=0;
}
	: f=functionDeclaration 
	{
		$func = $f.code;
		$stat = "closureInfo.FunctionDeclare(\"" + $f.name + "\", new " + $f.name + "());";
	}
	| statement 
	{
		$stat = $statement.code;
		$func = $statement.func;
		$returned = $statement.returned;
	}
	;
	

functionDeclaration returns [String code, String name]
@init{
	$code = ""; $name = "";
}
	: 'function' LT!* Identifier LT!* formalParameterList LT!* functionBody
	{	
		$name = $Identifier.text;
		$code = "public static class " + $Identifier.text + " extends JsFunction" +
				"{" +
				"public JsFunction GetDup()" + "{return new " + $Identifier.text + "();}" +
				"public String GetCanonicalName()" +  "{return \"Js.Preclude." + $Identifier.text + "\";}" +
				"public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception" + "{" + $formalParameterList.code + $functionBody.stat + (($functionBody.returned==0)?"return new JsUndefined();":"") +"}" + $functionBody.func + "}"
				;
	}
	;
	
functionExpression returns [String code, String name]
@init{
	$code = ""; $name = "";
}
	: 'function' LT!* i=Identifier? LT!* formalParameterList LT!* functionBody
	{
		if (i != null) {
			$name = $i.text;
		} else {
			$name = GenerateName();
		}

		$code = "public static class " + $name + " extends JsFunction" +
				"{" +
				"public JsFunction GetDup()" + "{return new " + $name + "();}" +
				"public String GetCanonicalName()" +  "{return \"Js.Preclude." + $name + "\";}" +
				"public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception" + "{" + $formalParameterList.code + $functionBody.stat + (($functionBody.returned==0)?"return new JsUndefined();":"") +"}" + $functionBody.func + "}"
				;
	}
	;
	
formalParameterList returns [String code]
@init{
	$code = "";
	int cnt = 0;
}
	: '(' (LT!* a=Identifier
	{
		$code = "closureInfo.Set(\"" + $a.text + "\", closureInfo.Get(\"arguments\").GetProperty(new JsIntegral(" + Integer.toString(cnt) + ")));";
		cnt++;
	}
	  (LT!* ',' LT!* b=Identifier 
	{
	  	$code += "closureInfo.Set(\"" + $b.text + "\", closureInfo.Get(\"arguments\").GetProperty(new JsIntegral(" + Integer.toString(cnt) + ")));";
		cnt++;
	}
	  )*)? LT!* ')'
	;

functionBody returns [String stat, String func, int returned]
@init
{
	$stat = ""; $func = ""; $returned=0;
}
	: '{' LT!* sourceElements LT!* '}'
	{
		$stat = $sourceElements.stat;
		$func = $sourceElements.func;
		$returned = $sourceElements.returned;
	}
	;


statement returns [String code, String func, int returned]
@init{
	$code = ""; $func = ""; $returned=0;
}
	: statementBlock 
	{
		$code = $statementBlock.code;
		$func = $statementBlock.func;
	}
	| variableStatement 
	{	
		$code = $variableStatement.code;
		$func = $variableStatement.func;
	}
	| emptyStatement
	| expressionStatement 
	{
		$code = $expressionStatement.code;
		$func = $expressionStatement.func;
	}
	| ifStatement 
	{
		$code = $ifStatement.code;
		$func = $ifStatement.func;
	}
	| iterationStatement 
	{
		$code = $iterationStatement.code;
		$func = $iterationStatement.func;
	}
	| continueStatement 
	{
		$code = $continueStatement.code;
	}
	| breakStatement 
	{
		$code = $breakStatement.code;
	}
	| returnStatement 
	{
		$code = $returnStatement.code;
		$returned = 1;
	}
	;
	
statementBlock returns [String code, String func]
@init{
	$code = ""; $func = "";
}
	: '{' LT!* statementList LT!* '}' 
	{
		$code = "{" + $statementList.code + "}";
		$func = $statementList.func;
	}
	| '{' LT!* '}' 
	{
		$code = "{}";
	}
	;
	
statementList returns [String code, String func]
@init{
	$code = ""; $func = "";
}
	: a=statement 
	{
		$code = $a.code;
		$func = $a.func;
	} 
	  (LT!* b=statement 
	{
		$code += $b.code;
		$func += $b.func;
	} 
	  )*
	;
	
variableStatement returns [String code, String func]
@init{
	$code = ""; $func = "";
}
	: 'var' LT!* variableDeclarationList (LT | ';')! 
	{
		$code = $variableDeclarationList.code;
		$func = $variableDeclarationList.func;
	}
	;
	
variableDeclarationList returns [String code, String func]
@init
{
	$code = ""; $func = "";
}
	: a=variableDeclaration 
	{
		$code = $a.code;
		$func = $a.func;
	} 
	  (LT!* ',' LT!* b=variableDeclaration 
	{
		$code += $b.code;
		$func += $b.func;
	}
	  )*
	;
	
variableDeclaration returns [String code, String func]
@init {
	$code = ""; $func = "";
}
	: Identifier
	| Identifier LT!* a=initialiser
	{
		$code = $a.code;
		$code += "closureInfo.Declare(\"" + $Identifier.text + "\", closureInfo.Get(\"" + $a.name + "\"));";
		$func = $a.func;
	}
	;

	
initialiser returns [String code, String name, String func]
@init {
	$code = ""; $name = ""; $func = "";
}
	: '=' LT!* a=assignmentExpression
	{
		$code = $a.code;
		$name = $a.name;
		$func = $a.func;
	}
	;
	
emptyStatement
	: ';'
	;
	
expressionStatement returns [String code, String func]
	: expression (LT | ';')! 
	{
		$code = $expression.code;
		$func = $expression.func;
	}
	;
	
ifStatement returns [String code, String func]
@init{
	$code = ""; $func = "";
}
	: 'if' LT!* '(' LT!* a=expression LT!* ')' LT!* b=statement (LT!* 'else' LT!* c=statement)?
	{
		$code += $a.code;
		$func = $a.func + $b.func + $c.func;
		String s = $b.code;
		if (s.charAt(0) != '{') s = "{" + "closureInfo=JsClosure.foldClosure(closureInfo);{" + s + "}closureInfo=JsClosure.unfoldClosure(closureInfo);" + "}";
		$code += "if (closureInfo.Get(\"" + $a.name + "\").ToBoolean()._getValue() == true) " + s;
		if (c != null) {
			s = $c.code;
			if (s.charAt(0) != '{') s = "{" + "closureInfo=JsClosure.foldClosure(closureInfo);{" + s + "}closureInfo=JsClosure.unfoldClosure(closureInfo);" + "}";
			$code += "else" + s;
		}
	}
	;
	
iterationStatement returns [String code, String func]
	: doWhileStatement 
	{
		$code = $doWhileStatement.code;
		$func = $doWhileStatement.func;
	}
	| whileStatement 
	{
		$code = $whileStatement.code;
		$func = $whileStatement.func;
	}
	| forStatement 
	{	
		$code = $forStatement.code;
		$func = $forStatement.func;
	}
	;
	
doWhileStatement returns [String code, String func]
@init{
	$code=""; $func="";
}
	: 'do' LT!* statement LT!* 'while' LT!* '(' expression ')' (LT | ';')!
	{
		$func = $statement.func + $expression.func;
		$code += "closureInfo=JsClosure.foldClosure(closureInfo);{";
		$code += $expression.code;
		$code += "do" + $statement.code + "while" + "(" + "closureInfo.Get(\"" + $expression.name + "\").ToBoolean()._getValue()==true" + ");";
		$code += "}closureInfo=JsClosure.unfoldClosure(closureInfo);";
	}
	;
	
whileStatement returns [String code, String func]
@init{
	$code=""; $func="";
}
	: 'while' LT!* '(' LT!* expression LT!* ')' LT!* statement
	{
		$func = $expression.func + $statement.func;
		$code += "closureInfo=JsClosure.foldClosure(closureInfo);{";
		$code += $expression.code;
		$code += "while(" + "closureInfo.Get(\"" + $expression.name + "\").ToBoolean()._getValue() == true" + ")" + $statement.code;
		$code += "}closureInfo=JsClosure.unfoldClosure(closureInfo);";
	}
	;
	
forStatement returns [String code, String func]
@init {
	$code = ""; $func = "";
}
	: 'for' LT!* '(' (LT!* a=forStatementInitialiserPart)? LT!* ';' (LT!* b=expression)? LT!* ';' (LT!* c=expression)? LT!* ')' LT!* d=statement
	{
		$func = $d.func;

		$code += "closureInfo=JsClosure.foldClosure(closureInfo);{";
		
		if (a != null) {
			$code += $a.code;
		}

		if (b != null) {
			$code += $b.code;
		}

		String s = $d.code;
		if (s.charAt(0) == '{') s = s.substring(1, s.length() - 1);
		if (c != null) s += $c.code;
		if (b != null) s += $b.code;
		s = "{" + s + "}";

		$code += "for (;" + "closureInfo.Get(\"" + $b.name + "\").ToBoolean()._getValue() == true" + ";)" + s;
		
		$code += "}closureInfo=JsClosure.unfoldClosure(closureInfo);";
	}
	;
	
forStatementInitialiserPart returns [String code]
	: a=expression 
	{
		$code = $a.code;
	}
	| 'var' LT!* b=variableDeclarationList
	{
		$code = $b.code;
	}
	;

continueStatement returns [String code]
	: 'continue' Identifier? (LT | ';')! 
	{
		$code = "continue";
	}
	;

breakStatement returns [String code]
	: 'break' Identifier? (LT | ';')! 
	{
		$code = "break";
	}
	;

returnStatement returns [String code]
@init{
	$code="";
}
	: 'return' expression? 
	{
		$code += $expression.code;
		$code += "return closureInfo.Get(\"" + $expression.name + "\");";
	} 
	  (LT | ';')! 
	;




expression returns [String code, String name, String membername, String func]
@init {
	$code=""; $name=""; $membername=""; $func="";
}
	: a=assignmentExpression 
	{
		$code = $a.code;
		$name = $a.name; 
		$membername = $a.membername;
		$func = $a.func;
	} 
	  (LT!* ',' LT!* b=assignmentExpression 
	{
		$code += "," + $b.code; 
		$name = $b.name; 
		$membername = $b.membername;
		$func += $b.func;
	}
	  )*
	;


assignmentExpression returns [String code, String name, String membername, String func]
@init{
	$code = ""; $name = ""; $membername = ""; $func="";
}
	: d=conditionalExpression
	{
		$code = $d.code;
		$name = $d.name;
		$membername = $d.membername;
		$func = $d.func;

		if ($membername != "")
		{	
			String na = GenerateName();
			$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")));";
			$name = na;
			$membername = "";
		}
	}	
	| a=leftHandSideExpression LT!* b=assignmentOperator LT!* c=assignmentExpression
	{
		$code = $c.code;
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func + $c.func;
		
		if ($membername == "")
		{
			if ($b.type == 0)
				$code += "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $c.name + "\")" + ");";
			if ($b.type == 1)
				$code += "closureInfo.Set(\"" + $name + "\", " + "closureInfo.Get(\"" + $name + "\").Asterisk(" + "closureInfo.Get(\"" + $c.name + "\")" + "));";
			if ($b.type == 2)
				$code += "closureInfo.Set(\"" + $name + "\", " + "closureInfo.Get(\"" + $name + "\").Slash(" + "closureInfo.Get(\"" + $c.name + "\")" + "));";
			if ($b.type == 3)
				$code += "closureInfo.Set(\"" + $name + "\", " + "closureInfo.Get(\"" + $name + "\").Plus(" + "closureInfo.Get(\"" + $c.name + "\")" + "));";
			if ($b.type == 4)
				$code += "closureInfo.Set(\"" + $name + "\", " + "closureInfo.Get(\"" + $name + "\").Minus(" + "closureInfo.Get(\"" + $c.name + "\")" + "));";
		}
		else
		{
			if ($b.type == 0)
				$code += "closureInfo.Get(\"" + $name + "\").SetProperty(" + "new JsString(\"" + $membername + "\"), closureInfo.Get(\"" + $c.name + "\")" + ");";
			if ($b.type == 1) {
				String na = GenerateName();
				$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")).Asterisk(closureInfo.Get(\"" + $c.name + "\")));";
				$code += "closureInfo.Get(\"" + $name + "\").SetProperty(new JsString(\"" + $membername + "\")," + na + ");";
			}
			if ($b.type == 2) {
				String na = GenerateName();
				$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")).Slash(closureInfo.Get(\"" + $c.name + "\")));";
				$code += "closureInfo.Get(\"" + $name + "\").SetProperty(new JsString(\"" + $membername + "\")," + na + ");";
			}
			if ($b.type == 3) {
				String na = GenerateName();
				$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")).Plus(closureInfo.Get(\"" + $c.name + "\")));";
				$code += "closureInfo.Get(\"" + $name + "\").SetProperty(new JsString(\"" + $membername + "\")," + na + ");";
			}
			if ($b.type == 4) {
				String na = GenerateName();
				$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")).Plus(closureInfo.Get(\"" + $c.name + "\")));";
				$code += "closureInfo.Get(\"" + $name + "\").SetProperty(new JsString(\"" + $membername + "\")," + na + ");";
			}
		}

		if ($membername != "")
		{	
			String na = GenerateName();
			$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")));";
			$name = na;
			$membername = "";
		}

	}
	;



leftHandSideExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=callExpression 
	{
		$code = $a.code;
		$name = $a.name; 
		$membername = $a.membername;
		$func = $a.func;
	}
	| b=memberExpression 
	{
		$code = $b.code;
		$name = $b.name; 
		$membername = $b.membername;
		$func = $b.func;
	}
	;

	
memberExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: (a=primaryExpression | f=functionExpression)
	{
		if (a != null) {
			$code = $a.code;
			$name = $a.name;
			$membername = $a.membername;
			$func = $a.func;
		} else if (f != null) {
			$code = "closureInfo.FunctionDeclare(\"" + $f.name + "\", new " + $f.name + "());";
			$name = $f.name;
			$func = $f.code;
		}
	} 
	  (LT!* b=memberExpressionSuffix
	{
		String na = GenerateName();
		if ($membername != "") {
			$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")));";
		} else {
			$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\"));";
		}
		$name = na;
		$membername = $b.membername;
	}
	  )*
	;


memberExpressionSuffix returns [String membername]
	: a=indexSuffix 
	{
		$membername = $a.membername;
	}
	| b=propertyReferenceSuffix 
	{
		$membername = $b.membername;
	}
	;


callExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=memberExpression
	{
		$code = $a.code;
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	  LT!* b=arguments
	{
		$code += $b.code;
		$func += $b.func;
		String na = GenerateName();
		if ($membername != "") {
			$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")).Execute(" + $b.name + "));";
		} else {
			$code += "closureInfo.Set(\"" + na + "\"," + "closureInfo.Get(\"" + $name + "\").Execute(" + $b.name + "));";
		}	
		$name = na;
		$membername = "";
	}
	;


arguments returns [String code, String name, String func]
@init{
	$name = GenerateName();
	$code = "JsList " + $name + " = " + "new JsList();";
	$func = "";
}
	: '(' (LT!* a=assignmentExpression
	{
		$code += $a.code + $name + "._push(" + "closureInfo.Get(\"" + $a.name + "\")" + ");";
		$func = $a.func;
	}
	  (LT!* ',' LT!* b=assignmentExpression
	{
		$code += $b.code + $name + "._push(" + "closureInfo.Get(\"" + $b.name + "\")" + ");";
		$func += $b.func;
	}
	  )*)? LT!* ')'
	;

	
indexSuffix returns [String membername]
	: '[' LT!* a=assignmentExpression LT!* ']' 
	{
		$membername = $a.name;
	}
	;	
	

propertyReferenceSuffix returns [String membername]
	: '.' LT!* Identifier 
	{
		$membername = $Identifier.text;
	}
	;

	
assignmentOperator returns [int type]
	: '=' {$type = 0;}
	| '*=' {$type = 1;}
	| '/=' {$type = 2;}
	| '+=' {$type = 3;}
	| '-=' {$type = 4;}
	;





conditionalExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=logicalORExpression
	{
		$code = $a.code;
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	| d=logicalORExpression LT!* '?' LT!* b=assignmentExpression LT!* ':' LT!* c=assignmentExpression
	{
		$name = GenerateName();
		$membername = "";
		$code = $d.code + $b.code + $c.code + 
				"if (closureInfo.Get(\"" + $d.name + "\")._getValue() == true)" + 
				"{" + "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $b.name + "\"))" + "}" + 
				"else {" + "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $c.name + "\"))" + "}";
		$func = $d.func + $c.func;
	}
	;


logicalORExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=logicalANDExpression 
	{
		$code = $a.code; 
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	  (LT!* '||' LT!* b=logicalANDExpression 
	{
		$code += $b.code;
		$func += $b.func;
		String na = GenerateName();
		$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").Or(closureInfo.Get(\"" + $b.name + "\")));";
		$name = na;
	}
	  )*
	;

	
logicalANDExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=equalityExpression
	{
		$code = $a.code; 
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	  (LT!* '&&' LT!* b=equalityExpression
	{
		$code += $b.code;
		$func += $b.func;
		String na = GenerateName();
		$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").And(closureInfo.Get(\"" + $b.name + "\")));";
		$name = na;
	}
	  )*
	;

	
equalityExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=relationalExpression
	{
		$code = $a.code; 
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	  (LT!* op=equalityOperator LT!* b=relationalExpression
	{
		$code += $b.code;
		$func += $b.func;
		String na = GenerateName();
		if ($op.type == 0) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").EqualTo(closureInfo.Get(\"" + $b.name + "\")));";
		} else if ($op.type == 1) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").EqualTo(closureInfo.Get(\"" + $b.name + "\")));";
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + na + "\").Exclaimation());";
		} else if ($op.type == 2) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").IdenticalTo(closureInfo.Get(\"" + $b.name + "\")));";
		} else if ($op.type == 3) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").IdenticalTo(closureInfo.Get(\"" + $b.name + "\")));";
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + na + "\").Exclaimation());";
		}
		$name = na;
	}
	  )*
	;

equalityOperator returns [int type]
	: '==' {$type = 0;}
	| '!=' {$type = 1;}
	| '===' {$type = 2;}
	| '!==' {$type = 3;}
	;
	
relationalExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=additiveExpression 
	{
		$code = $a.code; 
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	  (LT!* op=relationalOperator  LT!* b=additiveExpression
	{
		$code += $b.code;
		$func += $b.func;
		String na = GenerateName();
		if ($op.type == 0) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").LessThan(closureInfo.Get(\"" + $b.name + "\")));";
		} else if ($op.type == 1) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").LessThan(closureInfo.Get(\"" + $b.name + "\")));";
			$code += "closureInfo.Set(\"" + "temp" + "\" ,closureInfo.Get(\"" + $name + "\").Identical(closureInfo.Get(\"" + $b.name + "\")));";
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + na + "\").Or(closureInfo.Get(\"" + "temp" + "\")));";
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + na + "\").Exclaimation());";
		} else if ($op.type == 2) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").LessThan(closureInfo.Get(\"" + $b.name + "\")));";
			$code += "closureInfo.Set(\"" + "temp" + "\" ,closureInfo.Get(\"" + $name + "\").Identical(closureInfo.Get(\"" + $b.name + "\")));";
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + na + "\").Or(closureInfo.Get(\"" + "temp" + "\")));";
		} else if ($op.type == 3) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").LessThan(closureInfo.Get(\"" + $b.name + "\")));";
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + na + "\").Exclaimation());";
		}
		$name = na;
	}
	  )*
	;


relationalOperator returns [int type]
	: '<' {$type = 0;}
	| '>' {$type = 1;}
	| '<=' {$type = 2;}
	| '>=' {$type = 3;}
	;

additiveExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=multiplicativeExpression 
	{
		$code = $a.code; 
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	  (LT!* op=additiveOperator LT!* b=multiplicativeExpression
	{
		$code += $b.code;
		$func += $b.func;
		String na = GenerateName();
		if ($op.type == 0) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").Plus(closureInfo.Get(\"" + $b.name + "\")));";			
		} if ($op.type == 1) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").Minus(closureInfo.Get(\"" + $b.name + "\")));";
		}
		$name = na;
	}
	  )*
	;

additiveOperator returns [int type]
	: '+' {$type = 0;}
	| '-' {$type = 1;}
	;

multiplicativeExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=unaryExpression 
	{
		$code = $a.code; 
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	  (LT!* op=multiplicativeOperator LT!* b=unaryExpression
	{
		$code += $b.code;
		$func += $b.func;
		String na = GenerateName();
		if ($op.type == 0) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").Asterisk(closureInfo.Get(\"" + $b.name + "\")));";						
		} if ($op.type == 1) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").Slash(closureInfo.Get(\"" + $b.name + "\")));";						
		} if ($op.type == 2) {
			$code += "closureInfo.Set(\"" + na + "\" ,closureInfo.Get(\"" + $name + "\").Mod(closureInfo.Get(\"" + $b.name + "\")));";						
		}
		$name = na;
	}
	  )*
	;

multiplicativeOperator returns [int type]
	: '*' {$type = 0;}
	| '/' {$type = 1;}
	| '%' {$type = 2;}
	;

unaryExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=postfixExpression 
	{
		$code = $a.code; 
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	| '++' b=unaryExpression
	| '--' c=unaryExpression
	| '+' d=unaryExpression
	| '-' e=unaryExpression
	{
		$code = $e.code;
		$name = $e.name;
		$membername = $e.membername;
		$func = $e.func;
		$code += "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $name + "\").Asterisk(new JsIntegral(-1)));";
	}
	| '!' f=unaryExpression
	;

postfixExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: a=leftHandSideExpression b=postfixOperator?
	{
		if (b != null && $b.type == 0)
		{
			$code = $a.code;
			$name = $a.name;
			$membername = $a.membername;
			$func = $a.func;
			if ($membername != "") {
				$code += "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")).Plus(new JsIntegral(1)));";
			} else {
				$code += "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $name + "\").Plus(new JsIntegral(1)));";
			}
		}
		else if (b != null && $b.type == 1)
		{
			$code = $a.code;
			$name = $a.name;
			$membername = $a.membername;
			$func = $a.func;
			if ($membername != "") {
				$code += "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $name + "\").GetProperty(new JsString(\"" + $membername + "\")).Minus(new JsIntegral(1)));";
			} else {
				$code += "closureInfo.Set(\"" + $name + "\", closureInfo.Get(\"" + $name + "\").Minus(new JsIntegral(1)));";
			}
		}
		else 
		{
			$code = $a.code; 
			$name = $a.name; 
			$membername = $a.membername;
			$func = $a.func;
		}
	}
	;

postfixOperator returns [int type]
	: '++' {$type = 0;}
	| '--' {$type = 1;}
	;


primaryExpression returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
}
	: Identifier
	{
		$name = $Identifier.text;
	}
	| literal 
	{
		$name = GenerateName();
		if ($literal.type == "number") {
			$code += "closureInfo.Declare(\"" + $name + "\", JsNumber.initLiteral(" + $literal.value + "));";
		} else if ($literal.type == "string") {
			$code += "closureInfo.Declare(\"" + $name + "\", new JsString(" + $literal.value + "));";
		} else if ($literal.type == "boolean") {
			$code += "closureInfo.Declare(\"" + $name + "\", new JsBoolean(" + $literal.value + "));";
		} else if ($literal.type == "null") {
			$code += "closureInfo.Declare(\"" + $name + "\", new JsUndefined());";
		}
	}
	| '(' LT!* a=assignmentExpression LT!* ')'
	{
		$code = $a.code;
		$name = $a.name;
		$membername = $a.membername;
		$func = $a.func;
	}
	| d=arrayLiteral
	{
		$code = $d.code;
		$func = $d.func;
		$name = $d.name;
	}
	;

arrayLiteral returns [String code, String name, String membername, String func]
@init{
	$code=""; $name=""; $membername=""; $func="";
	$name = GenerateName();
	$code = "closureInfo.Declare(\"" + $name + "\", new JsList());";
}
	: '[' LT!* a=assignmentExpression? 
	{
		if (a != null)
		{
			$code += $a.code;
			$func += $a.func;
			$code += $name + "._push(closureInfo.Get(\"" + $a.name + "\")" + ");";
		}
	}
	  (LT!* ',' (LT!* b=assignmentExpression
	{
		if (b != null)
		{
			$code += $b.code;
			$func += $b.func;
			$code += $name + "._push(closureInfo.Get(\"" + $b.name + "\")" + ");";
		}
	}
	  )?)* LT!* ']'
	;


literal returns [String value, String type]
@init{
	$value=""; $type="";
}
	: 'null' 
	{
		$value = "null";
		$type = "null";
	}
	| 'true'
	{
		$value = "true";
		$type = "boolean"; 
	}
	| 'false' 
	{
		$value = "false";
		$type = "boolean";
	}
	| StringLiteral
	{
		$value = $StringLiteral.text;
		$type = "string";
	}
	| NumericLiteral
	{
		$value = $NumericLiteral.text;
		$type = "number";
	}
	;








// lexer rules.
StringLiteral
	: '"' DoubleStringCharacter* '"'
	| '\'' SingleStringCharacter* '\''
	;
	
fragment DoubleStringCharacter
	: ~('"' | '\\' | LT)	
	| '\\' EscapeSequence
	;

fragment SingleStringCharacter
	: ~('\'' | '\\' | LT)	
	| '\\' EscapeSequence
	;

fragment EscapeSequence
	: CharacterEscapeSequence
	| '0'
	| HexEscapeSequence
	| UnicodeEscapeSequence
	;
	
fragment CharacterEscapeSequence
	: SingleEscapeCharacter
	| NonEscapeCharacter
	;

fragment NonEscapeCharacter
	: ~(EscapeCharacter | LT)
	;

fragment SingleEscapeCharacter
	: '\'' | '"' | '\\' | 'b' | 'f' | 'n' | 'r' | 't' | 'v'
	;

fragment EscapeCharacter
	: SingleEscapeCharacter
	| DecimalDigit
	| 'x'
	| 'u'
	;
	
fragment HexEscapeSequence
	: 'x' HexDigit HexDigit
	;
	
fragment UnicodeEscapeSequence
	: 'u' HexDigit HexDigit HexDigit HexDigit
	;
	
NumericLiteral
	: DecimalLiteral
	| HexIntegerLiteral
	;
	
fragment HexIntegerLiteral
	: '0' ('x' | 'X') HexDigit+
	;
	
fragment HexDigit
	: DecimalDigit | ('a'..'f') | ('A'..'F')
	;
	
fragment DecimalLiteral
	: DecimalDigit+ '.' DecimalDigit* ExponentPart?
	| '.'? DecimalDigit+ ExponentPart?
	;
	
fragment DecimalDigit
	: ('0'..'9')
	;

fragment ExponentPart
	: ('e' | 'E') ('+' | '-') ? DecimalDigit+
	;

Identifier
	: IdentifierStart IdentifierPart*
	;
	
fragment IdentifierStart
	: UnicodeLetter
	| '$'
	| '_'
        | '\\' UnicodeEscapeSequence
        ;
        
fragment IdentifierPart
	: (IdentifierStart) => IdentifierStart // Avoids ambiguity, as some IdentifierStart chars also match following alternatives.
	| UnicodeDigit
	| UnicodeConnectorPunctuation
	;
	
fragment UnicodeLetter		// Any character in the Unicode categories "Uppercase letter (Lu)", 
	: '\u0041'..'\u005A'	// "Lowercase letter (Ll)", "Titlecase letter (Lt)",
	| '\u0061'..'\u007A'	// "Modifier letter (Lm)", "Other letter (Lo)", or "Letter number (Nl)".
	| '\u00AA'
	| '\u00B5'
	| '\u00BA'
	| '\u00C0'..'\u00D6'
	| '\u00D8'..'\u00F6'
	| '\u00F8'..'\u021F'
	| '\u0222'..'\u0233'
	| '\u0250'..'\u02AD'
	| '\u02B0'..'\u02B8'
	| '\u02BB'..'\u02C1'
	| '\u02D0'..'\u02D1'
	| '\u02E0'..'\u02E4'
	| '\u02EE'
	| '\u037A'
	| '\u0386'
	| '\u0388'..'\u038A'
	| '\u038C'
	| '\u038E'..'\u03A1'
	| '\u03A3'..'\u03CE'
	| '\u03D0'..'\u03D7'
	| '\u03DA'..'\u03F3'
	| '\u0400'..'\u0481'
	| '\u048C'..'\u04C4'
	| '\u04C7'..'\u04C8'
	| '\u04CB'..'\u04CC'
	| '\u04D0'..'\u04F5'
	| '\u04F8'..'\u04F9'
	| '\u0531'..'\u0556'
	| '\u0559'
	| '\u0561'..'\u0587'
	| '\u05D0'..'\u05EA'
	| '\u05F0'..'\u05F2'
	| '\u0621'..'\u063A'
	| '\u0640'..'\u064A'
	| '\u0671'..'\u06D3'
	| '\u06D5'
	| '\u06E5'..'\u06E6'
	| '\u06FA'..'\u06FC'
	| '\u0710'
	| '\u0712'..'\u072C'
	| '\u0780'..'\u07A5'
	| '\u0905'..'\u0939'
	| '\u093D'
	| '\u0950'
	| '\u0958'..'\u0961'
	| '\u0985'..'\u098C'
	| '\u098F'..'\u0990'
	| '\u0993'..'\u09A8'
	| '\u09AA'..'\u09B0'
	| '\u09B2'
	| '\u09B6'..'\u09B9'
	| '\u09DC'..'\u09DD'
	| '\u09DF'..'\u09E1'
	| '\u09F0'..'\u09F1'
	| '\u0A05'..'\u0A0A'
	| '\u0A0F'..'\u0A10'
	| '\u0A13'..'\u0A28'
	| '\u0A2A'..'\u0A30'
	| '\u0A32'..'\u0A33'
	| '\u0A35'..'\u0A36'
	| '\u0A38'..'\u0A39'
	| '\u0A59'..'\u0A5C'
	| '\u0A5E'
	| '\u0A72'..'\u0A74'
	| '\u0A85'..'\u0A8B'
	| '\u0A8D'
	| '\u0A8F'..'\u0A91'
	| '\u0A93'..'\u0AA8'
	| '\u0AAA'..'\u0AB0'
	| '\u0AB2'..'\u0AB3'
	| '\u0AB5'..'\u0AB9'
	| '\u0ABD'
	| '\u0AD0'
	| '\u0AE0'
	| '\u0B05'..'\u0B0C'
	| '\u0B0F'..'\u0B10'
	| '\u0B13'..'\u0B28'
	| '\u0B2A'..'\u0B30'
	| '\u0B32'..'\u0B33'
	| '\u0B36'..'\u0B39'
	| '\u0B3D'
	| '\u0B5C'..'\u0B5D'
	| '\u0B5F'..'\u0B61'
	| '\u0B85'..'\u0B8A'
	| '\u0B8E'..'\u0B90'
	| '\u0B92'..'\u0B95'
	| '\u0B99'..'\u0B9A'
	| '\u0B9C'
	| '\u0B9E'..'\u0B9F'
	| '\u0BA3'..'\u0BA4'
	| '\u0BA8'..'\u0BAA'
	| '\u0BAE'..'\u0BB5'
	| '\u0BB7'..'\u0BB9'
	| '\u0C05'..'\u0C0C'
	| '\u0C0E'..'\u0C10'
	| '\u0C12'..'\u0C28'
	| '\u0C2A'..'\u0C33'
	| '\u0C35'..'\u0C39'
	| '\u0C60'..'\u0C61'
	| '\u0C85'..'\u0C8C'
	| '\u0C8E'..'\u0C90'
	| '\u0C92'..'\u0CA8'
	| '\u0CAA'..'\u0CB3'
	| '\u0CB5'..'\u0CB9'
	| '\u0CDE'
	| '\u0CE0'..'\u0CE1'
	| '\u0D05'..'\u0D0C'
	| '\u0D0E'..'\u0D10'
	| '\u0D12'..'\u0D28'
	| '\u0D2A'..'\u0D39'
	| '\u0D60'..'\u0D61'
	| '\u0D85'..'\u0D96'
	| '\u0D9A'..'\u0DB1'
	| '\u0DB3'..'\u0DBB'
	| '\u0DBD'
	| '\u0DC0'..'\u0DC6'
	| '\u0E01'..'\u0E30'
	| '\u0E32'..'\u0E33'
	| '\u0E40'..'\u0E46'
	| '\u0E81'..'\u0E82'
	| '\u0E84'
	| '\u0E87'..'\u0E88'
	| '\u0E8A'
	| '\u0E8D'
	| '\u0E94'..'\u0E97'
	| '\u0E99'..'\u0E9F'
	| '\u0EA1'..'\u0EA3'
	| '\u0EA5'
	| '\u0EA7'
	| '\u0EAA'..'\u0EAB'
	| '\u0EAD'..'\u0EB0'
	| '\u0EB2'..'\u0EB3'
	| '\u0EBD'..'\u0EC4'
	| '\u0EC6'
	| '\u0EDC'..'\u0EDD'
	| '\u0F00'
	| '\u0F40'..'\u0F6A'
	| '\u0F88'..'\u0F8B'
	| '\u1000'..'\u1021'
	| '\u1023'..'\u1027'
	| '\u1029'..'\u102A'
	| '\u1050'..'\u1055'
	| '\u10A0'..'\u10C5'
	| '\u10D0'..'\u10F6'
	| '\u1100'..'\u1159'
	| '\u115F'..'\u11A2'
	| '\u11A8'..'\u11F9'
	| '\u1200'..'\u1206'
	| '\u1208'..'\u1246'
	| '\u1248'
	| '\u124A'..'\u124D'
	| '\u1250'..'\u1256'
	| '\u1258'
	| '\u125A'..'\u125D'
	| '\u1260'..'\u1286'
	| '\u1288'
	| '\u128A'..'\u128D'
	| '\u1290'..'\u12AE'
	| '\u12B0'
	| '\u12B2'..'\u12B5'
	| '\u12B8'..'\u12BE'
	| '\u12C0'
	| '\u12C2'..'\u12C5'
	| '\u12C8'..'\u12CE'
	| '\u12D0'..'\u12D6'
	| '\u12D8'..'\u12EE'
	| '\u12F0'..'\u130E'
	| '\u1310'
	| '\u1312'..'\u1315'
	| '\u1318'..'\u131E'
	| '\u1320'..'\u1346'
	| '\u1348'..'\u135A'
	| '\u13A0'..'\u13B0'
	| '\u13B1'..'\u13F4'
	| '\u1401'..'\u1676'
	| '\u1681'..'\u169A'
	| '\u16A0'..'\u16EA'
	| '\u1780'..'\u17B3'
	| '\u1820'..'\u1877'
	| '\u1880'..'\u18A8'
	| '\u1E00'..'\u1E9B'
	| '\u1EA0'..'\u1EE0'
	| '\u1EE1'..'\u1EF9'
	| '\u1F00'..'\u1F15'
	| '\u1F18'..'\u1F1D'
	| '\u1F20'..'\u1F39'
	| '\u1F3A'..'\u1F45'
	| '\u1F48'..'\u1F4D'
	| '\u1F50'..'\u1F57'
	| '\u1F59'
	| '\u1F5B'
	| '\u1F5D'
	| '\u1F5F'..'\u1F7D'
	| '\u1F80'..'\u1FB4'
	| '\u1FB6'..'\u1FBC'
	| '\u1FBE'
	| '\u1FC2'..'\u1FC4'
	| '\u1FC6'..'\u1FCC'
	| '\u1FD0'..'\u1FD3'
	| '\u1FD6'..'\u1FDB'
	| '\u1FE0'..'\u1FEC'
	| '\u1FF2'..'\u1FF4'
	| '\u1FF6'..'\u1FFC'
	| '\u207F'
	| '\u2102'
	| '\u2107'
	| '\u210A'..'\u2113'
	| '\u2115'
	| '\u2119'..'\u211D'
	| '\u2124'
	| '\u2126'
	| '\u2128'
	| '\u212A'..'\u212D'
	| '\u212F'..'\u2131'
	| '\u2133'..'\u2139'
	| '\u2160'..'\u2183'
	| '\u3005'..'\u3007'
	| '\u3021'..'\u3029'
	| '\u3031'..'\u3035'
	| '\u3038'..'\u303A'
	| '\u3041'..'\u3094'
	| '\u309D'..'\u309E'
	| '\u30A1'..'\u30FA'
	| '\u30FC'..'\u30FE'
	| '\u3105'..'\u312C'
	| '\u3131'..'\u318E'
	| '\u31A0'..'\u31B7'
	| '\u3400'
	| '\u4DB5'
	| '\u4E00'
	| '\u9FA5'
	| '\uA000'..'\uA48C'
	| '\uAC00'
	| '\uD7A3'
	| '\uF900'..'\uFA2D'
	| '\uFB00'..'\uFB06'
	| '\uFB13'..'\uFB17'
	| '\uFB1D'
	| '\uFB1F'..'\uFB28'
	| '\uFB2A'..'\uFB36'
	| '\uFB38'..'\uFB3C'
	| '\uFB3E'
	| '\uFB40'..'\uFB41'
	| '\uFB43'..'\uFB44'
	| '\uFB46'..'\uFBB1'
	| '\uFBD3'..'\uFD3D'
	| '\uFD50'..'\uFD8F'
	| '\uFD92'..'\uFDC7'
	| '\uFDF0'..'\uFDFB'
	| '\uFE70'..'\uFE72'
	| '\uFE74'
	| '\uFE76'..'\uFEFC'
	| '\uFF21'..'\uFF3A'
	| '\uFF41'..'\uFF5A'
	| '\uFF66'..'\uFFBE'
	| '\uFFC2'..'\uFFC7'
	| '\uFFCA'..'\uFFCF'
	| '\uFFD2'..'\uFFD7'
	| '\uFFDA'..'\uFFDC'
	;
	
fragment UnicodeCombiningMark	// Any character in the Unicode categories "Non-spacing mark (Mn)"
	: '\u0300'..'\u034E'	// or "Combining spacing mark (Mc)".
	| '\u0360'..'\u0362'
	| '\u0483'..'\u0486'
	| '\u0591'..'\u05A1'
	| '\u05A3'..'\u05B9'
	| '\u05BB'..'\u05BD'
	| '\u05BF' 
	| '\u05C1'..'\u05C2'
	| '\u05C4'
	| '\u064B'..'\u0655'
	| '\u0670'
	| '\u06D6'..'\u06DC'
	| '\u06DF'..'\u06E4'
	| '\u06E7'..'\u06E8'
	| '\u06EA'..'\u06ED'
	| '\u0711'
	| '\u0730'..'\u074A'
	| '\u07A6'..'\u07B0'
	| '\u0901'..'\u0903'
	| '\u093C'
	| '\u093E'..'\u094D'
	| '\u0951'..'\u0954'
	| '\u0962'..'\u0963'
	| '\u0981'..'\u0983'
	| '\u09BC'..'\u09C4'
	| '\u09C7'..'\u09C8'
	| '\u09CB'..'\u09CD'
	| '\u09D7'
	| '\u09E2'..'\u09E3'
	| '\u0A02'
	| '\u0A3C'
	| '\u0A3E'..'\u0A42'
	| '\u0A47'..'\u0A48'
	| '\u0A4B'..'\u0A4D'
	| '\u0A70'..'\u0A71'
	| '\u0A81'..'\u0A83'
	| '\u0ABC'
	| '\u0ABE'..'\u0AC5'
	| '\u0AC7'..'\u0AC9'
	| '\u0ACB'..'\u0ACD'
	| '\u0B01'..'\u0B03'
	| '\u0B3C'
	| '\u0B3E'..'\u0B43'
	| '\u0B47'..'\u0B48'
	| '\u0B4B'..'\u0B4D'
	| '\u0B56'..'\u0B57'
	| '\u0B82'..'\u0B83'
	| '\u0BBE'..'\u0BC2'
	| '\u0BC6'..'\u0BC8'
	| '\u0BCA'..'\u0BCD'
	| '\u0BD7'
	| '\u0C01'..'\u0C03'
	| '\u0C3E'..'\u0C44'
	| '\u0C46'..'\u0C48'
	| '\u0C4A'..'\u0C4D'
	| '\u0C55'..'\u0C56'
	| '\u0C82'..'\u0C83'
	| '\u0CBE'..'\u0CC4'
	| '\u0CC6'..'\u0CC8'
	| '\u0CCA'..'\u0CCD'
	| '\u0CD5'..'\u0CD6'
	| '\u0D02'..'\u0D03'
	| '\u0D3E'..'\u0D43'
	| '\u0D46'..'\u0D48'
	| '\u0D4A'..'\u0D4D'
	| '\u0D57'
	| '\u0D82'..'\u0D83'
	| '\u0DCA'
	| '\u0DCF'..'\u0DD4'
	| '\u0DD6'
	| '\u0DD8'..'\u0DDF'
	| '\u0DF2'..'\u0DF3'
	| '\u0E31'
	| '\u0E34'..'\u0E3A'
	| '\u0E47'..'\u0E4E'
	| '\u0EB1'
	| '\u0EB4'..'\u0EB9'
	| '\u0EBB'..'\u0EBC'
	| '\u0EC8'..'\u0ECD'
	| '\u0F18'..'\u0F19'
	| '\u0F35'
	| '\u0F37'
	| '\u0F39'
	| '\u0F3E'..'\u0F3F'
	| '\u0F71'..'\u0F84'
	| '\u0F86'..'\u0F87'
	| '\u0F90'..'\u0F97'
	| '\u0F99'..'\u0FBC'
	| '\u0FC6'
	| '\u102C'..'\u1032'
	| '\u1036'..'\u1039'
	| '\u1056'..'\u1059'
	| '\u17B4'..'\u17D3'
	| '\u18A9'
	| '\u20D0'..'\u20DC'
	| '\u20E1'
	| '\u302A'..'\u302F'
	| '\u3099'..'\u309A'
	| '\uFB1E'
	| '\uFE20'..'\uFE23'
	;

fragment UnicodeDigit		// Any character in the Unicode category "Decimal number (Nd)".
	: '\u0030'..'\u0039'
	| '\u0660'..'\u0669'
	| '\u06F0'..'\u06F9'
	| '\u0966'..'\u096F'
	| '\u09E6'..'\u09EF'
	| '\u0A66'..'\u0A6F'
	| '\u0AE6'..'\u0AEF'
	| '\u0B66'..'\u0B6F'
	| '\u0BE7'..'\u0BEF'
	| '\u0C66'..'\u0C6F'
	| '\u0CE6'..'\u0CEF'
	| '\u0D66'..'\u0D6F'
	| '\u0E50'..'\u0E59'
	| '\u0ED0'..'\u0ED9'
	| '\u0F20'..'\u0F29'
	| '\u1040'..'\u1049'
	| '\u1369'..'\u1371'
	| '\u17E0'..'\u17E9'
	| '\u1810'..'\u1819'
	| '\uFF10'..'\uFF19'
	;
	
fragment UnicodeConnectorPunctuation	// Any character in the Unicode category "Connector punctuation (Pc)".
	: '\u005F'
	| '\u203F'..'\u2040'
	| '\u30FB'
	| '\uFE33'..'\uFE34'
	| '\uFE4D'..'\uFE4F'
	| '\uFF3F'
	| '\uFF65'
	;
	
Comment
	: '/*' (options {greedy=false;} : .)* '*/' {$channel=HIDDEN;}
	;

LineComment
	: '//' ~(LT)* {$channel=HIDDEN;}
	;

LT
	: '\n'		// Line feed.
	| '\r'		// Carriage return.
	| '\u2028'	// Line separator.
	| '\u2029'	// Paragraph separator.
	;

WhiteSpace // Tab, vertical tab, form feed, space, non-breaking space and any other unicode "space separator".
	: ('\t' | '\v' | '\f' | ' ' | '\u00A0')	{$channel=HIDDEN;}
	;
