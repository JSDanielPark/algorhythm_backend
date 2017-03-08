package kr.devdogs.algorhythm.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service("fileUtils")
public class FileUtils {
	@Autowired private ServletContext servletContext;
	
	public String uploadFile(final MultipartFile uploadedFile, final String plusPath) {
		String relPath = new StringBuilder("/upload/")
				.append(plusPath == null ? "" : plusPath).toString();
		String filePath = servletContext.getRealPath("/resources" + relPath);
		File upDirectory = new File(filePath);
		if (!upDirectory.exists()) {
			upDirectory.mkdirs();
		}

		String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();

		filePath += fileName;

		final File uploadFile = new File(filePath);
		if (uploadFile.exists()) {
			uploadFile.delete();
		}
		BufferedOutputStream stream;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		try {
			FileCopyUtils.copy(uploadedFile.getInputStream(), stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return relPath + fileName;
	}
}
