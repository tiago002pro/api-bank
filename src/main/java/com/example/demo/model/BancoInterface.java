package com.example.demo.model;

public interface BancoInterface {

    default Double deposito(Conta conta, double valor) {
        double novo_saldo = conta.getSaldo() + valor;
        conta.setSaldo(novo_saldo);

        if(conta.getSaldo() >= 0 && conta.getTipo_conta().equals("Corrente")) {
            conta.setCheque_especial(1000.00);
        }
        if (conta.getSaldo() < 0 && conta.getTipo_conta().equals("Corrente")) {
            conta.setCheque_especial(conta.getSaldo()+1000);
        }
        return novo_saldo;
    }

    default Double saque(Conta conta, double valor){
        double saldo = conta.getSaldo();

        if(saldo >= valor) {
            saldo = saldo - valor;
            conta.setSaldo(saldo);
            if (conta.getSaldo() < 0 && conta.getTipo_conta().equals("Corrente")) {
                conta.setCheque_especial(conta.getSaldo()+1000);
            }
            return saldo;
        } else if (saldo < valor && libera_limite(conta)){
            if (libera_valor_limite(saldo, valor)) {
                saldo = saldo - valor;
                conta.setSaldo(saldo);
                if (conta.getSaldo() < 0 && conta.getTipo_conta().equals("Corrente")) {
                    conta.setCheque_especial(conta.getSaldo()+1000);
                }
                return saldo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    default Boolean libera_limite(Conta conta) {
        return conta.getTipo_conta().equals("Corrente");
    }

    default  Boolean libera_valor_limite(double saldo, double valor) {
        return saldo - valor + 1000.00 >= 0;
    }


    //
    // se o saldo < 0
    // data_uso_limite pega a data atual (do saque)
    // cont dias = data_uso_limite - data_hoje (saque e deposito) = retorna quantidade de dias
    // juros = saldo * 10%
    // juros = juros * dias

    // deposito
    // saldo = saldo - juros
    //



//    void extrato(Conta conta);
}
