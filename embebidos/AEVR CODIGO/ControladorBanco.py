import network
import utime
import umqtt.simple as mqtt
import umqtt.simple as mqtt
from machine import PWM, Pin



# Puertas
P1 = PWM(Pin(16))
P2 = PWM(Pin(17))
P1.freq(50)
P2.freq(50)

V1 = PWM(Pin(18))
V2 = PWM(Pin(19))
V1.freq(50)
V2.freq(50)

B = PWM(Pin(20))
B.freq(50)

S = PWM(Pin(21))
S.freq(50)

# Calculo angulo
def Abrir():
    dc = 1638.375 + 34.5 * 195
    return dc

def Cerrar():
    dc = 1638.375 + 34.5 * 90
    return dc
    
# Puertas
def Bobeda(angulo):
    if angulo == 'A':
        B.duty_u16(int(Abrir()))
        FocoB('E')
    elif angulo == 'C':
        FocoB('A')
        B.duty_u16(int(Cerrar()))

def PuertaV1(angulo):
    if angulo == 'A':
        V1.duty_u16(int(Abrir()))
    elif angulo == 'C':
        V1.duty_u16(int(Cerrar()))

def PuertaV2(angulo):
    if angulo == 'A':
        V2.duty_u16(int(Abrir()))
    elif angulo == 'C':
        V2.duty_u16(int(Cerrar()))

def PuertaP(angulo):
    if angulo == 'A':
        P1.duty_u16(int(Abrir()))
        dc2 = 1638.375 + 34.5 * 0
        P2.duty_u16(int(dc2))
    elif angulo == 'C':
        P1.duty_u16(int(Cerrar()))
        P2.duty_u16(int(Cerrar()))

def PuertaS(angulo):
    if angulo == 'A':
        S.duty_u16(int(Abrir()))
    elif angulo == 'C':
        S.duty_u16(int(Cerrar()))
        
# Focos

F1 = Pin(15, Pin.OUT)
F2 = Pin(14, Pin.OUT)
F3 = Pin(13, Pin.OUT)
F4 = Pin(12, Pin.OUT)

def FocoP(Ac):
    if Ac == 'A':
        F4.value(1)
    elif Ac == 'E':
        F4.value(0)
        
def FocoV(Ac):
    if Ac == 'A':
        F2.value(1)
    elif Ac == 'E':
        F2.value(0)
                    
def FocoPl(Ac):
    if Ac == 'A':
        F3.value(1)
    elif Ac == 'E':
        F3.value(0)
                    
def FocoB(Ac):
    if Ac == 'A':
        F1.value(1)
    elif Ac == 'E':
        F1.value(0)


##########################################################
# Funcion Susbcriptor
        
# Funcion Susbcriptor
def receptor(topic, msg):
    
    global estado_ventilador
    global estado_Luz_Vestidor

    Mensaje = msg.decode() # Mensaje Luz_Principal
    
    print('Instruccion: ', Mensaje) # Imprimimos Mensaje Luz_Principal 
    
    try:
        values = Mensaje.split('_')

        if len(values) == 3:
            OP, Cat, Ac = values

            print('Instruccion Parametros:')
            print('OP:', OP)
            print('Categoria:', Cat)            
            print('Accion:', Ac)

            if OP == 'P':
                if Cat == 'P':
                    PuertaP(Ac)
                elif Cat == 'S':
                    PuertaS(Ac)
                elif Cat == 'V1':
                    PuertaV1(Ac)
                elif Cat == 'V2':
                    PuertaV2(Ac)
                elif Cat == 'B':
                    Bobeda(Ac)
                    
            if OP == 'F':
                if Cat == 'P':
                    FocoP(Ac)
                elif Cat == 'PL': 
                    FocoPl(Ac)
                elif Cat == 'V':
                    FocoV(Ac)
                    
            elif OP == 'I':
                 if Cat == 'IOT':
                     Bobeda(Ac)
                
        else:
            print('Error: Debe ingresar exactamente 3 valores separados por _.')
            print(codigo_convertido)
    except ValueError:
        print('Error: Ingrese exactamente 3 valores separados por _.')


    
    
##########################################################













# Creamos nuestro objeto y lo ponemos en modo Estación
wf = network.WLAN(network.STA_IF)
wf.active(True)

# wf.connect(SSID, PASSWORD)
wf.connect("INFINITUM97BD_2.4", "T4kTQUYkH2")

# Esperamos a que nos conectemos
while not wf.isconnected():
    print(".")
    utime.sleep(1)

print("Conectado al Wifi!!!\n")

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

# Objeto
c = mqtt.MQTTClient("Pico01", "18.118.188.12")

# Definimos la variable mensaje_recibido antes del bucle
mensaje_recibido = None

c.set_callback(receptor)
c.connect()
c.subscribe('DEMO')



while True:
    c.wait_msg() 
        
        

