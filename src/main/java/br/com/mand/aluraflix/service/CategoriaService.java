package br.com.mand.aluraflix.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.mand.aluraflix.model.Categoria;
import br.com.mand.aluraflix.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}

	public List<Categoria> getCategoriasAtivos() {
		return this.categoriaRepository.findAll();
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

}
