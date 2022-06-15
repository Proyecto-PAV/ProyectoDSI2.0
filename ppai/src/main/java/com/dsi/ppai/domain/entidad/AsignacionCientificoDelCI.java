package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "asignacion_cientifico_ci")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AsignacionCientificoDelCI {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_asignacion_cientifico")
    private String id;

    @Column(name = "fecha_desde")
    private Date fechaDesde;

    @Column(name = "fecha_hasta")
    private Date fechaHasta;

    @OneToMany(mappedBy = "asignacionCientificoCI")
    private List<Turno> turnos;

    @OneToOne
    @JoinColumn(name = "legajo")
    private PersonalCientifico cientifico;

    @ManyToOne()
    @JoinColumn(name = "id_centro")
    private CentroDeInvestigacion centroDeInvestigacion;

    public boolean esCientificoActivo(){
        //todo Hacer un loop q recorra los cientificos
        if (this.fechaHasta == null){
            return true;
        }
        return false;
    }

    public Boolean esTucientifico(PersonalCientifico personalCientifico){
        if (Objects.equals(cientifico.getLegajo(), personalCientifico.getLegajo())){
            return true;
        }
        return false;
    }

    public void misTurnos(){
    }

    public void mostrarCientificoDelCl(){
    }

    public void setTurno(Turno turno){
        this.turnos.add(turno);
    }

}
