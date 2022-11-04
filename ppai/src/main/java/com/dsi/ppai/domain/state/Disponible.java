package com.dsi.ppai.domain.state;

import com.dsi.ppai.domain.entidad.CambioEstadoRT;
import com.dsi.ppai.domain.entidad.CambioEstadoTurno;
import com.dsi.ppai.domain.entidad.EstadoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "disponible")
@IdClass(EstadoId.class)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Disponible extends com.dsi.ppai.domain.entidad.Estado {

    @Id
    @Column(name = "nombre_estado")
    private String nombre;
    @Id
    private String ambito;

    private String descripcion;

    private Boolean esCancelable;

    private Boolean esReservable;

    private CambioEstadoRT[] cambiosEstadoRT;

    public void reservar() {

        obtenerCambiosEstado();
        buscarCambiosEstado();
        crearProximoEstado();
        crearCambioEstado();

    }

    public void obtenerCambiosEstado() {

    }

    public void buscarCambiosEstado() {

    }

    public void crearProximoEstado() {

    }

    public void crearCambioEstado() {
        new CambioEstadoTurno();
    }

}
