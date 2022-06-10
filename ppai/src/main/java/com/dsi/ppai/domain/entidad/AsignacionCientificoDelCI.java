package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AsignacionCientificoDelCI {
    private Date fechaDesde;
    private Date fechaHasta;
    private String eMail;
    private ArrayList<Turno> turnos;

    public boolean esCientificoActivo(){
        if (this.fechaHasta == null){
            return true;
        }
        return false;
    }

    public void misTurnos(){
    }

    public void mostrarCientificoDelCl(){
    }

    public void setTurno(Turno turno){
        this.turnos.add(turno);
    }
}
