import java.util.*;
import java.io.*;

public class Main {
    static int[] tamanhos = {1000, 10000, 100000};
    static int[] tamanhosDados = {1_000_000, 5_000_000, 20_000_000};
    static long[] seeds = {42L, 43L, 44L};
    static FuncaoHash[] funcoes = {
        new HashResto(), new HashMultiplicacao(), new HashDobramento()
    };

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.csv"));
        writer.write("FuncaoHash,TamanhoTabela,TamanhoDados,TempoInsercao(ms),Colisoes,TempoBusca(ms),Comparacoes\n");

        for (int f = 0; f < funcoes.length; f++) {
            FuncaoHash funcao = funcoes[f];
            for (int t = 0; t < tamanhos.length; t++) {
                int tamanhoTabela = tamanhos[t];
                for (int d = 0; d < tamanhosDados.length; d++) {
                    int tamanhoDados = tamanhosDados[d];
                    long seed = seeds[d];
                    System.out.println("Rodando: Funcao " + f + ", Tabela " + tamanhoTabela + ", Dados " + tamanhoDados);

                    List<Registro> dados = GeradorDados.gerar(tamanhoDados, seed);

                    TabelaHash tabela = new TabelaHash(tamanhoTabela, funcao);
                    long ini = System.currentTimeMillis();
                    for (Registro r : dados) {
                        tabela.inserir(r);
                    }
                    long fim = System.currentTimeMillis();
                    long tempoInsercao = fim - ini;
                    int colisoes = tabela.colisoes;

                    tabela.resetContadores();
                    Random rand = new Random(seed);
                    List<String> buscas = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        buscas.add(dados.get(rand.nextInt(dados.size())).getCodigo());
                    }

                    ini = System.currentTimeMillis();
                    for (String codigo : buscas) {
                        tabela.buscar(codigo);
                    }
                    fim = System.currentTimeMillis();
                    long tempoBusca = fim - ini;
                    int comparacoes = tabela.comparacoes;

                    writer.write(funcao.getClass().getSimpleName() + "," + tamanhoTabela + "," + tamanhoDados + "," + tempoInsercao + "," + colisoes + "," + tempoBusca + "," + comparacoes + "\n");
                }
            }
        }

        writer.close();
        System.out.println("Todos os testes foram concluídos.");
    }
}
