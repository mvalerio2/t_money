package com.simulator.tmoney.model;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "criptomoeda")
public class Criptomoeda implements Serializable {

    private static final long serialVersionUID = -8585864352960329345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "sigla")
    private String sigla;
    
    @OneToMany(mappedBy = "criptomoedaId")
    private List<Carteira> carteiraList;
    
    @OneToMany(mappedBy = "criptomoeda")
    private List<HistoricoCotacao> historicoCotacaoList;

}
