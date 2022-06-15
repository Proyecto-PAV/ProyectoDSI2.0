package com.dsi.ppai.domain.entidad;

import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "turno")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class Turno {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_turno")
    private String idTurno;

    @Column(name = "dia_semana")
    private Date diaSemana;

    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @Column(name = "fecha_hora_fin")
    private Date fechaHoraFin;

    @Column(name = "fecha_hora_inicio")
    private Date fechaHoraInicio;

    @OneToMany(mappedBy = "turno")
    private List<CambioEstadoTurno> cambiosEstadoTurno;

    @ManyToOne
    @JoinColumn(name = "id_asignacion_cientifico")
    private AsignacionCientificoDelCI asignacionCientificoCI;

    @ManyToOne
    @JoinColumn(name = "numero_rt")
    private RecursoTecnologico recursoTecnologicoDelTurno;

    /*
    @OneToOne
    @Column(name = "cambio_estado_actual")
    private CambioEstadoTurno cambioEstadoActual;
     */


    public boolean estoyDisponible(){
        if (fechaHoraFin == null) {
            return true;
        }
        else{
            return false;
        }
    }


    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public boolean esPosteriorFechaActual(Date fechaActual){
        if (fechaActual.before(this.fechaHoraInicio)){
            return true;
        }
        else{
            return false;
        }
    }

    /*
    public List mostrarDatos(){
        List arrayDatos = new ArrayList();
        for (int i = 0; i < this.cambiosEstadoTurno.size(); i++) {
            if (cambiosEstadoTurno.get(i).esActual()){
                this.cambioEstadoActual = cambiosEstadoTurno.get(i);
            }
        }
        this.estadoActual = this.cambioEstadoActual.obtenerEstado();
        arrayDatos.add(getFechaHoraInicio(), getFechaHoraFin(), this.estadoActual);
        return arrayDatos;
    }
     */

    public Turno mostrarDatos() {

        // Consulta cambios de estado
        this.setCambiosEstadoTurno();

        for (CambioEstadoTurno cambioEstadoTurno : this.cambiosEstadoTurno) {
            if (cambioEstadoTurno.esActual()) {
                //this.cambioEstadoActual = cambioEstadoTurno;
                //this.cambioEstadoActual.setEstado(this.cambioEstadoActual.obtenerEstado(this.cambioEstadoActual));
            }
        }
        return this;
    }

    private void setCambiosEstadoTurno(){
        this.cambiosEstadoTurno = Repository.findCETurnos();
    }
}
