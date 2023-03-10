package kgb.toyproject.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import kgb.toyproject.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var inputNumber : Int = 0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.editNumber.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()) 0
            else text.toString().toInt()
            if(cmToM){
                binding.textNumber.text = inputNumber.times(0.01).toString()
            } else{
                binding.textNumber.text = inputNumber.times(100).toString()
            }

        }

        binding.btnSwap.setOnClickListener {
            cmToM = !cmToM
            if(cmToM){
                binding.textInputUnit.text="cm"
                binding.textOutputUnit.text="m"
                binding.textNumber.text = inputNumber.times(0.01).toString()
            }else{
                binding.textInputUnit.text="m"
                binding.textOutputUnit.text="cm"
                binding.textNumber.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.textInputUnit.text = if(cmToM) "cm" else "m"
        binding.textOutputUnit.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}