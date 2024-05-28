package com.FreeConvertClone.Service;

import java.io.IOException;
import java.net.URL;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {

	RestTemplate restTemplate = new RestTemplate();
	public String convertPdfToWord(MultipartFile file) 
	{
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String,Object> body = new  LinkedMultiValueMap<>();
		body.add("file", file.getResource());
		HttpEntity<MultiValueMap<String,Object>> resource = new HttpEntity<>(body,header);
		System.out.println(resource);
		String url = "http://127.0.0.1:5000/PdfToWord";
		
		
		ResponseEntity<String> response=restTemplate.postForEntity(url, resource, String.class);
		return response.getBody();
		
	}
	
	public String convertVideoToAudio(MultipartFile video) throws IOException
	{
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
	    String url="http://127.0.0.1:5000/VideoToAudio";
	    MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
	    body.add("file", video.getResource());
	    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body,header);
	    System.out.println(request );
	    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	    return response.getBody();
	   
	}
	
	public String convertWordToPdf(MultipartFile file)
	{
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String,Object> body = new  LinkedMultiValueMap<>();
		body.add("file", file.getResource());
		HttpEntity<MultiValueMap<String,Object>> resource = new HttpEntity<>(body,header);
		System.out.println(resource);
		String url = "http://127.0.0.1:5000/WordToPdf";
		
		
		ResponseEntity<String> response=restTemplate.postForEntity(url, resource, String.class);
		return response.getBody();
	}
	

}
