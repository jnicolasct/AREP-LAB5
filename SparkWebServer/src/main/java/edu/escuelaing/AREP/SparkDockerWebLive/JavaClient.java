package edu.escuelaing.AREP.SparkDockerWebLive;

import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JavaClient {

    private String protocol = "http";
    private String host = "http://ec2-34-224-66-113.compute-1.amazonaws.com:";
    private String[] ports={"8087","8088","8089"};
    private int actualPort = 0;
    private String pathView = "/";
    private String pathAdd = "/add?Mensaje=";
    URL url12;


    /**
     * Metodo que realiza un request a los logservices para consultar a la base de datos
     * @return osdatos contenidos en la base de datos
     * @throws Exception
     */

    public String readAll() throws Exception {
        URL url1 = new URL(host+ ports[actualPort] + pathView);
        String result= "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url1.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                result = result + inputLine;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return result;
    }

    /**
     * Metodo que reliaza un request para añadir datos a la base de datos
     * @param texto el texto que se desea guardar
     * @throws IOException Si no se puede realizar la conexion
     */
    public void add(String texto) throws IOException {
        String path = pathAdd + (texto.replace(" ", "-"));
        URL url1 = new URL(host + ports[actualPort] + path);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url1.openStream()));) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Metodo balaceador de cargas que distribye las request entre los diferentes puertos.
     */
    public void roundRobin(){
        if (this.actualPort == 2){
            this.actualPort = 0;
        }
        else{
            this.actualPort = this.actualPort+1;
        }
    }

}
