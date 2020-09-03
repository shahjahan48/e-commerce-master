'use strict';

function validateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function ValidateRegister(){
    var flag=true;
    var emailId=document.getElementById('emailAddress').value;
    var password=document.getElementById('password').value;
    var confirmPassword=document.getElementById('confirmPassword').value;
    var firstName=document.getElementById('firstName').value;

    if(!validateEmail(emailId) || emailId.trim() === ''){
        document.getElementById('emailError').classList.remove('hide');
        document.getElementById('emailError').innerHTML = '&nbsp;Please provide valid email address';
        flag=false;
    }
    else {
        document.getElementById('emailError').classList.add('hide');
        document.getElementById('emailError').innerHTML = '';
    }
    if(password.length < 4 || password===""){
        document.getElementById('passwordError').classList.remove('hide');
        if(password.length <= 4)
            document.getElementById('passwordError').innerHTML = '&nbsp;Minimum five characters needed';
        if(password==="")
            document.getElementById('passwordError').innerHTML = '&nbsp;Please provide a password';
        flag=false;
    }
    else{
        document.getElementById('passwordError').classList.add('hide');
        document.getElementById('passwordError').innerHTML = '';
    }
    if(confirmPassword!==password){
        document.getElementById('rePasswordError').classList.remove('hide');
        document.getElementById('rePasswordError').innerHTML = '&nbsp;Password mismatched';
        flag=false;
    }else{
        document.getElementById('rePasswordError').classList.add('hide');
        document.getElementById('rePasswordError').innerHTML = '';
    }
    if(firstName===''){
        document.getElementById('firstNameError').classList.remove('hide');
        document.getElementById('firstNameError').innerHTML = '&nbsp;Please provide first name';
        flag=false;
    }else{
        document.getElementById('firstNameError').classList.add('hide');
        document.getElementById('firstNameError').innerHTML = '';
    }
    return flag;
}