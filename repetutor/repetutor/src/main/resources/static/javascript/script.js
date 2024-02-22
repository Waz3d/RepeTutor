function functionDropdown(){

}

function viewTutor(id){
    window.location.href = "/viewTutor?id=" + id.toString();
}

function openChat(id){
    window.location.href = "/chat?id=" + id.toString();
}

/* When the user scrolls down, hide the navbar. When the user scrolls up, show the navbar */
var prevScrollpos = window.pageYOffset;
window.onscroll = function() {
    var currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
        document.getElementById("navbar").style.top = "0";
    } else {
        document.getElementById("navbar").style.top = "-50px";
    }
    prevScrollpos = currentScrollPos;
}

function addComment(id){
    window.location.href = "/addComment?id=" + id.toString();
}