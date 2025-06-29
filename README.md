# Historical Weather Viewer App

This Android app allows users to **retrieve historical weather data** for any date and location using a free weather API. The user can input a **specific date and year**, and the app will display the **maximum and minimum temperatures** recorded on that day.

---

## ✨ Features

- Select or enter a location (city or coordinates)
- Choose any historical date
- Fetch weather data using a free API
- Display maximum and minimum temperatures
- Easy-to-use Kotlin UI with date picker
- Handles API errors and invalid inputs gracefully

---

## 🔧 Technologies Used

- **Kotlin (Android App Development)**
- **Retrofit2** or **Volley** (HTTP Networking)
- **Gson** or **Kotlinx.serialization** (JSON Parsing)
- **Free Weather API** ([Open-Meteo.com](https://open-meteo.com/))

---

## 📲 How It Works

1. **User Input:**
   - User selects a date via a date picker
   - User enters a location (e.g., "New York")

2. **API Request:**
   - The app sends a GET request to the weather API for historical data

3. **JSON Response:**
   - The app parses the response to extract `max_temp`, `min_temp`, and other optional metrics

4. **Display Results:**
   - The temperatures are shown in the UI in Celsius or Fahrenheit
