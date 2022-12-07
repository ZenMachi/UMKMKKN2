package com.dokari4.umkmkkn2.data

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dokari4.umkmkkn2.R
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.data.local.UmkmEntity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import timber.log.Timber
import java.io.BufferedReader

class StartingData(private val context: Context): RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingNotes(context)
        }
    }

    //Filling database with the data from JSON
    private fun fillWithStartingNotes(context: Context){
        //obtaining instance of data access object
        val dao = UmkmDatabase.getInstance(context)?.dao

        // using try catch to load the necessary data
        try {
            //creating variable that holds the loaded data
            val data = loadJSONArray(context)
            if (data != null){
                //looping through the variable as specified fields are loaded with data
                for (i in 0 until data.length()){
                    //variable to obtain the JSON object
                    val item = data.getJSONObject(i)
                    //Using the JSON object to assign data
                    val umkmNamaPengusaha = item.getString("umkm-nama-pengusaha")
                    val umkmAlamatUsaha = item.getString("umkm-alamat-usaha")
                    val umkmJenisUsaha = item.getString("umkm-jenis-usaha")
                    val umkmKodeInput = item.getString("umkm-kode-input")
                    val umkmNomorTelepon = item.getString("umkm-nomor-telepon")
                    val umkmLokasiMaps = item.getString("umkm-lokasi-maps")
                    val umkmFotoUsaha = item.getString("umkm-foto-usaha")
//                    val umkmFotoGaleri = item.getJSONArray("umkm-foto-galeri")
//
//                    val photoList = Array(umkmFotoGaleri.length()) {
//                        umkmFotoGaleri.getString(it)
//                    }


                    //data loaded to the entity
                    val umkmEntity = UmkmEntity(
                        umkmNamaPengusaha,umkmAlamatUsaha,umkmJenisUsaha,umkmKodeInput, umkmNomorTelepon, umkmLokasiMaps, umkmFotoUsaha
                    )

                    //using dao to insert data to the database
                    dao?.insertUmkm(umkmEntity)
                }
            }
        }
        //error when exception occurs
        catch (e: JSONException) {
            Timber.d("fillWithStartingNotes: $e")
        }
    }

    // loads JSON data
    private fun loadJSONArray(context: Context): JSONArray?{
        //obtain input byte
        val inputStream = context.resources.openRawResource(R.raw.umkm_data)
        //using Buffered reader to read the inputstream byte
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}