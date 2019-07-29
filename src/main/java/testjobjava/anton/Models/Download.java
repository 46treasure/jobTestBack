package testjobjava.anton.Models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class Download implements Runnable {

    private ArrayList<String> urls;
    private File file;
    
    public Download() {
    }

    public Download(ArrayList<String> urls, File file) {
        this.urls = urls;
        this.file = file;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Download download = (Download) o;
        return Objects.equals(urls, download.urls) &&
                Objects.equals(file, download.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urls, file);
    }

    @Override
    public String toString() {
        return "Download{" +
                "urls=" + urls +
                ", file=" + file +
                '}';
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < this.urls.size(); i++) {
                URL url = new URL(this.urls.get(i));
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                BufferedInputStream in = new BufferedInputStream(http.getInputStream());
                    FileOutputStream fos = new FileOutputStream(this.file);
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
