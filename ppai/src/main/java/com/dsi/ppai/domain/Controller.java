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
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> endpoints

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
<<<<<<< HEAD

        return icuService.opcionReservarTurnoRecursoTecnologico();

=======
        return icuService.opcionReservarTurnoRecursoTecnologico();
>>>>>>> endpoints
    }

    @GetMapping(path = "/mostrarRT/{nombreTipoRT}")
    public ArrayList<ArrayList<Object>> getRecursosTecnologicos(@PathVariable String nombreTipoRT){
        return icuService.tomarSeleccionTipoRecurso(nombreTipoRT);
    }

<<<<<<< HEAD
    @GetMapping(path = "/mostrarRT/{nombre}")
    public  HashMap<String, ArrayList<RecursoTecnologico>> getRecursosTecnologicos(@PathVariable String nombreTipoRecurso){
        return icuService.tomarSeleccionTipoRecursoTecnologico(nombreTipoRecurso);
=======
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
>>>>>>> endpoints

        /*HashMap<String, ArrayList<RecursoTecnologico>> hashMap;
        PantallaReservaTurno pantallaReservaTurno = new PantallaReservaTurno();
        hashMap = pantallaReservaTurno.getRecursosTecnologicosAgrupados();
        //      pantallaReservaTurno.mostrarRecursosTecnologicos();
        return hashMap;

        //
        //hashMap.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));*/
    }

<<<<<<< HEAD

=======
>>>>>>> endpoints
}
