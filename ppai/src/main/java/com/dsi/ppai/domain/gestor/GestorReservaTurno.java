package com.dsi.ppai.domain.gestor;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.*;
import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
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
        this.buscarTipoRecurso();
        this.pantallaReservaTurno = pantallaReservaTurno;
        this.pantallaReservaTurno.mostrarTiposRecursos(this.listadoNombresTipoRecurso);
    };

    public void buscarTipoRecurso() {
        this.repository = new Repository();
        List<TipoRecursoTecnologico> tipoRecursoTecnologicosBD =  this.repository.findAllTipoRT();
        for(TipoRecursoTecnologico tipoRecursoTecnologico : tipoRecursoTecnologicosBD){
            this.listadoNombresTipoRecurso = new ArrayList<>();
            this.listadoNombresTipoRecurso.add(tipoRecursoTecnologico.getNombre());
        }
    };

    public void tomarSeleccionTipoRecurso(TipoRecursoTecnologico tipoRecurso) {
        this.tipoRecursoTecnologicoSeleccionado = tipoRecurso;
        this.obtenerRTActivos(tipoRecurso);
    };

    public void obtenerRTActivos(TipoRecursoTecnologico tipoRecursoTecnologicoSeleccionado) {
        List<RecursoTecnologico> recursoTecnologicosBD = this.repository.findRTDelTipo();
        this.listadoRecursosTecnologicosActivos = new ArrayList<>();

        for (RecursoTecnologico recursoTecnologicoBD : recursoTecnologicosBD) {
            String idTipoRecursoSelec = tipoRecursoTecnologicoSeleccionado.getIdTipoRecurso();

            //true si el RT tiene el mismo id del tipo de recurso selec
            if(recursoTecnologicoBD.esDelTiporRTSeleccionado(idTipoRecursoSelec)){
                //si es true se lo agrega a la lista de RT activos
                if (recursoTecnologicoBD.esReservable()){
                    this.listadoRecursosTecnologicosActivos.add(recursoTecnologicoBD);
                }
            }
        }
        System.out.println(this.listadoRecursosTecnologicosActivos);
        this.listadoRecursosTecnologicos = new ArrayList<>();

        for (RecursoTecnologico recursoTecnologicoActivo : this.listadoRecursosTecnologicosActivos) {
            this.listadoRecursosTecnologicos.add(recursoTecnologicoActivo.mostrarDatosRT());
        }

    }

    public void agruparPorCentroDeInvestigacion() {
        List<String> idsCI = this.repository.findIDDelCI();
        this.listadoRecursosTecnologicos.sort(idsCI.get(1));
    };

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
