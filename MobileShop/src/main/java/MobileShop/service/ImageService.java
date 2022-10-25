package MobileShop.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	File save(MultipartFile file, String folder);

	void delete(String folder, String image);

}
