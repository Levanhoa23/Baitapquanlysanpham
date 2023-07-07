package Bai1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class SanPham {
    private String tenSanPham;
    private String donViTinh;
    private int soLuong;
    private double giaBan;
    private double thanhTien;

    public SanPham(String tenSanPham, String donViTinh, int soLuong, double giaBan) {
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = soLuong * giaBan;
    }

    public String layTenSanPham() {
        return tenSanPham;
    }

    public String layDonViTinh() {
        return donViTinh;
    }

    public int laySoLuong() {
        return soLuong;
    }

    public double layGiaBan() {
        return giaBan;
    }

    public double layThanhTien() {
        return thanhTien;
    }

    public void duaVaoThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}

public class Bai_01 {
    private static ArrayList<SanPham> danhSachSanPham = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char luaChon;

        do {
            System.out.println("==== MENU ====");
            System.out.println("1. Nhập thông tin sản phẩm");
            System.out.println("2. Tính giá trị thành tiền");
            System.out.println("3. Sắp xếp danh sách sản phẩm theo thành tiền giảm dần");
            System.out.println("4. Sắp xếp danh sách sản phẩm theo giá bán tăng dần");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("Q. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");
            luaChon = scanner.nextLine().charAt(0);

            switch (luaChon) {
                case '1':
                    nhapThongTinSanPham(scanner);
                    break;
                case '2':
                    tinhGiaTriThanhTien();
                    break;
                case '3':
                    sapXepTheoThanhTienGiamDan();
                    xuatDanhSachSanPham();
                    break;
                case '4':
                    sapXepTheoGiaBanTangDan();
                    xuatDanhSachSanPham();
                    break;
                case '5':
                    timKiemSanPhamTheoTen(scanner);
                    break;
                case 'Q':
                case 'q':
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }

            System.out.println();
        } while (luaChon != 'Q' && luaChon != 'q');

        scanner.close();
    }

    private static void nhapThongTinSanPham(Scanner scanner) {
        System.out.println("===== NHẬP THÔNG TIN SẢN PHẨM =====");
        System.out.print("Nhập tên sản phẩm: ");
        String tenSanPham = scanner.nextLine();
        System.out.print("Nhập đơn vị tính: ");
        String donViTinh = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        int soLuong = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập giá bán: ");
        double giaBan = Double.parseDouble(scanner.nextLine());

        SanPham sanPham = new SanPham(tenSanPham, donViTinh, soLuong, giaBan);
        danhSachSanPham.add(sanPham);

        System.out.println("Đã thêm sản phẩm thành công.");
    }

    private static void tinhGiaTriThanhTien() {
        System.out.println("===== TÍNH GIÁ TRỊ THÀNH TIỀN =====");
        for (SanPham sanPham : danhSachSanPham) {
            double thanhTien = sanPham.laySoLuong() * sanPham.layGiaBan();
            sanPham.duaVaoThanhTien(thanhTien);
        }

        System.out.println("Đã tính giá trị thành tiền cho các sản phẩm.");
    }

    private static void sapXepTheoThanhTienGiamDan() {
        System.out.println("===== SẮP XẾP THEO THÀNH TIỀN GIẢM DẦN =====");
        Collections.sort(danhSachSanPham, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sp1, SanPham sp2) {
                return Double.compare(sp2.layThanhTien(), sp1.layThanhTien());
            }
        });
    }

    private static void sapXepTheoGiaBanTangDan() {
        System.out.println("===== SẮP XẾP THEO GIÁ BÁN TĂNG DẦN =====");
        Collections.sort(danhSachSanPham, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sp1, SanPham sp2) {
                return Double.compare(sp1.layGiaBan(), sp2.layGiaBan());
            }
        });
    }

    private static void timKiemSanPhamTheoTen(Scanner scanner) {
        System.out.println("===== TÌM KIẾM SẢN PHẨM THEO TÊN =====");
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String tenSanPham = scanner.nextLine();

        boolean timThay = false;
        for (SanPham sanPham : danhSachSanPham) {
            if (sanPham.layTenSanPham().equalsIgnoreCase(tenSanPham)) {
                System.out.println("Thông tin sản phẩm:");
                System.out.println("Tên sản phẩm: " + sanPham.layTenSanPham());
                System.out.println("Đơn vị tính: " + sanPham.layDonViTinh());
                System.out.println("Số lượng: " + sanPham.laySoLuong());
                System.out.println("Giá bán: " + sanPham.layGiaBan());
                System.out.println("Thành tiền: " + sanPham.layThanhTien());
                timThay = true;
                break;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy sản phẩm có tên '" + tenSanPham + "'.");
        }
    }

    private static void xuatDanhSachSanPham() {
        System.out.println("===== DANH SÁCH SẢN PHẨM =====");
        for (SanPham sanPham : danhSachSanPham) {
            System.out.println("Tên sản phẩm: " + sanPham.layTenSanPham());
            System.out.println("Đơn vị tính: " + sanPham.layDonViTinh());
            System.out.println("Số lượng: " + sanPham.laySoLuong());
            System.out.println("Giá bán: " + sanPham.layGiaBan());
            System.out.println("Thành tiền: " + sanPham.layThanhTien());
            System.out.println("============================");
        }
    }
}
