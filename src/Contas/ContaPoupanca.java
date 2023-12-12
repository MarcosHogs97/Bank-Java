package Contas;

import javax.swing.*;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String titular, int numeroConta) {
        super(titular, "Poupança",numeroConta);

    }

    @Override
    protected void depositar(double valor) {
        this.saldo += valor + (valor * 0.015);
    }

    @Override
    protected void sacar(double valor) {
            this.saldo -= valor;
    }


    protected void emprestimo(double valor) {
        JOptionPane.showMessageDialog(null, "Infelizmente nao é possível realizar empréstimo em conta do tipo poupança");
    }

}