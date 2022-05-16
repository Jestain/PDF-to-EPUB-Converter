/**
 * ����XMLHttpRequest�������������
 * @param reqType���������ͣ�GET��POST��
 * @param url����������ַ
 * @param async���Ƿ��첽����
 * @param resFun����Ӧ�Ļص�����
 * @param parameter :�������
 * @return :XMLHttpRequest����
 */
function httpRequest(reqType,url,async,resFun,parameter){	
	var request = null;
	if( window.XMLHttpRequest ){		//��IE�����������XMLHttpRequest����
		request = new XMLHttpRequest();
	}else if( window.ActiveXObject ){	//IE�����������XMLHttpRequest����
		var arrSignatures = ["Msxml2.XMLHTTP", "Microsoft.XMLHTTP", "Microsoft.XMLHTTP", "MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP"];		
		for( var i = 0; i < arrSignatures.length; i++ ){
			request = new ActiveXObject( arrSignatures[i] );
			if( request || typeof( request ) == "object" )
				break;
		}
	}
	if( request || typeof( request ) == "object" ){
		if(reqType.toLowerCase()=="post"){		//��POST��ʽ�ύ
			request.open(reqType, url, true);	//�򿪷���������
			//����MIME����
			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			request.onreadystatechange = resFun;//���ô�����Ӧ�Ļص�����
			parameter = encodeURI(parameter);	//�������ַ������б���
			request.send(parameter);			//��������
		}
		else{									//��GET��ʽ�ύ
			url = url+"?"+parameter;			//��������URL��GET��ʽ��Ҫ��URL�и����������
			request.open(reqType, url, true);	//�򿪷���������
			request.onreadystatechange = resFun;//��Ӧ�ص�����
			request.send(null);					//��������
		}
	}
	else{
		alert( "���������֧��Ajax��" );
	}	
	return request;
}