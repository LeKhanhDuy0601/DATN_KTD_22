package MobileShop.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import MobileShop.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ServletContext servletContext;

	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(servletContext.getRealPath("/" + folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String string = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(string.hashCode()) + string.substring(string.lastIndexOf("."));
		try {
			File savedFile = new File(dir, name);
			file.transferTo(savedFile);
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void delete(String folder, String image) {
		// TODO Auto-generated method stub
		File fileDelete = new File(servletContext.getRealPath("/"+folder+"/"+image));
		if (fileDelete.exists()) {
			fileDelete.delete();
		}
	}

}
