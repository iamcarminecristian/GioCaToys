function validate(form1){
	var n=validateFName(form1.name);
	var c=validateFName(form1.surname)
	var u=validateUser(form1.username);
	var p=validatePass(form1.password, form1.cpassword);
	var e=validateEmail(form1.mail);
	var naz=validateNotNull(form1.nationality);
	var d=validateNotNull(form1.bday);
	var v=validateNotNull(form1.street);
	var city=validateNotNull(form1.city);
	var civic=validateNotNull(form1.civico);
	var prov=validateNotNull(form1.provincia);
	var cap=validateNotNull(form1.cap);
	if(n==false||c==false||u==false||p==false||e==false||naz==false||d==false||v==false||city==false||civic==false||prov==false||cap==false){
		return false;
	}
}
function validateLog(form2){
	var u=validateUser(form2.user);
	var p=validatePassword(form2.pass);
	if(u==false||p==false){
		return false;
	}
}

function validateUs(form3){
	var p = validatePass2(form3.pass,form3.pass2,form3.pass3,form3.userP)
	if(p==false){
		return false;
	}
}
function validateFName(FLname){
	if(FLname.value!=""&&FLname.value.match(/^[A-Za-z\s]+$/)){
		return true;
	}
	else{
		FLname.style.border = "3px solid red";
		return false;
	}
}

function validateUser(user){
	if(user.value.length>3&&user.value!=""&&user.value.match(/^[0-9A-Za-z\.-]+$/)){
		return true;
	}
	else{
		user.style.border = "3px solid red";
		return false;
	}
}
function validatePass(pass,passc){
	var p = pass.value;
	if(passc.value==p&&p.length>7&&p!=""&&p.match(/[A-Z]/g)&&p.match(/[a-z]/g)&&p.match(/[0-9]/g))
		return true;
	else{
		document.getElementById("pPass").style.display = "inline-block"
		pass.style.border = "3px solid red";
		passc.style.border = "3px solid red";
		return false;
	}
}
function validatePassword(pass){
	var p = pass.value;
	if(p.length>7&&p!=""&&p.match(/[A-Z]/g)&&p.match(/[a-z]/g)&&p.match(/[0-9]/g))
		return true;
	else{
		pass.style.border = "3px solid red";
		return false;
	}
}
function validatePass2(pass,passn,passc,passo){
	var p = pass.value;
	var pn = passn.value;
	var pc = passc.value
	var po = passo.value;
	if(po==p&&pn==pc&&pn.length>7&&pn!=""&&pn.match(/[A-Z]/g)&&pn.match(/[a-z]/g)&&pn.match(/[0-9]/g))
		return true;
	else{
		pass.style.border = "3px solid red";
		passc.style.border = "3px solid red";
		passn.style.border = "3px solid red";
		passo.style.border = "3px solid red";
		return false;
	}
}
function validateEmail(email){
	if(email.value!=""&&email.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)){
		return true;
	}
	else{
		email.style.border = "3px solid red";
		return false;
	}
}
function validateNotNull(elem){
	if(elem.value!=""){
		return true;
	}
	else{
		elem.style.border = "3px solid red"
		return false;
	}
}

