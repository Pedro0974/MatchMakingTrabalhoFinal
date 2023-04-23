import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Matchmaking {
	private ArrayList<Equipe> equipes;

	public Matchmaking(ListaDeJogadores listaDeJogadores, ArrayList<Equipe> equipes2) {
		this.equipes = new ArrayList<>();
	}

	public void lerEquipes() {
		try {
			File file = new File("equipes.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				String[] campos = linha.split(",");
				Equipe equipe = new Equipe();
				for (int i = 0; i < campos.length; i++) {
					int id = Integer.parseInt(campos[i]);
					Player jogador = ListaDeJogadores.getJogadorById(id);
					equipe.adicionarJogador(jogador);
				}
				this.equipes.add(equipe);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo equipes.txt não encontrado.");
		}
	}

	public Partida montarPartida() {
		// seleciona as duas equipes com menor diferença de poder total
		Equipe equipe1 = null;
		Equipe equipe2 = null;
		int menorDiferenca = Integer.MAX_VALUE;
		for (int i = 0; i < this.equipes.size() - 1; i++) {
			for (int j = i + 1; j < this.equipes.size(); j++) {
				Equipe e1 = this.equipes.get(i);
				Equipe e2 = this.equipes.get(j);
				int diferenca = Math.abs(e1.getTotalHabilidade() - e2.getTotalHabilidade());
				if (diferenca < menorDiferenca && diferenca <= 1000) {
					equipe1 = e1;
					equipe2 = e2;
					menorDiferenca = diferenca;
				}
			}
		}

		// cria e retorna uma nova partida com as duas equipes selecionadas
		return new Partida(equipe1, equipe2);
	}
}