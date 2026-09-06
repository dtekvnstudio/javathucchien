# Sprint Board – Dự Án ShopVN

Mỗi Sprint = một module/feature. Mỗi Sprint có **4 tài liệu** theo quy trình dev thực tế.

---

## Quy trình 4 bước

| Bước | File | Ai viết | Ai làm |
|------|------|---------|--------|
| 1 | `01-requirements.md` | PO (Giảng viên) | Dev đọc & hiểu |
| 2 | `02-design.md` | **Dev (học viên)** | Tech Lead **review** · `02-design.md` = tham chiếu/đáp án |
| 3 | `03-api-tasks.md` | Dev break task | **Dev implement** |
| 4 | `04-test.md` | QA spec | **Dev/QA verify** |

👉 Chi tiết quy trình: [03-quy-trinh-lam-viec-dev.md](../03-quy-trinh-lam-viec-dev.md)

---

## Danh sách Sprint

| Sprint | Module | Ngày | Trạng thái | Tài liệu |
|--------|--------|------|------------|----------|
| **Sprint 01** | Khởi tạo nền tảng | Day 01–03 | ⏳ TODO | [sprint-01-khoi-tao/](sprint-01-khoi-tao/) |
| **Sprint 02** | Product API | Day 04–05 | ⏳ TODO | [sprint-02-product/](sprint-02-product/) |
| **Sprint 03** | Authentication | Day 06–09 | ⏳ TODO | [sprint-03-auth/](sprint-03-auth/) |
| **Sprint 04** | Catalog | Day 10–12 | ⏳ TODO | [sprint-04-catalog/](sprint-04-catalog/) |
| **Sprint 05** | Cart & Order | Day 13–17 | ⏳ TODO | [sprint-05-cart-order/](sprint-05-cart-order/) |
| **Sprint 06** | Nâng cao & Deploy | Day 18–22 | ⏳ TODO | [sprint-06-nang-cao/](sprint-06-nang-cao/) |

---

## Cách Dev bắt đầu một Sprint

```bash
# 1. Checkout code baseline
git checkout day-04    # Ngày bắt đầu Sprint 02

# 2. Đọc theo thứ tự
docs/sprints/sprint-02-product/01-requirements.md
docs/sprints/sprint-02-product/02-design.md
docs/sprints/sprint-02-product/03-api-tasks.md   ← Pick task tại đây
docs/sprints/sprint-02-product/04-test.md        ← Verify sau khi code xong

# 3. Tạo branch làm task
git checkout -b student/ten-ban/SPRINT-02-TASK-001
```

---

## Template nhanh

Khi giảng viên tạo Sprint mới, copy 4 file template từ [\_template/](_template/).
