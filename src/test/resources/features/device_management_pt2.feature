  # ============================================================
  # Feature  : Device Management Bagian 2 + Export CSV + Log Event
  # PIC      : Muhammad Arrofii Faiz
  # TC IDs   : TC-DS-08, TC-DS-09, TC-DS-10, TC-DS-11,
  #            TC-DS-12, TC-DS-13, TC-DS-14,
  #            TC-EXP-01, TC-EXP-02,
  #            TC-LDE-01, TC-LDE-02, TC-LDE-03
  # Jumlah   : 12 TC
  # ============================================================
@device
Feature: Detail Device, Edit, Hapus, Export CSV, dan Log Event

  Background:
    Given pengguna sudah login sebagai Admin
    And pengguna berada di halaman Devices

  # ----------------------------------------------------------
  # MODUL: Device Management – Detail, Edit, Hapus
  # ----------------------------------------------------------

  # TC-DS-08
  @device
  Scenario Outline: Filter Time Series pada halaman detail device
    Given pengguna membuka halaman detail device "ropi"
    When pengguna memilih filter Time Series "<filter>"
    Then grafik performa menampilkan data sesuai rentang waktu "<filter>"

    Examples:
      | filter   |
      | 1 Hours   |
      | 12 Hours |
      | 1 Day |

  # TC-DS-09
  @device
  Scenario: Melihat detail device dengan status Online
    Given terdapat device "ropi" dengan status "Online"
    When pengguna memilih device "ropi" dari daftar
    Then halaman detail device ditampilkan
    And halaman menampilkan filter time series
    And halaman menampilkan tombol "Download CSV"
    And halaman menampilkan performance summary
    And halaman menampilkan tombol delete
    And grafik RAM, CPU, dan Disk ditampilkan dalam keadaan aktif
    And tabel logs ditampilkan dalam keadaan aktif

  # TC-DS-10
  @device
  Scenario: Melihat detail device dengan status Pending
    Given terdapat device "Server Lab A" dengan status "Pending"
    When pengguna memilih device "Server Lab A" dari daftar
    Then halaman detail device ditampilkan
    And grafik dan tabel logs ditampilkan dalam keadaan "Pending"

  # TC-DS-11
  @device
  Scenario: Melihat detail device dengan status Offline
    Given terdapat device "jindan-lin" dengan status "Offline"
    When pengguna memilih device "jindan-lin" dari daftar
    Then halaman detail device ditampilkan
    And grafik dan tabel logs ditampilkan dalam keadaan "Offline"

  # TC-DS-12
  @device
  Scenario: Download file CSV data grafik dari halaman detail device
    Given pengguna membuka halaman detail device "ropi" dengan status "Online"
    When pengguna menekan tombol "Download CSV" di samping filter Time Series
    Then proses download langsung berjalan

  # TC-DS-13
  @device
  Scenario: Edit nama device
    Given pengguna membuka halaman detail device "Laptop Memet"
    When pengguna menekan tombol "Edit" di dekat nama device
    And pengguna memasukkan nama device baru "PC Lab 1 Updated"
    And pengguna mengkonfirmasi perubahan
    Then nama device langsung berganti menjadi "PC Lab 1 Updated"

  # TC-DS-14
  @device
  Scenario: Hapus device dari sistem
    Given pengguna membuka halaman detail device "PC Lab 1 Updated"
    When pengguna menekan tombol "Delete"
    Then muncul pop-up konfirmasi penghapusan
    When pengguna menekan tombol "Delete" pada pop-up konfirmasi
    Then muncul notifikasi bahwa device berhasil dihapus
    And device "PC Lab 1 Updated" tidak lagi muncul dalam daftar

  # ----------------------------------------------------------
  # MODUL: Export CSV
  # ----------------------------------------------------------

  # TC-EXP-01
  @device
  Scenario: Download CSV dari halaman dashboard
    Given pengguna berada di halaman dashboard
    When pengguna menekan tombol "Download CSV"
    Then proses download file CSV langsung berjalan

  # ----------------------------------------------------------
  # MODUL: Log Event
  # ----------------------------------------------------------

  # TC-LDE-01
  @device
  Scenario: Tabel log event ditampilkan di dashboard
    Given pengguna berada di halaman dashboard
    When pengguna scroll ke bagian log event
    Then tabel log event berhasil ditampilkan

  # TC-LDE-02
  @device
  Scenario: Isi log event memuat aktivitas create dan update
    Given pengguna berada di halaman dashboard
    And tabel log event telah ditampilkan
    Then tabel menampilkan event bertipe "update" dan "delete"
    And setiap event memuat informasi waktu kejadian

  # TC-LDE-03
  @device
  Scenario: Log event dapat difilter berdasarkan waktu
    Given pengguna berada di halaman dashboard
    And tabel log event telah ditampilkan
    When pengguna mengganti filter waktu pada log event
    Then data pada tabel log event berubah sesuai filter yang dipilih