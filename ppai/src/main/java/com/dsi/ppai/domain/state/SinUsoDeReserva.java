package com.dsi.ppai.domain.state;

import com.dsi.ppai.domain.entidad.Estado;
import com.dsi.ppai.domain.entidad.EstadoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class SinUsoDeReserva extends Estado {
}
