package com.refridev.bankapp.ui.transaction.detail

import android.content.Context
import android.content.Intent
import com.refridev.bankapp.base.BaseActivity
import com.refridev.bankapp.databinding.ActivityTransactionDetailBinding
import com.refridev.bankapp.ui.transaction.pin.InputPinActivity

class TransactionDetailActivity : BaseActivity<ActivityTransactionDetailBinding, TransactionDetailViewModel>
    (ActivityTransactionDetailBinding::inflate) {

    override fun initView() {
        setButtonListener()
        setView()
    }

    private fun setButtonListener(){
        getViewBinding().btnBack.setOnClickListener {
            finish()
        }

        getViewBinding().btnConfirm.setOnClickListener {
            startActivity(InputPinActivity.getStartIntent(this))
        }
    }

    private fun setView(){
        val name = intent.getStringExtra(EXTRA_NAME)
        var account = intent.getStringExtra(EXTRA_ACCOUNT)
        val amount = intent.getStringExtra(EXTRA_AMOUNT)

        getViewBinding().tvRecipientName.text = name
        getViewBinding().tvRecipientAccount.text = account
        getViewBinding().tvTransferAmount.text = amount
        getViewBinding().tvTotal.text = amount
        getViewBinding().tvAmount.text = amount
    }

    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}
    override fun showError(isErrorEnabled: Boolean, msg: String?) {}


    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"
        private const val EXTRA_ACCOUNT = "EXTRA_ACCOUNT"
        private const val EXTRA_AMOUNT = "EXTRA_AMOUNT"

        fun getStartIntent(context: Context?, name: String, account: String, amount: String): Intent {
            return Intent(context, TransactionDetailActivity::class.java)
                .putExtra(EXTRA_NAME, name)
                .putExtra(EXTRA_ACCOUNT, account)
                .putExtra(EXTRA_AMOUNT, amount)

        }
    }
}