package com.dsi.ppai.service;

<<<<<<< HEAD
import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ICUService {
    List<String> opcionReservarTurnoRecursoTecnologico();

    HashMap<String, ArrayList<RecursoTecnologico>> tomarSeleccionTipoRecursoTecnologico(String nombreTipoRecurso);
=======
import java.util.ArrayList;
import java.util.List;

public interface ICUService {

    List<String> opcionReservarTurnoRecursoTecnologico();

    ArrayList<ArrayList<Object>> tomarSeleccionTipoRecurso(String nombreTipoRT);

    ArrayList<ArrayList<Object>> tomarSeleccionRecursoTecnologico(Integer numeroRT);

    String tomarSeleccionTurno(String idTurno);

    String tomarConfirmaciones(Boolean confirmacion);
>>>>>>> endpoints
}
