package com.example.calcsalario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName: EditText = findViewById(R.id.et_name)
        val etSalary: EditText = findViewById(R.id.et_salary)
        val btnCalculate: Button = findViewById(R.id.btn_calculate)

        val tvRentaValue: TextView = findViewById(R.id.tv_renta_value)
        val tvAfpValue: TextView = findViewById(R.id.tv_afp_value)
        val tvIsssValue: TextView = findViewById(R.id.tv_isss_value)
        val tvNetSalaryValue: TextView = findViewById(R.id.tv_net_salary_value)

        val decimalFormat = DecimalFormat("#.00")

        btnCalculate.setOnClickListener {
            val salaryBase = etSalary.text.toString().toDoubleOrNull()

            if (salaryBase != null) {
                val renta = calculateRenta(salaryBase)
                val afp = salaryBase * 0.0725
                val isss = salaryBase * 0.03
                val salarioNeto = salaryBase - renta - afp - isss

                tvRentaValue.text = "$${decimalFormat.format(renta)}"
                tvAfpValue.text = "$${decimalFormat.format(afp)}"
                tvIsssValue.text = "$${decimalFormat.format(isss)}"
                tvNetSalaryValue.text = "$${decimalFormat.format(salarioNeto)}"
            } else {
                tvRentaValue.text = ""
                tvAfpValue.text = ""
                tvIsssValue.text = ""
                tvNetSalaryValue.text = "Por favor, ingrese un salario válido."
            }
        }
    }

    private fun calculateRenta(salary: Double): Double {
        return when {
            salary <= 472 -> 0.0
            salary <= 895.24 -> (salary - 472) * 0.1 + 17.67
            salary <= 2038.10 -> (salary - 895.24) * 0.2 + 60.0
            else -> (salary - 2038.10) * 0.3 + 288.57
        }
    }
}