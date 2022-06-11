package com.dsi.ppai.domain.boundary;

import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import com.dsi.ppai.domain.entidad.Turno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PantallaReservaTurno {

    private String botonReservarTurno;
    private Boolean confirmacionTurno;
    private List<RecursoTecnologico> listadoRecursosTecnologico;
    private List<TipoRecursoTecnologico> listadoTipoRecursos;
    private List<Turno> listadoTurno;
    private String modoNotificacion;
    private String recursoTecnologicoSeleccionado;
    private String tipoRecursoSeleccionado;
    private String turnoSeleccionado;
    private String ventanaHabilitada;

    public void habilitarPantalla(){}

    public void mostrarRecursosTecnologicos(){}

    public void mostrarTiposRecursos(){}

    public void opcionReservarTurnoRecursoTecnologico(){}

    public void pedirSeleccionTurnos(){}

    public void solicitarConfirmacionYModoNotificacion(){}

    public void tomarConfirmacionTurno(){}

    public void tomarModoNotificacion(){}

    public void tomarSeleccionRecursoTecnologico(){}

    public void tomarSeleccionTipoRecurso(){}

    public void tomarSeleccionTurno(){}
}
