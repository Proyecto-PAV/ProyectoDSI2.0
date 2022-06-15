package com.dsi.ppai;

import com.dsi.ppai.domain.boundary.PantallaReservaTurno;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PpaiApplication {

	public static void main(String[] args) {

		SpringApplication.run(PpaiApplication.class, args);
		PantallaReservaTurno pantallaReservaTurno = new PantallaReservaTurno();
		pantallaReservaTurno.opcionReservarTurnoRecursoTecnologico();
		pantallaReservaTurno.tomarSeleccionRecursoTecnologico();

	}
}
