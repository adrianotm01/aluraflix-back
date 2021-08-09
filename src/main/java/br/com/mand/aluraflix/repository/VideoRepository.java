package br.com.mand.aluraflix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.mand.aluraflix.model.Video;

public interface VideoRepository extends PagingAndSortingRepository<Video, Integer>{
	
	@EntityGraph(attributePaths = {"categorias"})
	public List<Video> findAll();
	
	public boolean existsByLink(String link);

	public boolean existsByLinkAndId(String link, Integer id);
	
	@Query("SELECT new Video(v.id,v.nome,v.link,v.descricao) FROM Video v WHERE v.id = ?1")
	public Optional<Video> findById(Integer id);
	
	@EntityGraph(type = EntityGraphType.FETCH,attributePaths = { "categorias"})
	//@Query("SELECT new Video(v.id,v.nome,v.link,v.descricao, c) FROM Video v LEFT JOIN FETCH v.categorias c WHERE c.id = ?1")
	public List<Video> findByCategoriasId(Integer id);

	@EntityGraph(attributePaths = {"categorias"})
	public List<Video> findByNomeContainingIgnoreCase(String nome);
}
