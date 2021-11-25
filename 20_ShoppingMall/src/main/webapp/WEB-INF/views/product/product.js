/**
 * 
 */
function go_cart() {
	
	if($("#quantity").val() ==""){//quantity 입력 필드에 같이 있는 확인
		alert("수량을 입력해 주세요");//값이 없으면 alert 출력
		$("#quantity").foucus();
	}else{
		//값이 있으면 url => "cart_insert" submit
		$("#theform").attr("action", "cart_insert").submit(); //POST 방식으로 전달
	}
}