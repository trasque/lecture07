package com.raisetech.lecture07;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhatsYourCityRestController {
  private final CityService cityData;

  @Autowired
  public WhatsYourCityRestController(CityService cityData) {
    this.cityData = cityData;
  }

  @GetMapping("/city/data")
  public List<String> whatsYourCity(@RequestParam(defaultValue = "none") String name) {
    return cityData.cityInfo(name);
  }

  @PostMapping("/addcity/data")
  public List<String> addCity(@RequestBody @Validated CityDataForm cityData) {
    List<String> registerData = new ArrayList<>();

    registerData.add(this.cityData.addCityName(cityData.getCityName()));
    registerData.add(this.cityData.addCityDescription(cityData.getCityDescription()));
    registerData.add(this.cityData.addCityPopulation(cityData.getCityPopulation()));
    registerData.add(this.cityData.addCityIndustrie(cityData.getCityIndustrie()));
    return registerData;
  }
}
