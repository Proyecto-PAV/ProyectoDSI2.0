package com.dsi.ppai.repository;

import com.dsi.ppai.domain.entidad.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    static Connection con = DBManager.getConnection();

    public static List<TipoRecursoTecnologico> findAllTipoRT(){
        ArrayList<TipoRecursoTecnologico> arrayTipoRT = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from TIPO_RECURSO_TECNOLOGICO");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                TipoRecursoTecnologico tipoRT = new TipoRecursoTecnologico();
                tipoRT.setIdTipoRecurso(rs.getString(1));
                tipoRT.setNombre(rs.getString(2));
                tipoRT.setDescripcion(rs.getString(3));
                arrayTipoRT.add(tipoRT);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayTipoRT;
    }

    public static List<RecursoTecnologico> findRTDelTipo(){
        ArrayList<RecursoTecnologico> arrayRTDelTipoRT = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from RECURSO_TECNOLOGICO");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                RecursoTecnologico recursoTec = new RecursoTecnologico();
                recursoTec.setNumeroRT(rs.getInt(1));
                recursoTec.setDuracionManteniientoPreventivo(rs.getInt(2));
                recursoTec.setFechaAlta(rs.getDate(3));
                recursoTec.setFraccionHorarioTurno(rs.getInt(4));
                recursoTec.setImagenes(rs.getString(5));
                recursoTec.setPeriodicidadMantenimientoPreventivo(rs.getInt(6));
                recursoTec.setCentroDeInvestigacion(CentroDeInvestigacion.builder().idCentroInvestigacion(rs.getString(7)).build());
                recursoTec.setModelo(Modelo.builder().nombre(rs.getString(8)).build());
                recursoTec.setTipoRecursoTecnologico(TipoRecursoTecnologico.builder().idTipoRecurso(rs.getString(9)).build());
                arrayRTDelTipoRT.add(recursoTec);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayRTDelTipoRT;
    }

    public static List<CambioEstadoRT> findCEDelRT(Integer recursoTecnologicoId){
        ArrayList<CambioEstadoRT> arrayCEDelRT = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM CAMBIO_ESTADO_RT WHERE NUMERO_RT = ?;");
            ps.setInt(1, recursoTecnologicoId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                CambioEstadoRT cambioEstado = new CambioEstadoRT();
                cambioEstado.setCambioEstadoRTId(rs.getString(1));
                cambioEstado.setFechaHoraDesde(rs.getDate(2));
                cambioEstado.setFechaHoraHasta(rs.getDate(3));
                cambioEstado.setEstado(Estado.builder().nombre(rs.getString(4)).build());
                cambioEstado.setRecursoTecnologicoDelCE(RecursoTecnologico.builder().numeroRT(rs.getInt(5)).build());
                arrayCEDelRT.add(cambioEstado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayCEDelRT;
    }

    public static Estado findEstadoDelCE(String estadoId, String ambitoRT){
        Estado estado = new Estado();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ESTADO WHERE NOMBRE_ESTADO = ? AND AMBITO = ?;");
            ps.setString(1, estadoId);
            ps.setString(2, ambitoRT);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                estado.setNombre(rs.getString(1));
                estado.setAmbito(rs.getString(2));
                estado.setDescripcion(rs.getString(3));
                estado.setEsCancelable(rs.getBoolean(4));
                estado.setEsReservable(rs.getBoolean(5));}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estado;
    }

    public static List<Estado> findEstados() {
        ArrayList<Estado> arrayEstadosAmbitoTurno = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from estado");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setAmbito(rs.getString(1));
                estado.setNombre(rs.getString(2));
                estado.setDescripcion(rs.getString(3));
                estado.setEsCancelable(rs.getBoolean(4));
                estado.setEsReservable(rs.getBoolean(5));
                arrayEstadosAmbitoTurno.add(estado);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(arrayEstadosAmbitoTurno.toString());

        return arrayEstadosAmbitoTurno;
    }

    public static List<Turno> findTurnos(){
        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from turno");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Turno turno = new Turno();
                turno.setIdTurno(rs.getString(1));
                turno.setDiaSemana(rs.getDate(2));
                turno.setFechaGeneracion(rs.getDate(3));
                turno.setFechaHoraFin(rs.getDate(4));
                turno.setFechaHoraInicio(rs.getDate(5));
                turno.setNombreEstadoCambioEstadoActual(rs.getString(6));
                turno.setAsignacionCientificoCI(AsignacionCientificoDelCI.builder().id(rs.getString(7)).build());
                turno.setRecursoTecnologicoDelTurno(RecursoTecnologico.builder().numeroRT(rs.getInt(8)).build());
                turnos.add(turno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return turnos;
    }

    public static  List<CambioEstadoTurno> findCETurnos(){
        ArrayList<CambioEstadoTurno> cETurnos = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("select  * from cambio_estado_turno");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CambioEstadoTurno cambioEstado = new CambioEstadoTurno();
                cambioEstado.setIdCambioEstadoTurno(rs.getString(1));
                cambioEstado.setFechaHoraDesde(rs.getDate(2));
                cambioEstado.setFechaHoraHasta(rs.getDate(3));
                cambioEstado.setEstado(Estado.builder().ambito(rs.getString(4)).nombre(rs.getString(5)).build());
                cambioEstado.setTurno(Turno.builder().idTurno(rs.getString(6)).build());
                cETurnos.add(cambioEstado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cETurnos;
    }

}
