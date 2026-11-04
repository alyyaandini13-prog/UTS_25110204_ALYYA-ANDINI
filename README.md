Kesimpulan Ujian
1. Database db_inventori dibuat untuk menyimpan informasi barang laboratorium komputer. Di dalamnya terdapat tabel barang yang terdiri dari kode barang, nama barang, kategori, dan stok. Setiap barang memiliki kode yang berbeda sebagai identitas utama dalam database.

2. Pembuatan aplikasi dilakukan menggunakan Java NetBeans dengan membagi program ke beberapa package, yaitu model, dao, dan view. Pembagian tersebut membantu memisahkan fungsi setiap bagian sehingga kode program lebih mudah dipahami dan dikembangkan.

3. Koneksi antara Java dan MySQL menggunakan JDBC dengan bantuan MySQL Connector/J. Driver tersebut berfungsi sebagai penghubung agar aplikasi dapat mengakses dan mengolah data yang tersimpan di dalam database.

4. Class Koneksi berfungsi mengatur hubungan antara aplikasi dengan database MySQL. Proses koneksi dilakukan menggunakan DriverManager sehingga program dapat mengetahui apakah database berhasil dihubungkan atau mengalami kesalahan.

5. Class Barang digunakan untuk menampung informasi setiap barang yang akan dikelola. Class ini memiliki empat atribut utama serta dilengkapi constructor, getter, dan setter untuk mengatur nilai data barang.

6. Class BarangDAO bertugas menjalankan proses pengelolaan data melalui perintah SQL. Fungsi yang digunakan meliputi penambahan data, pengambilan data, pembaruan data, dan penghapusan data dengan bantuan PreparedStatement.

7. FormBarang merupakan tampilan utama aplikasi yang dibuat menggunakan Java Swing. Pada form tersebut tersedia kolom pengisian data, pilihan kategori, tombol Simpan, Ubah, Hapus, dan Bersihkan, serta tabel untuk melihat seluruh data barang yang telah dimasukkan.

8. Proses pengujian dilakukan dengan memasukkan beberapa data barang, mencoba menyimpan data, mengubah informasi, dan menghapus data. Validasi juga diterapkan untuk mencegah input kosong, stok berupa huruf, dan stok bernilai negatif.

9. Berdasarkan proses pembuatan aplikasi, penggunaan Java NetBeans dan MySQL dapat membantu mempermudah pengelolaan inventaris barang laboratorium. Data dapat disimpan secara terstruktur sehingga lebih mudah diperiksa, diperbarui, dan digunakan kembali.

Kesimpulan akhir: Aplikasi pengelolaan data barang ini dirancang untuk membantu proses pencatatan inventaris laboratorium komputer agar lebih praktis, teratur, dan efisien dengan memanfaatkan pemrograman Java serta database MySQL.

