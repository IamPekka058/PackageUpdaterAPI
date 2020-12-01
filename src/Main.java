import api.iampekka058.PackageUpdater;
import api.iampekka058.functions.Check;
import api.iampekka058.functions.Download;

public class Main {
    public static void main(String[] args) {
        PackageUpdater packageUpdater = new PackageUpdater("./plugins/","http://zerodev.bplaced.net/packageupdater/");
        packageUpdater.getAllLocalPackages();
        if(Check.packageExists("http://zerodev.bplaced.net/packageupdater/","Test")){
            packageUpdater.downloadFile("Test");
        }

    }
}
