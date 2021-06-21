package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Extrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_conta")
    private String tipo_conta;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "novo_saldo")
    private Double novo_saldo;

    @Column(name = "cheque_especial")
    private Double cheque_especial;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "id_conta", referencedColumnName = "id")
//    private Conta conta;



}
