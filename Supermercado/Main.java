/**
 * Main.
 * @author Magarida Lopes 64557
 */

import excecoes.*;
import system.*;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    //Constantes que definem as mensagens para o utilizador
    private static final String CARRINHO_CRIADO = "Carrinho criado com sucesso.\n";
    private static final String CARRINHO_EXISTENTE = "Carrinho existente!\n";
    private static final String CARRINHO_INEXISTENTE = "Carrinho inexistente!\n";
    private static final String ARTIGO_CRIADO = "Artigo criado com sucesso.\n";
    private static final String ARTIGO_DEPOSITADO = "Artigo adicionado com sucesso.\n";
    private static final String ARTIGO_EXISTENTE = "Artigo existente!\n";
    private static final String ARTIGO_INEXISTENTE = "Artigo inexistente!\n";
    private static final String CAPACIDADE_EXCEDIDA = "Capacidade excedida!\n";
    private static final String ARTIGO_REMOVIDO = "Artigo removido com sucesso.\n";
    private static final String ARTIGO_INEXISTENTE_CARRINHO = "Artigo inexistente no carrinho!\n";
    private static final String CARRIHNO_VAZIO = "Carrinho vazio!\n";
    private static final String ADEUS = "Volte sempre.\n";

    // Comandos
    private static final String CRIAR_CARRINHO = "CARRINHO";
    private static final String CRIAR_ARTIGO = "ARTIGO";
    private static final String DEPOSITA = "DEPOSITA";
    private static final String REMOVE = "REMOVE";
    private static final String LISTA = "LISTA";
    private static final String PAGA = "PAGA";
    private static final String SAIR = "SAIR";


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Supermercado supermercado = new SupermercadoClass();
        String command;
        do {
            command = getCommand(in);
            executeCommand (command, in, supermercado);
        } while (!command.equals(SAIR));
        in.close();
    }

    /**
     * Le o novo comando a executar.
     * @param in input de onde os dados vao ser lidos.
     */
    private static String getCommand(Scanner in) {
        String command = in.next().toUpperCase();
        if (command.equals("CRIA"))
            command = in.next().toUpperCase().trim();
        return command;
    }

    /**
     * Interpretador de commandos.
     * @param command o commando a ser interpretado e executado.
     * @param in o scanner para leitura de parametros.
     * @param supermercado a classe de topo.
     * @pre command != null && in != null && supermercado != null
     */
    private static void executeCommand (String command, Scanner in, Supermercado supermercado) {
        switch (command) {
            case CRIAR_CARRINHO -> executeCriarCarrinho (in, supermercado);
            case CRIAR_ARTIGO -> executeCriarArtigo (in, supermercado);
            case DEPOSITA -> executeDeposita (in, supermercado);
            case REMOVE -> executeRemove (in, supermercado);
            case LISTA -> executeLista (in, supermercado);
            case PAGA -> executePaga (in, supermercado);
            case SAIR -> System.out.println(ADEUS);
        }
    }

    /**
     * Executa o comando "CRIA CARRINHO".
     * É criado um novo carrinho com o nome e capacidade dadas.
     * Caso ja exista um carrinho com o nome dado é enviada uma mensagem de erro.
     * @param in o scaner para leitura de parametros.
     * @param supermercado o supermercado.
     * @pre in != null && supermercado != null
     */
    private static void executeCriarCarrinho (Scanner in, Supermercado supermercado) {
        String ID = in.next();
        int capacidade = in.nextInt();
        in.nextLine();
        try {
            supermercado.addCarrinho(ID, capacidade);
            System.out.println(CARRINHO_CRIADO);
        } catch (CarroJaExistenteException e) {
            System.out.println(CARRINHO_EXISTENTE);
        }
    }

    /**
     * Executa o comando "CRIA ARTIGO".
     * É criado um novo artigo com o nome, volume e preço dados.
     * Caso ja exista um artigo com o nome dado, é enviada uma mensagem de erro.
     * @param in o scanner para leitura de parametros.
     * @param supermercado o supermercado.
     * @pre in != null && supermercado != null
     */
    private static void executeCriarArtigo (Scanner in, Supermercado supermercado) {
        String nome = in.next().trim();
        int preco = in.nextInt();
        int volume = in.nextInt();
        in.nextLine();
        try {
            supermercado.addArtigo(nome, preco, volume);
            System.out.println(ARTIGO_CRIADO);
        } catch (ArtigoJaExistenteException e) {
            System.out.println(ARTIGO_EXISTENTE);
        }
    }

    /**
     * Executa o comando "DEPOSITA".
     * É adicionado o artigo ao carrinho.
     * É enviada mensagem de erro caso o carrinho ou o artigo nao existam,
     * e se o carrinho ja se encontrar demasiado cheio.
     * @param in o scanner para leitura de parametros.
     * @param supermercado o supermercado.
     * @pre in != null && supermercado != null
     */
    private static void executeDeposita (Scanner in, Supermercado supermercado) {
        String artigo = in.next().trim();
        String carrinho = in.next().trim();
        in.nextLine();
        try {
            supermercado.depositarArtigo(artigo, carrinho);
            System.out.println(ARTIGO_DEPOSITADO);
        } catch (ArtigoNaoExistenteException e) {
            System.out.println(ARTIGO_INEXISTENTE);
        } catch (CarroNaoExistenteException e) {
            System.out.println(CARRINHO_INEXISTENTE);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(CAPACIDADE_EXCEDIDA);
        }
    }

    /**
     * Executa o comando "REMOVE".
     * Remove um artigo do carrinho.
     * É enviada mensagem de erro caso o carrinho nao existir ou
     * se o artigo nao existir no carrinho.
     * @param in o scanner para leitura de parametros.
     * @param supermercado o supermercado.
     * @pre in != null && supermercado != null
     */
    private static void executeRemove (Scanner in, Supermercado supermercado) {
        String artigo = in.next().trim();
        String carrinho = in.next().trim();
        in.nextLine();
        try {
            supermercado.removeArtigo(artigo, carrinho);
            System.out.println(ARTIGO_REMOVIDO);
        } catch (CarroNaoExistenteException e) {
            System.out.println(CARRINHO_INEXISTENTE);
        } catch (ArtigoNaoExistenteException e) {
            System.out.println(ARTIGO_INEXISTENTE_CARRINHO);
        }
    }

    /**
     * Executa o comando "LISTA".
     * Lista o conteudo de um carrinho.
     * É enviada mensagem de erro caso o carrinho nao exista.
     * @param in o scanner para leitura de parametros.
     * @param supermercado o supermercado.
     * @pre in != null && supermercado != null
     */
    private static void executeLista (Scanner in, Supermercado supermercado) {
        String carrinho = in.next().trim();
        in.nextLine();
        try {
            Iterator <Artigo> it = supermercado.listaArtigos(carrinho);
            while (it.hasNext()) {
                Artigo a = it.next();
                System.out.println(a.getNome() + " " + a.getPreco());
            }
            System.out.println();
        } catch (CarrinhoVazioException e) {
            System.out.println(CARRIHNO_VAZIO);
        } catch (CarroNaoExistenteException e) {
            System.out.println(CARRINHO_INEXISTENTE);
        }
    }

    /**
     * Executa o comando "PAGA".
     * O conteudo de um carrinho é pago e o mesmo esvaziado.
     * É enviada mensagem de erro caso o carrinho nao exista ou se o
     * mesmo estiver vazio.
     * @param in o scanner para leitura de parametros.
     * @param supermercado o supermercado.
     * @pre in != null && supermercado != null
     */
    private static void executePaga (Scanner in, Supermercado supermercado) {
        String carrinho = in.next().trim();
        in.nextLine();
        try {
            System.out.println(supermercado.pagarConta(carrinho));
            System.out.println();
        } catch (CarroNaoExistenteException e) {
            System.out.println(CARRINHO_INEXISTENTE);
        } catch (CarrinhoVazioException e) {
            System.out.println(CARRIHNO_VAZIO);
        }
    }


}