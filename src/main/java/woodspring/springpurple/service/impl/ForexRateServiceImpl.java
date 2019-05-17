package woodspring.springpurple.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodspring.springpurple.dao.ForexApiHttpRepository;
import woodspring.springpurple.dao.InvestingRepository;
import woodspring.springpurple.service.ForexRateService;


@Service
public class ForexRateServiceImpl implements ForexRateService {

	@Autowired
	ForexApiHttpRepository fxApiHttpRepo;
	
	@Autowired
	InvestingRepository investingRepo;
	
	@Override
	public String getCurrenciesList() {
		
		String retStr = new String();
		try {
			retStr = fxApiHttpRepo.sendGet(retStr) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
	}

	@Override
	public String getSpot(String currencyPair) {
		String retStr = currencyPair;
		try {
			retStr = fxApiHttpRepo.sendGet(retStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
	}

	@Override
	public String getAllSpot() {
		String retStr = fxApiHttpRepo.getAllSpotRate();
		return retStr;
	}

	@Override
	public String getForexListFromInv() {
		String retStr = new String();
		try {
			retStr = investingRepo.sendGet(retStr) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
	}
	
	@Override
	public String getFxOptionFromInv() {
		String retStr = new String();
		try {
			retStr = investingRepo.sendGetFxOption(retStr) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
	}

}
