function funcGen(i)
{
    var x=function()
    {
        console.log(i);
    };
    return x;
}
for (var i=0;i<5;i++)
    setTimeout(function()
    {
        console.log(i);
    },i*1000);
for (var i=0;i<5;i++)
    setTimeout(funcGen(i),i*1000);
