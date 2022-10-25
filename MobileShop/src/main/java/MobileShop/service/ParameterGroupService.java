package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.ParameterGroup;
import MobileShop.Validation.ParameterGroupCreateValidation;
import MobileShop.Validation.ParameterGroupUpdateValidation;

public interface ParameterGroupService {

	List<ParameterGroup> findAll();

	ResponseEntity<?> create(@Valid ParameterGroupCreateValidation parameterGroupCreateValidation);

	ResponseEntity<?> update(@Valid ParameterGroupUpdateValidation parameterGroupUpdateValidation);

	ResponseEntity<?> delete(@Valid ParameterGroupUpdateValidation parameterGroupUpdateValidation);

}
