package com.example.demo.repository;

import com.example.demo.model.ClientePF;
import com.example.demo.model.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

    @Query(value = "SELECT * FROM Extrato WHERE id_cliente = :id_cliente AND tipo_conta = :tipo_conta", nativeQuery = true)
    List<Extrato> findExtratoCliente(
            @Param("tipo_conta") String tipo_conta,
            @Param("id_cliente") Long id
    );


}
