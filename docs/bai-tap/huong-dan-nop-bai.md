# Hướng Dẫn Nộp Bài (Dev Workflow)

Học viên nộp kết quả **theo Task ID** trong Sprint, giống quy trình dev thực tế.

---

## 1. Quy trình nộp task

```
Đọc Requirements → Review Design → Pick Task → Code → Test → Commit → PR
```

### Bước 1: Đọc tài liệu Sprint

```bash
git fetch --tags
git checkout day-04    # Baseline ngày bắt đầu Sprint 02

# Đọc theo thứ tự:
docs/sprints/sprint-02-product/01-requirements.md
docs/sprints/sprint-02-product/02-design.md
docs/sprints/sprint-02-product/03-api-tasks.md   ← Pick task
docs/sprints/sprint-02-product/04-test.md          ← Verify
```

### Bước 2: Tạo branch theo Task ID

```bash
git checkout -b student/minh-nguyen/SPRINT-02-TASK-001
```

### Bước 3: Implement + Test

- Code theo Design
- Chạy test case trong `04-test.md`
- Đảm bảo DoD trong task được tick hết

### Bước 4: Commit với Task ID

```bash
git add .
git commit -m "[SPRINT-02-TASK-001] Implement GET /api/products

- Thêm ProductController, ProductService
- Unit test ProductControllerTest
"
git push origin student/minh-nguyen/SPRINT-02-TASK-001
```

### Bước 5: Tạo Pull Request

| Trường | Giá trị |
|--------|---------|
| **Title** | `[SPRINT-02-TASK-001] Implement GET /api/products – Minh Nguyen` |
| **Base** | `develop` |
| **Mô tả** | Link task, test result, ghi chú |

**Template PR description:**

```markdown
## Task
- Sprint: SPRINT-02
- Task ID: SPRINT-02-TASK-001
- Baseline: day-04

## Changes
- ...

## Test Result
- [x] TC-001 PASS
- [x] TC-002 PASS
- [x] Unit test pass

## Checklist (DoD)
- [x] API đúng contract Design
- [x] Validation
- [x] Test pass
```

---

## 2. Quy ước đặt tên branch

```
student/<ho-ten>/SPRINT-XX-TASK-YYY
```

**Ví dụ:**
- `student/minh-nguyen/SPRINT-02-TASK-001`
- `student/thu-ha/SPRINT-05-TASK-004`

---

## 3. Nộp nhiều task trong 1 Sprint

Mỗi task = 1 branch = 1 PR riêng (khuyến nghị).

Hoặc gom nhiều task nhỏ trong 1 ngày:

```
student/minh-nguyen/sprint-02-day-04
```

Commit message vẫn phải có Task ID từng commit.

---

## 4. Deadline

| Loại | Thời hạn |
|------|----------|
| Task trong Sprint | Trước Sprint tiếp theo |
| Sprint review | Cuối tuần Sprint |
| Final (Sprint 06) | +3 ngày sau Day 22 |

---

## 5. Tiêu chí chấm (theo DoD)

| Tiêu chí | Trọng số |
|----------|----------|
| Task hoàn thành đúng Requirements | 40% |
| Code đúng Design / convention | 20% |
| Test case pass (04-test.md) | 30% |
| Commit message + PR chuẩn | 10% |

---

## 6. Lưu ý

- **Task chính** nộp qua PR theo Task ID
- **Bài tập bổ sung** (`docs/bai-tap/`) – tuỳ chọn, nộp cùng PR hoặc PR riêng
- Không push trực tiếp lên `main` / `develop`
- Xem đáp án sau 3 ngày: tag `solution-day-XX` hoặc `solutions/`

---

## 7. Nộp qua group (tuỳ chọn)

```
Họ tên: ...
Task ID: SPRINT-02-TASK-001
Sprint: Sprint 02 – Product API
Link PR: ...
Test: TC-001 PASS, TC-002 PASS
```
