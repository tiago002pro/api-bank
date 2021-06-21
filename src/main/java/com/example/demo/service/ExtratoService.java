package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.model.Extrato;
import com.example.demo.repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExtratoService {

    @Autowired
    ExtratoRepository repository;

    public void registra_saque(Conta conta, Double valor) {
        Extrato extrato = new Extrato();
        List<Extrato> extrato_conta = conta.getLancamento_extrato();

        extrato.setTipo_conta(conta.getTipo_conta());
        extrato.setData(LocalDate.now());
        extrato.setSaldo(conta.getSaldo()+valor);
        extrato.setDescricao("Saque");
        extrato.setValor(valor * (-1));
        extrato.setNovo_saldo(extrato.getSaldo()+extrato.getValor());
        extrato.setCheque_especial(conta.getCheque_especial());
        extrato.setCliente(conta.getCliente());
        extrato_conta.add(extrato);
        this.repository.save(extrato);
    }

    public void registra_deposito(Conta conta, Double valor) {
        Extrato extrato = new Extrato();
        List<Extrato> extrato_conta = conta.getLancamento_extrato();

        extrato.setTipo_conta(conta.getTipo_conta());
        extrato.setData(LocalDate.now());
        extrato.setSaldo(conta.getSaldo()-valor);
        extrato.setDescricao("Depósito");
        extrato.setValor(valor);
        extrato.setNovo_saldo(extrato.getSaldo()+extrato.getValor());
        extrato.setCheque_especial(conta.getCheque_especial());
        extrato.setCliente(conta.getCliente());
        extrato_conta.add(extrato);
        this.repository.save(extrato);
    }

    public void registra_transferencia(Conta conta_origem, Conta conta_destino, Double valor) {
        Extrato extrato_1 = new Extrato();
        Extrato extrato_2 = new Extrato();
        List<Extrato> extrato_conta_origem = conta_origem.getLancamento_extrato();
        List<Extrato> extrato_conta_destino = conta_destino.getLancamento_extrato();

        extrato_1.setTipo_conta(conta_origem.getTipo_conta());
        extrato_1.setData(LocalDate.now());
        extrato_1.setSaldo(conta_origem.getSaldo()+valor);
        extrato_1.setDescricao("Transferência feita");
        extrato_1.setValor(valor * (-1));
        extrato_1.setNovo_saldo(extrato_1.getSaldo()+extrato_1.getValor());
        extrato_1.setCheque_especial(conta_origem.getCheque_especial());
        extrato_1.setCliente(conta_origem.getCliente());
        extrato_conta_origem.add(extrato_1);
        this.repository.save(extrato_1);

        extrato_2.setTipo_conta(conta_destino.getTipo_conta());
        extrato_2.setData(LocalDate.now());
        extrato_2.setSaldo(conta_destino.getSaldo()-valor);
        extrato_2.setDescricao("Transferência recebida");
        extrato_2.setValor(valor);
        extrato_2.setNovo_saldo(extrato_2.getSaldo()+extrato_2.getValor());
        extrato_2.setCheque_especial(conta_destino.getCheque_especial());
        extrato_2.setCliente(conta_destino.getCliente());
        extrato_conta_destino.add(extrato_2);
        this.repository.save(extrato_2);
    }

    public List<Extrato> extrato_cliente(Long id_cliente, Character tipo_conta) {
        if (tipo_conta == 'C') {
            return this.repository.findExtratoCliente("Corrente", id_cliente);
//            return this.repository.findExtratoCliente(id_cliente);
        } else if (tipo_conta == 'P') {
            return this.repository.findExtratoCliente("Poupança", id_cliente);
//            return this.repository.findExtratoCliente(id_cliente);
        } else {
            return null;
        }
    }
}
