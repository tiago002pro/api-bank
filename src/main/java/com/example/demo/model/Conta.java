package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "conta")
public class Conta implements BancoInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_conta")
    private String tipo_conta;

    @Column(name = "ag")
    private Integer ag;

    @Column(name = "conta")
    private Integer conta;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "cheque_especial")
    private Double cheque_especial;

    @Column(name = "juros")
    private Double juros;

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_conta", referencedColumnName = "id")
    private List<Extrato> lancamento_extrato;

}
