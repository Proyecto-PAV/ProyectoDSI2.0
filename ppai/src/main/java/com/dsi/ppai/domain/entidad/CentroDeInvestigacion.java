package com.dsi.ppai.domain.entidad;

import com.dsi.ppai.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


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

    @Column(name = "caracteristicas_generales")
    private String caracteristicasGenerales;

    private String coordenadas;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String direccion;

    private String edificio;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "fecha_resolucion_creacion")
    private Date fechaResolucionCreacion;

    @Column(name = "motivo_baja")
    private String motivoBaja;

    @Column(name = "nombre_centro")
    private String nombre;

    @Column(name = "numeor_resolucion_creacion")
    private int numeroResolucionCreacion;

    private String piso;

    private String reglamento;

    private String sigla;

    @Column(name = "telefono_contacto")
    private String telefonoContacto;

    @Column(name = "tiempo_antelacion_reserva")
    private Date tiempoAntelacionReserva;

    @OneToMany(mappedBy = "centroDeInvestigacion")
    private List<AsignacionCientificoDelCI> cientificos;

    @OneToMany(mappedBy = "centroDeInvestigacion")
    private List<RecursoTecnologico> recursosTecnologicos;


    //Métodos.
    public String esAsignado(PersonalCientifico personalCientifico) {
        String mailCientifico = null;
        this.cientificos = Repository.findAllAsignacionesCI();
        for (AsignacionCientificoDelCI ac : this.cientificos) {
            if (Objects.equals(ac.getCentroDeInvestigacion().getIdCentroInvestigacion(), this.idCentroInvestigacion)) {
                if (ac.esTucientifico(personalCientifico)) {
                    mailCientifico = personalCientifico.getCorreoElectronicoPersonal();
                }
            }
        }
        return mailCientifico;
    }

    public void asignarTurno() {
    }

}
