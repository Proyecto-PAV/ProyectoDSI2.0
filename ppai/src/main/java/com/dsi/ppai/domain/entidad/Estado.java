package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@Entity
@Table(name = "estado")
@IdClass(EstadoId.class)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

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
        if (this.nombre.equals("Reservado")) {
            return true;
        }
        else return false;
    }
}
