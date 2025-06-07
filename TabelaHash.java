import java.util.*;

public class TabelaHash {
    private List<Registro>[] tabela;
    private FuncaoHash funcaoHash;
    public int colisoes;
    public int comparacoes;

    @SuppressWarnings("unchecked")
    public TabelaHash(int tamanho, FuncaoHash funcaoHash) {
        this.tabela = new ArrayList[tamanho];
        this.funcaoHash = funcaoHash;
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArrayList<>();
        }
        this.colisoes = 0;
        this.comparacoes = 0;
    }

    public void inserir(Registro r) {
        int chave = r.getCodigoNumerico();
        int pos = funcaoHash.hash(chave, tabela.length);
        if (!tabela[pos].isEmpty()) colisoes++;
        tabela[pos].add(r);
    }

    public Registro buscar(String codigo) {
        int chave = Integer.parseInt(codigo);
        int pos = funcaoHash.hash(chave, tabela.length);
        for (Registro r : tabela[pos]) {
            comparacoes++;
            if (r.getCodigo().equals(codigo)) return r;
        }
        return null;
    }

    public void resetContadores() {
        colisoes = 0;
        comparacoes = 0;
    }
}
