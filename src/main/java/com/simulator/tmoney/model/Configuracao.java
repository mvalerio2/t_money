package com.simulator.tmoney.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "configuracao")
public class Configuracao implements Serializable{
    
    private static final long serialVersionUID = -5511920299573871984L;
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Column(name = "valorInicial")
    private Double valorInicial;
    
}
