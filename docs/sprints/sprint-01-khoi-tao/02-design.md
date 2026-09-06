# Sprint 01 – Design: Khởi tạo nền tảng

> **Tham chiếu / đáp án** — Học viên **tự viết Design** trước, Tech Lead review, rồi mới mở file này để so sánh.

---

## Sprint: Khởi tạo nền tảng


| Thuộc tính | Giá trị                    |
| ---------- | -------------------------- |
| Sprint ID  | SPRINT-01                  |
| Author     | Dev (học viên)             |
| Reviewer   | Tech Lead (DTEK VN Studio) |


---

## 1. Tổng quan kiến trúc

Sprint 01 xây **tầng nền** của hệ thống ShopVN:

```
┌─────────────────────────────────────────┐
│  (Chưa có Controller / API)            │  ← Sprint 02
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  Repository Layer                        │  ← Sprint 01 (TASK-004)
│  CategoryRepository, ProductRepository   │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  Model Layer (JPA Entity)              │  ← Sprint 01 (TASK-003)
│  Category, Product                     │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  PostgreSQL 16 (Docker)                │  ← Sprint 01 (TASK-002)
│  Flyway V1 migration                   │  ← Sprint 01 (TASK-004)
└─────────────────────────────────────────┘
```

**Vị trí trong dự án:** Module nền tảng — mọi sprint sau (Product API, Auth, Cart...) đều dựa trên entity và DB schema được tạo ở đây.

---

## 2. Cấu trúc project

```
javathucchien/
└── ecommerce/                          ← Maven module chính
    ├── pom.xml
    ├── docker-compose.yml
    └── src/
        ├── main/
        │   ├── java/com/shop/
        │   │   ├── EcommerceApplication.java
        │   │   ├── config/             ← để trống, dùng sau
        │   │   ├── model/
        │   │   │   ├── Category.java
        │   │   │   └── Product.java
        │   │   └── repository/
        │   │       ├── CategoryRepository.java
        │   │       └── ProductRepository.java
        │   └── resources/
        │       ├── application.yml
        │       └── db/migration/
        │           └── V1__init_schema.sql
        └── test/java/com/shop/
            └── repository/
                └── ProductRepositoryTest.java
```

### Dependencies (`pom.xml`)


| Dependency                     | Mục đích                    |
| ------------------------------ | --------------------------- |
| `spring-boot-starter-web`      | Embedded Tomcat, chạy app   |
| `spring-boot-starter-data-jpa` | Hibernate + Spring Data JPA |
| `postgresql`                   | JDBC driver                 |
| `flyway-core`                  | Database migration          |
| `spring-boot-starter-test`     | JUnit 5, `@SpringBootTest`  |


---

## 3. ERD (Entity Relationship Diagram)

```
┌──────────────────────┐         ┌──────────────────────┐
│      categories      │         │       products       │
├──────────────────────┤         ├──────────────────────┤
│ PK  id          BIGSERIAL      │ PK  id          BIGSERIAL
│     name        VARCHAR(100)   │ FK  category_id BIGINT → categories.id
│ FK  parent_id   BIGINT (self)  │     name        VARCHAR(200)
│     created_at  TIMESTAMP      │     price       DECIMAL(15,2)
└──────────┬───────────┘         │     stock       INTEGER DEFAULT 0
           │                     │     image_url   VARCHAR(500)
           │ 1                   │     description TEXT
           │                     │     created_at  TIMESTAMP
           │ N                   └──────────────────────┘
           └──────────────────────── (category_id)
```

### Quan hệ


| Quan hệ             | Kiểu JPA                    | Mô tả                            |
| ------------------- | --------------------------- | -------------------------------- |
| Category → Product  | `@OneToMany` / `@ManyToOne` | Một danh mục có nhiều sản phẩm   |
| Category → Category | `@ManyToOne` self           | Danh mục cha – con (`parent_id`) |


### Entity mới


| Entity     | Table        | Mô tả                                  |
| ---------- | ------------ | -------------------------------------- |
| `Category` | `categories` | Danh mục sản phẩm, hỗ trợ cây phân cấp |
| `Product`  | `products`   | Sản phẩm thuộc một danh mục            |


---

## 4. Flyway Migration V1

**File:** `src/main/resources/db/migration/V1__init_schema.sql`

```sql
-- V1__init_schema.sql
CREATE TABLE categories (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    parent_id   BIGINT        REFERENCES categories(id),
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id          BIGSERIAL PRIMARY KEY,
    category_id BIGINT          NOT NULL REFERENCES categories(id),
    name        VARCHAR(200)    NOT NULL,
    price       DECIMAL(15, 2)  NOT NULL,
    stock       INTEGER         NOT NULL DEFAULT 0,
    image_url   VARCHAR(500),
    description TEXT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_categories_parent_id ON categories(parent_id);
```

---

## 5. Entity Design

### Category.java

```java
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // getters, setters, constructors
}
```

### Product.java

```java
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // getters, setters, constructors
}
```

---

## 6. Repository Interface

```java
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentIsNull();
}

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
```

---

## 7. Cấu hình

### docker-compose.yml

```yaml
services:
  postgres:
    image: postgres:16-alpine
    container_name: shopvn-postgres
    environment:
      POSTGRES_DB: shopvn
      POSTGRES_USER: shopvn
      POSTGRES_PASSWORD: shopvn123
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

### application.yml

```yaml
spring:
  application:
    name: shopvn

  datasource:
    url: jdbc:postgresql://localhost:5432/shopvn
    username: shopvn
    password: shopvn123
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true

  flyway:
    enabled: true
    locations: classpath:db/migration

server:
  port: 8080
```

---

## 8. API Contract

> **Lưu ý:** Sprint 01 **không** triển khai REST API nghiệp vụ. App chỉ cần start thành công; verify qua integration test và kiểm tra DB.

Sprint 02 sẽ bổ sung `GET /api/products`, `POST /api/products`, ...

---

## 9. Class Diagram

```
┌─────────────────────┐
│ EcommerceApplication│
└─────────────────────┘

┌─────────────────────┐       ┌─────────────────────┐
│  CategoryRepository │──────>│      Category       │
│  <<interface>>      │       │  - id: Long         │
│  + findByParent...  │       │  - name: String     │
└─────────────────────┘       │  - parent: Category │
                              │  - createdAt        │
┌─────────────────────┐       └──────────┬──────────┘
│  ProductRepository  │──────>│         │ 1
│  <<interface>>      │       │         │
│  + findByCategory.. │       │         │ *
└─────────────────────┘       ┌──────────▼──────────┐
                              │       Product       │
                              │  - id: Long         │
                              │  - name: String     │
                              │  - price: BigDecimal│
                              │  - stock: Integer   │
                              │  - imageUrl: String │
                              │  - description      │
                              │  - category         │
                              │  - createdAt        │
                              └─────────────────────┘
```

---

## 10. Sequence Diagram – Lưu entity qua Repository

```
Dev/Test          ProductRepository       Hibernate          PostgreSQL
   │                     │                    │                   │
   │ save(product)       │                    │                   │
   │────────────────────>│                    │                   │
   │                     │ persist()          │                   │
   │                     │───────────────────>│                   │
   │                     │                    │ INSERT products   │
   │                     │                    │──────────────────>│
   │                     │                    │<──────────────────│
   │<────────────────────│  Product (id=1)  │                   │
```

---

## 11. DTO Mapping

Sprint 01 **chưa có DTO** — entity dùng trực tiếp trong repository test. DTO (`ProductRequest`, `ProductResponse`) sẽ thêm ở Sprint 02.


| Request DTO   | Entity Field | Validation |
| ------------- | ------------ | ---------- |
| *(Sprint 02)* |              |            |


---

## 12. Quyết định kỹ thuật (ADR)


| #   | Quyết định                       | Lý do                                        |
| --- | -------------------------------- | -------------------------------------------- |
| 1   | Package gốc `com.shop`           | Ngắn gọn, khớp tên dự án ShopVN              |
| 2   | Flyway thay `ddl-auto=update`    | Schema có version, deploy production an toàn |
| 3   | `ddl-auto=validate`              | Hibernate chỉ validate, không tự sửa DB      |
| 4   | `BigDecimal` cho `price`         | Tránh lỗi làm tròn số thực với tiền tệ       |
| 5   | `FetchType.LAZY` cho quan hệ     | Tránh N+1 và load dư dữ liệu khi chưa cần    |
| 6   | PostgreSQL 16 Alpine (Docker)    | Nhẹ, đủ cho dev local                        |
| 7   | Index `category_id`, `parent_id` | Tối ưu query theo danh mục (Sprint 04)       |


---

## 13. Tài liệu liên quan

- [Requirements](01-requirements.md) · [API Tasks](03-api-tasks.md) · [Test](04-test.md)
- [ERD tổng thể dự án](../../00-tong-quan-du-an.md#4-erd--sơ-đồ-cơ-sở-dữ-liệu)

