package com.example.mc_assignment_question1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mc_assignment_question1.ui.theme.Mc_assignment_Question1Theme
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : ComponentActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mc_assignment_Question1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherApp()
                }
            }
        }
    }

    @Composable
    fun WeatherApp() {
        var date by remember { mutableStateOf("") }
        val maxTemp = remember { mutableStateOf("") }
        val minTemp = remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Enter date (YYYY-MM-DD)") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        fetchWeatherData(date, maxTemp, minTemp)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fetch Weather")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Maximum Temperature: ${maxTemp.value}")
            Text("Minimum Temperature: ${minTemp.value}")
        }
    }

    private suspend fun fetchWeatherData(date: String, maxTemp: MutableState<String>, minTemp: MutableState<String>) {
        val location = "New York"
        val apiKey = "a9b77d33deae14a5955856cedb7c5daa" // Replace with your actual API key
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$location&appid=$apiKey&units=metric"
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                maxTemp.value = "N/A"
                minTemp.value = "N/A"
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonData = response.body?.string()
                if (jsonData != null) {
                    val jsonObject = JSONObject(jsonData)
                    val weather = jsonObject.getJSONObject("main")
                    if (weather != null) {
                        maxTemp.value = weather.getDouble("temp_max").toString()
                        minTemp.value = weather.getDouble("temp_min").toString()
                    } else {
                        maxTemp.value = "N/A"
                        minTemp.value = "N/A"
                    }
                }
            }
        })
    }
}