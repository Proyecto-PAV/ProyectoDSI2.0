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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/mostrarRT/{nombreTipoRT}")
    public ArrayList<ArrayList<Object>> getRecursosTecnologicos(@PathVariable String nombreTipoRT){
        return icuService.tomarSeleccionTipoRecurso(nombreTipoRT);
    }

    @GetMapping(path = "/mostrarTurnosRT/{numeroRT}")
    public ArrayList<ArrayList<Object>> getTurnos(@PathVariable Integer numeroRT) {
        return icuService.tomarSeleccionRecursoTecnologico(numeroRT);
    }

    @GetMapping(path = "/turno/{idTurno}")
    public String setTurno(@PathVariable String idTurno){
        icuService.tomarSeleccionTurno(idTurno);
        return "ok";
    }

    @PostMapping(path = "/confirmar/{confirmacion}")
    public String getConfirmaciones(@PathVariable Boolean confirmacion){
        return icuService.tomarConfirmaciones(confirmacion);


        /*HashMap<String, ArrayList<RecursoTecnologico>> hashMap;
        PantallaReservaTurno pantallaReservaTurno = new PantallaReservaTurno();
        hashMap = pantallaReservaTurno.getRecursosTecnologicosAgrupados();
        //      pantallaReservaTurno.mostrarRecursosTecnologicos();
        return hashMap;

        //
        //hashMap.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));*/
    }

}
