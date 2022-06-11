package com.dsi.ppai.domain.gestor;

import com.dsi.ppai.domain.entidad.Estado;
import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import com.dsi.ppai.domain.entidad.Turno;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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

    public void reservarTurnoRecursoTecnologico() {};

    //public TipoRecursoTecnologico buscarTipoRecurso() {};

    public void tomarSeleccionTipoRecurso(TipoRecursoTecnologico tipoRecurso) {};

    //public RecursoTecnologico obtenerRTActivos() {};

    public void agruparPorCentroDeInvestigacion() {};

    public void tomarSeleccionRecursoTecnologico(RecursoTecnologico recursoTecnologico) {};

    public void verificarClienteLogueado(){};

    //public List<Turno> obtenerTurnosRT(){};

    //public Date getFechaHoraActual(){};

    public void agruparYOrdenarTurnos(){};

    //definirColorTurnos

    //public Turno tomarSeleccionTurno(){};

    //public Boolean tomarConfirmacionYMododeNotificacion(){};

    public void registrarReservaTurno(){};

    //public Estado buscarEstadoReservado(){};

    public void notificarCientifico(){};

    public void finCU(){};
}
