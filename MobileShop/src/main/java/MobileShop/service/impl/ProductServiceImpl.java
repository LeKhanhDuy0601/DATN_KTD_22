package MobileShop.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Common.SlugUtils;
import MobileShop.Dao.ProductDAO;
import MobileShop.Dao.ProductImageDAO;
import MobileShop.Entity.Product;
import MobileShop.Entity.ProductImage;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.ProductCreateValidation;
import MobileShop.Validation.ProductUpdateValidation;
import MobileShop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	ProductImageDAO productImageDAO;
	
	@Autowired
	SlugUtils slugUtils;
	
	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return productDAO.findAllProducts();
	}

	@Override
	public ResponseEntity<?> createProduct(ProductCreateValidation productCreateValidation) {
		// TODO Auto-generated method stub
		if(productDAO.existsByCode(productCreateValidation.getCode())) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Mã sản phẩm đã tồn tại!", "code"));
		}
		Calendar created_at = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		String slug = slugUtils.makeSlug(productCreateValidation.getName());
		Product product = new Product(
				productCreateValidation.getCode(),
				productCreateValidation.getName(),
				slug,
				0,
				Integer.parseInt(productCreateValidation.getPrice()),
				productCreateValidation.getType(),
				productCreateValidation.getShort_description(),
				productCreateValidation.getDescription(),
				Integer.parseInt(productCreateValidation.getWeight()),
				productCreateValidation.getWeight_unit(),
				Integer.parseInt(productCreateValidation.getHeight()),
				productCreateValidation.getHeight_unit(),
				Integer.parseInt(productCreateValidation.getWidth()),
				productCreateValidation.getWidth_unit(),
				0,
				Integer.parseInt(productCreateValidation.getWarranty_period()),
				productCreateValidation.getActivity(),
				null,
				created_at.getTime(),
				false,
				null,
				productCreateValidation.getBrand(),
				productCreateValidation.getUnit(),
				productCreateValidation.getCategories());
		
		productDAO.save(product);
		productCreateValidation.getImages().forEach(image -> {
			ProductImage productImage = new ProductImage(image.getName(), product);
			productImageDAO.save(productImage);
		});
		return ResponseEntity.ok(new ResponseMessage("Sản phẩm được tạo thành công!"));
	}

	@Override
	public ResponseEntity<?> updateProduct(ProductUpdateValidation productUpdateValidation) {
		// TODO Auto-generated method stub
		Product product = productDAO.findById(productUpdateValidation.getId()).get();
		if(product == null || product.getDeleted() == true || product.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Sản phẩm không tồn tại!", "product"));
		}
		String slug = slugUtils.makeSlug(productUpdateValidation.getName());
		product.setName(productUpdateValidation.getName());
		product.setSlug(slug);
		product.setType(productUpdateValidation.getType());
//		product.setPrice(Integer.parseInt(productUpdateValidation.getPrice()));
		product.setShort_description(productUpdateValidation.getShort_description());
		product.setDescription(productUpdateValidation.getDescription());
		product.setWeight(Integer.parseInt(productUpdateValidation.getWeight()));
		product.setWeight_unit(productUpdateValidation.getWeight_unit());
		product.setHeight(Integer.parseInt(productUpdateValidation.getHeight()));
		product.setHeight_unit(productUpdateValidation.getHeight_unit());
		product.setWidth(Integer.parseInt(productUpdateValidation.getWidth()));
		product.setWidth_unit(productUpdateValidation.getWidth_unit());
		product.setWarranty_period(Integer.parseInt(productUpdateValidation.getWarranty_period()));;
		product.setActivity(productUpdateValidation.getActivity());
		product.setBrand(productUpdateValidation.getBrand());
		product.setUnit(productUpdateValidation.getUnit());
		productDAO.save(product);
		productImageDAO.saveAll(productUpdateValidation.getImages());
		return ResponseEntity.ok(new ResponseMessage("Cập nhật sản phẩm thành công!"));
	}

	@Override
	public ResponseEntity<?> deleteProduct(ProductUpdateValidation productUpdateValidation) {
		// TODO Auto-generated method stub
		Product product = productDAO.findById(productUpdateValidation.getId()).get();
		if(product == null || product.getDeleted() == true || product.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Sản phẩm không tồn tại!", "product"));
		}
		if(product.getQty() > 0) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Sản phẩm còn tồn kho!", "product"));
		}
		product.setDeleted(true);
		//product.setDeleted_by(null);
		productDAO.save(product);
		return ResponseEntity.ok(new ResponseMessage("Xóa sản phẩm thành công!"));
	}

	@Override
	public List<Product> search(String search) {
		// TODO Auto-generated method stub
		if(search.equals("null")) {
			return productDAO.findAllProducts();
		}
		return productDAO.search(search);
	}

	@Override
	public List<Product> findNewProducts() {
		// TODO Auto-generated method stub
//		return productDAO.findNewProducts(Sort.by(Direction.DESC, "created_at"));
		return productDAO.findNewProducts();
	}

	@Override
	public Product productDetail(Integer proId, String slug) {
		// TODO Auto-generated method stub
		return productDAO.productDetail(proId, slug);
	}
	
}
