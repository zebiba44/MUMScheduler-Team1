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
 

function deleteStudent(url,firstname,lastname){
const http = new RestHTTP; 
var urlB="http://localhost:8080/api/students/";
var r = confirm("Are you sur you want to delete : "+firstname+" "+lastname);
  if (r == true) {
http.delete(urlB+url) 

.then(data => {location.reload();
console.log(data)}) 
.catch(err => console.log(err)); 

}  
}

function updateStudent(){
const http = new easyHTTP; 

let id=document.getElementById("id").value;
let firstName=document.getElementById("firstName").value;
let lastName=document.getElementById("lastName").value;
let email=document.getElementById("email").value;
let age=document.getElementById("age").value;


var urlB="http://localhost:8080/api/students/"+id;


const data = {
	id:id,
    firstname: firstName, 
    lastname: lastName, 
    email: email,
    age:age
  }   
// Update Post 
http.put(urlB,data, function(err, post){ 
  if(err) { 
    console.log(err); 
  } else { 
window.location.href = "http://localhost:8080/students";
    console.log("hi zozo"); 

  } 
}); 
}