package testjobjava.anton.Models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Download implements Runnable {

    UUID id = UUID.randomUUID();
    private ArrayList<String> urls;

    public Download() {
    }

    public Download(ArrayList<String> urls) {
        this.urls = urls;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Download download = (Download) o;
        return Objects.equals(urls, download.urls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urls);
    }

    @Override
    public String toString() {
        return "Download{" +
                "urls=" + urls +
                '}';
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < this.urls.size(); i++) {
                URL url = new URL(this.urls.get(i));
                String[] splited = url.getFile().split("/");
                System.out.println(Arrays.toString(splited));
                String substring = splited[splited.length - 1].substring(0, 4);
                File file = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + substring + ".png");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                BufferedInputStream in = new BufferedInputStream(http.getInputStream());
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                    byte[] buffer = new byte[1024];
                    int read = 0;
                    while ((read = in.read(buffer, 0, 1024)) >= 0) {
                        bout.write(buffer, 0, read);
                    }
                    bout.close();
                    in.close();
                    System.out.println("Complete");
                }
            } catch(IOException e){
                e.printStackTrace();
            }

    }
}
