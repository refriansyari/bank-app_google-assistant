package com.refridev.bankapp.ui

import android.content.Intent
import com.refridev.bankapp.base.BaseActivity
import com.refridev.bankapp.databinding.ActivityMainBinding
import com.refridev.bankapp.ui.recipient.RecipientListActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate) {

    override fun initView() {
        onLogoClicked()
    }

    private fun onLogoClicked(){
        getViewBinding().ivLogo.setOnClickListener {
            val intent = Intent(this, RecipientListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}
    override fun showError(isErrorEnabled: Boolean, msg: String?) {}


}