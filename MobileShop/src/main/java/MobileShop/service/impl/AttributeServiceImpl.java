package MobileShop.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.AttributeDAO;
import MobileShop.Entity.Attribute;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.AttributeCreateValidation;
import MobileShop.Validation.AttributeUpdateValidation;
import MobileShop.service.AttributeService;

@Service
public class AttributeServiceImpl  implements AttributeService{
@Autowired
AttributeDAO attributeDAO;
@Override
public List<Attribute> findAllAttributes() {
	// TODO Auto-generated method stub
	return attributeDAO.findAllAttributes();
}

@Override
public List<Attribute> findAttributesByAttributeGroup(Integer group_id) {
	// TODO Auto-generated method stub
	return attributeDAO.findAttributesByAttributeGroup(group_id);
}

@Override
public ResponseEntity<?> createAttribute(AttributeCreateValidation attributeCreateValidation) {
	// TODO Auto-generated method stub
	
	Attribute attribute = new Attribute(
			attributeCreateValidation.getName(),
			attributeCreateValidation.getDescription(),
			attributeCreateValidation.getValue(),
			attributeCreateValidation.getActivity(),
			false,
			attributeCreateValidation.getAttribute_group());
	attributeDAO.save(attribute);
	return ResponseEntity.ok(new ResponseMessage("Thuộc tính được tạo thành công!"));
}

@Override
public ResponseEntity<?> updateAttribute(AttributeUpdateValidation attributeUpdateValidation) {
	// TODO Auto-generated method stub
	Attribute attribute = attributeDAO.findById(attributeUpdateValidation.getId()).get();
	if(attribute == null || attribute.getDeleted() == true || attribute.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Thuộc tính không tồn tại.", "attribute"));
	}
	
	attribute.setName(attributeUpdateValidation.getName());
	attribute.setDescription(attributeUpdateValidation.getDescription());
	attribute.setValue(attributeUpdateValidation.getValue());
	attribute.setActivity(attributeUpdateValidation.getActivity());
	attribute.setAttribute_group(attributeUpdateValidation.getAttribute_group());
	attributeDAO.save(attribute);
	return ResponseEntity.ok(new ResponseMessage("Thuộc tính được cập nhật thành công!"));
}

@Override
public ResponseEntity<?> deleteAttribute(AttributeUpdateValidation attributeUpdateValidation) {
	// TODO Auto-generated method stub
	Attribute attribute = attributeDAO.findById(attributeUpdateValidation.getId()).get();
	if(attribute == null || attribute.getDeleted() == true || attribute.getDeleted_by() != null) {
		return ResponseEntity.badRequest().body(new ResponseMessageError("Thuộc tính không tồn tại.", "attribute"));
	}
	attribute.setDeleted(true);
//	attribute.setDeleted_by(null);
	attributeDAO.save(attribute);
	return ResponseEntity.ok(new ResponseMessage("Thuộc tính được xóa thành công!"));
}

@Override
public List<Attribute> search(String key_search) {
	// TODO Auto-generated method stub
	if(key_search.equals("null")) {
		return attributeDAO.findAllAttributes();
	}
	return attributeDAO.search(key_search);
}
}
