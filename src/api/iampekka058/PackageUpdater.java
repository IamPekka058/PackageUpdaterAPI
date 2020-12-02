package api.iampekka058;

import api.iampekka058.functions.Check;
import api.iampekka058.functions.JsonReader;
import api.iampekka058.functions.Zip;
import api.iampekka058.objects.Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PackageUpdater {

    private String path;
    private String url;

    public void downloadFile(String name) {
        try {
            File old = new File(path+name);
            if(old.exists()) old.delete();
            System.out.println("Downloading Package...");
            System.out.println(path);
            FileOutputStream fos = new FileOutputStream(path+name+".zip");
            fos.getChannel().transferFrom(Channels.newChannel(new URL(this.url+"/"+name+"/latest/"+name+".zip").openStream()), 0, Long.MAX_VALUE);
            fos.close();
            System.out.println("Finished downloading Package!");
            Zip.unZip(Paths.get(path)+"/"+name+".zip",path+name);
            File zipped_file = new File(path+name+".zip");
            if(zipped_file.exists()) zipped_file.delete();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can't download Package!");
        }
    }

    public ArrayList<Package> getAllLocalPackages(){
        ArrayList<Package> packages = new ArrayList<>();
        File[] directories = new File(this.path).listFiles(File::isDirectory);
        if(directories != null) {
            for (File dir : directories) {
                if (Check.isPackage(dir.getPath())) {
                    packages.add(new Package(dir.getName(), JsonReader.readJsonFromPath(dir.getPath() + "/info.json"),url,this));
                }
            }
        }
        return packages;
    }

    public Package getPackage(String name){
        if(Check.packageExists(this.url,name)) {
                this.downloadFile(name);
                System.out.println(url+name+"/info.json");
            try {
                return new Package(name,JsonReader.readJsonFromUrl(url+name+"/info.json"),url+name+"/latest/"+name, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public PackageUpdater(String path, String url){
        this.path = path;
        this.url = url;
    }
}
