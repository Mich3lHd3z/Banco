from machine import PWM,Pin
import utime

serv = PWM(Pin(18))
serv.freq(50)

whil = True
while whil:
    
    puerta = input('Puerta: ')
    if puerta == 'P':
        PuertaP()

        
    
        
def PuertaP():
    angulo = input('op: ')
    if angulo == 'A':
        dc = 1638.375 + 34.5 * 180
        serv.duty_u16(int(dc))
    if angulo == 'C':
        dc = 1638.375 + 34.5 * 80
        serv.duty_u16(int(dc))
    
            
        
       
       
       
       
       
       
