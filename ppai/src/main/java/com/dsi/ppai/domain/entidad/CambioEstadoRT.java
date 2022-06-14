package com.dsi.ppai.domain.entidad;

import com.dsi.ppai.repository.Repository;
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
    @JoinColumns({
            @JoinColumn(name = "ambito"),
            @JoinColumn(name = "nombre_estado")
    })
    private Estado estado;


    @ManyToOne
    @JoinColumn(name = "numero_rt")
    private RecursoTecnologico recursoTecnologicoDelCE;

    public Boolean esActual(){
        return this.getFechaHoraHasta() == null;
    };

    public Boolean esReservable(CambioEstadoRT cambioEstadoRT){
        Repository repository = new Repository();
        Estado estadoDelRTBD = repository.findEstadoDelCE(cambioEstadoRT.getEstado().getNombre(), "RT");
        cambioEstadoRT.setEstado(estadoDelRTBD);
        System.out.println(this.getEstado().esReservable());
        return this.getEstado().esReservable();
    };
}
