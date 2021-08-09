package br.com.mand.aluraflix.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.mand.aluraflix.model.Categoria;
import br.com.mand.aluraflix.model.Video;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Integer>{
	
	public List<Categoria> findAll();
	
}
