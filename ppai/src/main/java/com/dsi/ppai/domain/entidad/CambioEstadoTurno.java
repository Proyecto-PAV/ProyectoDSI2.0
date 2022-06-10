package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CambioEstadoTurno {
    private Date fechaHoraDesde;
    private Date fechaHoraHasta;
    private Estado estado;

    private void mostrarCambioEstadoTurno(){

    }

    public boolean esActual(){
        if (this.fechaHoraHasta == null){
            return true;
        }
        return false;
    }

    public String obtenerEstado(){
        return this.estado.getNombre();
    }
}
