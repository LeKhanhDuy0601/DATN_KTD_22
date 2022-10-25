package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.Parameter;
import MobileShop.Validation.ParameterCreateValidation;
import MobileShop.Validation.ParameterUpdateValidation;

public interface ParameterService {

	List<Parameter> findAllParameters();

	ResponseEntity<?> create(@Valid ParameterCreateValidation parameterCreateValidation);

	ResponseEntity<?> update(@Valid ParameterUpdateValidation parameterUpdateValidation);

	ResponseEntity<?> delete(@Valid ParameterUpdateValidation parameterUpdateValidation);

	List<Parameter> search(String key_search);

}
