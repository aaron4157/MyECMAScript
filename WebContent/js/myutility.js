//This script is written with pure JS library, no additives.
 
function greet(){
	alert("Hello, Java script!");
}

function jsonrequest(url,cFunction){	
	var request = new XMLHttpRequest();
	request.responseType = "json";
	request.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      cFunction(this); //callback
	    }
	  };
  	//asychronous request
	request.open("GET", url, true);  
	//activation
	request.send();
}

//A simple output method

function rendata(a, jsons){
	var item, x='content: ';
	
	for(item in jsons){
		x+=JSON.stringify(jsons[item])+'<br>';
	}
	document.getElementById(a).innerHTML = x;
}

//Output bike data as table
 
function rendtable(jsons, screen){
	

	var tableItem = document.querySelector('table tbody');	//css selector not for IE8-
	//var tableItem = document.getElementsByTagName('table')[0];
	var elem;		
	
	for(elem in jsons){
		//篩選資料
		if(screen(jsons[elem])){			
		
		//產生資料列元素
		var row = document.createElement('tr');
		var sno = document.createElement('td');
		var sna = document.createElement('td');
		var sbi = document.createElement('td');
		var bemp = document.createElement('td');		
		var sarea = document.createElement('td');
		var ar = document.createElement('td');				
		var mday = document.createElement('td');
		//填入資料
		sno.innerHTML = jsons[elem].sno;		
		sna.innerHTML = jsons[elem].sna;
		sbi.innerHTML = jsons[elem].sbi;
		bemp.innerHTML = jsons[elem].bemp;
		sarea.innerHTML = jsons[elem].sarea;
		ar.innerHTML = jsons[elem].ar;
		mday.innerHTML = jsons[elem].mday;
		//排版
		row.classList.add('dynamic');	
		row.appendChild(sno);
		row.appendChild(sna);
		row.appendChild(sbi);
		row.appendChild(bemp);
		row.appendChild(sarea);
		row.appendChild(ar);
		row.appendChild(mday);
		
			tableItem.appendChild(row);
		}
	}
}

function cleantable(){
	var tbItem = document.querySelector('table');
	document.removeChild(tbItem);//node incorrect
}

//district filter

function isDistrict(item){
	return item.sarea === document.getElementById('mydist').value;
}

//distance filer

function isNearby(item){
	//convert 1km into angle in rad, then square it
	//this approximattion underscores real (arc) distance > 10km
	const farthest = 1*1/6371/6371;
	var lng1 = parseFloat(item.lng)*Math.PI/180, lat1 = parseFloat(item.lat)*Math.PI/180;
	var lng0 = this.mylng, lat0 = this.mylat;	
	var dlng = lng1 - lng0, dlat = lat1 - lat0;
	var seperaxn = dlng * dlng + Math.cos(lat0) * Math.cos(lat0) * dlat * dlat ;
	return seperaxn < farthest ;
}

