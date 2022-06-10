package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Modelo {

    private String nombre;

    public void mostrarMarcaYModelo() {
        // Aca hay que obtener la marca de ese modelo pero no se como hacerlo por la relacion xd
    }
}
