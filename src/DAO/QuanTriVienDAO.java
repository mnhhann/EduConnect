/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import ENTITY.QuanTriVien;
import UTILS.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.list;

/**
 *
 * @author Hân Mai
 */
public class QuanTriVienDAO extends EduConnectDAO<QuanTriVien, String> {

    String INSERT_SQL = "INSERT INTO QuanTriVien VALUES (?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE QuanTriVien set TenDangNhap = ?, MatKhau = ?, Email = ?, HoVaTen = ? WHERE MaQuanTriVien = ?";
    String DELETE_SQL = "DELETE FROM QuanTriVien Where MaQuanTriVien = ?";
    String SELECT_ALL_SQL = "SELECT * FROM QuanTriVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM QuanTriVien WHERE TenDangNhap = ?";

    @Override
    public void insert(QuanTriVien entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaQuanTriVien(),
                entity.getTenDangNhap(),
                entity.getMatKhau(),
                entity.getEmail(),
                entity.getHoTen());

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
