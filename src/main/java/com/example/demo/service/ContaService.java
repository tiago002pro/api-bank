package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContaService {

    @Autowired
    ContaRepository repository;

    @Autowired
    ClienteService clienteService;

    public String criaConta(Long id_cliente, Character tipo_conta, Map<String, Object> json) {
        Cliente cliente = clienteService.getCliente(id_cliente);
        Conta conta = new Conta();

        if (tipo_conta == 'C') {
            if (verificarContasExistentes(id_cliente, tipo_conta)) {
                return "Só é permitido ter uma conta corrente!";
            } else {
                conta.setTipo_conta("Corrente");
                conta.setCheque_especial(1000.00);
            }
        }

        else if (tipo_conta == 'P') {
            if (verificarContasExistentes(id_cliente, tipo_conta)) {
                return "Só é permitido ter uma conta poupança";
            } else {
                conta.setTipo_conta("Poupança");
                conta.setCheque_especial(0.0);
            }
        }

        conta.setCliente(cliente);
        conta.setAg((Integer) json.get("Agência"));
        conta.setConta((Integer) json.get("Conta"));
        conta.setSaldo(0.0);
        conta.setJuros(0.0);
        conta.setLancamento_extrato(new ArrayList<>());
        this.repository.save(conta);
        return "Conta cadastrada com sucesso!";
    }

    public List<Conta> getContas() {
        return this.repository.findAll();
    }

    public List<Conta> getContas(Long id_cliente) {
        return this.repository.findAllContaWithCliente(id_cliente);
    }

    public Conta getContaId(Long id) {
        return this.repository.findById(id).get();
    }

    public Boolean verificarContasExistentes(Long id_cliente, Character tipo_conta) {

        if (tipo_conta == 'C') {
            return (this.repository.findAllCliente(id_cliente, "Corrente").size() > 0);
        }
        else if (tipo_conta == 'P') {
            return (this.repository.findAllCliente(id_cliente, "Poupança").size() > 0);
        }
        else {
            return false;
        }
    }
}
