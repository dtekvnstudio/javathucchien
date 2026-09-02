# Quy Trình Git Trong Khóa Học

Tài liệu mô tả cách giảng viên push code và cách học viên theo dõi từng buổi học.

---

## 1. Cấu trúc branch

```
main          ← Ổn định, MVP hoàn chỉnh cuối khóa
  └── develop ← Tích hợp code hàng ngày
```

Mỗi ngày học được đánh **tag** trên `develop`:

```
day-01, day-02, day-03, ... day-22
```

---

## 2. Quy trình giảng viên (mỗi ngày)

```bash
# 1. Tạo branch làm việc
git checkout develop
git pull origin develop
git checkout -b day-15-tao-order

# 2. Code + docs + bài tập
# ...

# 3. Commit có cấu trúc
git add .
git commit -m "[SPRINT-05-TASK-004] Tạo Order từ Cart

- Thêm OrderService, OrderController
- Flyway V5: bảng orders, order_items
- Sprint docs: docs/sprints/sprint-05-cart-order/
"

# 4. Merge vào develop
git checkout develop
git merge day-15-tao-order

# 5. Tag và push
git tag day-15
git push origin develop --tags
```

---

## 3. Quy trình học viên

### Lần đầu clone

```bash
git clone <URL_REPO>
cd javathucchien
```

### Xem code đúng buổi học

```bash
git fetch --tags
git checkout day-15
```

### Làm bài tập trên branch riêng

```bash
git checkout day-15
git checkout -b student/ten-ban/SPRINT-05-TASK-004
# ... implement task ...
git add .
git commit -m "[SPRINT-05-TASK-004] Implement POST /api/orders"
git push origin student/ten-ban/SPRINT-05-TASK-004
```

Sau đó tạo **Pull Request** trên GitHub.

### Cập nhật code buổi mới

```bash
git fetch --tags
git checkout day-16
```

---

## 4. Quy ước đặt tên

| Loại | Format | Ví dụ |
|------|--------|-------|
| Tag ngày học | `day-XX` | `day-07` |
| Branch giảng viên | `day-XX-mo-ta-ngan` | `day-07-jwt-auth` |
| Branch học viên | `student/ten-ban/SPRINT-XX-TASK-YYY` | `student/minh-nguyen/SPRINT-03-TASK-002` |

---

## 5. Quy ước commit message

```
[SPRINT-XX-TASK-YYY] Tiêu đề ngắn gọn

- Bullet mô tả thay đổi code
- Bullet cập nhật sprint docs nếu có
```

**Ví dụ:**

```
[SPRINT-03-TASK-002] JWT Authentication

- Thêm JwtService, JwtAuthFilter
- API POST /api/auth/login, /api/auth/register
- Cập nhật docs/sprints/sprint-03-auth/
```

---

## 6. Tag vs Branch

| | Tag | Branch |
|---|-----|--------|
| Mục đích | Đánh dấu snapshot cố định | Phát triển tiếp |
| Học viên | Checkout để xem code buổi đó | Tạo branch làm bài tập |
| Thay đổi | Không (immutable) | Có thể commit thêm |

**Khuyến nghị:** Học viên luôn checkout **tag** `day-XX` làm điểm xuất phát, rồi tạo branch riêng để làm bài.

---

## 7. Xem diff giữa hai buổi

```bash
# So sánh Day 14 và Day 15
git diff day-14..day-15

# Chỉ xem file thay đổi
git diff day-14..day-15 --stat
```

Hữu ích khi học viên muốn tự code theo video rồi so với đáp án.

---

## 8. Đáp án bài tập

Đáp án được publish dưới dạng tag riêng (sau 3–5 ngày):

```bash
git checkout solution-day-15
```

Hoặc xem tại thư mục `solutions/day-15/` (repo private hoặc mở sau).

---

## 9. FAQ

**H: Checkout tag bị detached HEAD?**  
Đ: Bình thường. Tạo branch mới từ tag: `git checkout -b student/ten-ban/day-15 day-15`

**H: Muốn quay lại code buổi trước?**  
Đ: `git checkout day-14`

**H: Lỡ commit nhầm trên develop?**  
Đ: Liên hệ giảng viên. Học viên không push trực tiếp lên `develop`.
