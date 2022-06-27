package com.raisetech.lecture07;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  public List<String> addCity(@RequestBody @Validated CityDataForm cityDataForm) {
    List<String> registerData = new ArrayList<>();

    registerData.add(cityData.addCityName(cityDataForm.getCityName()));
    registerData.add(cityData.addCityDescription(cityDataForm.getCityDescription()));
    registerData.add(cityData.addCityPopulation(cityDataForm.getCityPopulation()));
    registerData.add(cityData.addCityIndustrie(cityDataForm.getCityIndustrie()));
    return registerData;
  }

}
