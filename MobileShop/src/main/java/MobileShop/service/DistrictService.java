package MobileShop.service;

import java.util.List;

import MobileShop.Entity.District;

public interface DistrictService {

	List<District> getListDistrictByCity(Integer cityID);

}
