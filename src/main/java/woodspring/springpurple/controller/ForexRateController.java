package woodspring.springpurple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springpurple.service.ForexRateService;

@RestController
@RequestMapping({"/forex"})
public class ForexRateController {
	
	@Autowired
	ForexRateService fxRateSrv;
	
	@GetMapping
	public String findAll(){
	  return fxRateSrv.getCurrenciesList();
	}

	@RequestMapping("/all")
	public String getSllSpotRate(){
	  return fxRateSrv.getAllSpot();
	}
	
	@GetMapping(path = {"/{pair}"})
	public String getSpotByPair(@PathVariable String pair){
		//ResponseEntity<Contact> 
	  return fxRateSrv.getSpot(pair);
	          //.map(record -> ResponseEntity.ok().body(record))
	          //.orElse(ResponseEntity.notFound().build());
	}
}
