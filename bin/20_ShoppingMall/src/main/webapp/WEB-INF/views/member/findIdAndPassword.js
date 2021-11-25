/**
 * id, password찾기 관련 자바 스크립트 함수
 */

function find_id_form() {
var url ="find_id_form"; // 콘트롤러에서 jsp를 윈도우에서 뜨게 지정해줌
	
	window.open(url, "_blank_1" , 
	"toolbar=no, menubar=no, scrollbars=yes, resizable = no, width = 550, height = 500,"+
	"top=300, left=300");
}
//아이디를 찾기 위해 find_id URL요청 전송
//이름, 이메일이 입력되어있는지 확인
function findMemberId() {
	//이름과 이메일 입력 확인
	if($("#name").val() == ""){
		alert("이름을 입력해 주세요!");
		$("#name").focus();
	}else if($("email").val() == ""){
		alert("이메일을 입력해주세요");
		$("#email").focus();
	}else{
		$("#findId").attr("action", "find_id").submit();
	}
}

//아이디, 이름 , 비밀 번호를 입력하여 비밀번호 찾기

function findPassword() {
	if($("#id2").val() == ""){
		alert("아이디를 입력해 주세요!");
		$("#id2").focus();
	}else if($("#name2").val() == ""){
		alert("이름을 입력해 주세요!");
		$("#name2").focus();
	}else if($("#email2").val() == ""){
		alert("이메일을 입력해주세요");
		$("#email2").focus();
	}else{
		$("#findPW").attr("action", "find_password").submit();
	}
}

//암호 변경을 위한 입력 검증 및 요청 처리

function changePassword() {
	//비밀번호와 비밀번호 입력 확인
	if($("#pwd").val() == ""){
		alert("비밀번호를 입력하세요");
		$("#pwd").focus();
	}else if($("#pwd").val() != $("#pwdcheck").val()){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pwd").focus();
	}else{
		$("#pwd_form").attr("action", "change_password").submit();
	}
}