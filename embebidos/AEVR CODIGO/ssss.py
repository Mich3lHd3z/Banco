from machine import Pin
import utime

# Configura el pin del LED
led1 = Pin(15, Pin.OUT)
led2 = Pin(14, Pin.OUT)
led3 = Pin(13, Pin.OUT)
led4 = Pin(12, Pin.OUT)

# Bucle infinito
while True:
    # Enciende el LED
    led1.value(1)
    led2.value(1)                                      
    led3.value(1)
    led4.value(1)
    # Espera durante 2 segundos
    utime.sleep(2)
    # Apaga el LED
    led1.value(0)
    led2.value(0)
    led3.value(0)
    led4.value(0)
    # Espera durante 2 segundos antes de reiniciar el ciclo
   
    utime.sleep(2)