package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario {
    @Id
    private String usuario;
    private String clave;
    private Boolean habilitado;
    @OneToOne
    @JoinColumn(name = "empleado_sesion")
    private Sesion sesion;


    public void habilitar(){
        this.habilitado = true;
    }

    public void inhabilitar(){
        this.habilitado = false;
    }

    public void modificarPassword(){}

    public void obtenerCientifico(){}

}
