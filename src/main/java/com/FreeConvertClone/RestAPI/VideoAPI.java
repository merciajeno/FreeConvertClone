package com.FreeConvertClone.RestAPI;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.UrlResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FreeConvertClone.Service.VideoService;
import com.FreeConvertClone.RestAPI.FileUploadResponse;

@RestController
public class VideoAPI {

	@Autowired
	VideoService service;
	
	@PostMapping("/upload/video")
	public ResponseEntity<Resource> addVideo(@RequestParam("videoFile") MultipartFile video) throws IOException
	{
		System.out.println(video);
		try {
			String path=service.convertVideoToAudio(video);
			Path file=Paths.get(path);
			UrlResource resource =new UrlResource(file.toUri()) ;
			 String contentType = "application/octet-stream";
		        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
		         
		        return ResponseEntity.ok()
		                .contentType(MediaType.parseMediaType(contentType))
		                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
		                .body(resource); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@PostMapping("/upload/pdf")
	public ResponseEntity<Resource> addPdf(@RequestParam("file") MultipartFile file) throws IOException
	{
		System.out.println(file.getName());
		String f=service.convertPdfToWord(file);
		Path path = Paths.get(f);
		UrlResource resource = new UrlResource(path.toUri());
		 String contentType = "application/octet-stream";
	        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
	         
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
	                .body(resource); 
	}
	
	@PostMapping("/upload/word")
	public ResponseEntity<Resource> addWord(@RequestParam("file") MultipartFile file) throws IOException
	{
		System.out.println(file.getName());
		String f=service.convertWordToPdf(file);
		Path path = Paths.get(f);
		UrlResource resource = new UrlResource(path.toUri());
		 String contentType = "application/octet-stream";
	        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
	         
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
	                .body(resource); 
		
	}
	
	@PostMapping("/upload/image")
	public void addImage(@RequestParam("image") MultipartFile image)
	{
		
	}
	
	@PostMapping("/api")
	public void something()
	{
		
	}
}
