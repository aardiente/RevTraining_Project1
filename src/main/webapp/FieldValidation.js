/**
 * 
 */

function accessField()
{
	var master = document.getElementById('accessCodeDiv');
	var ninput = document.createElement('input');
	ninput.type = 'text';
	ninput.setAttribute('class', 'form-control');
	ninput.setAttribute('id', 'codefield');
	ninput.setAttribute('name', 'accessCodeField');
	
	var nlabel = document.createElement('label');
	nlabel.type = 'text';
	nlabel.setAttribute('for', 'codelabel');
	nlabel.setAttribute('class', 'form-control');
	
	
	master.append(ninput);

	
	var btn = document.getElementById('accessCodeBtn');
	btn.style.visibility = 'hidden';
}

function validateAmount()
{
	var amount = document.getElementById("amountTB").value;

	if(amount <= 0 || amount == null)	
		return false;
	else
		return true;
}

function validateByLength(strField, min, max)
{
	if(strField.length < min)
		return true;
	else if(strField.length > max && max != 0)
		return true;
	else
		return false;
}
function setErrorMsg(fieldId, msg)
{
	document.getElementById(fieldId).value = msg;
}


function validateFields()
{
	var uname 	= document.getElementById("usernameTB").value;
	var pass 	= document.getElementById("passwordTB").value;
	var conpass = document.getElementById("conpasswordTB").value;
	var fname	= document.getElementById("fnameTB").value;
	var lname	= document.getElementById("lnameTB").value;
	var email 	= document.getElementById("emailTB").value;
	var add		= document.getElementById("addressTB").value;
	var phone 	= document.getElementById("phonenumberTB").value;
	
	if(validateByLength(uname, 5, 12))
	{
		alert("Username must be between 5 and 12 letters.");
		return false;
	}
	else if(validateByLength(pass, 5, 12))
	{
		alert("Password must be between 5 and 12 letters.");
		return false;
	}
	else if(pass !== conpass)
	{
		alert("Passwords do not match");
		return false;
	}
	else if(validateByLength(fname, 0, 0) || validateByLength(lname, 0, 0))
	{
		alert("You must enter your name.");
		return false;
	}
	else if(validateByLength(email, 0, 0))
	{
		alert("Please enter your email");
		return false;
	}
	else if(!validateEmail(email))
	{
		alert("Invalid email format");
		return false;
	}
	else if(validateByLength(add, 0, 0))
	{
		alert("Please enter your address");
		return false;
	}
	else if(validateByLength(phone, 0, 0))
	{
		alert("Please enter your phone number. I won't write it on the bathroom wall... promise. What you dont trust me? *scoffs* how dare you!");
		return false;
	}

	return true;
}
	
function validateEmail(email)
{
	var emailUnicode = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	return emailUnicode.test(email);
}

	
	
function validateLogin()
{
	var user = document.getElementById("username").value;
	var pass = document.getElementById("password").value;
	
	if(validateByLength(user, 5, 12))
	{

		setErrorMsg("errorUser", "<-");
		return false;
	}
	else if(validateByLength(pass, 5, 12))
	{
		setErrorMsg("errorPass", "<-");
		return false;
	}
	else
		return true;
}

