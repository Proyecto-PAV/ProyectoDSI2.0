package com.dsi.ppai.domain.entidad;

import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    private String usuario;
    private String clave;
    private Boolean habilitado;
    @OneToOne
    @JoinColumn(name = "legajo")
    private PersonalCientifico personalCientifico;

    public void habilitar(){
        this.habilitado = true;
    }

    public void inhabilitar(){
        this.habilitado = false;
    }

    public void modificarPassword(){}

    public PersonalCientifico obtenerCientifico(){
        //TODO no hace falta crear la lista si el usuario siempre va a tener un cientifico
        List<PersonalCientifico> pcs = Repository.findPersonalCientifico();
        PersonalCientifico cientificoLogueado = new PersonalCientifico();
        for(PersonalCientifico pc : pcs){
            if(Objects.equals(pc.getLegajo(), this.personalCientifico.getLegajo())){
                cientificoLogueado = pc;
            }
        }
        return cientificoLogueado;
    }

}
