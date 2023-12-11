package Contas;

import javax.swing.*;

public class ContaPJ extends Conta {
    protected final double limite;
    protected double saldoEmprestimo;
    protected int quantidadeParcelas;
    protected double valorParcelas;
    protected double total;

    public ContaPJ(String titular) {
        super(titular, "PF");
        this.limite = 2500.00;
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

    protected double getValorParcelas() {
        return valorParcelas;
    }

    protected void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    protected double getTotal() {
        return total;
    }

    protected void setTotal(double total, double impostos) {
        this.total = total + impostos + (total * 0.15);
        this.saldoEmprestimo = total + impostos + (total * 0.15);
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

    protected double impostoDoAmor(double total) {
        // Imposto sobre Serviços (ISS) - alíquota municipal
        final double aliquotaISS = 5.0; // Exemplo de alíquota do ISS
        double valorISS = total * aliquotaISS / 100;

        // Imposto de Renda da Pessoa Jurídica (IRPJ) - alíquota federal
        final double aliquotaIRPJ = 15.0; // Exemplo de alíquota do IRPJ
        double valorIRPJ = total * aliquotaIRPJ / 100;

        // Contribuição Social sobre o Lucro Líquido (CSLL) - alíquota federal
        final double aliquotaCSLL = 9.0; // Exemplo de alíquota da CSLL
        double valorCSLL = total * aliquotaCSLL / 100;

        // Programa de Integração Social (PIS) - alíquota federal
        final double aliquotaPIS = 1.65; // Exemplo de alíquota do PIS
        double valorPIS = total * aliquotaPIS / 100;

        // Contribuição para o Financiamento da Seguridade Social (COFINS) - alíquota federal
        final double aliquotaCOFINS = 7.6; // Exemplo de alíquota da COFINS
        double valorCOFINS = total * aliquotaCOFINS / 100;

        // Imposto sobre Circulação de Mercadorias e Serviços (ICMS) - alíquota estadual
        final double aliquotaICMS = 12.0; // Exemplo de alíquota do ICMS
        double valorICMS = total * aliquotaICMS / 100;

        // Imposto sobre Produtos Industrializados (IPI) - alíquota federal
        final double aliquotaIPI = 2.5; // Exemplo de alíquota do IPI
        double valorIPI = total * aliquotaIPI / 100;

        // Contribuição Previdenciária Patronal - alíquota sobre a folha de pagamento
        final double aliquotaPrevidencia = 20.0; // Exemplo de alíquota de contribuição previdenciária
        double valorPrevidencia = total * aliquotaPrevidencia / 100;
        return valorIPI + valorICMS + valorPrevidencia + valorCOFINS + valorPIS + valorCSLL + valorISS + valorIRPJ;
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
            JOptionPane.showMessageDialog(null, "Voce nao possui limite suficiente para realizar o empréstimo!!\nLimite para empréstimo de R$" + String.format("%.2f", getEmprestimo()));
            return;
        }
        if (valor > 0 && valor <= getEmprestimo()) {
            setQuantidadeParcelas(validarValoresInt("Em quantas parcelas você deseja pagar o empréstimo?"));
            setValorParcelas(valor / getQuantidadeParcelas());
            setTotal(valor, impostoDoAmor(valor));
            JOptionPane.showMessageDialog(null, "O valor total a ser pago pelo empréstimo é de R$" + String.format("%.2f", getTotal()) + "\nSera cobrado mensalmente um valor de R$" + (getTotal() / getQuantidadeParcelas()) + " Durante " + getQuantidadeParcelas() + " Meses");
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Deseja realizar o empréstimo?", "Empréstimo", JOptionPane.YES_NO_OPTION);
            if (yesOrNo == JOptionPane.YES_OPTION) {
                if (getEmprestimo() - valor >= 0) {
                    setEmprestimo(valor);
                    depositar(valor);
                    JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
                    JOptionPane.showMessageDialog(null, "Seu saldo atual é de R$" + String.format("%.2f", getSaldo()) + "\nSeu limite para empréstimo é de R$" + String.format("%.2f", getLimite()) + "\nLimite desponivel de R$ " + String.format("%.2f", getLimite() - getTotal()));

                } else {
                    JOptionPane.showMessageDialog(null, "Empréstimo cancelado");

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Valor inválido.");
        }
    }

    protected void pagarEmprestimo() {
        if (getSaldo() < getTotal()) {
            JOptionPane.showMessageDialog(null, "Voce nao possui saldo o suficiente para pagar o empréstimo");
        } else {
            sacar(getTotal());
            quitaEmprestimo();
        }
    }
}
