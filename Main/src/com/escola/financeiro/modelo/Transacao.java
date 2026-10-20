package com.escola.financeiro.modelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transacao {
    private final String descricao;
    private final BigDecimal valor; // Precisão absoluta para dinheiro
    private final String tipo;      // "ENTRADA" ou "SAIDA"
    private final LocalDateTime dataHora;

    public Transacao(String descricao, BigDecimal valor, String tipo) {
        // Regra de Segurança 1: Validar se a descrição está vazia
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da transação não pode estar vazia.");
        }

        // Regra de Segurança 2: Validar se o valor é nulo, negativo ou zero
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");
        }

        // Regra de Segurança 3: Validar se o tipo está correto
        if (tipo == null || (!tipo.equalsIgnoreCase("ENTRADA") && !tipo.equalsIgnoreCase("SAIDA"))) {
            throw new IllegalArgumentException("O tipo da transação deve ser exatamente 'ENTRADA' ou 'SAIDA'.");
        }

        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo.toUpperCase();
        this.dataHora = LocalDateTime.now(); // Captura automática imutável
    }

    // Formata a linha perfeitamente para o Excel/CSV sem quebrar o formato decimal
    public String formatarParaCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("%s;%s;%s;%s", descricao, valor.toString(), tipo, dataHora.format(formatter));
    }

    // Getters normais (repare que não existem "Setters", tornando a classe Imutável)
    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
    public String getTipo() { return tipo; }
    public LocalDateTime getDataHora() { return dataHora; }

    // Exibe o valor formatado em formato de moeda local (Ex: R$ 450,00)
    public String getValorFormatado() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valor);
    }
}


