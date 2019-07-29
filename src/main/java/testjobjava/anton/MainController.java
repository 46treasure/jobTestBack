package testjobjava.anton;

import org.springframework.web.bind.annotation.*;
import testjobjava.anton.Models.Download;

import java.io.File;
import java.util.*;

@RestController
public class MainController {

    UUID id = UUID.randomUUID();


    File file = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + this.id + ".png");


    @PostMapping("/")
    @CrossOrigin("http://localhost:4200")
    public void download(@RequestBody ArrayList<String> urls){
        System.out.println(urls);
        new Thread(new Download(urls, file)).start();
    }

 }
