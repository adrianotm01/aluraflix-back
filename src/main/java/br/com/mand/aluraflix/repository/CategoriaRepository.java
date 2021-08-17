package br.com.mand.aluraflix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.mand.aluraflix.model.Categoria;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Integer>{
	
	public Page<Categoria> findAll(Pageable page);
	
}
