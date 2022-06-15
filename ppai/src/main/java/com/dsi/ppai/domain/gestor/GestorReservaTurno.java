package com.dsi.ppai.domain.gestor;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.*;
import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Repository repository;

    public void reservarTurnoRecursoTecnologico(PantallaReservaTurno pantallaReservaTurno) {
        List<TipoRecursoTecnologico> tipoRecursoTecnologicos = this.buscarTipoRecurso();
        this.pantallaReservaTurno = pantallaReservaTurno;
        this.pantallaReservaTurno.mostrarTiposRecursos(tipoRecursoTecnologicos);
    };

    public List<TipoRecursoTecnologico> buscarTipoRecurso() {
        this.repository = new Repository();
        return this.repository.findAllTipoRT();
    };

    /*
    public List<String> buscarTipoRecurso() {
        this.repository = new Repository();

        List<String> nombresTipoRT = null;

        List<TipoRecursoTecnologico> tipoRecursoTecnologicos = this.repository.findAllTipoRT();

        for (TipoRecursoTecnologico tipoRecursoTecnologico : tipoRecursoTecnologicos) {
            nombresTipoRT.add(tipoRecursoTecnologico.getNombre());
        }

        return nombresTipoRT;
    }
     */


    public void tomarSeleccionTipoRecurso(TipoRecursoTecnologico tipoRecurso) {
        this.tipoRecursoTecnologicoSeleccionado = tipoRecurso;
        this.obtenerRTActivos(tipoRecurso);
    };

    public RecursoTecnologico obtenerRTActivos(TipoRecursoTecnologico tipoRecursoTecnologicoSeleccionado) {
        List<RecursoTecnologico> recursoTecnologicosBD = this.repository.findRTDelTipo();
        for (RecursoTecnologico recursoTecnologicoBD : recursoTecnologicosBD) {
            String idTipoRecursoSelec = tipoRecursoTecnologicoSeleccionado.getIdTipoRecurso();

            //true si el RT tiene el mismo id del tipo de recurso selec
            if(recursoTecnologicoBD.esDelTiporRTSeleccionado(idTipoRecursoSelec)){
                //si es true se lo agrega a la lista de RT activos
                if (recursoTecnologicoBD.esReservable()){
                    System.out.println(recursoTecnologicoBD);
                    this.listadoRecursosTecnologicosActivos.add(recursoTecnologicoBD);
                }
            }
        }
        return null;
    };

    public void agruparPorCentroDeInvestigacion() {};

    public void tomarSeleccionRecursoTecnologico(RecursoTecnologico recursoTecnologico) {};


    public List<Turno> obtenerTurnosRT(){
        this.setFechaHoraActual();
        /**
         * Traer fecha y hora actual
         */
        this.recursoTecnologicoSeleccionado.mostrarTurnos(this.fechaHoraActual);

        return null;
    };

    public void setFechaHoraActual(){
        this.fechaHoraActual = new Date();
    }

    public void agruparYOrdenarTurnos(){};

    //definirColorTurnos

    public Turno tomarSeleccionTurno(){
        return null;
    };

    public Boolean tomarConfirmacionYMododeNotificacion(){
        return null;
    };

    public void registrarReservaTurno(){
        this.buscarEstadoReservado();
    };

    public void buscarEstadoReservado(){

        this.repository = new Repository();

        List<Estado> estadosBD = this.repository.findEstados();

        List<Estado> estadosAmbitoTurno = new ArrayList<>();

        for (Estado estado : estadosBD) {
            if (estado.esAmbitoTurno()) {
                estadosAmbitoTurno.add(estado);
            }
        }

        for (Estado estado : estadosAmbitoTurno) {
            if (estado.esReservado()) {
                this.estadoReservado = estado;
            }
        }
    }

    public void notificarCientifico(){};

    public void finCU(){};
}
