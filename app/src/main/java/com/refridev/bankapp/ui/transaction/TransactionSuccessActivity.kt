package com.refridev.bankapp.ui.transaction

import android.content.Context
import android.content.Intent
import com.refridev.bankapp.base.BaseActivity
import com.refridev.bankapp.databinding.ActivityTransactionSuccessBinding
import com.refridev.bankapp.ui.MainActivity

class TransactionSuccessActivity : BaseActivity<ActivityTransactionSuccessBinding, TransactionSuccessViewModel>
    (ActivityTransactionSuccessBinding::inflate) {

    override fun initView() {
        onButtonClicked()
    }

    private fun onButtonClicked(){
        getViewBinding().btnBackToApp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }

    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}
    override fun showError(isErrorEnabled: Boolean, msg: String?) {}

    companion object {
        fun getStartIntent(context: Context?): Intent {
            return Intent(context, TransactionSuccessActivity::class.java)
        }
    }
}