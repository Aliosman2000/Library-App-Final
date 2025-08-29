Kullanıcı girişi için kullanici adi : aliyilmaz şifre : 12345
Admin Girişi için kullanici adi : aysedemir şifre : 5678


KÜTÜPHANE YÖNETİM SİSTEMİ

Bu proje, kütüphane kaynaklarını ve kullanıcılarını çok rollü bir sistem aracılığıyla yönetmek için tasarlanmış kapsamlı bir Android uygulamasıdır. Hem yöneticiler hem de normal kullanıcılar için farklı erişim seviyeleri sağlayarak, güvenli kullanıcı kimlik doğrulaması, rol tabanlı yetkilendirme ve yerel bir Oda Veritabanı kullanarak güçlü veri yönetimi özellikleri sunar.

Temel Özellikler
Yönetici Paneli

Kapsamlı Üye Yönetimi: Yönetici, tüm kullanıcı hesaplarını ekleme, kaldırma, güncelleme ve listeleme konusunda tam CRUD (Oluşturma, Okuma, Güncelleme, Silme) yetkilerine sahiptir. Bu yetkiye, kullanıcı adları ve parolalar gibi kullanıcı kimlik bilgilerini değiştirme yetkisi de dahildir.

Kitap Yönetimi: Yönetici, kitap kataloğunda tam CRUD işlemlerini gerçekleştirebilir:

Kitap girişlerini ekleyin, güncelleyin ve silin .

Kütüphane koleksiyonundaki tüm kitapları listeleyin .

Gelişmiş Kitap Arama: Yönetici, kitap veritabanında yazara, kategoriye veya başlığa göre arama yapabilir.

Kullanıcı Paneli

Okuduklarım: Kullanıcılar okudukları kitapları kişisel listeye ekleyebilirler.

Kişisel Kitap Yönetimi: Kullanıcılar kendi listelerinde My Bookskitap ekleme, silme ve listeleme gibi CRUD ayrıcalıklarına sahiptir.

Kullanıcı Düzeyinde Arama: Kullanıcılar kitapları yazara, kategoriye veya başlığa göre arayabilir.

Kullanılan Teknolojiler
Dil: Kotlin

Platform: Android SDK

Veritabanı: Room Veritabanı (yerel verilerin verimli bir şekilde kalıcılığı için)

Mimari: MVVM (Model-Görünüm-GörünümModeli) - eğer varsa

Kütüphaneler: Android Jetpack kütüphaneleri

Yapı Sistemi: Gradle

Kurulum ve Kullanım
Bu projeyi açıp çalıştırabilmeniz için Android Studio'ya ihtiyacınız olacak .

Depoyu klonlayın:

Bash

git clone https://github.com/your-username/your-repo-name.git
Projeyi açın:

Android Studio'yu başlatın ve karşılama ekranından Aç'ı seçin.

Klonlanmış proje klasörüne gidin ve Tamam'a tıklayın .

Uygulamayı çalıştırın:

Bir Android cihazı bağlayın veya bir emülatör başlatın.

Uygulamayı derlemek ve yüklemek için Android Studio'daki "Çalıştır" butonuna tıklayın .
