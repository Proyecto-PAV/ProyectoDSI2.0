package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "centro_investigacion")
public class CentroDeInvestigacion {

    //Atributos
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_centro")
    private String idCentroInvestigacion;

    @Column(name="caracteristicas_generales")
    private String caracteristicasGenerales;

    private String coordenadas;

    @Column(name="correo_electronico")
    private String correoElectronico;

    private String direccion;

    private String edificio;

    @Column(name="fecha_alta")
    private Date fechaAlta;

    @Column(name="fecha_baja")
    private Date fechaBaja;

    @Column(name="fecha_resolucion_creacion")
    private Date fechaResolucionCreacion;

    @Column(name="motivo_baja")
    private String motivoBaja;

    @Column(name="nombre_centro")
    private String nombre;

    @Column(name="numeor_resolucion_creacion")
    private int numeroResolucionCreacion;

    private String piso;

    private String reglamento;

    private String sigla;

    @Column(name="telefono_contacto")
    private String telefonoContacto;

    @Column(name="tiempo_antelacion_reserva")
    private Date tiempoAntelacionReserva;

    @OneToMany(mappedBy = "centroDeInvestigacion")
    private List<AsignacionCientificoDelCI> cientificos;

    @OneToMany(mappedBy = "centroDeInvestigacion")
    private List<RecursoTecnologico> recursosTecnologicos;


    //Métodos.

    public void asignarTurno() {}

}
