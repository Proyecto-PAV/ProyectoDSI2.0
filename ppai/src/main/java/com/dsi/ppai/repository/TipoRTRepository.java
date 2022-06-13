package com.dsi.ppai.repository;

import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TipoRTRepository extends CrudRepository<TipoRecursoTecnologico, String> {

    @Query(value = "select * from TIPO_RECURSO_TECNOLOGICO", nativeQuery = true)
    List<TipoRecursoTecnologico> finAllRT();
}
