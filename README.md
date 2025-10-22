# 🏪 ShopService Application

![Build Status](https://github.com/nilijoski/shop-service/actions/workflows/maven.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-25%2B-orange)

## 📘 Overview
This project implements a simple **Shop Management System** written in **Java 25** using **Maven**.  
It demonstrates basic object-oriented design with `Product`, `Order`, `Repository`, and `Service` layers.  
The main logic resides in the `ShopService`, which handles order placement by interacting with both `ProductRepo` and `OrderRepo`.

---

## 🚀 Features

- Manage products and orders in memory  
- Two repository implementations (`List` and `Map`)  
- Order creation with automatic timestamp and validation  
- Product lookup by EAN or name  
- Order lookup by ID or customer  
- Simple console output to simulate shop operations  

---

## 🧠 Example Usage

```java
List<Product> products = new ArrayList<>();
products.add(new Product(1001, "Apple", "iPhone 16", "Latest model"));
ProductRepo productRepo = new ProductRepo(products);

OrderRepo orderRepo = new OrderListRepo();
ShopService shopService = new ShopService(productRepo, orderRepo);

shopService.placeOrder(1, 1001, 2, "John Doe");
```

**Console Output:**
```
Order placed successfully:
Order{ID=1, product=Product{EAN=1001, brand='Apple', productName='iPhone 16', ...}, amount=2, ...}
```

## 🧾 Requirements

- **Java 25**
- **Maven 3.9.11+**
- **JUnit 5**
- **Mockito (optional for tests)**

---

## 🏗️ Future Improvements

- Add persistence (database or JSON storage)  
- Extend order lifecycle (payment, shipping, etc.)  
- Add tests for `ShopService` logic  

---

## 🧑‍💻 Author

**Niko Ilijoski**  
GitHub: [@nilijoski](https://github.com/nilijoski)

---
