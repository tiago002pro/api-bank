package com.example.demo.repository;

import com.example.demo.model.Cliente;
import com.example.demo.model.ClientePF;
import com.example.demo.model.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "select pf from ClientePF as pf")
    List<ClientePF> findAllPF();

    @Query(value = "select pj from ClientePJ as pj")
    List<ClientePJ> findAllPJ();
}
