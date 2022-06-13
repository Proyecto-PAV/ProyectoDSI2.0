package com.dsi.ppai.domain.gestor;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.Estado;
import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import com.dsi.ppai.domain.entidad.Turno;
import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GestorReservaTurno {
    private String cientificoLogueado;
    private Boolean confirmacionTurno;
    private String emailCientifico;
    private Estado estadoReservado;
    private Boolean esTuCientifico;
    private Date fechaHoraActual;
    private List<String> listadoNombresTipoRecurso;
    private List<RecursoTecnologico> listadoRecursosTecnologicos;
    private List<RecursoTecnologico> listadoRecursosTecnologicosActivos;
    private List<Turno> listadoTurnosRecursoTecnologico;
    private RecursoTecnologico recursoTecnologicoSeleccionado;
    private TipoRecursoTecnologico tipoRecursoTecnologicoSeleccionado;
    private Turno turnoSeleccionado;
    private PantallaReservaTurno pantallaReservaTurno;
    private Repository tipoRTRepository;

    public void reservarTurnoRecursoTecnologico() {
        List<TipoRecursoTecnologico> tipoRecursoTecnologicos = this.buscarTipoRecurso();
        //creo una nueva instancia de pantalla?
        this.pantallaReservaTurno = new PantallaReservaTurno();
        this.pantallaReservaTurno.mostrarTiposRecursos(tipoRecursoTecnologicos);
    };

    public List<TipoRecursoTecnologico> buscarTipoRecurso() {
        //falta hacer la conceccion con la bd
        return tipoRTRepository.findAllTipoRT();
    };

    public void tomarSeleccionTipoRecurso(TipoRecursoTecnologico tipoRecurso) {};

    public RecursoTecnologico obtenerRTActivos() {
        return null;
    };

    public void agruparPorCentroDeInvestigacion() {};

    public void tomarSeleccionRecursoTecnologico(RecursoTecnologico recursoTecnologico) {};

    public void verificarClienteLogueado(){};

    public List<Turno> obtenerTurnosRT(){
        return null;
    };

    public Date getFechaHoraActual(){
        return null;
    };

    public void agruparYOrdenarTurnos(){};

    //definirColorTurnos

    public Turno tomarSeleccionTurno(){
        return null;
    };

    public Boolean tomarConfirmacionYMododeNotificacion(){
        return null;
    };

    public void registrarReservaTurno(){};

    public Estado buscarEstadoReservado(){
        return null;
    };

    public void notificarCientifico(){};

    public void finCU(){};
}
