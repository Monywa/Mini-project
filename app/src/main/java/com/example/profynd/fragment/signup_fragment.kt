package com.example.profynd.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.profynd.MainActivity
import com.example.profynd.R
import com.example.profynd.database.DatabaseHandler
import com.example.profynd.database.User
import kotlinx.android.synthetic.main.signup.*


/**
 * A simple [Fragment] subclass.
 */
class signup_fragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var sign_Uppp=view.findViewById<Button>(R.id.signup_btn)

        sign_Uppp.setOnClickListener{

            if(signup_name.text.length>0 && signup_email.text.length>0 && signup_password.length()>0){
                var user= User(signup_name.text.toString(),signup_email.text.toString(),signup_password.text.toString())
                var db= DatabaseHandler(context!!).getInstance(activity)

                if(db?.checkMail(signup_email.text.toString())==true){
                db!!.Insert(user)
                Toast.makeText(context,"Success Registratoin ",Toast.LENGTH_LONG).show()
                db.close()
                }else Toast.makeText(context,"The email is already exsited",Toast.LENGTH_LONG).show()



//
//                val cursor = db.getAllName()
//                cursor!!.moveToFirst()
//                signup_name.append((cursor.getString(cursor.getColumnIndex(DatabaseHandler.Col_Name))))
//                while (cursor.moveToNext()) {
//                    signup_name.append((cursor.getString(cursor.getColumnIndex(DatabaseHandler.Col_Name))))
//                   signup_name.append("\n")
//                }
//                cursor.close()


//            }
            }
            else
                Toast.makeText(context,"Please Fill All Data's",Toast.LENGTH_LONG).show()
        }







    }
}
