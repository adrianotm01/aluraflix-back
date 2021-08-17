package br.com.mand.aluraflix.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mand.aluraflix.model.Categoria;
import br.com.mand.aluraflix.model.Video;
import br.com.mand.aluraflix.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	private VideoService videoService;
	
	public CategoriaService(CategoriaRepository categoriaRepository,VideoService videoService) {
		super();
		this.categoriaRepository = categoriaRepository;
		this.videoService = videoService;
	}

	public Page<Categoria> getCategoriasAtivos(Pageable page) {
		return this.categoriaRepository.findAll(page);
	}

	public Categoria getCategoriaById(Integer id) {
		return this.categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}

	public void deletar(Integer id) {
		Categoria categoria = this.getCategoriaById(id);
		this.categoriaRepository.delete(categoria);
	}

	public void salvar(Categoria categoria) {
		this.categoriaRepository.save(categoria);
	}

	public void atualizar(Integer id, Categoria categoria) {
		Categoria categoriaLegada = this.getCategoriaById(id);
		categoriaLegada.setCor(categoria.getCor());
		categoriaLegada.setTitulo(categoria.getTitulo());
		this.categoriaRepository.save(categoriaLegada);
	}

	public List<Video> getVideosByCategoria(Integer id) {
		return this.videoService.getVideosByCategoria(id);
	}

}
