package com.raisetech.lecture07;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WhatsYourCityController {
  private final CityService cityData;
  private final String cityName;
  private final String cityDescription;
  private final String cityPopulation;
  private final String cityIndustrie;

  @Autowired
  public WhatsYourCityController(CityService cityData) {
    this.cityData = cityData;
    this.cityName = "cityName";
    this.cityDescription = "cityDescription";
    this.cityPopulation = "cityPopulation";
    this.cityIndustrie = "cityIndustrie";
  }

  // 今回の GET ではバリデーションしない
  // 登録された市だけ動作し、それ以外の文字列はすべて「登録なし」のデータを返す
  @GetMapping("/city")
  public String whatsYourCity(@RequestParam(defaultValue = "none") String name, Model model) {
    List<String> information = cityData.cityInfo(name);

    model.addAttribute(cityName, information.get(0));
    model.addAttribute(cityDescription, information.get(1));
    model.addAttribute(cityPopulation, information.get(2));
    model.addAttribute(cityIndustrie, information.get(3));
    return "/city";
  }

  // POST は RequestBody で受ける -> CityDataForm.java でマッピングされる
  // @Validated により CityDataForm.java 内でバリデーションが実施される
  // バリデーション違反すると 400 Bad Request とかになるが、今回は例外を拾う
  // 例外は BindingResult result に格納される　その場合は errorpage.html に飛ぶ
  @PostMapping("/addcity") 
  public String addCity(@RequestBody @Validated CityDataForm cityDataForm,
                        BindingResult result,
                        Model model) {
    if (result.hasErrors()) {
      return "/errorpage";
    }

    model.addAttribute(cityName, cityData.addCityName(cityDataForm.getCityName()));
    model.addAttribute(cityDescription, cityData.addCityDescription(cityDataForm.getCityDescription()));
    model.addAttribute(cityPopulation, cityData.addCityPopulation(cityDataForm.getCityPopulation()));
    model.addAttribute(cityIndustrie, cityData.addCityIndustrie(cityDataForm.getCityIndustrie()));
    return "/addcity";
  }

  // 上記 POSTと同等の挙動
  // データベース上書き用の setCityName() などを使う
  @PatchMapping("/editcity")
  public String editCity(@RequestBody @Validated CityDataForm cityDataForm,
                          BindingResult result,
                          Model model) {
    if (result.hasErrors()) {
      return "/errorpage";
    }

    model.addAttribute(cityName, cityData.setCityName(cityDataForm.getCityName()));
    model.addAttribute(cityDescription, cityData.setCityDescription(cityDataForm.getCityDescription()));
    model.addAttribute(cityPopulation, cityData.setCityPopulation(cityDataForm.getCityPopulation()));
    model.addAttribute(cityIndustrie, cityData.setCityIndustrie(cityDataForm.getCityIndustrie()));
    return "/editcity";
  }

  // GET と同様にURLパラメータで街の情報を消すような想定
  @DeleteMapping("/erasecity")
  public String eraseCity(@RequestParam(defaultValue = "") String name, Model model) {
    List<String> eraseInformation = cityData.eraseInfo(name);
    
    model.addAttribute(cityName, eraseInformation.get(0));
    model.addAttribute(cityDescription, eraseInformation.get(1));
    model.addAttribute(cityPopulation, eraseInformation.get(2));
    model.addAttribute(cityIndustrie, eraseInformation.get(3));
    return "/erasecity";
  }
}
