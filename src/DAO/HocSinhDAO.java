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
                entity.getGioiTinh(),
                entity.getSoDienThoai(),
                entity.getDiaChi());

    }

    @Override
    public void update(HocSinh entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getTenDangNhap(),
                entity.getMatKhau(),
                entity.getEmail(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getSoDienThoai(),
                entity.getDiaChi(),
                entity.getMaHocSinh());
    }

    @Override
    public void delete(String maHocSinh) {
        JDBCHelper.update(DELETE_SQL, maHocSinh);
    }

    @Override
    protected List<HocSinh> selectBySql(String sql, Object... args) {
        List<HocSinh> list = new ArrayList<HocSinh>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                HocSinh entity = new HocSinh();
                entity.setMaHocSinh(rs.getInt("MaHocSinh"));
                entity.setTenDangNhap(rs.getString("TenDangNhap"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setEmail(rs.getString("Email"));
                entity.setHoTen(rs.getString("HoVaTen"));
                entity.setNgaySinh(rs.getString("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setSoDienThoai(rs.getString("SoDienThoai"));
                entity.setDiaChi(rs.getString("DiaChi"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log chi tiết về ngoại lệ
            throw new RuntimeException("Lỗi khi thực hiện truy vấn SQL", e);
        }
    }

    @Override
    public HocSinh selectByid(String id) {
        List<HocSinh> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocSinh> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }
}
