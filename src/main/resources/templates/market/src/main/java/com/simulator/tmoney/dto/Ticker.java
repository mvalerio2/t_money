package com.simulator.tmoney.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker {

    private Double high;
//    high: Maior preço unitário de negociação das últimas 24 horas.

    private Double low;
//    low: Menor preço unitário de negociação das últimas 24 horas.

    private Double vol;
//    vol: Quantidade negociada nas últimas 24 horas.

    private Double last;
//    last: Preço unitário da última negociação.

    private Double buy;
//    buy: Maior preço de oferta de compra das últimas 24 horas.

    private Double sell;
//    sell: Menor preço de oferta de venda das últimas 24 horas.

    private LocalDate date;
//    date: Data e hora da informação em Era Unix


}
