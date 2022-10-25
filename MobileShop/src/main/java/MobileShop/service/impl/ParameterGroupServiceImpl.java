package MobileShop.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.ParameterGroupDAO;
import MobileShop.Entity.ParameterGroup;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.ParameterGroupCreateValidation;
import MobileShop.Validation.ParameterGroupUpdateValidation;
import MobileShop.service.ParameterGroupService;

@Service
public class ParameterGroupServiceImpl implements ParameterGroupService{

	@Autowired
	ParameterGroupDAO parameterGroupDAO;

	@Override
	public List<ParameterGroup> findAll() {
		// TODO Auto-generated method stub
		return parameterGroupDAO.findAllGroups();
	}

	@Override
	public ResponseEntity<?> create(@Valid ParameterGroupCreateValidation parameterGroupCreateValidation) {
		// TODO Auto-generated method stub
		ParameterGroup parameter_group = new ParameterGroup(
				parameterGroupCreateValidation.getName(),
				Integer.parseInt(parameterGroupCreateValidation.getLevel()),
				parameterGroupCreateValidation.getDescription(),
				parameterGroupCreateValidation.getActivity(),
				false,
				null);
		parameterGroupDAO.save(parameter_group);
		return ResponseEntity.ok(new ResponseMessage("Nhóm thông số được tạo thành công!"));
	}

	@Override
	public ResponseEntity<?> update(@Valid ParameterGroupUpdateValidation parameterGroupUpdateValidation) {
		// TODO Auto-generated method stub
		ParameterGroup parameter_group = parameterGroupDAO.findById(parameterGroupUpdateValidation.getId()).get();
		if(parameter_group == null || parameter_group.getDeleted() == true || parameter_group.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Nhóm thông số không tồn tại.", "parameter_group"));
		}
		parameter_group.setName(parameterGroupUpdateValidation.getName());
		parameter_group.setLevel(Integer.parseInt(parameterGroupUpdateValidation.getLevel()));
		parameter_group.setDescription(parameterGroupUpdateValidation.getDescription());
		parameter_group.setActivity(parameterGroupUpdateValidation.getActivity());
		parameterGroupDAO.save(parameter_group);
		return ResponseEntity.ok(new ResponseMessage("Nhóm thông số được cập nhật thành công!"));
	}

	@Override
	public ResponseEntity<?> delete(@Valid ParameterGroupUpdateValidation parameterGroupUpdateValidation) {
		// TODO Auto-generated method stub
		ParameterGroup parameter_group = parameterGroupDAO.findById(parameterGroupUpdateValidation.getId()).get();
		if(parameter_group == null || parameter_group.getDeleted() == true || parameter_group.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Nhóm thông số không tồn tại.", "parameter_group"));
		}
		parameter_group.setDeleted(true);
	//	parameter_group.setDeleted_by(null);
		parameterGroupDAO.save(parameter_group);
		return ResponseEntity.ok(new ResponseMessage("Nhóm thông số được xóa thành công!"));
	}
	
}
