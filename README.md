# Java Thực Chiến – Xây Dựng Web Thương Mại Điện Tử

Khóa học Java **thực chiến** – học viên **đóng vai Backend Developer** trong dự án **ShopVN**, nhận task và phát triển theo quy trình dự án thật.

**GitHub:** https://github.com/dtekvnstudio/javathucchien

> **Điều kiện tiên quyết:** Học viên đã nắm Java cơ bản (OOP, Collection, Exception, Maven cơ bản).

---

## Bạn là Dev trong dự án

Mỗi Sprint bạn nhận task từ Tech Lead và làm theo **4 bước**:

```
Requirements → Design → API Tasks → Test
     ↓            ↓          ↓         ↓
  PO giao     Dev viết    Dev code   Dev verify
  (đọc)       Design+TL   (làm)      (pass)
              review
```

👉 Chi tiết: [Quy trình làm việc Dev](docs/03-quy-trinh-lam-viec-dev.md)

---

## Bắt đầu nhanh

1. Đọc [Tổng quan dự án ShopVN](docs/00-tong-quan-du-an.md)
2. Cài đặt môi trường: [Hướng dẫn cài đặt](docs/01-cai-dat-moi-truong.md)
3. Làm quen Git: [Quy trình Git](docs/02-quy-trinh-git.md)
4. Vào [Sprint Board](docs/sprints/README.md) → chọn Sprint → đọc Requirements → làm Task

```bash
git clone https://github.com/dtekvnstudio/javathucchien.git
cd javathucchien
git checkout day-01
```

---

## Sprint Board

| Sprint | Module | Ngày | Tài liệu |
|--------|--------|------|----------|
| **Sprint 01** | Khởi tạo nền tảng | Day 01–03 | [docs/sprints/sprint-01-khoi-tao/](docs/sprints/sprint-01-khoi-tao/) |
| **Sprint 02** | Product API | Day 04–05 | [docs/sprints/sprint-02-product/](docs/sprints/sprint-02-product/) |
| **Sprint 03** | Authentication | Day 06–09 | [docs/sprints/sprint-03-auth/](docs/sprints/sprint-03-auth/) |
| **Sprint 04** | Catalog | Day 10–12 | [docs/sprints/sprint-04-catalog/](docs/sprints/sprint-04-catalog/) |
| **Sprint 05** | Cart & Order | Day 13–17 | [docs/sprints/sprint-05-cart-order/](docs/sprints/sprint-05-cart-order/) |
| **Sprint 06** | Nâng cao & Deploy | Day 18–22 | [docs/sprints/sprint-06-nang-cao/](docs/sprints/sprint-06-nang-cao/) |

Mỗi Sprint gồm 4 file:

| File | Vai trò | Dev làm gì |
|------|---------|------------|
| `01-requirements.md` | PO | **Đọc** – hiểu yêu cầu |
| `02-design.md` | Dev (học viên) | **Viết** thiết kế → Tech Lead review · file trong repo = tham chiếu |
| `03-api-tasks.md` | Tech Lead | **Implement** – pick task & code |
| `04-test.md` | QA | **Verify** – chạy test case |

---

## Cấu trúc repo

```
javathucchien/
├── README.md
├── CHANGELOG.md
├── docs/
│   ├── 00-tong-quan-du-an.md
│   ├── 01-cai-dat-moi-truong.md
│   ├── 02-quy-trinh-git.md
│   ├── 03-quy-trinh-lam-viec-dev.md   ← Quy trình Dev
│   ├── sprints/                        ← ⭐ Sprint Board (Requirements/Design/Tasks/Test)
│   ├── bai-giang/                      ← Video walkthrough (Tech Lead)
│   └── bai-tap/                        ← Bài tập bổ sung (tuỳ chọn)
├── ecommerce/                          ← Source code dự án
└── solutions/                          ← Đáp án (mở sau 3 ngày)
```

---

## Video & Bài giảng

Video = buổi **Tech Lead walkthrough**: giải thích Requirements, Design và demo implement task.

| Ngày | Sprint | Video |
|------|--------|-------|
| Day 01–03 | Sprint 01 | [bai-giang/day-01 → day-03](docs/bai-giang/) |
| Day 04–05 | Sprint 02 | [bai-giang/day-04 → day-05](docs/bai-giang/) |
| ... | ... | ... |

---

## Liên hệ & Hỗ trợ

- **Zalo:** 0368708845
- **Email:** dtekvn.studio@gmail.com
- **Kênh YouTube:** _(cập nhật link playlist)_

---

## License

Tài liệu và source code phục vụ mục đích giáo dục.
