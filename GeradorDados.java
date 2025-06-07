import java.util.*;
class GeradorDados {
    public static List<Registro> gerar(int quantidade, long seed) {
        Random rand = new Random(seed);
        List<Registro> lista = new ArrayList<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            String codigo = String.format("%09d", rand.nextInt(1_000_000_000));
            lista.add(new Registro(codigo));
        }
        return lista;
    }
}