package com.gosty.loginandshowdata

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.gosty.loginandshowdata.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            initView()
        }
    }

    private fun initView() {
        binding?.btnLogin?.setOnClickListener {
            if (validateData()) {
                val student = StudentModel(
                    name = binding?.edtName?.text.toString().trim(),
                    email = binding?.edtEmail?.text.toString().trim(),
                    jurusan = binding?.edtJurusan?.text.toString().trim(),
                    semester = binding?.edtSemester?.text.toString().trim().toInt()
                )
                val detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putParcelable(DetailFragment.EXTRA_DATA, student)
                detailFragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.frame_container, detailFragment, DetailFragment::class.java.simpleName)
                    addToBackStack(null)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    commit()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    activity?.getString(R.string.invalid_input),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateData(): Boolean {
        var result = true
        binding?.apply {
            val name = edtName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val jurusan = edtJurusan.text.toString().trim()
            val semester = edtSemester.text.toString().trim()

            // validate empty fields
            if (name.isEmpty()) {
                edtName.error = activity?.getString(R.string.empty_name)
                result = false
            }
            if (email.isEmpty()) {
                edtEmail.error = activity?.getString(R.string.empty_email)
                result = false
            }
            if (jurusan.isEmpty()) {
                edtJurusan.error = activity?.getString(R.string.empty_jurusan)
                result = false
            }
            if (semester.isEmpty()) {
                edtSemester.error = activity?.getString(R.string.empty_semester)
                result = false
            }

            // validate email format
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.error = activity?.getString(R.string.email_wrong)
                result = false
            }

            // validate semester range
            if (semester.isNotEmpty() && semester.toInt() !in 14 downTo 1) {
                edtSemester.error = activity?.getString(R.string.semester_wrong)
                result = false
            }
        }

        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}