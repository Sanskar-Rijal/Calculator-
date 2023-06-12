package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
   private var number:Boolean=false
   private var decimal:Boolean=false
    private var inputmain:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputmain=findViewById(R.id.main)
    }
    fun press(view:View)
   {
      inputmain?.append((view as Button).text)
       number=true
    }
    fun clear(view:View)
   {
        inputmain?.text=""
       number=false
       decimal=false
    }
    fun decimal(view: View)
    {
        if(number && !decimal)
        {
            inputmain?.append(".")
            number=false
            decimal=true
        }
    }
    fun operator(view: View)
    {
        inputmain?.text?.let {
        if(number && !add(it.toString()))
        {
            inputmain?.append((view as Button).text)
            number=false
            decimal=false
        }
            else
        {
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show()
        }
        }
    }
    private fun add(value:String):Boolean
    {
        return if(value.startsWith("-")) {
            false
        } else {
            (value.contains("+") || value.contains("/") ||
                    value.contains("*") || value.contains("-"))
        }
    }
    private fun removezero(result:String):String
    {
        var value:String=result
        if(result.contains(".0"))
        {
            value=result.substring(0,result.length-2)
        }
        return value
    }
    fun equal(view: View)
    {
        if(number)
        {
            var value=inputmain?.text.toString()
            var prefix=""
            try {
                if(value.startsWith("-"))
                {
                    prefix="-"
                    value=value.substring(1)
                }
                if(value.contains("-"))
                {
                    val split = value.split("-")
                    var one = split[0]
                    val two = split[1]//99-1  split0=99 split1=1
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    inputmain?.text = removezero("${one.toDouble() - two.toDouble()}")
                }
                else if (value.contains("+"))
                {
                    val split=value.split("+")
                    val one=split[0]
                    val two=split[1]
                    inputmain?.text= removezero("${one.toDouble() + two.toDouble()}")
                }
                else if(value.contains("*"))
                {
                    val split= value.split("*")
                    val one=split[0]
                    val two=split[1]
                    inputmain?.text=removezero((one.toDouble() * two.toDouble()).toString())
                }
                else if(value.contains("/"))
                {
                    val split=value.split("/")
                    val one =split[0]
                    val two=split[1]
                    inputmain?.text=removezero((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
        else
        {
            Toast.makeText(this,"Can't press = without expression",Toast.LENGTH_SHORT).show()
        }
    }
}