# Что делать тебе (без кода) — очень коротко

## 1) Установи инструменты
- Android Studio (последняя стабильная)
- JDK **17** (не 21+ и не 25)

> Почему важно: Android Gradle Plugin в проекте рассчитан на JDK 17.

## 2) Открой проект
- `File -> Open` и выбери папку проекта `GPT`
- Дождись `Gradle Sync`

## 3) Настрой local.properties
В корне проекта создай файл `local.properties` на основе `local.properties.example`:

```properties
sdk.dir=/ABSOLUTE/PATH/TO/Android/Sdk
NUTRITION_API_BASE_URL=https://example.com/
NUTRITION_API_KEY=replace_me
```

## 4) Запусти проверки
В терминале Android Studio:

```bash
./gradlew test
./gradlew assembleDebug
```

## 5) Запусти приложение
- Создай эмулятор (Pixel + Android 14)
- Нажми **Run 'app'**
- Пройди экран Onboarding
- Должен открыться Home с дневными КБЖУ

---

## Если ошибка `25.0.1`
Это почти всегда запуск на неподходящей Java (например, JDK 25).

Сделай так:
1. Android Studio → `Settings -> Build, Execution, Deployment -> Build Tools -> Gradle`
2. `Gradle JDK` = **JDK 17**
3. Синхронизация заново + повтори `./gradlew test`
