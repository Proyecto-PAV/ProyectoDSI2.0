package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tipo_recurso_tecnologico")
public class TipoRecursoTecnologico {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_tipo_recurso")
    private String idTipoRecurso;
    private String nombre;
    private String descripcion;

    public TipoRecursoTecnologico crear(){
        return null;
    };

    public String mostrarCategoria(){
        return this.nombre;
    }
}
