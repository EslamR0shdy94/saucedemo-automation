# E-commerce Website Automation - TestNG & Selenium

## 🎯 Objective
This project contains automated tests for an e-commerce website ([Sauce Demo](https://https://www.saucedemo.com)) focusing on:
- ✅ Various Users Login  Validation and check error messages
- ✅ Adding & Deleting Items to Cart with different users
- ✅ Check Glitch user to check website performance
- ✅ Check UI bugs of products on inventory page
- ✅ Check Glitch user to check website performance
- ✅ Checkout Process flow (Valid & Invalid Shipping Info)
- ✅ Check Logout from any page
- ✅ Validate checkout behavior for error_user (form input blocked + Finish button failure)

---

## 🧪 Test Cases
| Test Case | Description                                                                                     |
|----------|-------------------------------------------------------------------------------------------------|
| **TC01** | Test login for multiple users (`standard_user`, `secret_sauce`, expected: )                   |
| **TC02** | Test login for `locked_out_user` and verify error message                                        |
| **TC03** | Test login for `problem_user` and verify broken images / UI bugs                                |
| **TC04** | Test login for `visual_user` and verify standard behavior                                        |
| **TC05** | Performance test for `performance_glitch_user`                                                  |
| **TC06** | Verify `error_user` cannot add/remove items correctly                                            |
| **TC07** | Verify `standard_user` can add and remove all products correctly                                |
| **TC08** | Validate checkout info with various invalid inputs (empty fields, missing data, numeric input)  |
| **TC09** | Checkout flow for `error_user` — detects blocked last name field & Finish button bug            |
| **TC10** | Complete checkout flow with `standard_user` and verify success message                          |
| **TC11** | Logout from Cart page                                                                           |
| **TC12** | Logout from Checkout Complete page                                                              |
| **TC13** | Logout from Checkout Info page                                                                  |
| **TC14** | Logout from Checkout Overview page                                                              |
| **TC15** | Logout from Inventory page                                                                      |

Each test case includes: objective, steps, expected results, screenshots, and pass/fail criteria.

---

## 🧑‍💻 Tech Stack
- Java 17+
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)

---

## 🏗️ Project Structure

```bash
automation-saucedemo [ecommerce_automation]
│
├── src
│   └── test
│       └── java
│       │     ├── pages          # Page Object classes for each page
│       │     ├── Tests          # TestNG test classes
│       │     └── utils          # Utility classes (e.g. WebDriverManager)
│       │ 
│       │
│       │
│       └── resources
│               └── testng.xml          # TestNG suite configuration
│
├── target
│       └── surefire-reports
│                     └── index.html      # TestNG generated report
│                              
│
├── pom.xml                    # Maven dependencies & build config
└── README.md                  # Project documentation
```
## ⚙️ Setup Instructions

### ✅ Prerequisites
- JDK 17 or higher
- Maven installed and configured
- IntelliJ IDEA or Eclipse

### 📥 Installation Steps
```bash
git clone https://github.com/EslamR0shdy94/saucedemo-automation.git
mvn clean install
```

### 🧪 Running Tests
```bash
mvn clean test
```
### 📊 Generate Allure Report with screen shots for both pass/fail tests
```bash
allure serve target/allure-results
```
---

## 👨‍💻 Author

- **Eslam Roshdy**
- [LinkedIn](www.linkedin.com/in/eslam-roshdy-a638b2175)
- Email: esroshdy22@gmail.com

---