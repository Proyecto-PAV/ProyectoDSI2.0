package com.dsi.ppai.domain.state;

import com.dsi.ppai.domain.entidad.CambioEstadoTurno;
import com.dsi.ppai.domain.entidad.Estado;
import com.dsi.ppai.domain.entidad.EstadoId;
import com.dsi.ppai.domain.entidad.Turno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class EnMantenimiento extends Estado {

    private String nombre;

    private String ambito;

    private String descripcion;

    private Boolean esCancelable;

    private Boolean esReservable;

    private List<CambioEstadoTurno> cambioEstadoTurno;


    /**
     * Esto no esta terminado, falta por hacer pero es lo ultimo
     */

    public void reservar(Turno turno, Date fechaHoraActual) {

        obtenerCambiosEstado(turno);
        buscarCambiosEstado();
        crearProximoEstado();
        crearCambioEstado();

    }

    public void obtenerCambiosEstado(Turno turno) {
        cambioEstadoTurno = turno.getCambiosEstadoTurno();
    }

    public void buscarCambiosEstado() {
        for (CambioEstadoTurno cambioEstadoTurno : cambioEstadoTurno) {
            if (cambioEstadoTurno.esActual()) {
                CambioEstadoTurno cambioEstadoActual;
            }
        }
    }

    public void crearProximoEstado() {

    }

    public void crearCambioEstado() {
        new CambioEstadoTurno();
    }
}
