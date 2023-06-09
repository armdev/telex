package io.project.app.telex.resources;

import io.project.app.telex.services.TelexRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        log.info("Received Telex: " + telex);
        return ResponseEntity.status(HttpStatus.OK).body(telex + " is  received");

    }

    @PostMapping(path = "/send")
    public ResponseEntity post(@RequestBody TelexRequest telex) {
        log.info("Received TelexRequest: " + telex.toString());
        return ResponseEntity.status(HttpStatus.OK).body(telex.toString() + " is  received");

    }

}
