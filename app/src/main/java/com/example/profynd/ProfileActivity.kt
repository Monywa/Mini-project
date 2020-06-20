package com.example.profynd

import android.os.Bundle
import android.provider.ContactsContract
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.profynd.database.DatabaseHandler
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_profile)

        val id:Int = intent.extras!!.getString("key")!!.toInt()
        var db= DatabaseHandler(this)
        val cursorName=db.getName(id)
        val cursorEmail=db.getEmail(id)
        val cursorPassword=db.getPassword(id)
        if(cursorName != null && cursorName.moveToFirst()){
            profile_name.setText(cursorName.getString(cursorName.getColumnIndex(DatabaseHandler.Col_Name)))
        }
        if(cursorEmail != null && cursorEmail.moveToFirst()){
            profile_email.setText(cursorEmail.getString(cursorEmail.getColumnIndex(DatabaseHandler.Col_Email)))
        }
        if(cursorPassword != null && cursorPassword.moveToFirst()){
            profile_newpassword.setText(cursorEmail.getString(cursorPassword.getColumnIndex(DatabaseHandler.Col_Password)))
            profile_confirmpassword.setText(cursorEmail.getString(cursorPassword.getColumnIndex(DatabaseHandler.Col_Password)))
        }


        profile_updatebtn.setOnClickListener{
            if(profile_name.text.length>0 && profile_email.text.length>0 && profile_confirmpassword.text.length>0 && profile_newpassword.text.length>0){
                if(profile_newpassword.text.toString()==profile_confirmpassword.text.toString()){
                    db= DatabaseHandler(this)
                    db.UpdateData(id,profile_name.text.toString(),profile_email.text.toString(),profile_newpassword.text.toString())
                    db.close()


                }else Toast.makeText(this,"New password and confirm password do not match",Toast.LENGTH_LONG).show()

            }else Toast.makeText(this,"Please fill all data ", Toast.LENGTH_LONG).show()
        }
    }
}
