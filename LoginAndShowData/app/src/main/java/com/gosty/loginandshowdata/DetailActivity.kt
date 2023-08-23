package com.gosty.loginandshowdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gosty.loginandshowdata.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val student = intent.getParcelableExtra<StudentModel>(EXTRA_DATA)
        initView(student)
    }

    private fun initView(student: StudentModel?) {
        binding.apply {
            tvDataName.text = getString(R.string.show_data, student?.name)
            tvDataEmail.text = getString(R.string.show_data, student?.email)
            tvDataJurusan.text = getString(R.string.show_data, student?.jurusan)
            tvDataSemester.text = getString(R.string.show_data, student?.semester.toString())
        }
    }

    companion object {
        const val EXTRA_DATA = "extra"
    }
}