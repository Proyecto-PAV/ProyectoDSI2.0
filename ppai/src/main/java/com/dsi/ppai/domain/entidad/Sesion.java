package com.dsi.ppai.domain.entidad;

import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "sesion")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class Sesion {

    @Id
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

    @OneToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;


    public PersonalCientifico mostrarCliente(){
        //TODO no hace falta la lista porque la sesion solo tiene un usuario
        List<Usuario> usuarios = Repository.findUsuarioDeSesion();
        Usuario usuarioSesion = null;
        for (Usuario usuario : usuarios){
            if (Objects.equals(usuario.getUsuario(), this.usuario.getUsuario())){
                usuarioSesion = usuario;
                break;
            }
        }
        return usuarioSesion.obtenerCientifico();
    }
}