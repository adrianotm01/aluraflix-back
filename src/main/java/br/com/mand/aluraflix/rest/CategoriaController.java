package br.com.mand.aluraflix.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mand.aluraflix.model.Categoria;
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
	public ResponseEntity<List<Categoria>> getCategoriasAtivos(){
		return ResponseEntity.ok().body(this.categoriaService.getCategoriasAtivos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id){
		return ResponseEntity.ok().body(this.categoriaService.getCategoriaById(id));
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
	
}
