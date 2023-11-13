/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

/**
 *
 * @author Hân Mai
 */
public class QuanTriVien {
    private int maQuanTriVien;
    private String tenDangNhap;
    private String matKhau;
    private String email;
    private String hoTen;

    @Override
    public String toString() {
        return "QuanTriVien{" + "hoTen=" + hoTen + '}';
    }
    
    public int getMaQuanTriVien() {
        return maQuanTriVien;
    }

    public void setMaQuanTriVien(int maQuanTriVien) {
        this.maQuanTriVien = maQuanTriVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    
}
