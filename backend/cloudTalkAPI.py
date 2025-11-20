import requests
from requests.auth import HTTPBasicAuth
import psycopg2


#beginDateInput = input("Select begin date for search: (format: YYYY-MM-DD) ")
#fechaFinInput = input("Select end date for search: (format: YYYY-MM-DD) ")
beginDateInput = "2025-11-19"
endDateInput = "2025-11-20"
date_from = ""
date_to = ""
hour = " 00:00:00"
list = []

#Conexion with BD
connection = psycopg2.connect(
    host="localhost",
    port="5432",
    database="calls", 
    username = "admin",
    password= "admin123"
)

print("Connected to DB")
cur = connection.cursor()

url = "https://my.cloudtalk.io/api/calls/index.json?date_from=" + beginDateInput + hour + "&date_to=" + endDateInput + hour
username = "KUFFMEIH6AUAQ6XIKLPRM"
password = "?GbGZ8oPvDB9XwT78B1.2V.x%jQW@7jmA50?piF5"

response = requests.get(url, auth=HTTPBasicAuth(username, password))

print(response.status_code)
#print(response.json())
data = response.json()


for item in data["responseData"]["data"]:
    
    if item["Agent"]["id"] is not None:
        id = item["Agent"]["id"]
        print(id)
        name = item['Agent']['firstname']+ ' ' + item['Agent']['lastname']
        print(name)
        insertPrompt = "INSERT INTO users (id, name) VALUES ('" +id +  "', '" + name + "');"
        print(insertPrompt)
        try:
            cur.execute(insertPrompt)
            connection.commit()
        except Exception as e:
            print("Could not send. Error: " + str(e))

connection.close()
