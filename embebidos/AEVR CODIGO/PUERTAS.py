from machine import PWM, Pin
import utime

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

def Abrir():
    dc = 1638.375 + 34.5 * 195
    return dc

def Cerrar():
    dc = 1638.375 + 34.5 * 90
    return dc
    

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
    if angulo == 'A':
        dc = 1638.375 + 34.5 * 180
        S.duty_u16(int(dc))
    elif angulo == 'C':
        S.duty_u16(int(Cerrar()))

def PsOP():
    try:
        input_str = input('Ingrese 2 las opciones (Puerta)(,)(Opcion[A,C]) : ')
        values = input_str.split(',')

        if len(values) == 2:
            puerta, OP = values
            # Si deseas que los valores sean enteros, puedes usar:
            # puerta, OP = map(int, values)

            print('Valores ingresados:')
            print('Puerta:', puerta)
            print('OP:', OP)

            if puerta == 'P':
                PuertaP(OP)
            elif puerta == 'S':
                PuertaS(OP)
            elif puerta == 'V1':
                PuertaV1(OP)
            elif puerta == 'V2':
                PuertaV2(OP)
            elif puerta == 'B':
                Bobeda(OP)
        else:
            print('Error: Debe ingresar exactamente 3 valores separados por comas.')
    except ValueError:
        print('Error: Ingrese exactamente 3 valores separados por comas.')

while True:
    print('Ventanilla principal [V1]:')
    print('Ventanilla Pasillo [V2]:')
    print('Principal [P]:')
    print('Salida [S]:')
    print('Bobeda [B]:')
    PsOP()

