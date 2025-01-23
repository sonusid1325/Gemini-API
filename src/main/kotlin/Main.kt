import okhttp3.*
import io.github.cdimascio.dotenv.dotenv
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


fun gemini(query: String){
    val env = dotenv({
        directory = "."
        filename = ".env"
    })

    val dotenv = dotenv {
        directory = "."
        filename = ".env"
    }
    val apiKey = dotenv["GEMINI_API_KEY"]

    if (apiKey.isNullOrEmpty()) {
        println("Error: GEMINI_API_KEY is missing in .env file.")
        return
    }

    // API URL
    val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$apiKey"

    // JSON body
    val jsonBody = JSONObject()
        .put("contents", listOf(
            JSONObject()
                .put("parts", listOf(
                    JSONObject().put("text", "Explain how AI works")
                ))
        ))

    // Create OkHttp client
    val client = OkHttpClient()

    // Build request
    val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .addHeader("Content-Type", "application/json")
        .build()

}

fun main() {

}
