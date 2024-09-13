package com.br.Sac.controller;

import com.br.Sac.model.dto.StatusDTO;
import com.br.Sac.service.StatusService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StatusDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> cadastrar(@RequestBody StatusDTO objeto) throws Exception {
        return statusService.cadastrar(objeto);
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StatusDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listar() throws Exception {
        return statusService.listar();
    }

    @GetMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StatusDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listarPorId(@PathVariable Long idObjeto) throws Exception {
        return statusService.listarPorId(idObjeto);
    }

    @PutMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StatusDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> editar(@PathVariable Long idObjeto, @RequestBody StatusDTO objeto) throws Exception {
        return statusService.editar(idObjeto, objeto);
    }

    @DeleteMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StatusDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> excluir(@PathVariable Long idObjeto) throws Exception {
        return statusService.excluir(idObjeto);
    }
}
