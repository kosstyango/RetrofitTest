package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    //    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = findViewById<TextView>(R.id.userName)
//        binding = ActivityMainBinding.inflate(LayoutInflater)
        setContentView(R.layout.activity_main)
////Создаем перехватчик
//        val interceptor = HttpLoggingInterceptor().apply {
//            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BASIC
//        }
////Создаем клиент и добавляем туда перехватчик
//        val okHttpCLient = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()
////Создаем объект Retrofit и добавляем кастомный клиент с перехватчиком
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://reqres.in/")
//            .addConverterFactory(GsonConverterFactory.create()) //добавили конвертер, чтобы он под капотом парсил наш JSON-ответ
//            //.client(okHttpCLient)
//            .build()
//
//        val service = retrofit.create(RetrofitInterface::class.java)
//        service.getUsers().enqueue(object: Callback<UsersData> {
//            override fun onResponce(call: Call<UsersData>, response: Response<UsersData>) {
//                println("!!! ${responce.body()}")
//            }
//
//            override fun onFailoure(call: Call<UsersData>, t: Trowable) {
//                t.printStackTrace()
//            }
//        })

//    }
        Executors.newSingleThreadExecutor().execute {
            val url = URL("https://reqres.in/api/users/2")
            val connection = url.openConnection() as HttpsURLConnection
            val gson = Gson()
            try {
                val br = BufferedReader(InputStreamReader(connection.inputStream))
                val line = br.readLine()
                val pers = gson.fromJson(line, Person::class.java)

                println("Имя пользователя: ${pers.data.first_name}")
                println("Фамилия: ${pers.data.last_name}")


        //val data = Data(id = 0, email = "", first_name = "", last_name = "", avatar = "")

            } finally {
                connection.disconnect()
            }
        }
    }
}

