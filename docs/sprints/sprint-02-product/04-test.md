# Template – Test

> Copy file này vào sprint mới, đổi tên thành `04-test.md`

---

## Sprint: Product API

| Thuộc tính | Giá trị |
|-----------|---------|
| Sprint ID | SPRINT-02 |
| QA | Dev (self-test) + Tech Lead review |

---

## 1. Test Environment

| Thành phần | Giá trị |
|-----------|---------|
| Base URL | `http://localhost:8080` |
| Database | PostgreSQL (Docker) |
| Auth token | _(nếu cần)_ |

---

## 2. Manual Test Cases

### TC-001: [Tên test case]

| | |
|---|---|
| **Task liên quan** | SPRINT-02-TASK-001 |
| **Priority** | P0 |

**Precondition:** _(bổ sung)_

**Steps:**
1. ...
2. ...

**Expected Result:**
- HTTP 200
- Response body: `{ ... }`

**Actual Result:** _(Dev điền khi test)_

**Status:** ☐ PASS / ☐ FAIL

---

## 3. Unit Test Checklist

| Class | Test method | Task | Status |
|-------|-------------|------|--------|
| `XxxControllerTest` | `shouldReturn200When...` | TASK-001 | ☐ |
| _(bổ sung)_ | | | |

---

## 4. Postman Collection

- File: `postman/sprint-XX.json` _(sẽ bổ sung)_
- Import vào Postman → chạy toàn bộ → all pass

---

## 5. Regression Checklist

Sprint này không được phá vỡ feature cũ:

- [ ] _(API cũ vẫn hoạt động)_

---

## 6. Sign-off

| Vai trò | Tên | Ngày | Kết quả |
|---------|-----|------|---------|
| Dev | | | ☐ Pass |
| Tech Lead | | | ☐ Approved |
