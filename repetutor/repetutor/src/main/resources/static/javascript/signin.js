function studentSignIn(){
    var elements = document.getElementsByClassName("tutorField");
    for (let i=0; i<elements.length; i++)
        elements[i].setAttribute("hidden", "hidden");
    document.getElementById("form").removeAttribute("hidden");
    document.getElementById("role").setAttribute("value", "student");
    document.getElementById("studentSignIn").setAttribute("style", "opacity: 0.8");
    document.getElementById("tutorSignIn").setAttribute("style", "opacity: 1");
}
function tutorSignIn(){
    var elements = document.getElementsByClassName("tutorField");
    for (let i=0; i<elements.length; i++)
        elements[i].removeAttribute("hidden");
    document.getElementById("form").removeAttribute("hidden");
    document.getElementById("role").setAttribute("value", "tutor");
    document.getElementById("studentSignIn").setAttribute("style", "opacity: 1");
    document.getElementById("tutorSignIn").setAttribute("style", "opacity: 0.8");
}

function hashPassword(){
    var psw = document.getElementById("psw").value;
    var salt = document.getElementById("salt").value;
    var hash = CryptoJS.SHA256(psw+salt);
    document.getElementById("passwordHash").setAttribute("value", hash);
}

function validate(){
}

function count(){
    var description = document.getElementById("description").value;
    var countString = description.length.toString();
    document.getElementById("count").innerText = countString.concat("/255");
}

function hashPasswordModify(){
    var psw = document.getElementById("psw").value;
    var salt = document.getElementById("salt").value;
    var hash = CryptoJS.SHA256(psw+salt);
    document.getElementById("passwordHash").setAttribute("value", hash);
    if(psw.length!=0){
        document.getElementById("check").setAttribute("value", "si");
    }else{
        document.getElementById("check").setAttribute("value", "no");
    }
}