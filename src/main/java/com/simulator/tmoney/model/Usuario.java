package com.simulator.tmoney.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 62584794141389503L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "senha")
    private String senha;

    @OneToMany(mappedBy = "usuarioId")
    private List<Carteira> carteiras;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
