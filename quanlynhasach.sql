-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 29, 2024 lúc 01:51 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlynhasach`
--

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_CapNhatCongNoThangMoi` ()   BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    SET Thang = MONTH(CURRENT_DATE);
    SET Nam = YEAR(CURRENT_DATE);

    INSERT INTO BAOCAOCONGNO (MaKhachHang, NoDau, NoCuoi, PhatSinh, Thang, Nam)
    SELECT kh.MaKhachHang, kh.TongNo, kh.TongNo, 0, Thang, Nam
    FROM KHACHHANG kh
    WHERE kh.MaKhachHang != 1 AND NOT EXISTS (
        SELECT 1
        FROM BAOCAOCONGNO
        WHERE MaKhachHang = kh.MaKhachHang AND Thang = Thang AND Nam = Nam
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_CapNhatTonKhoThangMoi` ()   BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    SET Thang = MONTH(CURRENT_DATE);
    SET Nam = YEAR(CURRENT_DATE);

    INSERT INTO BAOCAOTON (MaSach, TonDau, TonCuoi, PhatSinh, Thang, Nam)
    SELECT s.MaSach, 0, s.SoLuongNhap, s.SoLuongNhap, Thang, Nam
    FROM CT_PHIEUNHAPSACH s
    WHERE NOT EXISTS (
        SELECT 1
        FROM BAOCAOTON
        WHERE MaSach = s.MaSach AND Thang = Thang AND Nam = Nam
    );
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baocaocongno`
--

CREATE TABLE `baocaocongno` (
  `MaBaoCaoCongNo` int(11) NOT NULL,
  `MaKhachHang` int(11) NOT NULL,
  `NoDau` int(11) DEFAULT NULL,
  `NoCuoi` int(11) DEFAULT NULL,
  `PhatSinh` int(11) DEFAULT NULL,
  `Thang` int(11) DEFAULT NULL,
  `Nam` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `baocaocongno`
--

INSERT INTO `baocaocongno` (`MaBaoCaoCongNo`, `MaKhachHang`, `NoDau`, `NoCuoi`, `PhatSinh`, `Thang`, `Nam`) VALUES
(8, 2, 250000, 750000, 2250000, 12, 2024),
(9, 3, 450000, 1350000, 1125000, 12, 2024),
(10, 4, 350000, 1050000, 3375000, 12, 2024),
(11, 1, 0, 0, 2250000, 11, 2024);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baocaoton`
--

CREATE TABLE `baocaoton` (
  `MaBaoCaoTonKho` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `TonDau` int(11) DEFAULT NULL,
  `TonCuoi` int(11) DEFAULT NULL,
  `PhatSinh` int(11) DEFAULT NULL,
  `Thang` int(11) DEFAULT NULL,
  `Nam` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `baocaoton`
--

INSERT INTO `baocaoton` (`MaBaoCaoTonKho`, `MaSach`, `TonDau`, `TonCuoi`, `PhatSinh`, `Thang`, `Nam`) VALUES
(8, 1, 0, 100, 420, 12, 2024),
(9, 2, 0, 120, 430, 12, 2024);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cthd`
--

CREATE TABLE `cthd` (
  `MaHoaDon` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `ThanhTien` int(11) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cthd`
--

INSERT INTO `cthd` (`MaHoaDon`, `MaSach`, `ThanhTien`, `SoLuong`) VALUES
(1, 1, 1500000, 30),
(1, 2, 2000000, 20),
(2, 1, 1000000, 20),
(2, 2, 3000000, 30),
(3, 1, 1500000, 30),
(3, 2, 5000000, 50),
(4, 2, 2000000, 20),
(5, 1, 1500000, 30),
(6, 1, 1500000, 30),
(6, 2, 2000000, 20),
(7, 1, 1750000, 35),
(7, 2, 3000000, 30),
(8, 1, 1750000, 35),
(8, 2, 5000000, 50),
(9, 2, 1000000, 10),
(10, 1, 2000000, 40),
(11, 1, 1250000, 25),
(11, 2, 2500000, 25),
(12, 1, 1250000, 25),
(12, 2, 2500000, 25),
(13, 1, 1250000, 25),
(13, 2, 2500000, 25),
(14, 2, 2500000, 25),
(15, 1, 1250000, 25);

--
-- Bẫy `cthd`
--
DELIMITER $$
CREATE TRIGGER `trg_CapNhatBaoCaoTonKhiBanSach` AFTER INSERT ON `cthd` FOR EACH ROW BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    DECLARE TonDau INT DEFAULT 0;
    SET Thang = MONTH(CURRENT_DATE);
    SET Nam = YEAR(CURRENT_DATE);

    -- Lấy số lượng tồn cuối của tháng trước làm tồn đầu của tháng hiện tại
    SELECT TonCuoi INTO TonDau
    FROM BAOCAOTON
    WHERE MaSach = NEW.MaSach
    AND Thang = Thang - 1
    AND Nam = Nam;

    -- Cập nhật bảng BAOCAOTON cho tháng hiện tại
    IF NOT EXISTS (SELECT 1 FROM BAOCAOTON WHERE MaSach = NEW.MaSach AND Thang = Thang AND Nam = Nam) THEN
        INSERT INTO BAOCAOTON (MaSach, TonDau, TonCuoi, PhatSinh, Thang, Nam)
        VALUES (NEW.MaSach, TonDau, TonDau - NEW.SoLuong, -NEW.SoLuong, Thang, Nam);
    ELSE
        UPDATE BAOCAOTON
        SET TonCuoi = TonCuoi - NEW.SoLuong
        WHERE MaSach = NEW.MaSach AND Thang = Thang AND Nam = Nam;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_UpdateCTHDValues` BEFORE INSERT ON `cthd` FOR EACH ROW BEGIN
    -- Tính toán ThanhTien dựa trên SoLuong và DonGia từ bảng SACH
    SET NEW.ThanhTien = NEW.SoLuong * (SELECT DonGia FROM SACH WHERE MaSach = NEW.MaSach);

    -- Cập nhật số lượng sách trong bảng SACH
    UPDATE SACH
    SET SoLuong = SoLuong - NEW.SoLuong
    WHERE MaSach = NEW.MaSach;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_phieunhapsach`
--

CREATE TABLE `ct_phieunhapsach` (
  `MaPhieuNhapSach` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `SoLuongNhap` int(11) DEFAULT NULL,
  `DonGiaNhap` int(11) DEFAULT NULL,
  `ThanhTien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_phieunhapsach`
--

INSERT INTO `ct_phieunhapsach` (`MaPhieuNhapSach`, `MaSach`, `SoLuongNhap`, `DonGiaNhap`, `ThanhTien`) VALUES
(1, 1, 200, 50000, 10000000),
(2, 2, 150, 100000, 15000000),
(3, 1, 50, 50000, 2500000),
(4, 2, 100, 100000, 10000000),
(6, 1, 200, 100000, 20000000),
(6, 2, 200, 110000, 22000000);

--
-- Bẫy `ct_phieunhapsach`
--
DELIMITER $$
CREATE TRIGGER `trg_CapNhatBaoCaoTonKhiNhapSach` AFTER INSERT ON `ct_phieunhapsach` FOR EACH ROW BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    DECLARE TonDau INT DEFAULT 0;
    SET Thang = MONTH(CURRENT_DATE);
    SET Nam = YEAR(CURRENT_DATE);

    -- Lấy số lượng tồn cuối của tháng trước làm tồn đầu của tháng hiện tại
    SELECT TonCuoi INTO TonDau
    FROM BAOCAOTON
    WHERE MaSach = NEW.MaSach
    AND Thang = Thang - 1
    AND Nam = Nam;

    -- Cập nhật bảng BAOCAOTON cho tháng hiện tại
    IF NOT EXISTS (SELECT 1 FROM BAOCAOTON WHERE MaSach = NEW.MaSach AND Thang = Thang AND Nam = Nam) THEN
        INSERT INTO BAOCAOTON (MaSach, TonDau, TonCuoi, PhatSinh, Thang, Nam)
        VALUES (NEW.MaSach, TonDau, TonDau + NEW.SoLuongNhap, NEW.SoLuongNhap, Thang, Nam);
    ELSE
        UPDATE BAOCAOTON
        SET TonCuoi = TonCuoi + NEW.SoLuongNhap,
            PhatSinh = PhatSinh + NEW.SoLuongNhap
        WHERE MaSach = NEW.MaSach AND Thang = Thang AND Nam = Nam;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_UpdateCTPhieuNhapSachValues` BEFORE INSERT ON `ct_phieunhapsach` FOR EACH ROW BEGIN
    -- Tính toán ThanhTien dựa trên SoLuongNhap và DonGiaNhap
    SET NEW.ThanhTien = NEW.SoLuongNhap * NEW.DonGiaNhap;

    -- Cập nhật số lượng sách trong bảng SACH
    UPDATE SACH
    SET SoLuong = SoLuong + NEW.SoLuongNhap
    WHERE MaSach = NEW.MaSach;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dausach`
--

CREATE TABLE `dausach` (
  `MaDauSach` int(11) NOT NULL,
  `TenDauSach` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MaTheLoai` int(11) NOT NULL,
  `MaTacGia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dausach`
--

INSERT INTO `dausach` (`MaDauSach`, `TenDauSach`, `MaTheLoai`, `MaTacGia`) VALUES
(1, 'Chí Phèo', 1, 1),
(2, 'Số Đỏ', 1, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHoaDon` int(11) NOT NULL,
  `MaKhachHang` int(11) NOT NULL,
  `NgayLap` date DEFAULT NULL,
  `TongTien` int(11) DEFAULT NULL,
  `SoTienThu` int(11) DEFAULT NULL,
  `ConLai` int(11) DEFAULT NULL,
  `MaNhanVien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHoaDon`, `MaKhachHang`, `NgayLap`, `TongTien`, `SoTienThu`, `ConLai`, `MaNhanVien`) VALUES
(1, 1, '2024-11-10', 3500000, 2450000, 1050000, 2),
(2, 2, '2024-11-12', 4000000, 2800000, 1200000, 2),
(3, 3, '2024-11-14', 6500000, 4550000, 1950000, 2),
(4, 4, '2024-11-16', 2000000, 1400000, 600000, 2),
(5, 1, '2024-11-17', 1500000, 1050000, 450000, 2),
(6, 2, '2024-11-18', 3500000, 2450000, 1050000, 2),
(7, 3, '2024-11-20', 4750000, 3325000, 1425000, 2),
(8, 4, '2024-11-22', 6750000, 4725000, 2025000, 2),
(9, 2, '2024-11-24', 1000000, 1000000, 0, 2),
(10, 4, '2024-11-25', 2000000, 1400000, 600000, 1),
(11, 3, '2024-12-05', 3750000, 3750000, 0, 1),
(12, 4, '2024-12-07', 3750000, 2625000, 1125000, 1),
(13, 1, '2024-12-09', 3750000, 2625000, 1125000, 1),
(14, 2, '2024-12-11', 2500000, 1750000, 750000, 1),
(15, 3, '2024-12-12', 1250000, 875000, 375000, 1);

--
-- Bẫy `hoadon`
--
DELIMITER $$
CREATE TRIGGER `trg_update_baocaocongno_hoadon` AFTER INSERT ON `hoadon` FOR EACH ROW BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    DECLARE NoDau INT DEFAULT 0;
    DECLARE PhatSinh INT DEFAULT 0;

    SET Thang = MONTH(NEW.NgayLap);
    SET Nam = YEAR(NEW.NgayLap);

    -- Lấy số nợ đầu kỳ
    SELECT NoCuoi INTO NoDau
    FROM BAOCAOCONGNO
    WHERE MaKhachHang = NEW.MaKhachHang
    AND Thang = Thang - 1
    AND Nam = Nam;

    -- Tính phát sinh
    SET PhatSinh = NEW.ConLai;

    -- Cập nhật bảng BAOCAOCONGNO
    IF NOT EXISTS (SELECT 1 FROM BAOCAOCONGNO WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam) THEN
        INSERT INTO BAOCAOCONGNO (MaKhachHang, NoDau, NoCuoi, PhatSinh, Thang, Nam)
        VALUES (NEW.MaKhachHang, NoDau, NoDau + PhatSinh, PhatSinh, Thang, Nam);
    ELSE
        UPDATE BAOCAOCONGNO
        SET NoCuoi = NoCuoi + PhatSinh,
            PhatSinh = PhatSinh + NEW.ConLai
        WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam;
    END IF;

    -- Cập nhật tổng nợ của khách hàng
    UPDATE KHACHHANG
    SET TongNo = TongNo + NEW.ConLai
    WHERE MaKhachHang = NEW.MaKhachHang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKhachHang` int(11) NOT NULL,
  `TenKhachHang` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SoDienThoai` varchar(10) DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TongNo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKhachHang`, `TenKhachHang`, `DiaChi`, `SoDienThoai`, `Email`, `TongNo`) VALUES
(1, 'Nguyễn Văn A', 'HCM', '0123456789', 'nguyenvana@gmail.com', 0),
(2, 'Lê Thị B', 'Hà Nội', '0987651234', 'lethib@gmail.com', 500000),
(3, 'Trần Nhân C', 'HCM', '0234567890', 'trannhanc@gmail.com', 900000),
(4, 'Đỗ Kim D', 'HCM', '0987654321', 'dokimd@gmail.com', 700000);

--
-- Bẫy `khachhang`
--
DELIMITER $$
CREATE TRIGGER `trg_CapNhatCongNoKhiSuaKhachHang` AFTER UPDATE ON `khachhang` FOR EACH ROW BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    DECLARE ThayDoiNo INT;

    SET Thang = MONTH(CURRENT_DATE);
    SET Nam = YEAR(CURRENT_DATE);
    SET ThayDoiNo = NEW.TongNo - OLD.TongNo;

    IF NEW.MaKhachHang != 1 THEN
        IF NOT EXISTS (SELECT 1 FROM BAOCAOCONGNO WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam) THEN
            INSERT INTO BAOCAOCONGNO (MaKhachHang, NoDau, NoCuoi, PhatSinh, Thang, Nam)
            VALUES (NEW.MaKhachHang, OLD.TongNo, NEW.TongNo, CASE WHEN ThayDoiNo > 0 THEN ThayDoiNo ELSE 0 END, Thang, Nam);
        ELSE
            IF ThayDoiNo > 0 THEN
                UPDATE BAOCAOCONGNO
                SET NoCuoi = NoCuoi + ThayDoiNo,
                    PhatSinh = PhatSinh + ThayDoiNo
                WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam;
            ELSE
                UPDATE BAOCAOCONGNO
                SET NoCuoi = NoCuoi + ThayDoiNo
                WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam;
            END IF;
        END IF;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_ThemCongNoKhiTaoKhachHangMoi` AFTER INSERT ON `khachhang` FOR EACH ROW BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    SET Thang = MONTH(CURRENT_DATE);
    SET Nam = YEAR(CURRENT_DATE);

    IF NEW.MaKhachHang != 1 THEN
        INSERT INTO BAOCAOCONGNO (MaKhachHang, NoDau, NoCuoi, PhatSinh, Thang, Nam)
        VALUES (NEW.MaKhachHang, NEW.TongNo, NEW.TongNo, 0, Thang, Nam);
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` int(11) NOT NULL,
  `TenNhanVien` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GioiTinh` enum('Nam','Nữ') DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `NgayVaoLam` date DEFAULT NULL,
  `SoDienThoai` varchar(10) DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Luong` int(11) DEFAULT 0,
  `TrangThai` bit(1) DEFAULT b'1',
  `TenDangNhap` varchar(100) NOT NULL,
  `MatKhau` varchar(100) NOT NULL,
  `VaiTro` enum('Nhân viên','Quản lí') NOT NULL DEFAULT 'Nhân viên',
  `MaQuyen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `TenNhanVien`, `GioiTinh`, `NgaySinh`, `NgayVaoLam`, `SoDienThoai`, `Email`, `Luong`, `TrangThai`, `TenDangNhap`, `MatKhau`, `VaiTro`, `MaQuyen`) VALUES
(1, 'Nguyễn Tiến Đạt', 'Nam', '2004-02-16', '2024-01-01', '0987654123', '22520227@gm.uit.edu.vn', 3000000, b'1', 'admin', '$2a$12$YKSAx/ZyKjvubNjra95oOer7NdpQBdgZQ7hCD8elgUsE2.2I.QWau', 'Quản lí', 1),
(2, 'Trần Nguyễn Bảo Hoàng', 'Nữ', '1990-01-02', '2020-01-20', '0354198583', '22520478@gm.uit.edu.vn', 1500000, b'1', 'nhanvien', '$2a$12$YKSAx/ZyKjvubNjra95oOer7NdpQBdgZQ7hCD8elgUsE2.2I.QWau', 'Nhân viên', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nxb`
--

CREATE TABLE `nxb` (
  `MaNhaXuatBan` int(11) NOT NULL,
  `TenNhaXuatBan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nxb`
--

INSERT INTO `nxb` (`MaNhaXuatBan`, `TenNhaXuatBan`) VALUES
(1, 'NXB Trẻ'),
(2, 'NXB Văn học'),
(3, 'NXB Kim Đồng'),
(4, 'NXB Giáo dục');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaQuyen` int(11) NOT NULL,
  `TenQuyen` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`) VALUES
(1, 'Toàn quyền'),
(2, 'Nhân viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhapsach`
--

CREATE TABLE `phieunhapsach` (
  `MaPhieuNhapSach` int(11) NOT NULL,
  `NgayNhap` date DEFAULT NULL,
  `TongTien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhapsach`
--

INSERT INTO `phieunhapsach` (`MaPhieuNhapSach`, `NgayNhap`, `TongTien`) VALUES
(1, '2024-11-11', 10000000),
(2, '2024-11-17', 15000000),
(3, '2024-12-05', 2500000),
(4, '2024-12-15', 10000000),
(6, '2024-11-29', 42000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuthutien`
--

CREATE TABLE `phieuthutien` (
  `MaPhieuThuTien` int(11) NOT NULL,
  `MaKhachHang` int(11) NOT NULL,
  `SoTienThu` int(11) DEFAULT NULL,
  `NgayThuTien` date DEFAULT NULL,
  `MaNhanVien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieuthutien`
--

INSERT INTO `phieuthutien` (`MaPhieuThuTien`, `MaKhachHang`, `SoTienThu`, `NgayThuTien`, `MaNhanVien`) VALUES
(1, 1, 1000000, '2024-11-26', 2),
(2, 2, 2250000, '2024-11-26', 2),
(3, 3, 3000000, '2024-11-26', 2),
(4, 4, 3000000, '2024-11-26', 2),
(5, 1, 1625000, '2024-12-15', 1),
(6, 2, 500000, '2024-12-16', 1),
(7, 3, 300000, '2024-12-17', 1),
(8, 4, 1000000, '2024-12-18', 1);

--
-- Bẫy `phieuthutien`
--
DELIMITER $$
CREATE TRIGGER `trg_update_baocaocongno_phieuthutien` AFTER INSERT ON `phieuthutien` FOR EACH ROW BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    DECLARE SoTienThu INT DEFAULT 0;

    SET Thang = MONTH(NEW.NgayThuTien);
    SET Nam = YEAR(NEW.NgayThuTien);

    -- Tính số tiền thu
    SET SoTienThu = NEW.SoTienThu;

    -- Cập nhật bảng BAOCAOCONGNO
    IF NOT EXISTS (SELECT 1 FROM BAOCAOCONGNO WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam) THEN
        INSERT INTO BAOCAOCONGNO (MaKhachHang, NoDau, NoCuoi, PhatSinh, Thang, Nam)
        VALUES (NEW.MaKhachHang, 0, -SoTienThu, 0, Thang, Nam);
    ELSE
        UPDATE BAOCAOCONGNO
        SET NoCuoi = NoCuoi - SoTienThu
        WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam;
    END IF;

    -- Cập nhật tổng nợ của khách hàng
    UPDATE KHACHHANG
    SET TongNo = TongNo - SoTienThu
    WHERE MaKhachHang = NEW.MaKhachHang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_update_baocaocongno_phieuthutien_update` AFTER UPDATE ON `phieuthutien` FOR EACH ROW BEGIN
    DECLARE Thang INT;
    DECLARE Nam INT;
    DECLARE SoTienThu INT DEFAULT 0;

    SET Thang = MONTH(NEW.NgayThuTien);
    SET Nam = YEAR(NEW.NgayThuTien);

    -- Tính số tiền thu
    SET SoTienThu = NEW.SoTienThu - OLD.SoTienThu;

    -- Cập nhật bảng BAOCAOCONGNO
    IF NOT EXISTS (SELECT 1 FROM BAOCAOCONGNO WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam) THEN
        INSERT INTO BAOCAOCONGNO (MaKhachHang, NoDau, NoCuoi, PhatSinh, Thang, Nam)
        VALUES (NEW.MaKhachHang, 0, -SoTienThu, 0, Thang, Nam);
    ELSE
        UPDATE BAOCAOCONGNO
        SET NoCuoi = NoCuoi - SoTienThu
        WHERE MaKhachHang = NEW.MaKhachHang AND Thang = Thang AND Nam = Nam;
    END IF;

    -- Cập nhật tổng nợ của khách hàng
    UPDATE KHACHHANG
    SET TongNo = TongNo - SoTienThu
    WHERE MaKhachHang = NEW.MaKhachHang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `MaSach` int(11) NOT NULL,
  `MaDauSach` int(11) NOT NULL,
  `DonGia` decimal(20,2) NOT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `NamXuatBan` int(11) DEFAULT NULL,
  `MaNhaXuatBan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`MaSach`, `MaDauSach`, `DonGia`, `SoLuong`, `NamXuatBan`, `MaNhaXuatBan`) VALUES
(1, 1, 50000.00, 200, 2020, 1),
(2, 1, 100000.00, 240, 2021, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tacgia`
--

CREATE TABLE `tacgia` (
  `MaTacGia` int(11) NOT NULL,
  `TenTacGia` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tacgia`
--

INSERT INTO `tacgia` (`MaTacGia`, `TenTacGia`) VALUES
(1, 'Nam Cao'),
(2, 'Vũ Trọng Phụng'),
(3, 'Dale Carnegie'),
(4, 'Nguyễn Nhật Ánh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thamso`
--

CREATE TABLE `thamso` (
  `id` int(11) NOT NULL,
  `SoLuongNhapToiThieu` int(11) NOT NULL DEFAULT 150,
  `SoLuongTonToiThieuDeNhap` int(11) NOT NULL DEFAULT 300,
  `MonNoKhachHangToiDaDeBan` int(11) NOT NULL DEFAULT 1000000,
  `SoLuongTonSauKhiBanToiThieu` int(11) NOT NULL DEFAULT 20,
  `ApDungChinhSachThuNo` bit(1) NOT NULL DEFAULT b'1',
  `HeSoGiaBan` decimal(5,2) NOT NULL DEFAULT 0.05
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thamso`
--

INSERT INTO `thamso` (`id`, `SoLuongNhapToiThieu`, `SoLuongTonToiThieuDeNhap`, `MonNoKhachHangToiDaDeBan`, `SoLuongTonSauKhiBanToiThieu`, `ApDungChinhSachThuNo`, `HeSoGiaBan`) VALUES
(1, 150, 300, 1000000, 20, b'1', 0.05);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloai`
--

CREATE TABLE `theloai` (
  `MaTheLoai` int(11) NOT NULL,
  `TenTheLoai` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `theloai`
--

INSERT INTO `theloai` (`MaTheLoai`, `TenTheLoai`) VALUES
(1, 'Văn học'),
(2, 'Tiểu thuyết'),
(3, 'Lịch sử'),
(4, 'Tâm lý học'),
(5, 'Kinh tế'),
(6, 'Nghệ thuật'),
(7, 'Giáo dục');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baocaocongno`
--
ALTER TABLE `baocaocongno`
  ADD PRIMARY KEY (`MaBaoCaoCongNo`),
  ADD KEY `fk_baocaocongno_khachhang` (`MaKhachHang`);

--
-- Chỉ mục cho bảng `baocaoton`
--
ALTER TABLE `baocaoton`
  ADD PRIMARY KEY (`MaBaoCaoTonKho`),
  ADD KEY `fk_baocaoton_sach` (`MaSach`);

--
-- Chỉ mục cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`MaHoaDon`,`MaSach`),
  ADD KEY `fk_cthd_sach` (`MaSach`);

--
-- Chỉ mục cho bảng `ct_phieunhapsach`
--
ALTER TABLE `ct_phieunhapsach`
  ADD PRIMARY KEY (`MaPhieuNhapSach`,`MaSach`),
  ADD KEY `fk_ctphieunhapsach_sach` (`MaSach`);

--
-- Chỉ mục cho bảng `dausach`
--
ALTER TABLE `dausach`
  ADD PRIMARY KEY (`MaDauSach`),
  ADD KEY `fk_dausach_theloai` (`MaTheLoai`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHoaDon`),
  ADD KEY `fk_hoadon_nhanvien` (`MaNhanVien`),
  ADD KEY `fk_hoadon_khachhang` (`MaKhachHang`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKhachHang`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`),
  ADD UNIQUE KEY `TenDangNhap` (`TenDangNhap`),
  ADD KEY `fk_nhanvien_phanquyen` (`MaQuyen`);

--
-- Chỉ mục cho bảng `nxb`
--
ALTER TABLE `nxb`
  ADD PRIMARY KEY (`MaNhaXuatBan`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Chỉ mục cho bảng `phieunhapsach`
--
ALTER TABLE `phieunhapsach`
  ADD PRIMARY KEY (`MaPhieuNhapSach`);

--
-- Chỉ mục cho bảng `phieuthutien`
--
ALTER TABLE `phieuthutien`
  ADD PRIMARY KEY (`MaPhieuThuTien`),
  ADD KEY `fk_phieuthutien_khachhang` (`MaKhachHang`),
  ADD KEY `fk_phieuthutien_nhanvien` (`MaNhanVien`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`MaSach`),
  ADD KEY `fk_sach_dausach` (`MaDauSach`),
  ADD KEY `fk_sach_nhaxuatban` (`MaNhaXuatBan`);

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`MaTacGia`);

--
-- Chỉ mục cho bảng `thamso`
--
ALTER TABLE `thamso`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`MaTheLoai`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `baocaocongno`
--
ALTER TABLE `baocaocongno`
  MODIFY `MaBaoCaoCongNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `baocaoton`
--
ALTER TABLE `baocaoton`
  MODIFY `MaBaoCaoTonKho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `dausach`
--
ALTER TABLE `dausach`
  MODIFY `MaDauSach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHoaDon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `nxb`
--
ALTER TABLE `nxb`
  MODIFY `MaNhaXuatBan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `MaQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `phieunhapsach`
--
ALTER TABLE `phieunhapsach`
  MODIFY `MaPhieuNhapSach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `phieuthutien`
--
ALTER TABLE `phieuthutien`
  MODIFY `MaPhieuThuTien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `sach`
--
ALTER TABLE `sach`
  MODIFY `MaSach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  MODIFY `MaTacGia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `thamso`
--
ALTER TABLE `thamso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `theloai`
--
ALTER TABLE `theloai`
  MODIFY `MaTheLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `baocaocongno`
--
ALTER TABLE `baocaocongno`
  ADD CONSTRAINT `fk_baocaocongno_khachhang` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`);

--
-- Các ràng buộc cho bảng `baocaoton`
--
ALTER TABLE `baocaoton`
  ADD CONSTRAINT `fk_baocaoton_sach` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`);

--
-- Các ràng buộc cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD CONSTRAINT `fk_cthd_hoadon` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`),
  ADD CONSTRAINT `fk_cthd_sach` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`);

--
-- Các ràng buộc cho bảng `ct_phieunhapsach`
--
ALTER TABLE `ct_phieunhapsach`
  ADD CONSTRAINT `fk_ctphieunhapsach_phieunhapsach` FOREIGN KEY (`MaPhieuNhapSach`) REFERENCES `phieunhapsach` (`MaPhieuNhapSach`),
  ADD CONSTRAINT `fk_ctphieunhapsach_sach` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`);

--
-- Các ràng buộc cho bảng `dausach`
--
ALTER TABLE `dausach`
  ADD CONSTRAINT `fk_dausach_theloai` FOREIGN KEY (`MaTheLoai`) REFERENCES `theloai` (`MaTheLoai`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk_hoadon_khachhang` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`),
  ADD CONSTRAINT `fk_hoadon_nhanvien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `fk_nhanvien_phanquyen` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`);

--
-- Các ràng buộc cho bảng `phieuthutien`
--
ALTER TABLE `phieuthutien`
  ADD CONSTRAINT `fk_phieuthutien_khachhang` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`),
  ADD CONSTRAINT `fk_phieuthutien_nhanvien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`);

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `fk_sach_dausach` FOREIGN KEY (`MaDauSach`) REFERENCES `dausach` (`MaDauSach`),
  ADD CONSTRAINT `fk_sach_nhaxuatban` FOREIGN KEY (`MaNhaXuatBan`) REFERENCES `nxb` (`MaNhaXuatBan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
