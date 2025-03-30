NarPOS Test Otomasyonu - Cucumber & Selenium

Bu proje, NarPOS sisteminin Giriş Testleri, Stok Kartı Yönetimi ve Fatura Yönetimi süreçlerini Cucumber ve Selenium kullanarak test etmek için hazırlanmıştır.

📌 Proje İçeriği

Bu proje aşağıdaki test senaryolarını kapsamaktadır:

Giriş Testleri

Stok Kartı Yönetimi (CRUD İşlemleri)

Fatura Yönetimi (Ekle & Sil)

🚀 Kurulum

1️⃣ Gereksinimler

Projenin çalışması için aşağıdaki araçların sisteminizde yüklü olması gerekmektedir:

Java 11 veya üstü

Maven

IntelliJ IDEA (veya Eclipse)

Google Chrome & ChromeDriver

2️⃣ Projeyi Klonlayın

git clone https://github.com/Bdoganayy/NarPos
cd narpos-cucumber

3️⃣ Gerekli Bağımlılıkları Yükleyin

mvn clean install

4️⃣ Testleri Çalıştırın

mvn test

📂 Proje Yapısı

📦 narpos-cucumber
┣ 📂 src
┃ ┣ 📂 main
┃ ┃ ┣ 📂 java
┃ ┃ ┃ ┗ 📂 pages ➝ Sayfa nesne modeli (POM) sınıfları
┃ ┣ 📂 test
┃ ┃ ┣ 📂 java
┃ ┃ ┃ ┣ 📂 stepDefinitions ➝ Cucumber adımları
┃ ┃ ┃ ┣ 📂 runners ➝ Test runner sınıfı
┃ ┃ ┃ ┗ 📂 utilities ➝ WebDriver ve yardımcı metodlar
┃ ┃ ┣ 📂 resources
┃ ┃ ┃ ┣ 📂 features ➝ Cucumber feature dosyaları
┃ ┃ ┃ ┗ 📜 config.properties ➝ Test yapılandırmaları
┗ 📜 pom.xml ➝ Proje bağımlılıkları

🔹 Test Senaryoları

1️⃣ Giriş Testleri

✅ narcost.narpos.com.tr adresine git

✅ Hatalı giriş testi:

E-posta: wrongmail@narpos.com.tr

Şifre: wrongpassword

"Giriş Yap" butonuna tıkla ve uyarı mesajını doğrula

✅ Başarılı giriş testi:

E-posta: testuser@narpos.com.tr

Şifre: 123456

"Giriş Yap" butonuna tıkla ve URL'nin anasayfa içerdiğini doğrula

2️⃣ Stok Kartı Yönetimi (CRUD İşlemleri)

✅ Stok listesine git

✅ "DOMATES" stok kartını sil (Eğer varsa)

✅ Yeni bir "DOMATES" stok kartı ekle

✅ Eklenen stok kartını düzenle ve değişiklikleri kontrol et

✅ Stok kartını sil ve gerçekten silindiğini kontrol et

✅ Aynı stok kartını tekrar ekleyerek hatasız çalıştığını test et

3️⃣ Fatura Yönetimi (Ekle & Sil)

✅ "Alış Faturası" sayfasına git ve URL'nin purchase-invoice içerdiğini doğrula

✅ Yeni fatura ekle ve bilgileri doğrula

✅ Eklenen faturayı sil ve silindiğini kontrol et

🏃 Testlerin Çalıştırılması

Tüm Testleri Çalıştırma

mvn test

Belirli Feature Dosyalarını Çalıştırma

mvn test -Dcucumber.options="src/test/resources/features/giris.feature"

Belirli Etiketleri Çalıştırma

mvn test -Dcucumber.options="--tags @StokYonetimi"

📊 Test Raporları

Testler çalıştırıldıktan sonra raporları aşağıdaki dizinde bulabilirsiniz:

target/cucumber-reports.html

Chrome ile açarak test sonuçlarını görüntüleyebilirsiniz.