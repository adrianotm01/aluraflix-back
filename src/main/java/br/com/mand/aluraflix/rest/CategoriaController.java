package br.com.mand.aluraflix.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mand.aluraflix.dto.VideoDTO;
import br.com.mand.aluraflix.model.Categoria;
import br.com.mand.aluraflix.model.Video;
import br.com.mand.aluraflix.service.CategoriaService;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {

	private CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}

	@GetMapping
	public ResponseEntity<Page<Categoria>> getCategoriasAtivos(Pageable page){
		return ResponseEntity.ok().body(this.categoriaService.getCategoriasAtivos(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id){
		return ResponseEntity.ok().body(this.categoriaService.getCategoriaById(id));
	}
	
	@GetMapping("/{id}/videos")
	public ResponseEntity<List<VideoDTO>> getVideosByCategoria(@PathVariable("id") Integer id){
		List<VideoDTO> videosDTO = converteVideoParaDTO(this.categoriaService.getVideosByCategoria(id));
		return ResponseEntity.ok(videosDTO);
	}
	
	
	@PostMapping
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody @Valid Categoria video){
		this.categoriaService.salvar(video);
		return ResponseEntity.accepted().body(video);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarCategoria(@PathVariable("id") Integer id){
		categoriaService.deletar(id);
		return ResponseEntity.accepted().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> atualizarCategoria(@PathVariable("id")Integer id, @RequestBody Categoria video){
		this.categoriaService.atualizar(id,video);
		return ResponseEntity.ok().build();
		
	}
	
	private List<VideoDTO> converteVideoParaDTO(List<Video> videos) {
		List<VideoDTO> videosDTO = videos.stream().map(vid -> {
			VideoDTO dto = new VideoDTO(vid.getId(), vid.getNome(), vid.getDescricao(), vid.getLink(), vid.getCategorias().getId());
			return dto;
		}).collect(Collectors.toList());
		return videosDTO;
	}
	
	
}
