# siyai-front-android (ready)

Готовый каркас под ТЗ (multi-module):

- **multi-module**: `app` / `core` / `feature`
- **4 фичи** (каждая разделена на `api` + `impl`):
  - `feature:auth` (локальная авторизация через Room)
  - `feature:feed` (список из API)
  - `feature:details` (деталка элемента)
  - `feature:favorites` (избранное в Room)
- **UI**: Jetpack Compose
- **MVI**: Orbit
- **Navigation**: Voyager
- **Images**: Coil
- **DI**: Hilt
- **DB**: Room (+ KSP)
- **Network**: Retrofit + OkHttp + Kotlin Serialization
- **Version catalog**: `gradle/libs.versions.toml`
- **CI** (GitHub Actions на PR): detekt + unit tests + assembleDebug (+ шаг под Firebase App Distribution, включится после добавления секретов)
- **Firebase подключение предусмотрено**: Analytics + Crashlytics + Performance (**google-services.json не коммитить**)

---

## Быстрый запуск (Windows)

1) **Распакуй проект** в папку с латиницей, например:
   - `D:\siyai-front-android`

2) Открой **Android Studio** → **Open** → выбери папку проекта.

3) Дождись **Gradle Sync**.

4) Подключи телефон по USB (у тебя уже подключился) и нажми **Run ▶**.

> Если открываешь проект прямо из `.zip` — будет куча ошибок. Нужна именно распакованная папка.

---

## Firebase (для скриншотов Analytics / Crashlytics / Performance)

1) Создай проект в Firebase.
2) Добавь Android-приложение с `applicationId`:
   - `com.example.siyai`
3) Скачай `google-services.json` и положи в:
   - `app/google-services.json`
4) Пересобери проект.

> Если `google-services.json` **нет**, проект **всё равно собирается** — Firebase-плагины автоматически не применяются.

---

## Unit tests (>= 50% use-case)

Тесты добавлены на половину use-case (как минимум):

- `feature:auth:impl` — `LoginUseCaseTest`
- `feature:feed:impl` — `GetProductsUseCaseTest`
- `feature:favorites:impl` — `ToggleFavoriteUseCaseTest`

Запуск:

```bash
./gradlew test
```

---

## Структура модулей

- `app`
  - Compose UI + Voyager Nav + подключение фич
- `core:common`
  - общие модели/утилиты
- `core:network`
  - Retrofit + OkHttp + Kotlin Serialization
- `core:db`
  - Room DB + DAO
- `core:analytics`
  - отправка событий (в т.ч. screen_open)
- `feature:<name>:api`
  - контракты/интерфейсы
- `feature:<name>:impl`
  - реализация, use-cases, репозитории, ViewModel
