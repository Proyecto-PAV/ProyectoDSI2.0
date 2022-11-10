package com.dsi.ppai.domain.state;

import com.dsi.ppai.domain.entidad.*;
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
public class Disponible extends Estado {

    private String nombre;

    private String ambito;

    private String descripcion;

    private Boolean esCancelable;

    private Boolean esReservable;

    private List<CambioEstadoTurno> cambiosEstadoTurno;

    private CambioEstadoTurno cambioEstadoAntiguo;

    private CambioEstadoTurno cambioEstadoActual;

    private Estado estadoCreado;


    /**
     * Esto no esta terminado, falta por hacer pero es lo ultimo
     */
    
    public void reservar(Turno turno, Date fechaHoraActual, PersonalCientifico cientificoLogueado, RecursoTecnologico recursoTecnologico) {
        this.obtenerCambiosEstado(turno);
        this.buscarCambiosEstado(fechaHoraActual);
        this.crearProximoEstado(cientificoLogueado, recursoTecnologico);
        this.crearCambioEstado(turno);
        this.setNuevosEstados(turno);

    }

    public void obtenerCambiosEstado(Turno turno) {
        this.cambiosEstadoTurno = turno.getCambiosEstadoTurno();
    }

    public void buscarCambiosEstado(Date fechaHoraActual) {
        CambioEstadoTurno cambioEstadoActual = null;
        for (CambioEstadoTurno cambioEstadoTurno : this.cambiosEstadoTurno) {
            if (cambioEstadoTurno.esActual()) {
                cambioEstadoActual = cambioEstadoTurno;
                break;
            }
        }
        cambioEstadoActual.setFechaHoraHasta(fechaHoraActual);

        this.cambioEstadoAntiguo = cambioEstadoActual;
    }

    public void crearProximoEstado(PersonalCientifico cientificoLogueado, RecursoTecnologico recursoTecnologico){
        for (int i = 0; i < recursoTecnologico.getCentroDeInvestigacion().getCientificos().size(); i++) {
            if (recursoTecnologico.getCentroDeInvestigacion().getCientificos().get(i).getCientifico().equals(cientificoLogueado)){
                this.estadoCreado = new ConReservaConfirmada("ConReservaConfirmada", "TURNO", null, false, true);
            }else{
                this.estadoCreado = new PendienteConfirmacionReserva("PendienteConfirmacionReserva", "TURNO", null, false, true);
            }
        }

    }

    public void crearCambioEstado(Turno turno) {
        CambioEstadoTurno cambioEstadoTurno = new CambioEstadoTurno("00002412-e7b6-4c88-8f71-abcf5az24g11", this.cambioEstadoAntiguo.getFechaHoraHasta(), null, this.estadoCreado, turno);
        this.cambioEstadoActual = cambioEstadoTurno;
    }

    public void setNuevosEstados(Turno turno){
        turno.agregarCambioEstado(cambioEstadoAntiguo, cambioEstadoActual);
        turno.setEstado(this.estadoCreado);
    }

}
