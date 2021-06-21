package com.example.demo.repository;

import com.example.demo.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query(value = "select * from Conta where id_cliente = :cliente and tipo_conta = :tipo_conta" , nativeQuery = true)
    List<Conta> findAllCliente(
            @Param("cliente") Long cliente,
            @Param("tipo_conta") String tipo_conta
    );

    @Query(value = "select * from Conta where id_cliente = :cliente", nativeQuery = true)
    List<Conta> findAllContaWithCliente(
            @Param("cliente") Long cliente
    );
}
