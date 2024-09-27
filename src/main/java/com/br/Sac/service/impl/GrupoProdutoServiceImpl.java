package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.GrupoProduto;
import com.br.Sac.model.dto.GrupoProdutoDTO;
import com.br.Sac.repository.GrupoProdutoRepository;
import com.br.Sac.service.GrupoProdutoService;
import com.br.Sac.service.util.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GrupoProdutoServiceImpl implements GrupoProdutoService {

    @Autowired
    private GrupoProdutoRepository grupoProdutoRepository;

    @Autowired
    private CustomObjectMapper<GrupoProduto, GrupoProdutoDTO> grupoProdutoMapper;

    @Override
    public ResponseEntity<Object> cadastrar(GrupoProdutoDTO objeto) throws Exception {

        if (grupoProdutoRepository.existsByCodigo(objeto.getCodigo())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Grupo Produto. Já existe outro Grupo Produto com o mesmo codigo."));
        }

        if (grupoProdutoRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Grupo Produto; Já existe outro Grupo Produto com o mesmo nome."));
        }

        GrupoProduto grupoProduto = new GrupoProduto();
        grupoProduto.setCodigo(objeto.getCodigo());
        grupoProduto.setNome(objeto.getNome());

        GrupoProduto objetoCriado = grupoProdutoRepository.saveAndFlush(grupoProduto);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/{id}")
                                .buildAndExpand(objetoCriado.getId())
                                .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> listar() throws Exception {

        List<GrupoProdutoDTO> grupoProdutoDTOs = grupoProdutoMapper.converterParaListaDeDtos(grupoProdutoRepository.findAll());
        if (grupoProdutoDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Grupo Produto cadastrados no sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(grupoProdutoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, GrupoProdutoDTO objeto) throws Exception {

        GrupoProduto dadosDto = grupoProdutoMapper.converterParaEntidade(objeto);
        GrupoProduto paraEditar = grupoProdutoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Grupo Produto com ID " + idObjeto + " não foi encontrado!"));

        if (grupoProdutoRepository.existsByCodigoAndIdNot(objeto.getCodigo(), objeto.getId())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Grupo Produto. Já existe outro Grupo Produto com o mesmo codigo."));
        }

        if (grupoProdutoRepository.existsByNomeAndIdNot(objeto.getNome(), objeto.getId())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Grupo Produto; Já existe outro Grupo Produto com o mesmo nome."));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        GrupoProduto objetoAtualizado = grupoProdutoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(grupoProdutoMapper.converterParaDto(objetoAtualizado)));

    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        GrupoProduto grupoProduto = grupoProdutoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Grupo Produto com ID " + idObjeto + " não encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(grupoProdutoMapper.converterParaDto(grupoProduto)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
        grupoProdutoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Grupo Produto com ID " + idObjeto + " não encontrado!"));

        grupoProdutoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Grupo Produto excluído com sucesso"));
    }
}
