package com.dsi.ppai.domain;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.Turno;
import com.dsi.ppai.repository.TipoRTRepository;
import com.dsi.ppai.service.ICUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


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
    public ArrayList<ArrayList<Object>> getSesionActual(){
        HashMap<String, ArrayList<Object>> hash = new HashMap<String, ArrayList<Object>>();
        ArrayList<Object> array = new ArrayList<Object>();

        Turno turno = new Turno();
        Date date = new Date();
        turno.setFechaHoraInicio(date);
        array.add(turno);
        hash.put("16", array);
        hash.put("17", array);


        ArrayList<ArrayList<Object>> arrayDeArrays = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Object>> entry : hash.entrySet()) {
            ArrayList<Object> a = new ArrayList<>();
            String key = entry.getKey();
            a.add(key);
            for(Object value: entry.getValue()){
                a.add(value);
            }
            arrayDeArrays.add(a);
        }
        return arrayDeArrays;


    }

    @GetMapping(path = "/mostrarRT")
    public void getRecursosTecnologicos(){
        HashMap<String, ArrayList<RecursoTecnologico>> hashMap;
        PantallaReservaTurno pantallaReservaTurno = new PantallaReservaTurno();
//        pantallaReservaTurno.mostrarRecursosTecnologicos();
        hashMap = pantallaReservaTurno.getRecursosTecnologicosAgrupados();
//        hashMap.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));

    }



}

