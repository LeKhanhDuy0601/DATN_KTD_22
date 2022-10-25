package MobileShop.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.BrandDAO;
import MobileShop.Entity.Brand;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.BrandCreateValidation;
import MobileShop.Validation.BrandUpdateValidation;
import MobileShop.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService{
@Autowired
BrandDAO brandDAO;
@Override
public List<Brand> findAll() {
	// TODO Auto-generated method stub
	return brandDAO.findAllBrands();
}

@Override
public ResponseEntity<?> createBrand(@Valid BrandCreateValidation brandCreateValidation) {
	// TODO Auto-generated method stub
	if(brandCreateValidation.getAvatar().equals("favicon.ico")) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Vui lòng chọn ảnh đại diện cho thương hiệu.", "avatar"));
	}
	if(brandDAO.existsByCode(brandCreateValidation.getCode())) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Mã thương hiệu đã được sử dụng.", "code"));
	}
	Brand brand = new Brand(
			brandCreateValidation.getCode(),
			brandCreateValidation.getName(),
			brandCreateValidation.getShort_description(),
			brandCreateValidation.getDescription(),
			brandCreateValidation.getAvatar(),
			brandCreateValidation.getActivity(),
			false);
	brandDAO.save(brand);
	return ResponseEntity.ok(new ResponseMessage("Thương hiệu được tạo thành công!"));
}

@Override
public ResponseEntity<?> updateBrand(BrandUpdateValidation brandUpdateValidation) {
	// TODO Auto-generated method stub
	if(brandUpdateValidation.getAvatar().equals("favicon.ico")) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Vui lòng chọn ảnh đại diện cho thương hiệu.", "avatar"));
	}
	Brand brand = brandDAO.findById(brandUpdateValidation.getId()).get();
	if(brand == null || brand.getDeleted() == true || brand.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Thương hiệu không tồn tại.", "brand"));
	}
	brand.setName(brandUpdateValidation.getName());
	brand.setShort_description(brandUpdateValidation.getShort_description());
	brand.setDescription(brandUpdateValidation.getDescription());
	brand.setAvatar(brandUpdateValidation.getAvatar());
	brand.setActivity(brandUpdateValidation.getActivity());
	brandDAO.save(brand);
	return ResponseEntity.ok(new ResponseMessage("Thương hiệu được cập nhật thành công!"));
}

@Override
public ResponseEntity<?> deleteBrand(BrandUpdateValidation brandUpdateValidation) {
	// TODO Auto-generated method stub
	Brand brand = brandDAO.findById(brandUpdateValidation.getId()).get();
	if(brand == null || brand.getDeleted() == true || brand.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Thương hiệu không tồn tại.", "brand"));
	}
	brand.setDeleted(true);
//	brand.setDeleted_by(null);
	brandDAO.save(brand);
	return ResponseEntity.ok(new ResponseMessage("Thương hiệu được xóa thành công!"));
}

@Override
public List<Brand> search(String key_search) {
	// TODO Auto-generated method stub
	if(key_search.equals("null")) {
		return brandDAO.findAllBrands();
	}
	return brandDAO.search(key_search);
}
}
