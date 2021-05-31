package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(), BackPressedForFragments{
    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var transitItem: TransitItemSecondFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        transitItem = context as TransitItemSecondFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val minText = view.findViewById<EditText>(R.id.min_value)
        val maxText = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            try {
                val min = minText?.text.toString()
                val max = maxText?.text.toString()
                if (min.toInt() < max.toInt()) {
                    transitItem?.openCastSecondFragment(min.toInt(), max.toInt())
                } else {
                    Toast.makeText(context, "Данные не валидны", Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Данные не валидны", Toast.LENGTH_SHORT).show()
            }
        }
    }

    interface TransitItemSecondFragment {
        fun openCastSecondFragment(min: Int, max: Int)
    }

    override fun onBackPressed() {

    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}


