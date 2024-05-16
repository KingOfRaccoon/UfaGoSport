package ru.skittens.ufagosport.ui.navigation

enum class Destinations(val title: String = "") {
    Splash,
    OnBoarding,
    Authentication,
    Registration,
    Main,
    Map("Карта"),
    NewsFriends("Лента новостей"),
    Profile("Профиль"),
    ListFriends("Список друзей"),
    Rating("Рейтинг"),
    Achievement("Достижения"),
    Event("Точка интереса")
}