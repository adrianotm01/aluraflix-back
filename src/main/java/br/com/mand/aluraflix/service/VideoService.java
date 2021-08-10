package br.com.mand.aluraflix.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.mand.aluraflix.dto.VideoDTO;
import br.com.mand.aluraflix.model.Categoria;
import br.com.mand.aluraflix.model.Video;
import br.com.mand.aluraflix.repository.VideoRepository;

@Service
public class VideoService {

	private VideoRepository videoRepo;

	public VideoService(VideoRepository videoRepo) {
		this.videoRepo = videoRepo;
	}

	public List<Video> getVideosAtivos(Map<String, String> pesquisa) {
		if (pesquisa.isEmpty()) {			
			return videoRepo.findAll();
		}else {
			return videoRepo.findByNomeContainingIgnoreCase(pesquisa.values().stream().findFirst().get());
		}
	}

	public Video getVideoById(Integer id) {
		return videoRepo.findById(id).orElseThrow(() -> new NoSuchElementException());
	}

	@Transactional
	public void salvar(Video video) {
		if (existeVideoCadastrado(video)) {
			throw new IllegalArgumentException();
		} else {
			this.videoRepo.save(video);
		}
	}

	public List<Video> getVideosByCategoria(Integer id) {
		return this.videoRepo.findByCategoriasId(id);
	}	
	
	public boolean existeVideoCadastrado(Video video) {
		return video.getId() == null ? this.videoRepo.existsByLink(video.getLink()) : this.videoRepo.existsByLinkAndId(video.getLink(),video.getId());
	}

	public void deletar(Integer id) {
		Video videoParaDeletar = this.getVideoById(id);
		this.videoRepo.delete(videoParaDeletar);
	}

	@Transactional
	public void atualizar(Integer id, Video video) {
		if (existeVideoCadastrado(video)) {
			throw new IllegalArgumentException();
		} else {
			Video videoLegado = this.getVideoById(id);
			videoLegado.setDescricao(video.getDescricao());
			videoLegado.setLink(video.getLink());
			videoLegado.setNome(video.getNome());
			this.videoRepo.save(videoLegado);
		}
	}

}
