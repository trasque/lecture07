package com.raisetech.lecture07;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CityService {

  //==========
  // GET 用
  //==========
  public List<String> cityInfo(String name) {
    // データベースから引っ張ってきたかのような仮実装
    // cityData 要素の使い道
    // 0] cityName
    // 1] cityDescription
    // 2] cityPopulation
    // 3] cityIndustrie
    List<String> cityData = new ArrayList<>();

    switch (name) {
      case "urayasu" -> {
        cityData.add("浦安市");
        cityData.add("千葉県最後の防衛ライン。しかし既にその実態は、魔法のネズミによって＜＜東京＞＞に捕らえられているも同然だった。");
        cityData.add("164000");
        cityData.add("鉄鋼流通・東京ディズニーリゾート商業施設郡・湾岸高級住宅地");
      }
      case "atsugi" -> {
        cityData.add("厚木市");
        cityData.add("衛星都市の位置にありながら神奈川県内で唯一昼間人口の方が多い産業都市としての側面を持つ。交通要所とされるが山間・農業地帯も多い。");
        cityData.add("223000");
        cityData.add("研究開発・流通及びサービス業・とん漬");
      }
      default -> {
        cityData.add("指定なし");
        cityData.add("指定なし");
        cityData.add("指定なし");
        cityData.add("指定なし");
      }
    }

    return cityData;
  }

  //==========
  // POST 用
  //==========
  public String addCityName(String name) {
    // データベースへ要素を登録する仮実装
    return name;
  }

  public String addCityDescription(String description) {
    // データベースへ要素を登録する仮実装
    return description;
  }

  public String addCityPopulation(String population) {
    // データベースへ要素を登録する仮実装
    return population;
  }

  public String addCityIndustrie(String industrie) {
    // データベースへ要素を登録する仮実装
    return industrie;
  }
  
  //==========
  // PATCH 用
  //==========
  public String setCityName(String name) {
    // データベースの要素を上書きする仮実装
    return name;
  }

  public String setCityDescription(String description) {
    // データベースの要素を上書きする仮実装
    return description;
  }

  public String setCityPopulation(String population) {
    // データベースの要素を上書きする仮実装
    return population;
  }

  public String setCityIndustrie(String industrie) {
    // データベースの要素を上書きする仮実装
    return industrie;
  }

  //==========
  // DELETE 用
  //==========
  public List<String> eraseInfo(String name) {
    List<String> eraseData = new ArrayList<>();

    switch (name) {
      case "urayasu" -> {
        eraseData.add("浦安市");
        eraseData.add("千葉県最後の防衛ライン。しかし既にその実態は、魔法のネズミによって＜＜東京＞＞に捕らえられているも同然だった。");
        eraseData.add("164000");
        eraseData.add("鉄鋼流通・東京ディズニーリゾート商業施設郡・湾岸高級住宅地");
      }
      case "atsugi" -> {
        eraseData.add("厚木市");
        eraseData.add("衛星都市の位置にありながら神奈川県内で唯一昼間人口の方が多い産業都市としての側面を持つ。交通要所とされるが山間・農業地帯も多い。");
        eraseData.add("223000");
        eraseData.add("研究開発・流通及びサービス業・とん漬");
      }
      default -> {
        eraseData.add("指定なし");
        eraseData.add("指定なし");
        eraseData.add("指定なし");
        eraseData.add("指定なし");
      }
    }

    return eraseData;
  }
}
