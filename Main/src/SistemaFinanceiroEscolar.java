import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SistemaFinanceiroEscolar {

    // Modelo para representar uma transação financeira
    static class Transacao {
        private String id;
        private String descricao;
        private double valor;
        private String tipo; // "ENTRADA" ou "SAIDA"
        private LocalDateTime dataHora;

        public Transacao(String id, String descricao, double valor, String tipo) {
            this.id = id;
            this.descricao = descricao;
            this.valor = valor;
            this.tipo = tipo;
            this.dataHora = LocalDateTime.now(); // Captura automática do momento do pagamento
        }

        public String formatarParaCSV() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return String.format("%s;%s;%.2f;%s;%s", id, descricao, valor, tipo, dataHora.format(formatter));
        }
    }

    private List<Transacao> historicoTransacoes = new ArrayList<>();
    private static final String CAMINHO_PLANILHA = "controle_financeiro.csv";

    // 1. Identificação de Entrada e Saída
    public void registrarTransacao(String id, String descricao, double valor, String tipo) {
        if (!tipo.equalsIgnoreCase("ENTRADA") && !tipo.equalsIgnoreCase("SAIDA")) {
            System.out.println("❌ Tipo inválido! Use 'ENTRADA' ou 'SAIDA'.");
            return;
        }

        Transacao novaTransacao = new Transacao(id, descricao, valor, tipo.toUpperCase());
        historicoTransacoes.add(novaTransacao);
        System.out.println("✅ Transação registrada com sucesso: " + descricao);

        // 2. Salvamento Automático em Planilha
        salvarEmPlanilha(novaTransacao);
    }

    // Gravação automática e confiável utilizando manipulação de arquivos nativa do Java
    private void salvarEmPlanilha(Transacao transacao) {
        try (FileWriter fw = new FileWriter(CAMINHO_PLANILHA, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Se o arquivo estiver vazio, cria o cabeçalho antes (opcional para organização)
            pw.println(transacao.formatarParaCSV());
            System.out.println("💾 Dados salvos automaticamente em: " + CAMINHO_PLANILHA);

        } catch (IOException e) {
            System.err.println("❌ Erro crítico ao salvar na planilha: " + e.getMessage());
        }
    }

    // 3. Simulação de Emissão de Boleto Automático
    public void emitirBoletoMatricula(String nomeAluno, String matricula, double valor) {
        System.out.println("\n--- 📝 EMISSÃO DE BOLETO AUTOMÁTICO ---");
        System.out.println("Beneficiário: Escola Modelo Java S.A.");
        System.out.println("Pagador: " + nomeAluno + " | Matrícula: " + matricula);
        System.out.println("Valor: R$ " + valor);

        // Simulação de linha digitável segura
        String codigoBarras = "34191.79001 01043.513184 91020.150008 7 987600000" + String.format("%.0f", valor);
        System.out.println("Linha Digitável: " + codigoBarras);
        System.out.println("---------------------------------------\n");

        // Após emitir o boleto no sistema real, o pagamento geraria a entrada:
        registrarTransacao(matricula, "Pagamento Matrícula - " + nomeAluno, valor, "ENTRADA");
    }

    public static void main(String[] args) {
        SistemaFinanceiroEscolar sistema = new SistemaFinanceiroEscolar();

        System.out.println("=== INICIANDO SISTEMA FINANCEIRO ESCOLAR ===\n");

        // Emitindo boleto e registrando entrada automática
        sistema.emitirBoletoMatricula("Ana Silva", "MAT-2026-001", 450.00);

        // Registrando uma saída (exemplo: compra de material escolar)
        sistema.registrarTransacao("SAIDA-001", "Compra de Giz e Apagadores", 120.50, "SAIDA");
    }
}
