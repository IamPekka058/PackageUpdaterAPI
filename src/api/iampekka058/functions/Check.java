package api.iampekka058.functions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Check {
    public static boolean packageExists(String url, String name) {
        try {
            URLConnection c = new URL(url+name+"/latest/"+name+".zip").openConnection();
            String contentType = c.getContentType();
            if(contentType.equals("application/zip")){
                System.out.println("Das Paket "+name+" wurde gefunden! -> "+url+name+"/latest/"+name+".zip");
                return true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("Das Paket "+name+" wurde nicht gefunden! -> "+url+name+"/latest/"+name+".zip");
        return false;
    }
}
