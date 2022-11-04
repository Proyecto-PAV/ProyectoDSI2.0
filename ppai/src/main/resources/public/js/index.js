/*function esconderElemento(name) {
    var x = document.getElementById(name); 
    x.style.display = "none";
    if(name === "container-button-accionar"){
        mostrarElemento("container-reservar-turno")
    }
    if(name === "container-reservar-turno"){
        mostrarElemento("container-button-accionar")
    }

}

function mostrarElemento(name) {
    var x = document.getElementById(name);
    x.style.display = "flex";
}

function confirmar(btn){
    btn.style.display = "none";
    var x = document.getElementById("container-mostrar-recursos"); 
    x.style.display = "none";
}*/

function reservar(){
    window.location.href = "http://localhost:9090/reservar.html";
}