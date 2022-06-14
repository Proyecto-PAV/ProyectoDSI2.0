package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "marca")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Marca {

    @Id
    @Column(name = "nombre_marca")
    @ReadOnlyProperty
    private String nombre;

    @OneToMany(mappedBy = "marcaDelModelo")
    private List<Modelo> modelos;


}
