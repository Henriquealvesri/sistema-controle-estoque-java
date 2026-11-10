import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Estoque {

    private ArrayList<Produto> produtos = new ArrayList<>();

    public Estoque() {
        carregarProdutos();
    }

    public void adicionarProduto(Produto produto) {

        if (buscarProduto(produto.getCodigo()) != null) {

            System.out.println(
                    "Erro: o código "
                    + produto.getCodigo()
                    + " já está cadastrado.");

            return;
        }

        produtos.add(produto);

        salvarProduto(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public void listarProdutos() {

        if (produtos.isEmpty()) {

            System.out.println("Nenhum produto cadastrado.");

            return;
        }

        for (Produto produto : produtos) {

            System.out.println(produto);
        }
    }

    public Produto buscarProduto(int codigo) {

        for (Produto produto : produtos) {

            if (produto.getCodigo() == codigo) {

                return produto;
            }
        }

        return null;
    }

    public void removerProduto(int codigo) {

        Produto produto = buscarProduto(codigo);

        if (produto != null) {

            produtos.remove(produto);

            salvarTodosProdutos();

            System.out.println("Produto removido com sucesso!");

        } else {

            System.out.println("Produto não encontrado.");
        }
    }

    public void atualizarEstoque(int codigo, int quantidade) {

        Produto produto = buscarProduto(codigo);

        if (produto != null) {

            produto.setQuantidade(quantidade);

            salvarTodosProdutos();

            System.out.println("Estoque atualizado!");

        } else {

            System.out.println("Produto não encontrado.");
        }
    }

    public void mostrarValorTotalEstoque() {

        double total = 0;

        for (Produto produto : produtos) {

            double subtotal =
                    produto.getQuantidade()
                    * produto.getPreco();

            System.out.printf(
                    "%s -> %d x %.2f = %.2f%n",
                    produto.getNome(),
                    produto.getQuantidade(),
                    produto.getPreco(),
                    subtotal);

            total += subtotal;
        }

        System.out.println("--------------------------");

        System.out.printf(
                "Valor total do estoque: R$ %.2f%n",
                total);
    }

    public void exportarRelatorio() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("relatorio.txt"));

            writer.write("=================================");
            writer.newLine();

            writer.write("RELATORIO DE ESTOQUE");
            writer.newLine();

            writer.write("=================================");
            writer.newLine();
            writer.newLine();

            double total = 0;

            for (Produto produto : produtos) {

                double subtotal =
                        produto.getQuantidade()
                        * produto.getPreco();

                writer.write("Codigo: " + produto.getCodigo());
                writer.newLine();

                writer.write("Nome: " + produto.getNome());
                writer.newLine();

                writer.write("Quantidade: " + produto.getQuantidade());
                writer.newLine();

                writer.write(
                        String.format(
                                "Preco: R$ %.2f",
                                produto.getPreco()));

                writer.newLine();

                writer.write(
                        String.format(
                                "Subtotal: R$ %.2f",
                                subtotal));

                writer.newLine();

                writer.write("---------------------------------");
                writer.newLine();

                total += subtotal;
            }

            writer.newLine();

            writer.write(
                    String.format(
                            "VALOR TOTAL DO ESTOQUE: R$ %.2f",
                            total));

            writer.close();

            System.out.println(
                    "Relatorio exportado com sucesso!");

        } catch (IOException e) {

            System.out.println(
                    "Erro ao gerar relatorio.");
        }
    }

    private void salvarProduto(Produto produto) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("produtos.txt", true));

            writer.write(
                    produto.getCodigo() + ";" +
                    produto.getNome() + ";" +
                    produto.getQuantidade() + ";" +
                    produto.getPreco());

            writer.newLine();

            writer.close();

        } catch (IOException e) {

            System.out.println("Erro ao salvar produto.");
        }
    }

    private void salvarTodosProdutos() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("produtos.txt"));

            for (Produto produto : produtos) {

                writer.write(
                        produto.getCodigo() + ";" +
                        produto.getNome() + ";" +
                        produto.getQuantidade() + ";" +
                        produto.getPreco());

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Erro ao atualizar arquivo.");
        }
    }

    private void carregarProdutos() {

        try {

            File arquivo = new File("produtos.txt");

            if (!arquivo.exists()) {

                return;
            }

            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {

                String linha = leitor.nextLine();

                String[] dados = linha.split(";");

                int codigo = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int quantidade = Integer.parseInt(dados[2]);
                double preco = Double.parseDouble(dados[3]);

                Produto produto =
                        new Produto(
                                codigo,
                                nome,
                                quantidade,
                                preco);

                produtos.add(produto);
            }

            leitor.close();

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Arquivo não encontrado.");
        }
    }
}                