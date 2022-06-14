package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recurso_tecnologico")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecursoTecnologico {

    @Id
    @Column(name = "numero_rt")
    private Integer numeroRT;

    @Column(name = "duracion_mantenimiento_preventivo")
    private Integer duracionManteniientoPreventivo;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fraccion_hora_turno")
    private Integer fraccionHorarioTurno;

    private String imagenes;

    @Column(name = "periodicidad_mant_preventivo")
    private Integer periodicidadMantenimientoPreventivo;

    @OneToMany(mappedBy = "recursoTecnologicoDelTurno")
    private List<Turno> turnos;

    @OneToMany(mappedBy = "recursoTecnologicoDelCE")
    private List<CambioEstadoRT> cambioEstadoRTS;

    @OneToOne
    @JoinColumn(name = "id_tipo_recurso")
    private TipoRecursoTecnologico tipoRecursoTecnologico;

    @OneToOne
    @JoinColumn(name = "nombre_modelo")
    private Modelo modelo;

    @ManyToOne()
    @JoinColumn(name = "id_centro")
    private CentroDeInvestigacion centroDeInvestigacion;

    public Boolean esDelTiporRTSeleccionado(String tipoRecursoTecnologicoSelec){
        return this.getTipoRecursoTecnologico().getIdTipoRecurso().equals(tipoRecursoTecnologicoSelec);
    };

    public void conocerCaracteristicasRecursos(){};

    public void conocerCategoria(){};

    public void crear(){};

    public void habilitar(){};

    public void miModeloYMarca(){};

    public void misTurnosDisponibles(){};

    public void mostrarRT(){};

    public void nuevoMantenimientoPreventivo(){};
}
