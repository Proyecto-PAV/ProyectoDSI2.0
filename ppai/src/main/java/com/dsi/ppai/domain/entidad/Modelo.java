package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "modelo")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Modelo {

    @Id
    @Column(name = "nombre_modelo")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "nombre_marca")
    private Marca marcaDelModelo;

    public void mostrarMarcaYModelo() {
        // Aca hay que obtener la marca de ese modelo pero no se como hacerlo por la relacion xd
    }
}
