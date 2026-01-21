package id.bni.nasabah.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public Map<String, String> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Success");
        response.put("message", "  BANK-AIMAR");
        response.put("version", "1.0.0");
        return response;
    }
}