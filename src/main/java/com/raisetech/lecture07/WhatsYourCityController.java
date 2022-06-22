package com.raisetech.lecture07;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WhatsYourCityController {
  private final CityService cityData;

  @Autowired
  public WhatsYourCityController(CityService cityData) {
    this.cityData = cityData;
  }

  @GetMapping("/city")
  public String whatsYourCity(@RequestParam(defaultValue = "none") String name, Model model) {
    List<String> information = cityData.cityInfo(name);

    model.addAttribute("cityName", information.get(0));
    model.addAttribute("cityDescription", information.get(1));
    model.addAttribute("cityPopulation", information.get(2));
    model.addAttribute("cityIndustrie", information.get(3));
    return "/city";
  }
}
