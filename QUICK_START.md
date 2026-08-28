# ✅ QUICK START GUIDE - Tiles & Granites Spring Boot Application

**Location:** `C:\Users\harsa\Desktop\Tiles And granities`

---

## 📋 Installation & Setup Steps

### Step 1: Install Prerequisites

#### Java 17+ (if not installed)
```
Download: https://www.oracle.com/java/technologies/downloads/
Extract and add to PATH
```

#### Maven (if not installed)
```
Download: https://maven.apache.org/download.cgi
Extract and add to PATH
Verify: mvn --version
```

#### PostgreSQL Server (if not installed)
```
Download: https://www.postgresql.org/download/
Install and start PostgreSQL service
Verify: psql -U postgres -d postgres
```

---

### Step 2: Create PostgreSQL Database

Open **psql** or **pgAdmin** and run:

```sql
CREATE DATABASE tile_and_granite;
\c tile_and_granite
\dt  -- Should show 0 tables initially
```

---

### Step 3: Configure Database Connection

**File:** `src/main/resources/application.properties`

Find and update these lines:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tile_and_granite
spring.datasource.username=postgres
spring.datasource.password=YOUR_POSTGRES_PASSWORD
```

Replace `YOUR_POSTGRES_PASSWORD` with your PostgreSQL password.

---

### Step 4: Build the Project

Open **Command Prompt** or **PowerShell** and run:

```bash
# Navigate to project directory
cd "C:\Users\harsa\Desktop\Tiles And granities"

# Build the project
mvn clean install

# This will:
# - Download dependencies
# - Compile Java classes
# - Package the application
```

**Expected Output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: X.XXs
```

---

### Step 5: Run the Application

```bash
mvn spring-boot:run
```

**Expected Output:**
```
Started TileAndGraniteApplication in X.XXX seconds
Listening on port 8080
Context path: /api
```

---

### Step 6: Test the Application

#### Option A: Using cURL (Command Line)

```bash
# Test if server is running
curl http://localhost:8080/api/products

# Create a product
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"productCode\":\"PROD001\",\"name\":\"Tile\",\"price\":100,\"quantity\":50,\"type\":\"Ceramic\"}"
```

#### Option B: Using Postman

1. Download **Postman** from https://www.postman.com/downloads/
2. Create new request
3. Set method to `POST`
4. URL: `http://localhost:8080/api/products`
5. Headers: `Content-Type: application/json`
6. Body (JSON):
```json
{
  "productCode": "PROD001",
  "name": "Premium Ceramic Tile",
  "description": "High quality ceramic tile",
  "price": 299.99,
  "quantity": 100,
  "type": "Ceramic"
}
```
7. Click **Send**

---

## 🧪 Sample API Requests

### 1. Create a Customer

**Endpoint:** `POST /api/customers`

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "address": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001"
}
```

### 2. Create a Product

**Endpoint:** `POST /api/products`

```json
{
  "productCode": "PROD001",
  "name": "Premium Floor Tile",
  "description": "Premium quality tile for flooring",
  "price": 299.99,
  "quantity": 100,
  "type": "Ceramic"
}
```

### 3. Create a Tile

**Endpoint:** `POST /api/tiles`

```json
{
  "tileCode": "TILE001",
  "name": "Marble Tile 600x600",
  "description": "Italian marble floor tile",
  "size": "600x600mm",
  "color": "White",
  "material": "Marble",
  "price": 499.99,
  "stock": 200,
  "finishType": "Polished"
}
```

### 4. Create a Granite

**Endpoint:** `POST /api/granites`

```json
{
  "graniteCode": "GRAN001",
  "name": "Black Granite Countertop",
  "description": "Premium black granite",
  "origin": "India",
  "color": "Black",
  "pattern": "Speckled",
  "price": 399.99,
  "quantity": 50,
  "finishType": "Polished",
  "thickness": 25.0
}
```

### 5. Get All Products

**Endpoint:** `GET /api/products`

```bash
curl http://localhost:8080/api/products
```

### 6. Get Product by ID

**Endpoint:** `GET /api/products/1`

```bash
curl http://localhost:8080/api/products/1
```

### 7. Update Product

**Endpoint:** `PUT /api/products/1`

```json
{
  "productCode": "PROD001",
  "name": "Updated Product Name",
  "description": "Updated description",
  "price": 349.99,
  "quantity": 150,
  "type": "Ceramic"
}
```

### 8. Delete Product

**Endpoint:** `DELETE /api/products/1`

```bash
curl -X DELETE http://localhost:8080/api/products/1
```

---

## 📡 Available Endpoints Summary

### Products (/api/products)
- `POST` - Create product
- `GET` - Get all products
- `GET /{id}` - Get by ID
- `GET /code/{code}` - Get by code
- `GET /search?name=xyz` - Search
- `PUT /{id}` - Update
- `DELETE /{id}` - Delete

### Tiles (/api/tiles)
- `POST` - Create tile
- `GET` - Get all
- `GET /{id}` - Get by ID
- `GET /color/{color}` - Filter by color
- `GET /material/{material}` - Filter by material
- `PUT /{id}` - Update
- `DELETE /{id}` - Delete

### Granites (/api/granites)
- `POST` - Create granite
- `GET` - Get all
- `GET /{id}` - Get by ID
- `GET /origin/{origin}` - Filter by origin
- `GET /color/{color}` - Filter by color
- `PUT /{id}` - Update
- `DELETE /{id}` - Delete

### Customers (/api/customers)
- `POST` - Create customer
- `GET` - Get all
- `GET /{id}` - Get by ID
- `GET /email/{email}` - Get by email
- `GET /city/{city}` - Filter by city
- `PUT /{id}` - Update
- `DELETE /{id}` - Delete

### Orders (/api/orders)
- `POST` - Create order
- `GET` - Get all
- `GET /{id}` - Get by ID
- `GET /customer/{customerId}` - Get by customer
- `GET /status/{status}` - Filter by status
- `PUT /{id}` - Update
- `DELETE /{id}` - Delete

---

## ❌ Troubleshooting

### Issue: "mvn is not recognized"
**Solution:**
- Install Maven from https://maven.apache.org/
- Add Maven bin folder to System PATH
- Restart command prompt
- Verify: `mvn --version`

### Issue: "Cannot connect to PostgreSQL"
**Solution:**
- Start PostgreSQL service
- Verify database exists: `CREATE DATABASE tile_and_granite;`
- Check credentials in application.properties
- Verify PostgreSQL is listening on port 5432

### Issue: "Port 8080 already in use"
**Solution:**
- Change port in application.properties:
```properties
server.port=8081
```
- Or kill process using port 8080

### Issue: Build fails with "java.lang.UnsupportedClassVersionError"
**Solution:**
- Ensure Java 17+ is installed
- Verify Java version: `java -version`
- Update Maven: `mvn -U clean install`

### Issue: "Table already exists" or schema issues
**Solution:**
- Drop and recreate database:
```sql
DROP DATABASE tile_and_granite;
CREATE DATABASE tile_and_granite;
```
- Restart application (Hibernate will recreate tables)

---

## 🎯 Testing Workflow

1. ✅ Start application: `mvn spring-boot:run`
2. ✅ Create a customer (note customer ID)
3. ✅ Create products/tiles/granites
4. ✅ Create order linked to customer
5. ✅ Retrieve all records
6. ✅ Update records
7. ✅ Delete records
8. ✅ Search/filter records

---

## 📊 Database Tables

Automatically created in PostgreSQL:

```sql
\dt
-- Output:
-- customers
-- granites
-- orders
-- products
-- tiles

-- Check table structure:
\d customers
\d products
\d tiles
\d granites
\d orders
```

---

## 🛑 Stop Application

Press `Ctrl + C` in the command prompt where application is running.

---

## 📚 Additional Resources

- **Spring Boot Docs:** https://spring.io/projects/spring-boot
- **Spring Data JPA:** https://spring.io/projects/spring-data-jpa
- **PostgreSQL Docs:** https://www.postgresql.org/docs/
- **Maven Guide:** https://maven.apache.org/guides/

---

## ✨ Next Steps

After successfully running the app:
1. Explore all endpoints using Postman
2. Create comprehensive test data
3. Build a frontend application
4. Add authentication (JWT)
5. Add API documentation (Swagger)
6. Deploy to cloud

---

**🎉 You're all set! Happy coding!**

**Project Location:** `C:\Users\harsa\Desktop\Tiles And granities`
