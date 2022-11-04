package com.dsi.ppai.domain.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "estado")
@IdClass(EstadoId.class)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Estado {

    @Id
    @Column(name = "nombre_estado")
    private String nombre;
    @Id
    private String ambito;

    private String descripcion;

    private Boolean esCancelable;

    private Boolean esReservable;

    public Boolean esReservable(){
        return this.getEsReservable();
    };

    public Boolean esAmbitoTurno() {

        if (this.ambito.equals("TURNO")) {
            return true;
        }
        else return false;
    }

    public Boolean esReservado() {
        if (this.nombre.equals("Ocupado")) {
            return true;
        }
        else return false;
    }
    public void anular(){

    }
    public void cancelarRerva(){

    }
    public void crear(){

    }
    public void ejecutarReserva(){

    }
    public void elimimnarInventario(){

    }
    public void finalizarMantenimiento(){

    }
    public void generarBajaTecnica(){

    }
    public void habilitar(){

    }
    public void ingresarMantenimientoCorrectivo(){

    }
    public void iniciarMantenimiento(){

    }
    public void mantener(){

    }
    public void mostrarEstado(){

    }
    public void noEjecutarReserva(){

    }
    public void prorrogarMantenimiento(){

    }
    public void reservar(){

    }
    public void reservarProvisorio(){

    }

}
