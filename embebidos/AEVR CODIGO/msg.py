import socket
import time

s = socket.socket()
print("Por Conectarse al servidor")
s.connect(("192.168.1.80",2023))
print("Conectado al Servidor")

Continuar = True
while Continuar:
    a = input("Digite el mensaje a enviar: ")
    s.send(a.encode()) # Tenemos que mandarle Bites
    # .encode() = string a bites
    # .decode() = bites a string

    if a == "z":
        Continuar = False

print("Fin de Programa")
s.close()