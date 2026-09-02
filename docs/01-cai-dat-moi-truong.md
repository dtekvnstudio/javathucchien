# Hướng Dẫn Cài Đặt Môi Trường

Tài liệu này hướng dẫn học viên chuẩn bị môi trường trước khi bắt đầu Day 01.

> **Phạm vi:** Chỉ cài tool và verify (`java -version`, `docker --version`...). **Chưa** chạy `docker compose up` hay `mvn spring-boot:run` — chưa có code cho đến Tập 03 / Day 01.
>
> **Video Tập 02:** Tạo bằng Google NotebookLM + clip demo **JDK + Maven**. IntelliJ, Docker, Git, Bruno — tự cài theo tài liệu này. Git workflow xem trên YouTube.
>
> **Repo:** https://github.com/dtekvnstudio/javathucchien

> **Thứ tự khuyến nghị:** Cài **AI trước** → dùng AI hướng dẫn cài JDK, Maven, Docker, Git... phía sau.

---

## 1. Yêu cầu phần cứng tối thiểu

| Thành phần | Tối thiểu |
|-----------|-----------|
| RAM | 8 GB (khuyến nghị 16 GB) |
| Ổ cứng trống | 10 GB |
| OS | Windows 10+, macOS 12+, Ubuntu 20.04+ |

---

## 2. Cài AI trước (Bước 1 — làm đầu tiên)

Khóa học **không cấm** dùng AI. Ngược lại — **cài AI trước**, rồi dùng AI làm trợ lý setup các tool còn lại.

> **Nguyên tắc:** AI giúp bạn gõ nhanh và setup nhanh hơn; **bạn** vẫn phải hiểu từng bước để debug khi đi làm.

### Chọn 1 trong các tool sau

| Tool | Loại | Link | Ghi chú |
|------|------|------|---------|
| **Cursor** | AI IDE | https://cursor.com/ | **Khuyến nghị chính** — hiểu context repo, hỗ trợ cài đặt + code |
| **Claude** | Chat AI | https://claude.ai/ | Tốt cho hướng dẫn setup từng bước, giải thích dài |
| **ChatGPT** | Chat AI | https://chatgpt.com/ | Phổ biến, Codex mạnh về lệnh terminal |
| **GitHub Copilot** | Plugin IDE | Trong IntelliJ / VS Code | Dùng sau khi đã có IDE — không thay chat AI lúc setup |

Bạn **không cần cài hết**. Tối thiểu: **1 AI tool** (Cursor hoặc Claude/ChatGPT).

### Cài Cursor (khuyến nghị)

1. Tải tại [cursor.com](https://cursor.com/) — có free tier
2. Đăng ký account, mở app
3. Sẵn sàng chat — chưa cần mở repo

### Hoặc dùng Claude / ChatGPT trên browser

1. Tạo account miễn phí
2. Mở tab chat — dùng ngay, không cần cài thêm

### Prompt tổng — copy ngay sau khi cài AI

Dán prompt này vào AI, **thay OS của bạn** vào dòng `[OS]`:

```
Tôi là fresher backend Java, đang setup môi trường dev cho khóa học ShopVN (Spring Boot 3, Java 21).
OS của tôi: [macOS / Windows 11 / Ubuntu 22.04].

Hãy hướng dẫn tôi cài từng bước theo thứ tự:
1. JDK 21 LTS + JAVA_HOME
2. Apache Maven 3.9+
3. IntelliJ IDEA Community
4. Docker Desktop
5. Git (config user.name, user.email)
6. Bruno

Với mỗi bước:
- Lệnh cài trên OS của tôi
- Lệnh kiểm tra (verify)
- 1–2 lỗi thường gặp và cách fix

Giải thích ngắn tại sao cần tool đó. Tiếng Việt.
Làm từng bước một — xong bước 1 tôi báo lại rồi mới sang bước 2.
```

> Làm **từng bước một**: cài xong JDK → chạy `java -version` → báo AI kết quả → mới sang Maven. Đừng cài ồ ạt rồi không biết lỗi ở đâu.

### Quy tắc dùng AI trong khóa học

| ✅ Nên | ❌ Không nên |
|--------|-------------|
| Hỏi AI **hướng dẫn cài** từng tool trên OS của bạn | Bảo AI "cài hết cho tôi" rồi không verify |
| Dán **output lỗi** + hỏi "fix thế nào?" | Copy code bài tập từ AI, không đọc, không test |
| Dùng AI đọc `01-requirements.md` rồi **tự implement** | Skip video, chỉ nhờ AI viết xong project |
| So sánh code AI với tag `day-XX` sau khi tự làm | Paste code AI vào phỏng vấn mà không hiểu |

**Bảo mật:** Không dán API key, password, JWT secret vào chat AI.

> Video hướng dẫn chi tiết: Java Thực Chiến **Tập 02** — Setup môi trường.

---

## 3. JDK 21

> **Cách làm:** Mở AI đã cài ở Bước 1 → hỏi theo prompt bên dưới → làm theo → verify.

### Prompt hỏi AI

```
Hướng dẫn tôi cài JDK 21 LTS trên [macOS / Windows / Ubuntu].
Bao gồm: lệnh cài, thiết lập JAVA_HOME, lệnh kiểm tra java -version và echo JAVA_HOME.
Nếu máy tôi đang có Java 8/17 thì xử lý thế nào?
```

### Tham chiếu nhanh (macOS)

```bash
brew install openjdk@21
java -version
# openjdk version "21.x.x" ...
```

### Tham chiếu nhanh (Windows)

Tải JDK 21 LTS từ [Adoptium](https://adoptium.net/) — tick **"Set JAVA_HOME"** khi cài.

### Kiểm tra — bắt buộc

```bash
java -version
echo $JAVA_HOME    # macOS/Linux
```

Kết quả phải là **Java 21**. Nếu sai — dán output vào AI, hỏi tiếp.

---

## 4. Apache Maven

### Prompt hỏi AI

```
Tôi đã cài JDK 21, java -version OK.
Hướng dẫn cài Apache Maven 3.9+ trên [OS của tôi].
Lệnh kiểm tra mvn -version — phải thấy Java 21.
```

### Tham chiếu nhanh (macOS)

```bash
brew install maven
mvn -version
# Apache Maven 3.9.x, Java version: 21
```

---

## 5. Cài đặt IDE

### Prompt hỏi AI

```
Hướng dẫn cài IntelliJ IDEA Community trên [OS của tôi].
Cần cấu hình JDK 21 trong Project Structure.
Plugin nên cài: Spring Boot, Lombok (bật annotation processing).
```

| IDE | Link |
|-----|------|
| IntelliJ IDEA Community | https://www.jetbrains.com/idea/download/ |
| Cursor (nếu chọn làm IDE chính) | https://cursor.com/ |

**Khuyến nghị:** IntelliJ IDEA Community cho video bài giảng. Cursor đã cài ở Bước 1 dùng song song khi debug.

### Plugin gợi ý (IntelliJ)

- Spring Boot
- Lombok (bật annotation processing: Settings → Build → Compiler → Annotation Processors)

---

## 6. Docker Desktop

### Prompt hỏi AI

```
Hướng dẫn cài Docker Desktop trên [OS của tôi].
Lệnh kiểm tra docker --version và docker compose version.
Lưu ý WSL2 nếu Windows. RAM 8GB thì allocate bao nhiêu cho Docker?
```

Tải: https://www.docker.com/products/docker-desktop/

### Kiểm tra

```bash
docker --version
docker compose version
```

Docker dùng để chạy PostgreSQL, Redis — không cài DB trực tiếp lên máy.

---

## 7. Git

### Prompt hỏi AI

```
Hướng dẫn cài Git trên [OS của tôi].
Cấu hình user.name và user.email cho GitHub.
Lệnh kiểm tra git --version.
```

### Kiểm tra

```bash
git --version
git config --global user.name "Ten Ban"
git config --global user.email "email@example.com"
```

---

## 8. Công cụ test API (Bruno)

**Khuyến nghị:** [Bruno](https://www.usebruno.com/) — open-source, collection lưu file trong repo Git, không bắt buộc account.

### Prompt hỏi AI

```
Hướng dẫn tải và cài Bruno API client trên [OS của tôi].
Link: https://www.usebruno.com/downloads
Tạo collection ShopVN. Test request GET https://httpbin.org/get để verify.
```

| Công cụ | Link | Ghi chú |
|---------|------|---------|
| **Bruno** (khuyến nghị) | https://www.usebruno.com/downloads | Collection commit được vào Git |
| Insomnia | https://insomnia.rest/download | Thay thế nếu không dùng Bruno |

### Cài nhanh

1. Tải Bruno tại [usebruno.com/downloads](https://www.usebruno.com/downloads/)
2. **Create Collection** → **ShopVN**
3. **New Request** → GET `https://httpbin.org/get` → Send → thấy JSON

Collection API từ repo sẽ có sau Day 05 (thư mục `bruno/` — mở folder trong Bruno).

---

## 9. Clone repository

Repo public: **https://github.com/dtekvnstudio/javathucchien**

Sau khi Git đã cài:

```bash
git clone https://github.com/dtekvnstudio/javathucchien.git
cd javathucchien
git fetch --tags
git checkout day-01
```

### Mở repo trong Cursor (nếu dùng)

**File → Open Folder** → chọn `javathucchien`

Thử hỏi AI:

```
Tôi vừa clone repo javathucchien, checkout day-01.
Giải thích cấu trúc thư mục docs/ và ecommerce/ cho fresher.
```

---

## 10. Verify Docker (giai đoạn setup — chưa chạy DB)

Ở bước setup môi trường, bạn **chỉ cần** cài Docker Desktop và verify:

```bash
docker --version
docker compose version
```

Icon whale trong Docker Desktop phải ở trạng thái **Running**.

> **Chưa chạy** `docker compose up` ở giai đoạn này — folder `ecommerce/` chưa có `docker-compose.yml` cho đến khi bạn code **Day 01 / Tập 03**.

---

## 11. Bước tiếp theo — Day 01 (sau khi có code)

Các lệnh dưới đây **không thuộc** phần setup môi trường. Làm sau khi xem **Tập 03** và đã có skeleton project Spring Boot.

### Chạy PostgreSQL (từ Day 01)

```bash
cd javathucchien/ecommerce
docker compose up -d
docker ps
# Container postgres port 5432
```

### Chạy ứng dụng Spring Boot (từ Day 01)

```bash
cd javathucchien/ecommerce
mvn spring-boot:run
```

Truy cập: http://localhost:8080

Swagger UI (từ Day 22): http://localhost:8080/swagger-ui.html

> Nếu chạy `mvn spring-boot:run` ngay sau setup mà chưa có code → **bình thường là lỗi**. Quay lại đây sau Tập 03.

---

## 12. Prompt mẫu — dùng trong khóa học (sau khi setup xong)

**Đọc requirements:**

```
Tôi đang học Java backend, dự án ShopVN (Spring Boot 3).
Đọc file docs/sprints/sprint-01-khoi-tao/01-requirements.md.
Tóm tắt 3 việc tôi cần làm hôm nay, theo thứ tự. Tiếng Việt.
```

**Debug lỗi:**

```
Spring Boot project, Java 21, PostgreSQL Docker port 5432.
Lỗi: [dán stack trace]
Gợi ý 3 bước kiểm tra, không viết hộ code.
```

**Review code trước khi commit:**

```
Review đoạn code sau theo góc Senior Java.
Chỉ ra bug tiềm ẩn, vi phạm layered architecture.
Không rewrite — chỉ list vấn đề.
[dán code]
```

---

## 13. Xử lý lỗi thường gặp

| Lỗi | Nguyên nhân | Cách xử lý |
|-----|-------------|------------|
| `JAVA_HOME not set` | Chưa export biến môi trường | Dán lỗi vào AI + OS của bạn |
| `java -version` ra 8/17 | PATH ưu tiên JDK cũ | Hỏi AI fix JAVA_HOME trên OS bạn |
| Maven báo Java 8 | `JAVA_HOME` sai | `mvn -version` phải khớp Java 21 |
| `Port 8080 already in use` | App khác chiếm port | Đổi `server.port` — *từ Day 01 khi đã chạy app* |
| `Connection refused: 5432` | PostgreSQL chưa chạy | `docker compose up -d` — *từ Day 01 khi đã có `docker-compose.yml`* |
| Docker "daemon not running" | Docker Desktop tắt | Mở Docker Desktop |
| IntelliJ code đỏ hết | SDK sai | Project Structure → JDK 21 |

---

## 14. Checklist trước Day 01

Làm theo thứ tự:

- [ ] **Bước 1:** Cài Cursor hoặc Claude/ChatGPT
- [ ] Dùng **prompt tổng** (mục 2) để AI hướng dẫn cài từng tool
- [ ] `java -version` → **21.x**
- [ ] `mvn -version` → Maven 3.8+ và Java 21
- [ ] IntelliJ (hoặc Cursor) mở được project Maven
- [ ] `docker --version` OK, Docker Desktop **running**
- [ ] `git --version` OK, đã config user.name / user.email
- [ ] Bruno cài và gửi được request test (GET httpbin.org/get)
- [ ] Clone `javathucchien` + `git checkout day-01` OK
- [ ] Đã thử hỏi AI 1 câu về cấu trúc repo hoặc sprint doc

> **Chưa cần** ở giai đoạn setup: `docker compose up`, `mvn spring-boot:run` — làm sau **Tập 03 / Day 01** khi đã có code.
