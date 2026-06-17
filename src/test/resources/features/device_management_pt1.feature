# ============================================================
# Feature  : Device Management Bagian 1 + Peak Performance Summary
# PIC      : Muhammad Zidan Alhilali
# TC IDs   : TC-DS-01, TC-DS-02, TC-DS-03, TC-DS-04,
#            TC-DS-05, TC-DS-06, TC-DS-07,
#            TC-PEAK-01, TC-PEAK-02, TC-PEAK-03, TC-PEAK-04
# Jumlah   : 11 TC
# ============================================================

@device_pt1
Feature: Device Management (Tampilan, Tambah, Filter, Search) dan Peak Performance

  Background:
    Given pengguna sudah login sebagai Admin
    And pengguna berada di halaman dashboard

  # ----------------------------------------------------------
  # MODUL: Device Management – Tampilan, Tambah, Filter, Search
  # ----------------------------------------------------------

  # TC-DS-01
  Scenario: Tampilan halaman Devices
    When pengguna menekan tombol "Devices" di navbar
    Then halaman Devices berhasil ditampilkan
    And halaman menampilkan total jumlah device terdaftar
    And halaman menampilkan jumlah device per status
    And halaman menampilkan tombol "Add Device"
    And halaman menampilkan filter OS
    And halaman menampilkan kolom pencarian

  # TC-DS-03
  Scenario: Gagal menambah device karena form tidak lengkap
    Given pengguna berada di halaman Devices
    When pengguna menekan tombol "Add Device"
    And pengguna membiarkan salah satu field form kosong
    And pengguna menekan tombol "Add"
    Then field yang kosong ditandai dengan warna merah sebagai validasi error

  # TC-DS-02
  Scenario: Menambah device baru dengan data lengkap
    Given pengguna berada di halaman Devices
    When pengguna menekan tombol "Add Device"
    And pengguna mengisi nama device dengan "Server Lab A"
    And pengguna memilih OS "Linux"
    And pengguna memasukkan email "admin@mail.ugm.ac.id"
    And pengguna menekan tombol "Add"
    Then muncul pesan sukses penambahan device
    And device baru "Server Lab A" muncul dalam daftar device

  # TC-DS-04
  Scenario Outline: Filter device berdasarkan OS yang tersedia
    Given pengguna berada di halaman Devices
    And terdapat device dengan berbagai jenis OS
    When pengguna memilih filter OS "<os>"
    Then halaman menampilkan semua device dengan OS "<os>"

    Examples:
      | os      |
      | Windows |
      | Linux   |

  # TC-DS-05
  Scenario: Filter OS dengan hasil kosong menampilkan pesan error
    Given pengguna berada di halaman Devices
    And tidak ada device dengan OS "macOS" yang terdaftar
    When pengguna memilih filter OS "macOS"
    Then halaman menampilkan pesan bahwa tidak ada device ditemukan

  # TC-DS-06
  Scenario: Mencari device yang terdaftar menggunakan kolom pencarian
    Given pengguna berada di halaman Devices
    And terdapat device dengan nama "PC Lab 1"
    When pengguna mengetikkan "PC Lab 1" pada kolom pencarian
    Then halaman menampilkan device dengan nama "PC Lab 1"

  # TC-DS-07
  Scenario: Mencari device yang tidak terdaftar
    Given pengguna berada di halaman Devices
    When pengguna mengetikkan "sbfsdbfdbf" pada kolom pencarian
    Then halaman menampilkan pesan bahwa tidak ada device ditemukan

  # ----------------------------------------------------------
  # MODUL: Peak Performance Summary
  # ----------------------------------------------------------

  # TC-PEAK-01
  Scenario: Nilai peak RAM ditampilkan di dashboard
    Given pengguna berada di halaman dashboard
    When pengguna melihat bagian performance summary
    Then nilai peak RAM ditampilkan dengan satuan yang benar

  # TC-PEAK-02
  Scenario: Nilai peak CPU ditampilkan di dashboard
    Given pengguna berada di halaman dashboard
    When pengguna melihat bagian performance summary
    Then nilai peak CPU ditampilkan dengan satuan yang benar

  # TC-PEAK-03
  Scenario: Nilai peak Disk/Storage ditampilkan di dashboard
    Given pengguna berada di halaman dashboard
    When pengguna melihat bagian performance summary
    Then nilai peak Storage ditampilkan dengan satuan yang benar

#  # TC-PEAK-04
  Scenario: Nilai peak ter-update sesuai filter yang dipilih
    Given pengguna berada di halaman dashboard
    When pengguna mengganti pilihan filter waktu
    Then nilai peak RAM berubah sesuai filter baru
    And nilai peak CPU berubah sesuai filter baru
    And nilai peak Storage berubah sesuai filter baru
