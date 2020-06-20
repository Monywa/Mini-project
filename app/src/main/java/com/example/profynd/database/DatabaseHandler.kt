package com.example.profynd.database

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.util.Log.d
import android.view.textclassifier.ConversationActions
import android.widget.Toast

//val Database_Name="MyDb"
//val Table_name="User"
//val Col_Id="id"
//val Col_Name="name"
//val Col_Email="email"
//val Col_Password="password"

class DatabaseHandler(var context: Context):SQLiteOpenHelper(context, Database_Name,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + Table_name + "(" +
                Col_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_Name + " VARCHAR(100)," +
                Col_Email + " VARCHAR(100)," +
                Col_Password + " VARCHAR(100))"
        db?.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $Table_name")
    }

    fun Insert(user: User) {
        val db = this.writableDatabase
        val con = ContentValues()
        con.put(Col_Name, user.name)
        con.put(Col_Email, user.email)
        con.put(Col_Password, user.password)

        var result = db.insert(Table_name, null, con)

        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(context, "SUccess", Toast.LENGTH_SHORT).show()


        db.close()

    }

    private var databaseInstance: DatabaseHandler? = null

    fun getInstance(context: Context?): DatabaseHandler? {
        if (databaseInstance == null) {
            databaseInstance = DatabaseHandler(context!!)
        }
        return databaseInstance
    }

//    fun readData(): MutableList<User>
//    {
//        var list: MutableList<User> = ArrayList()
//        val db = this.writableDatabase
//        var query = "Select * from " + Table_name
//        var result = db.rawQuery(query, null)
//
//        var buffer=StringBuffer();
//
//        if (result.moveToFirst()) {
//            do {
//             buffer.append("ss",result.getString(0) )
//                     buffer.append("ddd",result.getString(1))
//                buffer.append("ddd",result.getString(2))
//                buffer.append("ddd",result.getString(3))
//
//
//
//
//            } while (result.moveToFirst())
//        }
//        return list
//        db.close()
//    }

    fun getAllName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $Table_name", null)
    }


    public fun getAutorization(email:String,password :String):Boolean{
        val db=this.readableDatabase
        var cursor:Cursor=db.rawQuery("select * from $Table_name where $Col_Email=? AND  $Col_Password=?", arrayOf(email,password))
        return if (cursor.count > 0) return true else false
    }



    public fun checkMail(email : String):Boolean{
        val db=this.readableDatabase
        var cursor:Cursor=db.rawQuery("select * from $Table_name where $Col_Email=?", arrayOf(email))
        return if (cursor.count > 0) return false else true

    }

    public  fun UpdateData(id:Int,name:String,email: String,password:String){
        var db=this.writableDatabase
        val c=ContentValues()

        c.put(Col_Name,name)
        c.put(Col_Email,email)
        c.put(Col_Password,password)

        db?.update(Table_name,c, "$Col_Id=$id",null)
        Toast.makeText(context,"SUCCESSFUL UPDATE PROFILE",Toast.LENGTH_LONG).show()
        db.close()
    }


    public fun getID(email:String,password: String):Cursor{
        val db=this.readableDatabase
        return db.rawQuery("select  $Col_Id  from $Table_name where $Col_Email=?", arrayOf(email))
    }
    public fun getName(id:Int):Cursor{
        val db=this.readableDatabase
        return db.rawQuery("select  $Col_Name  from $Table_name where $Col_Id=$id",null)
    }

    public fun getEmail(id:Int):Cursor{
        val db=this.readableDatabase
        return db.rawQuery("select  $Col_Email  from $Table_name where $Col_Id=$id",null)
    }

    public fun getPassword(id:Int):Cursor{
        val db=this.readableDatabase
        return db.rawQuery("select $Col_Password  from $Table_name where $Col_Id=$id",null)
    }
    companion object {
        private val DATABASE_VERSION = 1
        val Database_Name="MyDb"
        val Table_name="User"
        val Col_Id="id"
        val Col_Name="name"
        val Col_Email="email"
        val Col_Password="password"
    }
}

