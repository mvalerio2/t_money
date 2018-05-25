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
@Table(name = "solicitacao_saldo")
public class SolicitacaoSaldo implements Serializable {

    private static final long serialVersionUID = -5894418044699757249L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Column(name = "valor_atual_carteira")
    private long valorAtualCarteira;

    @NotNull
    @Column(name = "valor_solicitado_carteira")
    private long valorSolicitadoCarteira;

    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

    @NotNull
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @JoinColumn(name = "carteira_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Carteira carteiraId;
    
}
