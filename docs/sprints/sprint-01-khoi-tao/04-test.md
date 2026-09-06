# Sprint 01 – Test: Khởi tạo nền tảng

---

## Sprint: Khởi tạo nền tảng

| Thuộc tính | Giá trị |
|-----------|---------|
| Sprint ID | SPRINT-01 |
| QA | Dev (self-test) + Tech Lead review |

---

## 1. Test Environment

| Thành phần | Giá trị |
|-----------|---------|
| Working directory | `ecommerce/` |
| Base URL | `http://localhost:8080` |
| Database | PostgreSQL 16 (Docker), DB `shopvn` |
| DB credentials | user `shopvn` / pass `shopvn123` |
| Java | 21 |
| Auth token | Không cần (chưa có API auth) |

### Chuẩn bị trước khi test

```bash
cd ecommerce
docker compose up -d
docker ps   # shopvn-postgres running
mvn clean test
mvn spring-boot:run
```

---

## 2. Manual Test Cases

### TC-001: Project compile và start thành công

| | |
|---|---|
| **Task liên quan** | SPRINT-01-TASK-001 |
| **Priority** | P0 |

**Precondition:** Đã clone repo, JDK 21 và Maven cài đặt OK.

**Steps:**
1. `cd ecommerce`
2. Chạy `mvn clean compile`
3. Chạy `mvn spring-boot:run`
4. Mở browser: http://localhost:8080

**Expected Result:**
- `BUILD SUCCESS` khi compile
- Log có dòng `Started EcommerceApplication` (hoặc tương đương)
- App listen port 8080 (có thể trả 404 — chưa có controller)
- Không có `APPLICATION FAILED TO START`

**Actual Result:** _(Dev điền khi test)_

**Status:** ☐ PASS / ☐ FAIL

---

### TC-002: Docker PostgreSQL chạy và app kết nối DB

| | |
|---|---|
| **Task liên quan** | SPRINT-01-TASK-002 |
| **Priority** | P0 |

**Precondition:** TASK-001 hoàn thành. Docker Desktop đang chạy.

**Steps:**
1. `cd ecommerce`
2. `docker compose up -d`
3. `docker ps` — kiểm tra container `shopvn-postgres`
4. `mvn spring-boot:run`
5. Quan sát log startup

**Expected Result:**
- Container status `Up`, port `5432` mapped
- App start **không** có lỗi `Connection refused` hoặc `FATAL: password authentication failed`
- Log HikariCP: `HikariPool-1 - Start completed` (hoặc tương đương)

**Actual Result:** _(Dev điền khi test)_

**Status:** ☐ PASS / ☐ FAIL

---

### TC-003: Flyway V1 tạo bảng thành công

| | |
|---|---|
| **Task liên quan** | SPRINT-01-TASK-004 |
| **Priority** | P0 |

**Precondition:** TASK-002, TASK-003, TASK-004 hoàn thành.

**Steps:**
1. `docker compose up -d`
2. `mvn spring-boot:run`
3. Kiểm tra log Flyway: `Migrating schema ... to version 1`
4. Vào PostgreSQL:
   ```bash
   docker exec -it shopvn-postgres psql -U shopvn -d shopvn -c '\dt'
   ```
5. Kiểm tra cấu trúc bảng:
   ```bash
   docker exec -it shopvn-postgres psql -U shopvn -d shopvn -c '\d products'
   docker exec -it shopvn-postgres psql -U shopvn -d shopvn -c '\d categories'
   ```

**Expected Result:**
- Bảng: `categories`, `products`, `flyway_schema_history`
- `products` có cột: `id`, `category_id`, `name`, `price`, `stock`, `image_url`, `description`, `created_at`
- `categories` có cột: `id`, `name`, `parent_id`, `created_at`
- Flyway log: `Successfully applied 1 migration`

**Actual Result:** _(Dev điền khi test)_

**Status:** ☐ PASS / ☐ FAIL

---

### TC-004: Repository save và find entity

| | |
|---|---|
| **Task liên quan** | SPRINT-01-TASK-004 |
| **Priority** | P0 |

**Precondition:** TC-003 pass. Integration test đã viết.

**Steps:**
1. `cd ecommerce`
2. `docker compose up -d`
3. Chạy `mvn test -Dtest=ProductRepositoryTest`
4. (Tuỳ chọn) Chạy thủ công trong test hoặc main tạm:
   - Save `Category` tên `"Điện thoại"`
   - Save `Product` gắn category đó, `price = 1990000`, `stock = 10`
   - `findById` → entity không null

**Expected Result:**
- `mvn test` → `BUILD SUCCESS`, `ProductRepositoryTest` pass
- Product sau save có `id` được generate
- `category.getName()` = `"Điện thoại"`
- `price` = `1990000.00`

**Actual Result:** _(Dev điền khi test)_

**Status:** ☐ PASS / ☐ FAIL

---

### TC-005: Entity mapping validate với Hibernate

| | |
|---|---|
| **Task liên quan** | SPRINT-01-TASK-003 |
| **Priority** | P1 |

**Precondition:** Entity + Flyway V1 đã có. `ddl-auto: validate`.

**Steps:**
1. `mvn spring-boot:run`
2. Quan sát log Hibernate startup

**Expected Result:**
- App start thành công — Hibernate **không** báo `Schema-validation: missing table` hoặc `wrong column type`
- Nếu có lỗi validate → entity mapping chưa khớp Flyway SQL

**Actual Result:** _(Dev điền khi test)_

**Status:** ☐ PASS / ☐ FAIL

---

### TC-006: Query theo categoryId

| | |
|---|---|
| **Task liên quan** | SPRINT-01-TASK-004 |
| **Priority** | P1 |

**Precondition:** TC-004 pass. Có ít nhất 2 product cùng category trong test data.

**Steps:**
1. Trong `ProductRepositoryTest` (hoặc test riêng):
   - Tạo 1 category, 2 products cùng category
   - Gọi `productRepository.findByCategoryId(categoryId)`
2. Assert kết quả

**Expected Result:**
- Trả về list size = 2
- Tất cả product thuộc đúng `categoryId`

**Actual Result:** _(Dev điền khi test)_

**Status:** ☐ PASS / ☐ FAIL

---

## 3. Unit / Integration Test Checklist

| Class | Test method | Task | Status |
|-------|-------------|------|--------|
| `ProductRepositoryTest` | `shouldSaveAndFindProduct` | TASK-004 | ☐ |
| `ProductRepositoryTest` | `shouldFindProductsByCategoryId` | TASK-004 | ☐ |
| `CategoryRepositoryTest` | `shouldSaveCategoryWithParent` _(tuỳ chọn)_ | TASK-004 | ☐ |
| `CategoryRepositoryTest` | `shouldFindRootCategories` _(tuỳ chọn)_ | TASK-004 | ☐ |

**Chạy toàn bộ test:**

```bash
cd ecommerce
mvn test
```

**Expected:** `Tests run: X, Failures: 0, Errors: 0`

---

## 4. Smoke Test Commands (copy-paste)

```bash
# 1. Docker
cd ecommerce && docker compose up -d && docker ps

# 2. Build + Test
mvn clean test

# 3. Run app
mvn spring-boot:run

# 4. Check DB tables
docker exec -it shopvn-postgres psql -U shopvn -d shopvn -c "SELECT tablename FROM pg_tables WHERE schemaname='public';"

# 5. Check Flyway history
docker exec -it shopvn-postgres psql -U shopvn -d shopvn -c "SELECT version, description, success FROM flyway_schema_history;"
```

---

## 5. Postman / Bruno

Sprint 01 **không có REST API** — không cần collection Postman/Bruno.

Sprint 02 sẽ bổ sung `bruno/sprint-02-product.bru` hoặc tương đương.

---

## 6. Regression Checklist

Sprint đầu tiên — không có feature cũ. Checklist cho sprint sau:

- [ ] Sau Sprint 02: `GET /api/products` vẫn hoạt động sau khi sửa entity
- [ ] Flyway V2+ không sửa/xóa V1 đã apply

---

## 7. Troubleshooting

| Triệu chứng | Nguyên nhân có thể | Cách xử lý |
|-------------|-------------------|------------|
| `Connection refused: 5432` | PostgreSQL chưa chạy | `docker compose up -d` |
| `password authentication failed` | Sai user/pass trong `application.yml` | Khớp với `docker-compose.yml` |
| `Validate failed: missing table` | Chưa có Flyway V1 hoặc chưa chạy | Kiểm tra file migration + restart app |
| Flyway checksum mismatch | Đã sửa V1 sau khi apply | `docker compose down -v` → up lại |
| `Port 8080 already in use` | App khác chiếm port | Đổi `server.port` hoặc kill process |
| Hibernate `wrong column type` | Entity không khớp SQL | So sánh `@Column` với `V1__init_schema.sql` |

---

## 8. Definition of Done – Sprint 01

Sprint 01 **PASS** khi tất cả điều kiện sau đúng:

- [ ] TC-001 → TC-004: **PASS**
- [ ] `mvn test` pass
- [ ] 4 task trong [03-api-tasks.md](03-api-tasks.md) mark **DONE**
- [ ] Commit message có Task ID đúng format
- [ ] Code đã push / PR (nếu theo quy trình lớp)

---

## 9. Sign-off

| Vai trò | Tên | Ngày | Kết quả |
|---------|-----|------|---------|
| Dev | | | ☐ Pass |
| Tech Lead | | | ☐ Approved |

---

## 10. Tài liệu liên quan

- [Requirements](01-requirements.md) · [Design](02-design.md) · [API Tasks](03-api-tasks.md)
