package Contas;

import javax.swing.*;

public class ContaPF extends Conta {
    protected final double limite;
    protected double saldoEmprestimo;
    protected int quantidadeParcelas;
    protected double valorParcelas;
    protected double total;

    public ContaPF(String titular) {
        super(titular, "PF");
        this.limite = 1100.50;
        this.saldoEmprestimo = this.limite;
    }

    protected double getLimite() {
        return limite;
    }

    protected int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    protected void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }


    protected void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    protected double getTotal() {
        return total;
    }

    protected void setTotal(double total) {
        final double taxaIOF = 1.38; // Exemplo de taxa de IOF
        final double taxaIR = 16.0;  // Exemplo de taxa de IR
        double valorIOF = total * taxaIOF / 100;// Calculando o valor do IOF
        double valorIR = total * taxaIR / 100; // Calculando o valor do IR
        this.total = total + valorIOF + valorIR + (total * 0.25);
        this.saldoEmprestimo = total + valorIOF + valorIR + (total * 0.25);
    }

    protected double getEmprestimo() {
        return saldoEmprestimo;
    }

    protected void setEmprestimo(double emprestimo) {
        this.saldoEmprestimo -= emprestimo;
    }
    protected void quitaEmprestimo() {
        this.saldoEmprestimo = this.limite;
    }

    @Override
    protected void depositar(double valor) {
        this.saldo += valor;
    }

    @Override
    protected void sacar(double valor) {
        if (this.getSaldo() >= valor) {
            this.saldo -= valor;
            return;
        }
        JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o saque tente novamente mais tarde!");
    }

    @Override
    protected void emprestimo(double valor) {
        if (getEmprestimo() < valor) {
            JOptionPane.showMessageDialog(null, "Voce nao possui limite suficiente para realizar o empréstimo!!\nLimite para empréstimo de R$" + String.format("%.2f",getEmprestimo()));
            return;
        }
        if (valor > 0 && valor <= getEmprestimo()) {
            setQuantidadeParcelas(validarValoresInt("Em quantas parcelas você deseja pagar o empréstimo?"));
            setValorParcelas(valor / getQuantidadeParcelas());
            setTotal(valor);
            JOptionPane.showMessageDialog(null, "O valor total a ser pago pelo empréstimo é de R$" + String.format("%.2f",getTotal()) + "\nSera cobrado mensalmente um valor de R$" + String.format("%.2f",getTotal() / getQuantidadeParcelas()) + " Durante " + getQuantidadeParcelas() + " Meses");
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Deseja realizar o empréstimo?", "Empréstimo", JOptionPane.YES_NO_OPTION);
            if (yesOrNo == JOptionPane.YES_OPTION) {
                if (getEmprestimo() - valor >= 0) {
                    setEmprestimo(valor);
                    depositar(valor);
                    JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
                    JOptionPane.showMessageDialog(null, "Seu saldo atual é de R$" + String.format("%.2f",getSaldo()) + "\nSeu limite para empréstimo é de R$" + String.format("%.2f",getLimite()) + "\nLimite desponivel de R$ " + String.format("%.2f",getLimite() - getTotal()));

                } else {
                    JOptionPane.showMessageDialog(null, "Empréstimo cancelado");

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Valor inválido.");
        }
    }
    protected void pagarEmprestimo(){
        if(getSaldo() < getTotal()){
            JOptionPane.showMessageDialog(null, "Voce nao possui saldo o suficiente para pagar o empréstimo");
        }else{
            sacar(getTotal());
            quitaEmprestimo();
        }
    }
}