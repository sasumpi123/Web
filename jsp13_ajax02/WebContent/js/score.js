/*
 * XMLHttpRequest : 서버와 통신을 도와주는 객체. http를 통한 데이터 송수신 지원. (javascript object)
 * 
 * --readyState
 * 	0: uninitialized - 실행(load)되지 않음
 * 	1: loading - 로드 중
 * 	2: loaded - 로드 됨
 * 	3: interactive - 통신 됨
 * 	4: complete - 통신 완료
 * 
 * --status
 * 	200 : 성공
 *  400 : bad request
 *  401 : unauthorized
 *  403 : forbidden
 * 	404 : not found
 *  500 : internal server error
 *  
 *  --encodeURIComponent() : 모든 문자 인코딩
 *    encodeURI() : 주소줄에 사용되는 특수문자는 제외하고 인코딩
 *  --decodeURIComponent() : 모든 문자 디코딩
 *    docodeURI() : 주소줄에 사용되는 툭수문자는 제외하고 디코딩
 *    
 *  --JSON.parse() : String을 JSONObject로 바꿔주는 메소드, JSON String을 JSONObject로 바꿔주기도 함.
 *  
 */


function callback(){
	alert("readyState : " + httpRequest.readyState);
	
	if(httpRequest.readyState==4){
		alert("status : " + httpRequest.status);
		
		if(httpRequest.status==200){
			var obj = JSON.parse(httpRequest.responseText);
			
			document.getElementById("result").innerHTML=decodeURIComponent(obj.name) +" 님의 총점은 " + obj.sum + " 이고, 평균은 " + obj.avg + " 입니다."
		} else {
			alert("통신 실패");
		}
	}
}

function load(){
	var url = getParameterValues();
	
	// 변수의 호이스팅 발생
	httpRequest=new XMLHttpRequest();					// 서버와 통신
	httpRequest.onreadystatechange=callback;			// 처리할 함수 (readystate가 change될 때마다 on, 실행한다)
	httpRequest.open("GET",url,true);					// true : 비동기 / false : 동기 로 요청
	httpRequest.send();									// "GET" : send() / "POST" : send(String) <-보낼 데이터를 넣는다.
	
}

function getParameterValues(){
	var name="name="+encodeURIComponent(document.getElementById("name").value);
	var kor="kor="+document.getElementById("kor").value;
	var eng="eng="+document.getElementById("eng").value;
	var math="math="+document.getElementById("math").value;
	
	var url = "score.cal?"+name+"&"+kor+"&"+eng+"&"+math;
	console.log(url);
	
	return url;
}



