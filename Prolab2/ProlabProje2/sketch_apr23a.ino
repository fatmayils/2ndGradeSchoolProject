
#include <LiquidCrystal.h>
#include <EEPROM.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h> 
//bizim kasadaki paralarımızın adedini tutuyor.
struct paraAdedi{
  int beslik;
  int onluk;
  int yirmilik;
  int ellilik;
  int yuzluk;
}money;
//hizmet ve hizmet özelliklerini tutuyor
struct hizmetTurleri{
  int hizmetId;
  char hizmet[20];
  int kalanHizmetAdet;
  int hizmetFiyat;
}hizmetTuru[4];
//lcd için
LiquidCrystal lcd(8,7,6,5,4,3);
//butonların pinleri
int button1 = 13,button2=12,button3=11,button4=10,button5=9,button6=2;
int veri;
//ledlerin pinleri
int ledRed=A0,ledGreen=A5;
//kullanıcının yüklediği paralar ve adedi
int five=0,ten=0,twenty=0,fifty=0,hundred=0;
//para üstü adetleri
int given5=0,given10=0,given20=0,given50=0,given100=0;
//yüklenen para,yüklenen para,harcanan para,harcanan para
int  sum=0,sum2,sum3,control;
int firstButtons=1,secondButtons=1;
//yaptığımız işlem adedi
int service1=0,service2=0,service3=0,service4=0;
//para üstü
int change;
void setup() {
  Serial.begin(9600);
  lcd.begin(16, 2);
  lcd.print("PARA YUKLEYINIZ");
  
pinMode(ledRed, OUTPUT);
pinMode(ledGreen, OUTPUT);
/*money.beslik=5;
money.onluk=10;
money.yirmilik=20;
money.ellilik=50;
money.yuzluk=100;
hizmetTuru[0].hizmetId=1;
strcpy(hizmetTuru[0].hizmet,"kopukleme");
hizmetTuru[0].hizmetFiyat=30;
hizmetTuru[0].kalanHizmetAdet=15;

   hizmetTuru[1].hizmetId=2;
    strcpy(hizmetTuru[1].hizmet,"yikama");
    hizmetTuru[1].hizmetFiyat=50;
    hizmetTuru[1].kalanHizmetAdet=10;

        hizmetTuru[2].hizmetId=3;
        strcpy(hizmetTuru[2].hizmet,"durulama");
        hizmetTuru[2].hizmetFiyat=100;
        hizmetTuru[2].kalanHizmetAdet=5;

            hizmetTuru[3].hizmetId=4;
            strcpy(hizmetTuru[3].hizmet,"cilalama");
            hizmetTuru[3].hizmetFiyat=20;
            hizmetTuru[3].kalanHizmetAdet=50;
          */
          /*
EEPROM.put(0,money.beslik);
EEPROM.put(4,money.onluk);
EEPROM.put(8,money.yirmilik);
EEPROM.put(12,money.ellilik);
EEPROM.put(16,money.yuzluk);
int a=0;
EEPROM.put(20,hizmetTuru[0].hizmetId);
for(int i = 24; i <sizeof(hizmetTuru[0].hizmet); i++){
   EEPROM.write(i,hizmetTuru[0].hizmet[a]);
   a++;
}
EEPROM.put(40,hizmetTuru[0].hizmetFiyat);
EEPROM.put(44,hizmetTuru[0].kalanHizmetAdet);

 a=0;
EEPROM.put(48,hizmetTuru[0].hizmetId);
for(int i = 52; i <sizeof(hizmetTuru[1].hizmet); i++){
   EEPROM.write(i,hizmetTuru[1].hizmet[a]);
   a++;
}
EEPROM.put(64,hizmetTuru[1].hizmetFiyat);
EEPROM.put(68,hizmetTuru[1].kalanHizmetAdet);

a=0;
EEPROM.put(72,hizmetTuru[2].hizmetId);
for(int i = 76; i <sizeof(hizmetTuru[0].hizmet); i++){
   EEPROM.write(i,hizmetTuru[2].hizmet[a]);
   a++;
}
EEPROM.put(92,hizmetTuru[2].hizmetFiyat);
EEPROM.put(96,hizmetTuru[2].kalanHizmetAdet);

a=0;
EEPROM.put(102,hizmetTuru[3].hizmetId);
for(int i = 106; i <sizeof(hizmetTuru[3].hizmet); i++){
   EEPROM.write(i,hizmetTuru[3].hizmet[a]);
   a++;
}
EEPROM.put(120,hizmetTuru[3].hizmetFiyat);
EEPROM.put(124,hizmetTuru[3].kalanHizmetAdet);
*/
/*
EEPROM.update(0,20);
EEPROM.update(4,20);
EEPROM.update(8,10);
EEPROM.update(12,30);
EEPROM.update(16,5);
EEPROM.put(40,15);
EEPROM.put(44,30);
EEPROM.put(64,10);
EEPROM.put(68,50);
EEPROM.put(92,5);
EEPROM.put(96,100);
EEPROM.put(120,50);
EEPROM.put(124,20);
*/
money.beslik=EEPROM.get(0,veri);
money.onluk=EEPROM.get(4,veri);
money.yirmilik=EEPROM.get(8,veri);
money.ellilik=EEPROM.get(12,veri);
money.yuzluk=EEPROM.get(16,veri);
hizmetTuru[0].hizmetId=EEPROM.get(20,veri);
strcpy(hizmetTuru[0].hizmet,"kopukleme");
hizmetTuru[0].hizmetFiyat=EEPROM.get(40,veri);
hizmetTuru[0].kalanHizmetAdet=EEPROM.get(44,veri);

   hizmetTuru[1].hizmetId=EEPROM.get(48,veri);
    strcpy(hizmetTuru[1].hizmet,"yikama");
    hizmetTuru[1].hizmetFiyat=EEPROM.get(64,veri);
    hizmetTuru[1].kalanHizmetAdet=EEPROM.get(68,veri);

        hizmetTuru[2].hizmetId=EEPROM.get(72,veri);
        strcpy(hizmetTuru[2].hizmet,"durulama");
        hizmetTuru[2].hizmetFiyat=EEPROM.get(92,veri);
        hizmetTuru[2].kalanHizmetAdet=EEPROM.get(96,veri);

            hizmetTuru[3].hizmetId=EEPROM.get(102,veri);
            strcpy(hizmetTuru[3].hizmet,"cilalama");
            hizmetTuru[3].hizmetFiyat=EEPROM.get(120,veri);
            hizmetTuru[3].kalanHizmetAdet=EEPROM.get(124,veri);

lcd.setCursor(0,1);
lcd.print(money.beslik);
lcd.print(" ");
lcd.print(money.onluk);
lcd.print(" ");
lcd.print(money.yirmilik);
lcd.print(" ");
lcd.print(money.ellilik);
lcd.print(" ");
lcd.print(money.yuzluk);

}
//tıklanan buton numarası
int clickButton()
{
  
  if (  digitalRead(13) == HIGH) { 
      return 1;
   }
     if (  digitalRead(12) == HIGH) { 
      return 2;
   }
     if (  digitalRead(11) == HIGH) { 
   
      return 3;
   }
     if (  digitalRead(10) == HIGH) {  
       return 4;
   }
     if (  digitalRead(9) == HIGH) { 
   
      return 5;
   }
     if (  digitalRead(2) == HIGH) { 
      return 6;        
   }
}
//para yükleyen fonksiyon
void putMoney()
{
  if(clickButton()==1)
  {
  lcd.clear();
  lcd.print("5 tl yuklediniz");
     five=five+1;
lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
    delay(500);
     
  }
  if(clickButton()==2)
  {

   lcd.clear();
 lcd.print("10 tl yuklediniz");
      ten=ten+1; 
      lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
      delay(500);
  }
  if(clickButton()==3)
  {
      lcd.clear();
     lcd.print("20 tl yuklediniz");
      twenty=twenty+1;
   lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
      delay(500);
  }
  if(clickButton()==4)
  { 
      lcd.clear();
     lcd.print("50 tl yuklediniz");
      fifty= fifty+1;  
  lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
      delay(500);
  }
  if(clickButton()==5)
  {
      lcd.clear();
     lcd.print("100 tl yuklediniz");
      hundred=hundred+1;
   lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);;
      delay(500); 
  }
  if(clickButton()==6)
  {
    lcd.clear();
    sum=(five*5)+(ten*10)+(twenty*20)+(fifty*50)+(hundred*100);
    if(sum!=0){
      lcd.print(sum);  
      lcd.print(" tl yukledin");
      lcd.setCursor(0,1); 
      lcd.print("Hizmet Secin");
      delay(500);
      firstButtons=0;
      secondButtons=1;
      sum2=sum;
      services();
       //delay(500);
}
else{
  lcd.print("   !!!HATA!!!");
  lcd.setCursor(0,1);
  lcd.print("PARA YUKLEYINIZ");
}
      delay(500);
  }
}

void services()
{
  delay(500);
  lcd.clear();
   lcd.print("Hizmet Secin");
  lcd.setCursor(0,1);
lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);

  while(secondButtons==1){
   if(clickButton()==1)
  {
    lcd.clear();
    if(sum-hizmetTuru[0].hizmetFiyat>=0){
      if(hizmetTuru[0].kalanHizmetAdet>0){
        int a=0;
        for(int j = 20;j<sizeof(hizmetTuru[0].hizmet);j++){
    hizmetTuru[0].hizmet[a] = EEPROM.read(j);
    a++;
}
     lcd.print(hizmetTuru[0].hizmet);
     sum-=hizmetTuru[0].hizmetFiyat;
     service1++;
     hizmetTuru[0].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
     lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
     
  }
  if(clickButton()==2)
  {
  lcd.clear();
        if(sum-hizmetTuru[1].hizmetFiyat>=0){
      if(hizmetTuru[1].kalanHizmetAdet>0){
     lcd.print(hizmetTuru[1].hizmet);
     sum-=hizmetTuru[1].hizmetFiyat;
     service2++;
     hizmetTuru[1].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
     lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
  }
  if(clickButton()==3)
  {
    lcd.clear();
        if(sum-hizmetTuru[2].hizmetFiyat>=0){
      if(hizmetTuru[2].kalanHizmetAdet>0){
     lcd.print(hizmetTuru[2].hizmet);
     sum-=hizmetTuru[2].hizmetFiyat;
     service3++;
     hizmetTuru[2].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
     lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
  }
  if(clickButton()==4)
  {    lcd.clear();
        if(sum-hizmetTuru[3].hizmetFiyat>=0){
      if(hizmetTuru[3].kalanHizmetAdet>0){
     lcd.print(hizmetTuru[3].hizmet);
     sum-=hizmetTuru[3].hizmetFiyat;
     service4++;
     hizmetTuru[3].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
     lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
  }
  if(clickButton()==5)
  {
     lcd.clear();
     lcd.print("resetlendi");
     sum=sum2;
     hizmetTuru[0].kalanHizmetAdet+=service1;
     hizmetTuru[1].kalanHizmetAdet+=service2;
     hizmetTuru[2].kalanHizmetAdet+=service3;
     hizmetTuru[3].kalanHizmetAdet+=service4;
     service1=0;
     service2=0;
     service3=0;
     service4=0;
      delay(1000); 
      lcd.clear();
     lcd.print("Hizmet Secin");
      lcd.setCursor(0,1);
 lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
  }
  if(clickButton()==6)
  {
    int number = random(1,5);
     if(number==2){
      digitalWrite(ledRed,HIGH);
      delay(1000);
      digitalWrite(ledRed,LOW);
      lcd.clear();
     lcd.print("Girdiginiz Para");
     lcd.setCursor(0,1);
     lcd.print("Geri Verildi");
     int a=0;
     while(a==0)
     {
     if(clickButton()==5)
     {
       lcd.clear();
     lcd.print("resetlendi");
     hizmetTuru[0].kalanHizmetAdet+=service1;
     hizmetTuru[1].kalanHizmetAdet+=service2;
     hizmetTuru[2].kalanHizmetAdet+=service3;
     hizmetTuru[3].kalanHizmetAdet+=service4;
     service1=0;
     service2=0;
     service3=0;
     service4=0;
      delay(500); 
    a++;
     }
     }
      delay(500);
     }
     else{
      digitalWrite(ledGreen,HIGH);
      delay(1000);
      digitalWrite(ledGreen,LOW);
      money.beslik+=five;
      money.onluk+=ten;
      money.yirmilik+=twenty;
      money.ellilik+=fifty;
      money.yuzluk+=hundred;
      sum3=service1*hizmetTuru[0].hizmetFiyat+service2*hizmetTuru[1].hizmetFiyat+service3*hizmetTuru[2].hizmetFiyat+service4*hizmetTuru[3].hizmetFiyat;
      change=sum2-sum3;
       lcd.clear();
     lcd.print("Para ustu:");
     lcd.print(change);
      writeChange();
      delay(2000);  
EEPROM.update(0,money.beslik);
EEPROM.update(4,money.onluk);
EEPROM.update(8,money.yirmilik);
EEPROM.update(12,money.ellilik);
EEPROM.update(16,money.yuzluk);
money.beslik=EEPROM.get(0,veri);
money.onluk=EEPROM.get(4,veri);
money.yirmilik=EEPROM.get(8,veri);
money.ellilik=EEPROM.get(12,veri);
money.yuzluk=EEPROM.get(16,veri);
    
     }
     secondButtons=0;
     firstButtons=1;
     sum=0;
     sum2=sum;
     five=0;
     ten=0;
     twenty=0;
     fifty=0;
     hundred=0;
     delay(500); 
      lcd.clear();
        lcd.print("PARA YUKLEYINIZ");
        lcd.setCursor(0,1);
lcd.print(money.beslik);
lcd.print(" ");
lcd.print(money.onluk);
lcd.print(" ");
lcd.print(money.yirmilik);
lcd.print(" ");
lcd.print(money.ellilik);
lcd.print(" ");
lcd.print(money.yuzluk);
service1=0;
     service2=0;
     service3=0;
     service4=0;
  }
  }
}
void writeChange()
{
   control=change;
  if(change/100>0&&money.yuzluk>=change/100)
  {
    given100=change/100;
    change-=(given100*100);
  }
  else if(change/100>0&&money.yuzluk<change/100)
  {
    given100=money.yuzluk;
    change-=(given100*100);
  }
  if(change/50>0&&money.ellilik>=change/50)
  {
    given50=change/50;
    change-=(given50*50);
  }
  else if(change/50>0&&money.ellilik<change/50)
  {
    given50=money.ellilik;
    change-=(given50*50);
  }

  if(change/20>0&&money.yirmilik>=change/20)
  {
    given20=change/20;
    change-=(given20*20);
  }
  else if(change/20>0&&money.yirmilik<change/20)
  {
    given20=money.yirmilik;
    change-=(given20*20);
  }
   if(change/10>0&&money.onluk>=change/10)
  {
    given10=change/10;
    change-=(given10*10);
  }
  else if(change/10>0&&money.onluk<change/10)
  {
    given10=money.onluk;
    change-=(given10*10);
  }

   if(change/5>0&&money.beslik>=change/5)
  {
    given5=change/5;
    change-=(given5*5);
  }
  else if(change/5>0&&money.beslik<change/5)
  {
    given5=money.beslik;
    change-=(given5*5);
  }
 if(given5*5+given10*10+given20*20+given50*50+given100*100==control){
lcd.setCursor(0,1);
lcd.print(given100);
lcd.print(" ");
lcd.print(given50);
lcd.print(" ");
lcd.print(given20);
lcd.print(" ");
lcd.print(given10);
lcd.print(" ");
lcd.print(given5);
lcd.print(" ");
  money.yuzluk-=given100;
  money.ellilik-=given50;
  money.yirmilik-=given20;
  money.onluk-=given10;
  money.beslik-=given5;
  }
  else{
     money.beslik-=five;
      money.onluk-=ten;
      money.yirmilik-=twenty;
      money.ellilik-=fifty;
      money.yuzluk-=hundred;
      hizmetTuru[0].kalanHizmetAdet+=service1;
      hizmetTuru[1].kalanHizmetAdet+=service2;
      hizmetTuru[2].kalanHizmetAdet+=service3;
      hizmetTuru[3].kalanHizmetAdet+=service4;
      delay(500);
      lcd.clear();
      lcd.print("Yeterli bozuk yok");
      lcd.setCursor(0,1);
      lcd.print("Para iadesi");
  }

  given5=0;
  given10=0;
  given20=0;
  given50=0;
  given100=0;
}
void loop() {
   if(firstButtons!=0){
   putMoney();
  
   }
   
   }

   /*#include <LiquidCrystal.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h> 
//bizim kasadaki paralarımızın adedini tutuyor.
struct paraAdedi{
  int beslik;
  int onluk;
  int yirmilik;
  int ellilik;
  int yuzluk;
}money;
//hizmet ve hizmet özelliklerini tutuyor
struct hizmetTurleri{
  int hizmetId;
  char hizmet[20];
  int kalanHizmetAdet;
  int hizmetFiyat;
}hizmetTuru[4];
//lcd için
LiquidCrystal lcd(8,7,6,5,4,3);
//butonların pinleri
int button1 = 13,button2=12,button3=11,button4=10,button5=9,button6=2;
//ledlerin pinleri
int ledRed=A0,ledGreen=A5;
//kullanıcının yüklediği paralar ve adedi
int five=0,ten=0,twenty=0,fifty=0,hundred=0;
//para üstü adetleri
int given5=0,given10=0,given20=0,given50=0,given100=0;
//yüklenen para,yüklenen para,harcanan para,harcanan para
int  sum=0,sum2,sum3,control;
int firstButtons=1,secondButtons=1;
//yaptığımız işlem adedi
int service1=0,service2=0,service3=0,service4=0;
//para üstü
int change;
void setup() {
  Serial.begin(9600);
  lcd.begin(16, 2);
  lcd.print("PARA YUKLEYINIZ");
pinMode(ledRed, OUTPUT);
pinMode(ledGreen, OUTPUT);
money.beslik=0;
money.onluk=0;
money.yirmilik=10;
money.ellilik=30;
money.yuzluk=5;
lcd.setCursor(0,1);
lcd.print(money.beslik);
lcd.print(" ");
lcd.print(money.onluk);
lcd.print(" ");
lcd.print(money.yirmilik);
lcd.print(" ");
lcd.print(money.ellilik);
lcd.print(" ");
lcd.print(money.yuzluk);
hizmetTuru[0].hizmetId=1;
strcpy(hizmetTuru[0].hizmet,"kopukleme");
hizmetTuru[0].hizmetFiyat=30;
hizmetTuru[0].kalanHizmetAdet=15;

   hizmetTuru[1].hizmetId=2;
    strcpy(hizmetTuru[1].hizmet,"yikama");
    hizmetTuru[1].hizmetFiyat=50;
    hizmetTuru[1].kalanHizmetAdet=10;

        hizmetTuru[2].hizmetId=3;
        strcpy(hizmetTuru[2].hizmet,"durulama");
        hizmetTuru[2].hizmetFiyat=100;
        hizmetTuru[2].kalanHizmetAdet=5;

            hizmetTuru[3].hizmetId=4;
            strcpy(hizmetTuru[3].hizmet,"cilalama");
            hizmetTuru[3].hizmetFiyat=20;
            hizmetTuru[3].kalanHizmetAdet=50;

}
//tıklanan buton numarası
int clickButton()
{
  
  if (  digitalRead(13) == HIGH) { 
      return 1;
   }
     if (  digitalRead(12) == HIGH) { 
      return 2;
   }
     if (  digitalRead(11) == HIGH) { 
   
      return 3;
   }
     if (  digitalRead(10) == HIGH) {  
       return 4;
   }
     if (  digitalRead(9) == HIGH) { 
   
      return 5;
   }
     if (  digitalRead(2) == HIGH) { 
      return 6;        
   }
}
//para yükleyen fonksiyon
void putMoney()
{
  if(clickButton()==1)
  {
  lcd.clear();
  lcd.print("5 tl yuklendi");
     five=five+1;
     lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
    delay(500);
     
  }
  if(clickButton()==2)
  {

   lcd.clear();
  lcd.print("10 tl yuklendi");
      ten=ten+1; 
      lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
      delay(500);
  }
  if(clickButton()==3)
  {
      lcd.clear();
      lcd.print("20 tl yuklendi");
      twenty=twenty+1;
      lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
      delay(500);
  }
  if(clickButton()==4)
  { 
      lcd.clear();
      lcd.print("50 tl yuklendi");  
      fifty= fifty+1;  
      lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
      delay(500);
  }
  if(clickButton()==5)
  {
      lcd.clear();
      lcd.print("100 tl yuklendi");
      hundred=hundred+1;
      lcd.setCursor(0,1);
lcd.print(money.beslik+five);
lcd.print(" ");
lcd.print(money.onluk+ten);
lcd.print(" ");
lcd.print(money.yirmilik+twenty);
lcd.print(" ");
lcd.print(money.ellilik+fifty);
lcd.print(" ");
lcd.print(money.yuzluk+hundred);
      delay(500); 
  }
  if(clickButton()==6)
  {
    lcd.clear();
    sum=(five*5)+(ten*10)+(twenty*20)+(fifty*50)+(hundred*100);
    if(sum!=0){
      lcd.print(sum);  
      lcd.print(" tl yukledin");
      lcd.setCursor(0,1); 
      lcd.print("Hizmet Secin");
      delay(500);
      firstButtons=0;
      secondButtons=1;
      sum2=sum;
      services();
       //delay(500);
}
else{
  lcd.print("   !!!HATA!!!");
  lcd.setCursor(0,1);
  lcd.print("PARA YUKLEYINIZ");
}
      delay(500);
  }
}

void services()
{
  delay(500);
  lcd.clear();
   lcd.print("Hizmet Secin");
  lcd.setCursor(0,1);
lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);

  while(secondButtons==1){
   if(clickButton()==1)
  {
    lcd.clear();
    if(sum-hizmetTuru[0].hizmetFiyat>=0){
      if(hizmetTuru[0].kalanHizmetAdet>0){
     lcd.print(hizmetTuru[0].hizmet);
     sum-=hizmetTuru[0].hizmetFiyat;
     service1++;
     hizmetTuru[0].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
    lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
     
  }
  if(clickButton()==2)
  {
  lcd.clear();
        if(sum-hizmetTuru[1].hizmetFiyat>=0){
      if(hizmetTuru[1].kalanHizmetAdet>0){
     lcd.print(hizmetTuru[1].hizmet);
     sum-=hizmetTuru[1].hizmetFiyat;
     service2++;
     hizmetTuru[1].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
    lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
  }
  if(clickButton()==3)
  {
    lcd.clear();
        if(sum-hizmetTuru[2].hizmetFiyat>=0){
      if(hizmetTuru[2].kalanHizmetAdet>0){
     lcd.print(hizmetTuru[2].hizmet);
     sum-=hizmetTuru[2].hizmetFiyat;
     service3++;
     hizmetTuru[2].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
    lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
  }
  if(clickButton()==4)
  {    lcd.clear();
        if(sum-hizmetTuru[3].hizmetFiyat>=0){
      if(hizmetTuru[3].kalanHizmetAdet>0){
     lcd.print(hizmetTuru[3].hizmet);
     sum-=hizmetTuru[3].hizmetFiyat;
     service4++;
     hizmetTuru[3].kalanHizmetAdet-=1;
     }
     else{
      lcd.print("kota bitti");
     }
    }
    else{
      lcd.print("yetersiz bakiye");
    }
    lcd.setCursor(0,1);
    lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
    delay(500);
  }
  if(clickButton()==5)
  {
     lcd.clear();
     lcd.print("resetlendi");
     sum=sum2;
     hizmetTuru[0].kalanHizmetAdet+=service1;
     hizmetTuru[1].kalanHizmetAdet+=service2;
     hizmetTuru[2].kalanHizmetAdet+=service3;
     hizmetTuru[3].kalanHizmetAdet+=service4;
     service1=0;
     service2=0;
     service3=0;
     service4=0;
      delay(500); 
      lcd.clear();
     lcd.print("Hizmet Secin");
     lcd.setCursor(0,1);
 lcd.print(hizmetTuru[0].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[1].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[2].kalanHizmetAdet);
lcd.print(" ");
lcd.print(hizmetTuru[3].kalanHizmetAdet);
  }
  if(clickButton()==6)
  {
    int number = random(1,5);
     if(number==2){
      digitalWrite(ledRed,HIGH);
      delay(1000);
      digitalWrite(ledRed,LOW);
      lcd.clear();
     lcd.print("Girdiginiz Para");
     lcd.setCursor(0,1);
     lcd.print("Geri Verildi");
     int a=0;
     while(a==0)
     {
     if(clickButton()==5)
     {
       lcd.clear();
     lcd.print("resetlendi");
     hizmetTuru[0].kalanHizmetAdet+=service1;
     hizmetTuru[1].kalanHizmetAdet+=service2;
     hizmetTuru[2].kalanHizmetAdet+=service3;
     hizmetTuru[3].kalanHizmetAdet+=service4;
     service1=0;
     service2=0;
     service3=0;
     service4=0;
    a++;
     }
     }
 delay(500);
     }
     else{
       digitalWrite(ledGreen,HIGH);
      delay(1000);
      digitalWrite(ledGreen,LOW);
      money.beslik+=five;
      money.onluk+=ten;
      money.yirmilik+=twenty;
      money.ellilik+=fifty;
      money.yuzluk+=hundred;
      sum3=service1*hizmetTuru[0].hizmetFiyat+service2*hizmetTuru[1].hizmetFiyat+service3*hizmetTuru[2].hizmetFiyat+service4*hizmetTuru[3].hizmetFiyat;
      change=sum2-sum3;
       lcd.clear();
     lcd.print("Para ustu:");
     lcd.print(change);
      writeChange();
      delay(2000);
     }
     secondButtons=0;
     firstButtons=1;
     sum=0;
     sum2=sum;
     five=0;
     ten=0;
     twenty=0;
     fifty=0;
     hundred=0;
      delay(500); 
      lcd.clear();
        lcd.print("PARA YUKLEYINIZ");
        lcd.setCursor(0,1);
lcd.print(money.beslik);
lcd.print(" ");
lcd.print(money.onluk);
lcd.print(" ");
lcd.print(money.yirmilik);
lcd.print(" ");
lcd.print(money.ellilik);
lcd.print(" ");
lcd.print(money.yuzluk);
service1=0;
     service2=0;
     service3=0;
     service4=0;
        
  }
  }
}
void writeChange()
{
   control=change;
  if(change/100>0&&money.yuzluk>=change/100)
  {
    given100=change/100;
    change-=(given100*100);
  }
  else if(change/100>0&&money.yuzluk<change/100)
  {
    given100=money.yuzluk;
    change-=(given100*100);
  }
  if(change/50>0&&money.ellilik>=change/50)
  {
    given50=change/50;
    change-=(given50*50);
  }
  else if(change/50>0&&money.ellilik<change/50)
  {
    given50=money.ellilik;
    change-=(given50*50);
  }

  if(change/20>0&&money.yirmilik>=change/20)
  {
    given20=change/20;
    change-=(given20*20);
  }
  else if(change/20>0&&money.yirmilik<change/20)
  {
    given20=money.yirmilik;
    change-=(given20*20);
  }
   if(change/10>0&&money.onluk>=change/10)
  {
    given10=change/10;
    change-=(given10*10);
  }
  else if(change/10>0&&money.onluk<change/10)
  {
    given10=money.onluk;
    change-=(given10*10);
  }

   if(change/5>0&&money.beslik>=change/5)
  {
    given5=change/5;
    change-=(given5*5);
  }
  else if(change/5>0&&money.beslik<change/5)
  {
    given5=money.beslik;
    change-=(given5*5);
  }
 if(given5*5+given10*10+given20*20+given50*50+given100*100==control){
lcd.setCursor(0,1);
lcd.print(given100);
lcd.print(" ");
lcd.print(given50);
lcd.print(" ");
lcd.print(given20);
lcd.print(" ");
lcd.print(given10);
lcd.print(" ");
lcd.print(given5);
lcd.print(" ");
  money.yuzluk-=given100;
  money.ellilik-=given50;
  money.yirmilik-=given20;
  money.onluk-=given10;
  money.beslik-=given5;
  }
  else{
     money.beslik-=five;
      money.onluk-=ten;
      money.yirmilik-=twenty;
      money.ellilik-=fifty;
      money.yuzluk-=hundred;
      hizmetTuru[0].kalanHizmetAdet+=service1;
      hizmetTuru[1].kalanHizmetAdet+=service2;
      hizmetTuru[2].kalanHizmetAdet+=service3;
      hizmetTuru[3].kalanHizmetAdet+=service4;
      delay(500);
      lcd.clear();
      lcd.print("Yeterli bozuk yok");
      lcd.setCursor(0,1);
      lcd.print("Para iadesi");
  }

  given5=0;
  given10=0;
  given20=0;
  given50=0;
  given100=0;
}
void loop() {
   if(firstButtons!=0){
   putMoney();
  
   }
   
   }
   */
