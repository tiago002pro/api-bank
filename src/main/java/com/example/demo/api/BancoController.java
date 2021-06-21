package com.example.demo.api;

import com.example.demo.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BancoController {

    @Autowired
    BancoService service;

    @PostMapping("/banco/saque/conta/{id_conta}")
    public String saque(@PathVariable Long id_conta, @RequestBody Map<String, Object> json) {
        return this.service.saque(id_conta, json);
    }

    @PostMapping("/banco/deposito/conta/{id_conta}")
    public String deposito(@PathVariable Long id_conta, @RequestBody Map<String, Object> json) {
        return this.service.deposito(id_conta, json);
    }

    @PostMapping("/banco/transferencia/conta/{id_conta_origem}/conta_destino/{id_conta_destino}")
    public String transferencia(@PathVariable Long id_conta_origem, @PathVariable Long id_conta_destino, @RequestBody Map<String, Object> json) {
        return this.service.transferencia(id_conta_origem, id_conta_destino, json);
    }
}
