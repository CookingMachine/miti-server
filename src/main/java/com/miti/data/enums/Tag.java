package com.miti.data.enums;

public enum Tag {

  WIFI("Wi-Fi"),
  DISCOUNT("Акции"),
  FOOD_DELIVERY("Доставка еды"),
  FOOD_OUTSIDE("Еда на вынос"),
  PRESENTS("Подарки"),
  BIRTHDAYS("Дни рождения"),
  LIVE_MUSIC("Живая музыка"),
  PLACE_RESTAURANT("Ресторан"),
  PLACE_BAR("Бар"),
  PLACE_CAFE("Кафе"),
  PLACE_FAST_FOOD("Фастфуд"),
  PLACE_TERRACE("Веранда"),
  PLACE_LOFT("Лофт"),
  PLACE_PIZZERIA("Пиццерия");

  private final String title;

  Tag(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
