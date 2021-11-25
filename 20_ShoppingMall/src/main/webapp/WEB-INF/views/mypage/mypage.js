/**
 * 장바구니 항목 삭제
 */

function go_cart_delete() {
	var count = 0; //count는 삭제할 항목의 개수가 저장된다
	//삭제할 항목이  0또는 하나만 체크되어 있을 경우
	
	/*
	 * if문과 for문의 내용은 삭제할 항목이 체크가 되어있는지 확인
	 * 체크가 하나도 안되어 있으면 삭제 처리 불가하도록 구현
	 * 삭제할 항목은 cseq 배열로 올라간다
	 * 
	 */
	if(document.formm.cseq.length == undefined){ //값 초기화 안된경우 undefined , 값 할당안됨 null
		//cseq가 하나만 체크된 경우
		if(document.formm.cseq.check == true){
			//배열아니라 따로 체크해 삭제
			count ++;
		}
	}
	
	//삭제할 항목이 2개 이상인 경우
	
	for(var i=0; i <document.formm.cseq.length; i++){
		if(document.formm.cseq[i].checked == true){
			count ++;
		}
	}
	
	if(count ==0){
		alert("삭제할 항목을 선택해 주세요");
	}else{
		document.formm.action = "cart_delete";
		document.formm.submit();
	}
}


//장바구니에 저장된 내역을 주문처리 요청
function go_order_insert() {
	$("#theform").attr("action","order_insert" ).submit(); //post 방식으로 넘어간다
	
}