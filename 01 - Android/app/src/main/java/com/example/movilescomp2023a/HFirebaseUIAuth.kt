package com.example.movilescomp2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class HFirebaseUIAuth : AppCompatActivity() {
    //Callback del INTENT de LOGIN
    private val respuestaLoginAuthUi=registerForActivityResult(FirebaseAuthUIActivityResultContract()){
            res:FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode=== RESULT_OK){
            if(res.idpResponse !=null){
                //Logica de negocio
                seLogeo(res.idpResponse!!)
            }
        }
    }

    fun seLogeo(res: IdpResponse){
        val btnLogin:Button=findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout=findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.visibility= View.VISIBLE
        btnLogin.visibility=View.INVISIBLE

        if(res.isNewUser==true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario:IdpResponse){
        /* usuario.email;  //luego auiq sigue la logica de enviar un correo al logearse, por ejm
         usuario.phoneNumber;
         usuario.user.name;*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)

        val btnLogin=findViewById<Button>(R.id.btn_login_firebase)
        btnLogin.setOnClickListener {
            val providers= arrayListOf(
                //Arreglo de PROVIDERS para logearse
            //Ej: Correo, FB, TW, google
            AuthUI.IdpConfig.EmailBuilder().build()
            )

            //Construimos el intent de login
            val logearseIntent=AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build()

            //Respuesta del intent de Login
            respuestaLoginAuthUi.launch(logearseIntent)
        }
        val btnLogout=findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.setOnClickListener { seDeslogeo() }

    }
    fun seDeslogeo(){
        val btnLogin=findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout=findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.visibility=View.INVISIBLE
        btnLogin.visibility=View.VISIBLE

        FirebaseAuth.getInstance().signOut()  //esta instancia sirve para tomar los datos del user actual, idk

    }
}