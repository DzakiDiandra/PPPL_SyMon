# Bug Report

* **Bug ID:** BR-LOGIN-001
* **Title:** CAPTCHA Cloudflare Muncul Sebelum Halaman Login Failed pada Pengujian Login dengan Email Non-UGM
* **Date:** 19 June 2026
* **Environment:** Production
* **Browser:** Google Chrome (Automated Testing with Selenium)
* **Severity:** Medium
* **Priority:** Medium

## Preconditions

1. Pengguna berada pada halaman login.
2. Selenium WebDriver aktif dan berhasil membuka aplikasi.
3. Akun yang digunakan tidak menggunakan domain email UGM.

## Steps to Reproduce

1. Buka halaman login aplikasi.
2. Klik tombol **Login dengan akun UGM**.
3. Masukkan email non-UGM atau kredensial yang tidak valid.
4. Lanjutkan proses login.
5. Amati respons sistem.

## Expected Result

Sistem menolak proses login dan menampilkan halaman atau pesan **"Login Failed"**, sehingga pengguna dapat mencoba login kembali menggunakan akun yang valid.

## Actual Result

Sistem mengarahkan pengguna ke halaman **CAPTCHA Cloudflare** sebelum halaman atau pesan **"Login Failed"** ditampilkan. Pengguna harus menyelesaikan CAPTCHA terlebih dahulu sebelum dapat melanjutkan proses login.

## Evidence

* Screenshot halaman CAPTCHA Cloudflare.
* Log hasil eksekusi Selenium.
* Rekaman video pengujian (jika tersedia).

## Status

**Open**

## Notes

Kemunculan CAPTCHA Cloudflare menghambat pengujian otomatis skenario login negatif menggunakan Selenium. Perlu dipastikan apakah perilaku ini memang merupakan konfigurasi keamanan yang diharapkan pada environment production atau merupakan hambatan terhadap validasi login gagal yang seharusnya ditampilkan langsung kepada pengguna.

### Impact on Automated Testing

CAPTCHA Cloudflare dirancang untuk membedakan pengguna manusia dengan aktivitas otomatis (bot). Karena Selenium termasuk automated browser, CAPTCHA dapat terpicu secara otomatis pada environment production. Akibatnya, test case login negatif tidak dapat divalidasi sepenuhnya melalui automation testing karena proses pengujian terhenti pada challenge CAPTCHA sebelum sistem menampilkan hasil autentikasi yang sebenarnya.

### Recommendation

* Konfirmasi kepada tim pengelola aplikasi apakah CAPTCHA merupakan mekanisme keamanan yang memang diaktifkan pada environment production.
* Jika perilaku tersebut memang diharapkan, maka test case ini dapat dikategorikan sebagai **blocked by security control** atau **not automatable in production environment**.
* Alternatif pengujian dapat dilakukan pada staging/testing environment yang tidak menerapkan CAPTCHA atau menggunakan whitelist untuk traffic automation testing.
