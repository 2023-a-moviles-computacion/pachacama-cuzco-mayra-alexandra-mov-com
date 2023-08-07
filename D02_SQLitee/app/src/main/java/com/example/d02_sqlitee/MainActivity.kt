package com.example.d02_sqlitee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {//viene de AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //hereda del padre
        setContentView(R.layout.activity_main) // R=Resource, activity_main=interfaz

        val botonListContinente = findViewById<Button>(R.id.btn_ir_lvcontinente)
        botonListContinente
            .setOnClickListener {
                irActividad(ListvContinente::class.java)
            }
    }

    fun irActividad( //metodo de la  clase
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent) //intent explicito
    }
}