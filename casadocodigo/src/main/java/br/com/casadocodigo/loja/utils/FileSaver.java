package br.com.casadocodigo.loja.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile sumario) {
		
		try {
			String realPath = request.getServletContext().getRealPath("/").concat(baseFolder);
			File directory = new File(realPath);
			if (!directory.exists()) {
				directory.mkdir();
			}
			
			sumario.transferTo(new File(directory, sumario.getOriginalFilename()));
			
			return baseFolder.concat("/").concat(sumario.getOriginalFilename());
		} catch (IllegalStateException | IOException exception) {
			throw new RuntimeException(exception);
		}
		
	}

}
