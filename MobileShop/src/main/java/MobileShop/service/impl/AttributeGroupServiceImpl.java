package MobileShop.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.AttributeDAO;
import MobileShop.Dao.AttributeGroupDAO;
import MobileShop.Entity.AttributeGroup;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.AttributeGroupCreateValidation;
import MobileShop.Validation.AttributeGroupUpdateValidation;
import MobileShop.service.AttributeGroupService;

@Service
public class AttributeGroupServiceImpl implements AttributeGroupService {
@Autowired
AttributeGroupDAO attributeGroupDAO;

@Override
public List<AttributeGroup> findAllAttributeGroup() {
	// TODO Auto-generated method stub
	return attributeGroupDAO.findAllAttributeGroups();
}

@Override
public ResponseEntity<?> createAttributeGroup(AttributeGroupCreateValidation attributeGroupCreateValidation) {
	// TODO Auto-generated method stub
	
	AttributeGroup attributeGroup = new AttributeGroup(
			attributeGroupCreateValidation.getName(),
			Integer.parseInt(attributeGroupCreateValidation.getLevel()),
			attributeGroupCreateValidation.getDescription(),
			attributeGroupCreateValidation.getActivity(),
			false);
	attributeGroupDAO.save(attributeGroup);
	return ResponseEntity.ok(new ResponseMessage("Nhóm thuộc tính được tạo thành công!"));
}

@Override
public ResponseEntity<?> updateAttributeGroup(AttributeGroupUpdateValidation attributeGroupUpdateValidation) {
	// TODO Auto-generated method stub
	AttributeGroup attributeGroup = attributeGroupDAO.findById(attributeGroupUpdateValidation.getId()).get();
	if(attributeGroup == null || attributeGroup.getDeleted() == true || attributeGroup.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Nhóm thuộc tính không tồn tại.", "attribute_group"));
	}
	attributeGroup.setName(attributeGroupUpdateValidation.getName());
	attributeGroup.setLevel(Integer.parseInt(attributeGroupUpdateValidation.getLevel()));
	attributeGroup.setActivity(attributeGroupUpdateValidation.getActivity());
	attributeGroup.setDescription(attributeGroupUpdateValidation.getDescription());
	attributeGroupDAO.save(attributeGroup);
	return ResponseEntity.ok(new ResponseMessage("Nhóm thuộc tính được cập nhật thành công!"));
}

@Override
public ResponseEntity<?> deleteAttributeGroup(@Valid AttributeGroupUpdateValidation attributeGroupUpdateValidation) {
	// TODO Auto-generated method stub
	AttributeGroup attributeGroup = attributeGroupDAO.findById(attributeGroupUpdateValidation.getId()).get();
	if(attributeGroup == null || attributeGroup.getDeleted() == true || attributeGroup.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Nhóm thuộc tính không tồn tại.", "attribute_group"));
	}
	attributeGroup.setDeleted(true);
//	attributeGroup.setDeleted_by(null);
	attributeGroupDAO.save(attributeGroup);
	return ResponseEntity.ok(new ResponseMessage("Nhóm thuộc tính được xóa thành công!"));
}

@Override
public List<AttributeGroup> search(String key_search) {
	// TODO Auto-generated method stub
	if(key_search.equals("null")) {return attributeGroupDAO.findAllAttributeGroups();
	}
	return attributeGroupDAO.search(key_search);
}

}
