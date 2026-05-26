# ============================================================
# Feature  : Login & Authentication + Admin Management
# PIC      : M. Dzaki Diandra Putra
# TC IDs   : TC-SUP-01, TC-SUP-02, TC-SUP-03, TC-SUP-04,
#            TC-SUP-05, TC-SUP-06, TC-RM-01,
#            TC-ADM-01, TC-ADM-02, TC-ADM-03, TC-ADM-04, TC-ADM-05
# Jumlah   : 12 TC
# ============================================================

Feature: Login, Autentikasi, Role Middleware, dan Admin Management

  Background:
    Given aplikasi SyMon dapat diakses di browser

  # ----------------------------------------------------------
  # MODUL: Login & Autentikasi
  # ----------------------------------------------------------

  # TC-SUP-01
  Scenario: Tampilan halaman Login
    When pengguna membuka halaman utama aplikasi SyMon
    Then halaman login ditampilkan
    And halaman menampilkan gambar PC di tengah
    And halaman menampilkan deskripsi singkat tentang SyMon
    And halaman menampilkan tombol "Login with UGM Email"

  # TC-SUP-02
  Scenario: Login berhasil menggunakan email UGM yang valid
    Given pengguna berada di halaman login
    When pengguna menekan tombol "Login with UGM Email"
    And pengguna memilih akun email dengan domain "@ugm.ac.id"
    Then login berhasil dilakukan
    And pengguna diarahkan ke halaman dashboard sesuai role

  # TC-SUP-03
  Scenario: Login gagal menggunakan email non-UGM
    Given pengguna berada di halaman login
    When pengguna menekan tombol "Login with UGM Email"
    And pengguna memilih akun email dengan domain selain "@ugm.ac.id"
    Then login gagal dilakukan
    And pengguna diarahkan ke halaman "Login Failed"

  # TC-SUP-04
  Scenario: Switch account setelah gagal login lalu login dengan email UGM
    Given pengguna berada di halaman "Login Failed"
    When pengguna menekan tombol "Switch Account"
    And pengguna memilih akun email dengan domain "@ugm.ac.id"
    Then login berhasil dilakukan
    And pengguna diarahkan ke halaman dashboard sesuai role

  # TC-SUP-05
  Scenario: Tampilan halaman Profile pengguna
    Given pengguna sudah login dan berada di halaman dashboard
    When pengguna menekan foto profil atau nama di pojok kanan atas navbar
    Then pengguna diarahkan ke halaman profil
    And halaman profil menampilkan foto pengguna
    And halaman profil menampilkan nama pengguna
    And halaman profil menampilkan email pengguna
    And halaman profil menampilkan role pengguna
    And halaman profil menampilkan footer
    And halaman profil menampilkan tombol "Log Out"

  # TC-SUP-06
  Scenario: Logout dari sistem
    Given pengguna sudah login dan berada di halaman profil
    When pengguna menekan tombol "Log Out"
    Then muncul pop-up konfirmasi logout
    When pengguna menekan tombol "Yes" pada pop-up
    Then pengguna diarahkan kembali ke halaman Login
    And sesi pengguna telah dihapus

  # ----------------------------------------------------------
  # MODUL: Role Middleware
  # ----------------------------------------------------------

  # TC-RM-01
  Scenario Outline: Akses fitur sesuai role pengguna
    Given pengguna dengan role "<role>" sudah melakukan login
    When sistem memverifikasi role pengguna melalui backend
    Then pengguna mendapatkan hak akses sesuai role "<role>"
    And fitur yang dibatasi untuk role lain tidak dapat diakses

    Examples:
      | role  |
      | Admin |
      | User  |

  # ----------------------------------------------------------
  # MODUL: Admin Management
  # ----------------------------------------------------------

  # TC-ADM-01
  Scenario: Admin dapat mengakses fitur Add Admin
    Given pengguna sudah login sebagai Admin
    When pengguna menekan tombol "Add Admin"
    Then modal atau form "Add Admin" muncul dan dapat diisi

  # TC-ADM-02
  Scenario: Berhasil menambah admin dengan email UGM yang valid
    Given pengguna sudah login sebagai Admin
    And modal Add Admin sudah terbuka
    When pengguna mengisi email "newadmin@mail.ugm.ac.id"
    And pengguna menekan tombol "Add"
    Then admin baru berhasil ditambahkan ke sistem
    And admin baru muncul dalam daftar user dengan role "Admin"

  # TC-ADM-03
  Scenario: Gagal menambah admin karena field email kosong
    Given pengguna sudah login sebagai Admin
    And modal Add Admin sudah terbuka
    When pengguna tidak mengisi field email
    And pengguna menekan tombol "Add"
    Then muncul pesan error "Email required"
    And admin tidak berhasil ditambahkan

  # TC-ADM-04
  Scenario: Gagal menambah admin karena format email tidak valid
    Given pengguna sudah login sebagai Admin
    And modal Add Admin sudah terbuka
    When pengguna mengisi email dengan format tidak valid "bukan-email"
    And pengguna menekan tombol "Add"
    Then muncul pesan validasi error format email
    And admin tidak berhasil ditambahkan

  # TC-ADM-05
  Scenario: Notifikasi sukses muncul setelah berhasil menambah admin
    Given pengguna sudah login sebagai Admin
    And modal Add Admin sudah terbuka
    And pengguna telah mengisi email valid "newadmin@mail.ugm.ac.id"
    When pengguna menekan tombol "Add"
    Then notifikasi sukses muncul di layar
    And modal Add Admin tertutup
