package com.simulator.tmoney.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "historico_transacao")
public class HistoricoTransacao implements Serializable {

    private static final long serialVersionUID = -3300059883407068335L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Column(name = "valor_anterior_carteira")
    private Double valorAnteriorCarteira;
    
    @NotNull
    @Column(name = "valor_atual_carteira")
    private Double valorAtualCarteira;
    
    @NotNull
    @Column(name = "quantidade_anterior_criptomoeda")
    private Double quantidadeAnteriorCriptomoeda;
    
    @NotNull
    @Column(name = "quantidade_atual_criptomoeda")
    private Double quantidadeAtualCriptomoeda;
    
    @NotNull
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @JoinColumn(name = "carteira_id", referencedColumnName = "id")
    @ManyToOne
    private Carteira carteiraId;
    
    @JoinColumn(name = "tipo_transacao_id", referencedColumnName = "id")
    @ManyToOne
    private TipoTransacao tipoTransacao;
    
}
