package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public void mostrarMarcaYModelo() {
        // Aca hay que obtener la marca de ese modelo pero no se como hacerlo por la relacion xd
    }
}
