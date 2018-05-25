package com.simulator.tmoney.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "carteira")
public class Carteira implements Serializable {

    private static final long serialVersionUID = 6856539286234880275L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Column(name = "saldo")
    private Double saldo;
    
    @Column(name = "quantidade_criptomoeda")
    private Double saldoCriptomoeda;
    
    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;
    
    @OneToMany(mappedBy = "carteiraId")
    private List<SolicitacaoSaldo> solicitacaoSaldoList;
    
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioId;
    
    @JoinColumn(name = "criptomoeda_id", referencedColumnName = "id")
    @ManyToOne
    private Criptomoeda criptomoedaId;
    
    @OneToMany(mappedBy = "carteiraId")
    private List<HistoricoTransacao> historicoTransacaoList;

}
