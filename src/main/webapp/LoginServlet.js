const url = "http://localhost:8080/check/core/"
let loginButton = document.getElementById("loginButton");
let showReimbButton = document.getElementById("getReimbButton");
let registerButton = document.getElementById("RegisterButton");
let data = new Date();

console.log(loginButton, showReimbButton, newReimb);
loginButton.addEventListener('click', login);
showReimbButton.addEventListener('click', getReimb);

registerButton.addEventListener('click', register)
console.log(registerButton);
async function login(){
    
    let username = document.getElementById("username").value;
  	let password = document.getElementById("password").value;

	let user = {
		username: username,
		password: password
	}

    let response = await fetch(url+"login",
    {
    	method:	"POST",  
    	body: JSON.stringify(user),
        credentials: "include"
    })
    console.log(response); 
	if(response.status === 200){
		let rows = document.getElementsByClassName("row");
    	let h1 = document.createElement('h1');
	    h1.style.textAlign = "center";
	    h1.innerText= " You are now logged in!"
	    rows[0].replaceChildren(h1);
	    rows[1].innerHTML="";
	    revealDivs();
    } else {
        console.log("Try again");
        

    }
			
}

async function register(){

	let username = document.getElementById("newusername").value;
  	let password = document.getElementById("newpassword").value;
  	let role = document.getElementById("role").value;
  	let firstname = document.getElementById("firstname").value;
  	let lastname = document.getElementById("lastname").value;
  	let email = document.getElementById("email").value;
  	
	let newuser = {
		
		username: username,
		password: password,
		role:role,
		firstname:firstname,
		lastname:lastname,
		email:email
	}
	

    let response = await fetch(url+"register",
    {
    	method:	"POST",  
    	body: JSON.stringify(newuser),
        credentials: "include"
    })
	
  if(response.status===201){
    getReimb();
  }else{
    console.log("Something went wrong! User cannot be created!!");
    console.log(response);
  }}
















































function revealDivs(){
  let divs = document.getElementsByTagName("div");

  for (let div of divs){
    div.hidden=false;
  }
}

async function newReimb(){
	let data = new Date();
  let createReimb = {
    id:0,
    
    amount:document.getElementById("newAmount").value,
    description:document.getElementById("newDescription").value,
    
    statusId:document.getElementById("newStatId").value,
    typeId:document.getElementById("newTypeId").value,
    submitted:document.getElementById(data).value,
    status:"PENDING",
    author:null,
    resolver:null
  }

  let response = await fetch(url+"reimreimbursementfunctions", {
    method:"POST",
    body:JSON.stringify(createReimb),
    credentials:"include"
  });

  if(response.status===201){
    getReimb();
  }else{
    console.log("Something went wrong! Reimbursement not added!!");
    console.log(response);
  }}
  
async function getReimb(){
	
	console.log("something")
  let response = await fetch(url+"reimreimbursementfunctions", {
    credentials:"include"
  })

  if(response.status===200){
    let list = await response.json();
    ReimbGetByStatus(list);
  }
}

async function ReimbGetByStatus(list){
	
	console.log(list)
	let statusId = {
		
		statusId:document.getElementById("searchforstatus").value
	}
	console.log(statusId)

    let response = await fetch(url+"reimreimbursementfunctions",
    {
    	method:	"POST",  
    	body: JSON.stringify(statusId),
        credentials: "include"
    })
	
  let tableBody = document.getElementById("tableBody");
  	tableBody.innerHTML=""
  for(let createReimb of list){
    let row = document.createElement("tr");
    let id = document.createElement("td");
    let amount = document.createElement("td");
    let submitted = document.createElement("td");
    let description = document.createElement("td");
    let statusId = document.createElement("td");
    let typeId = document.createElement("td");
    let author = document.createElement("td");
    let resolver = document.createElement("td");



    id.innerText = createReimb.id;
    amount.innerText = createReimb.amount;
    submitted.innerText = createReimb.submitted;
   
    description.innerText = createReimb.description;
   
    statusId.innerText = createReimb.statusId
    typeId.innerText = createReimb.typeId;
    author.innerText = createReimb.author.firstname+" "+createReimb.author.lastname;
	resolver.innerText = createReimb.resolver.firstname+" "+createReimb.resolver.lastname;
			
    
    row.appendChild(id);
    row.appendChild(amount);
    row.appendChild(description);
    row.appendChild(statusId);
    row.appendChild(typeId);
    
    row.appendChild(statusId);
    row.appendChild(submitted)
    row.appendChild(author);
    row.appendChild(resolver);
    tableBody.appendChild(row);
    	
   
  }
}