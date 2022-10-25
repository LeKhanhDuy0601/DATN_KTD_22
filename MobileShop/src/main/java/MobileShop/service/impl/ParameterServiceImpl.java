package MobileShop.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.ParameterDAO;
import MobileShop.Entity.Parameter;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.ParameterCreateValidation;
import MobileShop.Validation.ParameterUpdateValidation;
import MobileShop.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService{

	
	@Autowired
	ParameterDAO parameterDAO;

	@Override
	public List<Parameter> findAllParameters() {
		// TODO Auto-generated method stub
		return parameterDAO.findAllParameters();
	}

	@Override
	public ResponseEntity<?> create(@Valid ParameterCreateValidation parameterCreateValidation) {
		// TODO Auto-generated method stub
		Parameter parameter = new Parameter(
				parameterCreateValidation.getName(),
				Integer.parseInt(parameterCreateValidation.getLevel()),
				parameterCreateValidation.getDescription(),
				parameterCreateValidation.getActivity(),
				false,
				null,
				parameterCreateValidation.getParameter_group());
		parameterDAO.save(parameter);
		return ResponseEntity.ok(new ResponseMessage("Thông số kỹ thuật được tạo thành công!"));
	}

	@Override
	public ResponseEntity<?> update(@Valid ParameterUpdateValidation parameterUpdateValidation) {
		// TODO Auto-generated method stub
		Parameter parameter = parameterDAO.findById(parameterUpdateValidation.getId()).get();
		if(parameter == null || parameter.getDeleted() == true || parameter.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Thông số kỹ thuật không tồn tại.", "parameter"));
		}
		parameter.setName(parameterUpdateValidation.getName());
		parameter.setLevel(Integer.parseInt(parameterUpdateValidation.getLevel()));
		parameter.setDescription(parameterUpdateValidation.getDescription());
		parameter.setActivity(parameterUpdateValidation.getActivity());
		parameter.setParameter_group(parameterUpdateValidation.getParameter_group());
		parameterDAO.save(parameter);
		return ResponseEntity.ok(new ResponseMessage("Thông số kỹ thuật được cập nhật thành công!"));
	}

	@Override
	public ResponseEntity<?> delete(@Valid ParameterUpdateValidation parameterUpdateValidation) {
		// TODO Auto-generated method stub
		Parameter parameter = parameterDAO.findById(parameterUpdateValidation.getId()).get();
		if(parameter == null || parameter.getDeleted() == true || parameter.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Thông số kỹ thuật không tồn tại.", "parameter"));
		}
		parameter.setDeleted(true);
	//  parameter.setDeleted_by(null);	
		return ResponseEntity.ok(new ResponseMessage("Thông số kỹ thuật được xóa thành công!"));
	}

	@Override
	public List<Parameter> search(String key_search) {
		// TODO Auto-generated method stub
		if(key_search.equals("null")) {
			parameterDAO.findAllParameters();
		}
		return parameterDAO.search(key_search);
	}
	
}
