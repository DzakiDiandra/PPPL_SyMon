# ============================================================
# Feature  : Dashboard, Filter Waktu, Device Summary, Log Performa
# PIC      : Matthew Hayunaji Priantara
# TC IDs   : TC-DSH-01, TC-DSH-02,
#            TC-FLT-01, TC-FLT-02, TC-FLT-03,
#            TC-DQS-01, TC-DQS-02,
#            TC-LDP-01, TC-LDP-02, TC-LDP-03
# Jumlah   : 10 TC
# ============================================================

@dashboard
Feature: Dashboard Monitoring, Filter Waktu, Device Summary, dan Log Performa


  Background:
    Given pengguna sudah login ke sistem SyMon

  # ----------------------------------------------------------
  # MODUL: Dashboard Utama
  # ----------------------------------------------------------

  # TC-DSH-01
  @dashboard
  Scenario: Membuka halaman dashboard berhasil
    When pengguna masuk ke halaman dashboard
    Then halaman dashboard berhasil ditampilkan
    And tidak ada pesan error yang muncul

  # TC-DSH-02
  @dashboard
  Scenario: Grafik monitoring ditampilkan di halaman dashboard
    Given pengguna berada di halaman dashboard
    When pengguna mengamati bagian grafik pada halaman dashboard
    Then grafik RAM ditampilkan
    And grafik CPU ditampilkan
    And grafik Harddisk/Storage ditampilkan

  # ----------------------------------------------------------
  # MODUL: Filter Waktu
  # ----------------------------------------------------------

  # TC-FLT-01
  Scenario: Filter monitoring berdasarkan hari tertentu
    Given pengguna berada di halaman dashboard
    When pengguna memilih filter harian untuk tanggal tertentu
    Then grafik menampilkan data sesuai hari yang dipilih

  # TC-FLT-02
  Scenario: Filter monitoring berdasarkan mingguan
    Given pengguna berada di halaman dashboard
    When pengguna memilih filter "Weekly"
    Then grafik menampilkan data selama 1 minggu terakhir

  # TC-FLT-03
  Scenario: Seluruh grafik ter-update otomatis ketika filter diganti
    Given pengguna berada di halaman dashboard
    When pengguna mengganti pilihan filter
    Then seluruh grafik RAM, CPU, dan Storage ter-update sesuai filter baru
    And tidak perlu melakukan refresh halaman

  # ----------------------------------------------------------
  # MODUL: Device Quick Summary
  # ----------------------------------------------------------

  # TC-DQS-01
  Scenario: Total device ditampilkan di bagian summary dashboard
    Given pengguna berada di halaman dashboard
    When pengguna melihat bagian device summary
    Then total jumlah device yang terdaftar ditampilkan

  # TC-DQS-02
  Scenario: Status masing-masing device ditampilkan di summary
    Given pengguna berada di halaman dashboard
    When pengguna melihat bagian device summary
    Then jumlah device dengan status "Online" ditampilkan
    And jumlah device dengan status "Offline" ditampilkan
    And jumlah device dengan status "Pending" ditampilkan

  # ----------------------------------------------------------
  # MODUL: Log Performa Device
  # ----------------------------------------------------------

  # TC-LDP-01
  Scenario: Tabel log performa ditampilkan di dashboard
    Given pengguna berada di halaman dashboard
    When pengguna scroll ke bagian log performa
    Then tabel log performa berhasil ditampilkan

  # TC-LDP-02
  Scenario: Isi log performa memuat data yang sesuai
    Given pengguna berada di halaman dashboard
    And tabel log performa telah ditampilkan
    When pengguna memeriksa kolom pada tabel log performa
    Then tabel log performa memuat kolom data CPU, RAM, Storage, dan timestamp

  # TC-LDP-03
  Scenario: Log performa dapat difilter berdasarkan waktu
    Given pengguna berada di halaman dashboard
    And tabel log performa telah ditampilkan
    When pengguna mengganti filter waktu pada log performa
    Then data pada tabel log performa berubah sesuai filter yang dipilih
