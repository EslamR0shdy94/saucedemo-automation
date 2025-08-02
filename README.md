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
| Test Case | Description |
|-----------|------------|
|           | Test Case | Description                                                                            |
| **TC01**  | Login with valid Standard User and verify successful navigation to Inventory page      |
| **TC02**  | Attempt login with Locked User and verify error message                                |
| **TC03**  | Attempt login with Problem User and verify UI behavior or broken images                |
| **TC04**  | Login with Performance Glitch User and verify delayed loading                          |
| **TC05**  | Add single/multiple products to cart and verify cart count & contents                  |
| **TC06**  | Remove product from cart and ensure it's no longer listed                              |
| **TC07**  | Verify cart page displays correct product(s) after adding from inventory               |
| **TC08**  | Navigate from Cart to Checkout Info page and verify UI                                 |
| **TC09**  | Leave all checkout info fields empty and verify required field errors                  |
| **TC10**  | Enter only first name and verify last name required error                              |
| **TC11**  | Enter first and last name without postal code and verify postal code required error    |
| **TC12**  | Enter invalid (numeric) name fields and verify system accepts or blocks it             |
| **TC13**  | Submit valid checkout info and navigate to Overview page                               |
| **TC14**  | Verify product info and total in Overview page before completing checkout              |
| **TC15**  | Finish checkout and verify Thank You confirmation message                              |
| **TC16**  | Navigate back from Checkout Info page and verify it returns to Cart (back button flow) |
| **TC17**  | Logout from any page and verify redirection to Login page                              |
| **TC18**  | Checkout flow for error_user — detects form input and order completion bugs  


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