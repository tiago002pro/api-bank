package com.example.demo.api;

import com.example.demo.model.Conta;
import com.example.demo.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ContaController {

    @Autowired
    ContaService service;

    @PostMapping("/conta/C{tipo_conta}/cliente/{id_cliente}")
    public String criaConta(@PathVariable Long id_cliente, @PathVariable Character tipo_conta, @RequestBody Map<String, Object> json) {
        return this.service.criaConta(id_cliente, tipo_conta, json);
    }

    @GetMapping("/contas")
    public List<Conta> getContas() {
        return this.service.getContas();
    }

    @GetMapping("/contas/cliente/{id}")
    public List<Conta> getContas(@PathVariable Long id) {
        return this.service.getContas(id);
    }

    @GetMapping("/conta/{id}")
    public Conta getContaId(@PathVariable Long id) {
        return this.service.getContaId(id);
    }

}
