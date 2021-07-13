
// ES6 class 
class RestHTTP { 

	async delete(url) { 

		const response = await fetch(url, { 
			method: 'DELETE', 
			headers: { 
				'Content-type': 'application/json'
			} 
		}); 

		const resData = 'resource deleted...'; 

		return resData; 
	}
}

function easyHTTP() { 

// Initializing new XMLHttpRequest method. 
this.http = new XMLHttpRequest(); 
} 

// Make an HTTP PUT Request 
easyHTTP.prototype.put = function(url, data, callback) { 

// Open an obejct (POST, PATH, ASYN-TRUE/FALSE) 
this.http.open('PUT', url, true); 

// Set content-type 
this.http.setRequestHeader( 
	'Content-type', 'application/json'); 

// Assigning this to self to have 
// scope of this into the function onload 
let self = this; 

// When response is ready 
this.http.onload = function() { 

	// Callback function (Error, response text) 
	callback(null, self.http.responseText); 
} 

// Since the data is an object so 
// we need to stringify it 
this.http.send(JSON.stringify(data)); 
}
 

function deleteEntry(url,name){
const http = new RestHTTP; 
var urlB="http://localhost:8080/api/entries/";
var r = confirm("Are you sur you want to delete : "+name);
  if (r == true) {
http.delete(urlB+url) 

.then(data => {location.reload();
console.log(data)}) 
.catch(err => console.log(err)); 

}  
}

function updateEntry(event){



var form = document.forms["entryForm"];
//alert(form);
let formData = new FormData( form ) ;

var data = {};
formData.forEach((value, key) => data[key] = value);
var json = JSON.stringify(data);

console.log(data);
console.log(json);

let http = new easyHTTP; 

let id=window.document.getElementById("id").value;


var urlB="http://localhost:8080/api/entries/"+id;
event.preventDefault();
// Update Post 
http.put(urlB, data, function(err, post){ 
  if(err) { 
	console.log(err);
	alert(err);  
  } else {
	  alert("success");
	  console.log(post);
	//window.location.href = "http://localhost:8080/entries";
  } 
}); 

;

}
function onLoad(){
	
	//window.document.getElementById("submitEntry").onclick=updateEntry;
}

window.onload=onLoad;