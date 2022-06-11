package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@Entity
@Table(name = "estado")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

    @Id
    @Column(name = "nombre_estado")
    private String nombre;

    private String ambito;
    private String descripcion;
    private boolean esCancelable;
    private boolean esReservable;



    public String mostrarEstado() {
        return this.nombre;
    }
}
