import network
import utime
import usocket
from machine import Pin
import micropython

#----------------------------------------------------------------------------------------
led = Pin(18, Pin.OUT)
Led_Conectando = Pin(19, Pin.OUT)
Led_Respuesta_Server = Pin(20, Pin.OUT)
#----------------------------------------------------------------------------------------

#----------------------------------------------------------------------------------------
# Creamos nuestro objeto y lo ponemos en modo Estación
wf = network.WLAN(network.STA_IF)
wf.active(True)
wf.connect("INFINITUM97BD_2.4", "T4kTQUYkH2")

# Esperamos ha que nos conectemos
while not wf.isconnected():
    print(".")
    Led_Conectando.value(1)
    utime.sleep(.5)
    Led_Conectando.value(0)
    utime.sleep(1)

print("Conectado al Wifi!!!\n")
Led_Conectando.value(1)

# Obtenemos la configuración de red actual 
config = wf.ifconfig()

# Imprimimos la configuración de red
ip_address = config[0]

print("Configuración de red:")
print(f"Dirección IP: {ip_address}")


# Configuramos nuestro Socket
# Creamos nuestro opjeto
s = usocket.socket()

# Ponemos nuestras funciones
# s.bind(("IP",PUERTO))
s.bind(("192.168.1.80",2023))
s.listen(10)
led.value(0)
print("\nSocket creado, Esperando Conexiones...")
Led_Respuesta_Server.value(1)

# Creamos a un cliente(sc), le ponemos su direccion(addr)
(sc,addr) = s.accept() # NOTA: NO va a salir de esta parte hasta que un cliente se conecte
print(f"Cliente conectado, IP: {addr}")

# Leemos los mensajes
continuar = True
while continuar:
    Led_Respuesta_Server.value(0)
    utime.sleep(.5)
    Led_Respuesta_Server.value(1)
    utime.sleep(1)
    
    dato = sc.recv(32).decode()
    print(dato)
    
    if dato == "C":
        Led_Respuesta_Server.value(0)
        Led_Conectando.value(0)
        continuar = False
        
    elif dato == "A":
        led.value(1)
        #led.on()
        
    elif dato == "D":
        led.value(0)
        #led.off()
#----------------------------------------------------------------------------------------
print("Fin de Programa")
sc.close()
s.close()