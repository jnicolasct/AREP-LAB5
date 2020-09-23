package edu.escuelaing.AREP.SparkDockerWebLive;
import static spark.Spark.*;
import spark.Response;
import spark.Request;
/**
 * Hello world!
 *
 */
public class SparkWebServer
{
    private static final JavaClient cliente = new JavaClient();

    /**
     * Metodo main que inicializa el servicio REST
     * @param args argumentos fijo de un metodo main
     */
    public static void main( String[] args ) throws Exception {
        port(getPort());
        get("/hello", (req, res) -> "Hello Heroku");
        get("/", (req, res) -> inputDataPage(req, res));
        post("/", (req, res) -> resultDataPage(req, res));

    }

    /**
     * Metodo que contiene una vista donde el usuario puede ver los datos almacenados y agregar mas
     * @param req el request que realiza el cliente
     * @param res la respuesta que se le da al cliente
     * @return documento html en formato string
     */
    private static String inputDataPage(Request req, Response res) throws Exception {

        String result = cliente.readAll();
        cliente.roundRobin();
        String datos[] = result.split(",");
        String tabla = "";
        for (int i =0; i<datos.length; i++){
            String valores[] = datos[i].split("-");
            int valor = i+1;
            tabla = tabla + "<tr><td>" + valor + "</td><td>" + valores[0] + "</td><td>" + valores[1] + "</td></tr>";

        }
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<style>"
                + "table, th, td {"
                + "border: 1px solid black;"
                + "border-collapse: collapse;"
                + "border-spacing: 0;"
                + "}"
                + "</style>"
                + "<body>"
                + "<Center>"
                + "<h2>Adicionar a MongoDB</h2>"
                + "<p>Ingrese el mensaje que desea guardar</p>"
                + "<form method=\"post\" action=\"/\">"
                + "  Mensaje:<br>"
                + "  <input type=\"text\" name=\"Mensaje\">"
                + "  <br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</br>"
                + "</br>"
                + "<Table>"
                + "<tr>"
                + "<th> Logs </th>"
                + "<th> Text </th>"
                + "<th> Date </th>"
                + "</tr>"
                + tabla
                + "</Table>"
                + "</Center>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     * Metodo que agrega datos a la base de datos desde la vista
     * @param req el request que realiza el cliente
     * @param res la respuesta que se le da al cliente
     * @return Vista de todos los datos
     */
    private static String resultDataPage(Request req, Response res) throws Exception {
        String texto = req.queryParams("Mensaje");
        cliente.add(texto);
        cliente.roundRobin();
        return inputDataPage(req, res);


    }

    /**
     * Metodo que retorna el puerto por el que se va a ejecutar el servicio
     * @return el puerto por el que corre el servicio
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 7000;
    }
}
