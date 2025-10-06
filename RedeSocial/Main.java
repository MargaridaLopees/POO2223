
import java.util.Scanner;

/**
 * Classe Main para a aplicacao da RedeSocial.
 * @author Margarida Lopes 64557 LEI
 */
public class Main {

     // Constantes para os comandos do utilizador.
    public static final String CONSULTAR_PESSOA = "CONSULTAPESSOA";
    public static final String REGISTA = "REGISTA";
    public static final String CONSULTAR_AMIZADE = "CONSULTAAMIZADE";
    public static final String AMIGOS = "AMIGOS";
    public static final String CONSULTAR_AMIGOS = "CONSULTAAMIGOS";
    public static final String NOVO_ESTADO = "NOVOESTADO";
    public static final String CONSULTAR_ESTADO = "CONSULTAESTADO";
    public static final String PESSOAS = "PESSOAS";
    public static final String QUIT = "SAIR";


     // Constantes para o feedback do programa.
    public static final String PESSOA_REG = "Pessoa registada.\n";
    public static final String SEM_REG = "Sem registo.\n";
    public static final String PESSOA_REG_S = "Pessoa registada com sucesso.\n";
    public static final String AMZ_EXIS = "Amizade existente.\n";
    public static final String AMZ_INEXIS = "Amizade inexistente.\n";
    public static final String AMZ_CRIADA = "Amizade criada.\n";
    public static final String AMZ_INV = "Amizade invalida.\n";
    public static final String SEM_AMG_REG = "Nao tem amigos registados.\n";
    public static final String LIST_AMG = "Lista de amigos:";
    public static final String ESTADO_ALT = "Estado alterado.\n";
    public static final String REDE_VAZIA = "Rede Social vazia.\n";
    public static final String LIST_PESSOAS_REG = "Lista de pessoas registadas:";
    public static final String SAIR = "Adeus.\n";


    /**
     * Programa principal.
     * Cria uma nova rede social, invoca um scanner para leitura de comandos da consola e
     * executa o interpretador de comandos.
     * @param args argumentos para a execucao deste programa.
     */
    public static void main(String[] args) {
        RedeSocial redesocial = new RedeSocialClass();
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = getComand(in);
            executeComand(command, in, redesocial);
        } while (!command.equals(QUIT));
    }

    /**
     * Devolve o comando solicitado pelo utilizador.
     * @param in o Scanner para leitura do comando da consola.
     * @return o comando lido.
     * @pre in != null
     */
    private static String getComand(Scanner in) {
        String command = in.nextLine().toUpperCase();
        return command;
    }

    /**
     * Direciona o comando lido para a sua correta execucao.
     * @param command o comando lido da consola.
     * @param in o scanner para a leitura de, caso se aplique, parametros necesarios a execucao decada comando.
     * @param redesocial a rede social
     * @pre command != null & in != null && redesocial != null
     */
    private static void executeComand(String command, Scanner in, RedeSocial redesocial) {
        switch (command) {
            case CONSULTAR_PESSOA:
                processConsultarPessoa(in, redesocial);
                break;
            case REGISTA:
                processRegista(in, redesocial);
                break;
            case CONSULTAR_AMIZADE:
                processConsultarAmizade(in, redesocial);
                break;
            case AMIGOS:
                processAmigos(in, redesocial);
                break;
            case CONSULTAR_AMIGOS:
                processConsultarAmigos(in, redesocial);
                break;
            case NOVO_ESTADO:
                processNovoEstado(in, redesocial);
                break;
            case CONSULTAR_ESTADO:
                processConsultarEstado(in, redesocial);
                break;
            case PESSOAS:
                processPessoas(redesocial);
                break;
            case QUIT:
                System.out.println(SAIR);
                break;
        }
    }

    /**
     * Metodo responsavel pela execucao do comando CONSULTAPESSOA:
     * da feedback sobre o registo, ou nao, de uma pessoa na rede social.
     * @param in o scanner para leitura de parametros necessarios a execucao deste metodo
     *           (o nome da pessoa a saber se esta registada na rede social).
     * @param redeSocial a rede social.
     * @pre in != null && redesocial != null
     */
    private static void processConsultarPessoa(Scanner in, RedeSocial redeSocial) {
        String nome = in.nextLine();
        if (redeSocial.isPessoaRegistada(nome)) {
            System.out.println(PESSOA_REG);
        } else
            System.out.println(SEM_REG);
    }

    /**
     * Metodo responsavel pela execucao do comando REGISTA:
     * da feedback sobre se uma dada pessoa foi corretaemnte registada se ja se encontrava registada na rede social.
     * @param in o scanner para leitura de parametros necessarios a execucao deste metodo.
     * @param redesocial a rede social.
     * @pre in != null && redesocial != null
     */
    private static void processRegista(Scanner in, RedeSocial redesocial) {
        String nome = in.nextLine();
        String email = in.nextLine();
        String estado = in.nextLine();
        if (redesocial.registaPessoa(nome, email, estado)) {
            System.out.println(PESSOA_REG_S);
        } else System.out.println(PESSOA_REG);
    }

    /**
     * Metodo responsavel pela execucao do comando CONSULTAAMIZADE:
     * da feedback sobre a amizade de duas pessoas na rede social.
     * @param in o scanner para leitura de parametros necessarios a execucao deste metodo.
     * @param redesocial a rede social.
     * @pre in != null && redesocial != null
     */
    private static void processConsultarAmizade(Scanner in, RedeSocial redesocial) {
        String nome1 = in.nextLine();
        String nome2 = in.nextLine();
        if (redesocial.isAmigos(nome1, nome2)) {
            System.out.println(AMZ_EXIS);
        } else System.out.println(AMZ_INEXIS);
    }

    /**
     * Metodo responsavel pela execucao do comando AMIGOS:
     * da feedback sobre a criacao de amizade entre duas pessoas.
     * @param in o scanner para leitura de parametros necessarios a execucao deste metodo.
     * @param redesocial a rede social.
     * @pre in != null && redesocial != null
     */
    private static void processAmigos(Scanner in, RedeSocial redesocial) {
        String nome1 = in.nextLine();
        String nome2 = in.nextLine();
        int result = redesocial.criarAmizade(nome1, nome2);
        if (result == 3) {                     // ERRO_3: Nao e possivel criar a amizade.
            System.out.println(AMZ_INV);
        } else if (result == 2) {              // ERRO_2: Uma das pessoas nao se encontra registada na rede social.
            System.out.println(SEM_REG);
        } else if (result == 1) {              // ERRO_1: As duas pessoas ja sao amigas.
            System.out.println(AMZ_EXIS);
        } else System.out.println(AMZ_CRIADA);
    }

    /**
     * Metodo responsavel pela execucao do comando CONSULTAAMIGOS:
     * lista todos os amigos de uma dada pessoa, caso existam.
     * @param in o scanner para leitura de parametros necessarios a execucao deste metodo.
     * @param redesocial a rede social.
     * @pre in != null && redesocial != null
     */
    private static void processConsultarAmigos(Scanner in, RedeSocial redesocial) {
        String nome = in.nextLine();
        if (!redesocial.isPessoaRegistada(nome)) {       // Pessoa nao se encontra registada na rede social.
            System.out.println(SEM_REG);
        } else if (redesocial.getNrAmigos(nome)==0) {    // Pessoa sem amizades criadas.
            System.out.println(SEM_AMG_REG);
        } else {
            IteradorAmigos iteradorAmigos = redesocial.iteradorAmg(nome);
            System.out.println(LIST_AMG);
            while (iteradorAmigos.hasNext()) {
                Pessoa amigo = iteradorAmigos.next();
                System.out.println(amigo.getNome() + "; " + amigo.getEmail());
            } System.out.println("");
        }
    }

    /**
     * Metodo responsavel pela execucao do comando NOVOESTADO:
     * altera o estado de uma dada pessoa, caso esteja registada na rede social.
     * @param in o scanner para leitura de parametros necessarios a execucao deste metodo.
     * @param redesocial a rede social.
     * @pre in != null && redesocial != null
     */
    private static void processNovoEstado(Scanner in, RedeSocial redesocial) {
        String nome = in.nextLine();
        String estado = in.nextLine();
        if (redesocial.setEstado(nome, estado)) {
            System.out.println(ESTADO_ALT);
        } else System.out.println(SEM_REG);    // Pessoa nao se encontra registada na rede social.
    }

    /**
     * Metoto responsavel pela execucao do comando CONSULTAESTADO:
     * dada uma pessoa, devolve o seu estado.
     * @param in o scanner para leitura de parametros necessarios a execucao deste metodo.
     * @param redesocial a rede social.
     * @pre in != null && redesocial != null
     */
    private static void processConsultarEstado(Scanner in, RedeSocial redesocial) {
        String nome = in.nextLine();
        if (redesocial.getEstado(nome) == null) {    // Pessoa nao se encontra registada na rede social.
            System.out.println(SEM_REG);
        } else
            System.out.println(redesocial.getEstado(nome) + "\n");
    }

    /**
     * Metodo responsavel pela execucao do comando PESSOAS:
     * listas todas as pessoas registadas na rede social, caso existam.
     * @param redesocial a rede social.
     * @pre redesocial != null
     */
    private static void processPessoas(RedeSocial redesocial) {
        IteradorRegistados iteradorRegistados = redesocial.iteradorReg();
        if (redesocial.getNrRegistados() == 0) {  // Sem pessoas registadas na rede social.
            System.out.println(REDE_VAZIA);
        } else {
            System.out.println(LIST_PESSOAS_REG);
            while (iteradorRegistados.hasNext()) {
                Pessoa registado = iteradorRegistados.next();
                System.out.println(registado.getNome() + "; " + registado.getEmail());
            } System.out.println("");
        }
    }
}