package com.kotlinpratice.firebaseotpverification.authentication

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kotlinpratice.firebaseotpverification.R
import com.kotlinpratice.firebaseotpverification.databinding.FragmentSignUpBinding
import java.util.Calendar


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    lateinit var arrayList: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabSignUp.setOnClickListener {
            if (!binding.etSignupFName.getText().toString().trim()
                    .isEmpty() && !binding.etSignupLName.getText().toString().trim()
                    .isEmpty() && !binding.etSignupNumber.getText().toString().trim()
                    .isEmpty() && !binding.etSignupEmail.getText().toString().trim()
                    .isEmpty() && !binding.etSignupDob.getText().toString().trim()
                    .isEmpty() && !binding.etSignupPassword.getText().toString().trim()
                    .isEmpty() && !binding.etSignupConfirmPass.getText().toString().trim()
                    .isEmpty()) {

                if (binding.spinnerSignupGender.getSelectedItem().toString().trim() == "Gender") {
                    Toast.makeText(requireContext(), "Please select Gender", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (binding.etSignupPassword.getText().toString() == binding.etSignupConfirmPass.getText().toString()) {
                        if (binding.cbSignup.isChecked()) {
                            binding.llSignup.setVisibility(View.INVISIBLE)
                            binding.pbSignup.setVisibility(View.VISIBLE)
                            findNavController().navigate(R.id.action_signUpFragment_to_homeScreenFragment)
                        } else {
                            Toast.makeText(requireContext(), "Please agree terms and conditions", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Password doesn't match", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(requireContext(), "Please enter all details", Toast.LENGTH_SHORT).show()
            }
        }

        binding.spinnerSignupGender.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLACK)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })

        arrayList = java.util.ArrayList()
        arrayList.add("Gender")
        arrayList.add("Male")
        arrayList.add("Female")
        arrayList.add("Others")
        adapter = ArrayAdapter<String>(requireActivity(), R.layout.spinner_dropdown_style, arrayList)
        //adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        binding.spinnerSignupGender.setAdapter(adapter)

        binding.etSignupDob.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val date = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireActivity(), { view, year, month, dayOfMonth ->
                    binding.etSignupDob.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year) }, year, month, date)
            datePickerDialog.show()

        }

    }



}