package api.iampekka058.objects;

import api.iampekka058.PackageUpdater;
import api.iampekka058.functions.Check;
import api.iampekka058.functions.JsonReader;
import org.json.JSONObject;

import java.io.IOException;

public class Package {
    private String name;
    private JSONObject jsonObject;
    private String version;
    private PackageUpdater packageUpdater;
    private String url;

    public Package(String name, JSONObject jsonObject, String url, PackageUpdater packageUpdater){
        this.name=name;
        this.jsonObject=jsonObject;
        this.url = url;
        this.packageUpdater = packageUpdater;
        version = jsonObject.get("version").toString();
    }

    public void update(){
        try {
            String version_online = JsonReader.readJsonFromUrl(this.url).getString("version");
            if(Check.packageExists(this.url,name)) {
                if (!version_online.equals(version)) {
                    packageUpdater.downloadFile(name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
