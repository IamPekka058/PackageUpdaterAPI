package api.iampekka058;

import api.iampekka058.functions.Check;
import api.iampekka058.functions.JsonReader;
import api.iampekka058.functions.Zip;
import api.iampekka058.objects.Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.util.ArrayList;

public class PackageUpdater {

    private String path;
    private String url;

    public void downloadFile(String name) {
        try {
            System.out.println("Downloading Package...");
            FileOutputStream fos = new FileOutputStream(path);
            fos.getChannel().transferFrom(Channels.newChannel(new URL(this.url).openStream()), 0, Long.MAX_VALUE);
            fos.close();
            System.out.println("Finished downloading Package!");
            Zip.unZip(this.path+".zip",this.path);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can't download Package!");
        }
    }

    public ArrayList<Package> getAllLocalPackages(){
        ArrayList<Package> packages = new ArrayList<>();
        File[] directories = new File(this.path).listFiles(File::isDirectory);
        for(File dir : directories){
            if(Check.isPackage(dir.getPath())){
                packages.add(new Package(dir.getName(), JsonReader.readJsonFromPath(dir.getPath()+"/info.json")));
            }
        }
        return packages;
    }
    public PackageUpdater(String path, String url){
        this.path = path;
        this.url = url;
    }
}
