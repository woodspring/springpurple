package woodspring.springpurple.service;

public interface ForexRateService {
	
	String getCurrenciesList();
	String getAllSpot();
	String getSpot(String currencyPair);

}
