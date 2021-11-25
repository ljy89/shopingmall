/**
 * 
 */

function go_search() {
	$("#prod_form").attr("action", "admin_product_list").submit();
	
}

//상품 전체 조회
function go_total() {
	$("#key").val("");
	$("#prod_form").attr("action", "admin_product_list").submit();
}

//상품등록 페이지 요청
function go_wrt() {	
	$("#prod_form").attr("action", "admin_product_write_form").submit();
}
//판매가(price2) - 원가(price1)를 계산하여 순이익(price3)을 표기
function go_ab() {	
	var a = $("#price2").val();
	var b = $("#price1").val();
	
	$("#price3").val(a-b);
	
	
}
/*
 * 문자열 내에 들어 있는 컴마를 제거
 * 자바 정규표현식 gi : g모든 문서 검색 i  대소문자 무시  /,/를  "" 빈문자열로 대체
 * input 입력 문자열
 */

function remove_comma(input) {
	return input.value.replace(/,/gi, "");
}

//상품등록 폼에 항목이 입려되었는지 확인

function go_save() {
	if($("#kind").val()==""){
		alert("상품종류를 입력하세요");
		$("#kind").focus();
	}else if($("#name").val()==""){
		alert("상품명을 입력하세요");
		$("#name").focus();
	}else if($("#price1").val()==""){
		alert("원가를 입력하세요");
		$("#price1").focus();
	}else if($("#price2").val()==""){
		alert("판매가를 입력하세요");
		$("#price2").focus();
	}else if($("#content").val()==""){
		alert("상품상세 내용을 입력하세요");
		$("#content").focus();
	}else if($("#product_image").val()==""){
		alert("상품상세 내용을 입력하세요");
		$("#product_image").focus();
	}else{
		$("#write_form").attr("action", "admin_product_write").submit();
	}
	
	
}