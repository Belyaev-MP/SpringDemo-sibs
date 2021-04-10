function doFilter(inputId) {
	let val = document.getElementById(inputId).value;
	
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/ajax/car-filter');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState != 4) {
			return;
		}
		
		if (xhr.status != 200) {
			alert('AJAX request error');
		} else {
			let response = xhr.responseText;
			
			if (response.length > 0) {
				fillTable(JSON.parse(response));
			}
		}
	}
	
	xhr.send("q=" + val);
}

function fillTable(data) {
	document.querySelectorAll('#carList tr').forEach(function(el,i){
		if (!el.classList.contains('column-header')) {
			el.remove();
		}
	});
	
	let table = document.getElementById('carList');
	
	data.forEach(function(el, i){
		let tr = document.createElement('tr');
		
		
		tr.appendChild(createCell(el.brand.name));
		tr.appendChild(createCell(el.name));
		tr.appendChild(createCell(el.productionDateFormat));
		tr.appendChild(createCell(el.price));
		
		table.appendChild(tr);
	});
	
}


function createCell(name) {
	let td = document.createElement('td');
	td.innerText = name;
	return td;
}