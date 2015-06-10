function isOperator(val)
{
	var OperatorStr = "+-*/()";
	return OperatorStr.indexOf(val)>-1;
}

function getPriority(val)
{
	switch(val)
	{
		case '+':
		case '-': return 1;
		case '*':
		case '/': return 2;
		default: return 0;
	}
}

function comparePri(op1,op2)
{
	return getPriority(op1)<=getPriority(op2);
}

function dal2Rpn(exp)
{
	var inputExp = [];
	var outputStack = [];
	var outputExp = [];

	for (var i = 0; i < exp.length; i++) {
		var c = exp[i];
		if(c!=' ')
			inputExp.push(c);
	};

	while(inputExp.length>0)
	{
		cur = inputExp.shift();
		if(isOperator(cur))
		{
        	if(cur == '(')
        	{
        		outputStack.push('(');

        	}
        	else if(cur == ')')
        	{
        		var top = outputStack.pop();
        		while(top!='('&&outputStack.length>0)
        		{
        			outputExp.push(top);
        			top = outputStack.pop();
        		}
        		if (top!='(')
        		{
        			console.log("error: unmatched ()");
                    process.exit(-1);
        		}
        	}
        	else
        	{
        		while(comparePri(cur,outputStack[outputStack.length-1])&&outputStack.length>0)
        		{
        			outputExp.push(outputStack.pop());

        		}
        		outputStack.push(cur);
        	}
		}
		else
		{
			outputExp.push(-(-cur));
		}
	}

	if(outputStack.length > 0){
        if(outputStack[outputStack.length - 1] == ')' || outputStack[outputStack.length - 1] == '('){
            console.log("error: unmatched ()");
            process.exit(-1);
        }
        while(outputStack.length > 0){
            outputExp.push(outputStack.pop());
        }
    }
    return outputExp;
}

function evalRpn(rpnQueue){
    var outputStack = [];
    while(rpnQueue.length > 0){
        var cur = rpnQueue.shift();

        if(!isOperator(cur)){
            outputStack.push(cur);
        }else{
            if(outputStack.length < 2){
                console.log("invalid stack length");
                process.exit(-1);
            }
            var sec = outputStack.pop();
            var fir = outputStack.pop();

            outputStack.push(getResult(fir, sec, cur));
        }
    }

    if(outputStack.length != 1){
        console.log("invalid exp");
        process.exit(-1);
    }else{
        return outputStack[0];
    }
}

function calculate(exp)
{
    var rpnExp = dal2Rpn(exp);
    return evalRpn(rpnExp);
}

function getResult(a,b,op)
{
    switch(op)
    {
        case'+': return a+b;
        case'-': return a-b;
        case'*': return a*b;
        case'/':
            if(b!=0)
                return a/b;
            else
            {
                console.log("invalid divisor");
                process.exit(-1);
            }
    }
}

console.log(calculate('1 + 2 * (3 - 4) / 5+6/5'));
console.log(calculate('( 1 + 2 ) * (( 3 - 4 ) / 5)'));
