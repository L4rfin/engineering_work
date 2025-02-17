import requests
from gpiozero import LED
from time import sleep


def service_check():
    # red_led = LED(17)
    # green_led = LED(27)
    # red_led.on()
    # green_led.off()
    while True:
        try:
            # Wysłanie żądania HTTP do localhost:8080
            response = requests.get("http://localhost:8080", timeout=5)
            if response.status_code == 200:
                # Jeśli strona jest aktywna, zapal zielonego LED-a, zgaś czerwonego
                # red_led.off()
                # green_led.on()
                print("work")
            else:
                # Jeśli odpowiedź nie jest 200, zapal czerwonego, zgaś zielonego
                # red_led.on()
                # green_led.off()
                print("dont work")
        except requests.ConnectionError:
            # W przypadku błędu połączenia zapal czerwonego, zgaś zielonego
            # red_led.on()
            # green_led.off()
            print("connection error")
        except requests.Timeout:
            # W przypadku przekroczenia czasu oczekiwania zapal czerwonego, zgaś zielonego
            # red_led.on()
            # green_led.off()
            print("timeout error")

        # Czekaj 5 sekund przed kolejną próbą
        sleep(5)
