package Contas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Processamento {
    protected ArrayList<Conta> contas = new ArrayList<>();

    public void exe() {

        String[] options = {"Cadastrar conta", "Excluir conta", "Sacar", "Depositar", "Solicitar empréstimo", "Pagar Emprestimo", "Visualizar Conta", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha oque deseja fazer.", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        switch (escolha) {

            case 0://"Cadastrar conta"
                cadastrarUsuario();
                exe();
                break;

            case 1: //"Excluir conta"
                excluirUsuario();
                exe();
                break;

            case 2: //"Sacar"
                processarSaque();
                exe();
                break;

            case 3://"Depositar"
                processarDeposito();
                exe();
                break;
            case 4://"Solicitar empréstimo"
                processarEmprestimo();
                exe();
                break;
            case 5://"Pagar Emprestimo"
                processarPagamentoEmprestimo();
                exe();
                break;
            case 6://Visualizar Conta
                processarVizualizar();
                exe();
                break;
            case 7://sair
                JOptionPane.showMessageDialog(null, "Obrigado por usar nossa aplicação, volte sempre!!");
                break;
        }
    }

    protected void cadastrarUsuario() {
        int simOuNao = JOptionPane.showConfirmDialog(null, "Deseja realizar o primeiro depósito?", "Depósito", JOptionPane.YES_NO_OPTION);
        if (simOuNao == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Vamos iniciar o processo de cadastro.");
            String[] tiposConta = {"Conta PF", "Conta PJ", "Conta Poupança"};
            int escolhaConta = JOptionPane.showOptionDialog(null, "Para começar, por favor, informe o tipo de conta desejado.", "Cadastro Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiposConta, null);
            if (escolhaConta == 0) {
                JOptionPane.showMessageDialog(null, "Por favor, insira o nome do titular da conta PF.");
                String titular = JOptionPane.showInputDialog(null, "Titular:", "Conta PF", JOptionPane.PLAIN_MESSAGE);
                contas.add(new ContaPF(titular));
                JOptionPane.showMessageDialog(null, "Conta PF cadastrada com sucesso!");
            } else if (escolhaConta == 1) {
                JOptionPane.showMessageDialog(null, "Por favor, insira o nome do titular da conta PJ.");
                String titular = JOptionPane.showInputDialog(null, "Titular:", "Conta PJ", JOptionPane.PLAIN_MESSAGE);
                contas.add(new ContaPJ(titular));
                JOptionPane.showMessageDialog(null, "Conta PJ cadastrada com sucesso!");
            } else if (escolhaConta == 2) {
                JOptionPane.showMessageDialog(null, "Por favor, insira o nome do titular da conta Poupança.");
                String titular = JOptionPane.showInputDialog(null, "Titular:", "Conta Poupança", JOptionPane.PLAIN_MESSAGE);
                contas.add(new ContaPoupanca(titular));
                JOptionPane.showMessageDialog(null, "Conta Poupança cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
            simOuNao = JOptionPane.showConfirmDialog(null, "Deseja realizar o primeiro depósito?", "Depósito", JOptionPane.YES_NO_OPTION);
            if (simOuNao == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Por favor, insira o valor do primeiro depósito.");
                double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor:", "Depósito", JOptionPane.PLAIN_MESSAGE));
                contas.get(contas.size() - 1).addDeposito(valorDeposito);
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
            }
            double saldo = contas.get(contas.size() - 1).getSaldo();
            int numero = contas.get(contas.size() - 1).getNumeroConta();
            String tipo = contas.get(contas.size() - 1).getTipo();
            String titular = contas.get(contas.size() - 1).getTitular();
            JOptionPane.showMessageDialog(null, "Titular: " + titular + "\nTipo da conta: " + tipo + "\nSaldo atual: R$" + saldo + "\nNumero da conta : " + numero);
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado. Se decidir cadastrar uma conta no futuro, estaremos aqui para ajudar!");
        }
    }

    protected void excluirUsuario() {
        String[] tiposContaE = {"Titular da conta", "Numero de conta"};
        int escolha = JOptionPane.showOptionDialog(null, "Por favor, informe o tipo de busca que deseja fazer.", "Visualizar Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiposContaE, null);
        if (escolha == 0) {
            String titular = JOptionPane.showInputDialog(null, "Titular:", "Visualizar Conta Por Titular", JOptionPane.PLAIN_MESSAGE);
            Conta contaEncontrada = buscarPorTitular(titular);
            excluirConta(contaEncontrada);
        } else {
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da Conta:", "Visualizar Conta Por Numero da Conta", JOptionPane.PLAIN_MESSAGE));
            Conta contaEncontrada = buscarPorNumeroConta(numeroConta);
            excluirConta(contaEncontrada);
        }
    }

    protected void processarSaque() {
        String[] tiposContaS = {"Titular da conta", "Numero de conta"};
        int escolha = JOptionPane.showOptionDialog(null, "Por favor, informe o tipo de busca que deseja fazer.", "Saque", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiposContaS, null);
        if (escolha == 0) {
            String titular = JOptionPane.showInputDialog(null, "Titular:", "Visualizar Conta Por Titular", JOptionPane.PLAIN_MESSAGE);
            Conta contaEncontrada = buscarPorTitular(titular);
            saque(contaEncontrada);
            print(contaEncontrada);
        } else {
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da Conta:", "Visualizar Conta Por Numero da Conta", JOptionPane.PLAIN_MESSAGE));
            Conta contaEncontrada = buscarPorNumeroConta(numeroConta);
            saque(contaEncontrada);
            print(contaEncontrada);
        }
    }

    protected void processarDeposito() {
        String[] tiposContaD = {"Titular da conta", "Numero de conta"};
        int escolha = JOptionPane.showOptionDialog(null, "Por favor, informe o tipo de busca que deseja fazer.", "Visualizar Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiposContaD, null);
        if (escolha == 0) {
            String titular = JOptionPane.showInputDialog(null, "Titular:", "Visualizar Conta Por Titular", JOptionPane.PLAIN_MESSAGE);
            Conta contaEncontrada = buscarPorTitular(titular);
            deposito(contaEncontrada);
            print(contaEncontrada);
        } else {
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da Conta:", "Visualizar Conta Por Numero da Conta", JOptionPane.PLAIN_MESSAGE));
            Conta contaEncontrada = buscarPorNumeroConta(numeroConta);
            deposito(contaEncontrada);
            print(contaEncontrada);
        }
    }

    protected void processarEmprestimo() {
        String[] tiposContaEm = {"Titular da conta", "Numero de conta"};
        int escolha = JOptionPane.showOptionDialog(null, "Por favor, informe o tipo de busca que deseja fazer.", "Visualizar Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiposContaEm, null);
        if (escolha == 0) {
            String titular = JOptionPane.showInputDialog(null, "Titular:", "Visualizar Conta Por Titular", JOptionPane.PLAIN_MESSAGE);
            Conta contaEncontrada = buscarPorTitular(titular);
            emprestimo(contaEncontrada);
            print(contaEncontrada);
        } else {
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da Conta:", "Visualizar Conta Por Numero da Conta", JOptionPane.PLAIN_MESSAGE));
            Conta contaEncontrada = buscarPorNumeroConta(numeroConta);
            emprestimo(contaEncontrada);
            print(contaEncontrada);
        }
    }

    protected void processarVizualizar() {
        String[] tiposContaV = {"Titular da conta", "Numero de conta"};
        int escolha = JOptionPane.showOptionDialog(null, "Para começar, por favor, informe o tipo de busca que deseja fazer.", "Visualizar Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiposContaV, null);
        if (escolha == 0) {
            String titular = JOptionPane.showInputDialog(null, "Titular:", "Visualizar Conta Por Titular", JOptionPane.PLAIN_MESSAGE);
            Conta contaEncontrada = buscarPorTitular(titular);
            print(contaEncontrada);
        } else {
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Titular:", "Visualizar Conta Por Numero da Conta", JOptionPane.PLAIN_MESSAGE));
            Conta contaEncontrada = buscarPorNumeroConta(numeroConta);
            print(contaEncontrada);
        }
    }
    protected void processarPagamentoEmprestimo(){
        String[] tiposContaEm = {"Titular da conta", "Numero de conta"};
        int escolha = JOptionPane.showOptionDialog(null, "Por favor, informe o tipo de busca que deseja fazer.", "Visualizar Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiposContaEm, null);
        if (escolha == 0) {
            String titular = JOptionPane.showInputDialog(null, "Titular:", "Visualizar Conta Por Titular", JOptionPane.PLAIN_MESSAGE);
            Conta contaEncontrada = buscarPorTitular(titular);
            pagarEmprestimo(contaEncontrada);
            print(contaEncontrada);
        } else {
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da Conta:", "Visualizar Conta Por Numero da Conta", JOptionPane.PLAIN_MESSAGE));
            Conta contaEncontrada = buscarPorNumeroConta(numeroConta);
            pagarEmprestimo(contaEncontrada);
            print(contaEncontrada);
        }
    }

    protected Conta buscarPorTitular(String titular) {
        for (Conta conta : contas) {
            if (conta.getTitular().equalsIgnoreCase(titular)) {
                return conta;
            }
        }
        return null;
    }

    protected Conta buscarPorNumeroConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        JOptionPane.showMessageDialog(null, "Conta nao encotrada");
        return null;
    }

    protected void excluirConta(Conta conta) {
        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Conta não encontrada!");
            return;
        }

        if (conta.getSaldo() > 0) {
            JOptionPane.showMessageDialog(null, "Conta com saldo. Exclusão não permitida!");
            return;
        }

        if (conta.getEmprestimo() != 0) {
            JOptionPane.showMessageDialog(null, "Conta possui empréstimo. Exclusão não permitida!");
            return;
        }

        contas.remove(conta);
        JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
    }

    protected void print(Conta contaEncontrada) {
        double saldo = contaEncontrada.getSaldo();
        int numero = contaEncontrada.getNumeroConta();
        String tipo = contaEncontrada.getTipo();
        String nomeTitular = contaEncontrada.getTitular();
        String emprestimo = "";
        if (Objects.equals(tipo, "PF") || Objects.equals(tipo, "PJ")) {
            emprestimo = "\nSeu limite de empréstimo é de R$" + String.format("%.2f",contaEncontrada.getLimite()) + "\nSeu limite disponivel é de R$" + contaEncontrada.getEmprestimo();

        }
        JOptionPane.showMessageDialog(null, "Titular: " + nomeTitular + "\nTipo da conta: " + tipo + "\nSaldo atual: R$" + String.format("%.2f",saldo) + "\nNumero da conta : " + numero + emprestimo);

    }

    protected void saque(Conta conta) {
        if (conta.getSaldo() == 0) {
            JOptionPane.showMessageDialog(null, "Voce nao possui saldo insuficiente para realizar o saque!!");
            return;
        }
        double valorSaque = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor:", "Saque", JOptionPane.PLAIN_MESSAGE));
        if (conta.getSaldo() < valorSaque) {
            JOptionPane.showMessageDialog(null, "Voce nao possui saldo suficiente para realizar o saque!!");
            return;
        }
        conta.addSacar(valorSaque);
        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
    }

    protected void deposito(Conta conta) {
        double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor:", "Deposito", JOptionPane.PLAIN_MESSAGE));
        conta.addDeposito(valorDeposito);
        JOptionPane.showMessageDialog(null, "Deposito realizado com sucesso!");
    }

    protected void emprestimo(Conta conta) {
        double valorEmprestimo = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor:", "Empréstimo", JOptionPane.PLAIN_MESSAGE));
        conta.addEmprestimo(valorEmprestimo);
    }
    protected void pagarEmprestimo(Conta conta){
        conta.addpagarEmprestimo();
    }
}