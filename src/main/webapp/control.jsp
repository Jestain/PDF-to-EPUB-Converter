<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/* function test() {
	url = "send";
	xmlHttpRequest.onreadystatechange = callback;  
  	xmlHttpRequest.open("POST", url, true);  
  	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        xmlHttpRequest.send("param=test");
        } */


}
</script>
</head>
<body>
<script type="text/javascript">
function test() {
    var xhr = new XMLHttpRequest()
    xhr.open('POST', 'send') // 设置请求行
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
   // xhr.send('username=zhangsan&password=123&&this.data1') // 以 urlencoded 格式设置请求体
 	xhr.send('command=convert&fileName=computer');
    xhr.onload = function () {
        if (xhr.readyState !== 4) return
        console.log(xhr.responseText)

    }
}
</script>
	<button onclick="test()">点击</button>
</body>
</html>