# QA Mesto Selenium POM Test Project 📧

![Java](https://img.shields.io/badge/Java-11-blue)
![Maven](https://img.shields.io/badge/Maven-3.9+-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.20-green)
![JUnit](https://img.shields.io/badge/JUnit-4-red)
![Coverage](https://img.shields.io/badge/Coverage-100%25-brightgreen)

Проект автоматизированного тестирования для [Mesto](https://qa-mesto.praktikum-services.ru/) с использованием Selenium WebDriver и Java. Проект реализует паттерн Page Object Model (POM) и включает комплексные тесты для проверки функциональности авторизации и редактирования профиля пользователя.

## 🚀 Быстрый старт
### Требования
- **Java JDK 11+** (рекомендуется Zulu 11.0.27+)
- **Apache Maven 3.9+**
- **Google Chrome** (версия 139+)
- **ChromeDriver** (версия 139.0.7258.68+)
- **Firefox** (версия 141+, опционально)

### Установка и запуск
```bash
# Клонирование репозитория
git clone <repository-url>
cd mesto-autotests
# Запуск тестов
mvn clean test
# Запуск с определенным браузером
mvn test -Dbrowser=firefox
# Генерация отчета о покрытии
mvn jacoco:report
```

## 📂 Структура проекта
```
mesto-autotests/
├── src/main/java/ru/mesto/
│   ├── HeaderPage.java           # Page Object для заголовка страницы
│   ├── HomePage.java             # Page Object для главной страницы
│   ├── LoginPage.java            # Page Object для страницы авторизации
│   ├── ProfilePage.java          # Page Object для страницы редактирования профиля
│   └── WebDriverFactory.java     # Фабрика для создания WebDriver
├── src/test/java/ru/mesto/
│   ├── EditProfileTest.java      # Интеграционный тест редактирования профиля
│   ├── HomePageEdgeCaseTest.java # Тесты крайних случаев для HomePage
│   ├── HomePageTest.java         # Юнит-тесты для HomePage
│   ├── LoginPageTest.java        # Юнит-тесты для LoginPage
│   ├── LoginTest.java            # Интеграционный тест авторизации
│   ├── ProfilePageTest.java      # Юнит-тесты для ProfilePage
│   └── WebDriverFactoryTest.java # Тесты для WebDriverFactory
├── target/site/jacoco/           # Отчет о покрытии тестами
├── pom.xml                       # Конфигурация Maven
├── LICENSE
├── README.md
└── .gitignore
```

## 📋 Функционал
### Задача 1: Проверка авторизации
- Ввод email и пароля на странице авторизации
- Нажатие кнопки входа в систему
- Ожидание загрузки главной страницы
- Проверка отображения email пользователя в заголовке страницы

### Задача 2: Редактирование профиля
- Ожидание загрузки данных профиля на главной странице
- Нажатие кнопки редактирования профиля
- Ввод нового значения в поле "Занятие"
- Сохранение изменений
- Проверка обновления данных на главной странице

### Дополнительные возможности
- Поддержка нескольких браузеров (Chrome, Firefox)
- Явные ожидания элементов страницы
- Параллельное выполнение тестов
- 100% покрытие кода тестами

## 📊 Покрытие тестами
Проект включает комплексные модульные и интеграционные тесты с 100% покрытием кода:

**Результаты тестирования:**
- ✅ Tests run: 20+
- ✅ Failures: 0
- ✅ Errors: 0
- ✅ Skipped: 0
- ✅ Code Coverage: 100%

**Запуск тестов с проверкой покрытия:**
```bash
mvn clean test
```
Отчет о покрытии генерируется в директории `target/site/jacoco/index.html`

## 💻 Пример работы

```java
// Авторизация в системе
WebDriver driver = WebDriverFactory.getDriver();
driver.get("https://qa-mesto.praktikum-services.ru/");

LoginPage loginPage = new LoginPage(driver);
loginPage.login("versaria.o@yandex.ru", "qaswef-ketped-3motKo");

// Проверка авторизации
HeaderPage headerPage = new HeaderPage(driver);
headerPage.waitForLoadHeader();
String actualEmail = headerPage.getEmailFromHeader();
assertThat(actualEmail, is("versaria.o@yandex.ru"));

// Редактирование профиля
HomePage homePage = new HomePage(driver);
homePage.waitForLoadProfileData();
homePage.clickEditProfileButton();

ProfilePage profilePage = new ProfilePage(driver);
String newActivity = "Тестировщик";
profilePage.setNewActivity(newActivity);
profilePage.clickSaveButton();

// Проверка изменений
homePage.waitForChangedActivity(newActivity);
String actualActivity = homePage.getActivity();
assertEquals(newActivity, actualActivity);
```

## 📜 Лицензия
MIT License. Полный текст доступен в файле [LICENSE](https://github.com/Versaria/qa-mesto-selenium-test/blob/main/LICENSE).

## 🤝 Как внести вклад
1. Форкните проект
2. Создайте ветку для вашей функции (`git checkout -b feature/AmazingFeature`)
3. Закоммитьте изменения (`git commit -m 'Add some AmazingFeature'`)
4. Запушьте в ветку (`git push origin feature/AmazingFeature`)
5. Откройте Pull Request

---

<details>
<summary>🔧 Дополнительные команды</summary>

```bash
# Запуск конкретного тестового класса
mvn test -Dtest=LoginTest

# Запуск тестов с детальным логированием
mvn test -Dselenium.log.level=ALL

# Пропуск тестов (только компиляция)
mvn clean compile
```
</details>