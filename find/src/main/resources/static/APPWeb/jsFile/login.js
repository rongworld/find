// JavaScript Document

//适配不同尺寸的移动端//
function setHtmlSize(){
	document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px'; 
}

//创建XHR对象
function createXHR()
{
	if(typeof XMLHttpRequest != "undefined")
	{
		return new XMLHttpRequest();
	}
	else if(typeof ActiveXObject != "undefined")
	{
		if(typeof arguments.callee.activeXString != "undefined")
		{
			var version = ["MSXML2.XMLHttp.6.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp"],i,len;
			for(i=0,len=version.length;i<len;i++)
			{
				try
				{
					new AxtiveXObject(version[i]);
					arguments.callee.activeXString = version[i];
					break;
				}
				catch(e)
				{
				}
			}
		}
		return new AxtiveXObject(arguments.callee.activeXString);
	}
	else
	{
		throw new Error("No XHR object available");
	}
}

//获取登录信息并判断输入正确与否
function getMessage(Message){
	var numID = document.getElementById("num");
	var passwordID = document.getElementById("password");
	Message.username=numID.value;
	Message.password=passwordID.value;
	if(Message.username==""||Message.username.length!=10||Message.password==""){
		document.getElementById("ErrorTip").style.display="block";
		document.getElementById("ErrorTip").innerHTML="学号或密码错误！";
		return false;
	}
	else{
		return true;
	}
}

//移除错误提示框
function deleteTipMessage(){
	document.getElementById("ErrorTip").style.display="none";
}

//在localstorage中添加信息
function addMessage(message){
	if(!window.localStorage){
		alert("no support to localStorage");
		return false;
	}
	else{
		var storage = window.localStorage;
		storage.a = message;	
	}
}

//从localstorage中获取信息
function getLocalMessage(){
	if(!window.localStorage){
		return false;
	}
	else{
		alert(window.localStorage.a);
	}
}

//发送信息函数
function sendMessage(Message,url){
	var jsonMessage =JSON.stringify(Message);
	alert(jsonMessage);
	var xhr= new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status >= 200 && xhr.status <300 || xhr.status == 304){
				console.log(xhr.responseText);
			}
			else{
				alert("Request is fail:"+xhr.status);
			}
		}
	};
	xhr.open('post',url,true);
	xhr.send(jsonMessage);
	alert(xhr.status);
	var msg = JSON.parse(xhr.responseText);
	//addMessage(msg.token);
	//getLocalMessage();
}
		

//将登录信息发送给服务器
function sendLoginMessage(){
	var Message = {
	username:'',
	password:''
};	
	if(getMessage(Message)){
		sendMessage(Message,"http://localhost:8080/api/token");
	}
	else{
		setTimeout("deleteTipMessage()",1000);
	}
}

