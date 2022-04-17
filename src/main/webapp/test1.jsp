<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html charset=gb2312">
    <title>SmartPDF</title>
    <script language="javascript" src="js/request.js"></script>
    <script language="javascript">
    
       

    </script>

     <style>
        #inputback-ground {
            position: absolute;
            left: 250px;
            top: 300px;
            text-align: center;
            width: 950px;
            height: 220px;
            display: inline-block;
            padding: 3px 5px;
            overflow: hidden;
            color: #fff;
            background: rgba(207, 34, 12, 0.99);
            border: 15px solid rgba(253, 33, 12, 0.99);
            font-size: x-large;
            /*            border: 3px;
            solid-color: bisque;*/
        }

        #inputback-ground input {

            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            outline: none;
            background-color: transparent;
            filter: alpha(opacity=0);
            -moz-opacity: 0;
            -khtml-opacity: 0;
            opacity: 0;

        }

        .button1 {
            position: absolute;
            top: -1px;
            right: 20%;
            background-color: transparent;
            overflow: visible;
            padding: 0;
        }

        .button2 {
            position: absolute;
            background-color: transparent;
            overflow: visible;
            right: 15%;
        }

        .button3 {
            position: absolute;
            top:-1px;
            background-color: transparent;
            overflow: visible;
            right: 10%;
        }

        #fileList-ul {
            position: relative;
            top: 600px;
        }

        ul, li {
            list-style: none;
        }

        #fileList-ul {
            left: 150px;
            width: 1040px;
        }

        /*        #fileList-ul:hover{
                    background: #ffaf6a;
                }*/
        #formstyle {
            position: relative;
            left: 400px;
            top: 120px;
            text-align: center;
            border: 2px;
            solid-color: beige;
            background: white;
            color: black;;
            width: 190px;
            height: 70px;
        }

        #image {
            position: relative;
            top: 10px;
            left: -10px;
        }

        .fileLi {
            left: 300px;
        }

        #title-image {
            width: 100%;
            position: absolute;
            top:-50px;
            border: 2px;
            solid-color: darkgrey;
        }

        a {
            
            color: black;
        }
        
        #github:hover {
            color: #60F9D4;
        }

        a:hover {
            color: #91BFD6;
        }
        .bar{
        	background-color:#91BFD6;
        }
        #imformation{
            position: relative ;
            top:800px; left:0% ;
            font-weight: normal;
        }
        #div1 {
            position: absolute;
            top: -40px;
            margin-top: 100px;
            width: 100%;
            border-bottom: 2px solid darkgrey;
        }
    </style>
    
    
    <script crossorigin="anonymous" integrity="sha512-n/4gHW3atM3QqRcbCn6ewmpxcLAHGaDjpEBu4xZd47N0W2oQ+6q7oc3PXstrJYXcbNU1OHdQ1T7pAP+gi5Yu8g==" src="https://lib.baomitu.com/jquery/3.6.0/jquery.js"></script>
</head>
<body>
<!-- <span id='progressPercent' style='width=40 display=none'>0%</span> -->
<div> <img src="img/image33.png" style="width: 40px;height: 40px;position: absolute;top: 100px;left: 32%"><h1 style="position: absolute ;top:75px;left: 35%">电子图书格式自动转换工具</h1></div>
<div id="web_content">
    <div id="login_background" alt="图片路径不正确"></div>
</div>


<div> <img src="img/image33.png" style="width: 40px;height: 40px;position: absolute;top: 100px;left: 32%"><h1 style="position: absolute ;top:75px;left: 35%">电子图书格式自动转换工具</h1></div>
<div id="web_content">
    <div id="login_background" alt="图片路径不正确"></div>
</div>
<div id="inputback-ground" >
    <div id="formstyle">
        <img src="img/image17.png" id="image">
        <span style="position: relative;left: -3%">选择文件<br></span>
        <span style="position: relative;top:20px;font-size: 15px;color: white">将文件拖拽到此处</span>
    </div>
    <img src="img/image41.png"style="position: relative;top: -50px;left: 20px">
    <form action="upload" enctype="multipart/form-data" method="post" id="formmate" >
        <input  type="file" name="file1" multiple="multiple" id="input-button"  onchange="doload()"><br/>
    </form>
</div>

<div id="div1">

</div>
<div id="fileList-ul">
    <ul id="fileList-ul1">
    </ul>
</div>
<div id="title-image">
    <img src="img/image24.png" style="height: 40px;width: 60px;position:relative;top: 60px;left: 60px">
    <span></span>
    <span style="position: absolute;left: 150px;top:70px"> <a href="#d1" style="text-decoration: none"> 项目简介</a></span>
    <span style="position: absolute; left: 250px;top:70px"><a href="#d2" style="text-decoration: none;">项目背景</a></span>
    <span style="position: absolute; left: 350px;top:70px"><a href="#d3" style="text-decoration: none;">主要功能</a></span>
    <span style="position: absolute; left: 450px;top:70px"><a href="#d4" style="text-decoration: none;">数据安全</a></span>
    <span style="position: absolute; left: 550px;top:70px"><a href="#d5" style="text-decoration: none;">通用设备</a></span>
    <span style="position: absolute; left: 650px;top:70px"><a href="#d6" style="text-decoration: none;">高质量</a></span>
    <span style="position: absolute; left: 750px;top:70px"><a href="#d7" style="text-decoration: none;">运行效果</a></span>
    <span style="position: absolute; left: 850px;top:70px"><a href="#d8" style="text-decoration: none;">源码展示</a></span>
</div>
<div style="position: absolute;top:170px;left: 250px; height: 130px; width: 990px;background-color:#E9E9E9;text-align:center">
	<br>
	<span style="border:1px solid green; color:green" >EPUB</span>
	<br>
	<span>电子出版物</span>
	<br>
	<span>是由国际数字出版论坛制定的格式。它的特点是基于XML和XHTM为基础选择使用CSS样式。这种格式的主要特征</span>
	<br>
	<span>是使用可回流文本（自动换行），这使得其能够让图像适应不同设备的显示器。ePub文件是扩展名为.epub的zip</span>
	<br>
	<span>文档。</span>
</div>
<!-- <div>
    <img src="img/image39.png" style="position: absolute;top:180px;left: 480px; height: 120px; width: 600px">
</div> -->
<div id="imformation">
    <div id="d1" > <span style="position: relative; left: 250px"><h3 class="bar" style="position: relative; color: white; width: 1000px;height: 30px;">项目简介</h3> PDF文本格式到EPUB电子图书格式的自动转换工具，实现PDF到EPUB的批量转换服务；转换时需保留原有PDF文档的排版<br>样式、标题格式和目录格式；转换后的文档支持保留原文件名和重新命名；转换过程有完整日志记录便于查看转换完成进度。<br>通过将PDF的底层文件来实现的格式转换，可以完美兼容移动设备<br>的阅读需求且保证了文件内容一致<br><br><br></span></div>
    <div id="d2" > <span style="position: relative; left: 250px"><h3 class="bar" style="position: relative; color: white;width: 1000px;height: 30px">项目背景</h3> 移动电子设备的迅速普及和日益多样化改变了与生活息息相关的各行各业，人们不再满足于纸质阅读，开始追求更加方便高效的电子书阅读方<br>式。PDF是传统PC阅读模式下常用的文档和图书格式，EPUB是移动阅读常用的图书格式。EPUB格式能够在移动设备上带给读者优质的阅读体<br>验。为了使传统的电子图书更好地满足移动互联时代的阅读需求，出版和阅读服务行业需要研制开发便捷高效的PDF到EPUB的转换工具，快<br>速将海量的PDF图书转换成为EPUB格式。这项工作将极大拓展高校图书馆和公共图书馆等文化单位的阅读服务能力，助力全民阅读活动的推广<br>和持续进行。<br></span></div>
    <div id="d3" > <span style="position: relative; left: 250px"><h3 class="bar" style="position:relative; color: white;width: 1000px;height: 30px">主要功能</h3>  （1）采用B/S架构开发，可批量上传、转换pdf，看查看上传、转换进度，可暂停、继续、取消转换。<br>（2）转换任务需完整完成，提供进度查看和故障查看日志；<br>（3）保留原有PDF文档的排版样式、标题格式和目录格式；<br>（4）可以作为独立工具运行；也可作为服务运行，支持分布式任务调用。<br></span></div>
    <div id="d4" > <span style="position: relative; left: 250px"><h3 class="bar" style="position: relative;  color: white;width: 1000px;height: 30px">数据安全</h3> 确保文档安全是我们的首要任务，服务器端并不会显示文件内容，<br>保证数据处理的全过程安全，数据处理，包括数据的收集、存储、使用、加工、传输、提供、公开等<br>建立和采用的技术和管理的安全保护,任务结束后不会将您的文件保留<br><br><br></span></div>
    <div id="d5" ><span style="position:relative; left: 250px"><h3 class="bar" style="position: relative;  color: white;width: 1000px;height: 30px">通用设备</h3>计算机设备及软件：包括计算机设备、终端设备、移动设备、网络设备、计算机软件等；<br>无需注册或者安装任何软件，在线转换PDF,在线下载可完美适用于各种热门浏览器如Chrome,Edge<br><br><br></span></div>
    <div id="d6" > <span style="position: relative; left: 250px"><h3 class="bar" style="position: relative;color: white;width: 1000px;height: 30px">高质量</h3>建立坐标定位机制，建立独立样式库，缩小样式的控制范围，实现行—样式精准匹配。<br>实现移动端效果一致化，通过建立屏幕框架，动态调整展示效果，满足适配性需求。<br>建立离线式—在线式两实现系统，为单机/其它应用提供高质量转换服务。<br>请您亲自尝试一下吧,转化出的EPUB可完美适用于移动平台，保证内容的准确<br><br><br></span></div>
    <div id="d7" > <span style="position: relative; left: 250px"><h3 class="bar" style="position: relative; color: white;width: 1000px;height: 30px">运行效果</h3><img  src="img/compare.png"><br><br><br></span></div>
    <div id="d8" > <span style="position: relative; left: 250px"><h3 class="bar" style="position: relative; color: white;width: 1000px;height: 30px">github地址</h3>我们在GitHub上创建了与该工程开源代码，用户可点击<a id="github" href="https://github.com/Jestain/PDF-to-EPUB-Convertor/tree/main/version1" style=":hover {color:#0a6aa1} ;text-decoration:none">这里的链接</a>查看详情。<br><br><br></span></div>
    <div style="text-align: center; position: relative;"><h3>给工具评分 <img onclick="score1()" id="score1" style="width: 24px;height: 24px;position: relative;vertical-align: top;;left: 10px" src="img/shoucang1.png"><img onclick="score2()" id="score2" style="width: 24px;height: 24px;position: relative;vertical-align: top;;left: 10px" src="img/shoucang1.png"><img onclick="score3()" id="score3" style="width: 24px;height: 24px;position: relative;vertical-align: top;;left: 10px" src="img/shoucang1.png"><img onclick="score4()" id="score4" style="width: 24px;height: 24px;position: relative;vertical-align: top;;left: 10px" src="img/shoucang1.png"><img onclick="score5()" id="score5" style="width: 24px;height: 24px;position: relative;vertical-align: top;;left: 10px" src="img/shoucang1.png"></h3></div>
    <div id="qqnumber" style="position: relative;text-align: center"> <h4>邮箱地址:175273446@qq.com</h4></div>
</div>
	
	
<script>
var sendFileName=new Array();
var j=0+'j';
var m=0+'m';
var k=0+'k';
var p=0+'p';
var q=0+"spanId"
var r=0+"imgId"
var y=0+"spanIdy"
var z=0+"imgIdz"
var index=0;
var ima=0+"ba"
var imb=0+"bb"
var h2=new Array();
var myVar=new Array();
var myVar2=new Array();
function appendFile() {
	var file=document.getElementById('input-button')
    for(var i=0;i<file.files.length;i++){
        if(file.files[i]==undefined){
            alert('no file');
        }
        else{
        	var fileNameend = file.files[i].name
        	var endwith = fileNameend.substring(fileNameend.length -4, fileNameend.length)
        	if (endwith == '.pdf') {
            sendFileName[index] = fileNameend;
            ifchange[index]=true;
            h2[index]=0;
            var text = "<li  onmouseover='limouseover(this)' onmouseleave='limouseleave(this)'  style='height: 30px;width: 100%; position: relative;left: 6%'; class='fileLi' id="+p+" >" + "<span>" + "<img src='img/image42.png' style='width: 30px; height: 30px'>" + "</span>" + "<span style='vertical-align: top;font-size: 125%'>" + fileNameend + "</span>" + "<span style='position: absolute;left:40%'><img  id="+z+" class='imgProgress' width='0' height='20' src='img/progress.png' style='margin-top:5px;display: none ' ><span id="+y+" class='progressPercent' style='width=40 display=none; vertical-align: top;font-size: 125% ;position: relative; left: 1px;display: none ' >点击转换</span></span> <span  style='position: absolute;left:30%'><img id="+r+" class='imgProgress' width='10' height='20' src='img/progress.png' style='margin-top:5px' ><span id="+q+" class='progressPercent' style='width=40 display=none; vertical-align: top;font-size: 125% ;position: relative; left: 1px' >0%</span></span> <button disabled='true' style='border: none' onclick='senddsort1(this)' class='button1' id=" + j + "> <img id="+ima+" style='width: 28px;height: 32px' src='img/start.png'></button>  <button style='border: none' onclick='sendsort2(this)' class='button2' id=" + k + "> <img style='width: 25px;height: 25px' src='img/delete1.png'></button><button disabled='true' style='border: none' onclick='sendsort3(this)' class='button3' id=" + m + "> <img id="+imb+" style='width: 34px;height: 28px' src='img/download.png'></button>" + "</li>";
            $("#fileList-ul1").append(text).hide().fadeIn(200);
            $(".fileLi").css({
                "border": "1px solid rgba(0,0,0,0.09)",
                "margin": "2px"
            })
           
            $("#"+index+"spanId").bind('DOMNodeInserted',function(){
            	
                var sId=parseInt(this.id)+'spanId'
                var buttonid=parseInt(this.id)+'j'
                var imageid=parseInt(this.id)+'imgId'
                var imageidz=parseInt(this.id)+'imgIdz'
                var spanidy=parseInt(this.id)+'spanIdy'
                var im1=parseInt(this.id)+'ba'
                if($("#"+this.id).text()=='100%'){
                	
                	document.getElementById(buttonid).disabled=false;
                    $("#"+im1).attr('src','img/start1.png')
                    $("#"+imageid).hide()
                    $("#"+sId).text("上传成功").hide().fadeIn(200);
                    $("#"+imageidz).fadeIn(200);
                    $("#"+spanidy).fadeIn(200);
                }
            });
            $("#"+index+"spanIdy").bind('DOMNodeInserted',function(){
            	var im1=parseInt(this.id)+'ba'
                var sId=parseInt(this.id)+'spanId'
                var buttonid=parseInt(this.id)+'m'
                var buttonid1=parseInt(this.id)+'j'
                var imabid=parseInt(this.id)+'bb'
                
                if($("#"+this.id).text()=='100%'){
                	 $("#"+im1).attr('src','img/start.png')
                	 $("#"+buttonid1).attr('disabled',true)
                    $("#"+buttonid).attr('disabled',false)
                  $("#"+imabid).attr('src','img/download1.png')
                    $("#"+sId).text("转换成功").hide().fadeIn(200);
                }
            });
            var tempj=parseInt(j)
            tempj=tempj+1;
            j=tempj+'j';
            var tempm=parseInt(m)
            tempm=tempm+1;
            m=tempm+'m';
            var tempk=parseInt(k)
            tempk=tempk+1;
            k=tempk+'k';
            var tempp=parseInt(p)
            tempp=tempp+1;
            p=tempp+'p';
            var tempq=parseInt(q)
            tempq=tempq+1;
            q=tempq+'spanId';
            var tempr=parseInt(r)
            tempr=tempr+1;
            r=tempr+'imgId';
            var tempy=parseInt(y)
            tempy=tempy+1;
            y=tempy+'spanIdy';
            var tempz=parseInt(z)
            tempz=tempz+1;
            z=tempz+'imgIdz';
            var tempba=parseInt(ima)
            tempba=tempba+1;
            ima=tempba+'ba';
            var tempbb=parseInt(imb)
            tempbb=tempbb+1;
            imb=tempbb+'bb';
            index++;
        }
    else
        {
            alert('选取文件格式不对');
        }
        }
        
        

    }
}

var a=new Array(); 

var ifchange=new Array();





function senddsort1(e) {
	var numOr=e.id;
	var changes=parseInt(numOr);
    if (ifchange[changes]) {
    	
    	begin(e);
        changebutton1(e);
        ifchange[changes] = !ifchange[changes];
    } else {
    	stop(e);
        changebutton2(e)
        ifchange[changes] = !ifchange[changes];
    }
}

function begin(e) {
	
    var idbutton1 = e.id;
    
    var indexbutton1 = parseInt(idbutton1);
    var xhr = new XMLHttpRequest()
    xhr.open('POST', 'send') // 设置请求行
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    // xhr.send('username=zhangsan&password=123&&this.data1') // 以 urlencoded 格式设置请求体
    xhr.send('command=convert&sendFileName=' + sendFileName[indexbutton1]+'&order='+indexbutton1);
    myVar2[indexbutton1] = setInterval(function(){	   
 	   alertFunc2(sendFileName[indexbutton1],indexbutton1,myVar2[indexbutton1]);}, 10);
    
    xhr.onload = function () {
        if (xhr.readyState !== 4) return
        console.log(xhr.responseText)
    }
}

function alertFunc2(fName,order,myVar) {
    getProgress2(fName,order);
    if(h2[order]==100){
    	clearInterval(myVar);
    }
}

function getProgress2(fName,order){
    //var url="showProgress.jsp";									//服务器地址
    //var param ="nocache="+new Date().getTime()+"&key=a"+fileOrder+"imgId";//保持每次请求URL参数都不同 ，避免上传时进度条不动
    //request=httpRequest("post",url,true,callbackFunc(fileOrder),param);	//调用请求方法
	   var formData=new FormData();
	   formData.append('order', order);
	   
	   var xhr = new XMLHttpRequest();
	   　　xhr.open('POST',"showConvert.jsp");
	   xhr.responseType = 'text';
	   　　// 定义上传完成后的回调函数
	   　　xhr.onload = function () {
	   　　　　if (xhr.readyState === xhr.DONE) {
	        if (xhr.status === 200) {
	            //console.log("xhr.response:"+xhr.response);
	            //console.log("xhr.responseText"+xhr.responseText);
	            var text=xhr.responseText;
	            console.log("text:"+text+"fileOrder"+fileOrder);
	            callbackFunc2(order,text);
	        }
	    }
	   　　};
	   　　xhr.send(formData);
	   
}
function callbackFunc2(fileOrder,responText){
    
	console.log(h2[fileOrder]);
   h2[fileOrder] = responText;//获得返回的响应数据，该数据位上传进度百分比
   h2[fileOrder]=h2[fileOrder].replace(/\s/g,"");	//去除字符串中的Unicode空白符
  	console.log(h2[fileOrder]);
   document.getElementById(fileOrder+"spanIdy").style.display="";	//显示百分比
   document.getElementById(fileOrder+"spanIdy").innerHTML=h2[fileOrder]+"%";		//显示完成的百分比
 /*   document.getElementById("progressBar").style.display="block"; */	//显示进度条
   document.getElementById(fileOrder+"imgIdz").width=h2[fileOrder]*(235/100);		//显示完成的进度


}


function stop(e) {
    var idbutton2 = e.id;
    var indexbutton2 = parseInt(idbutton2);
    var xhr = new XMLHttpRequest()
    xhr.open('POST', 'send') // 设置请求行
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    // xhr.send('username=zhangsan&password=123&&this.data1') // 以 urlencoded 格式设置请求体
    xhr.send('command=interrupt&sendFileName=' + sendFileName[indexbutton2]+'&order='+indexbutton2);
    clearInterval(myVar2[indexbutton2]);
   
    xhr.onload = function () {
        if (xhr.readyState !== 4) return
        console.log(xhr.responseText)
    }
}

function kill(e) {
    var idbutton3 = e.id;
    var indexbutton3 = parseInt(idbutton3);
    var xhr = new XMLHttpRequest()
    xhr.open('POST', 'send') // 设置请求行
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    // xhr.send('username=zhangsan&password=123&&this.data1') // 以 urlencoded 格式设置请求体
    xhr.send('command=delete&sendFileName=' + sendFileName[indexbutton3]+'&order='+indexbutton3);
    xhr.onload = function () {
        if (xhr.readyState !== 4) return
        console.log(xhr.responseText)
    }
    clearInterval(myVar2[indexbutton3]);
    clearInterval(myVar[indexbutton3]);
}



	function sendsort2(e) {
        kill(e)
        var idnumber=parseInt(e.id);
        var realid=idnumber+'p'
        $("#"+realid).remove();
        clearInterval(myVar2[indexbutton1]);
    }

   

	  function sendsort3(e) {
	        var idbutton4 = e.id;
	        var indexbutton4 = parseInt(idbutton4);
	        window.location.href="down?sendFileName="+sendFileName[indexbutton4];
	    }
    
</script>

<script>
function changebutton1(e) {
    var spanId1=parseInt(e.id)+'spanId';
    var imaid=parseInt(e.id)+'ba'
    $("#"+imaid).attr("src",'img/stop1.png')
    $('#'+spanId1).text("转换中")

}
function changebutton2(e) {
	var imaid=parseInt(e.id)+'ba'
    var spanId1=parseInt(e.id)+'spanId';
	$("#"+imaid).attr("src",'img/start1.png')
		$('#'+spanId1).text("暂停中")
}
</script>
<script>
    function limouseover(e) {
        $("#"+e.id).css("background-color",'rgba(255,219,35,0.08)')
    }
    function limouseleave(e) {
        $("#"+e.id).css("background-color",'white')
    }
</script>



<script>
    function canNotClick(e) {
        document.getElementById(e.id).disabled=ture;
    }
    
</script>
    <script>
    var request = false;
    var h=new Array();
    function getObjectURL(file) {
        var url = null;
        if (window.createObjcectURL != undefined) {
            url = window.createOjcectURL(file);
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file);
        }
        
        return url;
    }
    var fileOrder=0;
    function doload(){
    	var formData;
    	appendFile();
    	for(var i=0;i<document.getElementById('input-button').files.length;i++){
    	formData = new FormData();
		var url=getObjectURL(document.getElementById('input-button').files[i]);
   　　// 建立一个upload表单项，值为上传的文件
     if(url.substring(0,4)=="blob"){
    	 
     	
   　　formData.append('input-button', document.getElementById('input-button').files[i]);
   　　var xhr = new XMLHttpRequest();
   　　xhr.open('POST',"upload");
   　　// 定义上传完成后的回调函数
   　　xhr.onload = function () {
   　　　　if (xhr.status === 200) {
   　　　　　　console.log('上传成功');
   　　　　} else {
   　　　　　　console.log('出错了');
   　　　　}
   　　};
   console.log("一起上传时这次文件的名字："+document.getElementById('input-button').files[i].name);
   console.log("一起上传时这次文件的序号："+fileOrder);
   　　xhr.send(formData);
   		sleep(100);
   	   myFunction(fileOrder);
   		fileOrder++;
   	   
   	  
       }
    	}
    	
    }
    function sleep(n) {
        var start = new Date().getTime();
        //  console.log('休眠前：' + start);
        while (true) {
            if (new Date().getTime() - start > n) {
                break;
            }
        }
        // console.log('休眠后：' + new Date().getTime());
    }
	function myFunction(fileOrderOut) {
       	var fileOrder=fileOrderOut;


       	console.log("h的长度："+h.length)
 	   h[h.length]=0;
       myVar[fileOrder] = setInterval(function(){
    	   
    	   alertFunc(fileOrder,myVar[fileOrder]);}, 10);
           
       }
       function alertFunc(fileOrder,myVar) {
           getProgress(fileOrder);
           if(h[fileOrder]==100){
           	clearInterval(myVar);
           }
       }
       function getProgress(fileOrder){
           //var url="showProgress.jsp";									//服务器地址
           //var param ="nocache="+new Date().getTime()+"&key=a"+fileOrder+"imgId";//保持每次请求URL参数都不同 ，避免上传时进度条不动
           //request=httpRequest("post",url,true,callbackFunc(fileOrder),param);	//调用请求方法
    	   var formData=new FormData();
    	   formData.append('key', fileOrder);
    	   
    	   var xhr = new XMLHttpRequest();
    	   　　xhr.open('POST',"showProgress.jsp");
    	   xhr.responseType = 'text';
    	   　　// 定义上传完成后的回调函数
    	   　　xhr.onload = function () {
    	   　　　　if (xhr.readyState === xhr.DONE) {
    	        if (xhr.status === 200) {
    	            //console.log("xhr.response:"+xhr.response);
    	            //console.log("xhr.responseText"+xhr.responseText);
    	            var text=xhr.responseText;
    	            console.log("绑定时的进度和序号-text:"+text+"fileOrder:"+fileOrder);
    	            callbackFunc(fileOrder,text);
    	        }
    	    }
    	   　　};
    	   　　xhr.send(formData);
    	   
       }
       //Ajax回调函数
       function callbackFunc(fileOrder,responText){
            
					//console.log(h[fileOrder]);
                   h[fileOrder] = responText;//获得返回的响应数据，该数据位上传进度百分比
                   h[fileOrder]=h[fileOrder].replace(/\s/g,"");	//去除字符串中的Unicode空白符
                  	//console.log(h[fileOrder]);
                   document.getElementById(fileOrder+"spanId").style.display="";	//显示百分比
                   document.getElementById(fileOrder+"spanId").innerHTML=h[fileOrder]+"%";		//显示完成的百分比
                 /*   document.getElementById("progressBar").style.display="block"; */	//显示进度条
                   document.getElementById(fileOrder+"imgId").width=h[fileOrder]*(235/100);		//显示完成的进度
         
           
       }
       

        
    </script>
<script>
    function score1() {
        $("#score1").attr('src',"img/scorefill.png");
    }
    function score2() {
        $("#score2").attr('src',"img/scorefill.png");
    }   function score3() {
        $("#score3").attr('src',"img/scorefill.png");
    }   function score4() {
        $("#score4").attr('src',"img/scorefill.png");
    }   function score5() {
        $("#score5").attr('src',"img/scorefill.png");
    }
</script>
</body>
</html>