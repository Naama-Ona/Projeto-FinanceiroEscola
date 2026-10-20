package com.escola.financeiro.servico;

import com.escola.financeiro.modelo.Aluno;

public class ServicoBoleto {

    // Gera a lógica do boleto vinculando diretamente a um Aluno (Objeto completo)
    public String emitirBoletoMatricula(Aluno aluno, double valor) {
        System.out.println("\n--- 📑 GERANDO BOLETO BANCÁRIO ---");
        System.out.println("Sacado: " + aluno.getNome() + " (Matrícula: " + aluno.getMatricula() + ")");
        System.out.println("Enviar para: " + aluno.getEmail());

        // Simulação de geração de código de barras seguro baseado nos dados
        String codigoBarras = "34191.79001 01043.513184 91020.150008 7 987600000" + String.format("%.0f", valor);
        System.out.println("Código gerado: " + codigoBarras);

        return codigoBarras;
    }
}
