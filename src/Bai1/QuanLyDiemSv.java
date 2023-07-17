package Bai1;
import java.util.Scanner;

public class QuanLyDiemSv {
    public static void main(String[] args) {
        int n = 0;
        String[] hoten = null;
        double[] dToan = null;
        double[] dVan = null;
        double[] dAnh = null;
        double[] dTB = null;

        char key = ' ';
        do {
            inMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print("Ban chon chuc nang nao: ");
            key = sc.next().charAt(0);

            switch (key) {
                case '1':
                    n = nhapSoLuongSinhVien();
                    hoten = new String[n];
                    dToan = new double[n];
                    dVan = new double[n];
                    dAnh = new double[n];
                    dTB = new double[n];
                    nhapHoTenVaDiem(hoten, dToan, dVan, dAnh);
                    break;
                case '2':
                    if (n > 0) {
                        dTB = tinhDTB(dToan, dVan, dAnh);
                        inDSSV(hoten, dToan, dVan, dAnh, dTB);
                    } else {
                        System.out.println("Nhap du lieu truoc khi tinh diem trung binh.");
                    }
                    break;
                case '3':
                    if (n > 0) {
                        inDSSV(hoten, dToan, dVan, dAnh, dTB);
                    } else {
                        System.out.println("Nhap du lieu truoc khi in danh sach sinh vien.");
                    }
                    break;
                case '4':
                    if (n > 0) {
                        sapxepDSSV(hoten, dToan, dVan, dAnh, dTB);
                        inDSSV(hoten, dToan, dVan, dAnh, dTB);
                    } else {
                        System.out.println("Nhap du lieu truoc khi sap xep danh sach sinh vien.");
                    }
                    break;
                case '5':
                    if (n > 0) {
                        timkiemDiem(hoten, dToan, dVan, dAnh, dTB);
                    } else {
                        System.out.println("Nhap du lieu truoc khi tim kiem theo diem Toan.");
                    }
                    break;
                case 'Q':
                case 'q':
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    break;
            }
        } while (key != 'Q' && key != 'q');
    }

    public static void inMenu() {
        System.out.println("*******************************");
        System.out.println("(1): Nhap du lieu");
        System.out.println("(2): Tinh Diem trung binh");
        System.out.println("(3): In danh sach sinh vien");
        System.out.println("(4): Sap xep theo DTB");
        System.out.println("(5): Tim kiem theo diem Toan");
        System.out.println("(Q/q): Thoat khoi chuong trinh");
        System.out.println("*******************************");
    }

    public static int nhapSoLuongSinhVien() {
        try {
            int n;
            Scanner sc = new Scanner(System.in);
            do {
                System.out.print("Nhap so luong sinh vien n = ");
                n = sc.nextInt();
            } while (n <= 0);
            return n;
        } catch (Exception e) {
            System.out.println("DA XAY RA LOI TRONG QUA TRINH NHAP DU LIEU");
            return -1;
        }
    }

    public static void nhapHoTenVaDiem(String[] hoten, double[] dToan, double[] dVan, double[] dAnh) {
        System.out.println("*************************************************************");
        try {
            for (int i = 0; i < hoten.length; i++) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Nhap du lieu cho sinh vien thu " + (i + 1));
                System.out.print("Ho ten: ");
                hoten[i] = sc.nextLine();
                System.out.print("Diem Toan: ");
                dToan[i] = sc.nextDouble();
                System.out.print("Diem Van: ");
                dVan[i] = sc.nextDouble();
                System.out.print("Diem Anh: ");
                dAnh[i] = sc.nextDouble();
            }
        } catch (Exception e) {
            System.out.println("DA XAY RA LOI TRONG QUA TRINH NHAP DU LIEU");
        }
        System.out.println("*************************************************************");
    }

    public static double[] tinhDTB(double[] dToan, double[] dVan, double[] dAnh) {
        double[] dtb = new double[dToan.length];
        for (int i = 0; i < dtb.length; i++) {
            dtb[i] = Math.round(((dToan[i] + dVan[i] + dAnh[i]) / 3) * 100.0) / 100.0;
        }
        return dtb;
    }

    public static void sapxepDSSV(String[] hoten, double[] dToan, double[] dVan, double[] dAnh, double[] dTB) {
        for (int i = 0; i < dTB.length - 1; i++) {
            for (int j = i + 1; j < dTB.length; j++) {
                if (dTB[i] < dTB[j]) {
                    double temp = dTB[i];
                    dTB[i] = dTB[j];
                    dTB[j] = temp;

                    temp = dToan[i];
                    dToan[i] = dToan[j];
                    dToan[j] = temp;

                    temp = dVan[i];
                    dVan[i] = dVan[j];
                    dVan[j] = temp;

                    temp = dAnh[i];
                    dAnh[i] = dAnh[j];
                    dAnh[j] = temp;

                    String tempName = hoten[i];
                    hoten[i] = hoten[j];
                    hoten[j] = tempName;
                }
            }
        }
    }

    public static void inDSSV(String[] hoten, double[] dToan, double[] dVan, double[] dAnh, double[] dTB) {
        System.out.println("*************************************************************");
        System.out.println("\t\t\t DANH SACH SINH VIEN");
        for (int i = 0; i < hoten.length; i++) {
            System.out.println(hoten[i] + "; Toan: " + dToan[i] + "; Van: " + dVan[i] + "; Anh: " + dAnh[i] + "; DTB: " + dTB[i]);
        }
        System.out.println("*************************************************************");
    }

    public static void timkiemDiem(String[]hoten, double[] dToan, double[] dVan, double[] dAnh, double[] dTB) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        double diemcantim;
        try {
            do {
                System.out.print("Diem Toan can tim: ");
                diemcantim = sc.nextDouble();
            } while (diemcantim < 0.0 || diemcantim > 10.0);

            for (int i = 0; i < dToan.length; i++) {
                if (dToan[i] == diemcantim) {
                    System.out.println(
                            hoten[i] + "; Toan: " + dToan[i] + "; Van: " + dVan[i] + "; Anh: " + dAnh[i] + "; DTB: " + dTB[i]);
                    count = count + 1;
                }
            }
            if (count == 0) {
                System.out.println("KHONG TIM THAY");
            }
        } catch (Exception e) {
            System.out.println("DA XAY RA LOI TRONG QUA TRINH NHAP DU LIEU");
        }
    }

    public static void timHoTen(String[] hoten, double[] dToan, double[] dVan, double[] dAnh, double[] dTB) {
        // TODO: Implement the search by name functionality
    }
}
