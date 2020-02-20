import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class CoinChange {

    // C(N,S) = C(N, S') - C(N - Ms, S)
    /*
        Os casos base são:
        C(N, S) = 1, N = 0 --> como o valor é zero, só tem uma solução = não selecionar nenhuma moeda, troco = 0 moedas
        C(N, S) = 0, N < 0 --> não existe solução válida, pois o valor é negativo (inválido)
        C(N, S) = 0, N >=1, S = {} --> não temos moedas para dar o troco, portanto não tem solução
     */

    static int calculate(int N, List<Integer> moedas, Stack<Integer> sol, List<Integer[]> conj_solucoes) {
        if ((N<0) || (moedas.size()==0)) return 0;
        if (N==0) {
            // acrescenta o conjunto "sol" as solucoes possiveis
            conj_solucoes.add( sol.toArray(new Integer[sol.size()]));
            return 1;
        }

        Integer moeda = moedas.get(0); // obtem uma novo moeda para ver se faz parte da solucao
        sol.push(moeda); // acrescento ao conjunto corrente que representa a solucao
        int v1 = calculate(N-moeda, moedas, sol, conj_solucoes); // 1o termo da equacao

        sol.pop(); // como vou tentar a 2a opcao, que nao considera a moeda, retiro do conj. corrente de solucao

        List<Integer> moedas_novo = new ArrayList<>(moedas);
        moedas_novo.remove(moeda);
        int v2 = calculate(N, moedas_novo, sol, conj_solucoes); // 2o termo da equacao

        return v1 + v2; // retorna a equacao de recorrencia
    }

    public static void main(String[] args) {
        int N = 4; // valor do troco
        List<Integer> moedas = new ArrayList<>(Arrays.asList(1, 2, 3));
        Stack<Integer> sol = new Stack<>();
        List<Integer[]> conj_solucoes = new ArrayList<>();

        int num = calculate(N, moedas, sol, conj_solucoes);
        System.out.println("Localizadas " + num + " maneiras de obter o troco.");
        System.out.println("Elas sao:");
        for(Integer[] s : conj_solucoes)
            System.out.println("* " + Arrays.toString(s));
    }
}