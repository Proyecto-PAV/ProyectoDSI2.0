package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CentroDeInvestigacion {

    //Atributos
    private String caracteristicasGenerales;
    private String coordenadas;
    private String correoElectronico;
    private String direccion;
    private String edificio;
    private Date fechaAlta;
    private Date fechaBaja;
    private Date fechaResolucionCreacion;
    private String motivoBaja;
    private String nombre;
    private int numeroResolucionCreacion;
    private String piso;
    private String reglamento;
    private String sigla;
    private String telefonoContacto;
    private Date tiempoAntelacionReserva;
    private List<AsignacionCientificoDelCI> cientificos;
    private List<RecursoTecnologico> recursosTecnologicos;


    //Métodos.
    public Boolean esAsignado(AsignacionCientificoDelCI cientifico) {
        return null;
    }

    public void asignarTurno() {}

}
