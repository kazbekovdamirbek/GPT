# FoodLens (MVP Skeleton)

Android-приложение “Фото еды → калории и БЖУ”.

## Что уже сделано в этом шаге
- Базовый каркас проекта на **Kotlin + Jetpack Compose**.
- Архитектура: **MVVM + Clean Architecture**.
- DI: **Hilt**.
- Локальное хранение профиля: **DataStore**.
- Подготовлена БД: **Room** (`MealEntryEntity`, `MealEntryDao`, `FoodLensDatabase`).
- Подключены Firebase SDK, Retrofit/OkHttp (как инфраструктурная база для следующих шагов).
- Реализованы 2 экрана MVP:
  - `Onboarding`
  - `Home`
- Добавлены базовые unit-тесты.

## local.properties (не коммитить ключи)
Скопируйте `local.properties.example` в `local.properties` и заполните:

```properties
sdk.dir=/Users/<you>/Library/Android/sdk
NUTRITION_API_BASE_URL=https://example.com/
NUTRITION_API_KEY=replace_me
```

## Первый запуск
```bash
./gradlew test
./gradlew assembleDebug
```

## Структура
```text
app/src/main/java/com/foodlens
├── data
│   ├── local
│   └── repository
├── di
├── domain
│   ├── model
│   ├── repository
│   └── usecase
├── presentation
│   ├── home
│   ├── navigation
│   └── onboarding
└── ui/theme
```

## Для тебя (очень простой пошаговый старт)
Открой инструкцию: `docs/STEP_BY_STEP_FOR_YOU_RU.md`

Быстрая проверка окружения:
```bash
./scripts/check_env.sh
```
