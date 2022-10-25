package MobileShop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.PriceHistory;
import MobileShop.Validation.PriceHistoryProductCreateValidation;
import MobileShop.Validation.PriceHistoryVariantCreateValidation;

public interface PriceHistoryService {

	List<PriceHistory> getListPriceHistories();

	ResponseEntity<?> createPriceHistoryProduct(PriceHistoryProductCreateValidation priceHistoryProductCreateValidation);
	
	ResponseEntity<?> createPriceHistoryVariant(PriceHistoryVariantCreateValidation priceHistoryVariantCreateValidation);

	List<PriceHistory> search(String search);
	
}
