package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "cambio_estado_rt")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CambioEstadoRT {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_ce_rt")
    private String cambioEstadoRTId;

    @Column(name = "fecha_desde")
    private Date fechaHoraDesde;

    @Column(name = "fecha_hasta")
    private Date fechaHoraHasta;

    @OneToOne
    @JoinColumn(name = "nombre_estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_recurso_tecnologico")
    private RecursoTecnologico recursoTecnologicoDelCE;
}
