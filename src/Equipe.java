import java.util.ArrayList;

public class Equipe {
    private ArrayList<Player> jogadores;

    public Equipe() {
        this.jogadores = new ArrayList<>();
    }

    public void adicionarJogador(Player jogador) {
        this.jogadores.add(jogador);
    }

    public boolean contemJogador(Player jogador) {
        return this.jogadores.contains(jogador);
    }

    public int getTotalHabilidade() {
        int totalHabilidade = 0;
        for (Player jogador : this.jogadores) {
            totalHabilidade += jogador.getPontuacaoHabilidade();
        }
        return totalHabilidade;
    }
    
    public ArrayList<Player> getJogadores() {
        return this.jogadores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipe:\n");
        for (Player jogador : this.jogadores) {
            sb.append(String.format("ID: %d | Nome: %s | Habilidade: %d\n", jogador.getId(), jogador.getRole(), jogador.getPontuacaoHabilidade()));
        }
        return sb.toString();
    }

	
}