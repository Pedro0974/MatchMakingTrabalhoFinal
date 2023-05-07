import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListaDeJogadores {
	private static ArrayList<Player> jogadores = new ArrayList<>();

    public ListaDeJogadores() {
        ListaDeJogadores.jogadores = new ArrayList<>();
    }

    public void adicionarJogador(Player jogador) {
        ListaDeJogadores.jogadores.add(jogador);
        salvarListaDeJogadores();
    }
    
    public static Player getJogadorById(int id) {
        for (Player jogador : jogadores) {
            if (jogador.getId() == id) {
                return jogador;
            }
        }
        return null;
    }

    private void salvarListaDeJogadores() {
        try {
            FileWriter writer = new FileWriter("jogadores.txt");
            for (Player jogador : jogadores) {
                writer.write(jogador.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar a lista de jogadores.");
        }
    }

    public ArrayList<Player> getJogadores() {
        return ListaDeJogadores.jogadores;
    }
    
    public String toString() {
        String result = "";
        for (Player jogador : jogadores) {
            result += String.format("Id: %d\nRole: %s\nPontuação de habilidade: %d\n\n",
                                    jogador.getId(), jogador.getRole(), jogador.getPontuacaoHabilidade());
        }
        return result;
    } 
}
