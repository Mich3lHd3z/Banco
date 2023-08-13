import network
import utime
import umqtt.simple as mqtt
from machine import Pin

##########################################################
# Funcion Susbcriptor
def receptor(topic,msg):
    m = msg.decode()
    print(m)
    if m == 'A':
        led.on()
    elif m == 'B':
        led.off()

##########################################################

# Creamos nuestro objeto y lo ponemos en modo Estación
wf = network.WLAN(network.STA_IF)
wf.active(True)

# wf.connect(SSID, PASSWORD)
wf.connect("INFINITUM97BD_2.4", "T4kTQUYkH2")


# Esperamos ha que nos conectemos
while not wf.isconnected(): # Lo ejecutamos mienstras no estemos conectados
    print(".")
    utime.sleep(1)

print("Conectado al Wifi!!!\n")

# Obtenemos la configuración de red actual
#print(wf.ifconfig())

# o tambien de esta forma
# --------------------------------------------------------------------------- #

# Obtenemos la configuración de red actual
config = wf.ifconfig()

# Imprimimos la configuración de red
ip_address = config[0]
subnet_mask = config[1]
gateway = config[2]
dns_servers = config[3]

print("Configuración de red:")
print(f"Dirección IP: {ip_address}")
print(f"Máscara de subred: {subnet_mask}")
print(f"Puerta de enlace (Gateway): {gateway}")
print(f"Servidores DNS: {dns_servers}")



########################################

led = Pin(18, Pin.OUT)
# Objeto
c = mqtt.MQTTClient("Pico02","192.168.1.70")

c.set_callback(receptor)
c.connect()
c.subscribe('demo')

print('Esperando Mensajes...')
while True:
    c.wait_msg()