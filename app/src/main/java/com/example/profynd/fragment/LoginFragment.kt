package com.example.profynd.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.profynd.ProfileActivity
import com.example.profynd.R
import com.example.profynd.database.DatabaseHandler
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginemail=view.findViewById<EditText>(R.id.login_email)
        val loginpassword=view.findViewById<EditText>(R.id.login_password)
        var cursorInt:String

        login_btn.setOnClickListener{
            if(login_email.text.length>0 && login_password.text.length>0 ){
                var db= DatabaseHandler(context!!).getInstance(activity)
                if(db?.getAutorization(loginemail.text.toString(),loginpassword.text.toString())!!){
                    val intent=Intent(this.activity,ProfileActivity::class.java)
                    val cursor=db.getID(loginemail.text.toString(),loginpassword.text.toString())
                    val bundle = Bundle()
                    if(cursor != null && cursor.moveToFirst()){
                        cursorInt=cursor.getInt(cursor.getColumnIndex(DatabaseHandler.Col_Id)).toString()

                    bundle.putString("key",cursorInt)
                    intent.putExtras(bundle)}
                    startActivity(intent)

                    db.close()
                }else
                    Toast.makeText(context,"no account founded or Wrong Password",Toast.LENGTH_LONG).show()
//               if(loginemail.text.toString()=="hh"&& loginpassword.text.toString()=="hh"){
//                   startActivity(Intent(this.activity,ProfileActivity::class.java))
//
//                }

            }else
                Toast.makeText(context,"Please Fill All Data's", Toast.LENGTH_LONG).show()

        }
    }
}



