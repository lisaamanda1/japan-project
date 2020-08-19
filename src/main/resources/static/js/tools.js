/**
 * 字符串格式化
 */
String.prototype.format = function()
{
    var args = arguments;
    return this.replace(/\{(\d+)\}/g,                
        function(m,i){
            return args[i];
        });
}

function loadHeader() {
	$(".header").load("/header/header.html");
}