package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estado {

    private Object ambito;
    private String descripcion;
    private boolean esCancelable;
    private boolean esReservable;
    private String nombre;


    public String mostrarEstado() {
        return this.nombre;
    }
}
