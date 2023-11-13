/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.HocSinh;
import ENTITY.QuanTriVien;
import UTILS.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hân Mai
 */
public class HocSinhDAO extends EduConnectDAO<HocSinh, String>{

    String INSERT_SQL = "INSERT INTO HocSinh VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE QuanTriVien set TenDangNhap = ?, MatKhau = ?, Email = ?, HoVaTen = ?, NgaySinh = ?, GioiTinh = ?, SoDienThoai = ?, DiaChi = ? WHERE MaHocSinh = ?";
    String DELETE_SQL = "DELETE FROM HocSinh Where MaHocSinh = ?";
    String SELECT_ALL_SQL = "SELECT * FROM HocSinh";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocSinh WHERE TenDangNhap = ?";

    @Override
    public void insert(HocSinh entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaHocSinh(),
                entity.getTenDangNhap(),
                entity.getMatKhau(),
                entity.getEmail(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getgioiTinh());

    }

    @Override
    public void update(QuanTriVien entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getTenDangNhap(),
                entity.getMatKhau(),
                entity.getEmail(),
                entity.getHoTen(),
                entity.getMaQuanTriVien());
    }

    @Override
    public void delete(String maQuanTriVien) {
        JDBCHelper.update(DELETE_SQL, maQuanTriVien);
    }

    @Override
    public QuanTriVien selectByid(String id) {
        List<QuanTriVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<QuanTriVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<QuanTriVien> selectBySql(String sql, Object... args) {
        List<QuanTriVien> list = new ArrayList<QuanTriVien>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                QuanTriVien entity = new QuanTriVien();
                entity.setMaQuanTriVien(rs.getInt("MaQuanTriVien"));
                entity.setTenDangNhap(rs.getString("TenDangNhap"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setEmail(rs.getString("Email"));
                entity.setHoTen(rs.getString("HoVaTen"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log chi tiết về ngoại lệ
            throw new RuntimeException("Lỗi khi thực hiện truy vấn SQL", e);
        }
    }
}
