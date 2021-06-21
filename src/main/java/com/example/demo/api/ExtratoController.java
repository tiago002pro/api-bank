package com.example.demo.api;

import com.example.demo.model.Extrato;
import com.example.demo.service.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExtratoController {

    @Autowired
    ExtratoService service;

    @GetMapping("/extrato/cliente/{id_cliente}/C{tipo_conta}")
    public List<Extrato> extrato_cliente(@PathVariable Long id_cliente, @PathVariable Character tipo_conta) {
        return this.service.extrato_cliente(id_cliente, tipo_conta);
    }
}
