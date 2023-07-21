# Librerias
from tkinter import Tk
from tkinter import Button
import socket
import time

# Librerias para diseño
from tkinter import *
from tkinter import ttk

def prender():
    print("Presiono prender")
    s.send("A".encode())

def apagar():
    print("Presiono Apagar")
    s.send("D".encode())

def cerrar():
    print("Presiono Cerrar")
    s.send("D".encode())
    time.sleep(0.5)
    s.send("C".encode())
    s.close()
    Ventana.destroy()

# Objeto
Ventana = Tk()
Ventana.protocol("WM_DELETE_WINDOW", cerrar) # Bloquear el botón de cerrar (X)
Ventana.resizable(False, False) # Bloquear la capacidad de redimensionar la ventana
Ventana.geometry("400x100") # Tamaño Ventana (Largo x Ancho)

style = ttk.Style()
style.configure("TButton",
                padding=6,             # Relleno
                relief="flat",         # Relieve
                background="#4CAF50",  # Color de fondo
                foreground="#000000",  # Color de texto
                font=("Arial", 12))    # Fuente

# Ventana, Texto, Funcion
b1 = ttk.Button(Ventana, text="Prender", command=prender, style="TButton")
b2 = ttk.Button(Ventana, text="Apagar", command=apagar, style="TButton")
b3 = ttk.Button(Ventana, text="Cerrar", command=cerrar, style="TButton")
s = socket.socket()
print("Por Conectarse al servidor")


b1.place(x=15, y=20)
b2.place(x=143, y=20)
b3.place(x=270, y=20)
s.connect(("192.168.1.80", 2023))
print("Conectado al Servidor")

# Funciones
Ventana.mainloop()  # Bucle, siempre poner al final