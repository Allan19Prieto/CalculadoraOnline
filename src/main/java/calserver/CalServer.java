package calserver;
import Arbol.Main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//Imports para el CSV
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor principal de web socket
 * contendar la evaluacion del arbol y las consutas al archivo
 * tambien la creacion y guardad ode las operaciones en csv
 */
@ServerEndpoint("/ws")
public class CalServer {
    private Session session;
    private Main post;
    private Map<String, String> usernames = new HashMap<String, String>();
    private static final String ARCHIVO_PATH = "C:\\Users\\Allan\\Desktop\\demo\\src\\main\\java\\calserver\\operaciones.csv";
    /**
     * Manejador de eventos
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnOpen
    public void open(Session session) throws IOException {
        this.session = session;
        String userid = session.getId();
        session.getBasicRemote().sendText("Ingreso el usuario el usuario " + userid);
    }

    /**
     * Controlador de cierre de una conexion
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnClose
    public void close(Session session) throws IOException, EncodeException {
        /*Este seria el identificador de id*/
        String userId = session.getId();
        if (usernames.containsKey(userId)) {
            String username = usernames.get(userId);
            usernames.remove(userId);
            for (Session peer : session.getOpenSessions())
                peer.getBasicRemote().sendText("(Server): " + userId +" left the chat room.");
        }
    }

    /**
     * Interactua y intercambia mensajes con el cliente
     * @param message
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
        String userId = session.getId();
        System.out.println(userId + " : " + message);
        String Tiempo;

        //Llamamos al tiempo


        post = new Main();
        post.principal(message);
        añadirExpresion(message, session);
        añadirResultado(String.valueOf(post.getResultado()),session);
        EscribirCSV(String.valueOf(message),String.valueOf( post.getResultado()), session, Hora());
    }

    /**
     * suncion para obtener el resultado
     * @param message
     * @param session
     * @throws IOException
     */
    public void añadirResultado(String message, Session session) throws IOException {
        session.getBasicRemote().sendText(" ");
        session.getBasicRemote().sendText("Resultado:  "+message);


    }

    /**
     * Funcion para devolver el mensaje
     * @param message
     * @param session
     * @throws IOException
     */
    public void añadirExpresion(String message, Session session) throws IOException {
        session.getBasicRemote().sendText(" ");
        session.getBasicRemote().sendText("Expresion:  "+message);
    }

    /**
     * Funcion para obtener la hora
     * @return
     */
    public String Hora() {
        String Fecha = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        return Fecha;
    }

    /**
     * Para escribir el archivo CSV
     * @param Entrada
     * @param session
     * @param hora
     * @throws IOException
     */
    public void EscribirCSV(String message ,String Entrada, Session session, String hora) throws IOException {

        //Variables del archivo y el identificador
        //final String ARCHIVO_PATH = "./operaciones.csv";
        String IdUser = session.getId();

        //Array donde se guardan los datos existentes del archivo
        List list = new ArrayList();
        try(
                //Lectura
                Reader reader = Files.newBufferedReader(Paths.get(ARCHIVO_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ){
            for (CSVRecord csvRecord : csvParser){
                //Guardar dato sen el array
                String linea[] = {csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3)};
                list.add(linea);

                //System.out.println(list);
            }
        }
        try{
            //Instantiating the CSVWriter class
            CSVWriter writer = new CSVWriter(new FileWriter(ARCHIVO_PATH));
            String Dato[] = {IdUser, message, Entrada, hora};
            list.add(Dato);

            writer.writeAll(list);
            writer.flush();
            System.out.println("Data entered");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
