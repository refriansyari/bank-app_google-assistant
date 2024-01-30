package com.refridev.bankapp.ui.transaction.amount

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.refridev.bankapp.R
import com.refridev.bankapp.base.BaseActivity
import com.refridev.bankapp.databinding.ActivityInputAmountBinding
import com.refridev.bankapp.ui.transaction.detail.TransactionDetailActivity


class InputAmountActivity : BaseActivity<ActivityInputAmountBinding, InputAmountViewModel>
    (ActivityInputAmountBinding::inflate) {

    private var name : String = ""
    private var account : String = ""
    private var amount : String = ""

    override fun initView() {

        setButtonListener()
        setTextWatcher()
        getIntentData()
    }

    private fun getIntentData(){
        // Extract the data you need from the Intent extras
        amount = intent.getStringExtra(EXTRA_AMOUNT) ?: "0"
        name = intent.getStringExtra(EXTRA_NAME) ?: getString(R.string.detail_recipient_name)
        account = intent.getStringExtra(EXTRA_ACCOUNT) ?: getString(R.string.detail_recipient_acc)
        if (amount != "0") {
            // For example, update UI elements with the retrieved data
            getViewBinding().etAmount.setText(amount)
            getViewBinding().btnNext.isEnabled = true
            getViewBinding().btnNext.alpha = 1.0f
        }else {
            showKeyboard(getViewBinding().etAmount)
        }
    }



    private fun setButtonListener(){
        getViewBinding().btnBack.setOnClickListener {
            finish()
        }

        getViewBinding().btnNext.setOnClickListener {
            val amount = getViewBinding().etAmount.text.toString()
            startActivity(TransactionDetailActivity.getStartIntent(this, name, account, amount))
        }
    }

    private fun setTextWatcher(){

        getViewBinding().etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {

                s?.let {
                    val inputText = it.toString()
                    if (inputText.startsWith("0") && inputText.length > 1) {
                        // If input starts with '0' and has more than one character
                        val firstNonZeroIndex = inputText.indexOfFirst { it != '0' }
                        if (firstNonZeroIndex != -1) {
                            // Replace '0' with the first non-zero digit
                            val replacedText = inputText.substring(firstNonZeroIndex)
                            // Update EditText text only if a replacement was made
                            if (replacedText != inputText) {
                                getViewBinding().etAmount.setText(replacedText)
                                // Set the cursor position to the end of the EditText
                                getViewBinding().etAmount.setSelection(replacedText.length)
                            }
                        }
                    }
                }

                if (getViewBinding().etAmount.text.toString().startsWith("0")
                    && getViewBinding().etAmount.text!!.length > 1) {
                    getViewBinding().etAmount.setText("0")
                    getViewBinding().etAmount.setSelection(getViewBinding().etAmount.text.toString().length)
                }

                val text = s.toString()
                val isEnabled = text.isNotEmpty() && text != "0"
                getViewBinding().btnNext.isEnabled = isEnabled
                getViewBinding().btnNext.alpha = if (isEnabled) 1.0f else 0.5f

            }
        })

        getViewBinding().btnNext.isEnabled = false

    }

    private fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)

        getViewBinding().btnNext.isEnabled = false
        getViewBinding().btnNext.alpha = 0.5f
    }

    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}
    override fun showError(isErrorEnabled: Boolean, msg: String?) {}

    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"
        private const val EXTRA_ACCOUNT = "EXTRA_ACCOUNT"
        private const val EXTRA_AMOUNT = "EXTRA_AMOUNT"
        private const val EXTRA_VALUE = "value"

        fun getStartIntent(context: Context?, name: String, account: String, amount: String): Intent {
            return Intent(context, InputAmountActivity::class.java)
                .putExtra(EXTRA_NAME, name)
                .putExtra(EXTRA_ACCOUNT, account)
                .putExtra(EXTRA_AMOUNT, amount)
        }
    }
}