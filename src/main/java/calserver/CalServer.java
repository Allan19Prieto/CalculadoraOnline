package calserver;
import Arbol.Main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servidor principal de web socket
 * contendar la evaluacion del arbol y las consutas al archivo
 * tambien la creacion y guardad ode las operaciones en csv
 */
@ServerEndpoint("/ws")
public class CalServer {
    private Main post;
    private Map<String, String> usernames = new HashMap<String, String>();
    /**
     * Manejador de eventos
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnOpen
    public void open(Session session) throws IOException {
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
        //System.out.println(userId + " : " + message);
        /*if( message.equals("8")){
            session.getBasicRemote().sendText("("+userId + ") "  + "M verdad");
        }
        if( message.equals("5")){
            session.getBasicRemote().sendText("("+userId + ") "  + "M falso");
        }*/
        post = new Main();
        post.principal(message);
        añadir(message, session);

    }

    public void añadir(String message, Session session) throws IOException {
        session.getBasicRemote().sendText(message);

    }
    public String añadir2(String x){
        return x;
    }


}
