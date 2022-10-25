package MobileShop.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.CategoryGroupDAO;
import MobileShop.Entity.CategoryGroup;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.CategoryGroupCreateValidation;
import MobileShop.Validation.CategoryGroupUpdateValidation;
import MobileShop.service.CategoryGroupService;

@Service

public class CategoryGroupServiceImpl implements CategoryGroupService {
@Autowired
CategoryGroupDAO categoryGroupDAO;

@Override
public List<CategoryGroup> findAllCategoryGroup() {
	// TODO Auto-generated method stub
	return categoryGroupDAO.findAllCategoryGroup(Sort.by(Direction.ASC, "level"));
}

@Override
public ResponseEntity<?> create(@Valid CategoryGroupCreateValidation categoryGroupCreateValidation) {
	if(categoryGroupCreateValidation.getIcon().equals("mailchimp.png")) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Vui lòng chọn icon cho nhóm danh mục.", "icon"));
	}
	if(categoryGroupDAO.existsByCode(categoryGroupCreateValidation.getCode())) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Mã nhóm danh mục đã được sử dụng.", "code"));
	}
	CategoryGroup categoryGroup = new CategoryGroup(
			categoryGroupCreateValidation.getCode(),
			categoryGroupCreateValidation.getName(),
			Integer.parseInt(categoryGroupCreateValidation.getLevel()),
			categoryGroupCreateValidation.getIcon(),
			categoryGroupCreateValidation.getActivity(),
			false);
	categoryGroupDAO.save(categoryGroup);
	return ResponseEntity.ok(new ResponseMessage("Nhóm danh mục được tạo thành công!"));
}

@Override
public ResponseEntity<?> update(@Valid CategoryGroupUpdateValidation categoryGroupUpdateValidation) {
	if(categoryGroupUpdateValidation.getIcon().equals("mailchimp.png")) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Vui lòng chọn icon cho nhóm danh mục.", "icon"));
	}
	CategoryGroup categoryGroup = categoryGroupDAO.findById(categoryGroupUpdateValidation.getId()).get();
	if(categoryGroup == null || categoryGroup.getDeleted() == true || categoryGroup.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Nhóm danh mục không tồn tại.", "category_group"));
	}
	categoryGroup.setName(categoryGroupUpdateValidation.getName());
	categoryGroup.setLevel(Integer.parseInt(categoryGroupUpdateValidation.getLevel()));
	categoryGroup.setIcon(categoryGroupUpdateValidation.getIcon());
	categoryGroup.setActivity(categoryGroupUpdateValidation.getActivity());
	categoryGroupDAO.save(categoryGroup);
	return ResponseEntity.ok(new ResponseMessage("Nhóm danh mục được cập nhật thành công!"));
}

@Override
public ResponseEntity<?> delete(@Valid CategoryGroupUpdateValidation categoryGroupUpdateValidation) {
	CategoryGroup categoryGroup = categoryGroupDAO.findById(categoryGroupUpdateValidation.getId()).get();
	if(categoryGroup == null || categoryGroup.getDeleted() == true || categoryGroup.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Nhóm danh mục không tồn tại.", "category_group"));
	}
	categoryGroup.setDeleted(true);
//	categoryGroup.setDeleted_by(null);
	categoryGroupDAO.save(categoryGroup);
	return ResponseEntity.ok(new ResponseMessage("Nhóm danh mục được xóa thành công!"));
}
}
