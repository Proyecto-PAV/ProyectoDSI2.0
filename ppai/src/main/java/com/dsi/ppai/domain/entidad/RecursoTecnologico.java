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
                if(actualCE.esReservable(actualCE)){
                    return true;
                };
            };
        };
        return false;
    };

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
        recursoTecnologico.setCentroDeInvestigacion(this.getCentroDeInvestigacion()); //TODO aca se tiene que invocar al metodo de getNombre en la pantalla
        recursoTecnologico.setModelo(this.getModelo());
        recursoTecnologico.getModelo().setMarcaDelModelo();

        return recursoTecnologico;
    };

    public void reservar(Turno turnoSeleccionado, String cientificoLogueado) {
        turnoSeleccionado.reservar();
        centroDeInvestigacion.asignarTurno(turnoSeleccionado, cientificoLogueado);
        List<AsignacionCientificoDelCI> lista1 = new ArrayList<>();
        for(AsignacionCientificoDelCI asignacion : lista1){
            if(asignacion.getCientifico().getNombre().equals(cientificoLogueado)){
                asignacion.setTurno(turnoSeleccionado);
            } else {
                System.out.println("Vayase a la mierda.");
            }
        }
    }

    public void nuevoMantenimientoPreventivo(){};
}
