# Quản Lý Nhà Sách

## Giới Thiệu

**Quản Lý Nhà Sách** là một ứng dụng desktop được phát triển bằng Java (NetBeans) với giao diện Swing và cơ sở dữ liệu quản lý bằng phpMyAdmin (MySQL). Ứng dụng hỗ trợ quản lý sách, khách hàng, nhân viên, và doanh thu.

## Chức Năng Chính

1. **Quản Lý Sách**:

   - Thêm, sửa, xoá thông tin sách.
   - Tìm kiếm sách theo tên sách.
   - Bán sách
   - Nhập sách
     
2. **Quản Lý Đầu Sách**:
   - Thêm, sửa, xoá thông tin đầu sách.
     
3. **Quản Lý Khách Hàng**: 
   - Thêm, sửa, xoá thông tin khách hàng.
   - Thay đổi thông tin tài khoản.

4. **Quản Lý Nhân Viên**:
   - Quản lý thông tin nhân viên.

5. **Thống Kê Doanh Thu**:
   - Báo cáo công nợ theo tháng, năm.
   - Báo cáo tồn kho theo tháng, năm.
     
6. **Thay đổi quy định**:
   - Thay đổi thể loại và các tham số theo quy định.
     
7. **Phân quyền**:
   - Admin: Quản lý tất cả
   - Nhân viên: Chỉ quản lý sách, đầu sách, khách hàng và bán hàng
## Yêu Cầu Hệ Thống

1. **Phần Mềm**:

   - Java Runtime Environment (JRE) hoặc Java Development Kit (JDK).
   - NetBeans IDE (để build và chạy dự án).
   - phpMyAdmin và MySQL Server.

2. **Cứng Cụ**:

   - MySQL Connector/J (để kết nối Java Swing với MySQL).

## Hướng Dẫn Cài Đặt

1. **Clone repository**:

   ```bash
   git clone https://github.com/DatWguyn2811/QLNhaSach.git
   ```

2. **Cài đặt cơ sở dữ liệu**:

   - Mở phpMyAdmin.
   - Import file SQL trong file quanlynhasach.sql vào phpMyAdmin.

3. **Cài đặt dự án**:

   - Mở NetBeans IDE.
   - Load project từ thư mục repository.

4. **Chạy ứng dụng**:

   - Nhấn "Run" trong NetBeans IDE.
   - Truy cập giao diện desktop để sử dụng.
   - Ở màn hình đăng nhập, nhập tên tài khoản là admin nếu muốn vào giao diện admin. Nếu muốn vào giao diện nhân viên thì tên tài khoản là nhanvien.
   - Mật khẩu là 123456.

## Cáu Trúc Thư Mục

  - `src/main/java`: Mã nguồn chính của dự án.
  - `src/main/resources`: Tài nguyên (file cấu hình, giao diện).
  - `src/test/java`: Mã nguồn kiểm thử.
  - `pom.xml`: File cấu hình Maven.
  - `database/`: File SQL kết nối cơ sở dữ liệu.



