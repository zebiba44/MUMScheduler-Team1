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
 

function deleteSection(url){
const http = new RestHTTP; 
var urlB="http://localhost:8080/api/sections/";
var r = confirm("Are you sur you want to delete this Section "+url);
  if (r == true) {
http.delete(urlB+url) 

.then(data => {location.reload();
console.log(data)}) 
.catch(err => console.log(err)); 

}  
}

function updateSection(){
const http = new easyHTTP; 

let id=document.getElementById("id").value;
let capacity=document.getElementById("capacity").value;
let room=document.getElementById("roomLocation").value;
let faculty=document.getElementById("faculty").value;
let course=document.getElementById("course").value;


var urlB="http://localhost:8080/api/sections/"+id;

var arrayFaculty=faculty.split("%");
var arrayCourse=course.split("%");

const data = {
	id:id,
    capacity: capacity, 
    roomLocation: room, 
    faculty: {id:arrayFaculty[0],name:arrayFaculty[1],phonenumber:arrayFaculty[2],address:arrayFaculty[2]},
    course:{id:arrayCourse[0],name:arrayCourse[1],code:arrayCourse[2]}
  } 
console.log(data.faculty) ; 
console.log(data.course) ; 

// Update Post 
http.put(urlB,data, function(err, post){ 
  if(err) { 
    console.log(err); 
  } else { 
window.location.href = "http://localhost:8080/sections";

  } 
});
}