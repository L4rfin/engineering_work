import socket
import RPi.GPIO as GPIO
import time

# Ustawienia GPIO
GPIO.setmode(GPIO.BCM)  # Używamy numeracji BCM
LED_ON = GPIO.HIGH      # Stan, w którym LED jest włączona
LED_OFF = GPIO.LOW      # Stan, w którym LED jest wyłączona

# Piny, do których podłączone są diody LED
LED_OK_PIN = 17
LED_FAIL_PIN = 27

# Ustawienia pinów jako wyjścia
GPIO.setup(LED_OK_PIN, GPIO.OUT)
GPIO.setup(LED_FAIL_PIN, GPIO.OUT)

# Funkcja do sprawdzania dostępności usługi na porcie
def check_service(host, port):
    try:
        # Tworzymy gniazdo i próbujemy połączyć się z usługą
        with socket.create_connection((host, port), timeout=5):
            return True
    except (socket.timeout, socket.error):
        return False

# Główna logika
def main():
    while True:
        # Sprawdzamy, czy usługa jest dostępna na porcie 8080
        if check_service('127.0.0.1', 8080):  # Adres lokalny i port 8080
            GPIO.output(LED_OK_PIN, LED_ON)    # Zapal LED OK
            GPIO.output(LED_FAIL_PIN, LED_OFF) # Zgaś LED FAIL
            print("true")
        else:
            GPIO.output(LED_OK_PIN, LED_OFF)   # Zgaś LED OK
            GPIO.output(LED_FAIL_PIN, LED_ON)  # Zapal LED FAIL
            print("false")
        
        # Odczekaj chwilę przed ponownym sprawdzeniem
        time.sleep(1)

try:
    main()
except KeyboardInterrupt:
    print("Program przerwany.")
finally:
    GPIO.cleanup()  # Upewniamy się, że piny GPIO są zwolnione po zakończeniu programu
