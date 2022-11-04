package com.dsi.ppai.domain.boundary;

import com.dsi.ppai.domain.entidad.*;
import com.dsi.ppai.domain.gestor.GestorReservaTurno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PantallaReservaTurno {

    private String botonReservarTurno;
    private Boolean confirmacionTurno;
    private List<RecursoTecnologico> listadoRecursosTecnologico;
    private HashMap<String, ArrayList<RecursoTecnologico>> recursosTecnologicosAgrupados;
    private List<TipoRecursoTecnologico> listadoTipoRecursos;
    private List<Turno> listadoTurno;
    private String modoNotificacion;
    private String recursoTecnologicoSeleccionado;
    private TipoRecursoTecnologico tipoRecursoSeleccionado;
    private String turnoSeleccionado;
    private String ventanaHabilitada;
    private GestorReservaTurno gestorReservaTurno;

    public void opcionReservarTurnoRecursoTecnologico(){
        habilitarPantalla();
        this.gestorReservaTurno = new GestorReservaTurno();
        gestorReservaTurno.reservarTurnoRecursoTecnologico(this);

    }

    public void habilitarPantalla(){}

    public HashMap<String, ArrayList<RecursoTecnologico>> mostrarRecursosTecnologicos(){
      this.recursosTecnologicosAgrupados = this.getRecursosTecnologicosAgrupados();
      return this.recursosTecnologicosAgrupados;
    }

    public List<String> mostrarTiposRecursos(){
        //esto eliminar es para probar el flujo
        return this.gestorReservaTurno.getListadoNombresTipoRecurso();
        //el metodo tomarSeleccionTipoRecurso se ejecuta por un evento del front
    }


    public void tomarSeleccionTipoRecurso(String nombreTipoRecurso){
        //eliminar cuando obtengamos el tipo recurso seleccionado de pantalla
        //TipoRecursoTecnologico tipoRecursoTecnologicoSelec = TipoRecursoTecnologico.builder().idTipoRecurso("0000076d-d538-4247-a400-bf156c6d41ed").descripcion("la belu mas piola").nombre("Balanza de Precision").build();
        this.gestorReservaTurno.tomarSeleccionTipoRecurso(nombreTipoRecurso);
        //this.gestorReservaTurno.tomarSeleccionTipoRecurso(tipoRecursoTecnologicoSelec);
    }

    public void tomarSeleccionRecursoTecnologico(Integer numRecurso){
        this.gestorReservaTurno.tomarSeleccionRecursoTecnologico(numRecurso);
    }

    public void pedirSeleccionTurnos(){}

    public void solicitarConfirmacionYModoNotificacion(){

    }

    public void tomarConfirmacionTurno(){

        this.gestorReservaTurno = new GestorReservaTurno();
        this.gestorReservaTurno.registrarReservaTurno();

    }

    public void tomarModoNotificacion(){}

    public void tomarSeleccionTurno(){}
}
