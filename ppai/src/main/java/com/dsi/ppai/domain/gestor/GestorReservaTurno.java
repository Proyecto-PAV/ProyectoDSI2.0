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
    private PersonalCientifico cientificoLogueado;
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
    private HashMap<Integer, List<Turno>> turnosAgrupados;

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



    public void obtenerTurnosRT() {
        this.setFechaHoraActual();

        RecursoTecnologico recursoTecnologico = RecursoTecnologico.builder()
                .numeroRT(1)
                .duracionManteniientoPreventivo(5)
                .fechaAlta(null)
                .fraccionHorarioTurno(5)
                .imagenes(null)
                .periodicidadMantenimientoPreventivo(30)
                .centroDeInvestigacion(CentroDeInvestigacion.builder().idCentroInvestigacion("a2cd091e-37f4-4295-8213-68d286a5248a").build())
                .modelo(Modelo.builder().nombre("MM-400/800").build())
                .tipoRecursoTecnologico(TipoRecursoTecnologico.builder().idTipoRecurso("0000076d-d538-4247-a400-bf156c6d41ed").descripcion("la belu mas piola").nombre("Balanza de Precision").build())
                .build();

        this.recursoTecnologicoSeleccionado = recursoTecnologico;

        this.listadoTurnosRecursoTecnologico = this.recursoTecnologicoSeleccionado.mostrarTurnos(this.fechaHoraActual);

        agruparYOrdenarTurnos();
    }



    public void setFechaHoraActual(){
        this.fechaHoraActual = new Date();
    }

    public void agruparYOrdenarTurnos() {
        listadoTurnosRecursoTecnologico.sort(Comparator.comparing(Turno::getFechaHoraInicio));

        HashMap<Integer, List<Turno>> hashTurnosAgrupados = new HashMap<>();
        List<Turno> temporal = new ArrayList<>();

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

        this.turnosAgrupados = hashTurnosAgrupados; // Aca agrupamos por dia, pero sin mirar el mes, creemos instancias de un solo mes asi no renegamos
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
        this.cientificoLogueado = sesionActual.getUsuario().getPersonalCientifico();
        PersonalCientifico pc = sesionActual.mostrarCliente();
        System.out.println(recursoTecnologico.esCientificoDeTuCI(pc));
    }



    public Date getFechaHoraActual() {
        return null;
    }


    //definirColorTurnos

    public Turno tomarSeleccionTurno() {
        return null;
    }


    public Boolean tomarConfirmacionYModoDeNotificacion(String modoNotificacion) {
        //TODO Tomar por default el modo de notificacion de mail y enviar un email.
        return null;
    }

    public void registrarReservaTurno(){
        this.buscarEstadoReservado();
        this.recursoTecnologicoSeleccionado.reservar(this.turnoSeleccionado, this.cientificoLogueado, this.estadoReservado, this.fechaHoraActual);
    }

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



    public void notificarCientifico(){
        InterfazEmail email = new InterfazEmail();
        email.enviarEmail(emailCientifico);
    }

    public void finCU(){}
}

