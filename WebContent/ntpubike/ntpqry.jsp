<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- included library -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/MyECMAScript/js/table-sortable.js"></script>
<link type="text/css" rel="stylesheet" href="/MyECMAScript/css/styles.css"/>
<link type="text/css" rel="stylesheet" href="/MyECMAScript/css/table-sortable.css"/>
<!-- user defined library -->
<script type="text/javascript" src="/MyECMAScript/js/myutility.js"></script>


<script type="text/javascript">
var mylat, mylng;

$(function(){
	$('#localtime').text(new Date());
	
	// rendtable({data}, function(){return true});
	
	const dataArray = ${data};
	const colA = {
			'sno':"站點編號",
	    	'sna':"站點名稱",
	    	'sbi':"車輛數",
	    	'bemp':"空位數",
	    	'sarea':"站點行政區",
	    	'ar':"站點街道",
	    	'mday':"回報時間"
	    	};
	
	let table = $('#table-sortable').tableSortable({
    data: [],
    columns: {},
    rowsPerPage: 15,
    pagination: true,
    searchField: '#searchField'
	});
	
    //Set new data on table, columns is optional.
    table.setData(dataArray, colA);
	
	$('#refresh').dblclick(function(){		
		document.location.href="/MyECMAScript/ntpubike/json";
	});
	
	$('#distsort').click(function(){
		//$('tr').remove('.dynamic');			
		//rendtable({data}, isDistrict);
		table.setData(dataArray.filter(function(item, index, array){
			return isDistrict(item);
		}), colA);
	});
	
	$('#sitesort').click(function(){
		//mylat = parseFloat($('#mylat').val())*Math.PI/180;
		//mylng = parseFloat($('#mylng').val())*Math.PI/180;
		//$('tr').remove('.dynamic');		
		//rendtable({data}, isNearby);
		table.setData(dataArray.filter(function(item, index, array){
			mylat = parseFloat($('#mylat').val())*Math.PI/180;
			mylng = parseFloat($('#mylng').val())*Math.PI/180;
			return isNearby(item);
		}), colA);
	});
	
	$('#changeRows').on('change', function() {
		  table.updateRowsPerPage(parseInt($(this).val(), 15));
	});
});
</script>
<style type="text/css">
	table, th, td {
	  border: 1px solid black;
	}
</style>


<title>YouBike 即時資訊 </title>
</head>
<body>
	<h1>新北市 YouBike 動態查詢</h1>
	<h3 id="localtime"></h3>
	
	<div class="btn btn1">
	<button type="button" id="refresh">雙擊更新</button><br>
	<label for="mydist">選擇區域</label>
	<input type="text" id="mydist" value="板橋區"><br>
	<button type="button" id="distsort">定位A</button>
	</div>
	<div class="btn btn2 ">
	<label for="mylat">我的位置：緯度N</label>
	<input type="number" id="mylat" value="25.0167"><br>
	<label for="mylong">我的位置：經度E</label>
	<input type="number" id="mylng" value="121.4500"><br>
	<button type="button" id="sitesort">定位B</button>
	</div>
	
	<div class="btn btn3">
	<input type="text" placeholder="我想來點..." id="searchField">
	<span>資料數/分頁</span>
	<select name="rowsPerPage" id="changeRows">
	  <option value="1">1</option>
	  <option value="5">5</option>
	  <option value="10">10</option>
	  <option value="15" selected>15</option>
	</select>
	</div> 	
	<div id="table-sortable">
	
	</div>
</body>
</html>