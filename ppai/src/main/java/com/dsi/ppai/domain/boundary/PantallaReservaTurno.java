package com.dsi.ppai.domain.boundary;

import com.dsi.ppai.domain.entidad.*;
import com.dsi.ppai.domain.gestor.GestorReservaTurno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PantallaReservaTurno {

    private String botonReservarTurno;
    private Boolean confirmacionTurno;
    private List<RecursoTecnologico> listadoRecursosTecnologico;
    private HashMap<String, ArrayList<RecursoTecnologico>> recursosTecnologicosAgrupados;
    private List<TipoRecursoTecnologico> listadoTipoRecursos;
    private List<Turno> listadoTurno;
    private String modoNotificacion;
    private String recursoTecnologicoSeleccionado;
    private TipoRecursoTecnologico tipoRecursoSeleccionado;
    private String turnoSeleccionado;
    private String ventanaHabilitada;
    private GestorReservaTurno gestorReservaTurno;

    public void opcionReservarTurnoRecursoTecnologico(){
        habilitarPantalla();
        this.gestorReservaTurno = new GestorReservaTurno();
        gestorReservaTurno.reservarTurnoRecursoTecnologico(this);

    }

    public void habilitarPantalla(){}

    public void mostrarRecursosTecnologicos(){
//      this.gestorReservaTurno.obtenerRTActivos(this.tipoRecursoSeleccionado);
//      this.listadoRecursosTecnologico = this.gestorReservaTurno.getListadoRecursosTecnologicos();
//      this.gestorReservaTurno.agruparPorCentroDeInvestigacion();
//      this.recursosTecnologicosAgrupados = this.getRecursosTecnologicosAgrupados();
        this.recursosTecnologicosAgrupados = hashMap;
        System.out.println(this.recursosTecnologicosAgrupados.toString());
    }

    public List<String> mostrarTiposRecursos(){
        //esto eliminar es para probar el flujo
        return this.gestorReservaTurno.getListadoNombresTipoRecurso();
        //el metodo tomarSeleccionTipoRecurso se ejecuta por un evento del front
    }

    public void tomarSeleccionTipoRecurso(String nombreTipoRecurso){
        //eliminar cuando obtengamos el tipo recurso seleccionado de pantalla
        //TipoRecursoTecnologico tipoRecursoTecnologicoSelec = TipoRecursoTecnologico.builder().idTipoRecurso("0000076d-d538-4247-a400-bf156c6d41ed").descripcion("la belu mas piola").nombre("Balanza de Precision").build();
        this.gestorReservaTurno.tomarSeleccionTipoRecurso(nombreTipoRecurso);
        //this.gestorReservaTurno.tomarSeleccionTipoRecurso(tipoRecursoTecnologicoSelec);
    }

    public void tomarSeleccionRecursoTecnologico(){
        RecursoTecnologico recursoTecnologico = RecursoTecnologico.builder()
                .numeroRT(1)
                .duracionManteniientoPreventivo(5)
                .fechaAlta(null)
                .fraccionHorarioTurno(5)
                .imagenes(null)
                .periodicidadMantenimientoPreventivo(30)
                .centroDeInvestigacion(CentroDeInvestigacion.builder().idCentroInvestigacion("a2cd091e-37f4-4295-8213-68d286a5248a").build())
                .modelo(Modelo.builder().nombre("MM-400/800").build())
                .tipoRecursoTecnologico(TipoRecursoTecnologico.builder().idTipoRecurso("0000076d-d538-4247-a400-bf156c6d41ed").descripcion("la belu mas piola").nombre("Balanza de Precision").build())
                .build();
        this.gestorReservaTurno.tomarSeleccionRecursoTecnologico(recursoTecnologico);
    }

    public void pedirSeleccionTurnos(){}

    public void solicitarConfirmacionYModoNotificacion(){}

    public void tomarConfirmacionTurno(){}

    public void tomarModoNotificacion(){}

    public void tomarSeleccionTurno(){}
}
