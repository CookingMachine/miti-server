package com.miti.data.enums;

public enum Kitchen {
  AFRICAN("Африканская"),
  AMERICAN("Американская"),
  ASIAN("Азиатская"),
  BRAZILIAN("Бразильская"),
  CARIBBEAN("Карибская"),
  CHINESE("Китайская"),
  CHUVASH("Чувашская"),
  EASTERN("Восточная"),
  ESTONIAN("Эстонская"),
  EUROPEAN("Европейская"),
  FRENCH("Французская"),
  GEORGIAN("Грузинская"),
  GERMAN("Немецкая"),
  GREEK("Греческая"),
  INDIAN("Индийская"),
  IRISH("Ирландская"),
  ITALIAN("Итальянская"),
  KOREAN("Корейская"),
  KUBAN("Кубанская"),
  LATIN("Латинская"),
  RUSSIAN("Русская"),
  SERBIAN("Сербская"),
  SOUTHEAST("Юго-восточная"),
  SPANISH("Испанская"),
  SRI_LANKAN("Шри-Ланкская"),
  SWEDEN("Шведская"),
  TATAR("Татарская"),
  THAI("Тайская"),
  UDMURT("Удмуртская"),
  UKRAINIAN("Украинская"),
  UZBEK("Узбекская"),
  WESTERN("Западная");

  private final String name;

  Kitchen(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
