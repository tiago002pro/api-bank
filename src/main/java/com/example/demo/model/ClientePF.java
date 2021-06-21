package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cliente_pf")
public class ClientePF extends Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;
}
