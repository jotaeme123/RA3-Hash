class HashMultiplicacao implements FuncaoHash {
    private static final double A = 0.6180339887; // (raiz de 5 - 1) / 2
    public int hash(int chave, int tamanho) {
        double frac = (chave * A) % 1;
        return (int)(tamanho * frac);
    }
}