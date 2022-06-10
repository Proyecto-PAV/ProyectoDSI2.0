package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Marca {

    private String nombre;
    private ArrayList<Modelo> modelos;

    public String mostrarMarca() {
        return this.nombre;
    }

    public ArrayList<Modelo> mostrarMisModelos() {
        return this.modelos;
    }
}
