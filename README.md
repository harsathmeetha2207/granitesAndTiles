# 🎉 Spring Boot CRUD Application - Tiles & Granites

A fully functional **Spring Boot 3.1.5** application with **PostgreSQL** database integration implementing **CRUD operations** for an inventory management system.

## 📦 What's Included

- ✅ 28 Java Classes (Entities, Repositories, Services, Controllers, DTOs)
- ✅ 5 Entities (Product, Tile, Granite, Customer, Order)
- ✅ 50+ RESTful API Endpoints
- ✅ PostgreSQL Database Integration with Auto Schema Creation
- ✅ CORS Support for Frontend Integration
- ✅ Complete CRUD Operations (Create, Read, Update, Delete)
- ✅ Search and Filter Functionality
- ✅ Auto-generated Timestamps (createdAt, updatedAt)

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- PostgreSQL 12+

### Step 1: Create Database
```sql
CREATE DATABASE tile_and_granite;
```

### Step 2: Configure Database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### Step 3: Build & Run
```bash
# Navigate to project directory
cd "C:\Users\harsa\Desktop\Tiles And granities"

# Build
mvn clean install

# Run
mvn spring-boot:run
```

Application starts on: `http://localhost:8080/api`

## 📡 API Endpoints

### Products
- `POST /api/products` - Create
- `GET /api/products` - Get all
- `GET /api/products/{id}` - Get by ID
- `PUT /api/products/{id}` - Update
- `DELETE /api/products/{id}` - Delete

### Tiles
- `POST /api/tiles` - Create
- `GET /api/tiles` - Get all
- `GET /api/tiles/{id}` - Get by ID
- `GET /api/tiles/color/{color}` - Filter by color
- `PUT /api/tiles/{id}` - Update
- `DELETE /api/tiles/{id}` - Delete

### Granites
- `POST /api/granites` - Create
- `GET /api/granites` - Get all
- `GET /api/granites/{id}` - Get by ID
- `GET /api/granites/origin/{origin}` - Filter by origin
- `PUT /api/granites/{id}` - Update
- `DELETE /api/granites/{id}` - Delete

### Customers
- `POST /api/customers` - Create
- `GET /api/customers` - Get all
- `GET /api/customers/{id}` - Get by ID
- `GET /api/customers/email/{email}` - Get by email
- `PUT /api/customers/{id}` - Update
- `DELETE /api/customers/{id}` - Delete

### Orders
- `POST /api/orders` - Create
- `GET /api/orders` - Get all
- `GET /api/orders/{id}` - Get by ID
- `GET /api/orders/customer/{customerId}` - Get by customer
- `PUT /api/orders/{id}` - Update
- `DELETE /api/orders/{id}` - Delete

## 📋 Project Structure

```
src/main/java/com/tileandgranite/
├── entity/              (JPA Entities - 5 classes)
├── repository/          (Spring Data JPA - 5 interfaces)
├── service/             (Business Logic - 5 services)
├── controller/          (REST Endpoints - 5 controllers)
├── dto/                 (DTOs - 5 classes)
├── config/              (Configuration)
├── exception/           (Exception Handling)
└── TileAndGraniteApplication.java (Main App)
```

## 🛠️ Technology Stack

- Spring Boot 3.1.5
- Spring Data JPA
- PostgreSQL 12+
- Maven
- Java 17
- Lombok

## 📚 Database Tables

Automatically created:
- `products`
- `tiles`
- `granites`
- `customers`
- `orders`

## ✨ Features

✅ Full CRUD Operations
✅ RESTful API Design
✅ PostgreSQL Integration
✅ Search & Filter
✅ Error Handling
✅ CORS Support
✅ Auto Timestamps
✅ Layered Architecture
✅ DTO Pattern
✅ Clean Code

## 🔧 Testing

Use **Postman** to test endpoints:

**Create Product Example:**
```json
POST /api/products
{
  "productCode": "PROD001",
  "name": "Premium Tile",
  "price": 299.99,
  "quantity": 100,
  "type": "Ceramic"
}
```

## 📖 Documentation

See detailed API documentation and examples in project files.

---

**Status**: ✅ Ready to Run
**Location**: `C:\Users\harsa\Desktop\Tiles And granities`
