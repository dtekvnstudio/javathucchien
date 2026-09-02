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

---

## Chi tiết Task

### SPRINT-01-TASK-001: Khởi tạo Spring Boot project

**Mô tả:** _(bổ sung bởi Tech Lead)_

**DoD:**
- [ ] Project chạy được `mvn spring-boot:run`
- [ ] Package structure đúng convention
- [ ] Commit: `[SPRINT-01-TASK-001] Init Spring Boot project`

---

### SPRINT-01-TASK-002: Docker Compose PostgreSQL

**Mô tả:** _(bổ sung)_

**DoD:**
- [ ] `docker compose up -d` chạy PostgreSQL OK
- [ ] App connect DB thành công
- [ ] Commit: `[SPRINT-01-TASK-002] Add Docker PostgreSQL`

---

### SPRINT-01-TASK-003: Entity Product, Category

**Mô tả:** _(bổ sung)_

**DoD:**
- [ ] Entity mapping đúng Design
- [ ] Commit: `[SPRINT-01-TASK-003] Add Product Category entities`

---

### SPRINT-01-TASK-004: JPA Repository + Flyway V1

**Mô tả:** _(bổ sung)_

**DoD:**
- [ ] Flyway V1 tạo bảng thành công
- [ ] Repository query được
- [ ] Commit: `[SPRINT-01-TASK-004] Add JPA repo and Flyway V1`

---

## Thứ tự làm task

```
TASK-001 → TASK-002 → TASK-003 → TASK-004
```

## Tài liệu liên quan

- [Requirements](01-requirements.md)
- [Design](02-design.md)
- [Test](04-test.md)
