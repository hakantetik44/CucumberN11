# 🛍️ N11 E-Ticaret Test Otomasyon Projesi

## 📋 Genel Bakış
Bu proje, N11 e-ticaret platformu için Cucumber BDD framework'ü, Selenium WebDriver ve Java kullanılarak oluşturulmuş bir test otomasyon projesidir. Page Object Model tasarım desenini takip eder ve kullanıcı kimlik doğrulama ile ürün yönetimi için çeşitli test senaryoları içerir.

## 🔧 Teknoloji Stack'i
- ☕ Java
- 🌐 Selenium WebDriver 4.15.0
- 🥒 Cucumber Framework
- 🧪 JUnit
- 📦 Maven
- 🚗 WebDriverManager 5.6.2

## 📁 Proje Yapısı
```
CucumberN11/
├── src/
│   └── test/
│       ├── java/
│       │   ├── pages/         # Sayfa Nesneleri
│       │   ├── runner/        # Test Koşturucuları
│       │   ├── stepdefinitions/ # Adım Tanımlamaları
│       │   └── utilities/     # Yardımcı Sınıflar
│       └── resources/
│           ├── features/      # Cucumber Özellik Dosyaları
│           └── configuration.properties
├── pom.xml                    # Maven Bağımlılıkları
└── README.md
```

## ⭐ Temel Özellikler
- 📝 Cucumber ile BDD yaklaşımı
- 🎯 Page Object Model implementasyonu
- ⚙️ Properties dosyası ile yapılandırılabilir test ortamı
- 🌐 Otomatik tarayıcı yönetimi
- 📊 Detaylı loglama
- 🧹 Temiz ve sürdürülebilir test kodu yapısı

## 🎯 Test Senaryoları
Proje şu anda aşağıdaki test senaryolarını içermektedir:

1. 🔐 Kullanıcı Kimlik Doğrulama
   - N11 ana sayfasına gitme
   - Sayfa yüklemesini doğrulama
   - Geçerli kimlik bilgileriyle giriş yapma
   - Başarılı girişi doğrulama

2. 🛒 Ürün Yönetimi (Geliştirme Aşamasında)
   - Ürün arama fonksiyonalitesi
   - Sepete ürün ekleme
   - Sepet yönetimi

## 📋 Ön Gereksinimler
- ☕ Java JDK 11 veya üstü
- 📦 Maven
- 🌐 Chrome Tarayıcı (en son sürüm)
- 🚗 ChromeDriver (WebDriverManager tarafından otomatik yönetilir)

## 🚀 Kurulum Adımları
1. Projeyi klonlayın:
   ```bash
   git clone [repository-url]
   ```

2. Bağımlılıkları yükleyin:
   ```bash
   mvn clean install
   ```

3. Test özelliklerini yapılandırın:
   - `src/test/resources/configuration.properties` dosyasını test bilgilerinizle güncelleyin
   ```properties
   browser=chrome
   urlN11=https://www.n11.com/
   email=[test-emailiniz]
   sifre=[test-sifreniz]
   ```

## ▶️ Testleri Çalıştırma
### Tüm testleri çalıştırma
```bash
mvn clean test
```

### Belirli etiketleri çalıştırma
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## 🔍 Proje Bileşenleri

### 1. 🛠️ Yardımcı Araçlar
- `Driver.java`: WebDriver başlatma ve yapılandırma yönetimi
- `ConfigReader.java`: Properties dosyası işlemleri

### 2. 📄 Sayfa Nesneleri
- `N11page.java`: N11 web sitesi sayfaları için element konumlandırıcılar ve metodlar

### 3. 📝 Adım Tanımlamaları
- `N11step.java`: Özellik dosyalarında tanımlanan test adımlarının implementasyonu
- `Hooks.java`: Kurulum ve temizleme metodları

### 4. 🏃 Test Koşturucusu
- `TestRunner.java`: Cucumber testlerinin yapılandırması ve çalıştırılması

## ✨ Uygulanan En İyi Pratikler
- 🔄 WebDriver'ın lazy initialization'ı
- 🔒 Thread-safe sürücü yönetimi
- ⚡ Uygun exception handling
- ⏱️ Daha iyi senkronizasyon için explicit wait'ler
- 🧹 Temiz kod prensipleri
- 📊 Hata ayıklama için detaylı loglama

## 📊 Raporlama
Proje, test çalıştırması sonrasında aşağıdakileri içeren detaylı Cucumber raporları oluşturur:
- Test çalıştırma özeti
- Adım adım test detayları
- Başarısız testler için ekran görüntüleri
- Çalıştırma süresi ve durumu

## 🔧 Sorun Giderme
Yaygın sorunlar ve çözümleri:
1. Tarayıcı sürüm uyumsuzluğu
   - Çözüm: ChromeDriver'ı güncelleyin veya WebDriverManager'ın otomatik güncelleme özelliğini kullanın

2. Element bulunamama hataları
   - Çözüm: Uygun wait stratejilerini uygulayın
   - Element konumlandırıcıları kontrol edin
   - Sayfa yükleme tamamlanmasını doğrulayın

## 👥 Katkıda Bulunma
1. Repository'yi fork edin
2. Feature branch'inizi oluşturun
3. Değişikliklerinizi commit edin
4. Branch'e push yapın
5. Yeni bir Pull Request oluşturun

## 🔄 Bakım
- Bağımlılıkları düzenli olarak güncelleyin
- Test verilerini güncel tutun
- Test senaryolarını gözden geçirin ve güncelleyin
- Düzgün dokümantasyonu koruyun

## 📄 Lisans
[Lisansınızı belirtin]

## 📞 İletişim
[İletişim bilgileriniz]
