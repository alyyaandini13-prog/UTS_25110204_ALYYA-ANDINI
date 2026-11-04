package view;

import dao.BarangDAO;
import model.Barang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormBarang extends JFrame {

    private JTextField txtKode, txtNama, txtStok;
    private JComboBox<String> cmbKategori;
    private JTable tabel;
    private DefaultTableModel model;
    private BarangDAO dao = new BarangDAO();

    private final Color navy = new Color(36, 54, 75);
    private final Color latar = new Color(243, 246, 249);
    private final Color garis = new Color(218, 225, 233);

    public FormBarang() {
        setTitle("Data Barang Laboratorium");
        setSize(850, 600);
        setMinimumSize(new Dimension(750, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel utama = new JPanel(new BorderLayout(15, 15));
        utama.setBackground(latar);
        utama.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(navy);
        header.setBorder(
            BorderFactory.createEmptyBorder(18, 20, 18, 20)
        );

        JLabel judul = new JLabel("DATA BARANG LABORATORIUM");
        judul.setForeground(Color.WHITE);
        judul.setFont(new Font("SansSerif", Font.BOLD, 21));

        JLabel subjudul = new JLabel(
            "Sistem Informasi Inventaris"
        );
        subjudul.setForeground(Color.WHITE);
        subjudul.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel teksHeader = new JPanel(new GridLayout(2, 1, 0, 5));
        teksHeader.setBackground(navy);
        teksHeader.add(judul);
        teksHeader.add(subjudul);

        header.add(teksHeader, BorderLayout.WEST);
        utama.add(header, BorderLayout.NORTH);

        // Form input barang
        JPanel form = new JPanel(new BorderLayout(10, 15));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(garis),
            BorderFactory.createEmptyBorder(15, 18, 15, 18)
        ));

        JLabel labelForm = new JLabel("Form Barang");
        labelForm.setFont(new Font("SansSerif", Font.BOLD, 17));
        labelForm.setForeground(navy);
        form.add(labelForm, BorderLayout.NORTH);

        JPanel input = new JPanel(new GridLayout(2, 2, 15, 12));
        input.setBackground(Color.WHITE);

        txtKode = new JTextField();
        txtNama = new JTextField();
        txtStok = new JTextField();

        cmbKategori = new JComboBox<>(new String[]{
            "Komputer", "Jaringan", "Alat Ukur"
        });

        input.add(buatField("Kode Barang", txtKode));
        input.add(buatField("Nama Barang", txtNama));
        input.add(buatField("Kategori", cmbKategori));
        input.add(buatField("Stok", txtStok));

        form.add(input, BorderLayout.CENTER);

        // Tombol standar tanpa warna khusus
        JPanel tombol = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 8, 0)
        );
        tombol.setBackground(Color.WHITE);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUbah = new JButton("Ubah");
        JButton btnHapus = new JButton("Hapus");
        JButton btnBersih = new JButton("Bersihkan");

        tombol.add(btnTambah);
        tombol.add(btnUbah);
        tombol.add(btnHapus);
        tombol.add(btnBersih);

        form.add(tombol, BorderLayout.SOUTH);

        // Model tabel
        model = new DefaultTableModel(
            new String[]{
                "Kode Barang",
                "Nama Barang",
                "Kategori",
                "Stok"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Tabel output
        tabel = new JTable(model);
        tabel.setRowHeight(30);
        tabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabel.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        tabel.getTableHeader().setFont(
            new Font("SansSerif", Font.BOLD, 13)
        );
        tabel.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabel);

        // Kotak output
        JPanel panelTabel = new JPanel(new BorderLayout(0, 10));
        panelTabel.setBackground(Color.WHITE);
        panelTabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(garis),
            BorderFactory.createEmptyBorder(15, 18, 15, 18)
        ));

        JLabel labelTabel = new JLabel("Daftar Barang");
        labelTabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        labelTabel.setForeground(navy);

        panelTabel.add(labelTabel, BorderLayout.NORTH);
        panelTabel.add(scroll, BorderLayout.CENTER);

        JLabel footer = new JLabel(
            "Data barang laboratorium"
        );
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(Color.GRAY);
        panelTabel.add(footer, BorderLayout.SOUTH);

        // Menyusun form dan tabel
        JPanel isi = new JPanel(new BorderLayout(0, 15));
        isi.setBackground(latar);
        isi.add(form, BorderLayout.NORTH);
        isi.add(panelTabel, BorderLayout.CENTER);

        utama.add(isi, BorderLayout.CENTER);
        add(utama);

        // Aksi tombol
        btnTambah.addActionListener(e -> tambahBarang());
        btnUbah.addActionListener(e -> ubahBarang());
        btnHapus.addActionListener(e -> hapusBarang());
        btnBersih.addActionListener(e -> bersihkan());

        // Memilih data dari tabel
        tabel.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()
                    && tabel.getSelectedRow() >= 0) {

                int r = tabel.getSelectedRow();

                txtKode.setText(
                    model.getValueAt(r, 0).toString()
                );
                txtNama.setText(
                    model.getValueAt(r, 1).toString()
                );
                cmbKategori.setSelectedItem(
                    model.getValueAt(r, 2).toString()
                );
                txtStok.setText(
                    model.getValueAt(r, 3).toString()
                );

                // Kode menjadi identitas barang
                txtKode.setEditable(false);
            }
        });

        // Memuat data dari database
        tampilkanData();
    }

    // Membuat label dan komponen input
    private JPanel buatField(
            String label, JComponent komponen) {

        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(Color.WHITE);

        JLabel teks = new JLabel(label);
        teks.setFont(new Font("SansSerif", Font.BOLD, 12));
        teks.setForeground(navy);

        komponen.setPreferredSize(new Dimension(180, 32));
        komponen.setFont(new Font("SansSerif", Font.PLAIN, 13));

        panel.add(teks, BorderLayout.NORTH);
        panel.add(komponen, BorderLayout.CENTER);

        return panel;
    }

    // Menampilkan data barang
    private void tampilkanData() {
        model.setRowCount(0);

        List<Barang> daftar = dao.getAll();

        for (Barang b : daftar) {
            model.addRow(new Object[]{
                b.getKodeBarang(),
                b.getNamaBarang(),
                b.getKategori(),
                b.getStok()
            });
        }
    }

    // Validasi input
    private boolean validasi() {
        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        String stokText = txtStok.getText().trim();

        if (kode.isEmpty() || nama.isEmpty() || stokText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Kode, nama barang, dan stok wajib diisi!"
            );
            return false;
        }

        if (kode.length() > 10) {
            JOptionPane.showMessageDialog(
                this,
                "Kode barang maksimal 10 karakter!"
            );
            return false;
        }

        if (nama.length() > 50) {
            JOptionPane.showMessageDialog(
                this,
                "Nama barang maksimal 50 karakter!"
            );
            return false;
        }

        try {
            int stok = Integer.parseInt(stokText);

            if (stok < 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Stok tidak boleh negatif!"
                );
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Stok harus berupa angka bulat!"
            );
            return false;
        }

        return true;
    }

    // Mengambil input menjadi objek Barang
    private Barang dataForm() {
        return new Barang(
            txtKode.getText().trim(),
            txtNama.getText().trim(),
            cmbKategori.getSelectedItem().toString(),
            Integer.parseInt(txtStok.getText().trim())
        );
    }

    // Tambah data
    private void tambahBarang() {
        if (!validasi()) {
            return;
        }

        if (dao.tambah(dataForm())) {
            JOptionPane.showMessageDialog(
                this, "Data barang berhasil ditambahkan!"
            );
            tampilkanData();
            bersihkan();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Gagal menambah data. Periksa koneksi atau kode barang."
            );
        }
    }

    // Ubah data
    private void ubahBarang() {
        if (tabel.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(
                this, "Pilih data barang dari tabel terlebih dahulu!"
            );
            return;
        }

        if (!validasi()) {
            return;
        }

        if (dao.ubah(dataForm())) {
            JOptionPane.showMessageDialog(
                this, "Data barang berhasil diubah!"
            );
            tampilkanData();
            bersihkan();
        } else {
            JOptionPane.showMessageDialog(
                this, "Gagal mengubah data barang!"
            );
        }
    }

    // Hapus data
    private void hapusBarang() {
        int baris = tabel.getSelectedRow();

        if (baris < 0) {
            JOptionPane.showMessageDialog(
                this, "Pilih data barang yang ingin dihapus!"
            );
            return;
        }

        String kode = model.getValueAt(baris, 0).toString();

        int konfirmasi = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus barang " + kode + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            if (dao.hapus(kode)) {
                JOptionPane.showMessageDialog(
                    this, "Data barang berhasil dihapus!"
                );
                tampilkanData();
                bersihkan();
            } else {
                JOptionPane.showMessageDialog(
                    this, "Gagal menghapus data barang!"
                );
            }
        }
    }

    // Mengosongkan form
    private void bersihkan() {
        txtKode.setText("");
        txtNama.setText("");
        txtStok.setText("");
        cmbKategori.setSelectedIndex(0);

        txtKode.setEditable(true);
        tabel.clearSelection();
        txtKode.requestFocus();
    }

    // Menjalankan form
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormBarang().setVisible(true);
        });
    }
}