package com.dsi.ppai.domain.gestor;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.*;
import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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
    private HashMap<String, ArrayList<RecursoTecnologico>> recursosTecnologicosAgrupados;
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
    }


    public void buscarTipoRecurso() {
        this.repository = new Repository();
        List<TipoRecursoTecnologico> tipoRecursoTecnologicosBD = this.repository.findAllTipoRT();
        for (TipoRecursoTecnologico tipoRecursoTecnologico : tipoRecursoTecnologicosBD) {
            this.listadoNombresTipoRecurso = new ArrayList<>();
            this.listadoNombresTipoRecurso.add(tipoRecursoTecnologico.getNombre());
        }
    }


    public void tomarSeleccionTipoRecurso(TipoRecursoTecnologico tipoRecurso) {
        this.tipoRecursoTecnologicoSeleccionado = tipoRecurso;
        this.obtenerRTActivos(tipoRecurso);
    }


    public void obtenerRTActivos(TipoRecursoTecnologico tipoRecursoTecnologicoSeleccionado) {
        List<RecursoTecnologico> recursoTecnologicosBD = this.repository.findRTDelTipo();
        this.listadoRecursosTecnologicosActivos = new ArrayList<>();

        for (RecursoTecnologico recursoTecnologicoBD : recursoTecnologicosBD) {
            String idTipoRecursoSelec = tipoRecursoTecnologicoSeleccionado.getIdTipoRecurso();

            //true si el RT tiene el mismo id del tipo de recurso selec
            if (recursoTecnologicoBD.esDelTiporRTSeleccionado(idTipoRecursoSelec)) {
                //si es true se lo agrega a la lista de RT activos
                if (recursoTecnologicoBD.esReservable()) {
                    this.listadoRecursosTecnologicosActivos.add(recursoTecnologicoBD);
                }
            }
        }
        System.out.println(this.listadoRecursosTecnologicosActivos);
        this.listadoRecursosTecnologicos = new ArrayList<>();

        for (RecursoTecnologico recursoTecnologicoActivo : this.listadoRecursosTecnologicosActivos) {
            this.listadoRecursosTecnologicos.add(recursoTecnologicoActivo.mostrarDatosRT());
        }
        //Esto eliminarlo dsp
        this.agruparPorCentroDeInvestigacion();
    }

    public void agruparPorCentroDeInvestigacion() {
        HashMap<String, ArrayList<RecursoTecnologico>> recursosTecnologicosAgrupados = new HashMap<>();
        ArrayList<RecursoTecnologico> arrayRecursos = new ArrayList<>();
        for (int i = 0; i < this.listadoRecursosTecnologicos.size(); i++) {
            String rTKey = this.listadoRecursosTecnologicos.get(i).getCentroDeInvestigacion().getIdCentroInvestigacion();
            RecursoTecnologico rTValue = this.listadoRecursosTecnologicos.get(i);

            if (!recursosTecnologicosAgrupados.containsKey(rTKey)) {
                arrayRecursos.add(rTValue);
                recursosTecnologicosAgrupados.put(rTKey, arrayRecursos);
            } else {
                arrayRecursos = recursosTecnologicosAgrupados.get(rTKey);
                arrayRecursos.add(rTValue);
                recursosTecnologicosAgrupados.put(rTKey, arrayRecursos);
            }
        }
        this.recursosTecnologicosAgrupados = recursosTecnologicosAgrupados;
        this.pantallaReservaTurno.mostrarRecursosTecnologicos(this.recursosTecnologicosAgrupados);
    }


    public void tomarSeleccionRecursoTecnologico(RecursoTecnologico recursoTecnologico) {
        this.recursoTecnologicoSeleccionado = recursoTecnologico;
        this.verificarClienteLogueado(recursoTecnologico);
    }


    public void verificarClienteLogueado(RecursoTecnologico recursoTecnologico) {
        List<Sesion> sesiones = repository.findSesiones();
        Sesion sesionActual = null;
        for (Sesion sesion : sesiones) {
            if (sesion.getHoraFin() == null && sesion.getHoraFin() == null) {
                sesionActual = sesion;
                break;
            }
        }
        this.cientificoLogueado = sesionActual.getUsuario().getUsuario();
        PersonalCientifico pc = sesionActual.mostrarCliente();
        System.out.println(recursoTecnologico.esCientificoDeTuCI(pc));
    }


    public List<Turno> obtenerTurnosRT() {
        return null;
    }


    public Date getFechaHoraActual() {
        return null;
    }


    public void agruparYOrdenarTurnos() {
    }

    //definirColorTurnos

    public Turno tomarSeleccionTurno() {
        return null;
    }


    public Boolean tomarConfirmacionYMododeNotificacion() {
        return null;
    }

    public void registrarReservaTurno() {
    }


    public Estado buscarEstadoReservado() {
        return null;
    }


    public void notificarCientifico() {
    }


    public void finCU() {
    }
}

