package MobileShop.rest.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.PriceHistory;
import MobileShop.Validation.PriceHistoryProductCreateValidation;
import MobileShop.Validation.PriceHistoryVariantCreateValidation;
import MobileShop.service.PriceHistoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/price-histories")
public class PriceHistoryRestController {

	@Autowired
	PriceHistoryService priceHistoryService;
	
	@GetMapping()
	public List<PriceHistory> getListPriceHistories(){
		return priceHistoryService.getListPriceHistories();
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> createPriceHistoryProduct(@RequestBody @Valid PriceHistoryProductCreateValidation priceHistoryProductCreateValidation){
		return priceHistoryService.createPriceHistoryProduct(priceHistoryProductCreateValidation);
	}
	
	@PostMapping("/variant")
	public ResponseEntity<?> createPriceHistoryVariant(@RequestBody @Valid PriceHistoryVariantCreateValidation priceHistoryVariantCreateValidation){
		return priceHistoryService.createPriceHistoryVariant(priceHistoryVariantCreateValidation);
	}
	
	@GetMapping("/{search}")
	public List<PriceHistory> search(@PathVariable("search") String search){
		return priceHistoryService.search(search);
	}
}
