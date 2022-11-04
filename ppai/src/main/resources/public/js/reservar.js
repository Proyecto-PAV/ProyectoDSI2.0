
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
    window.location.href = "http://localhost:9090/index.html";
}


class tipoRecursoTecnologico{
      constructor(idTipoRecurso, nombre, descripcion) {
          this.idTipoRecurso = idTipoRecurso;
          this.nombre = nombre;
          this.descripcion = descripcion;
      }
}