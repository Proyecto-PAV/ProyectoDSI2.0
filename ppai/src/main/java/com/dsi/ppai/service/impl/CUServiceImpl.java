package com.dsi.ppai.service.impl;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import com.dsi.ppai.service.ICUService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CUServiceImpl implements ICUService {

    private PantallaReservaTurno pantallaReservaTurno;

    @PostConstruct
    public void init(){
        pantallaReservaTurno = new PantallaReservaTurno();
    }


    @Override
    public List<String> opcionReservarTurnoRecursoTecnologico() {
        pantallaReservaTurno.opcionReservarTurnoRecursoTecnologico();
        return pantallaReservaTurno.mostrarTiposRecursos();
    }

    @Override
    public ArrayList<ArrayList<Object>> tomarSeleccionTipoRecurso(String nombreTipoRT) {
        pantallaReservaTurno.tomarSeleccionTipoRecurso(nombreTipoRT);
        return pantallaReservaTurno.mostrarRecursosTecnologicos();
    }

    @Override
    public ArrayList<ArrayList<Object>> tomarSeleccionRecursoTecnologico(Integer numeroRT) {
        pantallaReservaTurno.tomarSeleccionRecursoTecnologico(numeroRT);
        return pantallaReservaTurno.pedirSeleccionTurnos();
    }

    @Override
    public String tomarSeleccionTurno(String idTurno) {
        pantallaReservaTurno.tomarSeleccionTurno(idTurno);
        return null;
    }

    @Override
    public String tomarConfirmaciones(Boolean confirmacion) {
        pantallaReservaTurno.tomarModoNotificacion();
        return pantallaReservaTurno.tomarConfirmacionTurno(confirmacion);
    }

    /* ESTE ME TIRABA ERROR POR ESO LO COMENTE
    @Override
    public HashMap<String, ArrayList<RecursoTecnologico>> tomarSeleccionTipoRecursoTecnologico(String nombreTipoRecurso) {
        pantallaReservaTurno.tomarSeleccionTipoRecurso(nombreTipoRecurso);
        return pantallaReservaTurno.mostrarRecursosTecnologicos();
    }
     */


}
