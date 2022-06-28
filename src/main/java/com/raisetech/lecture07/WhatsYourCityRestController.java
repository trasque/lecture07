package com.raisetech.lecture07;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 各 HTTPメソッドの挙動は @Controller 側とほぼ同等のため
// そちらのコメントを参照のこと
@RestController
public class WhatsYourCityRestController {
  private final CityService cityData;
  private final List<String> errorData = new ArrayList<>();

  @Autowired
  public WhatsYourCityRestController(CityService cityData) {
    this.cityData = cityData;
    this.errorData.add("Invalid value.");
  }

  @GetMapping("/city/data")
  public List<String> whatsYourCity(@RequestParam(defaultValue = "none") String name) {
    return cityData.cityInfo(name);
  }

  // POST でデータを登録するとAPIが「登録したデータ」を JSONで返す想定
  @PostMapping("/addcity/data")
  public List<String> addCity(@RequestBody @Validated CityDataForm cityDataForm, BindingResult result) {
    if (result.hasErrors()) return errorData;
    List<String> registerData = new ArrayList<>();

    registerData.add(cityData.addCityName(cityDataForm.getCityName()));
    registerData.add(cityData.addCityDescription(cityDataForm.getCityDescription()));
    registerData.add(cityData.addCityPopulation(cityDataForm.getCityPopulation()));
    registerData.add(cityData.addCityIndustrie(cityDataForm.getCityIndustrie()));
    return registerData;
  }

  // PATCH でデータを変更すると APIが「変更した結果」を JSONで返す想定
  @PatchMapping("/editcity/data")
  public List<String> editCity(@RequestBody @Validated CityDataForm cityDataForm, BindingResult result) {
    if (result.hasErrors()) return errorData;
    List<String> editData = new ArrayList<>();

    editData.add(cityData.setCityName(cityDataForm.getCityName()));
    editData.add(cityData.setCityDescription(cityDataForm.getCityDescription()));
    editData.add(cityData.setCityPopulation(cityDataForm.getCityPopulation()));
    editData.add(cityData.setCityIndustrie(cityDataForm.getCityIndustrie()));
    return editData;
  }
  
}
