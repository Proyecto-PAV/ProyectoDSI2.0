package com.dsi.ppai.domain;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.EstadoId;
import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import com.dsi.ppai.repository.TipoRTRepository;
import com.dsi.ppai.service.ICUService;
import com.dsi.ppai.service.impl.CUServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    ICUService icuService;

    private final TipoRTRepository tipoRTRepository;

    @Autowired
    public Controller(TipoRTRepository tipoRTRepository) {
        this.tipoRTRepository = tipoRTRepository;
    }

    @GetMapping(path = "/tipoRT")
    public List<String> getTipoRT() {

        return icuService.opcionReservarTurnoRecursoTecnologico();

    }

    @GetMapping(path = "/sesion")
    public void getSesionActual(){
    }

    @GetMapping(path = "/mostrarRT/{nombre}")
    public  HashMap<String, ArrayList<RecursoTecnologico>> getRecursosTecnologicos(@PathVariable String nombreTipoRecurso){
        return icuService.tomarSeleccionTipoRecursoTecnologico(nombreTipoRecurso);

        /*HashMap<String, ArrayList<RecursoTecnologico>> hashMap;
        PantallaReservaTurno pantallaReservaTurno = new PantallaReservaTurno();
        hashMap = pantallaReservaTurno.getRecursosTecnologicosAgrupados();
        //      pantallaReservaTurno.mostrarRecursosTecnologicos();
        return hashMap;

        //
        //hashMap.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));*/
    }


}
