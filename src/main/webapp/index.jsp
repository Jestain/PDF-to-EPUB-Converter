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
		var url="showProgress.jsp";									//��������ַ
		var param ="nocache="+new Date().getTime();//����ÿ������URL��������ͬ �������ϴ�ʱ����������
		request=httpRequest("post",url,true,callbackFunc,param);	//�������󷽷� 		
}
//Ajax�ص�����
function callbackFunc(){
	if( request.readyState==4 ){		 //�ж���Ӧ�Ƿ���� 
    	 	if( request.status == 200 ){  //�ж���Ӧ�Ƿ�ɹ�
    	 	
        		var h = request.responseText;//��÷��ص���Ӧ���ݣ�������λ�ϴ����Ȱٷֱ�
  				h=h.replace(/\s/g,"");	//ȥ���ַ����е�Unicode�հ׷�
  			/* 	alert(h); */
				document.getElementById("progressPercent").style.display="";	//��ʾ�ٷֱ�	
				document.getElementById("progressPercent").innerHTML=h+"%";		//��ʾ��ɵİٷֱ�
				document.getElementById("progressBar").style.display="block";	//��ʾ������
				document.getElementById("imgProgress").width=h*(235/100);		//��ʾ��ɵĽ���
		}
	}
}
function f2(){
	alert("������ʾ");
}

</script>
</head>
<body>
	<form  action="upload" enctype="multipart/form-data" method="post" >
	
        �ϴ��ļ�1��<input  type="file" name="file1" multiple="multiple" onchange="myFunction(this.form)"><br/>
        <div id="progressBar" alian="left">
        <img id="imgProgress" width="0" height="13" src="img/progress.png">
        </div>
        <span id="progressPercent" style="width=40 display=none">0%</span>
       	<!-- <button type="button" onclick="myFunction(this.form)">��ʼ</button> -->
    </form>
   	<button onclick="myFunction(this.form)">���</button>
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