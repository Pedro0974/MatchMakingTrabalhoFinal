import java.io.FileWriter;
import java.io.IOException;

public class Partida {
    private Equipe equipe1;
    private Equipe equipe2;
    private int pontuacaoEquipe1;
    private int pontuacaoEquipe2;

    public Partida(Equipe equipe1, Equipe equipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.pontuacaoEquipe1 = 0;
        this.pontuacaoEquipe2 = 0;
    }

    // Outros métodos da classe

    public void salvarPartida() {
        try {
            FileWriter writer = new FileWriter("partidas.txt", true);
            writer.write("Equipe 1: " + equipe1.toString() + "\n");
            writer.write("Equipe 2: " + equipe2.toString() + "\n");
            writer.write("Pontuação Equipe 1: " + pontuacaoEquipe1 + "\n");
            writer.write("Pontuação Equipe 2: " + pontuacaoEquipe2 + "\n");
            writer.write("----------------------------------\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar a partida.");
        }
    }
    
    
}