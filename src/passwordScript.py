import os
import random
import string
import board
import digitalio
import busio
from rpi_lcd import LCD


def generate_password():
    length = random.randint(4, 7)  # Długość hasła od 4 do 7 znaków
    characters = string.ascii_letters + string.digits  # Litery i cyfry
    password = ''.join(random.choice(characters) for _ in range(length))
    return password


config_path = os.path.expanduser("~/Desktop/data/config/config.txt")  # Ścieżka do pliku

# Tworzenie katalogu, jeśli nie istnieje
os.makedirs(os.path.dirname(config_path), exist_ok=True)


def save_password(password_new: str):
    with open(config_path, "w") as file:
        file.write(password_new)
    print(f"Hasło zapisane w: {config_path}")


# --- 3. Wyświetlanie hasła na LCD podłączonym do ADS1115 ---
# Parametry wyświetlacza LCD 16x2

def show_password_on_lcd(password_new: str):
    lcd = LCD()
    try:
        lcd.text("haslo:",1)
        lcd.text(password_new,2)
    except KeyboardInterrupt:
        pass


# Press the green button in the gutter to run the script.

if __name__ == '__main__':
    password = generate_password()
    save_password(password)
    show_password_on_lcd(password)
