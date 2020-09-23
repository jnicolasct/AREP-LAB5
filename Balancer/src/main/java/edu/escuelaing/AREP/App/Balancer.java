package edu.escuelaing.AREP.App;

import static spark.Spark.*;
import spark.Response;
import spark.Request;

/**
 * Hello world!
 *
 */

public class Balancer
{
    private static MongoDB DB = new MongoDB();

    /**
     * Metodo main que inicializa el servicio REST
     * @param args argumentos fijo de un metodo main
     */
    public static void main( String[] args )
    {
        port(getPort());
        get("/hello", (req, res) -> "Hello Balancer");
        get("/", (req, res) -> inputDataPage(req, res));
        get("/add", (req, res) -> addData(req, res));

    }

    /**
     * Metodo que conecta con la base de datos y extrae sus datos
     * @param req el request que realiza el cliente
     * @param res la respuesta que se le da al cliente
     * @return Datos obtenidos de la base de datos
     */
    private static String inputDataPage(Request req, Response res) {
        String result = DB.select();
        return result;
    }

    /**
     * Metodo que agrega un nuevo mensaje a la base de datos
     * @param req el request que realiza el cliente
     * @param res la respuesta que se le da al cliente
     * @return la vista de todos los datos en las base de datos
     */
    private static String addData(Request req, Response res) {
        DB.add(req.queryParams("Mensaje"));
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
        return 6001;
    }
}
