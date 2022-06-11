package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "centro")
public class CentroDeInvestigacion {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_centro")
    private Integer id;

    @Column(name="aracteristicas_generales")
    private String caracteristicasGenerales;
    @Column(name="coordenadas")
    private String coordenadas;
    @Column(name="correo_electronico")
    private String correoElectronico;
    @Column(name="direccion")
    private String direccion;
    @Column(name="edificio")
    private String edificio;
    @Column(name="fecha_alta")
    private Date fechaAlta;
    @Column(name="fecha_baja")
    private Date fechaBaja;
    @Column(name="fecha_resolucion_creacion")
    private Date fechaResolucionCreacion;
    @Column(name="motivo_Baja")
    private String motivoBaja;
    @Column(name="nombre_centro")
    private String nombre;
    @Column(name="numeor_resolucion_creacion")
    private int numeroResolucionCreacion;
    @Column(name="piso")
    private String piso;
    @Column(name="reglamento")
    private String reglamento;
    @Column(name="sigla")
    private String sigla;
    @Column(name="telefono_contacto")
    private String telefonoContacto;
    @Column(name="tiempo_antelacion_reserva")
    private Date tiempoAntelacionReserva;
    //private List<AsignacionCientificoDelCI> cientificos;
    //private List<RecursoTecnologico> recursosTecnologicos;


    //Métodos.
    public Boolean esAsignado() {
        return null;
    }

    public void asignarTurno() {}

}
