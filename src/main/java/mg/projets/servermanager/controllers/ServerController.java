package mg.projets.servermanager.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mg.projets.servermanager.enumeration.Status;
import mg.projets.servermanager.models.Response;
import mg.projets.servermanager.models.Server;
import mg.projets.servermanager.services.implementation.ServerServiceImplementation;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerServiceImplementation serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.list(20)))
                        .message("Servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/list/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.create(
                                server)))
                        .message("Server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

}
