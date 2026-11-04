package dao;

import model.Barang;
import model.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {

    // Menampilkan semua data barang
    public List<Barang> getAll() {
        List<Barang> daftar = new ArrayList<>();

        String sql = "SELECT * FROM barang";

        try (Connection conn = koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Barang b = new Barang(
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getInt("stok")
                );

                daftar.add(b);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }

        return daftar;
    }

    // Menambahkan barang
    public boolean tambah(Barang b) {
        String sql = "INSERT INTO barang "
                   + "(kode_barang, nama_barang, kategori, stok) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getKodeBarang());
            ps.setString(2, b.getNamaBarang());
            ps.setString(3, b.getKategori());
            ps.setInt(4, b.getStok());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambah data: " + e.getMessage());
            return false;
        }
    }

    // Mengubah barang
    public boolean ubah(Barang b) {
        String sql = "UPDATE barang SET nama_barang=?, "
                   + "kategori=?, stok=? WHERE kode_barang=?";

        try (Connection conn = koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getNamaBarang());
            ps.setString(2, b.getKategori());
            ps.setInt(3, b.getStok());
            ps.setString(4, b.getKodeBarang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data: " + e.getMessage());
            return false;
        }
    }

    // Menghapus barang
    public boolean hapus(String kode) {
        String sql = "DELETE FROM barang WHERE kode_barang=?";

        try (Connection conn = koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kode);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
            return false;
        }
    }
}