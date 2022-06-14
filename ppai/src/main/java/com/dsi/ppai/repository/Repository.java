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
                cambioEstado.setEstado(Estado.builder().nombre(rs.getString(5)).build());
                cambioEstado.setRecursoTecnologicoDelCE(RecursoTecnologico.builder().numeroRT(rs.getInt(6)).build());
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
}
