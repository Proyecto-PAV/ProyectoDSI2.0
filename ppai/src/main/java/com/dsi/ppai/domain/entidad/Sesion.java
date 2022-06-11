package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "sesion")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class Sesion {

    @Id
    @GeneratedValue
    @Column(name = "empleado_sesion")
    private String empleadoSesion;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "hora_fin")
    private Date horaFin;

    @Column(name = "hora_inicio")
    private Date horaInicio;


    public String mostrarCliente(){
        return this.empleadoSesion;
    }
}
