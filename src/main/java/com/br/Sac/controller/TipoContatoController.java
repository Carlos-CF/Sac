package com.br.Sac.controller;


import com.br.Sac.model.dto.TipoContatoDTO;
import com.br.Sac.service.TipoContatoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tipoContato")
public class TipoContatoController {

    @Autowired
    private TipoContatoService tipoContatoService;

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TipoContatoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> cadastrar(@RequestBody TipoContatoDTO objeto) throws Exception {
        return tipoContatoService.cadastrar(objeto);
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TipoContatoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listar() throws Exception {
        return tipoContatoService.listar();
    }

    @GetMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TipoContatoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listarPorId(@PathVariable Long idObjeto) throws Exception {
        return tipoContatoService.listarPorId(idObjeto);
    }

    @PutMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TipoContatoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> editar(@PathVariable Long idObjeto, @RequestBody TipoContatoDTO objeto) throws Exception {
        return tipoContatoService.editar(idObjeto, objeto);
    }

    @DeleteMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TipoContatoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> excluir(@PathVariable Long idObjeto) throws Exception {
        return tipoContatoService.excluir(idObjeto);
    }

    @PatchMapping("/{idObjeto}/status")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TipoContatoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> mudarStatus(@PathVariable Long idObjeto) throws Exception {
        return tipoContatoService.mudarStatus(idObjeto);
    }
}
