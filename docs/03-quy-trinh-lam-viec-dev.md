# Quy Trình Làm Việc – Dev Trong Dự Án

Học viên **đóng vai Backend Developer** trong team dự án **ShopVN**. Mỗi Sprint bạn nhận task từ Tech Lead, làm theo quy trình chuẩn của dự án thực tế.

---

## 1. Vai trò trong dự án

| Vai trò | Người đảm nhiệm | Trách nhiệm |
|---------|-----------------|-------------|
| **Product Owner (PO)** | Giảng viên | Viết Requirements, ưu tiên feature |
| **Tech Lead / Architect** | Giảng viên | **Review** Design, review code |
| **Backend Developer** | **Học viên** | Đọc spec, **viết Design**, break task, code, tự test |
| **QA** | Học viên | Verify theo test case |

---

## 2. Quy trình 4 bước mỗi Sprint

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│ 1. REQUIRE  │ →  │ 2. DESIGN   │ →  │ 3. API      │ →  │ 4. TEST     │
│   MENTS     │    │             │    │   TASKS     │    │             │
└─────────────┘    └─────────────┘    └─────────────┘    └─────────────┘
 PO giao spec      Học viên thiết kế    Dev code API       Dev/QA verify
 Học viên đọc      TL review Design    Học viên làm       Pass → Done
```

### Bước 1 – Requirements (PO giao)

- **Ai viết:** Giảng viên (PO)
- **Ai đọc:** Học viên (Dev)
- **File:** `docs/sprints/sprint-XX/01-requirements.md`
- **Nội dung:** User story, acceptance criteria, business rules
- **Dev cần làm:** Đọc kỹ, hỏi PO nếu chưa rõ, **không code khi chưa hiểu requirement**

### Bước 2 – Design (Học viên thiết kế)

- **Ai viết:** **Học viên (Dev)**
- **Ai review:** Giảng viên (Tech Lead)
- **File học viên:** `docs/hoc-vien/<tên-ban>/design-sprint-XX.md` (hoặc nháp trong sprint folder)
- **File tham chiếu:** `docs/sprints/sprint-XX/02-design.md` (đáp án — **mở sau khi nộp Design**)
- **Nội dung:** ERD, API contract, class diagram, sequence diagram
- **Dev cần làm:** Tự thiết kế từ Requirements → nộp → chỉnh theo feedback TL → rồi mới code

### Bước 3 – API Tasks (Dev implement)

- **Ai viết:** Giảng viên (Tech Lead phân task)
- **Ai làm:** **Học viên (Dev)**
- **File:** `docs/sprints/sprint-XX/03-api-tasks.md`
- **Nội dung:** Danh sách task có ID, mô tả, endpoint, DoD (Definition of Done)
- **Dev cần làm:** Pick task → code → commit → tạo PR

### Bước 4 – Test (Dev + QA verify)

- **Ai viết:** Giảng viên (QA spec)
- **Ai chạy:** **Học viên (Dev tự test + QA verify)**
- **File:** `docs/sprints/sprint-XX/04-test.md`
- **Nội dung:** Test case manual, unit test checklist, Postman collection
- **Dev cần làm:** Chạy hết test case → tất cả pass → mark task Done

---

## 3. Workflow hàng ngày của Dev

```
Sáng:  PO publish Requirements
       ↓
       Dev đọc Requirements → tự viết Design → TL review
       ↓
       Dev break task + pick task
       ↓
Chiều: Dev implement API + unit test
       ↓
       Dev chạy test case từ 04-test.md
       ↓
       Dev commit + PR → Tech Lead review
       ↓
Tối:  Tag day-XX trên develop
```

---

## 4. Quy ước Task ID

```
SPRINT-XX-TASK-YYY

Ví dụ:
  SPRINT-02-TASK-001  →  Sprint 2, task đầu tiên
  SPRINT-03-TASK-005  →  Sprint 3, task thứ 5
```

Mỗi task trong `03-api-tasks.md` có:

| Trường | Mô tả |
|--------|-------|
| **Task ID** | Mã định danh |
| **Title** | Tên task ngắn gọn |
| **Assignee** | Học viên tự assign |
| **Priority** | P0 (blocker) / P1 / P2 |
| **Estimate** | Thời gian ước lượng |
| **Description** | Mô tả chi tiết |
| **DoD** | Definition of Done – tiêu chí hoàn thành |
| **Status** | TODO / IN PROGRESS / IN REVIEW / DONE |

---

## 5. Definition of Done (DoD) chung

Task chỉ được mark **DONE** khi:

- [ ] Code compile, không warning nghiêm trọng
- [ ] API đúng contract trong Design
- [ ] Unit test pass (nếu task yêu cầu)
- [ ] Test case manual pass (04-test.md)
- [ ] Commit message có Task ID: `[SPRINT-02-TASK-001] Implement GET /api/products`
- [ ] PR được Tech Lead approve (hoặc self-review nếu tự học)

---

## 6. Commit message theo task

```
[SPRINT-XX-TASK-YYY] Mô tả ngắn

- Chi tiết thay đổi 1
- Chi tiết thay đổi 2
```

**Ví dụ:**

```
[SPRINT-02-TASK-003] Implement POST /api/products

- Thêm ProductCreateRequest DTO
- Thêm validation @NotBlank, @Min
- Unit test ProductControllerTest
```

---

## 7. Map Sprint ↔ Ngày học ↔ Video

| Sprint | Module | Ngày | Tag Git |
|--------|--------|------|---------|
| Sprint 01 | Khởi tạo nền tảng | Day 01–03 | `day-01` → `day-03` |
| Sprint 02 | Product API | Day 04–05 | `day-04` → `day-05` |
| Sprint 03 | Authentication | Day 06–09 | `day-06` → `day-09` |
| Sprint 04 | Catalog | Day 10–12 | `day-10` → `day-12` |
| Sprint 05 | Cart & Order | Day 13–17 | `day-13` → `day-17` |
| Sprint 06 | Nâng cao & Deploy | Day 18–22 | `day-18` → `day-22` |

Video bài giảng (`docs/bai-giang/`) = buổi **Tech Lead walkthrough** – review Design học viên, gợi ý và demo khi cần.

---

## 8. Tài liệu liên quan

- [Sprint Board – Danh sách Sprint](sprints/README.md)
- [Quy trình Git](02-quy-trinh-git.md)
- [Hướng dẫn nộp bài (PR theo task)](bai-tap/huong-dan-nop-bai.md)
