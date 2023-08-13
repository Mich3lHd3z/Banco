from machine import PWM, Pin
import utime

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
    elif angulo == 'C':
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
    if angulo == 'A'
        S.duty_u16(int(Abrir()))
    elif angulo == 'C':
        S.duty_u16(int(Cerrar()))
        
# Focos
F1 = Pin(22, Pin.OUT)
F2 = Pin(23, Pin.OUT)
F3 = Pin(24, Pin.OUT)
F4 = Pin(25, Pin.OUT)


def FocoP(Ac):
    if Ac == 'A'
        F1.value(1)
    elif Ac == 'C':
        F1.value(0)
        
def FocoV(Ac):
    if Ac == 'A'
        F2.value(1)
    elif Ac == 'C':
        F2.value(0)
                    
def FocoPl(Ac):
    if Ac == 'A'
        F3.value(1)
    elif Ac == 'C':
        F3.value(0)
                    
def FocoPO(Ac):
    if Ac == 'A'
        F4.value(1)
    elif Ac == 'C':
        F4.value(0)


#Ultra



def PsOP():
    try:
        input_str = input('Ingrese 2 las opciones (Opcion)(_)(Categoria)(_)(Ac[A,C]) : ')
        values = input_str.split('_')

        if len(values) == 2:
            OP, Cat, Ac = values

            print('Valores ingresados:')
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
                    
            elif OP == 'F':
                if Cat == 'P':
                    FocoP(Ac)
                elif Cat == 'V':
                    FocoV(Ac)
                elif Cat == 'PL':
                    FocoPl(Ac)
                elif Cat == 'PO':
                    FocoPO(Ac)
            
            elif OP == 'U':
                 if Cat == 'P':
                    UltraP(Ac)
                elif Cat == 'PL':
                    UltraPL(Ac)
            
            elif OP == 'I':
                
            
        else:
            print('Error: Debe ingresar exactamente 3 valores separados por _.')
    except ValueError:
        print('Error: Ingrese exactamente 3 valores separados por _.')

while True:
    print('Opcion [P][F][U][I]')
    
    print('Opcion [P] Puerta')
    print('Ventanilla principal [V1]:')
    print('Ventanilla Pasillo [V2]:')
    print('Principal [P]:')
    print('Salida [S]:')
    print('Bobeda [B]:')
    
    print('Opcion [F] Foco')
    
    print('Opcion [U] Ultrasonico')
    
    print('Opcion [I] BOvEDA')
    print('Encender [I_IOT_E]')
    print('Apagar [I_IOT_A]')
    
    PsOP()

