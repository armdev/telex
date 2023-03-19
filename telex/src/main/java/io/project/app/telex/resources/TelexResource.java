package io.project.app.telex.resources;



import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class TelexResource {

    @GetMapping(path = "/send")
    public ResponseEntity get(@RequestParam String telex) {
        log.info("Telex is here  " +telex);      
        return ResponseEntity.status(HttpStatus.OK).body(telex + " done");

    }

}
