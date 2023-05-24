package com.pd05529.bookmanagement.DBHelper;

public class Data_SQLite {
    //Tạo bảng
    //Tao bang ThuThu
    public static final String createThuThu = "create table ThuThu(" +
            "maTT TEXT PRIMARY KEY," +
            "hoTen TEXT NOT NULL," +
            "matKhau TEXT NOT NULL," +
            "phone INTEGER NOT NULL," +
            "role INTEGER NOT NULL)";

    //Tao bang ThanhVien
    public static final String createThanhVien = "create table ThanhVien(" +
            "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
            "hoTen TEXT NOT NULL," +
            "phone INTEGER NOT NULL," +
            "namSinh TEXT NOT NULL)";
    //Tao bang LoaiSach
    public static final String createLoaiSach = "create table LoaiSach(" +
            "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
            "tenLoai TEXT NOT NULL)";

    //Tao bang Sach
    public static final String createSach = "create table Sach(" +
            "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
            "tenSach TEXT NOT NULL," +
            "maLoai INTEGER REFERENCES LoaiSach(maLoai)," +
            "tacGia TEXT)";
    //Tao bang PhieuMuon
    public static final String createPhieuMuon="create table PhieuMuon(" +
            "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
            "maSach INTEGER REFERENCES Sach(maSach)," +
            "maTT TEXT REFERENCES ThuThu(maTT)," +
            "maTV INTEGER REFERENCES ThanhVien(maTV)," +
            "tienThue INTEGER NOT NULL," +
            "ngay DATE NOT NULL," +
            "traSach INTEGER NOT NULL)";
    //Câu lệnh tạo dữ liệu ban đầu
    public static final String INSERT_THUTHU = "INSERT INTO ThuThu VALUES" +
            "('admin','Trương Công Nghĩa','admin',339214624,0)," +
            "('thuthu01','Nguyễn Văn A','thuthu01',339214624,1)," +
            "('thuthu02','Trần Thị B','thuthu02',339214123,1)";
    public static final String INSERT_LOAISACH = "INSERT INTO LoaiSach(tenLoai) VALUES" +
            "('Tin học')," +
            "('Kinh tế')," +
            "('Chính trị')," +
            "('Võ')," +
            "('Xây dựng')";
    public static final String INSERT_THANHVIEN = "INSERT INTO ThanhVien(hoTen,phone,namSinh) VALUES" +
            "('Hồng B',12342,'2001/3/12')," +
            "('Lý A',12342,'1004/1/03')," +
            "('Chính Q',1457989,'1999/12/21')," +
            "('Nguyễn A',978542,'2001/4/30')";
    public static final String INSERT_SACH = "INSERT INTO Sach(tenSach,maLoai,tacGia) VALUES" +
            "('Lập trình C',1,'')," +
            "('Làm giàu',2,'')," +
            "('Kiến trúc xây dựng',5,'')," +
            "('Học thuật',1,'Nguyễn Chí')";
    public static final String INSERT_PHIEUMUON = "INSERT INTO PhieuMuon VALUES" +
            "(1,1,'admin',1,3000,'2021/03/05',1)," +
            "(2,1,'thuthu01',1,2000,'2022/03/05',1)," +
            "(3,2,'admin',4,6000,'2022/04/18',0)," +
            "(4,1,'admin',1,3000,'2022/09/21',1)," +
            "(5,2,'thuthu02',2,3000,'2022/10/05',0)";
}
