/*Incluimos primero la librería*/
#include <WiFi.h>
#include "DHT.h"
#define DHTTYPE DHT11   
#define DHTPIN 23
const int humsuelo = 33;
int valHumsuelo;
const char* ssid = "TP-Link_33A2";
const char* pass = "14642814";
DHT dht(DHTPIN, DHTTYPE);
void setup() {
  // put your setup code here, to run once:
 Serial.begin(115200);
 Serial.println("DHTxx test!");
 WiFi.begin(ssid, pass);
 pinMode(humsuelo, INPUT);
 dht.begin();
}

void loop() {
  delay(2000);
   double h = dht.readHumidity();
   double t = dht.readTemperature();
   Serial.print("Se está conectando a la red WiFi denominada ");
  Serial.println(ssid);
  /*Mientras se realiza la conexión a la red, aparecerán puntos, hasta que se realice la conexión*/
  while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }
  /*Con la conexión ya realizada, se pasa a imprimir algunos datos importantes, como la dirección IP asignada a nuestro dispositivo*/
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
   if (isnan(h) || isnan(t)) {
      Serial.println("Failed to read from DHT sensor!");
      return;
   }
   Serial.println("Humidity: ");
   Serial.println(h);
   Serial.print(" %\t");
   Serial.println("Temperature: ");
   Serial.println(t);
   Serial.print(" *C ");
   valHumsuelo = map(analogRead(humsuelo),4092,0,0,100);
  Serial.println("Humedad del suelo");
  Serial.println(valHumsuelo);
  Serial.println("%");
}
