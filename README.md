# Library CRUD Application

## Серверная часть

### Содержимое базы данных
- Самостоятельные таблицы
  - Информация о библиотеке
    - Телефон
    - Электронная почта
    - Адрес
      - Улица
      - Дом
    - Город
  - Информация о книге
    - Название книги
    - Автор книги
    - Описание книги
    - Издание
    - Издательство
    - Путь к файлу, с изображением обложки книги
  - Информация о клиенте
    - Имя пользователя
    - Пароль (пока что без Spring Security)
    - Дата рождения (например пользователь не сможет брать книги 18+, если он младше 18)
  - Таблица жанров книг
    - Название жанра

- Связи между таблицами
  - Связь мужду книгой и библиотекой, к которой она лежит, если ее не взял клиент 
  (одна библиотека ко многим книгам)
  - Связь между пользователей и книгой (одному пользователю может принадлежать 
  много книг, но книга только одному пользователю)
  - Связь между книгой и ее жанром (многие ко многим)

## Клиентская часть

- Пользователь, который будет вроде библиотекаря
  - Разрешать продление аренды книги

- Страница посетителя библиотеки (пока что без пароля)
  - Валидация значений

- Страница со всеми книгами библиотеки
  - Фильтрация
    - По году выпуска
    - По автору
    - По жанру
  - Сортировка
    - По дате выпустка
    - По названию книги

- Блок с информацией о книге (в списке)
  - Обложка книги
  - Название книги
  - Под ним маленьним шрифтом имя автора

- Блок с информацией о книге (детально)
  - Обложка книги
  - Описание книги
  - Дата публикации
  - Издание, которое есть в библиотеке
  - Количество свободных экземпляров книги в библиотеке

- Информация о клиенте:
  - Информация для самого клиента
    - Список книг, которые он взял
    - Дата получения книги в библиотеке
    - Дата, когда нужно сдать книгу в библиотеку
    - Кнопка запроса продления аренды книги