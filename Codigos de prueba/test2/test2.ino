const int humsuelo = 33;
int valHumsuelo;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(humsuelo, INPUT);
  

}
void loop() {
  // put your main code here, to run repeatedly:
valHumsuelo = map(analogRead(humsuelo),4092,0,0,100);
Serial.print("Humedad del suelo");
Serial.print(valHumsuelo);
Serial.print("%");
delay(2000);
}
