public class Produto {

    private int codigo;
    private String nome;
    private int quantidade;
    private double preco;

    public Produto(int codigo, String nome, int quantidade, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
               " | Nome: " + nome +
               " | Quantidade: " + quantidade +
               " | Preço: R$ " + String.format("%.2f", preco);
    }
}