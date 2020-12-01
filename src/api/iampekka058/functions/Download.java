package api.iampekka058.functions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;

public class Download {
    public static void downloadFile(String url, String path) throws IOException {
        new FileOutputStream(path).getChannel().transferFrom(Channels.newChannel(new URL(url).openStream()), 0, Long.MAX_VALUE);
    }
}
