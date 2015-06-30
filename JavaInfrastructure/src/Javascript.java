import jsFoundation.JsClosure;
import jsFoundation.JsRuntime;
import jsFoundation.jsType.*;
public class Javascript extends JsFunction 
{
	public JsFunction GetDup() 
	{
		return new Javascript();
	}
	public String GetCanonicalName() 
	{
		return "test.Javascript.main";
	}
	public static boolean alwaysTrue() 
	{
		return true;
	}
	public static void main(String[] args) throws Throwable
	{
		JsRuntime mn = new JsRuntime(new Javascript());
		mn.Run();
	}
	public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception 
	{
		closureInfo.FunctionDeclare("isOperator", new isOperator());
		closureInfo.FunctionDeclare("getPriority", new getPriority());
		closureInfo.FunctionDeclare("comparePri", new comparePri());
		closureInfo.FunctionDeclare("dal2Rpn", new dal2Rpn());
		closureInfo.FunctionDeclare("evalRpn", new evalRpn());
		closureInfo.FunctionDeclare("calculate", new calculate());
		closureInfo.FunctionDeclare("getResult", new getResult());
		closureInfo.Set("v400",closureInfo.Get("console"));
		closureInfo.Declare("v401", new JsList());
		closureInfo.Declare("v402", new JsList());
		closureInfo.Declare("v403", new JsString("1 + 2 * (3 - 4) / 5+6/5"));
		((JsList)(closureInfo.Get("v402")))._push(closureInfo.Get("v403"));
		closureInfo.Set("v404",closureInfo.Get("calculate").Execute((JsList)closureInfo.Get("v402")));
		((JsList)(closureInfo.Get("v401")))._push(closureInfo.Get("v404"));
		closureInfo.Set("v405",closureInfo.Get("v400").GetProperty(new JsString("log")).Execute((JsList)closureInfo.Get("v401")));
		closureInfo.Set("v409",closureInfo.Get("console"));
		closureInfo.Declare("v410", new JsList());
		closureInfo.Declare("v411", new JsList());
		closureInfo.Declare("v412", new JsString("( 1 + 2 ) * (( 3 - 4 ) / 5)"));
		((JsList)(closureInfo.Get("v411")))._push(closureInfo.Get("v412"));
		closureInfo.Set("v413",closureInfo.Get("calculate").Execute((JsList)closureInfo.Get("v411")));
		((JsList)(closureInfo.Get("v410")))._push(closureInfo.Get("v413"));
		closureInfo.Set("v414",closureInfo.Get("v409").GetProperty(new JsString("log")).Execute((JsList)closureInfo.Get("v410")));
		return new JsUndefined();
	}
	public static class isOperator extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new isOperator();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.isOperator";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("val", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.Declare("v5", new JsString("+-*/()"));
			closureInfo.Declare("OperatorStr", closureInfo.Get("v5"));
			closureInfo.Set("v6",closureInfo.Get("OperatorStr"));
			closureInfo.Declare("v7", new JsList());
			((JsList)(closureInfo.Get("v7")))._push(closureInfo.Get("val"));
			closureInfo.Set("v8",closureInfo.Get("v6").GetProperty(new JsString("indexOf")).Execute((JsList)closureInfo.Get("v7")));
			closureInfo.Declare("v9", JsNumber.initLiteral(1));
			closureInfo.Set("v9", closureInfo.Get("v9").Asterisk(new JsIntegral(-1)));
			closureInfo.Set("v10" ,closureInfo.Get("v8").LessThan(closureInfo.Get("v9")));
			closureInfo.Set("temp" ,closureInfo.Get("v8").IdenticalTo(closureInfo.Get("v9")));
			closureInfo.Set("v10" ,closureInfo.Get("v10").Or(closureInfo.Get("temp")));
			closureInfo.Set("v10" ,closureInfo.Get("v10").Exclaimation());
			if(Javascript.alwaysTrue()) 
			{
				 return closureInfo.Get("v10");
				 
			}
			 return new JsUndefined();
		}
		
	}
	public static class getPriority extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new getPriority();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.getPriority";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("val", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.Declare("v22", new JsString("-"));
			closureInfo.Set("v23" ,closureInfo.Get("val").EqualTo(closureInfo.Get("v22")));
			closureInfo.Declare("v24", new JsString("+"));
			closureInfo.Set("v25" ,closureInfo.Get("val").EqualTo(closureInfo.Get("v24")));
			closureInfo.Set("v26" ,closureInfo.Get("v23").Or(closureInfo.Get("v25")));
			if (closureInfo.Get("v26").ToBoolean()._getValue() == true) 
			{
				closureInfo=JsClosure.foldClosure(closureInfo);
				{
					closureInfo.Declare("v27", JsNumber.initLiteral(1));
					if(Javascript.alwaysTrue()) 
					{
						 return closureInfo.Get("v27");
						 
					}
					 
				}
				closureInfo=JsClosure.unfoldClosure(closureInfo);
			}
			else
			{
				closureInfo=JsClosure.foldClosure(closureInfo);
				{
					closureInfo.Declare("v28", new JsString("/"));
					closureInfo.Set("v29" ,closureInfo.Get("val").EqualTo(closureInfo.Get("v28")));
					closureInfo.Declare("v30", new JsString("*"));
					closureInfo.Set("v31" ,closureInfo.Get("val").EqualTo(closureInfo.Get("v30")));
					closureInfo.Set("v32" ,closureInfo.Get("v29").Or(closureInfo.Get("v31")));
					if (closureInfo.Get("v32").ToBoolean()._getValue() == true) 
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Declare("v33", JsNumber.initLiteral(2));
							if(Javascript.alwaysTrue()) 
							{
								 return closureInfo.Get("v33");
								 
							}
							 
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					else
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Declare("v34", JsNumber.initLiteral(0));
							if(Javascript.alwaysTrue()) 
							{
								 return closureInfo.Get("v34");
								 
							}
							 
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					
				}
				closureInfo=JsClosure.unfoldClosure(closureInfo);
			}
			return new JsUndefined();
		}
		
	}
	public static class comparePri extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new comparePri();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.comparePri";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("op1", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.Set("op2", closureInfo.Get("arguments").GetProperty(new JsIntegral(1)));
			closureInfo.Declare("v39", new JsList());
			((JsList)(closureInfo.Get("v39")))._push(closureInfo.Get("op1"));
			closureInfo.Set("v40",closureInfo.Get("getPriority").Execute((JsList)closureInfo.Get("v39")));
			closureInfo.Declare("v41", new JsList());
			((JsList)(closureInfo.Get("v41")))._push(closureInfo.Get("op2"));
			closureInfo.Set("v42",closureInfo.Get("getPriority").Execute((JsList)closureInfo.Get("v41")));
			closureInfo.Set("v43" ,closureInfo.Get("v40").LessThan(closureInfo.Get("v42")));
			closureInfo.Set("temp" ,closureInfo.Get("v40").IdenticalTo(closureInfo.Get("v42")));
			closureInfo.Set("v43" ,closureInfo.Get("v43").Or(closureInfo.Get("temp")));
			if(Javascript.alwaysTrue()) 
			{
				 return closureInfo.Get("v43");
				 
			}
			 return new JsUndefined();
		}
		
	}
	public static class dal2Rpn extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new dal2Rpn();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.dal2Rpn";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("exp", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.Declare("v135", new JsList());
			closureInfo.Declare("inputExp", closureInfo.Get("v135"));
			closureInfo.Declare("v136", new JsList());
			closureInfo.Declare("outputStack", closureInfo.Get("v136"));
			closureInfo.Declare("v137", new JsList());
			closureInfo.Declare("outputExp", closureInfo.Get("v137"));
			closureInfo.Set("v138",closureInfo.Get("exp"));
			closureInfo.Set("v139",closureInfo.Get("v138").GetProperty(new JsString("length")));
			closureInfo.Declare("t", closureInfo.Get("v139"));
			closureInfo=JsClosure.foldClosure(closureInfo);
			{
				closureInfo.Declare("v140", JsNumber.initLiteral(0));
				closureInfo.Declare("i", closureInfo.Get("v140"));
				closureInfo.Set("v141" ,closureInfo.Get("i").LessThan(closureInfo.Get("t")));
				for (;closureInfo.Get("v141").ToBoolean()._getValue() == true;)
				{
					closureInfo.Set("v142",closureInfo.Get("exp"));
					closureInfo.Set("v143",closureInfo.Get("v142").GetProperty(closureInfo.Get("i")));
					closureInfo.Declare("c", closureInfo.Get("v143"));
					closureInfo.Declare("v144", new JsString(" "));
					closureInfo.Set("v145" ,closureInfo.Get("c").EqualTo(closureInfo.Get("v144")));
					closureInfo.Set("v145" ,closureInfo.Get("v145").Exclaimation());
					if (closureInfo.Get("v145").ToBoolean()._getValue() == true) 
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Set("v146",closureInfo.Get("inputExp"));
							closureInfo.Declare("v147", new JsList());
							((JsList)(closureInfo.Get("v147")))._push(closureInfo.Get("c"));
							closureInfo.Set("v148",closureInfo.Get("v146").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v147")));
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					closureInfo.Set("i", closureInfo.Get("i").Plus(new JsIntegral(1)));
					closureInfo.Set("v141" ,closureInfo.Get("i").LessThan(closureInfo.Get("t")));
				}
				
			}
			closureInfo=JsClosure.unfoldClosure(closureInfo);
			closureInfo.Set("v149",closureInfo.Get("inputExp"));
			closureInfo.Set("v150",closureInfo.Get("v149").GetProperty(new JsString("length")));
			closureInfo.Declare("t1", closureInfo.Get("v150"));
			closureInfo=JsClosure.foldClosure(closureInfo);
			{
				closureInfo.Declare("v151", JsNumber.initLiteral(0));
				closureInfo.Set("v152" ,closureInfo.Get("t1").LessThan(closureInfo.Get("v151")));
				closureInfo.Set("temp" ,closureInfo.Get("t1").IdenticalTo(closureInfo.Get("v151")));
				closureInfo.Set("v152" ,closureInfo.Get("v152").Or(closureInfo.Get("temp")));
				closureInfo.Set("v152" ,closureInfo.Get("v152").Exclaimation());
				while(closureInfo.Get("v152").ToBoolean()._getValue() == true)
				{
					closureInfo.Set("v153",closureInfo.Get("inputExp"));
					closureInfo.Declare("v154", new JsList());
					closureInfo.Set("v155",closureInfo.Get("v153").GetProperty(new JsString("shift")).Execute((JsList)closureInfo.Get("v154")));
					closureInfo.Set("cur", closureInfo.Get("v155"));
					closureInfo.Declare("v156", new JsList());
					((JsList)(closureInfo.Get("v156")))._push(closureInfo.Get("cur"));
					closureInfo.Set("v157",closureInfo.Get("isOperator").Execute((JsList)closureInfo.Get("v156")));
					if (closureInfo.Get("v157").ToBoolean()._getValue() == true) 
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Declare("v158", new JsString("("));
							closureInfo.Set("v159" ,closureInfo.Get("cur").EqualTo(closureInfo.Get("v158")));
							if (closureInfo.Get("v159").ToBoolean()._getValue() == true) 
							{
								closureInfo=JsClosure.foldClosure(closureInfo);
								{
									closureInfo.Set("v160",closureInfo.Get("outputStack"));
									closureInfo.Declare("v161", new JsList());
									closureInfo.Declare("v162", new JsString("("));
									((JsList)(closureInfo.Get("v161")))._push(closureInfo.Get("v162"));
									closureInfo.Set("v163",closureInfo.Get("v160").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v161")));
								}
								closureInfo=JsClosure.unfoldClosure(closureInfo);
							}
							else
							{
								closureInfo=JsClosure.foldClosure(closureInfo);
								{
									closureInfo.Declare("v164", new JsString(")"));
									closureInfo.Set("v165" ,closureInfo.Get("cur").EqualTo(closureInfo.Get("v164")));
									if (closureInfo.Get("v165").ToBoolean()._getValue() == true) 
									{
										closureInfo=JsClosure.foldClosure(closureInfo);
										{
											closureInfo.Set("v166",closureInfo.Get("outputStack"));
											closureInfo.Declare("v167", new JsList());
											closureInfo.Set("v168",closureInfo.Get("v166").GetProperty(new JsString("pop")).Execute((JsList)closureInfo.Get("v167")));
											closureInfo.Declare("top", closureInfo.Get("v168"));
											closureInfo.Set("v169",closureInfo.Get("outputStack"));
											closureInfo.Set("v170",closureInfo.Get("v169").GetProperty(new JsString("length")));
											closureInfo.Declare("t2", closureInfo.Get("v170"));
											closureInfo=JsClosure.foldClosure(closureInfo);
											{
												closureInfo.Declare("v171", new JsString("("));
												closureInfo.Set("v172" ,closureInfo.Get("top").EqualTo(closureInfo.Get("v171")));
												closureInfo.Set("v172" ,closureInfo.Get("v172").Exclaimation());
												closureInfo.Declare("v173", JsNumber.initLiteral(0));
												closureInfo.Set("v174" ,closureInfo.Get("t2").LessThan(closureInfo.Get("v173")));
												closureInfo.Set("temp" ,closureInfo.Get("t2").IdenticalTo(closureInfo.Get("v173")));
												closureInfo.Set("v174" ,closureInfo.Get("v174").Or(closureInfo.Get("temp")));
												closureInfo.Set("v174" ,closureInfo.Get("v174").Exclaimation());
												closureInfo.Set("v175" ,closureInfo.Get("v172").And(closureInfo.Get("v174")));
												while(closureInfo.Get("v175").ToBoolean()._getValue() == true)
												{
													closureInfo.Set("v176",closureInfo.Get("outputExp"));
													closureInfo.Declare("v177", new JsList());
													((JsList)(closureInfo.Get("v177")))._push(closureInfo.Get("top"));
													closureInfo.Set("v178",closureInfo.Get("v176").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v177")));
													closureInfo.Set("v179",closureInfo.Get("outputStack"));
													closureInfo.Declare("v180", new JsList());
													closureInfo.Set("v181",closureInfo.Get("v179").GetProperty(new JsString("pop")).Execute((JsList)closureInfo.Get("v180")));
													closureInfo.Set("top", closureInfo.Get("v181"));
													closureInfo.Set("v182",closureInfo.Get("outputStack"));
													closureInfo.Set("v183",closureInfo.Get("v182").GetProperty(new JsString("length")));
													closureInfo.Set("t2", closureInfo.Get("v183"));
													closureInfo.Declare("v171", new JsString("("));
													closureInfo.Set("v172" ,closureInfo.Get("top").EqualTo(closureInfo.Get("v171")));
													closureInfo.Set("v172" ,closureInfo.Get("v172").Exclaimation());
													closureInfo.Declare("v173", JsNumber.initLiteral(0));
													closureInfo.Set("v174" ,closureInfo.Get("t2").LessThan(closureInfo.Get("v173")));
													closureInfo.Set("temp" ,closureInfo.Get("t2").IdenticalTo(closureInfo.Get("v173")));
													closureInfo.Set("v174" ,closureInfo.Get("v174").Or(closureInfo.Get("temp")));
													closureInfo.Set("v174" ,closureInfo.Get("v174").Exclaimation());
													closureInfo.Set("v175" ,closureInfo.Get("v172").And(closureInfo.Get("v174")));
												}
												
											}
											closureInfo=JsClosure.unfoldClosure(closureInfo);
											closureInfo.Declare("v184", new JsString("("));
											closureInfo.Set("v185" ,closureInfo.Get("top").EqualTo(closureInfo.Get("v184")));
											closureInfo.Set("v185" ,closureInfo.Get("v185").Exclaimation());
											if (closureInfo.Get("v185").ToBoolean()._getValue() == true) 
											{
												closureInfo=JsClosure.foldClosure(closureInfo);
												{
													closureInfo.Set("v186",closureInfo.Get("console"));
													closureInfo.Declare("v187", new JsList());
													closureInfo.Declare("v188", new JsString("error: unmatched ()"));
													((JsList)(closureInfo.Get("v187")))._push(closureInfo.Get("v188"));
													closureInfo.Set("v189",closureInfo.Get("v186").GetProperty(new JsString("log")).Execute((JsList)closureInfo.Get("v187")));
													closureInfo.Set("v190",closureInfo.Get("process"));
													closureInfo.Declare("v191", new JsList());
													closureInfo.Declare("v192", JsNumber.initLiteral(1));
													closureInfo.Set("v192", closureInfo.Get("v192").Asterisk(new JsIntegral(-1)));
													((JsList)(closureInfo.Get("v191")))._push(closureInfo.Get("v192"));
													closureInfo.Set("v193",closureInfo.Get("v190").GetProperty(new JsString("exit")).Execute((JsList)closureInfo.Get("v191")));
												}
												closureInfo=JsClosure.unfoldClosure(closureInfo);
											}
											
										}
										closureInfo=JsClosure.unfoldClosure(closureInfo);
									}
									else
									{
										closureInfo=JsClosure.foldClosure(closureInfo);
										{
											closureInfo.Set("v194",closureInfo.Get("outputStack"));
											closureInfo.Set("v195",closureInfo.Get("v194").GetProperty(new JsString("length")));
											closureInfo.Declare("t3", closureInfo.Get("v195"));
											closureInfo=JsClosure.foldClosure(closureInfo);
											{
												closureInfo.Declare("v196", new JsList());
												((JsList)(closureInfo.Get("v196")))._push(closureInfo.Get("cur"));
												closureInfo.Declare("v197", JsNumber.initLiteral(1));
												closureInfo.Set("v198" ,closureInfo.Get("t3").Minus(closureInfo.Get("v197")));
												closureInfo.Set("v199",closureInfo.Get("outputStack"));
												closureInfo.Set("v200",closureInfo.Get("v199").GetProperty(closureInfo.Get("v198")));
												((JsList)(closureInfo.Get("v196")))._push(closureInfo.Get("v200"));
												closureInfo.Set("v201",closureInfo.Get("comparePri").Execute((JsList)closureInfo.Get("v196")));
												closureInfo.Declare("v202", JsNumber.initLiteral(0));
												closureInfo.Set("v203" ,closureInfo.Get("t3").LessThan(closureInfo.Get("v202")));
												closureInfo.Set("temp" ,closureInfo.Get("t3").IdenticalTo(closureInfo.Get("v202")));
												closureInfo.Set("v203" ,closureInfo.Get("v203").Or(closureInfo.Get("temp")));
												closureInfo.Set("v203" ,closureInfo.Get("v203").Exclaimation());
												closureInfo.Set("v204" ,closureInfo.Get("v201").And(closureInfo.Get("v203")));
												while(closureInfo.Get("v204").ToBoolean()._getValue() == true)
												{
													closureInfo.Set("v205",closureInfo.Get("outputExp"));
													closureInfo.Declare("v206", new JsList());
													closureInfo.Set("v207",closureInfo.Get("outputStack"));
													closureInfo.Declare("v208", new JsList());
													closureInfo.Set("v209",closureInfo.Get("v207").GetProperty(new JsString("pop")).Execute((JsList)closureInfo.Get("v208")));
													((JsList)(closureInfo.Get("v206")))._push(closureInfo.Get("v209"));
													closureInfo.Set("v210",closureInfo.Get("v205").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v206")));
													closureInfo.Set("v211",closureInfo.Get("outputStack"));
													closureInfo.Set("v212",closureInfo.Get("v211").GetProperty(new JsString("length")));
													closureInfo.Set("t3", closureInfo.Get("v212"));
													closureInfo.Declare("v196", new JsList());
													((JsList)(closureInfo.Get("v196")))._push(closureInfo.Get("cur"));
													closureInfo.Declare("v197", JsNumber.initLiteral(1));
													closureInfo.Set("v198" ,closureInfo.Get("t3").Minus(closureInfo.Get("v197")));
													closureInfo.Set("v199",closureInfo.Get("outputStack"));
													closureInfo.Set("v200",closureInfo.Get("v199").GetProperty(closureInfo.Get("v198")));
													((JsList)(closureInfo.Get("v196")))._push(closureInfo.Get("v200"));
													closureInfo.Set("v201",closureInfo.Get("comparePri").Execute((JsList)closureInfo.Get("v196")));
													closureInfo.Declare("v202", JsNumber.initLiteral(0));
													closureInfo.Set("v203" ,closureInfo.Get("t3").LessThan(closureInfo.Get("v202")));
													closureInfo.Set("temp" ,closureInfo.Get("t3").IdenticalTo(closureInfo.Get("v202")));
													closureInfo.Set("v203" ,closureInfo.Get("v203").Or(closureInfo.Get("temp")));
													closureInfo.Set("v203" ,closureInfo.Get("v203").Exclaimation());
													closureInfo.Set("v204" ,closureInfo.Get("v201").And(closureInfo.Get("v203")));
												}
												
											}
											closureInfo=JsClosure.unfoldClosure(closureInfo);
											closureInfo.Set("v213",closureInfo.Get("outputStack"));
											closureInfo.Declare("v214", new JsList());
											((JsList)(closureInfo.Get("v214")))._push(closureInfo.Get("cur"));
											closureInfo.Set("v215",closureInfo.Get("v213").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v214")));
										}
										closureInfo=JsClosure.unfoldClosure(closureInfo);
									}
									
								}
								closureInfo=JsClosure.unfoldClosure(closureInfo);
							}
							
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					else
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Set("v216",closureInfo.Get("outputExp"));
							closureInfo.Declare("v217", new JsList());
							closureInfo.Declare("v218", JsNumber.initLiteral(0));
							closureInfo.Declare("v219", JsNumber.initLiteral(0));
							closureInfo.Set("v220" ,closureInfo.Get("v219").Minus(closureInfo.Get("cur")));
							closureInfo.Set("v221" ,closureInfo.Get("v218").Minus(closureInfo.Get("v220")));
							((JsList)(closureInfo.Get("v217")))._push(closureInfo.Get("v221"));
							closureInfo.Set("v222",closureInfo.Get("v216").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v217")));
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					closureInfo.Set("v223",closureInfo.Get("inputExp"));
					closureInfo.Set("v224",closureInfo.Get("v223").GetProperty(new JsString("length")));
					closureInfo.Set("t1", closureInfo.Get("v224"));
					closureInfo.Declare("v151", JsNumber.initLiteral(0));
					closureInfo.Set("v152" ,closureInfo.Get("t1").LessThan(closureInfo.Get("v151")));
					closureInfo.Set("temp" ,closureInfo.Get("t1").IdenticalTo(closureInfo.Get("v151")));
					closureInfo.Set("v152" ,closureInfo.Get("v152").Or(closureInfo.Get("temp")));
					closureInfo.Set("v152" ,closureInfo.Get("v152").Exclaimation());
				}
				
			}
			closureInfo=JsClosure.unfoldClosure(closureInfo);
			closureInfo.Set("v225",closureInfo.Get("outputStack"));
			closureInfo.Set("v226",closureInfo.Get("v225").GetProperty(new JsString("length")));
			closureInfo.Declare("t4", closureInfo.Get("v226"));
			closureInfo.Declare("v227", JsNumber.initLiteral(0));
			closureInfo.Set("v228" ,closureInfo.Get("t4").LessThan(closureInfo.Get("v227")));
			closureInfo.Set("temp" ,closureInfo.Get("t4").IdenticalTo(closureInfo.Get("v227")));
			closureInfo.Set("v228" ,closureInfo.Get("v228").Or(closureInfo.Get("temp")));
			closureInfo.Set("v228" ,closureInfo.Get("v228").Exclaimation());
			if (closureInfo.Get("v228").ToBoolean()._getValue() == true) 
			{
				closureInfo=JsClosure.foldClosure(closureInfo);
				{
					closureInfo.Declare("v229", JsNumber.initLiteral(1));
					closureInfo.Set("v230" ,closureInfo.Get("t4").Minus(closureInfo.Get("v229")));
					closureInfo.Set("v231",closureInfo.Get("outputStack"));
					closureInfo.Set("v232",closureInfo.Get("v231").GetProperty(closureInfo.Get("v230")));
					closureInfo.Declare("b1", closureInfo.Get("v232"));
					closureInfo.Declare("v233", new JsString(")"));
					closureInfo.Set("v234" ,closureInfo.Get("b1").EqualTo(closureInfo.Get("v233")));
					closureInfo.Declare("v235", new JsString("("));
					closureInfo.Set("v236" ,closureInfo.Get("b1").EqualTo(closureInfo.Get("v235")));
					closureInfo.Set("v237" ,closureInfo.Get("v234").Or(closureInfo.Get("v236")));
					if (closureInfo.Get("v237").ToBoolean()._getValue() == true) 
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Set("v238",closureInfo.Get("console"));
							closureInfo.Declare("v239", new JsList());
							closureInfo.Declare("v240", new JsString("error: unmatched ()"));
							((JsList)(closureInfo.Get("v239")))._push(closureInfo.Get("v240"));
							closureInfo.Set("v241",closureInfo.Get("v238").GetProperty(new JsString("log")).Execute((JsList)closureInfo.Get("v239")));
							closureInfo.Set("v242",closureInfo.Get("process"));
							closureInfo.Declare("v243", new JsList());
							closureInfo.Declare("v244", JsNumber.initLiteral(1));
							closureInfo.Set("v244", closureInfo.Get("v244").Asterisk(new JsIntegral(-1)));
							((JsList)(closureInfo.Get("v243")))._push(closureInfo.Get("v244"));
							closureInfo.Set("v245",closureInfo.Get("v242").GetProperty(new JsString("exit")).Execute((JsList)closureInfo.Get("v243")));
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					closureInfo.Set("v246",closureInfo.Get("outputStack"));
					closureInfo.Set("v247",closureInfo.Get("v246").GetProperty(new JsString("length")));
					closureInfo.Declare("t5", closureInfo.Get("v247"));
					closureInfo=JsClosure.foldClosure(closureInfo);
					{
						closureInfo.Declare("v248", JsNumber.initLiteral(0));
						closureInfo.Set("v249" ,closureInfo.Get("t5").LessThan(closureInfo.Get("v248")));
						closureInfo.Set("temp" ,closureInfo.Get("t5").IdenticalTo(closureInfo.Get("v248")));
						closureInfo.Set("v249" ,closureInfo.Get("v249").Or(closureInfo.Get("temp")));
						closureInfo.Set("v249" ,closureInfo.Get("v249").Exclaimation());
						while(closureInfo.Get("v249").ToBoolean()._getValue() == true)
						{
							closureInfo.Set("v250",closureInfo.Get("outputExp"));
							closureInfo.Declare("v251", new JsList());
							closureInfo.Set("v252",closureInfo.Get("outputStack"));
							closureInfo.Declare("v253", new JsList());
							closureInfo.Set("v254",closureInfo.Get("v252").GetProperty(new JsString("pop")).Execute((JsList)closureInfo.Get("v253")));
							((JsList)(closureInfo.Get("v251")))._push(closureInfo.Get("v254"));
							closureInfo.Set("v255",closureInfo.Get("v250").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v251")));
							closureInfo.Set("v256",closureInfo.Get("outputStack"));
							closureInfo.Set("v257",closureInfo.Get("v256").GetProperty(new JsString("length")));
							closureInfo.Set("t5", closureInfo.Get("v257"));
							closureInfo.Declare("v248", JsNumber.initLiteral(0));
							closureInfo.Set("v249" ,closureInfo.Get("t5").LessThan(closureInfo.Get("v248")));
							closureInfo.Set("temp" ,closureInfo.Get("t5").IdenticalTo(closureInfo.Get("v248")));
							closureInfo.Set("v249" ,closureInfo.Get("v249").Or(closureInfo.Get("temp")));
							closureInfo.Set("v249" ,closureInfo.Get("v249").Exclaimation());
						}
						
					}
					closureInfo=JsClosure.unfoldClosure(closureInfo);
				}
				closureInfo=JsClosure.unfoldClosure(closureInfo);
			}
			if(Javascript.alwaysTrue()) 
			{
				 return closureInfo.Get("outputExp");
				 
			}
			 return new JsUndefined();
		}
		
	}
	public static class evalRpn extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new evalRpn();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.evalRpn";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("rpnQueue", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.Declare("v294", new JsList());
			closureInfo.Declare("outputStack", closureInfo.Get("v294"));
			closureInfo.Set("v295",closureInfo.Get("rpnQueue"));
			closureInfo.Set("v296",closureInfo.Get("v295").GetProperty(new JsString("length")));
			closureInfo.Declare("t6", closureInfo.Get("v296"));
			closureInfo=JsClosure.foldClosure(closureInfo);
			{
				closureInfo.Declare("v297", JsNumber.initLiteral(0));
				closureInfo.Set("v298" ,closureInfo.Get("t6").LessThan(closureInfo.Get("v297")));
				closureInfo.Set("temp" ,closureInfo.Get("t6").IdenticalTo(closureInfo.Get("v297")));
				closureInfo.Set("v298" ,closureInfo.Get("v298").Or(closureInfo.Get("temp")));
				closureInfo.Set("v298" ,closureInfo.Get("v298").Exclaimation());
				while(closureInfo.Get("v298").ToBoolean()._getValue() == true)
				{
					closureInfo.Set("v299",closureInfo.Get("rpnQueue"));
					closureInfo.Declare("v300", new JsList());
					closureInfo.Set("v301",closureInfo.Get("v299").GetProperty(new JsString("shift")).Execute((JsList)closureInfo.Get("v300")));
					closureInfo.Declare("cur", closureInfo.Get("v301"));
					closureInfo.Declare("v302", new JsList());
					((JsList)(closureInfo.Get("v302")))._push(closureInfo.Get("cur"));
					closureInfo.Set("v303",closureInfo.Get("isOperator").Execute((JsList)closureInfo.Get("v302")));
					closureInfo.Declare("v304", new JsBoolean(false));
					closureInfo.Set("v305" ,closureInfo.Get("v303").EqualTo(closureInfo.Get("v304")));
					if (closureInfo.Get("v305").ToBoolean()._getValue() == true) 
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Set("v306",closureInfo.Get("outputStack"));
							closureInfo.Declare("v307", new JsList());
							((JsList)(closureInfo.Get("v307")))._push(closureInfo.Get("cur"));
							closureInfo.Set("v308",closureInfo.Get("v306").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v307")));
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					else
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Set("v309",closureInfo.Get("outputStack"));
							closureInfo.Set("v310",closureInfo.Get("v309").GetProperty(new JsString("length")));
							closureInfo.Declare("t7", closureInfo.Get("v310"));
							closureInfo.Declare("v311", JsNumber.initLiteral(2));
							closureInfo.Set("v312" ,closureInfo.Get("t7").LessThan(closureInfo.Get("v311")));
							if (closureInfo.Get("v312").ToBoolean()._getValue() == true) 
							{
								closureInfo=JsClosure.foldClosure(closureInfo);
								{
									closureInfo.Set("v313",closureInfo.Get("console"));
									closureInfo.Declare("v314", new JsList());
									closureInfo.Declare("v315", new JsString("invalid stack length"));
									((JsList)(closureInfo.Get("v314")))._push(closureInfo.Get("v315"));
									closureInfo.Set("v316",closureInfo.Get("v313").GetProperty(new JsString("log")).Execute((JsList)closureInfo.Get("v314")));
									closureInfo.Set("v317",closureInfo.Get("process"));
									closureInfo.Declare("v318", new JsList());
									closureInfo.Declare("v319", JsNumber.initLiteral(1));
									closureInfo.Set("v319", closureInfo.Get("v319").Asterisk(new JsIntegral(-1)));
									((JsList)(closureInfo.Get("v318")))._push(closureInfo.Get("v319"));
									closureInfo.Set("v320",closureInfo.Get("v317").GetProperty(new JsString("exit")).Execute((JsList)closureInfo.Get("v318")));
								}
								closureInfo=JsClosure.unfoldClosure(closureInfo);
							}
							closureInfo.Set("v321",closureInfo.Get("outputStack"));
							closureInfo.Declare("v322", new JsList());
							closureInfo.Set("v323",closureInfo.Get("v321").GetProperty(new JsString("pop")).Execute((JsList)closureInfo.Get("v322")));
							closureInfo.Declare("sec", closureInfo.Get("v323"));
							closureInfo.Set("v324",closureInfo.Get("outputStack"));
							closureInfo.Declare("v325", new JsList());
							closureInfo.Set("v326",closureInfo.Get("v324").GetProperty(new JsString("pop")).Execute((JsList)closureInfo.Get("v325")));
							closureInfo.Declare("fir", closureInfo.Get("v326"));
							closureInfo.Set("v327",closureInfo.Get("outputStack"));
							closureInfo.Declare("v328", new JsList());
							closureInfo.Declare("v329", new JsList());
							((JsList)(closureInfo.Get("v329")))._push(closureInfo.Get("fir"));
							((JsList)(closureInfo.Get("v329")))._push(closureInfo.Get("sec"));
							((JsList)(closureInfo.Get("v329")))._push(closureInfo.Get("cur"));
							closureInfo.Set("v330",closureInfo.Get("getResult").Execute((JsList)closureInfo.Get("v329")));
							((JsList)(closureInfo.Get("v328")))._push(closureInfo.Get("v330"));
							closureInfo.Set("v331",closureInfo.Get("v327").GetProperty(new JsString("push")).Execute((JsList)closureInfo.Get("v328")));
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					closureInfo.Set("v332",closureInfo.Get("rpnQueue"));
					closureInfo.Set("v333",closureInfo.Get("v332").GetProperty(new JsString("length")));
					closureInfo.Set("t6", closureInfo.Get("v333"));
					closureInfo.Declare("v297", JsNumber.initLiteral(0));
					closureInfo.Set("v298" ,closureInfo.Get("t6").LessThan(closureInfo.Get("v297")));
					closureInfo.Set("temp" ,closureInfo.Get("t6").IdenticalTo(closureInfo.Get("v297")));
					closureInfo.Set("v298" ,closureInfo.Get("v298").Or(closureInfo.Get("temp")));
					closureInfo.Set("v298" ,closureInfo.Get("v298").Exclaimation());
				}
				
			}
			closureInfo=JsClosure.unfoldClosure(closureInfo);
			closureInfo.Set("v334",closureInfo.Get("outputStack"));
			closureInfo.Set("v335",closureInfo.Get("v334").GetProperty(new JsString("length")));
			closureInfo.Declare("t8", closureInfo.Get("v335"));
			closureInfo.Declare("v336", JsNumber.initLiteral(1));
			closureInfo.Set("v337" ,closureInfo.Get("t8").EqualTo(closureInfo.Get("v336")));
			closureInfo.Set("v337" ,closureInfo.Get("v337").Exclaimation());
			if (closureInfo.Get("v337").ToBoolean()._getValue() == true) 
			{
				closureInfo=JsClosure.foldClosure(closureInfo);
				{
					closureInfo.Set("v338",closureInfo.Get("console"));
					closureInfo.Declare("v339", new JsList());
					closureInfo.Declare("v340", new JsString("invalid exp"));
					((JsList)(closureInfo.Get("v339")))._push(closureInfo.Get("v340"));
					closureInfo.Set("v341",closureInfo.Get("v338").GetProperty(new JsString("log")).Execute((JsList)closureInfo.Get("v339")));
					closureInfo.Set("v342",closureInfo.Get("process"));
					closureInfo.Declare("v343", new JsList());
					closureInfo.Declare("v344", JsNumber.initLiteral(1));
					closureInfo.Set("v344", closureInfo.Get("v344").Asterisk(new JsIntegral(-1)));
					((JsList)(closureInfo.Get("v343")))._push(closureInfo.Get("v344"));
					closureInfo.Set("v345",closureInfo.Get("v342").GetProperty(new JsString("exit")).Execute((JsList)closureInfo.Get("v343")));
				}
				closureInfo=JsClosure.unfoldClosure(closureInfo);
			}
			else
			{
				closureInfo=JsClosure.foldClosure(closureInfo);
				{
					closureInfo.Declare("v346", JsNumber.initLiteral(0));
					closureInfo.Set("v347",closureInfo.Get("outputStack"));
					closureInfo.Set("v348",closureInfo.Get("v347").GetProperty(closureInfo.Get("v346")));
					if(Javascript.alwaysTrue()) 
					{
						 return closureInfo.Get("v348");
						 
					}
					 
				}
				closureInfo=JsClosure.unfoldClosure(closureInfo);
			}
			return new JsUndefined();
		}
		
	}
	public static class calculate extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new calculate();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.calculate";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("exp", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.Declare("v353", new JsList());
			((JsList)(closureInfo.Get("v353")))._push(closureInfo.Get("exp"));
			closureInfo.Set("v354",closureInfo.Get("dal2Rpn").Execute((JsList)closureInfo.Get("v353")));
			closureInfo.Declare("rpnExp", closureInfo.Get("v354"));
			closureInfo.Declare("v355", new JsList());
			((JsList)(closureInfo.Get("v355")))._push(closureInfo.Get("rpnExp"));
			closureInfo.Set("v356",closureInfo.Get("evalRpn").Execute((JsList)closureInfo.Get("v355")));
			if(Javascript.alwaysTrue()) 
			{
				 return closureInfo.Get("v356");
				 
			}
			 return new JsUndefined();
		}
		
	}
	public static class getResult extends JsFunction
	{
		public JsFunction GetDup()
		{
			return new getResult();
		}
		public String GetCanonicalName()
		{
			return "Js.Preclude.getResult";
		}
		public JsVar ExecuteDetail(JsClosure closureInfo) throws Exception
		{
			closureInfo.Set("a", closureInfo.Get("arguments").GetProperty(new JsIntegral(0)));
			closureInfo.Set("b", closureInfo.Get("arguments").GetProperty(new JsIntegral(1)));
			closureInfo.Set("op", closureInfo.Get("arguments").GetProperty(new JsIntegral(2)));
			closureInfo.Declare("v377", new JsString("+"));
			closureInfo.Set("v378" ,closureInfo.Get("op").EqualTo(closureInfo.Get("v377")));
			if (closureInfo.Get("v378").ToBoolean()._getValue() == true) 
			{
				closureInfo=JsClosure.foldClosure(closureInfo);
				{
					closureInfo.Set("v379" ,closureInfo.Get("a").Plus(closureInfo.Get("b")));
					if(Javascript.alwaysTrue()) 
					{
						 return closureInfo.Get("v379");
						 
					}
					 
				}
				closureInfo=JsClosure.unfoldClosure(closureInfo);
			}
			else
			{
				closureInfo=JsClosure.foldClosure(closureInfo);
				{
					closureInfo.Declare("v380", new JsString("-"));
					closureInfo.Set("v381" ,closureInfo.Get("op").EqualTo(closureInfo.Get("v380")));
					if (closureInfo.Get("v381").ToBoolean()._getValue() == true) 
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Set("v382" ,closureInfo.Get("a").Minus(closureInfo.Get("b")));
							if(Javascript.alwaysTrue()) 
							{
								 return closureInfo.Get("v382");
								 
							}
							 
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					else
					{
						closureInfo=JsClosure.foldClosure(closureInfo);
						{
							closureInfo.Declare("v383", new JsString("*"));
							closureInfo.Set("v384" ,closureInfo.Get("op").EqualTo(closureInfo.Get("v383")));
							if (closureInfo.Get("v384").ToBoolean()._getValue() == true) 
							{
								closureInfo=JsClosure.foldClosure(closureInfo);
								{
									closureInfo.Set("v385" ,closureInfo.Get("a").Asterisk(closureInfo.Get("b")));
									if(Javascript.alwaysTrue()) 
									{
										 return closureInfo.Get("v385");
										 
									}
									 
								}
								closureInfo=JsClosure.unfoldClosure(closureInfo);
							}
							else
							{
								closureInfo=JsClosure.foldClosure(closureInfo);
								{
									closureInfo.Declare("v386", JsNumber.initLiteral(0));
									closureInfo.Set("v387" ,closureInfo.Get("b").EqualTo(closureInfo.Get("v386")));
									closureInfo.Set("v387" ,closureInfo.Get("v387").Exclaimation());
									if (closureInfo.Get("v387").ToBoolean()._getValue() == true) 
									{
										closureInfo=JsClosure.foldClosure(closureInfo);
										{
											closureInfo.Set("v388" ,closureInfo.Get("a").Slash(closureInfo.Get("b")));
											if(Javascript.alwaysTrue()) 
											{
												 return closureInfo.Get("v388");
												 
											}
											 
										}
										closureInfo=JsClosure.unfoldClosure(closureInfo);
									}
									else
									{
										closureInfo=JsClosure.foldClosure(closureInfo);
										{
											closureInfo.Set("v389",closureInfo.Get("console"));
											closureInfo.Declare("v390", new JsList());
											closureInfo.Declare("v391", new JsString("invalid divisor"));
											((JsList)(closureInfo.Get("v390")))._push(closureInfo.Get("v391"));
											closureInfo.Set("v392",closureInfo.Get("v389").GetProperty(new JsString("log")).Execute((JsList)closureInfo.Get("v390")));
											closureInfo.Set("v393",closureInfo.Get("process"));
											closureInfo.Declare("v394", new JsList());
											closureInfo.Declare("v395", JsNumber.initLiteral(1));
											closureInfo.Set("v395", closureInfo.Get("v395").Asterisk(new JsIntegral(-1)));
											((JsList)(closureInfo.Get("v394")))._push(closureInfo.Get("v395"));
											closureInfo.Set("v396",closureInfo.Get("v393").GetProperty(new JsString("exit")).Execute((JsList)closureInfo.Get("v394")));
										}
										closureInfo=JsClosure.unfoldClosure(closureInfo);
									}
									
								}
								closureInfo=JsClosure.unfoldClosure(closureInfo);
							}
							
						}
						closureInfo=JsClosure.unfoldClosure(closureInfo);
					}
					
				}
				closureInfo=JsClosure.unfoldClosure(closureInfo);
			}
			return new JsUndefined();
		}
		
	}
	
}
