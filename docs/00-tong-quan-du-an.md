# Tổng Quan Dự Án E-commerce

## 1. Giới thiệu

Dự án **ShopVN** là nền tảng thương mại điện tử xây dựng bằng **Spring Boot 3** + **PostgreSQL**. Học viên **đóng vai Backend Developer**, nhận task từ Tech Lead và phát triển theo quy trình: **Requirements → Design → API → Test**.

### Quy trình làm việc

| Bước | Tài liệu | Vai trò |
|------|----------|---------|
| 1. Requirements | `sprints/sprint-XX/01-requirements.md` | PO giao → Dev đọc |
| 2. Design | `sprints/sprint-XX/02-design.md` | **Dev thiết kế** → Tech Lead review |
| 3. API Tasks | `sprints/sprint-XX/03-api-tasks.md` | **Dev implement** |
| 4. Test | `sprints/sprint-XX/04-test.md` | Dev/QA verify |

👉 Chi tiết: [03-quy-trinh-lam-viec-dev.md](03-quy-trinh-lam-viec-dev.md) · [Sprint Board](sprints/README.md)

### Mục tiêu cuối khóa (MVP)

- [ ] Đăng ký / đăng nhập bằng JWT
- [ ] Phân quyền ADMIN và CUSTOMER
- [ ] CRUD sản phẩm, danh mục, upload ảnh
- [ ] Tìm kiếm, phân trang, lọc sản phẩm
- [ ] Giỏ hàng và đặt hàng
- [ ] Luồng trạng thái đơn hàng
- [ ] Thanh toán mock (VNPay/MoMo)
- [ ] Email xác nhận đơn hàng
- [ ] Cache Redis cho sản phẩm hot
- [ ] Admin dashboard API
- [ ] Docker deploy + Swagger documentation

---

## 2. Tech Stack

| Thành phần | Công nghệ | Phiên bản gợi ý |
|-----------|-----------|-----------------|
| Language | Java | 21 LTS |
| Framework | Spring Boot | 3.3.x |
| ORM | Spring Data JPA / Hibernate | (theo Boot) |
| Database | PostgreSQL | 16.x |
| Migration | Flyway | latest |
| Security | Spring Security + JWT | latest |
| Cache | Redis | 7.x |
| Mail | Spring Mail | (theo Boot) |
| API Docs | SpringDoc OpenAPI (Swagger) | 2.x |
| Container | Docker + Docker Compose | latest |
| Build | Maven | 3.9+ |
| Test | JUnit 5 + MockMvc | (theo Boot) |

---

## 3. Kiến trúc hệ thống

### 3.1 Layered Architecture

```
┌─────────────────────────────────────────┐
│           Client (Postman / FE)         │
└──────────────────┬──────────────────────┘
                   │ HTTP/JSON
┌──────────────────▼──────────────────────┐
│  Controller Layer                        │
│  - Nhận request, trả response            │
│  - Validate input (@Valid)               │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  Service Layer                           │
│  - Business logic                        │
│  - Transaction (@Transactional)          │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  Repository Layer                        │
│  - Truy vấn database (JPA)               │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│  PostgreSQL + Redis                      │
└─────────────────────────────────────────┘
```

### 3.2 Package structure (dự kiến)

```
com.shop
├── EcommerceApplication.java
├── config/           # Security, Redis, Swagger config
├── model/            # JPA Entity
├── repository/       # JPA Repository
├── service/          # Business logic
├── controller/       # REST Controller
├── dto/              # Request / Response DTO
├── mapper/           # Entity ↔ DTO
├── security/         # JWT filter, UserDetails
└── exception/        # Custom exception + Handler
```

---

## 4. ERD – Sơ đồ cơ sở dữ liệu

```
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│   Category   │       │   Product    │       │     User     │
├──────────────┤       ├──────────────┤       ├──────────────┤
│ id           │──┐    │ id           │    ┌──│ id           │
│ name         │  └───<│ category_id  │    │  │ email        │
│ parent_id    │       │ name         │    │  │ password     │
│ created_at   │       │ price        │    │  │ full_name    │
└──────────────┘       │ stock        │    │  │ role         │
                       │ image_url    │    │  │ created_at   │
                       │ description  │    │  └──────┬───────┘
                       │ created_at   │    │         │
                       └──────┬───────┘    │         │
                              │            │         │
         ┌────────────────────┼────────────┼─────────┤
         │                    │            │         │
┌────────▼───────┐   ┌────────▼───────┐   │  ┌──────▼───────┐
│   CartItem     │   │  OrderItem     │   │  │    Cart      │
├────────────────┤   ├────────────────┤   │  ├──────────────┤
│ id             │   │ id             │   │  │ id           │
│ cart_id        │   │ order_id       │   └──│ user_id      │
│ product_id     │   │ product_id     │      │ created_at   │
│ quantity       │   │ quantity       │      └──────┬───────┘
└────────┬───────┘   │ unit_price     │             │
         │           └────────┬───────┘      ┌──────▼───────┐
         │                    │              │   CartItem   │
┌────────▼───────┐   ┌────────▼───────┐      └──────────────┘
│     Cart       │   │     Order      │
└────────────────┘   ├────────────────┤
                     │ id             │
                     │ user_id        │
                     │ status         │
                     │ total_amount   │
                     │ payment_method │
                     │ created_at     │
                     └────────────────┘
```

### Quan hệ chính

| Quan hệ | Kiểu | Mô tả |
|---------|------|-------|
| Category → Product | 1-N | Một danh mục có nhiều sản phẩm |
| Category → Category | 1-N (self) | Danh mục cha – con |
| User → Cart | 1-1 | Mỗi user một giỏ hàng |
| Cart → CartItem | 1-N | Giỏ hàng chứa nhiều item |
| User → Order | 1-N | User có nhiều đơn hàng |
| Order → OrderItem | 1-N | Đơn hàng chứa nhiều sản phẩm |
| Product → OrderItem | 1-N | Sản phẩm xuất hiện trong nhiều đơn |

---

## 5. API Map (dự kiến)

### Auth
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| POST | `/api/auth/register` | Đăng ký | Public |
| POST | `/api/auth/login` | Đăng nhập, trả JWT | Public |

### User
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| GET | `/api/users/me` | Xem profile | Auth |
| PUT | `/api/users/me` | Cập nhật profile | Auth |
| PUT | `/api/users/me/password` | Đổi mật khẩu | Auth |

### Product
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| GET | `/api/products` | Danh sách (pagination) | Public |
| GET | `/api/products/{id}` | Chi tiết sản phẩm | Public |
| GET | `/api/products/search` | Tìm kiếm | Public |
| POST | `/api/products` | Tạo sản phẩm | ADMIN |
| PUT | `/api/products/{id}` | Cập nhật | ADMIN |
| DELETE | `/api/products/{id}` | Xóa | ADMIN |

### Category
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| GET | `/api/categories` | Danh sách danh mục | Public |
| POST | `/api/categories` | Tạo danh mục | ADMIN |

### Cart
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| GET | `/api/cart` | Xem giỏ hàng | Auth |
| POST | `/api/cart/items` | Thêm sản phẩm | Auth |
| PUT | `/api/cart/items/{id}` | Cập nhật số lượng | Auth |
| DELETE | `/api/cart/items/{id}` | Xóa khỏi giỏ | Auth |

### Order
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| POST | `/api/orders` | Tạo đơn từ giỏ | Auth |
| GET | `/api/orders` | Lịch sử đơn hàng | Auth |
| GET | `/api/orders/{id}` | Chi tiết đơn | Auth |
| PUT | `/api/orders/{id}/status` | Cập nhật trạng thái | ADMIN |

### Payment
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| POST | `/api/payments/vnpay/create` | Tạo link thanh toán | Auth |
| GET | `/api/payments/vnpay/callback` | Callback VNPay | Public |

### Admin
| Method | Endpoint | Mô tả | Role |
|--------|----------|-------|------|
| GET | `/api/admin/dashboard` | Thống kê tổng quan | ADMIN |
| GET | `/api/admin/orders` | Quản lý tất cả đơn | ADMIN |

---

## 6. Luồng nghiệp vụ chính

### 6.1 Luồng đặt hàng

```
Customer duyệt SP → Thêm vào Cart → Checkout
    → Tạo Order (PENDING) → Thanh toán
    → PAID → Admin xử lý → SHIPPED → DELIVERED
```

### 6.2 Trạng thái đơn hàng (Order Status)

```
PENDING → PAID → PROCESSING → SHIPPED → DELIVERED
                ↘ CANCELLED (trước khi SHIPPED)
                ↘ REFUNDED (sau khi PAID)
```

---

## 7. Response format chuẩn

### Success

```json
{
  "success": true,
  "message": "Thành công",
  "data": { ... },
  "timestamp": "2026-09-01T10:00:00"
}
```

### Error

```json
{
  "success": false,
  "message": "Sản phẩm không tồn tại",
  "errors": [
    { "field": "name", "message": "Tên không được để trống" }
  ],
  "timestamp": "2026-09-01T10:00:00"
}
```

### Pagination

```json
{
  "success": true,
  "data": {
    "content": [ ... ],
    "page": 0,
    "size": 10,
    "totalElements": 100,
    "totalPages": 10
  }
}
```

---

## 8. Lộ trình xây dựng theo ngày

| Ngày | Module | Entity / Feature mới |
|------|--------|---------------------|
| 01 | Setup | Project skeleton, Docker |
| 02 | Domain | Product, Category (entity) |
| 03 | Database | JPA Repository, Flyway V1 |
| 04–05 | Product API | CRUD + Validation |
| 06–09 | Auth | User, JWT, Role |
| 10–12 | Catalog | Category tree, Search, Upload |
| 13–17 | Order | Cart, Order, OrderItem |
| 18–22 | Advanced | Payment, Email, Cache, Admin, Deploy |

---

## 9. Quy ước đặt tên

| Loại | Quy ước | Ví dụ |
|------|---------|-------|
| Entity | PascalCase, số ít | `Product`, `OrderItem` |
| Table | snake_case, số nhiều | `products`, `order_items` |
| Column | snake_case | `created_at`, `unit_price` |
| API endpoint | kebab-case, số nhiều | `/api/order-items` |
| DTO Request | `XxxRequest` | `ProductCreateRequest` |
| DTO Response | `XxxResponse` | `ProductResponse` |
| Service | `XxxService` | `ProductService` |
| Repository | `XxxRepository` | `ProductRepository` |

---

## 10. Sprint Map

| Sprint | Module | Ngày | Thư mục |
|--------|--------|------|---------|
| Sprint 01 | Khởi tạo nền tảng | Day 01–03 | [sprint-01-khoi-tao](sprints/sprint-01-khoi-tao/) |
| Sprint 02 | Product API | Day 04–05 | [sprint-02-product](sprints/sprint-02-product/) |
| Sprint 03 | Authentication | Day 06–09 | [sprint-03-auth](sprints/sprint-03-auth/) |
| Sprint 04 | Catalog | Day 10–12 | [sprint-04-catalog](sprints/sprint-04-catalog/) |
| Sprint 05 | Cart & Order | Day 13–17 | [sprint-05-cart-order](sprints/sprint-05-cart-order/) |
| Sprint 06 | Nâng cao & Deploy | Day 18–22 | [sprint-06-nang-cao](sprints/sprint-06-nang-cao/) |

---

## 11. Tài liệu liên quan

- [Quy trình làm việc Dev](03-quy-trinh-lam-viec-dev.md)
- [Sprint Board](sprints/README.md)
- [Cài đặt môi trường](01-cai-dat-moi-truong.md)
- [Quy trình Git](02-quy-trinh-git.md)
- [Bài giảng (Tech Lead walkthrough)](../bai-giang/)
- [Bài tập bổ sung](../bai-tap/)
