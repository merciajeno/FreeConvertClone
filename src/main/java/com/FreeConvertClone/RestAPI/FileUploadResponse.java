package com.FreeConvertClone.RestAPI;

public class FileUploadResponse {

	private String fileName;
	private String downloadUri;
	public FileUploadResponse(String fileName, String downloadUri) {
		super();
		this.fileName = fileName;
		this.downloadUri = downloadUri;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDownloadUri() {
		return downloadUri;
	}
	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}

	
}
