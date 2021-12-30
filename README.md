# Поиск архитектора
Приложение для поиска архитекторов.

## Используемые технологии
Hilt, Retrofit, Room, Datastore, Gson, Navigation, Databinding, LiveData, Compose, Glide, Coil,
Proguard, Crashlytics

## Краткое описание
Данные архитекторов хранятся в Google таблице. При запуске приложения проверяется есть ли изменения,
при необходимости данные в приложении обновляются. Дальше можно сделать поиск по имеющимся данным.

Приложение состоит из трех модулей. Модуль **COMMON** является библиотекой с общими классами для
других модулей. Два других модуля это разные реализации приложения. Оба модуля делают одно и то же,
но с применением разных библиотек.

## Модуль ONE
Обычная MVVM версия приложения. В котором ViewModel предоставляет данные в xml макеты при помощи
LiveData, иногда с использованием databinding адаптеров.

![search](https://github.com/kulikovman/SearchArchitect/blob/develop/one/screenshots/login.jpg)
![search](https://github.com/kulikovman/SearchArchitect/blob/develop/one/screenshots/search.jpg)
![search](https://github.com/kulikovman/SearchArchitect/blob/develop/one/screenshots/detail.jpg)

Ссылка на apk - https://github.com/kulikovman/SearchArchitect/blob/develop/one/release/one-release.apk

Для входа в приложение использовать: *user_105 / RdpgL8*

## Модуль TWO
Compose версия приложения. Полностью отсутствуют фрагменты. Для построения макетов используются
composable функции, которые отрисовывают экраны на основе state предоставляемый из ViewModel. Так же
используется compose версия навигации. Цвета, темы и шрифты основаны на MaterialTheme.

![search](https://github.com/kulikovman/SearchArchitect/blob/develop/two/screenshots/login.jpg)
![search](https://github.com/kulikovman/SearchArchitect/blob/develop/two/screenshots/search.jpg)
![search](https://github.com/kulikovman/SearchArchitect/blob/develop/two/screenshots/detail.jpg)

Ссылка на apk - https://github.com/kulikovman/SearchArchitect/blob/develop/two/release/two-release.apk

Для входа в приложение использовать: *user_105 / RdpgL8*

## Детали и особенности
- Использование библиотеки Compose (экраны, темы, навигация).
- Использование многомодульной структуры с общим модулем-библиотекой.
- На странице контакта - телефон / почта / ссылка / иконки соцсетей - являются кликабельными и делают соответствующие переходы.
- Аватарки контактов загружаются из Вконтакте, если есть ссылка на страницу и имеется фото профиля.
- Отправка отчетов в Crashlytics, при падении и некоторых ошибках.
- Использование CollapsingToolbarLayout для обычной версии приложения.
- Поддержка светлой/темной темы.