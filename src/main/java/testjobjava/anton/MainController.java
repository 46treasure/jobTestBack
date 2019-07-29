package testjobjava.anton;

import org.springframework.web.bind.annotation.*;
import testjobjava.anton.Models.Download;

import java.io.File;
import java.util.*;

@RestController
public class MainController {



    @PostMapping("/")
    @CrossOrigin("http://localhost:4200")
    public void download(@RequestBody ArrayList<String> urls){
        System.out.println(urls);
        new Thread(new Download(urls)).start();
    }

 }
