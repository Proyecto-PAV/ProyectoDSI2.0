package com.dsi.ppai.domain;

import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import com.dsi.ppai.repository.TipoRTRepository;
import com.dsi.ppai.service.ICUService;
import com.dsi.ppai.service.impl.CUServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    public void getTipoRT() {
        icuService.opcionReservarTurnoRecursoTecnologico();
    }

    @GetMapping(path = "/sesion")
    public void getSesionActual(){
    }

}
