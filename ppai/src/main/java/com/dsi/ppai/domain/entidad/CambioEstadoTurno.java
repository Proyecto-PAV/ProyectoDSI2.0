package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cambio_estado_turno")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CambioEstadoTurno {

    @Id
    @GeneratedValue
    @Column(name = "id_cambio_estado_turno")
    private int id;

    @Column(name = "fecha_hora_desde")
    private Date fechaHoraDesde;

    @Column(name = "fecha_hora_hasta")
    private Date fechaHoraHasta;

    @OneToOne
    @JoinColumn(name = "nombre_estado")
    private Estado estado;

    private void mostrarCambioEstadoTurno(){

    }

    public boolean esActual(){
        if (this.fechaHoraHasta == null){
            return true;
        }
        return false;
    }

//    public String obtenerEstado(){
//        return this.estado.getNombre();
//    }
}
