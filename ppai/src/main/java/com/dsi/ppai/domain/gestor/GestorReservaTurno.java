package com.dsi.ppai.domain.gestor;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.*;
import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.*;
import java.util.*;
import java.util.List;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GestorReservaTurno {
    private PersonalCientifico cientificoLogueado;
    private Boolean confirmacionTurno;
    private String emailCientifico;
    private Estado estadoReservado;
    private Boolean esTuCientifico;
    private Date fechaHoraActual;
    private List<String> listadoNombresTipoRecurso;
    private List<RecursoTecnologico> listadoRecursosTecnologicos;
    private List<RecursoTecnologico> listadoRecursosTecnologicosActivos;
    private ArrayList<ArrayList<Object>> recursosTecnologicosAgrupados;
    private List<Turno> listadoTurnosRecursoTecnologico;
    private RecursoTecnologico recursoTecnologicoSeleccionado;
    private TipoRecursoTecnologico tipoRecursoTecnologicoSeleccionado;
    private Turno turnoSeleccionado;
    private PantallaReservaTurno pantallaReservaTurno;
    private ArrayList<ArrayList<Object>> turnosAgrupados;



    public void reservarTurnoRecursoTecnologico(PantallaReservaTurno pantallaReservaTurno) {
        this.pantallaReservaTurno = pantallaReservaTurno;
        this.buscarTipoRecurso();
        this.pantallaReservaTurno.mostrarTiposRecursos();
    }

    public void buscarTipoRecurso() {
        List<TipoRecursoTecnologico> tipoRecursoTecnologicosBD = Repository.findAllTipoRT();
        this.listadoNombresTipoRecurso = new ArrayList<>();
        for (TipoRecursoTecnologico tipoRecursoTecnologico : tipoRecursoTecnologicosBD) {
            this.listadoNombresTipoRecurso.add(tipoRecursoTecnologico.getNombre());
        }
    }

    /**
     * buscarTipoRecurso pero te trae una lista de strings, esto sigue el diagrama
     */
    /*
    public List<String> buscarTipoRecurso() {
        this.repository = new Repository();
>>>>>>> endpoints

    public void tomarSeleccionTipoRecurso(String nombreTipoRecursso) {
        TipoRecursoTecnologico tipoRecurso = new TipoRecursoTecnologico();
        List<TipoRecursoTecnologico> tipoRecursoTecnologicosBD = this.repository.findAllTipoRT();
        for (TipoRecursoTecnologico tipoRecursoTecnologico : tipoRecursoTecnologicosBD) {
            if (tipoRecursoTecnologico.getNombre() == nombreTipoRecursso){
                tipoRecurso = tipoRecursoTecnologico;
            }
        }
<<<<<<< HEAD
=======

        return nombresTipoRT;
    }
     */


    public void tomarSeleccionTipoRecurso(String nombreTipoRT) {
        //todo Corregir
        TipoRecursoTecnologico tipoRecurso = new TipoRecursoTecnologico();
        List<TipoRecursoTecnologico> tipoRecursoTecnologicosBD = Repository.findAllTipoRT();
        for (TipoRecursoTecnologico trt : tipoRecursoTecnologicosBD){
            if (Objects.equals(trt.getNombre(), nombreTipoRT)){
                tipoRecurso = trt;
            }
        }
        this.tipoRecursoTecnologicoSeleccionado = tipoRecurso;
        this.obtenerRTActivos(tipoRecurso);
    }

    public void obtenerRTActivos(TipoRecursoTecnologico tipoRecursoTecnologicoSeleccionado) {
        List<RecursoTecnologico> recursoTecnologicosBD = Repository.findAllRT();
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
        this.listadoRecursosTecnologicos = new ArrayList<>();

        for (RecursoTecnologico recursoTecnologicoActivo : this.listadoRecursosTecnologicosActivos) {
            this.listadoRecursosTecnologicos.add(recursoTecnologicoActivo.mostrarDatosRT());
        }
        //Esto eliminarlo dsp
        this.agruparPorCentroDeInvestigacion();
        this.pantallaReservaTurno.mostrarRecursosTecnologicos();
    }

    public void agruparPorCentroDeInvestigacion() {
        HashMap<String, ArrayList<RecursoTecnologico>> recursosTecnologicosAgrupados = new HashMap<>();
        ArrayList<ArrayList<Object>> arrayDeArrays = new ArrayList<>();
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
        for (Map.Entry<String, ArrayList<RecursoTecnologico>> entry : recursosTecnologicosAgrupados.entrySet()) {
            ArrayList<Object> a = new ArrayList<>();
            String key = entry.getKey();
            a.add(key);
            for(Object value: entry.getValue()){
                a.add(value);
            }
            arrayDeArrays.add(a);
        }
        this.recursosTecnologicosAgrupados = arrayDeArrays;
        this.pantallaReservaTurno.mostrarRecursosTecnologicos();
    }



    public void obtenerTurnosRT(RecursoTecnologico recursoTecnologico) {
        this.setFechaHoraActual();
        this.recursoTecnologicoSeleccionado = recursoTecnologico;
        this.listadoTurnosRecursoTecnologico = this.recursoTecnologicoSeleccionado.mostrarTurnos(this.fechaHoraActual);
        this.agruparYOrdenarTurnos();
    }



    public void setFechaHoraActual(){
        this.fechaHoraActual = new Date();
    }

    public void agruparYOrdenarTurnos() {
        listadoTurnosRecursoTecnologico.sort(Comparator.comparing(Turno::getFechaHoraInicio));

        HashMap<Integer, List<Turno>> hashTurnosAgrupados = new HashMap<>();
        List<Turno> temporal = new ArrayList<>();
        ArrayList<ArrayList<Object>> arrayDeArrays = new ArrayList<>();

        for (int i = 0; i < this.listadoTurnosRecursoTecnologico.size(); i++) {
            Integer dia = this.listadoTurnosRecursoTecnologico.get(i).getFechaHoraInicio().getDate();
            Turno turno = this.listadoTurnosRecursoTecnologico.get(i);

            if(!hashTurnosAgrupados.containsKey(dia)) {
                temporal.add(turno);
                hashTurnosAgrupados.put(dia, temporal);
            }
            else
            {
                temporal = hashTurnosAgrupados.get(dia);
                temporal.add(turno);
                hashTurnosAgrupados.put(dia, temporal);
            }
        }
        for (Map.Entry<Integer, List<Turno>> entry : hashTurnosAgrupados.entrySet()) {
            ArrayList<Object> a = new ArrayList<>();
            Integer key = entry.getKey();
            a.add(key);
            for(Object value: entry.getValue()){
                a.add(value);
            }
            arrayDeArrays.add(a);
        }
        this.turnosAgrupados = arrayDeArrays; // Aca agrupamos por dia, pero sin mirar el mes, creemos instancias de un solo mes asi no renegamos
    }



    public void tomarSeleccionRecursoTecnologico(Integer numeroRT) {
        RecursoTecnologico recursoTecnologico = new RecursoTecnologico();
        for (RecursoTecnologico rt : this.listadoRecursosTecnologicos){
            if (Objects.equals(rt.getNumeroRT(), numeroRT)){
                recursoTecnologico = rt;
            }
        }
        this.recursoTecnologicoSeleccionado = recursoTecnologico;
        this.verificarClienteLogueado(recursoTecnologico);
    }


    public void verificarClienteLogueado(RecursoTecnologico recursoTecnologico) {
        List<Sesion> sesiones = Repository.findSesiones();
        Sesion sesionActual = null;
        for (Sesion sesion : sesiones) {
            if (sesion.getHoraFin() == null && sesion.getHoraFin() == null) {
                sesionActual = sesion;
                break;
            }
        }
        this.cientificoLogueado = PersonalCientifico.builder().legajo(83123L).build();
        PersonalCientifico pc = sesionActual.mostrarCliente();
        this.emailCientifico = recursoTecnologico.esCientificoDeTuCI(pc);
        this.obtenerTurnosRT(recursoTecnologico);
    }

    public void getFechaHoraActual() {
        this.fechaHoraActual = new Date();
    }


    //definirColorTurnos

    public void tomarSeleccionTurno(String idTurno) {
        List<Turno> turnos = Repository.findTurnos();
        for (Turno t : turnos){
            if (Objects.equals(t.getIdTurno(), idTurno)){
                this.turnoSeleccionado = t;
                break;
            }
        }
        this.pantallaReservaTurno.solicitarConfirmacionYModoNotificacion();
    }


    public String tomarConfirmacionYMododeNotificacion(Boolean confirmacionTurno) {
        this.confirmacionTurno = confirmacionTurno;
        if (!confirmacionTurno){
            return "Cancelado";
        }else {
            this.registrarReservaTurno();
            this.notificarCientifico();
            return this.finCU();
        }

    }

    public void registrarReservaTurno(){
        //TODO falta el metodo de los chicos
        this.buscarEstadoReservado();
        this.recursoTecnologicoSeleccionado.reservar(this.turnoSeleccionado, this.cientificoLogueado, this.estadoReservado, this.fechaHoraActual);
    }

    public void buscarEstadoReservado(){

        List<Estado> estadosBD = Repository.findEstados();
        List<Estado> estadosAmbitoTurno = new ArrayList<>();
        for (Estado estado : estadosBD) {
            if (estado.esAmbitoTurno()) {
                estadosAmbitoTurno.add(estado);
            }
        }
        for (Estado estado : estadosAmbitoTurno) {
            if (estado.esReservado()) {
                this.estadoReservado = estado;
                System.out.println("Estado gestor: " + this.estadoReservado);
            }
        }
    }

    public void notificarCientifico() {
        InterfazEmail interfazEmail = new InterfazEmail();
        interfazEmail.enviarEmail(this.emailCientifico);
    }

    public String finCU(){
        return "Finalizado";
    }

    public ArrayList<ArrayList<Object>> getRecursosTecnologicosAgrupados() {
        return this.recursosTecnologicosAgrupados;
    }

    public List<String> getListadoNombresTipoRecurso() {
        return this.listadoNombresTipoRecurso;
    }

    public ArrayList<ArrayList<Object>> getTurnosAgrupados() {
        return this.turnosAgrupados;
    }
}

