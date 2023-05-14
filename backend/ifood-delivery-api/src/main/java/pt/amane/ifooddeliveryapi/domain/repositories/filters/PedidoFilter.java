package pt.amane.ifooddeliveryapi.domain.repositories.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant dataCriacaoInicio;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant dataCriacaoFim;

}
