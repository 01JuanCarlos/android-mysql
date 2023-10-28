package com.jc.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edtNombre = findViewById<EditText>(R.id.txt_nombre)
        var edtNumero = findViewById<EditText>(R.id.txt_numero)
        var btnAdd = findViewById<Button>(R.id.btn_enviar)
        btnAdd.setOnClickListener {
            val url="http://192.168.100.10/AppRestaurante/insertar_datos.php"
            val queue=Volley.newRequestQueue(this)
            var resultadoPost=object :StringRequest(Request.Method.POST,url,
                Response.Listener<String> { response ->
                    Toast.makeText(this,"Mesa insertado exitosamente",Toast.LENGTH_SHORT).show()
                },Response.ErrorListener { error->
                    Toast.makeText(this,"Error $error",Toast.LENGTH_LONG).show()
                }){
                override fun getParams():MutableMap<String,String>{
                    val parametros=HashMap<String, String>()
                    parametros.put("numero_mesa",edtNumero?.text.toString())
                    parametros.put("nombre_mesa",edtNombre?.text.toString())
                    return parametros
                }
            }
            queue.add(resultadoPost)
        }
    }

}