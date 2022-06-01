package com.example.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,DialogInterface.OnCancelListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAlertDialog = this.findViewById<Button>(R.id.btnAlertDialog)
        btnAlertDialog.setOnClickListener(this)

        val buttonProgressBar = this.findViewById<Button>(R.id.buttonProgressBar)
        buttonProgressBar.setOnClickListener(this)

        val btnProgressBar = this.findViewById<ProgressBar>(R.id.btnProgressBar)
        btnProgressBar.setOnClickListener(this)

        val btnDatePickerDialog = this.findViewById<Button>(R.id.btnDatePicker)
        btnDatePickerDialog.setOnClickListener(this)

        val btnSnackbar = this.findViewById<Button>(R.id.btnSnackBar)
        btnSnackbar.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val button: Button = v as Button
        when(button.text){

            "AlertDialog" -> alertDialogTest()
            "DatePicker" -> datePickerTest()
            "SnackBar" -> snackBarTest()
            "ProgressBar" -> progressBarTest()
            "Clique Aqui" -> Toast.makeText(this, "Snack Bar foi Clicado", Toast.LENGTH_SHORT).show()

        }


    }


//Testes do AlerDialog------------------------------------------------------------------
    private fun alertDialogTest() {

        val alertDialog =  AlertDialog.Builder(this)
        alertDialog.setTitle("TESTE")
        alertDialog.setMessage("Esta é uma mensgem de alerta bem simples")
        alertDialog.setPositiveButton("sim, tudo bem"){
            alertDialog,which ->
            Toast.makeText(this, "show, bom dia ", Toast.LENGTH_SHORT).show()
        }
        alertDialog.setNegativeButton("nada bem"){
                alertDialog,which ->
            Toast.makeText(this, "que pena, espero que ficque bem ", Toast.LENGTH_SHORT).show()
        }
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    //-------------------------------------------------------------------------


//Testes do ProgressBar ---------------------------------------------------------
    private fun progressBarTest() {
        val btnProgressBar:ProgressBar = findViewById<ProgressBar>(R.id.btnProgressBar)
        val lblProgressBar:TextView = findViewById<TextView>(R.id.lblProgressBar)

    Thread(Runnable {

        for (i in 0..100){
            this@MainActivity.runOnUiThread(Runnable {
                btnProgressBar.progress = i
                lblProgressBar.text = i.toString() + " % "
            })
            Thread.sleep(200)
        }
    }).start()

    }
    //---------------------------------------------------------------------------

    //Testes da SnackBar
    private fun snackBarTest() {

        val snackbar:Unit = Snackbar
            .make(this.findViewById(R.id.pnlMain),"Teste da snakBar",Snackbar.LENGTH_LONG)
            .setActionTextColor(Color.RED)
            .setAction("Clique Aqui",this)
            .show()


    }
//Testes do DatePicker------------------------------------------------------
    private fun datePickerTest() {
       val calendar: Calendar = Calendar.getInstance()
        val dia:Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mes:Int = calendar.get(Calendar.MONTH)
        val ano:Int = calendar.get(Calendar.YEAR)

    val datePicker:Unit = DatePickerDialog(this,this,ano,mes,dia).show()
    }

    override fun onDateSet(view: DatePicker?, ano: Int, mes: Int, dia: Int) {
        Toast.makeText(this,"Data Selecionada: ${dia}/${mes+1}/${ano}",Toast.LENGTH_LONG).show()
    }
    //--------------------------------------------------------------------------


    override fun onCancel(dialog: DialogInterface?) {
        TODO("Not yet implemented")
    }
}

