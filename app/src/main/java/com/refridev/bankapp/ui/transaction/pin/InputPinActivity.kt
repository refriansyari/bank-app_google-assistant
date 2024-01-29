package com.refridev.bankapp.ui.transaction.pin

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.refridev.bankapp.R
import com.refridev.bankapp.base.BaseActivity
import com.refridev.bankapp.databinding.ActivityInputPinBinding
import com.refridev.bankapp.ui.transaction.TransactionSuccessActivity

class InputPinActivity : BaseActivity<ActivityInputPinBinding, InputPinViewModel>(ActivityInputPinBinding::inflate)
{
    override fun initView() {
        setTextChangeListener()
    }

    private fun setTextChangeListener(){
        getViewBinding().etPin.requestFocus()
        getViewBinding().etPin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()){
                    getViewBinding().btnFingerPrint.visibility = View.VISIBLE
                    getViewBinding().btnDelete.visibility = View.GONE
                } else  {
                    getViewBinding().btnFingerPrint.visibility = View.GONE
                    getViewBinding().btnDelete.visibility = View.VISIBLE
                }
                setViewImageState(s.length)

                if (s.length == 6){
                    goToSuccessActivity()
                }
            }
        })
    }

    fun onButtonClick(view: View) {
        if (view is Button) {
            val digit = view.text.toString()
            val currentText = getViewBinding().etPin.text.toString()
            getViewBinding().etPin.setText(currentText + digit)
        }
    }

    fun onFingerprintClick(view: View) {
        if (view.id == R.id.btn_finger_print) {
            val toast = Toast.makeText(this, "Fingerprint", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun onForgotPinClick(view: View){
        if (view.id == R.id.btn_forgot_pin) {
            val toast = Toast.makeText(this, "Forgot PIN", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun onDeleteClick(view: View) {
        if (view.id == R.id.btn_delete) {
            val currentText = getViewBinding().etPin.text.toString()

            // Check if there is any text in the EditText
            if (currentText.isNotEmpty()) {
                // Remove the last character from the text
                val newText = currentText.substring(0, currentText.length - 1)
                getViewBinding().etPin.setText(newText)
            }
        }
    }

    private val circleViews by lazy {
        arrayOf(
            getViewBinding().circle1,
            getViewBinding().circle2,
            getViewBinding().circle3,
            getViewBinding().circle4,
            getViewBinding().circle5,
            getViewBinding().circle6
        )
    }

    private fun setViewImageState(input: Int) {
        for (i in circleViews.indices) {
            val backgroundResource = if (i < input) R.drawable.bg_pin_filled else R.drawable.bg_pin
            circleViews[i].setBackgroundResource(backgroundResource)
        }
    }

    private fun goToSuccessActivity(){
        startActivity(TransactionSuccessActivity.getStartIntent(this))
    }

    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}
    override fun showError(isErrorEnabled: Boolean, msg: String?) {}

    companion object {
        fun getStartIntent(context: Context?): Intent {
            return Intent(context, InputPinActivity::class.java)
        }
    }

}