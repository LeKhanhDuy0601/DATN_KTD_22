package MobileShop.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.AttributeDAO;
import MobileShop.Dao.ProductAttributeDAO;
import MobileShop.Dao.ProductVariantDAO;
import MobileShop.Dto.AttributeRequest;
import MobileShop.Entity.Attribute;
import MobileShop.Entity.ProductAttribute;
import MobileShop.Entity.ProductVariant;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.GetVariantByAttributesValidation;
import MobileShop.Validation.ProductVariantCreateValidation;
import MobileShop.Validation.ProductVariantUpdateValidation;
import MobileShop.service.ProductVariantService;

@Service
public class ProductVariantServiceImpl implements ProductVariantService{

	@Autowired
	ProductVariantDAO productVariantDAO;
	
	@Autowired
	ProductAttributeDAO productAttributeDAO;
	
	@Autowired
	AttributeDAO attributeDAO;

	@Override
	public List<ProductVariant> findVariantsByProduct(Integer product_id) {
		// TODO Auto-generated method stub
		return productVariantDAO.findVariantsByProduct(product_id);
	}

	@Override
	public ResponseEntity<?> createVariant(ProductVariantCreateValidation productVariantCreateValidation) {
		// TODO Auto-generated method stub
		if(productVariantCreateValidation.getImage().equals("1.jpg")) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Vui lòng chọn hình ảnh cho biến thể.", "image"));
		}
		Calendar created_at = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		List<ProductVariant> variants = productVariantDAO.findVariantsByProduct(productVariantCreateValidation.getProduct().getId());
		Integer result = productVariantCreateValidation.getAttributes().size();
		for(ProductVariant variant:variants){
			Integer check_variant = 0;
			for(Attribute attributed:variant.getAttributes()) {
				for(Attribute attribute:productVariantCreateValidation.getAttributes()) {
					if(attribute.getId() == attributed.getId()) {
						check_variant++;
					}
				}
			}
			if(check_variant == result) {
				return ResponseEntity.badRequest().body(new ResponseMessageError("Biển thể đã tồn tại.", "variant"));
			}
		};
		ProductVariant variant = new ProductVariant(
				productVariantCreateValidation.getName(),
				Integer.parseInt(productVariantCreateValidation.getPrice()),
				0,
				Integer.parseInt(productVariantCreateValidation.getWeight()),
				productVariantCreateValidation.getWeight_unit(),
				Integer.parseInt(productVariantCreateValidation.getHeight()),
				productVariantCreateValidation.getHeight_unit(),
				Integer.parseInt(productVariantCreateValidation.getWidth()),
				productVariantCreateValidation.getWidth_unit(),
				0,
				productVariantCreateValidation.getImage(),
				productVariantCreateValidation.getActivity(),
				null,
				created_at.getTime(),
				false,
				null,
				productVariantCreateValidation.getProduct());
		productVariantDAO.save(variant);
		for (int i = 0; i < productVariantCreateValidation.getAttributes().size(); i++) {
            for (int j = i + 1; j < productVariantCreateValidation.getAttributes().size(); j++) {
            	int level_1 = productVariantCreateValidation.getAttributes().get(i).getAttribute_group().getLevel();
            	int level_2 = productVariantCreateValidation.getAttributes().get(j).getAttribute_group().getLevel();
                if(level_1 > level_2) {
                	Collections.swap(productVariantCreateValidation.getAttributes(), i, j);
                }
            }
        }
		int level = 1;
		for(int i = 0; i < productVariantCreateValidation.getAttributes().size(); i++) {
			ProductAttribute product_attribute = new ProductAttribute();
			product_attribute.setLevel(level);
			product_attribute.setProduct_variant_attribute(variant);
			product_attribute.setAttribute(productVariantCreateValidation.getAttributes().get(i));
			productAttributeDAO.save(product_attribute);
		}
		return ResponseEntity.ok(new ResponseMessage("Biến thể được tạo thành công!"));
	}

	@Override
	public ResponseEntity<?> updateVariant(ProductVariantUpdateValidation productVariantUpdateValidation) {
		// TODO Auto-generated method stub
		ProductVariant variant = productVariantDAO.findById(productVariantUpdateValidation.getId()).get();
		if(variant == null || variant.getDeleted() == true || variant.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Biến thể không tồn tại!", "variant"));
		}
		if(productVariantUpdateValidation.getImage().equals("1.jpg")) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Vui lòng chọn hình ảnh cho biến thể.", "image"));
		}
		List<ProductVariant> variants = productVariantDAO.findVariantsByProduct(productVariantUpdateValidation.getProduct().getId());
		Integer result = productVariantUpdateValidation.getAttributes().size();
		variants.remove(variant);
		for(ProductVariant vrn:variants){
			Integer check_variant = 0;
			for(Attribute attributed:vrn.getAttributes()) {
				for(Attribute attribute:productVariantUpdateValidation.getAttributes()) {
					if(attribute.getId() == attributed.getId()) {
						check_variant++;
					}
				}
			}
			if(check_variant == result) {
				return ResponseEntity.badRequest().body(new ResponseMessageError("Biển thể đã tồn tại.", "variant"));
			}
		};
		variant.setName(productVariantUpdateValidation.getName());
		variant.setWeight(Integer.parseInt(productVariantUpdateValidation.getWeight()));
		variant.setWeight_unit(productVariantUpdateValidation.getWeight_unit());
		variant.setImage(productVariantUpdateValidation.getImage());
		variant.setActivity(productVariantUpdateValidation.getActivity());
		
		List<ProductAttribute> product_attributes = productAttributeDAO.findProductAttributesByVariant(variant.getId());
		int size = 0;
		for(ProductAttribute product_attribute:product_attributes) {
			Attribute attribute_change = null;
			boolean check_attribute = false;
			for(Attribute attribute:productVariantUpdateValidation.getAttributes()) {
				if(attribute.getId() == product_attribute.getAttribute().getId()) {
					productVariantUpdateValidation.getAttributes().remove(attribute);
					check_attribute = true;
				}
				if(attribute.getId() != product_attribute.getAttribute().getId() 
						&& attribute.getAttribute_group().getId() == product_attribute.getAttribute().getAttribute_group().getId()) {
					attribute_change = attribute;
				}
			}
			if(check_attribute == false && attribute_change == null) {
				productAttributeDAO.delete(product_attribute);
				size++;
			}
			if(check_attribute == false && attribute_change != null) {
				product_attribute.setAttribute(attribute_change);
				productAttributeDAO.save(product_attribute);
				productVariantUpdateValidation.getAttributes().remove(attribute_change);
				size++;
			}
		}
		
		if(productVariantUpdateValidation.getAttributes().size() > 0) {
			for(Attribute attribute:productVariantUpdateValidation.getAttributes()) {
				ProductAttribute product_attribute = new ProductAttribute(
						0,
						variant,
						attribute);
				productAttributeDAO.save(product_attribute);
				size++;
			}
		}
		
		if(size > 0) {
			List<ProductAttribute> product_attributes_update = productAttributeDAO.findProductAttributesByVariant(variant.getId());
			for (int i = 0; i < product_attributes_update.size(); i++) {
	            for (int j = i + 1; j < product_attributes_update.size(); j++) {
	            	int level_1 = product_attributes_update.get(i).getAttribute().getAttribute_group().getLevel();
	            	int level_2 = product_attributes_update.get(j).getAttribute().getAttribute_group().getLevel();
	                if(level_1 > level_2) {
	                	Collections.swap(product_attributes_update, i, j);
	                }
	            }
	        }
			int level = 1;
			for(ProductAttribute product_attribute:product_attributes_update) {
				product_attribute.setLevel(level);
				level++;
				productAttributeDAO.save(product_attribute);
			}
		}
		
		productVariantDAO.save(variant);
		return ResponseEntity.ok(new ResponseMessage("Biến thể được cập nhật thành công!"));
	}

	@Override
	public ResponseEntity<?> deleteVariant(ProductVariantUpdateValidation productVariantUpdateValidation) {
		// TODO Auto-generated method stub
		ProductVariant variant = productVariantDAO.findById(productVariantUpdateValidation.getId()).get();
		if(variant == null || variant.getDeleted() == true || variant.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Biến thể không tồn tại!", "variant"));
		}
		if(variant.getQty() > 0) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Biến thể còn hàng tồn kho!", "variant"));
		}
		variant.setDeleted(true);
//		variant.setDeleted_by(null);
		productVariantDAO.save(variant);
		return ResponseEntity.ok(new ResponseMessage("Biến thể được xóa thành công!"));
	}

	@Override
	public ResponseEntity<?> getVariantsByAttributes(
			GetVariantByAttributesValidation getVariantByAttributesValidation) {
		// TODO Auto-generated method stub
		List<ProductVariant> product_variants = productVariantDAO.findVariantsByProduct(getVariantByAttributesValidation.getProduct_id());
		List<ProductVariant> product_variants_filter = new ArrayList<>();
		int size_attributes = getVariantByAttributesValidation.getAttributes().size();
		boolean check_attribute_null = false;
		for(AttributeRequest attribute_request:getVariantByAttributesValidation.getAttributes()) {
			if(attribute_request.getAttribute_id() == 0) {
				check_attribute_null = true;
			}
		}
		if(check_attribute_null == true) {
			size_attributes--;
			for(ProductVariant product_variant:product_variants) {
				if(product_variant.getAttributes().size() == size_attributes) {
					List<ProductAttribute> product_attributes = productAttributeDAO.findByVariantID(product_variant.getId());
					int result = 0;
					for(ProductAttribute product_attribute:product_attributes) {
						for(AttributeRequest attribute_request:getVariantByAttributesValidation.getAttributes()) {
							if(product_attribute.getAttribute().getId() == attribute_request.getAttribute_id() 
									&& product_attribute.getLevel() == attribute_request.getId()) {
								result++;
							}
						}
					}
					if(result == size_attributes) {
						product_variants_filter.add(product_variant);
					}
				}
			}
			if(product_variants_filter.size() == 0) {
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.ok(product_variants_filter);
		}
		for(ProductVariant product_variant:product_variants) {
			List<ProductAttribute> product_attributes = productAttributeDAO.findByVariantID(product_variant.getId());
			int result = 0;
			for(ProductAttribute product_attribute:product_attributes) {
				for(AttributeRequest attribute_request:getVariantByAttributesValidation.getAttributes()) {
					if(product_attribute.getAttribute().getId() == attribute_request.getAttribute_id() 
							&& product_attribute.getLevel() == attribute_request.getId()) {
						result++;
					}
				}
			}
			if(result == size_attributes) {
				product_variants_filter.add(product_variant);
			}
		}
		if(product_variants_filter.size() == 0) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(product_variants_filter);
	}
	
}
