# Sprint 01 – Requirements: Khởi tạo nền tảng

> **Tài liệu PO** — mô tả *cần gì, vì sao, done khi nào*.  
> Chi tiết kỹ thuật (cấu trúc code, Flyway, entity mapping) xem [02-design.md](02-design.md) và [03-api-tasks.md](03-api-tasks.md).

---

## Thông tin Sprint

| Thuộc tính | Giá trị |
|-----------|---------|
| Sprint ID | SPRINT-01 |
| Epic | EPIC-01 — Product Catalog |
| PO | DTEK VN Studio |
| Thời gian | Day 01 – Day 03 |
| Trạng thái | Ready for Development |

---

## 1. Liên kết Product Backlog

Sprint 01 là **enabler** cho Epic **Product Catalog** — nền tảng để Sprint 02 triển khai API sản phẩm.

| Backlog Item | Loại | Sprint | Mô tả ngắn |
|--------------|------|--------|------------|
| EPIC-01 | Epic | 01–04 | Quản lý danh mục & sản phẩm trên ShopVN |
| BL-01 | Enabler | **01** | Hệ thống backend có thể khởi chạy và kết nối cơ sở dữ liệu |
| BL-02 | Feature | **01** | Lưu trữ được thông tin danh mục và sản phẩm |
| BL-03 | Feature | 02 | Admin tạo/sửa/xóa sản phẩm qua API |
| BL-04 | Feature | 04 | Danh mục dạng cây, tìm kiếm sản phẩm |

**Liên kết MVP:** Sprint này phục vụ mục tiêu *"CRUD sản phẩm, danh mục"* trong [tổng quan dự án](../../00-tong-quan-du-an.md#mục-tiêu-cuối-khóa-mvp).

---

## 2. Problem Statement

### Hiện trạng (As-is)

- ShopVN chưa có backend — không lưu được dữ liệu sản phẩm hay danh mục.
- Team chưa có môi trường phát triển thống nhất để bắt đầu xây tính năng bán hàng.

### Mục tiêu (To-be)

Sau Sprint 01, ShopVN có **nền tảng dữ liệu** cho catalog:

- Hệ thống backend **khởi chạy được** trên môi trường dev.
- **Lưu và truy xuất** được danh mục và sản phẩm (chưa cần giao diện hay API công khai).
- Dữ liệu **bền vững** — không mất khi tắt/bật lại môi trường dev.

### Vì sao làm trước Sprint 02?

Không thể bán hàng online nếu chưa có chỗ lưu sản phẩm. Sprint 01 dựng “kho dữ liệu” — Sprint 02 mở cửa cho Admin và khách qua API.

---

## 3. Sprint Goal

> **ShopVN có thể lưu trữ và truy xuất dữ liệu danh mục + sản phẩm trên cơ sở dữ liệu ổn định, sẵn sàng cho Sprint 02 (Product API).**

---

## 4. Personas

| Persona | Mô tả | Liên quan Sprint 01 |
|---------|-------|---------------------|
| **Admin ShopVN** | Quản lý danh mục, thêm/sửa sản phẩm, giá, tồn kho | Cần dữ liệu catalog tồn tại trước khi có màn hình/API |
| **Khách hàng** | Duyệt và mua sản phẩm | Chưa dùng trực tiếp sprint này; hưởng lợi từ sprint sau |
| **Đội phát triển** | Xây và vận hành backend | Cần môi trường dev nhất quán, dữ liệu có cấu trúc rõ ràng |

---

## 5. User Stories

### US-001: Nền tảng hệ thống ShopVN *(Enabler — Must)*

**Là** đội phát triển ShopVN,  
**tôi muốn** có hệ thống backend khởi chạy được trên môi trường dev,  
**để** team bắt đầu triển khai tính năng thương mại điện tử trên một nền tảng chung.

**Acceptance Criteria:**

```gherkin
Given môi trường dev đã được cài đặt theo hướng dẫn khóa học
When dev khởi chạy ứng dụng backend ShopVN
Then hệ thống start thành công và sẵn sàng nhận tích hợp tiếp theo
And không có lỗi nghiêm trọng ngăn cản việc phát triển
```

**Priority:** Must  
**Map backlog:** BL-01

---

### US-002: Kết nối cơ sở dữ liệu *(Enabler — Must)*

**Là** đội phát triển ShopVN,  
**tôi muốn** backend kết nối được với cơ sở dữ liệu trên môi trường dev,  
**để** dữ liệu sản phẩm được lưu bền vững, không chỉ tồn tại trong bộ nhớ tạm.

**Acceptance Criteria:**

```gherkin
Given cơ sở dữ liệu dev đang chạy
When ứng dụng backend khởi động
Then kết nối tới cơ sở dữ liệu thành công
And dev có thể tiếp tục phát triển tính năng lưu trữ dữ liệu
```

**Priority:** Must  
**Map backlog:** BL-01

---

### US-003: Lưu trữ danh mục sản phẩm *(Feature — Must)*

**Là** Admin ShopVN,  
**tôi muốn** hệ thống lưu được thông tin danh mục (kể cả danh mục con),  
**để** tôi có thể tổ chức sản phẩm theo nhóm khi bán hàng.

**Acceptance Criteria:**

```gherkin
Scenario: Tạo danh mục gốc
  Given hệ thống chưa có danh mục "Điện tử"
  When dữ liệu danh mục "Điện tử" được lưu vào hệ thống
  Then danh mục tồn tại với tên "Điện tử"
  And có thể truy xuất lại bằng định danh duy nhất

Scenario: Tạo danh mục con
  Given đã có danh mục cha "Điện tử"
  When lưu danh mục "Điện thoại" thuộc "Điện tử"
  Then "Điện thoại" liên kết đúng với danh mục cha
```

**Priority:** Must  
**Map backlog:** BL-02

---

### US-004: Lưu trữ thông tin sản phẩm *(Feature — Must)*

**Là** Admin ShopVN,  
**tôi muốn** hệ thống lưu được thông tin sản phẩm (tên, giá, tồn kho, mô tả, ảnh, danh mục),  
**để** chuẩn bị đưa sản phẩm lên kênh bán hàng.

**Acceptance Criteria:**

```gherkin
Scenario: Lưu sản phẩm thuộc danh mục
  Given đã có danh mục "Điện thoại"
  When lưu sản phẩm "iPhone 15" giá 25.990.000đ, tồn kho 10, thuộc "Điện thoại"
  Then sản phẩm tồn tại với đầy đủ thông tin đã lưu
  And sản phẩm gắn đúng một danh mục

Scenario: Truy xuất sản phẩm đã lưu
  Given đã lưu sản phẩm "iPhone 15"
  When truy vấn sản phẩm theo định danh
  Then nhận được tên, giá, tồn kho và danh mục tương ứng
```

**Priority:** Must  
**Map backlog:** BL-02

---

### US-005: Truy vấn sản phẩm theo danh mục *(Feature — Should)*

**Là** Admin ShopVN,  
**tôi muốn** xem được danh sách sản phẩm thuộc một danh mục,  
**để** quản lý kho hàng theo từng nhóm hàng.

**Acceptance Criteria:**

```gherkin
Given danh mục "Điện thoại" có 2 sản phẩm
When truy vấn sản phẩm theo danh mục "Điện thoại"
Then trả về đúng 2 sản phẩm thuộc danh mục đó
```

**Priority:** Should  
**Map backlog:** BL-02

---

## 6. Functional Requirements

| ID | Yêu cầu | User Story | Priority |
|----|---------|------------|----------|
| FR-01 | Hệ thống backend ShopVN khởi chạy được trên môi trường dev | US-001 | Must |
| FR-02 | Hệ thống kết nối được cơ sở dữ liệu dev khi khởi động | US-002 | Must |
| FR-03 | Lưu danh mục với tên và thời điểm tạo | US-003 | Must |
| FR-04 | Danh mục có thể có danh mục cha (cây phân cấp) | US-003 | Must |
| FR-05 | Lưu sản phẩm: tên, giá, tồn kho, mô tả, ảnh, danh mục, thời điểm tạo | US-004 | Must |
| FR-06 | Mỗi sản phẩm thuộc đúng một danh mục | US-004 | Must |
| FR-07 | Truy xuất sản phẩm theo định danh sau khi lưu | US-004 | Must |
| FR-08 | Truy vấn danh sách sản phẩm theo danh mục | US-005 | Should |
| FR-09 | Truy vấn danh mục gốc (không có cha) | US-003 | Should |

---

## 7. Non-Functional Requirements

| ID | Yêu cầu | Tiêu chí đo | Priority |
|----|---------|-------------|----------|
| NFR-01 | **Tính bền vững dữ liệu** — dữ liệu không mất khi restart app | Lưu → restart app → truy xuất lại được | Must |
| NFR-02 | **Môi trường dev tái lập được** — dev mới clone repo có thể chạy trong ≤ 30 phút (sau khi cài tool) | Checklist setup + sprint doc | Should |
| NFR-03 | **Schema có kiểm soát phiên bản** — thay đổi cấu trúc DB được theo dõi, không tự ý sửa production | Migration có version, không sửa tay DB | Must |
| NFR-04 | **Độ chính xác tiền tệ** — giá sản phẩm không bị sai số làm tròn | Giá 19.900.000đ lưu và đọc lại khớp | Must |
| NFR-05 | **Mở rộng** — cấu trúc dữ liệu đủ cho Sprint 02–04 (API, tìm kiếm, upload ảnh) | PO/Tech Lead review ERD | Should |

> Chi tiết triển khai NFR-03, NFR-04: xem [02-design.md](02-design.md).

---

## 8. Business Rules (Domain)

Quy tắc nghiệp vụ áp dụng cho **dữ liệu catalog** — enforcement đầy đủ qua API từ Sprint 02; Sprint 01 **định nghĩa và lưu trữ** đúng cấu trúc.

| ID | Quy tắc | Ví dụ hợp lệ | Ví dụ không hợp lệ | Áp dụng từ |
|----|---------|--------------|---------------------|------------|
| BR-01 | Tên danh mục bắt buộc, không rỗng | "Điện thoại" | "" | Sprint 02 (API) |
| BR-02 | Mỗi sản phẩm thuộc đúng **một** danh mục | iPhone → Điện thoại | Sản phẩm không có danh mục | Sprint 01 (cấu trúc) |
| BR-03 | Danh mục hỗ trợ phân cấp cha – con | Điện tử → Điện thoại | — | Sprint 01 |
| BR-04 | Giá sản phẩm ≥ 0 | 0đ (miễn phí) | Giá âm | Sprint 02 (API) |
| BR-05 | Tồn kho ≥ 0; mặc định 0 khi chưa nhập | stock = 0 | stock = -1 | Sprint 02 (API) |
| BR-06 | Tên sản phẩm bắt buộc, không rỗng | "iPhone 15" | "" | Sprint 02 (API) |
| BR-07 | Mỗi bản ghi có thời điểm tạo | createdAt có giá trị | — | Sprint 01 |

---

## 9. MoSCoW — Ưu tiên Sprint

| Must | Should | Could | Won't (Sprint 01) |
|------|--------|-------|-------------------|
| US-001, US-002, US-003, US-004 | US-005 | Seed data mẫu cho demo | REST API công khai |
| FR-01 → FR-07 | FR-08, FR-09 | Script import CSV sản phẩm | Đăng nhập / phân quyền |
| NFR-01, NFR-03, NFR-04 | NFR-02, NFR-05 | — | Giao diện web/mobile |
| | | | Validation lỗi trả về cho người dùng |
| | | | Upload ảnh, tìm kiếm, phân trang |

---

## 10. Out of Scope

Sprint 01 **không** bao gồm:

| Hạng mục | Lý do | Sprint dự kiến |
|----------|-------|----------------|
| API REST cho Admin/Khách | Chưa có nhu cầu giao tiếp qua HTTP | Sprint 02 |
| Validation & thông báo lỗi cho người dùng | Cần API layer trước | Sprint 02 |
| Đăng ký, đăng nhập, JWT | Chưa có user | Sprint 03 |
| Upload ảnh sản phẩm | Cần storage + API | Sprint 04 |
| Tìm kiếm, phân trang | Cần API + query | Sprint 04 |
| Giỏ hàng, đơn hàng | Phụ thuộc catalog + auth | Sprint 05 |
| Thanh toán, email, cache | Tính năng nâng cao | Sprint 06 |

---

## 11. Assumptions & Risks

### Giả định (Assumptions)

| # | Giả định |
|---|----------|
| A-01 | Học viên đã hoàn thành [cài đặt môi trường](../../01-cai-dat-moi-truong.md) trước Day 01 |
| A-02 | Mỗi dev có máy chạy được Docker (RAM ≥ 8GB) |
| A-03 | Sprint 01 chỉ phục vụ môi trường **dev** — chưa deploy production |
| A-04 | Một shop — chưa cần multi-tenant |

### Rủi ro (Risks)

| # | Rủi ro | Mức độ | Giảm thiểu |
|---|--------|--------|------------|
| R-01 | Dev chưa cài Docker → không chạy được DB | Cao | Checklist Day 00; hướng dẫn trong khóa học |
| R-02 | Port DB bị chiếm trên máy dev | Trung bình | Ghi chú trong Design; đổi port nếu cần |
| R-03 | Hiểu nhầm scope — dev code API sớm | Trung bình | Out of Scope rõ; PO review trước khi merge |
| R-04 | Dữ liệu test làm bẩn DB chung | Thấp | Mỗi dev dùng Docker local riêng |

---

## 12. Success Metrics

Sprint 01 được coi là **thành công** khi:

| # | Chỉ số | Cách đo |
|---|--------|---------|
| SM-01 | **100%** User Story Must (US-001 → US-004) đạt Acceptance Criteria | Verify theo [04-test.md](04-test.md) |
| SM-02 | Admin scenario: lưu danh mục + sản phẩm → truy xuất lại đúng | TC-004 pass |
| SM-03 | Dữ liệu **tồn tại sau restart** app và container DB | TC-003 + restart test |
| SM-04 | Không có blocker mở cho Sprint 02 | Tech Lead sign-off |

---

## 13. Definition of Ready

Story trong sprint này **Ready** khi:

- [ ] PO đã chốt scope Must / Won't
- [ ] Học viên đã viết Design và Tech Lead đã review (tham chiếu [02-design.md](02-design.md))
- [ ] Task breakdown có trong [03-api-tasks.md](03-api-tasks.md)
- [ ] Test scenario có trong [04-test.md](04-test.md)
- [ ] Học viên đã đọc Requirements và không còn câu hỏi mở (xem §15)

---

## 14. Dependencies

| Phụ thuộc | Loại | Ghi chú |
|-----------|------|---------|
| Môi trường dev (JDK, Maven, Docker, Git) | External | [01-cai-dat-moi-truong.md](../../01-cai-dat-moi-truong.md) |
| Tầm nhìn sản phẩm ShopVN | Business | [00-tong-quan-du-an.md](../../00-tong-quan-du-an.md) |
| Sprint trước | — | Không có — đây là sprint đầu tiên |

**Phụ thuộc vào Sprint 01 (downstream):**

| Sprint | Cần gì từ Sprint 01 |
|--------|---------------------|
| Sprint 02 — Product API | Dữ liệu sản phẩm/danh mục lưu được, schema ổn định |
| Sprint 04 — Catalog | Cây danh mục, trường ảnh/mô tả đã có trong model |

---

## 15. Open Questions

| # | Câu hỏi | Trạng thái | Trả lời / Hướng xử lý |
|---|---------|------------|------------------------|
| Q-01 | Sprint 01 có cần dữ liệu mẫu (seed) cho demo không? | Đã chốt | **Could** — không bắt buộc; dev tự tạo trong test |
| Q-02 | Admin có thể tạo sản phẩm không thuộc danh mục nào không? | Đã chốt | **Không** — BR-02 |
| Q-03 | Giá có hỗ trợ 0đ (quà tặng) không? | Đã chốt | **Có** — BR-04 (≥ 0) |
| Q-04 | Câu hỏi kỹ thuật (Flyway, Lombok, cấu trúc package)? | Chuyển Tech Lead | Xem [02-design.md](02-design.md) §12 ADR |

---

## 16. Tài liệu liên quan

| Vai trò | Tài liệu |
|---------|----------|
| PO (bạn đang đọc) | **01-requirements.md** |
| Dev (học viên) | Tự viết Design → [03-api-tasks.md](03-api-tasks.md) → code |
| Tech Lead | Review Design + code · tham chiếu [02-design.md](02-design.md) |
| Dev / QA verify | [04-test.md](04-test.md) |
| Quy trình | [03-quy-trinh-lam-viec-dev.md](../../03-quy-trinh-lam-viec-dev.md) |
