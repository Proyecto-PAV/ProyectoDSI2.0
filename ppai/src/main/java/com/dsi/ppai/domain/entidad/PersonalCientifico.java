package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "personal_cientifico")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalCientifico {

    @Id
    @GeneratedValue
    @Column(name = "legajo")
    private int legajo;

    private String nombre;

    private String apellido;

    @Column(name = "numero_documento")
    private int numeroDocumento;

    @Column(name = "correo_elzectronico_institucional")
    private String correoElectronicoInstitucional;

    @Column(name = "correo_electronico_personal")
    private String correoElectronicoPersonal;

    @Column(name = "telefono_celular")
    private String telefonoCelular;

    public void mostrarPersonalCientifico(){

    }

    public void inhabilitarUsuario(){

    }

    public void habilitarUsuario(){

    }

    public void tengoUsuarioHabilitado(){

    }

    public void mostrarMisNovedades(){

    }

}
