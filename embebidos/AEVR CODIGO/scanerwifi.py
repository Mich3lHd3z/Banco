import network

# Creamos nuestro objeto y lo ponemos en modo Estación
wf = network.WLAN(network.STA_IF)
wf.active(True)

# Realizamos el escaneo de las redes
lista = wf.scan()

# Imprimimos las redes formateadas
for redes in lista:
    ssid = redes[0].decode("utf-8")  # Convertimos el SSID a una cadena legible
    bssid = ":".join(f"{x:02x}" for x in redes[1])  # Convertimos la dirección BSSID a un formato legible
    rssi = redes[3]  # Obtenemos la potencia de señal RSSI

    print(f"SSID: {ssid}")
    print(f"BSSID: {bssid}")
    print(f"Potencia de señal (RSSI): {rssi} dBm")
    print("-------------------------------\n")