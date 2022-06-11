package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asignacion_cientifico_ci")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AsignacionCientificoDelCI {

    @Id
    @GeneratedValue
    @Column(name = "id_asignacion_cientifico")
    private int id;

    @Column(name = "fecha_desde")
    private Date fechaDesde;

    @Column(name = "fecha_hasta")
    private Date fechaHasta;

    private String eMail;

    @OneToMany
    @JoinColumn(name = "id_turno")
    private List<Turno> turnos;

    @OneToOne
    @JoinColumn(name = "legajo")
    private PersonalCientifico cientifico;

    public boolean esCientificoActivo(){
        //todo Hacer un loop q recorra los cientificos
        if (this.fechaHasta == null){
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
