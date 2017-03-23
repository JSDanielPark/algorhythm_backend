package kr.devdogs.algorhythm.file.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("fileService")
public class FileService {
@Autowired private ServletContext servletContext;
	
	public String uploadPhoto(final MultipartFile uploadedFile) {
		String relPath = "/img/upload/";
		String filePath = servletContext.getRealPath(relPath);
		File upDirectory = new File(filePath);
		if(!upDirectory.exists()) {
			upDirectory.mkdirs();
		}
		
		String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
		
		filePath += fileName;
		
		final File uploadFile = new File(filePath);
		if(uploadFile.exists()) {
			uploadFile.delete();
		}
		
		try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile))) {
			FileCopyUtils.copy(uploadedFile.getInputStream(), stream);
		} catch (FileNotFoundException e) {
			return null;
		} catch(IOException ioe) {
			return null;
		}
		
		return relPath + fileName;
	}
}
