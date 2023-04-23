public class Player implements Comparable<Player> {
    private int id;
    private String role;
    private int pontuacaoHabilidade;

    public Player(int id, String role, int pontuacaoHabilidade) {
        this.id = id;
        this.role = role;
        this.pontuacaoHabilidade = pontuacaoHabilidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPontuacaoHabilidade() {
        return pontuacaoHabilidade;
    }

    public void setPontuacaoHabilidade(int pontuacaoHabilidade) {
        this.pontuacaoHabilidade = pontuacaoHabilidade;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", pontuacaoHabilidade=" + pontuacaoHabilidade +
                '}';
    }
    
    @Override
    public int compareTo(Player outro) {
        return outro.getPontuacaoHabilidade() - this.getPontuacaoHabilidade();
    }
}