# Sprint 01 – API Tasks: Khởi tạo nền tảng

| Thuộc tính | Giá trị |
|-----------|---------|
| Sprint ID | SPRINT-01 |
| Dev | Học viên |
| Baseline tag | `day-01` |
| Ngày | Day 01–03 |

> **Dev: pick task từ bảng dưới → implement → verify bằng [04-test.md](04-test.md)**

---

## Task Board

| Task ID | Title | Ngày | Priority | Estimate | Status |
|---------|-------|------|----------|----------|--------|
| SPRINT-01-TASK-001 | Khởi tạo Spring Boot project + cấu trúc package | Day 01 | P0 | 2h | TODO |
| SPRINT-01-TASK-002 | Docker Compose PostgreSQL + application.yml | Day 01 | P0 | 1h | TODO |
| SPRINT-01-TASK-003 | Entity Product, Category | Day 02 | P0 | 2h | TODO |
| SPRINT-01-TASK-004 | JPA Repository + Flyway migration V1 | Day 03 | P0 | 2h | TODO |

**Status:** `TODO` → `IN PROGRESS` → `IN REVIEW` → `DONE`

---

## Chi tiết Task

### SPRINT-01-TASK-001: Khởi tạo Spring Boot project

| | |
|---|---|
| **Assignee** | Học viên |
| **Priority** | P0 |
| **Estimate** | 2h |
| **Status** | TODO |

**Mô tả:**

Tạo project Spring Boot trong thư mục `ecommerce/`. Dùng [Spring Initializr](https://start.spring.io/) hoặc tạo thủ công.

**File cần tạo:**
- `ecommerce/pom.xml`
- `ecommerce/src/main/java/com/shop/EcommerceApplication.java`
- Package rỗng: `config/`, `model/`, `repository/`

**Dependencies tối thiểu (pom.xml):**
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `postgresql`
- `flyway-core`
- `spring-boot-starter-test`

**Cấu hình pom.xml:**
- `groupId`: `com.shop`
- `artifactId`: `ecommerce`
- Java **21**
- Spring Boot **3.3.x** (parent)

**Definition of Done (DoD):**
- [ ] `cd ecommerce && mvn clean compile` thành công
- [ ] `mvn spring-boot:run` → app start, log không error nghiêm trọng
- [ ] Truy cập http://localhost:8080 (có thể 404 Whitelabel — bình thường, chưa có controller)
- [ ] Package structure khớp [02-design.md](02-design.md)
- [ ] Commit: `[SPRINT-01-TASK-001] Init Spring Boot project`

**Gợi ý kỹ thuật:**
- Dùng IntelliJ: **File → New → Project → Spring Boot**
- Hoặc: `curl` Spring Initializr rồi giải nén vào `ecommerce/`
- Chưa cần DB lúc này — có thể tạm comment datasource nếu chưa làm TASK-002

---

### SPRINT-01-TASK-002: Docker Compose PostgreSQL + application.yml

| | |
|---|---|
| **Assignee** | Học viên |
| **Priority** | P0 |
| **Estimate** | 1h |
| **Status** | TODO |

**Mô tả:**

Thêm PostgreSQL chạy bằng Docker Compose và cấu hình Spring kết nối database.

**File cần tạo / sửa:**
- `ecommerce/docker-compose.yml`
- `ecommerce/src/main/resources/application.yml`

**docker-compose.yml** theo Design:
- Image: `postgres:16-alpine`
- Database: `shopvn`, user/pass: `shopvn` / `shopvn123`
- Port: `5432`

**application.yml** theo Design:
- Datasource URL: `jdbc:postgresql://localhost:5432/shopvn`
- `spring.jpa.hibernate.ddl-auto: validate`
- `spring.flyway.enabled: true`

**Definition of Done (DoD):**
- [ ] `docker compose up -d` trong `ecommerce/` → container `shopvn-postgres` running
- [ ] `docker ps` thấy port `0.0.0.0:5432->5432/tcp`
- [ ] `mvn spring-boot:run` → không lỗi kết nối DB
- [ ] (Tạm thời) Nếu chưa có Flyway V1, có thể đặt `ddl-auto: none` rồi sửa lại ở TASK-004
- [ ] Commit: `[SPRINT-01-TASK-002] Add Docker PostgreSQL`

**Gợi ý kỹ thuật:**
- Đảm bảo Docker Desktop đang **Running** trước khi `docker compose up`
- Lỗi `Connection refused: 5432` → chạy lại `docker compose up -d`
- Kiểm tra DB: `docker exec -it shopvn-postgres psql -U shopvn -d shopvn -c '\dt'`

---

### SPRINT-01-TASK-003: Entity Product, Category

| | |
|---|---|
| **Assignee** | Học viên |
| **Priority** | P0 |
| **Estimate** | 2h |
| **Status** | TODO |

**Mô tả:**

Tạo JPA Entity `Category` và `Product` theo ERD trong [02-design.md](02-design.md).

**File cần tạo:**
- `ecommerce/src/main/java/com/shop/model/Category.java`
- `ecommerce/src/main/java/com/shop/model/Product.java`

**Yêu cầu mapping:**
- Table: `categories`, `products` (snake_case)
- `Category.parent` → self-reference `@ManyToOne`, nullable
- `Product.category` → `@ManyToOne`, `optional = false`
- `price`: `BigDecimal`, precision 15 scale 2
- `createdAt`: `@PrePersist` gán `LocalDateTime.now()`

**Definition of Done (DoD):**
- [ ] Entity mapping đúng Design (field, annotation, quan hệ)
- [ ] `mvn clean compile` pass
- [ ] App vẫn start được (có thể warn Flyway nếu chưa có V1 — OK)
- [ ] Commit: `[SPRINT-01-TASK-003] Add Product Category entities`

**Gợi ý kỹ thuật:**
- Dùng `@Column(name = "created_at")` cho camelCase → snake_case
- Tránh `FetchType.EAGER` trên quan hệ — dùng `LAZY`
- Có thể thêm `equals`/`hashCode` chỉ theo `id` (best practice JPA)

---

### SPRINT-01-TASK-004: JPA Repository + Flyway V1

| | |
|---|---|
| **Assignee** | Học viên |
| **Priority** | P0 |
| **Estimate** | 2h |
| **Status** | TODO |

**Mô tả:**

Tạo Flyway migration V1, Repository interface và integration test xác nhận persist/query hoạt động.

**File cần tạo:**
- `ecommerce/src/main/resources/db/migration/V1__init_schema.sql`
- `ecommerce/src/main/java/com/shop/repository/CategoryRepository.java`
- `ecommerce/src/main/java/com/shop/repository/ProductRepository.java`
- `ecommerce/src/test/java/com/shop/repository/ProductRepositoryTest.java` _(khuyến nghị)_

**Flyway V1:** Copy SQL từ [02-design.md §4](02-design.md#4-flyway-migration-v1)

**Repository methods:**
```java
// CategoryRepository
List<Category> findByParentIsNull();

// ProductRepository
List<Product> findByCategoryId(Long categoryId);
```

**Integration test gợi ý:**
```java
@SpringBootTest
@Transactional
class ProductRepositoryTest {
    // save Category → save Product → findById → assert not null
}
```

**Definition of Done (DoD):**
- [ ] Flyway V1 chạy khi app start — log `Successfully applied 1 migration`
- [ ] `\dt` trong psql thấy `categories`, `products`, `flyway_schema_history`
- [ ] `CategoryRepository.save()` và `ProductRepository.findById()` hoạt động
- [ ] `mvn test` pass (ít nhất `ProductRepositoryTest`)
- [ ] Commit: `[SPRINT-01-TASK-004] Add JPA repo and Flyway V1`

**Gợi ý kỹ thuật:**
- Nếu Flyway báo checksum mismatch sau khi sửa V1 → `flyway repair` hoặc drop DB volume: `docker compose down -v`
- Test dùng `@Transactional` để rollback sau mỗi test method
- Đảm bảo `ddl-auto: validate` — Hibernate không tự tạo bảng

---

## Thứ tự làm task

```
TASK-001 → TASK-002 → TASK-003 → TASK-004
```

| Task | Phụ thuộc |
|------|-----------|
| TASK-002 | TASK-001 (cần project + pom.xml) |
| TASK-003 | TASK-001 (cần package `model/`) |
| TASK-004 | TASK-002 (cần DB) + TASK-003 (cần entity) |

---

## Workflow Git (mỗi task)

```bash
git checkout day-01                    # hoặc tag mới nhất của bạn
git checkout -b student/ten-ban/SPRINT-01-TASK-001
# ... code ...
git add .
git commit -m "[SPRINT-01-TASK-001] Init Spring Boot project"
# Verify 04-test.md → mark DONE
```

---

## Blocked / Notes

| Task | Blocked by | Ghi chú |
|------|-----------|---------|
| TASK-002 | Docker Desktop chưa chạy | Mở Docker Desktop trước |
| TASK-004 | Chưa có entity | Hoàn thành TASK-003 trước |
| TASK-004 | Flyway checksum lỗi | `docker compose down -v` rồi up lại |

---

## Tài liệu liên quan

- [Requirements](01-requirements.md) · [Design](02-design.md) · [Test](04-test.md)
- [Cài đặt môi trường](../../01-cai-dat-moi-truong.md) · [Quy trình Git](../../02-quy-trinh-git.md)
