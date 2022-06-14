package com.dsi.ppai.repository;

import com.dsi.ppai.domain.entidad.CentroDeInvestigacion;
import com.dsi.ppai.domain.entidad.Modelo;
import com.dsi.ppai.domain.entidad.RecursoTecnologico;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;

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
                RecursoTecnologico tipoRT = new RecursoTecnologico();
                tipoRT.setNumeroRT(rs.getInt(1));
                tipoRT.setDuracionManteniientoPreventivo(rs.getInt(2));
                tipoRT.setFechaAlta(rs.getDate(3));
                tipoRT.setFraccionHorarioTurno(rs.getInt(4));
                tipoRT.setImagenes(rs.getString(5));
                tipoRT.setPeriodicidadMantenimientoPreventivo(rs.getInt(6));
                tipoRT.setCentroDeInvestigacion(CentroDeInvestigacion.builder().idCentroInvestigacion(rs.getString(7)).build());
                tipoRT.setModelo(Modelo.builder().nombre(rs.getString(8)).build());
                tipoRT.setTipoRecursoTecnologico(TipoRecursoTecnologico.builder().idTipoRecurso(rs.getString(9)).build());
                arrayRTDelTipoRT.add(tipoRT);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayRTDelTipoRT;
    }
}
