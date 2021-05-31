package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment(), BackPressedForFragments {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var transitItem: TransitItemFirstFragment? = null
    private var bufItem = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        transitItem = context as TransitItemFirstFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0
        bufItem = generate(min, max)

        result?.text = bufItem.toString()

        backButton?.setOnClickListener {
            transitItem?.openCastFirstFragment(bufItem)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        return (min..max).random()
    }

    interface TransitItemFirstFragment {
        fun openCastFirstFragment(previousNumber: Int)
    }

    override fun onBackPressed() {
        transitItem?.openCastFirstFragment(bufItem)
    }


    companion object {
        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}





