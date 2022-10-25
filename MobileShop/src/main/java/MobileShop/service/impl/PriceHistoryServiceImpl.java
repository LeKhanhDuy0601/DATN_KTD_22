package MobileShop.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Dao.PriceHistoryDAO;
import MobileShop.Dao.ProductDAO;
import MobileShop.Dao.ProductVariantDAO;
import MobileShop.Entity.PriceHistory;
import MobileShop.Entity.Product;
import MobileShop.Entity.ProductVariant;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.PriceHistoryProductCreateValidation;
import MobileShop.Validation.PriceHistoryVariantCreateValidation;
import MobileShop.service.PriceHistoryService;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService{

	@Autowired
	PriceHistoryDAO priceHistoryDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	ProductVariantDAO productVariantDAO;
	
	@Override
	public List<PriceHistory> getListPriceHistories() {
		// TODO Auto-generated method stub
		return priceHistoryDAO.findAll();
	}

	@Override
	public ResponseEntity<?> createPriceHistoryProduct(PriceHistoryProductCreateValidation priceHistoryProductCreateValidation) {
		// TODO Auto-generated method stub
		Product product = productDAO.findById(priceHistoryProductCreateValidation.getId()).get();
		if(product == null || product.getDeleted() == true || product.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Sản phẩm không tồn tại!", "product"));
		}
		Calendar created_at = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		PriceHistory priceHistory = new PriceHistory(
				product.getPrice(),
				Integer.parseInt(priceHistoryProductCreateValidation.getPrice()),
				created_at.getTime(),
				product,
				null,
				null);
		priceHistoryDAO.save(priceHistory);
		product.setPrice(Integer.parseInt(priceHistoryProductCreateValidation.getPrice()));
		productDAO.save(product);
		return ResponseEntity.ok(new ResponseMessage("Giá sản phẩm được cập nhật thành công!"));
	}

	@Override
	public ResponseEntity<?> createPriceHistoryVariant(PriceHistoryVariantCreateValidation priceHistoryVariantCreateValidation) {
		// TODO Auto-generated method stub
		ProductVariant variant = productVariantDAO.findById(priceHistoryVariantCreateValidation.getId()).get();
		if(variant == null || variant.getDeleted() == true || variant.getDeleted_by() != null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Biến thể không tồn tại!", "variant"));
		}
		Calendar created_at = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		PriceHistory priceHistory = new PriceHistory(
				variant.getPrice(),
				Integer.parseInt(priceHistoryVariantCreateValidation.getPrice()),
				created_at.getTime(),
				variant.getProduct(),
				variant,
				null);
		priceHistoryDAO.save(priceHistory);
		variant.setPrice(Integer.parseInt(priceHistoryVariantCreateValidation.getPrice()));
		productVariantDAO.save(variant);
		return ResponseEntity.ok(new ResponseMessage("Giá biến thể được cập nhật thành công!"));
	}

	@Override
	public List<PriceHistory> search(String search) {
		// TODO Auto-generated method stub
		if(search.equals("null")) {
			return priceHistoryDAO.findAll();
		}
		return priceHistoryDAO.search(search, Sort.by(Direction.DESC, "created_at"));
	}
	
}
