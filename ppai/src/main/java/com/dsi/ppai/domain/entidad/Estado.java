package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "estado")
@IdClass(EstadoId.class)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Estado {

    @Id
    @Column(name = "nombre_estado")
    private String nombre;
    @Id
    private String ambito;

    private String descripcion;

    private Boolean esCancelable;

    private Boolean esReservable;

    public Boolean esReservable(){
        return this.getEsReservable();
    };

    public Boolean esAmbitoTurno() {

        if (this.ambito.equals("TURNO")) {
            return true;
        }
        else return false;
    }

    public Boolean esReservado() {
        if (this.nombre.equals("Ocupado")) {
            return true;
        }
        else return false;
    }

    /**
     * Nuevo
     */
    public Estado mostrarDatos() {
        return this;
    }

    public void reservar(Turno turno, Date fechaHoraActual) {

    }
}
