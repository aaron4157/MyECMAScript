<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/MyECMAScript/js/myutility.js"></script>
<script type="text/javascript">
var mylat, mylng;

$(function(){
	//alert(new Date());
	//var dump = ${data};
	
	rendtable(${data}, function(){return true});
	
	$('#refresh').dblclick(function(){		
		document.location.href="/MyECMAScript/ntpubike/json";
	});
	
	$('#distsort').click(function(){
		$('tr').remove('.dynamic');		
		rendtable(${data}, isDistrict);
	});
	
	$('#sitesort').click(function(){
		mylat = parseFloat($('#mylat').val())*Math.PI/180;
		mylng = parseFloat($('#mylng').val())*Math.PI/180;
		$('tr').remove('.dynamic');		
		rendtable(${data}, isNearby);
	});
});
</script>
<style type="text/css">
	.dynamic:hover {
		background-color: yellow;
	}
</style>


<title>YouBike 即時資訊</title>
</head>
<body>
	<h1>新北市 YouBike 動態查詢</h1>
	
	<div>
	<button type="button" id="refresh">雙擊更新</button><br>
	<label for="mydist">選擇區域</label>
	<input type="text" id="mydist" value="板橋區"><br>
	<button type="button" id="distsort">定位A</button>
	</div>
	<div>
	<label for="mylat">我的位置：緯度N</label>
	<input type="number" id="mylat" value="25.0167"><br>
	<label for="mylong">我的位置：經度E</label>
	<input type="number" id="mylng" value="121.4500"><br>
	<button type="button" id="sitesort">定位B</button>
	</div>
	 	
	<table border='1'>
		<thead></thead>
		<tbody>
		<tr>
			<td>站點編號</td>
			<td>站點名稱</td>
			<td>車輛數</td>
			<td>空位數</td>
			<td>站點行政區</td>
			<td>站點街道名</td>
			<td>回報時間</td>
		</tr>
		</tbody>
	</table>
</body>
</html>