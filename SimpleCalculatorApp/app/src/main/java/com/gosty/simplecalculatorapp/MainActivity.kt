package com.gosty.simplecalculatorapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.faendir.rhino_android.RhinoAndroidHelper
import com.google.android.material.button.MaterialButton
import com.gosty.simplecalculatorapp.databinding.ActivityMainBinding
import org.mozilla.javascript.Scriptable
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.apply {
            // All Clear
            btnAc.setOnClickListener {
                tvResult.text = getString(R.string.zero)
                tvSolution.text = ""
            }
            btnOpenBracket.setOnClickListener {
                setSolution(btnOpenBracket)
            }
            btnCloseBracket.setOnClickListener {
                setSolution(btnCloseBracket)
            }
            btnDivide.setOnClickListener {
                setSolution(btnDivide)
            }
            btnNumSeven.setOnClickListener {
                setSolution(btnNumSeven)
            }
            btnNumEight.setOnClickListener {
                setSolution(btnNumEight)
            }
            btnNumNine.setOnClickListener {
                setSolution(btnNumNine)
            }
            btnMultiply.setOnClickListener {
                setSolution(btnMultiply)
            }
            btnNumFour.setOnClickListener {
                setSolution(btnNumFour)
            }
            btnNumFive.setOnClickListener {
                setSolution(btnNumFive)
            }
            btnNumSix.setOnClickListener {
                setSolution(btnNumSix)
            }
            btnSubtract.setOnClickListener {
                setSolution(btnSubtract)
            }
            btnNumOne.setOnClickListener {
                setSolution(btnNumOne)
            }
            btnNumTwo.setOnClickListener {
                setSolution(btnNumTwo)
            }
            btnNumThree.setOnClickListener {
                setSolution(btnNumThree)
            }
            btnAdd.setOnClickListener {
                setSolution(btnAdd)
            }
            btnNumZero.setOnClickListener {
                setSolution(btnNumZero)
            }
            btnNumDot.setOnClickListener {
                setSolution(btnNumDot)
            }
            btnC.setOnClickListener {
                setSolution(btnC)
            }
            btnEqual.setOnClickListener {
                val result = calculate(tvSolution.text.toString().trim())
                if (result != "Err") {
                    tvResult.text = result
                    tvSolution.text = result
                }
            }
        }
    }

    private fun setSolution(btn: MaterialButton) {
        var data = binding.tvSolution.text.toString().trim()
        if (btn.text.toString().trim() == "C" && binding.tvSolution.text.toString().trim()
                .isNotEmpty()
        ) {
            if (data == "NaN") {
                data = ""
                binding.tvResult.text = getString(R.string.zero)
            } else {
                data = data.substring(0, data.length - 1)
            }
        } else {
            data += if (btn.text.toString().trim() == "X") {
                "*"
            } else {
                btn.text.toString().trim()
            }
        }

        binding.tvSolution.text = data
    }

    private fun calculate(formula: String): String {
        try {
            val context = RhinoAndroidHelper().enterContext()
            context.optimizationLevel = -1
            val scriptable: Scriptable = context.initStandardObjects()
            var result =
                context.evaluateString(scriptable, formula, "Javascript", 1, null).toString()
            if (result.endsWith(".0")) {
                result = result.replace(".0", "")
            }
            return result
        } catch (e: Exception) {
            Log.e(MainActivity::class.java.simpleName, "calculate: ${e.localizedMessage}")
            return "Err"
        }
    }
}