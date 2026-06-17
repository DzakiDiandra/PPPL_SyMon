# DOKUMEN PEMBAGIAN JOBDESK

## Pengujian Perangkat Lunak

**SyMon — Sistem Monitoring Server, Log, dan Manajemen Data**  
IntelliJ IDEA · Java · Gherkin · Cucumber · JUnit  
Universitas Gadjah Mada  
26 Mei 2026

---

# 1. Informasi Proyek

| Item | Keterangan |
|--------|--------|
| Nama Sistem | SyMon (Sistem Monitoring Server, Log, dan Manajemen Data) |
| Institusi | Universitas Gadjah Mada |
| Framework Pengujian | IntelliJ IDEA · Java · Gherkin · Cucumber · JUnit |
| Jumlah Anggota | 4 Orang |
| Total Test Case | 45 TC dari 8 Modul (Sprint 1–Sprint 3) |
| Tanggal Dokumen | 26 Mei 2026 |

---

# 2. Ringkasan Pembagian

| No | Nama | Cakupan Modul | TC | Sprint |
|----|------|---------------|----|--------|
| 1 | M. Dzaki Diandra Putra | Login & Autentikasi + Role Middleware + Admin Management | 12 | Sprint 1 & 3 |
| 2 | Muhammad Zidan Alhilali | Device Management Bagian 1 + Peak Performance Summary | 11 | Sprint 2 & 3 |
| 3 | Muhammad Arrofii Faiz | Device Management Bagian 2 + Export CSV + Log Event | 12 | Sprint 2 & 3 |
| 4 | Matthew Hayunaji Priantara | Dashboard Utama + Filter Waktu + Device Summary + Log Performa | 10 | Sprint 3 |

---

# 3. Detail Jobdesk Per Anggota

## 3.1 M. Dzaki Diandra Putra

### Tanggung Jawab
- Login & Autentikasi (TC-SUP-01 s/d TC-SUP-06)
- Role Middleware (TC-RM-01)
- Admin Management (TC-ADM-01 s/d TC-ADM-05)
- Setup project IntelliJ, CucumberRunner.java, dan struktur folder

### File yang Dibuat
- login.feature
- LoginSteps.java
- RoleMiddlewareSteps.java
- AdminManagementSteps.java
- CucumberRunner.java

---

## 3.2 Muhammad Zidan Alhilali

### Tanggung Jawab
- Tampilan halaman Devices (TC-DS-01)
- Menambah device baru & validasi form (TC-DS-02, TC-DS-03)
- Filter OS device (TC-DS-04, TC-DS-05)
- Pencarian device (TC-DS-06, TC-DS-07)
- Peak Performance Summary (TC-PEAK-01 s/d TC-PEAK-04)

### File yang Dibuat
- device_management_part1.feature
- DeviceManagementSteps.java (bagian 1)
- PeakPerformanceSteps.java

---

## 3.3 Muhammad Arrofii Faiz

### Tanggung Jawab
- Filter Time Series & detail device per status (TC-DS-08 s/d TC-DS-11)
- Download CSV dari detail device (TC-DS-12)
- Edit dan hapus device (TC-DS-13, TC-DS-14)
- Export CSV dari dashboard (TC-EXP-01, TC-EXP-02)
- Log Event (TC-LDE-01 s/d TC-LDE-03)

### File yang Dibuat
- device_management_part2_export.feature
- DeviceManagementSteps.java (bagian 2)
- ExportSteps.java
- LogEventSteps.java

---

## 3.4 Matthew Hayunaji Priantara

### Tanggung Jawab
- Dashboard utama: tampilan & grafik monitoring (TC-DSH-01, TC-DSH-02)
- Filter waktu harian, mingguan, dan auto-update (TC-FLT-01 s/d TC-FLT-03)
- Device Quick Summary (TC-DQS-01, TC-DQS-02)
- Log Performa (TC-LDP-01 s/d TC-LDP-03)

### File yang Dibuat
- dashboard_logs_admin.feature
- DashboardSteps.java
- FilterSteps.java
- DeviceSummarySteps.java
- LogPerformaSteps.java

---

# 4. Struktur Folder Project

```text
symon-testing/
├── pom.xml
├── src/test/java
│   ├── runner
│   │   └── CucumberRunner.java
│   └── steps
│       ├── LoginSteps.java
│       ├── RoleMiddlewareSteps.java
│       ├── AdminManagementSteps.java
│       ├── DeviceManagementSteps.java
│       ├── PeakPerformanceSteps.java
│       ├── ExportSteps.java
│       ├── LogEventSteps.java
│       ├── DashboardSteps.java
│       ├── FilterSteps.java
│       ├── DeviceSummarySteps.java
│       └── LogPerformaSteps.java
└── src/test/resources/features
    ├── login.feature
    ├── device_management_part1.feature
    ├── device_management_part2_export.feature
    └── dashboard_logs_admin.feature
```

---

# 5. Dependency Maven (pom.xml)

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
