import os
import random
import string
import board
import digitalio
import busio
import adafruit_character_lcd.character_lcd_i2c as character_lcd


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
    lcd_columns = 16
    lcd_rows = 2

    # Inicjalizacja I2C (dla Raspberry Pi)
    i2c = busio.I2C(board.SCL, board.SDA)

    # Inicjalizacja wyświetlacza LCD
    lcd = character_lcd.Character_LCD_I2C(i2c, lcd_columns, lcd_rows)

    # Wyczyszczenie ekranu i wyświetlenie hasła
    lcd.clear()
    lcd.message = f"Haslo:\n{password}"
    print("Hasło wyświetlone na LCD.")


# Press the green button in the gutter to run the script.

if __name__ == '__main__':
    password = generate_password()
    save_password(password)
    show_password_on_lcd(password)
