package playsteboy.handling_spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam String name){
        if(name == null || name.isEmpty()){
            return ResponseEntity.status(400).body("Le nom d'utilisateur n'est pas valide");
        }
        return ResponseEntity.status(200).body("Welcome " + name);
    }
}
