//librerias

#include <WiFi.h>
#include "FirebaseESP32.h"
#include "DHT.h"
//pines 

#define DHTTYPE DHT11   
#define DHTPIN 23
const int humsuelo = 33;

//variables entorno
int valHumsuelo;
const char* ssid = "TP-Link_33A2";
const char* pass = "14642814";

//credenciales proyecto Firebase
#define FIREBASE_HOST "ardu-cfdaa-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "120UVzgFFNp6gzjg4YrQvW0wA6gWDBZ2ya5I8iKR"

//Firebase Data Object
WiFiClient client; 
FirebaseData firebaseData;
String ruta = "Ejemplo_01";

DHT dht(DHTPIN, DHTTYPE);
 double h;
 double t;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
 Serial.println("DHTxx test!");
 WiFi.begin(ssid, pass);
 Serial.print("Se está conectando a la red WiFi denominada ");
 Serial.println(ssid);
  /*Mientras se realiza la conexión a la red, aparecerán puntos, hasta que se realice la conexión*/
  while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
 pinMode(humsuelo, INPUT);
 dht.begin();
 Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
 Firebase.reconnectWiFi(true);
   if (isnan(h) || isnan(t)) {
      Serial.println("Failed to read from DHT sensor!");
      return;
   }
  
}

void loop() {
  // put your main code here, to run repeatedly:
  delay(1000);
 h = dht.readHumidity();
 t = dht.readTemperature();
 Serial.println("Humedad: ");
   Serial.println(h);
   Serial.println(", Temperatura: ");
   Serial.println(t);
   Serial.println();
   valHumsuelo = map(analogRead(humsuelo),4092,0,0,100);
  Serial.println("Humedad del suelo");
  Serial.println(valHumsuelo);
  
Firebase.setInt(firebaseData, ruta+ "/Temperatura",t);
Firebase.setInt(firebaseData, ruta+ "/Humedad", h);  
Firebase.setInt(firebaseData, ruta+ "/Humedad Tierra",valHumsuelo);

}
