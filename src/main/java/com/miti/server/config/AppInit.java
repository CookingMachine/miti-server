package com.miti.server.config;

import com.miti.server.enums.IngredientCategory;
import com.miti.server.enums.Kitchen;
import com.miti.server.enums.Measure;
import com.miti.server.enums.Role;
import com.miti.server.model.entity.*;
import com.miti.server.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppInit implements ApplicationRunner {
  private final UserService userService;
  private final RecipeService recipeService;
  private final CategoryService categoryService;
  private final ContextIngredientService contextIngredientService;
  private final IngredientService ingredientService;
  private final CommentService commentService;
  private final CalorieContentService calorieContentService;

  @Override
  public void run(ApplicationArguments args) {
    List<User> users = new ArrayList<>();
    List<Recipe> recipes = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    List<ContextIngredient> contextIngredients = new ArrayList<>();
    List<Ingredient> ingredients = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    List<CalorieContent> calories = new ArrayList<>();

    addUser(users, "admin", "Max", "$2y$12$lMziH.7icjkdRRpDVtaj4uAaG8qlVkBi/Hjl0e7aKsMXQY8i4HQNu", "admin@gmail.com", Role.ADMINISTRATION);
    addUser(users, "moder", "Govnoy", "$2y$12$zbcKnFG.OX.7r2k3nqEyj.9psdQhVzIy5eBvtVQegbPGVHupC0NHS", "moder@gmail.com", Role.MODERATION);
    addUser(users, "user", "Lyoha", "$2y$12$hrib5eAGooCIRrfViL4tju04yqpKdynd67YFqhCaTTPyOdjwEz1ia", "user@gmail.com", Role.USER);
    addUser(users, "poopGrabber", "НИколай22", "$2y$12$hrib5eAGooCIRrfViL4tju04yqpKdynd67YFqhCaTTPyOdjwEz1ia", "poop@yandex.ru", Role.USER);
    addUser(users, "pussyKiller2008", "Антошка", "$2y$12$hrib5eAGooCIRrfViL4tju04yqpKdynd67YFqhCaTTPyOdjwEz1ia", "anton2008bashkin@yahoo.com", Role.MODERATION);
    addUser(users, "youToo", "xxx", "$2y$12$hrib5eAGooCIRrfViL4tju04yqpKdynd67YFqhCaTTPyOdjwEz1ia", "xxx@yandex.ru", Role.USER);
    addUser(users, "robotDolby", "Cipa", "$2y$12$hrib5eAGooCIRrfViL4tju04yqpKdynd67YFqhCaTTPyOdjwEz1ia", "ikop@mial.ru", Role.USER);

    System.out.println(users);

    userService.addAllUsers(users);

    addCategory(categories, "firsts", "Первые блюдо");
    addCategory(categories, "seconds", "Вторые блюдо");
    addCategory(categories, "soups", "Супы");
    addCategory(categories, "meat", "Мясо");
    addCategory(categories, "fish", "Рыба");
    addCategory(categories, "breakfast","Завтрак");
    addCategory(categories, "porridge", "Каши");
    addCategory(categories, "salads", "Салаты");
    addCategory(categories, "dessert", "Десерты");
    addCategory(categories, "snacks", "Закуски");
    addCategory(categories, "non_alc_drinks", "Безалкогольные напитки");
    addCategory(categories, "alc_drinks", "Алкогольные напитки");

    categoryService.addAllCategories(categories);

    addIngredient(ingredients, "tomato", "Помидор", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "cucumber", "Огурец свежий", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "cucumber_salt", "Малосоленный огурец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "cucumber_marinade", "Маринованный огурец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "paprika_red", "Красный болгарский перец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "paprika_green", "Зелёный болгарский перец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "paprika_yellow", "Жёлтый болгарский перец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "apple", "яблоко", IngredientCategory.FRUITS);
    addIngredient(ingredients, "milk", "молоко", IngredientCategory.MILK_PRODUCTS);
    addIngredient(ingredients, "mussels", "мидии", IngredientCategory.SEA_PRODUCTS);
    addIngredient(ingredients, "beef", "говядина", IngredientCategory.MEAT);
    addIngredient(ingredients, "salmon", "лосось", IngredientCategory.FISH);
    addIngredient(ingredients, "italian", "итальянские специи", IngredientCategory.FLAVORING);
    addIngredient(ingredients, "basil", "базелик", IngredientCategory.GREENERY);
    addIngredient(ingredients, "olive_oil", "оливковое масло", IngredientCategory.OILS);
    addIngredient(ingredients, "paper", "перец", IngredientCategory.EXTRA);
    addIngredient(ingredients, "ice", "лёд", IngredientCategory.UNKNOWN);

    ingredientService.addAllIngredients(ingredients);

    addCaloriesContent(calories, 110L, 16L, 18L, 21L);
    addCaloriesContent(calories, 120L, 16L, 18L, 22L);
    addCaloriesContent(calories, 130L, 16L, 18L, 23L);
    addCaloriesContent(calories, 140L, 16L, 18L, 24L);
    addCaloriesContent(calories, 150L, 16L, 18L, 25L);
    addCaloriesContent(calories, 160L, 16L, 18L, 26L);
    addCaloriesContent(calories, 170L, 16L, 18L, 27L);
    addCaloriesContent(calories, 180L, 16L, 18L, 28L);
    addCaloriesContent(calories, 190L, 16L, 18L, 29L);
    addCaloriesContent(calories, 1500L, 16L, 18L, 30L);
    addCaloriesContent(calories, 200L, 16L, 18L, 20L);

    calorieContentService.addAllCalorieContent(calories);

    addRecipe(recipes, "Салат оливье", "Классический салат Оливье в советское время готовили исключительно с вареной колбасой, желательно - с  Докторской. Мы не оступили от традиции и приготовили Оливье по канонам  советской гастрономии.",
      userService.getUserById(1L), categoryService.getCategoryById("salads"), Kitchen.RUSSIAN, 300, calorieContentService.getCalorieContentById(1L));
    addRecipe(recipes, "Картошка фри", "Просто рецепт приготовления картошки фри в сковороде! Картофель фри любим многими!",
      userService.getUserById(2L), categoryService.getCategoryById("snacks"), Kitchen.AMERICAN, 1800, calorieContentService.getCalorieContentById(2L));
    addRecipe(recipes, "Борщ", "Еще не знаете, какое первое блюдо сделать на обед? Я хочу показать вам несложный пошаговый способ, как приготовить самый вкусный борщ. Насыщенный, аппетитный и сытный… чудесная идея для всей семьи! Подробнее: https://povar.ru/recipes/samyi_vkusnyi_borsh-57233.html",
      userService.getUserById(3L), categoryService.getCategoryById("firsts"), Kitchen.UKRAINIAN, 600, calorieContentService.getCalorieContentById(3L));
    addRecipe(recipes, "Котлеты \"Пожарские\" с картофельным пюре", "Сегодня предлагаю вам приготовить нежные куриные котлеты \"Пожарские\" с хрустящей корочкой и подать их с картофельным пюре.",
      userService.getUserById(6L),categoryService.getCategoryById("seconds"), Kitchen.RUSSIAN, 5400, calorieContentService.getCalorieContentById(4L));
    addRecipe(recipes, "Мясо по-французски", "Представляю вашему вниманию популярное блюдо русской кухни, которое, как говорят, было придумано французским поваром Урбеном Дюбуа специально для графа Алексея Орлова. Изначально блюдо называлось \"Телятина по-орловски\", но впоследствии рецепт был упрощен и сейчас блюдо из запеченных слоев мяса, картофеля, лука и сыра известно нам как \"Мясо по-французски\".",
      userService.getUserById(2L), categoryService.getCategoryById("meat"), Kitchen.RUSSIAN, 3600, calorieContentService.getCalorieContentById(5L));
    addRecipe(recipes,"Домашние пельмени", "Готовим вместе пельмени? Тесто для пельменей замешивается на кипятке. Повозиться, конечно, немножко придется, но зато какое наслаждение Вас ожидает потом! Горяченькие домашние пельмени, которые вы сделали своими руками. Сами. :) Это не сложно.",
      userService.getUserById(4L), categoryService.getCategoryById("firsts"), Kitchen.RUSSIAN, 7200, calorieContentService.getCalorieContentById(6L));
    addRecipe(recipes, "Гречка по-купечески", "Гречка \"А добавка будет?\". Если вы никогда не пробовали такой вариант гречки, бегом на кухню! Гречка по-купечески с мясным фаршем - вкусно, полезно и быстро.",
      userService.getUserById(3L), categoryService.getCategoryById("seconds"), Kitchen.RUSSIAN, 1200, calorieContentService.getCalorieContentById(7L));
    addRecipe(recipes, "Рассольник овощной", "Очень вкусный овощной рассольник с перловкой. Люблю лёгкие супы без мяса. Бальзам для желудка).",
      userService.getUserById(2L), categoryService.getCategoryById("soups"), Kitchen.RUSSIAN, 3600, calorieContentService.getCalorieContentById(8L));
    addRecipe(recipes, "Тюря", "Предлагаем вам приготовить старинное блюдо русской кухни. Тюря напоминает окрошку, а сделать тюрю можно буквально за несколько минут. Традиционными ингредиентами этого холодного блюда являются ржаной хлеб и квас или вода. Мы дополнили тюрю зеленым луком, огурцом и хреном.",
      userService.getUserById(5L), categoryService.getCategoryById("firsts"), Kitchen.RUSSIAN, 1800, calorieContentService.getCalorieContentById(9L));
    addRecipe(recipes, "Сырники", "Идеальный завтрак для вас - вкусные, ароматные сырники, которые очень просто сделать. Творог для сырников должен быть свежим, не слишком жирным и однородной текстуры.",
      userService.getUserById(4L),categoryService.getCategoryById("breakfast"), Kitchen.RUSSIAN, 1800, calorieContentService.getCalorieContentById(10L));
    addRecipe(recipes,"Ватрушки с творогом","Рецепт домашних ватрушек с творогом. Восхитительно вкусная домашняя выпечка из дрожжевого теста. Обязательно попробуйте приготовить любимую выпечку детей и взрослых - ватрушки с творогом.",
      userService.getUserById(2L),categoryService.getCategoryById("breakfast"),Kitchen.RUSSIAN, 3600, calorieContentService.getCalorieContentById(11L));

    recipeService.addAllRecipes(recipes);

    addContextIngredient(contextIngredients, 1L, Measure.Ml, ingredientService.getIngredientById("cucumber"),  recipeService.getRecipeById(1L));
    addContextIngredient(contextIngredients, 2L, Measure.Sl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(3L));
    addContextIngredient(contextIngredients, 10L, Measure.Sht, ingredientService.getIngredientById("cucumber_marinade"), recipeService.getRecipeById(1L));
    addContextIngredient(contextIngredients, 1L, Measure.Kg, ingredientService.getIngredientById("cucumber"), recipeService.getRecipeById(2L));
    addContextIngredient(contextIngredients, 1L, Measure.Chl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(1L));
    addContextIngredient(contextIngredients, 1L, Measure.Gr, ingredientService.getIngredientById("paprika_yellow"), recipeService.getRecipeById(1L));
    addContextIngredient(contextIngredients, 1L, Measure.Lit, ingredientService.getIngredientById("tomato"), recipeService.getRecipeById(3L));
    addContextIngredient(contextIngredients, 1L, Measure.St, ingredientService.getIngredientById("cucumber_salt"), recipeService.getRecipeById(1L));
    addContextIngredient(contextIngredients,2L, Measure.Ml, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(2L));
    addContextIngredient(contextIngredients, 1L, Measure.Ml, ingredientService.getIngredientById("cucumber"),  recipeService.getRecipeById(11L));
    addContextIngredient(contextIngredients, 2L, Measure.Sl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(11L));
    addContextIngredient(contextIngredients, 10L, Measure.Sht, ingredientService.getIngredientById("cucumber_marinade"), recipeService.getRecipeById(11L));
    addContextIngredient(contextIngredients, 1L, Measure.Kg, ingredientService.getIngredientById("cucumber"), recipeService.getRecipeById(11L));
    addContextIngredient(contextIngredients, 1L, Measure.Chl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(10L));
    addContextIngredient(contextIngredients, 1L, Measure.Gr, ingredientService.getIngredientById("paprika_yellow"), recipeService.getRecipeById(10L));
    addContextIngredient(contextIngredients, 1L, Measure.Lit, ingredientService.getIngredientById("tomato"), recipeService.getRecipeById(9L));
    addContextIngredient(contextIngredients, 1L, Measure.St, ingredientService.getIngredientById("cucumber_salt"), recipeService.getRecipeById(9L));
    addContextIngredient(contextIngredients,2L, Measure.Ml, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(9L));
    addContextIngredient(contextIngredients, 1L, Measure.Ml, ingredientService.getIngredientById("cucumber"),  recipeService.getRecipeById(9L));
    addContextIngredient(contextIngredients, 2L, Measure.Sl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(8L));
    addContextIngredient(contextIngredients, 10L, Measure.Sht, ingredientService.getIngredientById("cucumber_marinade"), recipeService.getRecipeById(7L));
    addContextIngredient(contextIngredients, 1L, Measure.Kg, ingredientService.getIngredientById("cucumber"), recipeService.getRecipeById(8L));
    addContextIngredient(contextIngredients, 1L, Measure.Chl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(7L));
    addContextIngredient(contextIngredients, 1L, Measure.Gr, ingredientService.getIngredientById("paprika_yellow"), recipeService.getRecipeById(7L));
    addContextIngredient(contextIngredients, 1L, Measure.Lit, ingredientService.getIngredientById("tomato"), recipeService.getRecipeById(6L));
    addContextIngredient(contextIngredients, 1L, Measure.St, ingredientService.getIngredientById("cucumber_salt"), recipeService.getRecipeById(6L));
    addContextIngredient(contextIngredients,2L, Measure.Ml, ingredientService.getIngredientById("paprika_green"), recipeService.getRecipeById(6L));
    addContextIngredient(contextIngredients, 1L, Measure.Ml, ingredientService.getIngredientById("cucumber"),  recipeService.getRecipeById(5L));
    addContextIngredient(contextIngredients, 2L, Measure.Sl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(5L));
    addContextIngredient(contextIngredients, 10L, Measure.Sht, ingredientService.getIngredientById("cucumber_marinade"), recipeService.getRecipeById(4L));
    addContextIngredient(contextIngredients, 1L, Measure.Kg, ingredientService.getIngredientById("cucumber"), recipeService.getRecipeById(4L));
    addContextIngredient(contextIngredients, 1L, Measure.Chl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(8L));
    addContextIngredient(contextIngredients, 1L, Measure.Gr, ingredientService.getIngredientById("paprika_yellow"), recipeService.getRecipeById(4L));
    addContextIngredient(contextIngredients, 1L, Measure.Lit, ingredientService.getIngredientById("tomato"), recipeService.getRecipeById(4L));
    addContextIngredient(contextIngredients, 1L, Measure.St, ingredientService.getIngredientById("cucumber_salt"), recipeService.getRecipeById(4L));
    addContextIngredient(contextIngredients,2L, Measure.Ml, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(4L));

    contextIngredientService.addAllContextIngredients(contextIngredients);

    addComments(comments, "Прекрасный рецепт. Спасибо вам огромное", recipeService.getRecipeById(1L), userService.getUserById(2L));
    addComments(comments, "Очень вкуснуя картошка. А главное - быстро.", recipeService.getRecipeById(2L), userService.getUserById(3L));
    addComments(comments, "Ммм, Лориса. Борщ просто вещь!", recipeService.getRecipeById(3L), userService.getUserById(1L));

    commentService.addAllComments(comments);
  }

  private void addUser(List<User> users, String username, String name, String password, String email, Role role){
    users.add(new User(username, name, password, email, role));
  }

  private void addCategory(List<Category> categories, String id, String name){
    categories.add(new Category(id, name));
  }

  private void addContextIngredient(List<ContextIngredient> contextIngredients, Long amount,
                                    Measure measure, Ingredient ingredient, Recipe recipe) {
    contextIngredients.add(new ContextIngredient(amount, measure, ingredient, recipe));
  }

  private void addIngredient(List<Ingredient> ingredients, String id, String name, IngredientCategory category){
    ingredients.add(new Ingredient(id, name, category));
  }

  private void addRecipe(List<Recipe> recipes, String name, String description, User author, Category category, Kitchen kitchen, int time, CalorieContent calorie){
    recipes.add(new Recipe(name, description, author, category, kitchen, time, calorie));
  }

  private void addComments(List<Comment> comments, String commentText, Recipe recipe, User user){
    comments.add(new Comment(commentText, user, recipe));
  }

  private void addCaloriesContent(List<CalorieContent> caloriesContent, Long calories, Long protein, Long fat, Long carbohydrates) {
    caloriesContent.add(new CalorieContent(calories, protein, fat, carbohydrates));
  }
}
