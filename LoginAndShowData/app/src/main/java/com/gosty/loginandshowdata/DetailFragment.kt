package com.gosty.loginandshowdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gosty.loginandshowdata.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val data = arguments?.getParcelable<StudentModel>(EXTRA_DATA)
            initView(data)
        }
    }

    private fun initView(student: StudentModel?) {
        binding?.apply {
            tvDataName.text = getString(R.string.show_data, student?.name)
            tvDataEmail.text = getString(R.string.show_data, student?.email)
            tvDataJurusan.text = getString(R.string.show_data, student?.jurusan)
            tvDataSemester.text = getString(R.string.show_data, student?.semester.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra"
    }
}