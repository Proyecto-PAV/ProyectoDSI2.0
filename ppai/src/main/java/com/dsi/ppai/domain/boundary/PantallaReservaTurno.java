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

    public void opcionReservarTurnoRecursoTecnologico() {
        habilitarPantalla();
        this.gestorReservaTurno = new GestorReservaTurno();
        this.gestorReservaTurno.reservarTurnoRecursoTecnologico(this);
    }

    public void habilitarPantalla() {
    }

    public ArrayList<ArrayList<Object>> mostrarRecursosTecnologicos() {
//        this.gestorReservaTurno.obtenerRTActivos(this.tipoRecursoSeleccionado);
//        this.listadoRecursosTecnologico = this.gestorReservaTurno.getListadoRecursosTecnologicos();
//        this.gestorReservaTurno.agruparPorCentroDeInvestigacion();
//        this.recursosTecnologicosAgrupados = this.getRecursosTecnologicosAgrupados();
        return this.gestorReservaTurno.getRecursosTecnologicosAgrupados();
    }

    public List<String> mostrarTiposRecursos() {
        //esto eliminar es para probar el flujo
        return this.gestorReservaTurno.getListadoNombresTipoRecurso();
    }


    public void tomarSeleccionTipoRecurso(String nombreTipoRT) {
        //eliminar cuando obtengamos el tipo recurso seleccionado de pantalla
        this.gestorReservaTurno.tomarSeleccionTipoRecurso(nombreTipoRT);
    }

    public void tomarSeleccionRecursoTecnologico(Integer nombreRT) {
        this.gestorReservaTurno.tomarSeleccionRecursoTecnologico(nombreRT);
    }

    public ArrayList<ArrayList<Object>> pedirSeleccionTurnos() {
        return this.gestorReservaTurno.getTurnosAgrupados();
    }

    public void solicitarConfirmacionYModoNotificacion() {
    }

    public String tomarConfirmacionTurno(Boolean confirmacion) {
        return this.gestorReservaTurno.tomarConfirmacionYMododeNotificacion(confirmacion);

    }


    public void tomarSeleccionTurno(String idTurno) {
        this.gestorReservaTurno.tomarSeleccionTurno(idTurno);
    }

    public void tomarModoNotificacion() {
    }
}
