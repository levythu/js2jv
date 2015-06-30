function isOperator(val)
{
	var OperatorStr = "+-*/()";
	return OperatorStr.indexOf(val) > -1;
}

function getPriority(val)
{
    if (val == '-' || val == '+') {
        return 1;
    } else if (val == '/' || val == '*') {
        return 2;
    } else {
        return 0;
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

    var t = exp.length;
	for (var i = 0; i < t; i++) {
		var c = exp[i];
		if(c!=' ')
			inputExp.push(c);
	};

    var t1 = inputExp.length;
	while(t1 > 0)
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
                var t2 = outputStack.length;
        		while(top != '(' && t2 > 0)
        		{ 
        			outputExp.push(top);
        			top = outputStack.pop();
                    t2 = outputStack.length;
        		}
        		if (top!='(')
        		{
        			console.log("error: unmatched ()");
                    process.exit(-1);
        		}
        	}
        	else
        	{
                var t3 = outputStack.length;
        		while(comparePri(cur,outputStack[t3 - 1]) && t3 > 0)
        		{
        			outputExp.push(outputStack.pop());
                    t3 = outputStack.length;
        		}
        		outputStack.push(cur);
        	}
		}
		else
		{
			outputExp.push(0-(0-cur));
		}
        t1 = inputExp.length;
	}

    var t4 = outputStack.length;
	if(t4 > 0){
        var b1 = outputStack[t4 - 1];
        if(b1 == ')' || b1 == '(') {
            console.log("error: unmatched ()");
            process.exit(-1);
        }
        var t5 = outputStack.length;
        while(t5 > 0){
            outputExp.push(outputStack.pop());
            t5 = outputStack.length;
        }
    }
    return outputExp;
}

function evalRpn(rpnQueue){
    var outputStack = [];
    var t6 = rpnQueue.length;
    while(t6 > 0){
        var cur = rpnQueue.shift();

        if(isOperator(cur) == false){
            outputStack.push(cur);
        }else{
            var t7 = outputStack.length;
            if(t7 < 2){
                console.log("invalid stack length");
                process.exit(-1);
            }
            var sec = outputStack.pop();
            var fir = outputStack.pop();

            outputStack.push(getResult(fir, sec, cur));
        }
        t6 = rpnQueue.length;
    }

    var t8 = outputStack.length;
    if(t8 != 1){
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
    if (op == '+') {
        return a + b;
    } else if (op == '-') {
        return a - b;
    } else if (op == '*') {
        return a * b;
    } else {
        if(b!=0) {
            return a/b;
        } else {
            console.log("invalid divisor");
            process.exit(-1);
        }
    }
}

console.log(calculate('1 + 2'));
console.log(calculate('6 / 3'));
console.log(calculate('5 / 3'));
console.log(calculate('5 * 3'));
console.log(calculate('5 - 3'));
console.log(calculate('1 + 2 * (3 - 4) / 5+6/5'));
console.log(calculate('( 1 + 2 ) * (( 3 - 4 ) / 5)'));

process.exit(0);