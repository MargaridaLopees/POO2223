
import Objects.Iterator;
import Objects.ShapesCollection;
import Objects.ShapesCollectionClass;
import Objects.Shape;

import java.util.Scanner;

/**
 * Classe Main para a aplicacao da ShapesCollection.
 * @author Margarida Lopes 64557 LEI
 */

public class Main {

    // Constantes para os comandos do utilizador.
    private static final String CIRCLE = "CIRCLE";
    private static final String RECTANGLE = "RECTANGLE";
    private static final String LIST = "LIST";
    private static final String MOVE = "MOVE";
    private static final String MINAREA = "MINAREA";
    private static final String EXIT = "EXIT";

    // Constantes para o feedback do programa.
    private static final String NEW_CIRC = "A new circle was added.\n";
    private static final String NEW_REC = "A new rectangle was added.\n";
    private static final String ID_EXIST = "Identifier already exists.\n";
    private static final String ID_NOT_EXIST = "Identifier does not exist.\n";
    private static final String LIST_SHAPES = "All shapes in the collection:";
    private static final String EMPTY = "Empty collection.\n";
    private static final String SHAPE_MOVED = "Shape was moved.\n";
    private static final String EXITING = "Exiting...\n";

    /**
     * Programa principal.
     * Cria uma nova colecao de formas, invoca um scanner para leitura de comandos da consola e
     * executa o interpretador de comandos.
     * @param args argumentos para a execucao deste programa.
     */
    public static void main(String[] args) {
       ShapesCollection collection = new ShapesCollectionClass();
       Scanner in = new Scanner(System.in);
        String command;
        do {
            command = getComand(in);
            executeComand(command, in, collection);
        } while (!command.equals(EXIT));
    }

    /**
     * Devolve o comando solicitado pelo utilizador.
     * @param in o Scanner para leitura do comando da consola.
     * @return o comando lido.
     * @pre in != null
     */
    private static String getComand(Scanner in) {
        String command = in.next().toUpperCase();
        return command;
    }

    /**
     * Direciona o comando lido para a sua correta execucao.
     * @param command o comando lido da consola.
     * @param in o scanner para a leitura de, caso se aplique, parametros necesarios a execucao decada comando.
     * @param collection a colecao de formas
     * @pre command != null & in != null && collection != null
     */
    private static void executeComand(String command, Scanner in, ShapesCollection collection) {
        switch (command) {
            case CIRCLE:
                processCircle(in, collection);
                break;
            case RECTANGLE:
                processRectangle(in, collection);
                break;
            case LIST:
                processList(collection);
                break;
            case MOVE:
                processMove(in, collection);
                break;
            case MINAREA:
                processMinArea(collection);
                break;
            case EXIT:
                System.out.println(EXITING);
                break;
        }
    }

    /**
     * Processa o comando CIRCLE - adiciona um novo circulo.
     * Caso o identificador ainda nao esteja a ser utilizado por outra forma da colecao, o circulo e adicionado.
     * @param in o scanner para a leitura dos parametros necessarios para o novo circulo.
     * @param collection a colecao de formas.
     * @pre in != null && collection != null
     */
    private static void processCircle (Scanner in, ShapesCollection collection) {
        String ID = in.next();
        int x = in.nextInt();
        int y = in.nextInt();
        int radius = in.nextInt();
        in.nextLine();
        if (collection.hasShape(ID)) {
            System.out.println(ID_EXIST);
        } else {
            collection.addCircle(ID, x, y, radius);
            System.out.println(NEW_CIRC);
        }
    }

    /**
     * Processa o comando RECTANGLE - adiciona um novo retangulo.
     * Caso o identificador ainda nao esteja a ser utilizado por outra forma da colecao, o retangulo e adicionado.
     * @param in o scanner para a leitura dos parametros necessarios para o novo retangulo.
     * @param collection a colecao de formas.
     * @pre in != null && collection != null
     */
    private static void processRectangle (Scanner in, ShapesCollection collection) {
        String ID = in.next();
        int x = in.nextInt();
        int y = in.nextInt();
        int height = in.nextInt();
        int width = in.nextInt();
        in.nextLine();
        if (collection.hasShape(ID)) {
            System.out.println(ID_EXIST);
        } else {
            collection.addRectangle(ID, x, y, height, width);
            System.out.println(NEW_REC);
        }
    }

    /**
     * Processa o comando LIST.
     * Caso exitam, lista todas as formas da colecao.
     * @param collection a colecao de formas.
     * @pre icollection != null
     */
    private static void processList (ShapesCollection collection) {
        if (collection.isEmpty()) {
            System.out.println(EMPTY);
        } else {
            Iterator it = collection.allShapesIterator();
            System.out.println(LIST_SHAPES);
            while (it.hasNext()) {
                Shape shape = it.next();
                String ID = shape.getID();
                int x = shape.getCenterX();
                int y = shape.getCenterY();
                String type = shape.getShape();
                System.out.println(ID + " (" + x + ", " + y + ") " +  type);
            } System.out.println("");
        }
    }

    /**
     * Processa o comando MOVE.
     * Caso a forma exista, altera as coordenadas da mesma.
     * @param in o scanner para a leitura dos parametros necessarios.
     * @param collection a colecao de formas.
     * @pre in != null && collection != null
     */
    private static void processMove (Scanner in, ShapesCollection collection) {
        String ID = in.next();
        int x = in.nextInt();
        int y = in.nextInt();
        in.nextLine();
        if (!collection.hasShape(ID)) {
            System.out.println(ID_NOT_EXIST);
        } else {
            collection.move(ID, x, y);
            System.out.println(SHAPE_MOVED);
        }
    }

    /**
     * Processa o comando MINAREA.
     * Devolve a forma cuja area e a menor. Em caso de empate, devolve a forma mais recente.
     * @param collection a colecao de formas.
     * @pre icollection != null
     */
    private static void processMinArea(ShapesCollection collection) {
        if (collection.isEmpty()) {
            System.out.println(EMPTY);
        } else {
            String ID = collection.smallestArea().getID();
            int x = collection.smallestArea().getCenterX();
            int y = collection.smallestArea().getCenterY();
            String type = collection.smallestArea().getShape();
            System.out.println(ID + " (" + x + ", " + y + ") " + type);
        } System.out.println("");
    }
}