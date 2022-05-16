<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>Insert title here</title>

<script language="javascript" src="js/request.js"></script>
<script language="javascript">
var request = false;
function getProgress(){	
		var url="showProgress.jsp";									//服务器地址
		var param ="nocache="+new Date().getTime();//保持每次请求URL参数都不同 ，避免上传时进度条不动
		request=httpRequest("post",url,true,callbackFunc,param);	//调用请求方法 		
}
//Ajax回调函数
function callbackFunc(){
	if( request.readyState==4 ){		 //判断响应是否完成 
    	 	if( request.status == 200 ){  //判断响应是否成功
    	 	
        		var h = request.responseText;//获得返回的响应数据，该数据位上传进度百分比
  				h=h.replace(/\s/g,"");	//去除字符串中的Unicode空白符
  			/* 	alert(h); */
				document.getElementById("progressPercent").style.display="";	//显示百分比	
				document.getElementById("progressPercent").innerHTML=h+"%";		//显示完成的百分比
				document.getElementById("progressBar").style.display="block";	//显示进度条
				document.getElementById("imgProgress").width=h*(235/100);		//显示完成的进度
		}
	}
}
function f2(){
	alert("不断显示");
}

</script>
</head>
<body>
	<form  action="upload" enctype="multipart/form-data" method="post" >
	
        上传文件1：<input  type="file" name="file1" multiple="multiple" onchange="myFunction(this.form)"><br/>
        <div id="progressBar" alian="left">
        <img id="imgProgress" width="0" height="13" src="img/progress.png">
        </div>
        <span id="progressPercent" style="width=40 display=none">0%</span>
       	<!-- <button type="button" onclick="myFunction(this.form)">开始</button> -->
    </form>
   	<button onclick="myFunction(this.form)">点击</button>
	<script>
			
			var myVar;
			
			function myFunction(form) {
				 form.submit(); 
				myVar = setInterval(alertFunc, 10);
			}
			
			
			function alertFunc() {
			  /*   alert("Hello!");  */
			    getProgress();  
				/*  f2(); */
			}
	</script>
</body>
</html>