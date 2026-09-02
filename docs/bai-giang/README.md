# Bài Giảng – Tech Lead Walkthrough

Video bài giảng = buổi **Tech Lead walkthrough**: giải thích Requirements, Design và demo cách implement task trong Sprint.

> Nội dung chi tiết do giảng viên tự viết. Mỗi video gắn với 1 Sprint / 1 ngày.

---

## Map Video ↔ Sprint ↔ Task

| Ngày | Sprint | Chủ đề walkthrough | Sprint docs |
|------|--------|-------------------|-------------|
| 01 | Sprint 01 | Khởi tạo project + Docker | [sprint-01](../sprints/sprint-01-khoi-tao/) |
| 02 | Sprint 01 | Domain Model | [sprint-01](../sprints/sprint-01-khoi-tao/) |
| 03 | Sprint 01 | JPA + Flyway | [sprint-01](../sprints/sprint-01-khoi-tao/) |
| 04 | Sprint 02 | REST API GET/POST Product | [sprint-02](../sprints/sprint-02-product/) |
| 05 | Sprint 02 | CRUD + Validation | [sprint-02](../sprints/sprint-02-product/) |
| 06 | Sprint 03 | User & Register | [sprint-03](../sprints/sprint-03-auth/) |
| 07 | Sprint 03 | JWT Auth | [sprint-03](../sprints/sprint-03-auth/) |
| 08 | Sprint 03 | Phân quyền | [sprint-03](../sprints/sprint-03-auth/) |
| 09 | Sprint 03 | Profile user | [sprint-03](../sprints/sprint-03-auth/) |
| 10 | Sprint 04 | Category | [sprint-04](../sprints/sprint-04-catalog/) |
| 11 | Sprint 04 | Pagination & Search | [sprint-04](../sprints/sprint-04-catalog/) |
| 12 | Sprint 04 | Upload ảnh | [sprint-04](../sprints/sprint-04-catalog/) |
| 13 | Sprint 05 | Giỏ hàng | [sprint-05](../sprints/sprint-05-cart-order/) |
| 14 | Sprint 05 | Merge Cart | [sprint-05](../sprints/sprint-05-cart-order/) |
| 15 | Sprint 05 | Tạo Order | [sprint-05](../sprints/sprint-05-cart-order/) |
| 16 | Sprint 05 | Order Status | [sprint-05](../sprints/sprint-05-cart-order/) |
| 17 | Sprint 05 | Order History | [sprint-05](../sprints/sprint-05-cart-order/) |
| 18 | Sprint 06 | Thanh toán | [sprint-06](../sprints/sprint-06-nang-cao/) |
| 19 | Sprint 06 | Email | [sprint-06](../sprints/sprint-06-nang-cao/) |
| 20 | Sprint 06 | Redis Cache | [sprint-06](../sprints/sprint-06-nang-cao/) |
| 21 | Sprint 06 | Admin Dashboard | [sprint-06](../sprints/sprint-06-nang-cao/) |
| 22 | Sprint 06 | Docker + Swagger | [sprint-06](../sprints/sprint-06-nang-cao/) |

---

## Cấu trúc mỗi buổi video (gợi ý)

1. **Review Requirements** – PO đã giao gì?
2. **Walkthrough Design** – ERD, API contract
3. **Demo implement Task** – code từng bước
4. **Chạy Test** – verify theo 04-test.md
5. **Q&A / Checkpoint**

---

## File bài giảng

| Ngày | File |
|------|------|
| 01 | [day-01-khoi-tao-project.md](day-01-khoi-tao-project.md) |
| 02 | [day-02-domain-model.md](day-02-domain-model.md) |
| ... | _(day-03 → day-22)_ |

---

## Template walkthrough

```markdown
# Day XX: [Chủ đề] – Tech Lead Walkthrough

## Sprint & Task
- Sprint: SPRINT-XX
- Task: SPRINT-XX-TASK-YYY

## Video
🔗 _(link YouTube)_

## 1. Review Requirements
_(Tóm tắt user story hôm nay)_

## 2. Walkthrough Design
_(ERD, API contract liên quan)_

## 3. Demo Implement
_(Snippet code chính)_

## 4. Verify Test
_(Chạy test case nào)_

## Checkpoint
- [ ] Task DONE theo DoD
```
