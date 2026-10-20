package com.escola.financeiro.principal;

import com.escola.financeiro.modelo.Transacao;
import com.escola.financeiro.servico.ServicoPlanilha;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        ServicoPlanilha planilha = new ServicoPlanilha();

        System.out.println("=== TESTANDO SEGURANÇA DO SISTEMA ===\n");

        // 🟢 CADASTRANDO UMA ENTRADA VÁLIDA
        try {
            BigDecimal valorMensalidade = new BigDecimal("450.00");
            Transacao t1 = new Transacao("Mensalidade - Aluno Lucas", valorMensalidade, "ENTRADA");
            planilha.salvarTransacao(t1);
            System.out.println("Sucesso: " + t1.getValorFormatado() + " arquivado.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------\n");

        // 🔴 TENTATIVA DE FRAUDE/ERRO: Cadastrar valor negativo
        try {
            System.out.println("Tentando injetar um valor inválido de R$ -100,00...");
            BigDecimal valorInvalido = new BigDecimal("-100.00");

            // O sistema vai travar aqui e pular direto para o 'catch'
            Transacao t2 = new Transacao("Mensalidade Invalida", valorInvalido, "ENTRADA");
            planilha.salvarTransacao(t2);

        } catch (IllegalArgumentException e) {
            System.out.println("🛡️ SISTEMA BLOQUEOU A OPERAÇÃO: " + e.getMessage());
        }
    }
}
