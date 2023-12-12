package Contas;

import javax.swing.*;
import java.util.Random;

public class Conta {
    protected String titular;
    protected String tipo;
    protected double saldo;
    protected int numeroConta;
    protected double emprestimo;
    protected double total;
    protected double limite;

    public Conta(String titular, String tipo,int numeroConta) {
        this.titular = titular;
        this.tipo = tipo;
        this.saldo = 0;
        this.emprestimo = 0;
        this.total = 0;
        this.limite = 0;
        this.numeroConta = numeroConta;
    }

    public Conta() {
        this.titular = null;
        this.tipo = null;
        this.saldo = 0;
        this.emprestimo = 0;
        this.total = 0;
        this.limite = 0;
        this.numeroConta = 0;
    }

    protected String getTitular() {
        return titular;
    }

    protected String getTipo() {
        return tipo;
    }

    protected double getSaldo() {
        return saldo;
    }

    protected int getNumeroConta() {
        return numeroConta;
    }

    protected double getEmprestimo() {
        return emprestimo;
    }

    protected double getTotal() {
        return total;
    }

    protected double getLimite() {
        return limite;
    }

    protected void setEmprestimo(double emprestimo) {
        this.emprestimo = emprestimo;
    }

    protected void depositar(double valor) {
    }

    protected void sacar(double valor) {

    }

    protected void emprestimo(double valor) {

    }

    protected void pagarEmprestimo() {

    }

    protected void addDeposito(double valor) {
        depositar(valor);
    }

    protected void addSacar(double valor) {
        sacar(valor);
    }

    protected void addEmprestimo(double valor) {
        emprestimo(valor);
    }

    protected void addpagarEmprestimo() {
        pagarEmprestimo();
    }


    protected int validarValoresInt(String texto) {
        String input = JOptionPane.showInputDialog(texto);
        if (isNumeroInteiro(input)) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }
    }

    protected boolean isNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean isNumeroInteiro(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
