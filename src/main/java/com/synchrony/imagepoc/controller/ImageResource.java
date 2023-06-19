package com.synchrony.imagepoc.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.synchrony.imagepoc.service.ImageDaoService;

/**
 * The Class ImageResource.
 */
@RestController
public class ImageResource {
	
	/** The image dao service. */
	private ImageDaoService imageDaoService;
	
	/**
	 * Instantiates a new image resource.
	 *
	 * @param imageDaoService the image dao service
	 */
	public ImageResource(ImageDaoService imageDaoService) {
		this.imageDaoService = imageDaoService;
	}

	
	/**
	 * Upload image.
	 *
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("/images/upload")
	public ResponseEntity<String> uploadImage() throws IOException {
		
		String savefile = imageDaoService.uploadImage();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savefile).toUri();
		return ResponseEntity.created(location).build();
	}

}
