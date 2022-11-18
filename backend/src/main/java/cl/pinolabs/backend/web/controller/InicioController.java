package cl.pinolabs.backend.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api","/"})
public class InicioController {

    @GetMapping
    public ResponseEntity<String> index(){
        return ResponseEntity.ok().body("Bienvenido al backend de la api duomo! <3");
    }
}
