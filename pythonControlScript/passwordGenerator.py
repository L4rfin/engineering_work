import random
import string
import os


def generate_password():
    desktop = os.path.expanduser("~/Desktop/data/config/config.txt")
    f = open(desktop, "w")
    print("password generator")
    password = ''.join(random.choice(string.ascii_uppercase + string.ascii_lowercase + string.digits) for _ in range(7))
    f.write(password)
    f.close()
    return password
