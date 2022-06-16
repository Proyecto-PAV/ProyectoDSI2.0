package com.dsi.ppai.service;

import java.util.ArrayList;
import java.util.List;

public interface ICUService {

    List<String> opcionReservarTurnoRecursoTecnologico();

    ArrayList<ArrayList<Object>> tomarSeleccionTipoRecurso(String nombreTipoRT);

    ArrayList<ArrayList<Object>> tomarSeleccionRecursoTecnologico(Integer numeroRT);
    String tomarSeleccionTurno(String idTurno);
}
