package com.raisetech.lecture07;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
  public List<String> whatsYourCity(@RequestParam(defaultValue = "none") String name, Model model) {
    return cityData.cityInfo(name);
  }

}
