package MobileShop.service;

import java.util.List;

import MobileShop.Entity.Ward;

public interface WardService {

	List<Ward> getListWardByDistrict(Integer districtID);

}
