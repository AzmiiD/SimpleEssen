# SimpleEssen

SimpleEssen adalah plugin Minecraft sederhana yang menyediakan beberapa perintah esensial dengan penggunaan yang mudah. Plugin ini dibuat untuk server yang ingin memiliki fungsionalitas dasar seperti teleportasi, warp, home, ekonomi, dan utilitas lainnya tanpa perlu menginstal plugin Essentials yang lebih kompleks.

## Fitur

Berikut adalah daftar fitur yang tersedia di SimpleEssen:

* **Teleportasi (TPA):**
    * `/tpa <nama_pemain>`: Mengirim permintaan teleportasi ke pemain lain.
    * `/tpaccept`: Menerima permintaan teleportasi yang masuk.
    * `/tpdeny`: Menolak permintaan teleportasi yang masuk.
* **Warp:**
    * `/setwarp <nama>` (`/swarp`): Membuat warp baru di lokasi Anda saat ini.
    * `/warp <nama>` (`/w`): Berpindah ke lokasi warp yang telah ditentukan.
    * `/delwarp <nama>`: Menghapus warp yang sudah ada.
    * `/warplist` (`/warps`): Menampilkan daftar semua warp yang tersedia.
* **Ekonomi (Membutuhkan Vault):**
    * `/pay <nama_pemain> <jumlah>`: Mengirim sejumlah uang kepada pemain lain.
    * `/baltop`: Menampilkan daftar 10 pemain dengan saldo tertinggi di server.
* **Utilitas Pemain:**
    * `/feed`: Mengisi penuh tingkat kelaparan Anda.
    * `/heal`: Menyembuhkan diri Anda hingga penuh.
    * `/sethome`: Menetapkan lokasi rumah Anda saat ini.
    * `/home`: Berpindah ke lokasi rumah yang telah Anda tetapkan.

## Instalasi

1.  Unduh file `.jar` plugin SimpleEssen dari [link download Anda di sini].
2.  Letakkan file `.jar` ke dalam folder `plugins` di direktori server Minecraft Anda.
3.  Restart atau reload server Anda.
4.  (Untuk fitur ekonomi) Pastikan plugin [Vault](https://www.spigotmc.org/resources/vault.42053/) juga terinstal di server Anda.

## Penggunaan Perintah

Berikut adalah daftar perintah dan penggunaannya:

* `/tpa <nama_pemain>`: Kirim permintaan teleportasi.
* `/tpaccept`: Terima permintaan teleportasi.
* `/tpdeny`: Tolak permintaan teleportasi.
* `/setwarp <nama>`: Buat warp baru.
* `/warp <nama>`: Teleport ke warp.
* `/delwarp <nama>`: Hapus warp.
* `/warplist`: Lihat daftar warp.
* `/pay <nama_pemain> <jumlah>`: Bayar pemain lain.
* `/feed`: Isi perut.
* `/heal`: Sembuhkan diri.
* `/sethome`: Set lokasi home.
* `/home`: Pergi ke home.
* `/baltop`: Lihat top saldo.

## Kontribusi

Jika Anda ingin berkontribusi pada pengembangan SimpleEssen, Anda dapat melakukan fork pada repositori GitHub ini dan mengirimkan pull request dengan perubahan yang Anda buat.

## Lisensi

MIT License

## Penulis

AzmiiD (Leader)

---

Terima kasih telah menggunakan SimpleEssen! Jika Anda mengalami masalah atau memiliki saran, silakan buat issue di repositori GitHub.
