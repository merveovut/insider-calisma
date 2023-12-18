# Web Automation

Selenium kullanılarak hazırlanmış bir web otomasyon projesidir.

### Projede Junit, Selenium, WebDriverManager, Extent Report kullanılmıştır.

### Sınıf Tanımlamaları

* `test/Scenarios`: Senaryo testlerinin bulunduğu Before ve After anotasyonlarının yönetildiği sınıftır.
* `hook/Hook`: Before ve After anotasyonlarının içerisinde yönetilen method tanımları Hook sınıfı içerisinde tanımlanmıştır.
* `listener/Watcher`: Junit ile senaryo pass, fail, skip, aborted durumlarını kontrol edebilmek
  ve rapora pass fail durumlarını yansıtabilmek için oluşturulmuş bir sınıftır.
* `base/StepImpl`: Selenium methodlarının bulunduğu generic methodlar ile oluşturulmuş sınıftır.
* `driver/DriverManager`: Browser Tipi ve Browser Optionslar ile driverın ayağa kaldırıldığı sınıftır.
* `browser/BrowserType`: Browser Tiplerinin default isimlendirmelerinin bulunduğu enum sınıftır.
* `browser/BrowserOptions`: Browserin hangi optionslar ile ayağa kaldırılacağının bulunduğu sınıftır.
* `helper/Configuration`: web-settings.properties içindeki parametrelerin ayarlandığı sınıftır.
* `helper/ConfigurationHelper`: Configuration sınıfındaki parametrelere erişebilmek 
   için instancenın kullanıldığı sınıftır.
* `helper/MethodCall`: Rapora hangi methodun çağırıldığı bilgisini ekleyebilmek için tanımlanmış
  hangi method içerisinde çağırılırsa, çağırılan method ismini veren sınıftır.
* `report/ExtentReporter`: Proje içerisinde kullanılan raporlama tanımlarının bulunduğu sınıftır.
* `pages`: Sayfa elementlerinin ve methodlarının tanımlandığı pakettir.
  ### Paket Sınıfları
  * `pages/PageCareers`
  * `pages/PageHome`
  * `pages/PageLeverForm`


* `resources`:
  * `web-settings.properties`: Aşağıda örnek bir şablon bulunmaktadır.
    Browser Tipleri : chrome, firefox, edge, safari, ie

    - testUrl=https://useinsider.com/
    - browserType=chrome
    - headless=false
    - secretMode=true
    - implicitlyTimeOut=100
    - pageTimeOut=250
    - scriptTimeOut=500