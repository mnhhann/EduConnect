/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.GiangVien;
import UTILS.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Hân Mai
 */
public class GiangVienDAO extends EduConnectDAO<GiangVien, String> {

    String INSERT_SQL = "INSERT INTO GiangVien VALUES (?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE GiangVien set TenDangNhap = ?, MatKhau = ?, Email = ?, HoVaTen = ?, NgaySinh = ?, GioiTinh = ?, SoDienThoai = ?, DiaChi = ?, BoMon = ? WHERE MaGiangVien = ?";
    String DELETE_SQL = "DELETE FROM GiangVien Where MaGiangVien = ?";
    String SELECT_ALL_SQL = "SELECT * FROM GiangVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM GiangVien WHERE TenDangNhap = ?";

    @Override
    public void insert(GiangVien entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaGiangVien(),
                entity.getTenDangNhap(),
                entity.getMatKhau(),
                entity.getEmail(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getSoDienThoai(),
                entity.getDiaChi(),
                entity.getBoMon());
    }

    @Override
    public void update(GiangVien entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getTenDangNhap(),
                entity.getMatKhau(),
                entity.getEmail(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getSoDienThoai(),
                entity.getDiaChi(),
                entity.getBoMon(),
                entity.getMaGiangVien());
    }

    @Override
    public void delete(String maGiangVien) {
        JDBCHelper.update(DELETE_SQL, maGiangVien);
    }

    @Override
    public GiangVien selectByid(String id) {
        List<GiangVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<GiangVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<GiangVien> selectBySql(String sql, Object... args) {
        List<GiangVien> list = new ArrayList<GiangVien>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                GiangVien entity = new GiangVien();
                entity.setMaGiangVien(rs.getInt("MaGiangVien"));
                entity.setTenDangNhap(rs.getString("TenDangNhap"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setEmail(rs.getString("Email"));
                entity.setHoTen(rs.getString("HoVaTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setSoDienThoai(rs.getString("SoDienThoai"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setBoMon(rs.getString("BoMon"));
                
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
