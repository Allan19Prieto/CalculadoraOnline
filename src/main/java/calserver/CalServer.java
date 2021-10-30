package calserver;
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

    /**
     * Metodo que envia un mensaje al abrir la conexion
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        session.getBasicRemote().sendText("Hola desde el servidor ");
    }

    /**
     *Metodo qeu realizara las cosa al resivir un mensaje desde el cliente
     * @param message
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
        String userId = session.getId();
        //System.out.println(userId + " : " + message);
        /*session.getBasicRemote().sendText("(Server): Welcome, " + message + "5");
        session.getBasicRemote().sendText("5+5");*/

    }

}
