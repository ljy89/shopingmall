/**
 * 
 */
//계약서 동의 여부 확인
function go_next() {
	if($('.agree')[0].checked == true){ //도읭함의 체크가 되어있으면 회원가입을 요청한다
		$('#join').attr('action', 'join_form').submit();
		
	}else if($('.agree')[1].checked == true){
		alert("약관에 동의 해 주셔야 합니다")
	}
}
//아이디 중복 확인 화면 출력
function idcheck() {
	if($('#id').val() ==""){
		alert("아이디를 입력해 주세요");
		$('#id').focus();
		
		return; 
	}
	// 아이디 중복체크를 위한 윈도우 open 요청
	var url="id_check_form?id=" + $('#id').val()
	
	window.open(url, "_blank_1" , 
			"toolbar=no, menubar=no, scrollbars=no, resizable = yes, width = 350, height = 200");
	
}
//사용 가능한 아이디를 사용
function idok() {
	$("#theform").attr("action", "id_check_confirmed").submit();
	
}

//필수입력 체크
//action url은 조인
function go_save() {
	if($("#id").val() == ""){
		alert("아이디를 입력해 주세요");
		$("#id").focus();
	}else if($("#id").val() != $("#reid").val()){
		alert("아이디를 중복체크 해주세요");
		$('#id').focus();
	}else if($("#pwd").val() ==""){
		alert("비밀번호를 입력해 주세요");
		$("#pwd").focus();
		
	}else if($("#pwdCheck").val() ==""){
		alert("비밀번호 확인을 입력해 주세요");
		$("#pwdCheck").focus();
		
	}else if($("#pwd").val() !=$("#pwdCheck").val()){
		alert("비밀번호가 일치하지 않습니다");
		$("#pwd").focus();
		
	}else if($("#name").val() ==""){
		alert("이름을 입력해 주세요");
		$("#name").focus();
		
	}else{
		$("#join").attr("action", "join").submit();
		
	}
}


//우편번호 찾기 창을 오픈 합니다.

function post_zip() {
	var url ="find_zip_num"; // 콘트롤러에서 jsp를 윈도우에서 뜨게 지정해줌
	
	window.open(url, "_blank_1" , 
	"toolbar=no, menubar=no, scrollbars=no, resizable = yes, width = 500, height = 350");
	
}