package com.escola.financeiro.servico;

import com.escola.financeiro.modelo.Transacao;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ServicoPlanilha {
    private static final String CAMINHO_ARQUIVO = "financeiro_escola.csv";

    public void salvarTransacao(Transacao transacao) {
        // O uso do try-with-resources garante que o arquivo seja FECHADO mesmo se ocorrer um erro técnico
        try (FileWriter fw = new FileWriter(CAMINHO_ARQUIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(transacao.formatarParaCSV());
            System.out.println("💾 [Backup Automático] Transação registrada no arquivo com sucesso.");

        } catch (IOException e) {
            // Em um sistema real, aqui você salvaria esse erro em um arquivo de log (ex: log.txt)
            System.err.println("❌ CRÍTICO: Falha ao gravar na planilha. Verifique se o arquivo está aberto no Excel.");
            throw new RuntimeException("Não foi possível salvar os dados financeiros de forma segura.", e);
        }
    }
}
