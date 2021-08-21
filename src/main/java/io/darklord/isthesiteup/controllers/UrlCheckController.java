package io.darklord.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP="Site up";
    private final String SITE_IS_DOWN="Site down";
    private final String INCORRECT_URL="Incorrect";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
            String returnMessage = "";
            try {
                URL urlobj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
                conn.setRequestMethod("GET");
                conn.connect(); 
                int responsedCode = conn.getResponseCode()/100;
                if(responsedCode !=2 || responsedCode !=3){
                    returnMessage = SITE_IS_DOWN;
                }else{
                    returnMessage = SITE_IS_UP;
                }
   
            } catch (MalformedURLException e) {
               returnMessage = INCORRECT_URL;
            } catch (IOException e) {
                
                returnMessage = SITE_IS_DOWN;
            }


            return returnMessage;
    }
}
