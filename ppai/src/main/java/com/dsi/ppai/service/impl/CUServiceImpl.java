package com.dsi.ppai.service.impl;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.service.ICUService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CUServiceImpl implements ICUService {

    private PantallaReservaTurno pantallaReservaTurno;

    @PostConstruct
    public void init(){
        pantallaReservaTurno = new PantallaReservaTurno();
    }


    @Override
    public void opcionReservarTurnoRecursoTecnologico() {
        pantallaReservaTurno.opcionReservarTurnoRecursoTecnologico();
    }
}
