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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mand.aluraflix.model.Video;
import br.com.mand.aluraflix.service.VideoService;

@RestController
@RequestMapping("/v1/videos")
public class VideoController {

	private VideoService videoService;
	
	public VideoController(VideoService videoService) {
		super();
		this.videoService = videoService;
	}

	@GetMapping
	public ResponseEntity<List<Video>> getVideosAtivos(){
		return ResponseEntity.ok().body(this.videoService.getVideosAtivos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Video> getVideoById(@PathVariable Integer id){
		return ResponseEntity.ok().body(this.videoService.getVideoById(id));
	}
	
	@PostMapping
	public ResponseEntity<Video> salvarVideo(@RequestBody @Valid Video video){
		this.videoService.salvar(video);
		return ResponseEntity.accepted().body(video);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarVideo(@PathVariable("id") Integer id){
		videoService.deletar(id);
		return ResponseEntity.accepted().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> atualizarVideo(@PathVariable("id")Integer id, @RequestBody Video video){
		this.videoService.atualizar(id,video);
		return ResponseEntity.ok().build();
		
	}
	
}
