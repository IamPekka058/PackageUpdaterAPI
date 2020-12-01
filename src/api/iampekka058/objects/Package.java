package api.iampekka058.objects;

import org.json.JSONObject;
import org.jsoup.Jsoup;

public class Package {
    private String name;
    private JSONObject jsonObject;

    public Package(String name, JSONObject jsonObject){
        this.name=name;
        this.jsonObject=jsonObject;
    }
}
