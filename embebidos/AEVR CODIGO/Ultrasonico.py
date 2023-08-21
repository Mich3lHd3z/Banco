from machine import Pin
import utime

trig = Pin(19,Pin.OUT)
echo = Pin(18,Pin.IN)

while True:
    
    trig.value(1)
    utime.sleep_us(10)
    trig.value(0)      # en estas 3 lineas, le indicamos que mande la onda sonora
    
    t1 = utime.ticks_us()
    t2 = 0
    
    while echo.value() == 0:
        t1 = utime.ticks_us()
        
    while echo.value() == 1:
        t2 = utime.ticks_us()
    
    t = t2-t1
    d = 17*t/1000
    
    print(d,' cms')
    
    utime.sleep_ms(500)
    
    if (d > 10):
        print('HOla')