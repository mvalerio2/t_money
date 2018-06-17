package com.simulator.tmoney.model;

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
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_transacao")
public class TipoTransacao implements Serializable {

    private static final long serialVersionUID = 6176455143262883582L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "tipo")
    private String tipo;
    
    @OneToMany(mappedBy = "tipoTransacao")
    private List<HistoricoTransacao> historicoTransacaoList;

}
