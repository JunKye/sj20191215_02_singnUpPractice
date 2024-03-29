package com.example.sj20191215_02_singnuppractice

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.sj20191215_02_singnuppractice.adapters.AlcoholAdapter
import com.example.sj20191215_02_singnuppractice.datas.Alcohol
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : BaseActivity() {

    var selectedbirthDay: Calendar? = null


    val alcoholList = ArrayList<Alcohol>()
    var alcoholAdapter: AlcoholAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        jobSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                jobSpinner.selectedItemPosition
                if (position != 0) {
                    Toast.makeText(mContext, jobSpinner.selectedItem.toString(), Toast.LENGTH_SHORT).show()
                }

            }


        }





//        pwEdt.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                Log.d("입력된값" , s.toString())
//            }
//
//        })


//        pwEdt.addTextChangedListener {
//            Log.d("입력된값", it.toString())
//        }

        birthDayTime.setOnClickListener(){
            val timePickerDialog = TimePickerDialog(mContext, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                selectedbirthDay?.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedbirthDay?.set(Calendar.MINUTE, minute)
                val sdf = SimpleDateFormat("a h:mm")
                birthDayTime.text = sdf.format(selectedbirthDay?.time)


            }, 20,5, false)
            timePickerDialog.show()
        }

        birthDayTxt.setOnClickListener {
            //            Toast.makeText(mContext,"생일지정 택스트 뷰 클릭", Toast.LENGTH_SHORT).show()
            val datePickerDialog = DatePickerDialog(
                mContext,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectedDateStr = "${year} / ${month - 1} / ${dayOfMonth}"
                    birthDayTxt.text = selectedDateStr

                    selectedbirthDay?.let {
                        Log.d("생년월일선택", "이미 선택된 값을 수정 - 다시선택")
                    }.let {
                        Log.d("생년월일선택", "선택된 값이 새로생김 - 처음선택")
                        selectedbirthDay = Calendar.getInstance()
                    }

//                    방법1 field로 선언
                    selectedbirthDay?.set(Calendar.YEAR, year)
                    selectedbirthDay?.set(Calendar.MONTH, month)
                    selectedbirthDay?.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                    방법2
                    selectedbirthDay?.set(year, month, dayOfMonth)

                    val sdf = SimpleDateFormat("yyyy년 M월 d일 (E)")
                    birthDayTxt.text = sdf.format(selectedbirthDay?.time)

                },
                2019,
                Calendar.DECEMBER,
                15
            )
            datePickerDialog.show()
        }



        pwEdt.addTextChangedListener {
            Log.d("입력된값", it.toString())
            val inputStr = it.toString()
            if (inputStr.length == 0) {
                pwStatusTxt.text = "버번입력하세요"
                pwStatusTxt.setTextColor(Color.RED)
            } else if (inputStr.length < 8) {
                pwStatusTxt.text = "버번이 너무 짧음"
                pwStatusTxt.setTextColor(Color.YELLOW)
                pwStatusTxt.setTextColor(Color.parseColor("#FDA0EF"))
            } else {
                pwStatusTxt.text = "사용해도 좋은 비밀번호입니다."
                pwStatusTxt.setTextColor(Color.GREEN)
                pwStatusTxt.setTextColor(Color.parseColor("#F2B3FA"))

            }
        }
    }

    override fun setValues() {
        addAlcohols()

        alcoholAdapter = AlcoholAdapter(mContext, R.layout.alcohol_spinner_list_item, alcoholList)
        alcholSpinner.adapter = alcoholAdapter

    }

    fun addAlcohols() {
        alcoholList.add(Alcohol("소주", "참이슬"))
        alcoholList.add(Alcohol("소주", "처음처럼"))
        alcoholList.add(Alcohol("소주", "C1"))
        alcoholList.add(Alcohol("소주", "한라산"))
        alcoholList.add(Alcohol("국내맥주", "카스"))
        alcoholList.add(Alcohol("국내맥주", "하이트"))
        alcoholList.add(Alcohol("국내맥주", "클라우드"))
        alcoholList.add(Alcohol("국내맥주", "오비라거"))
        alcoholList.add(Alcohol("해외맥주", "하이네켄"))
        alcoholList.add(Alcohol("해외맥주", "호가든"))
        alcoholList.add(Alcohol("해외맥주", "칭따오"))
    }

}
