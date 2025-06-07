class HashDobramento implements FuncaoHash {
    public int hash(int chave, int tamanho) {
        String s = String.format("%09d", chave);
        int soma = 0;
        for (int i = 0; i < s.length(); i += 3) {
            soma += Integer.parseInt(s.substring(i, Math.min(i + 3, s.length())));
        }
        return soma % tamanho;
    }
}
