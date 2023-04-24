import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ListaDeEquipes {
	private static ArrayList<Player> jogadores = new ArrayList<>();

	public static void lerJogadores() {
	    try {
	        File file = new File("jogadores.txt");
	        Scanner scanner = new Scanner(file);
	        while (scanner.hasNextLine()) {
	            String linha = scanner.nextLine();
	            String[] campos = linha.split(",");
	            if (campos.length >= 3) {
	                String idStr = campos[0].replace("Jogador{id=", "").trim();
	                StringBuilder numeros = new StringBuilder();
	                for (char c : idStr.toCharArray()) {
	                    if (Character.isDigit(c)) {
	                        numeros.append(c);
	                    }
	                }
	                int id = Integer.parseInt(numeros.toString());

	                String role = campos[1];

	                String habilidadeStr = campos[2].replace("pontuacaoHabilidade=", "").trim();
	                numeros = new StringBuilder();
	                for (char c : habilidadeStr.toCharArray()) {
	                    if (Character.isDigit(c)) {
	                        numeros.append(c);
	                    }
	                }
	                int habilidade = Integer.parseInt(numeros.toString());

	                Player jogador = new Player(id, role, habilidade);
	                jogadores.add(jogador);
	            }
	        }
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("Arquivo jogadores.txt não encontrado.");
	    }
	}
    
    public static ArrayList<Equipe> separarEquipes() {
        // ordena a lista de jogadores por habilidade
        Collections.sort(ListaDeEquipes.jogadores);

        // cria um conjunto para armazenar as roles dos jogadores em cada equipe
        Set<String> rolesEquipe1 = new HashSet<>();
        Set<String> rolesEquipe2 = new HashSet<>();

        // cria as duas equipes
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe();

        // percorre a lista de jogadores em ordem decrescente de habilidade
        for (int i = ListaDeEquipes.jogadores.size() - 1; i >= 0; i--) {
            Player jogador = ListaDeEquipes.jogadores.get(i);

            // verifica se o jogador já está em alguma equipe
            if (equipe1.contemJogador(jogador) || equipe2.contemJogador(jogador)) {
                continue;
            }

            // verifica se o jogador pode ser adicionado à equipe 1
            if (equipe1.getTotalHabilidade() + jogador.getPontuacaoHabilidade() <= equipe2.getTotalHabilidade() + 1000) {
                if (!rolesEquipe1.contains(jogador.getRole())) {
                    equipe1.adicionarJogador(jogador);
                    rolesEquipe1.add(jogador.getRole());
                }
            }

            // verifica se o jogador pode ser adicionado à equipe 2
            else if (equipe2.getTotalHabilidade() + jogador.getPontuacaoHabilidade() <= equipe1.getTotalHabilidade() + 1000) {
                if (!rolesEquipe2.contains(jogador.getRole())) {
                    equipe2.adicionarJogador(jogador);
                    rolesEquipe2.add(jogador.getRole());
                }
            }
        }

        // cria um ArrayList para armazenar as duas equipes e retorna
        ArrayList<Equipe> equipes = new ArrayList<>();
        equipes.add(equipe1);
        equipes.add(equipe2);
        return equipes;
    }
    
    public static void salvarListaDeEquipes(ArrayList<Equipe> equipes) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("equipes.txt"));
            for (Equipe equipe : equipes) {
                writer.write(equipe.toString());
                writer.write("Total de habilidade da equipe: " + equipe.getTotalHabilidade() + "\n\n");
            }
            writer.close();
            System.out.println("Equipes salvas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar as equipes.");
            e.printStackTrace();
        }
    }
}