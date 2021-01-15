//This script is written with pure JS library, no additives.
 
function greet(){
	alert("Hello, Java script!");
}

// A standard http request
function jsonrequest(url,cFunction){	
	let request = new XMLHttpRequest();
	request.responseType = "json";
	request.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      cFunction(this); //send xhr object to callback function
	    }
	};
	request.open("GET", url, true);  
	request.send();
}

//A simple output method
function rendata(a, jsons){
	var item, x='content: ';
	
	for(item in jsons){
		x+=JSON.stringify(jsons[item])+'<br>';
	}
	document.getElementById(a).innerHTML = x;
	document.getElementById(a).style = "";
}

//Output bike data as table
function rendtable(jsons, screen){
	
	const tableItem = document.querySelector('table tbody');	//css selector not for IE8-
	//let tableItem = document.getElementsByTagName('table')[0];
	let elem;		
	
	for(elem in jsons){
		//篩選資料
		if(screen(jsons[elem])){			
		
		//產生資料列元素
		let row = document.createElement('tr');
		let sno = document.createElement('td');
		let sna = document.createElement('td');
		let sbi = document.createElement('td');
		let bemp = document.createElement('td');		
		let sarea = document.createElement('td');
		let ar = document.createElement('td');				
		let mday = document.createElement('td');
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

//distance filter
function isNearby(item){
	//convert 1km into angle in rad, then square it
	//this approximattion underscores real (arc) distance > 10km
	const farthest = 1*1/6371/6371;
	let lng1 = parseFloat(item.lng)*Math.PI/180, lat1 = parseFloat(item.lat)*Math.PI/180;
	let lng0 = this.mylng, lat0 = this.mylat;	
	let dlng = lng1 - lng0, dlat = lat1 - lat0;
	let seperaxn = dlng * dlng + Math.cos(lat0) * Math.cos(lat0) * dlat * dlat ;
	return seperaxn < farthest ;
}

// table paginatior
// REF:https://www.cssscript.com/demo/simple-table-paginator-pure-javascript/

