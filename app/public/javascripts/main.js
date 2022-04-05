//window.onload = getToken();

//const { cookie } = require("express/lib/response");

getToken()

function getToken() {
    console.log("oye");
    let almecenamiento = document.cookie
    almecenamiento = almecenamiento.substring(5, almecenamiento.length);
    if (almecenamiento == "") {
        console.log("No hay token");
        window.location.href = "http://localhost:3000/sesionExpirada";

    } else {

        console.log("Hay token");

    }
}




function logOut(){
    console.log("Destrullendo cookie");    
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

    
}