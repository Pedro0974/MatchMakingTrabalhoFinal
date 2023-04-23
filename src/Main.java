import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ListaDeJogadores listaDeJogadores = new ListaDeJogadores();
		ArrayList<Equipe> equipes = new ArrayList<>();
		ArrayList<Partida> partidas = new ArrayList<>();

		int opcao = 0;
		while (opcao != 5) {
			System.out.println("Menu:");
			System.out.println("1 - Adicionar jogador");
			System.out.println("2 - Ver lista de jogadores");
			System.out.println("3 - Começar partida");
			System.out.println("4 - Ver lista de partidas");
			System.out.println("5 - Sair");
			System.out.print("Digite a opção desejada: ");

			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.print("Digite o id do jogador: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Digite a role do jogador: ");
				String role = scanner.nextLine();
				System.out.print("Digite a Habilidade do jogador: ");
				int pontuacaoHabilidade = scanner.nextInt();
				Player jogador = new Player(id, role, pontuacaoHabilidade);
				listaDeJogadores.adicionarJogador(jogador);
				System.out.println("Jogador adicionado com sucesso!");
				break;
			case 2:
				System.out.println(listaDeJogadores.toString());
				break;
			case 3:
				// Chama o método para ler a lista de jogadores
				ListaDeEquipes.lerJogadores();

				// Chama o método para spearer as equipes
				ArrayList<Equipe> equipes1 = ListaDeEquipes.separarEquipes();

				// Verifica se foi possível separar as equipes
				if (equipes1 == null) {
					System.out.println("Não foi possível criar uma partida com as equipes disponíveis.");
				} else {
					// Salva as equipes no arquivo equipes.txt
					ListaDeEquipes.salvarListaDeEquipes(equipes1);

					// Cria a partida
					Matchmaking matchmaking = new Matchmaking(listaDeJogadores, equipes1);
					Partida partida = matchmaking.montarPartida();
					if (partida != null) {
						partidas.add(partida);
						System.out.println("Partida criada com sucesso!");
					} else {
						System.out.println("Não foi possível criar uma partida com as equipes disponíveis.");
					}
				}
				break;
			case 4:
				System.out.println(partidas.toString());
				break;
			case 5:
				System.out.println("Encerrando programa...");
				break;
			default:
				System.out.println("Opção inválida. Digite novamente.");
				break;
			}
		}
	}
}
