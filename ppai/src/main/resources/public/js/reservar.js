
<<<<<<< HEAD
async function getTiposRecurso(){
    var select = document.getElementById("selectTiposRecurso");
    select.innerHTML = '';
    var opt = document.createElement('option');
    opt.value = "Todos";
    opt.innerHTML =  "Todos";
    select.appendChild(opt);
    try{
        await axios.get('http://localhost:9090/tipoRT').then(function (response){
            console.log(response);
            response.data.forEach(function(nombreTipoRecurso){
                var opt = document.createElement('option');
                opt.value = nombreTipoRecurso;
                opt.innerHTML = nombreTipoRecurso;
                select.appendChild(opt);})

            }
        )
    } catch (error) {
        console.error(error);
    }
}


function volver(){
=======
function cargarPagina(){
    getTiposRecurso();
    //cargarDias();
}



function volver() {
>>>>>>> endpoints
    window.location.href = "http://localhost:9090/index.html";
}


<<<<<<< HEAD
class tipoRecursoTecnologico{
      constructor(idTipoRecurso, nombre, descripcion) {
          this.idTipoRecurso = idTipoRecurso;
          this.nombre = nombre;
          this.descripcion = descripcion;
      }
=======
function pintarDiaOcupado(dia){
    diaBtn = document.getElementById(dia);
    diaBtn.style.backgroundColor = "#0d6efd";
    diaBtn.className = "ocupado";
    diaBtn.style.color = "#ffff";

}

function pintarDiasLibres(){
    for(var i = 1; i <= 30; i++){
        diaBtn = document.getElementById(i.toString());

        if(diaBtn.className !== "ocupado") {
            diaBtn.style.backgroundColor = "#e75050";
            diaBtn.style.color = "#ffff";
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
dia = null
function elegirDia(btn) {
    //FALTARIA RECORRER Y VER SI YA NO HAY UNO SELECCIONADO PARA DESMARCARLO
    if(dia === null) {
        document.getElementById(dia)
        btn.style.backgroundColor = "#0d6efd";
    }
    if (btn.className == "ocupado") {
        btn.style.backgroundColor = "rgb(80 231 146)";
        this.dia = btn.id;
        console.log(this.dia)
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

numeroRT = null
async function obtenerTurnos(btn) {
   
    this.numeroRT = btn.value;
    console.log(numeroRT);
    try {
        await axios.get('http://localhost:9090/mostrarTurnosRT/' + numeroRT).then(function (response) {
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

async function obtenerRecursosTecnologicos(){
    tipoRt = document.getElementById("selectTiposRecurso").value
    console.log(tipoRt);
    try{
        await axios.get('http://localhost:9090/mostrarRT/' + tipoRt).then(function (response){
            response.data.forEach(function(centro){
                console.log("unooo")
                for(var i=1; i < centro.length; i++){
                    armarTablaRecursos(centro[i])
                }
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
        + '</td> <td>' + recursoTecnologico.modelo.marcaDelModelo.nombre
        + '</td> <td>' + recursoTecnologico.modelo.nombre
        + '</td> <td>' + recursoTecnologico.cambioEstadoRTS[0].estado.ambito
        + '</td> <td>' +recursoTecnologico.centroDeInvestigacion.nombre
        + '</td> <td> <button class="btn btn-primary" value = "' + recursoTecnologico.numeroRT + '"  onClick="obtenerTurnos(this)">Consultar</button> </td>'
        + '</tr>'
}

async function cargarTablaTurnos(){
    try {
        console.log(this.numeroRT)
        await axios.get('http://localhost:9090/mostrarTurnosRT/' + this.numeroRT).then(function (response) {
            response.data.forEach(function (arrayTurnos) { //array
                for(var i=1; i < arrayTurnos.length; i++) {
                        console.log(arrayTurnos[0])
                        console.log(this.dia)
                        armarTablaTurnos(arrayTurnos[i], i)


                }
            });

        });
        pintarTablaTurnos();

    }
    catch (error) {
        console.log(error);
    }
}

function armarTablaTurnos(turno, rowId){

    var tabla = document.getElementById('tablaTurnosHorarios')
    var row = tabla.insertRow(-1)
    if(turno.fechaHoraFin == null){
        estadoFechaHoraFin = "Sin terminar";
    }
    row.innerHTML = '<tr class="row' + rowId + '"><td>' + turno.fechaHoraInicio
                    +'</td><td>' + estadoFechaHoraFin
                    + '</td><td><button class="btn btn-primary" id="'+ turno.idTurno + '" onclick="seleccionarTurno(this)">Seleccionar</td></tr>'
}

function pintarTablaTurnos(){

}

bandera = false;
async function seleccionarTurno(btn){
    console.log(btn.id)
    idTurno = btn.id;
   try{
        await axios.get('http://localhost:9090/turno/' + idTurno).then(
            function(response) {
                console.log(response.data);
                if(response.data == "ok"){
                    this.bandera = true;
                }
            }
        )
    }catch (error)
    {
        console.log(error);
    }
}

async function confirmarTurno(){
    mensaje = document.getElementById("mensaje")
    try{
        await axios.post('http://localhost:9090/confirmar/' + this.bandera).then(
            function(response) {
                mensaje.innerText = response.data;
                mensaje.style.color = "rgb(80 231 146)"
            }
        )
    }catch (error)
    {
        console.log(error);
    }
}


async function cancelarTurno(){
    mensaje = document.getElementById("mensaje")
    try{
        await axios.post('http://localhost:9090/confirmar/false' ).then(
            function(response) {
                mensaje.innerText = response.data;
                mensaje.style.color = "#e75050";
            }
        )
    }catch (error)
    {
        console.log(error);
    }
>>>>>>> endpoints
}