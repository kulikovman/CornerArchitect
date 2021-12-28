# Поиск архитектора
Приложение для поиска архитекторов.

## Краткое описание
Данные архитекторов хранятся в Google таблице. При запуске приложения проверяется есть ли изменения,
при необходимости данные в приложении обновляются. Дальше можно сделать поиск по имеющимся данным.

На странице контакта - телефон / почта / ссылка / иконки соцсетей - являются кликабельными и делают соответствующие переходы.

Аватарки контактов загружаются из Вконтакте, если есть ссылка на страницу и имеется фото профиля.

Приложение состоит из трех модулей. Модуль **COMMON** является библиотекой с общими классами для других модулей. Два других модуля это разные реализации приложения (оба модуля делают одно и то же, но с применением разных библиотек).

## Используемые технологии
Hilt, Retrofit, Room, Datastore, Gson, Navigation, Databinding, LiveData, Compose, Glide, Coil, Proguard, Crashlytics

## Модуль ONE
Модуль MVVM, в котором ViewModel предоставляет данные в xml макеты, через LiveData.

![search](https://github.com/kulikovman/CornerArchitect/blob/develop/one/screenshots/login.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/one/screenshots/search.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/one/screenshots/detail.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/one/screenshots/info.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/one/screenshots/update.jpg)

Ссылка на страницу в маркете - https://play.google.com/store/apps/details?id=com.corner.searcharchitect

Для входа использовать:
>user_105 / RdpgL8

## Модуль TWO
Модуль MVI, в котором ViewModel предоставляет state для composoble функции, на основании которого делается отрисовка экрана.

![search](https://github.com/kulikovman/CornerArchitect/blob/develop/two/screenshots/login.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/two/screenshots/search.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/two/screenshots/detail.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/two/screenshots/info.jpg)
![search](https://github.com/kulikovman/CornerArchitect/blob/develop/two/screenshots/update.jpg)

Ссылка на страницу в маркете - https://play.google.com/store/apps/details?id=com.searcharchitect.two

Для входа использовать:
>user_105 / RdpgL8
