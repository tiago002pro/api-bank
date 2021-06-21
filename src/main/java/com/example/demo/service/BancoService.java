package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BancoService {

    @Autowired
    ContaService contaService;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ExtratoService extratoService;

    public String saque(Long id_conta, Map<String, Object> json) {
        Conta conta = contaService.getContaId(id_conta);
        double valor = ((Double) json.get("Valor"));

        if (conta.getTipo_conta().equals("Corrente")) {
            if(conta.saque(conta, valor) != null) {
                extratoService.registra_saque(conta, valor);
                this.contaRepository.save(conta);
                return "Saque efetuado.";
            } else {
                return "Saldo insulficiente";
            }
        } else {
            if(conta.saque(conta, valor) != null) {
                extratoService.registra_saque(conta, valor);
                this.contaRepository.save(conta);
                return "Saque efetuado.";
            } else {
                return "Saldo insulficiente";
            }
        }
    }

    public String deposito(Long id_conta, Map<String, Object> json) {
        Conta conta = contaService.getContaId(id_conta);
        double valor = ((Double) json.get("Valor"));

        conta.deposito(conta, valor);
        extratoService.registra_deposito(conta, valor);
        this.contaRepository.save(conta);
        return "Depósito efetuado.";
    }

    public String transferencia(Long id_conta_origem, Long id_conta_destino, Map<String, Object> json) {
        Conta conta_origem = contaService.getContaId(id_conta_origem);
        Conta conta_destino = contaService.getContaId(id_conta_destino);
        double valor = ((Double) json.get("Valor"));

        if (conta_origem.saque(conta_origem, valor) == null) {
            return "Saldo insulficiente.";
        } else {
            conta_destino.deposito(conta_destino, valor);
            extratoService.registra_transferencia(conta_origem, conta_destino, valor);
            this.contaRepository.save(conta_origem);
            this.contaRepository.save(conta_destino);
            return "Transação efutuada.";
        }
    }
}
