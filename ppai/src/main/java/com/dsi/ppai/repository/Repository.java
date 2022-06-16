package com.dsi.ppai.repository;

import com.dsi.ppai.domain.entidad.*;
import com.dsi.ppai.domain.entidad.Sesion;
import com.dsi.ppai.domain.entidad.TipoRecursoTecnologico;
import com.dsi.ppai.domain.entidad.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    static Connection con = DBManager.getConnection();

    public static List<TipoRecursoTecnologico> findAllTipoRT() {
        ArrayList<TipoRecursoTecnologico> arrayTipoRT = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from TIPO_RECURSO_TECNOLOGICO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoRecursoTecnologico tipoRT = new TipoRecursoTecnologico();
                tipoRT.setIdTipoRecurso(rs.getString(1));
                tipoRT.setDescripcion(rs.getString(2));
                tipoRT.setNombre(rs.getString(3));
                arrayTipoRT.add(tipoRT);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayTipoRT;
    }

    public static List<RecursoTecnologico> findAllRT() {
        ArrayList<RecursoTecnologico> arrayRTDelTipoRT = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT RT.*, M.* from RECURSO_TECNOLOGICO RT " +
                    "INNER JOIN MODELO M ON RT.NOMBRE_MODELO = M.NOMBRE_MODELO" +
                    "INNER JOIN CENTRO_INVESTIGACION CI ON RT.ID_CENTRO = CI.ID_CENTRO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RecursoTecnologico recursoTec = new RecursoTecnologico();
                recursoTec.setNumeroRT(rs.getInt(1));
                recursoTec.setDuracionManteniientoPreventivo(rs.getInt(2));
                recursoTec.setFechaAlta(rs.getDate(3));
                recursoTec.setFraccionHorarioTurno(rs.getInt(4));
                recursoTec.setImagenes(rs.getString(5));
                recursoTec.setPeriodicidadMantenimientoPreventivo(rs.getInt(6));
                recursoTec.setCentroDeInvestigacion(CentroDeInvestigacion.builder().idCentroInvestigacion(rs.getString(7)).nombre(rs.getString(22)).build());
                recursoTec.setModelo(Modelo.builder().nombre(rs.getString(8)).marcaDelModelo(Marca.builder().nombre(rs.getString(11)).build()).build());
                recursoTec.setTipoRecursoTecnologico(TipoRecursoTecnologico.builder().idTipoRecurso(rs.getString(9)).build());
                arrayRTDelTipoRT.add(recursoTec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayRTDelTipoRT;
    }

    public static List<Sesion> findSesiones() {
        List<Sesion> sesiones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from SESION");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sesion sesion = new Sesion();
                sesion.setEmpleadoSesion(rs.getString(1));
                sesion.setFechaFin(rs.getDate(2));
                sesion.setFechaInicio(rs.getDate(3));
                sesion.setHoraFin(rs.getDate(4));
                sesion.setHoraInicio(rs.getDate(5));
                sesion.setUsuario(Usuario.builder().usuario(rs.getString(6)).build());
                sesiones.add(sesion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sesiones;
    }

    public static List<CambioEstadoRT> findAllCE() {
        ArrayList<CambioEstadoRT> arrayCEDelRT = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM CAMBIO_ESTADO_RT;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    public static List<Estado> findAllEstados() {
        List<Estado> estados = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ESTADO;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setNombre(rs.getString(2));
                estado.setAmbito(rs.getString(1));
                estado.setDescripcion(rs.getString(3));
                estado.setEsCancelable(rs.getBoolean(4));
                estado.setEsReservable(rs.getBoolean(5));
                estados.add(estado);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estados;
    }


    public static List<Estado> findEstados() {
        ArrayList<Estado> arrayEstadosAmbitoTurno = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ESTADO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setNombre(rs.getString(1));
                estado.setAmbito(rs.getString(2));
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

    public static List<Turno> findTurnos() {
        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from turno");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
            throw new RuntimeException();
        }
        return turnos;
    }

    public static List<AsignacionCientificoDelCI> findAllAsignacionesCI() {
        ArrayList<AsignacionCientificoDelCI> asignaciones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ASIGNACION_CIENTIFICO_CI;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AsignacionCientificoDelCI ac = new AsignacionCientificoDelCI();
                ac.setId(rs.getString(1));
                ac.setFechaDesde(rs.getDate(2));
                ac.setFechaHasta(rs.getDate(3));
                ac.setCentroDeInvestigacion(CentroDeInvestigacion.builder().idCentroInvestigacion(rs.getString(4)).build());
                ac.setCientifico(PersonalCientifico.builder().legajo(rs.getLong(5)).build());
                asignaciones.add(ac);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return asignaciones;
    }

    public List<String> findIDDelCI() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id_centro ci FROM centro_investigacion ci;");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ids.add(rs.getString(1));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ids;
    }

    public static List<Usuario> findUsuarioDeSesion() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USUARIO;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(rs.getString(1));
                usuario.setClave(rs.getString(2));
                usuario.setHabilitado(rs.getBoolean(3));
                usuario.setPersonalCientifico(PersonalCientifico.builder().legajo(rs.getLong(4)).build());
                usuarios.add(usuario);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuarios;
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


    public static List<PersonalCientifico> findPersonalCientifico() {
        List<PersonalCientifico> arrayPC = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from PERSONAL_CIENTIFICO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PersonalCientifico pc = new PersonalCientifico();
                pc.setLegajo(rs.getLong(1));
                pc.setApellido(rs.getString(2));
                pc.setCorreoElectronicoInstitucional(rs.getString(3));
                pc.setCorreoElectronicoPersonal(rs.getString(4));
                pc.setNombre(rs.getString(5));
                pc.setNumeroDocumento(rs.getInt(6));
                pc.setTelefonoCelular(rs.getString(7));
                arrayPC.add(pc);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayPC;
    }

}
