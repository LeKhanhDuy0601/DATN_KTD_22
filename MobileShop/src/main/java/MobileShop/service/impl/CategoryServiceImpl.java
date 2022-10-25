package MobileShop.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.CategoryDAO;
import MobileShop.Entity.Category;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.CategoryCreateValidation;
import MobileShop.Validation.CategoryUpdateValidation;
import MobileShop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	public List<Category> findAllCategory() {
		return categoryDAO.findAllCategory();
	}
	
	@Override
	public List<Category> findCategoriesByCategoryGroup(Integer group_id) {
		// TODO Auto-generated method stub
		return categoryDAO.findCategoriesByCategoryGroup(group_id);
	}
	
	@Override
	public ResponseEntity<?> createCategory(@Valid CategoryCreateValidation categoryCreateValidation) {
		if(categoryDAO.existsByCode(categoryCreateValidation.getCode())) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Mã danh mục đã được sử dụng.", "code"));
		}
		Category category = new Category(
				categoryCreateValidation.getCode(),
				categoryCreateValidation.getName(),
				Integer.parseInt(categoryCreateValidation.getLevel()),
				categoryCreateValidation.getView(),
				categoryCreateValidation.getActivity(),
				false,
				categoryCreateValidation.getCategoryGroup());
		categoryDAO.save(category);
		return ResponseEntity.ok(new ResponseMessage("Danh mục được tạo thành công!"));
	}
	
	@Override
	public ResponseEntity<?> updateCategory(@Valid CategoryUpdateValidation categoryUpdateValidation) {
		Category category = categoryDAO.findById(categoryUpdateValidation.getId()).get();
		if(category == null || category.getDeleted() == true || category.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Danh mục không tồn tại.", "category"));
		}
		category.setName(categoryUpdateValidation.getName());
		category.setLevel(Integer.parseInt(categoryUpdateValidation.getLevel()));
		category.setView(categoryUpdateValidation.getView());
		category.setCategoryGroup(categoryUpdateValidation.getCategoryGroup());
		category.setActivity(categoryUpdateValidation.getActivity());
		categoryDAO.save(category);
		return ResponseEntity.ok(new ResponseMessage("Danh mục được cập nhật thành công!"));
	}
	
	@Override
	public ResponseEntity<?> deleteCategory(@Valid CategoryUpdateValidation categoryUpdateValidation) {
		Category category = categoryDAO.findById(categoryUpdateValidation.getId()).get();
		if(category == null || category.getDeleted() == true || category.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Danh mục không tồn tại.", "category"));
		}
		category.setDeleted(true);
	//	category.setDeleted_by(null);
		categoryDAO.save(category);
		return ResponseEntity.ok(new ResponseMessage("Danh mục được xóa thành công!"));
	}
	
	@Override
	public List<Category> search(String key_search) {
		if(key_search.equals("null")) {
			return categoryDAO.findAllCategory();
		}
		return categoryDAO.search(key_search);
	}
}
