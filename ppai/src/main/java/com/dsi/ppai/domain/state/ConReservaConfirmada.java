package com.dsi.ppai.domain.state;

import com.dsi.ppai.domain.entidad.CambioEstadoTurno;
import com.dsi.ppai.domain.entidad.Estado;
import com.dsi.ppai.domain.entidad.EstadoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConReservaConfirmada extends Estado {

    private String nombre;

    private String ambito;

    private String descripcion;

    private Boolean esCancelable;

    private Boolean esReservable;

    public void newEstado (){

    }
}
