package com.raisetech.lecture07;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WhatsYourCityController {
  private final CityService cityData;

  @Autowired
  public WhatsYourCityController(CityService cityData) {
    this.cityData = cityData;
  }

  // 今回の GET ではバリデーションしない
  // 登録された文字列以外はすべてデフォルト表示とするため -> CityService.java を参照
  @GetMapping("/city")
  public String whatsYourCity(@RequestParam(defaultValue = "none") String name, Model model) {
    List<String> information = cityData.cityInfo(name);

    model.addAttribute("cityName", information.get(0));
    model.addAttribute("cityDescription", information.get(1));
    model.addAttribute("cityPopulation", information.get(2));
    model.addAttribute("cityIndustrie", information.get(3));
    return "/city";
  }

  // POST は RequestBody で受ける -> CityDataForm.java でマッピングされる
  // Validated により CityDataForm.java 内でバリデーションが実施される
  // バリデーション違反すると 400 Bad Request とかになる
  @PostMapping("/addcity") 
  public String addCity(@RequestBody @Validated CityDataForm cityData, Model model) {
    model.addAttribute("cityName", cityData.getCityName());
    model.addAttribute("cityDescription", cityData.getCityDescription());
    model.addAttribute("cityPopulation", cityData.getCityPopulation());
    model.addAttribute("cityIndustrie", cityData.getCityIndustrie());
    return "/addcity";
  }
}
