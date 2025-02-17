import time
import board
import busio
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import matplotlib.pyplot as plt
import numpy as np

i2c = busio.I2C(board.SCL, board.SDA)
ads = ADS.ADS1115(i2c)
chan = AnalogIn(ads, ADS.P0)


def data_acquisition():
    with open("ekg_data.txt", "w") as file:
        print("Zbieranie danych... Naciśnij Ctrl+C, aby zakończyć.")

        try:
            while True:
                voltage = chan.voltage  # Odczyt napięcia w V
                file.write(f"{time.time()},{voltage}\n")  # Zapis do pliku (czas, napięcie)
                print(f"Czas: {time.time():.2f} s | Napięcie: {voltage:.4f} V")
                time.sleep(0.1)  # 100 ms próbkowania

        except KeyboardInterrupt:
            print("\nZakończono zbieranie danych.")


def data_show():
    data = np.loadtxt("ekg_data.txt", delimiter=",")
    czas = data[:, 0] - data[0, 0]  # Normalizacja czasu (start od 0)
    napiecie = data[:, 1]

    plt.plot(czas, napiecie, label="Sygnał EKG")
    plt.xlabel("Czas (s)")
    plt.ylabel("Napięcie (V)")
    plt.title("Sygnał EKG z AD8232")
    plt.legend()
    plt.show()


if __name__ == '__main__':
    data_acquisition()
    data_show()
