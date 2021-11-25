<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	$.ajax({
		type: 'GET', 
		headers :{
			Accept: "application/json; charset=UTF-8",
			"Content-type" : "application/json; charset=UTF-8"
		},
		url: 'sales_record_chart',
		success : function(result){
			//최신 버전 의 구글 코어 차트 패키지 로드해준다
			google.charts.load('current', {'packages':['corechart']});
			
			//차트 API가 로드완료 되면 실행 콜백함수 선언
			 google.charts.setOnLoadCallback(function(){
				 drawChart(result);
			 });
		},
		error : function(){
			alert("Chart drwing error!")
		}
	});
	
	
	function drawChart(result){
		 // 차트 그리는데 사용할 데이터 객체 생성
        var data = new google.visualization.DataTable();
        data.addColumn("string", "pname");
        data.addColumn("number", "quantity");
        
        //controller에서 json type으로 전달된 데이터를 -> js 의array type으로 저장
        var dataArray = [];
        $.each(result, function(i, obj){
        	dataArray.push([obj.pname, obj.quantity]);
        })
        
        // data 객체에 dataArray 의 내용을 저장
        data.addRows(dataArray);
        
        //파이차트 그리기
        var piechart_options = {
        		title : '제품별 판매 실적',
        		width : 300,
        		height : 300
        		
        };
        
        var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));
        //파이차트 그리기
        piechart.draw(data, piechart_options);
        
       
        
        //바차트 그리기
        var barchart_options = {
        		title : '제품별 판매 실적',
        		width : 300,
        		height : 300
        		
        };
        
        var barchart = new google.visualization.BarChart(document.getElementById('barchart_div'));
        //바차트 그리기
        barchart.draw(data, barchart_options);
	}
});
</script>

	<div align="center">
	<h1>제품 판매 실적</h1>
	<table>
		<tr>
			<td><div id="piechart_div" style="border: 1px solid #ccc"></div></td>
			<td><div id="barchart_div" style="border: 1px solid #ccc"></div></td>
		</tr>
	
	</table>
	</div>

<%@ include file="../footer.jsp"%>
