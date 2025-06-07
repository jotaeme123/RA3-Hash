public class HashResto implements FuncaoHash {
    public int hash(int chave, int tamanho) {
        return chave % tamanho;
    }
}
