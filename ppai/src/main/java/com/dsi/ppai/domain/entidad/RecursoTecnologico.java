package com.dsi.ppai.domain.entidad;

import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recurso_tecnologico")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecursoTecnologico {

    @Id
    @Column(name = "numero_rt")
    private Integer numeroRT;

    @Column(name = "duracion_mantenimiento_preventivo")
    private Integer duracionManteniientoPreventivo;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fraccion_hora_turno")
    private Integer fraccionHorarioTurno;

    private String imagenes;

    @Column(name = "periodicidad_mant_preventivo")
    private Integer periodicidadMantenimientoPreventivo;

    @OneToMany(mappedBy = "recursoTecnologicoDelTurno")
    private List<Turno> turnos;

    @OneToMany(mappedBy = "recursoTecnologicoDelCE")
    private List<CambioEstadoRT> cambioEstadoRTS;

    /**
     * ESTO SERIA LO NUEVO, NO ESTOY SEGURO SI ESTA BIEN CREO QUE SI
     */
    @OneToOne(mappedBy = "recursoTecnologicoDelCE")
    private Estado estado;

    @OneToOne
    @JoinColumn(name = "id_tipo_recurso")
    private TipoRecursoTecnologico tipoRecursoTecnologico;

    @OneToOne
    @JoinColumn(name = "nombre_modelo")
    private Modelo modelo;

    @ManyToOne()
    @JoinColumn(name = "id_centro")
    private CentroDeInvestigacion centroDeInvestigacion;

    public Boolean esDelTiporRTSeleccionado(String tipoRecursoTecnologicoSelec){
        return this.getTipoRecursoTecnologico().getIdTipoRecurso().equals(tipoRecursoTecnologicoSelec);
    };


    /*
    ESTE SERIA EL METODO VIEJO
    public Boolean esReservable(){
        CambioEstadoRT actualCE;
        Repository repository = new Repository();
        //obtengo todos los cambios de estados del RT
        List<CambioEstadoRT> cambioEstadoRTS = repository.findCEDelRT(this.numeroRT);
        for (CambioEstadoRT cambioEstadoRT : cambioEstadoRTS){
            //de todos los CE busco el actual
            if(cambioEstadoRT.esActual()){
                actualCE = cambioEstadoRT;
                //le seteo al RT el CE actual por si se necesita posteriormente
                this.cambioEstadoRTS = new ArrayList<>();
                this.cambioEstadoRTS.add(actualCE);
                //verifico en el CE actual si su estado es reservable
                if(actualCE.esReservable()){ //Aca saque el parametro ya que no va, el CE identifica a su objeto
                    return true;
                };
            };
        };
        return false;
    };
     */

    /**
     * Este es el metodo nuevo
     */
    public Boolean esReservable(){
        return this.estado.esReservable();
    }

    public String esCientificoDeTuCI(PersonalCientifico personalCientifico){
        return this.centroDeInvestigacion.esAsignado(personalCientifico);
    }

    public void conocerCaracteristicasRecursos(){};

    public void conocerCategoria(){};

    public void crear(){};

    public void habilitar(){};

    public void miModeloYMarca(){};

    public void misTurnosDisponibles(){};

    public RecursoTecnologico mostrarDatosRT(){
        RecursoTecnologico recursoTecnologico = new RecursoTecnologico();
        recursoTecnologico.setNumeroRT(this.getNumeroRT());
        recursoTecnologico.setDuracionManteniientoPreventivo(this.getDuracionManteniientoPreventivo());
        recursoTecnologico.setFraccionHorarioTurno(this.getFraccionHorarioTurno());
        recursoTecnologico.setPeriodicidadMantenimientoPreventivo(this.getPeriodicidadMantenimientoPreventivo());
        recursoTecnologico.setCambioEstadoRTS(this.getCambioEstadoRTS());
        /**
         * Aca agregue nuevo este metodo
         */
        recursoTecnologico.setEstado(this.getEstado());
        /**
         *
         */
        recursoTecnologico.setCentroDeInvestigacion(this.getCentroDeInvestigacion()); //TODO aca se tiene que invocar al metodo de getNombre en la pantalla
        recursoTecnologico.setModelo(this.getModelo());
        recursoTecnologico.getModelo().setMarcaDelModelo();

        return recursoTecnologico;
    };

    public void nuevoMantenimientoPreventivo(){};

    public List<Turno> mostrarTurnos(Date fechaActual) {

        List<Turno> turnosPosteriorFechaActual = new ArrayList<>();
        this.setTurnos();

        for (Turno turno : this.turnos) {
            if (turno.getRecursoTecnologicoDelTurno().getNumeroRT() == this.numeroRT) {
                if (turno.esPosteriorFechaActual(fechaActual)) {
                    turnosPosteriorFechaActual.add(turno.mostrarDatos());
                }
            }
        }

        //System.out.println("Turnos posterior fecha actual " + turnosPosteriorFechaActual);

        return turnosPosteriorFechaActual;
    }

    private void setTurnos(){
        this.turnos = Repository.findTurnos();

        //System.out.println("Turnos BD " + this.turnos);
    }
}
