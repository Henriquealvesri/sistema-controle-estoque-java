import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE ESTOQUE =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Buscar Produto");
            System.out.println("4 - Atualizar Estoque");
            System.out.println("5 - Remover Produto");
            System.out.println("6 - Valor Total do Estoque");
            System.out.println("7 - Exportar Relatório");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    try {

                        System.out.print("Código: ");
                        int codigo = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Preço: ");
                        String precoTexto = scanner.nextLine();

                        double preco = Double.parseDouble(
                                precoTexto.replace(",", "."));

                        Produto produto =
                                new Produto(
                                        codigo,
                                        nome,
                                        quantidade,
                                        preco);

                        estoque.adicionarProduto(produto);

                    } catch (Exception e) {

                        System.out.println(
                                "Erro: verifique os dados digitados.");

                        scanner.nextLine();
                    }

                    break;

                case 2:

                    estoque.listarProdutos();

                    break;

                case 3:

                    System.out.print("Informe o código: ");

                    int codigoBusca = scanner.nextInt();

                    Produto encontrado =
                            estoque.buscarProduto(codigoBusca);

                    if (encontrado != null) {

                        System.out.println(encontrado);

                    } else {

                        System.out.println(
                                "Produto não encontrado.");
                    }

                    break;

                case 4:

                    System.out.print("Código do produto: ");

                    int codigoAtualizar =
                            scanner.nextInt();

                    System.out.print(
                            "Nova quantidade: ");

                    int novaQuantidade =
                            scanner.nextInt();

                    estoque.atualizarEstoque(
                            codigoAtualizar,
                            novaQuantidade);

                    break;

                case 5:

                    System.out.print(
                            "Código do produto: ");

                    int codigoRemover =
                            scanner.nextInt();

                    estoque.removerProduto(
                            codigoRemover);

                    break;

                case 6:

                    estoque.mostrarValorTotalEstoque();

                    break;

                case 7:

                    estoque.exportarRelatorio();

                    break;

                case 0:

                    System.out.println(
                            "Sistema encerrado.");

                    break;

                default:

                    System.out.println(
                            "Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}