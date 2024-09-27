package com.br.Sac.controller;

import com.br.Sac.model.dto.GrupoProdutoDTO;
import com.br.Sac.service.GrupoProdutoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "grupoProduto")
public class GrupoProdutoController {


    @Autowired
    private GrupoProdutoService grupoProdutoService;

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GrupoProdutoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> cadastrar(@RequestBody GrupoProdutoDTO objeto) throws Exception {
        return grupoProdutoService.cadastrar(objeto);
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GrupoProdutoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listar() throws Exception {
        return grupoProdutoService.listar();
    }

    @GetMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GrupoProdutoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listarPorId(@PathVariable Long idObjeto) throws Exception {
        return grupoProdutoService.listarPorId(idObjeto);
    }

    @PutMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GrupoProdutoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> editar(@PathVariable Long idObjeto, @RequestBody GrupoProdutoDTO objeto) throws Exception {
        return grupoProdutoService.editar(idObjeto, objeto);
    }

    @DeleteMapping("/{idObjeto}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GrupoProdutoDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> excluir(@PathVariable Long idObjeto) throws Exception {
        return grupoProdutoService.excluir(idObjeto);
    }
}
