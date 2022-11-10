
function cargarPagina(){
    getTiposRecurso();
    //cargarDias();
}



function volver() {
    window.location.href = "http://localhost:9090/index.html";
}


function pintarDiaOcupado(dia){
    diaBtn = document.getElementById(dia);
    diaBtn.style.backgroundColor = "#e75050";
    diaBtn.className = "ocupado";
    diaBtn.style.color = "#ffff"

}

function pintarDiasLibres(){
    for(var i = 1; i <= 30; i++){
        diaBtn = document.getElementById(i.toString());

        if(diaBtn.className !== "ocupado") {
            diaBtn.style.backgroundColor = "#0d6efd";
            diaBtn.style.color = "#ffff"
        }
    }
}

function cargarDias(){
    calendario = document.getElementById("days");

    for(var i = 1; i <=30; i++){
        var dia = document.createElement('button')
        dia.innerText = i;
        dia.id = i;
        calendario.appendChild(dia);
    }
    for(var i = 1; i <=2; i++){
        var dia = document.createElement('button')
        dia.innerText = i;
        dia.id = i;
        calendario.appendChild(dia);
    }
}

function elegirDia(btn) {
    if (btn.className != "ocupado") {
        btn.style.backgroundColor = "rgb(80 231 146)"
    }
}

/*PETICIONES*/

async function getTiposRecurso() {
    var select = document.getElementById("selectTiposRecurso");
    select.innerHTML = '';
    var opt = document.createElement('option');
    opt.value = "Todos";
    opt.innerHTML = "Todos";
    select.appendChild(opt);
    try {
        await axios.get('http://localhost:9090/tipoRT').then(function (response) {


                response.data.forEach(function (nombreTipoRecurso) {
                    var opt = document.createElement('option');
                    opt.value = nombreTipoRecurso;
                    opt.innerHTML = nombreTipoRecurso;
                    select.appendChild(opt);
                })

            }
        )
    } catch (error) {
        console.error(error);
    }
}

async function obtenerTurnos() {
    try {
        await axios.get('http://localhost:9090/sesion').then(function (response) {
            response.data.forEach(function (arrayTurnos) { //array

                for(var i=1; i < arrayTurnos.length; i++){
                    pintarDiaOcupado(arrayTurnos[i].fechaHoraInicio.charAt(8) + arrayTurnos[i].fechaHoraInicio.charAt(9))
                }
            });
            pintarDiasLibres();
        });

    }
    catch (error) {
        console.log(error);
    }

}

async function obtener(tipoRt){

    try{
        await axios.get('http://localhost:9090/mostrarRecursoTecnologico/' + tipoRt).then(function (response){
            response.data.forEach(function(centro){
                centro.forEach(function (recursoTecnologico) {
                        armarTablaRecursos(recursoTecnologico)
                    }
                )

            });

        })
    }catch(error) {
        console.log(error);
    }
}

function armarTablaRecursos(recursoTecnologico){
    var tabla = document.getElementById('tablaRecursosTecnologicos');
    var row = tabla.insertRow(-1);
    row.innerHTML = '<tr> <td>' + recursoTecnologico.numeroRT
        + '</td> <td>' + recursoTecnologico.modelo.marca
        + '</td> <td>' + recursoTecnologico.modelo.modelo
        + '</td> <td>' + recursoTecnologico.cambioEstadoRTS.estado.nombre
        + '</td> <td>' +recursoTecnologico.centroDeInvestigacion.nombre
        + '</td> <td> <input className="form-check-input" type="radio" name="flexRadioDefault"> </td>'
        + '</tr>'
}