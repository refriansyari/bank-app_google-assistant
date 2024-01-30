package com.refridev.bankapp.ui.recipient

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.refridev.bankapp.R
import com.refridev.bankapp.base.BaseActivity
import com.refridev.bankapp.base.Resource
import com.refridev.bankapp.data.model.response.RecipientListResponse
import com.refridev.bankapp.databinding.ActivityRecipientListBinding
import com.refridev.bankapp.ui.recipient.adapter.RecipientListAdapter
import com.refridev.bankapp.ui.transaction.amount.InputAmountActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipientListActivity : BaseActivity<ActivityRecipientListBinding, RecipientListViewModel>
    (ActivityRecipientListBinding::inflate), RecipientListAdapter.OnItemClickListener, RecipientListContract.View {

    private lateinit var adapter: RecipientListAdapter
    private var amount: String = ""

    override fun initView() {
        adapter = RecipientListAdapter()
        setRecycleView()
        getViewModel().getRecipientList()

        val intent = intent
        getIntentData(intent)
    }

    override fun setRecycleView(){
        getViewBinding().rvRecipient.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RecipientListActivity.adapter
        }
        adapter.setOnItemClickListener(this)
//        adapter.setItems(mockRecipients)
    }

    private fun getIntentData(intent: Intent){
        if (intent?.extras != null) {
            // Extract the data you need from the Intent extras
            amount = intent.getStringExtra(EXTRA_VALUE) ?: "0"
        }
    }

    override fun setDataAdapter(data: List<RecipientListResponse>?){
        data?.let{ adapter.setItems(it)}
    }

    override fun observeData() {
        getViewModel().getRecipientListLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let{data ->
                        if (data.isEmpty()) {
//                            showError(true, getString(R.string.text_no_item))
                            showContent(false)
                        } else {
                            showContent(true)
                            showError(false, null)
                            setDataAdapter(it.data)
                        }

                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }

                else -> {}
            }
        }
    }

    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}
    override fun showError(isErrorEnabled: Boolean, msg: String?) {}

    override fun onItemClicked(recipientListResponse: RecipientListResponse) {
        val name = recipientListResponse.recipientName.toString()
        val account = recipientListResponse.accountNumber.toString()
        val amount = intent.getStringExtra(EXTRA_VALUE) ?: "0"

        startActivity(InputAmountActivity.getStartIntent(this, name, account, amount))
    }

    companion object {
        private const val EXTRA_VALUE = "value"

        fun getStartIntent(context: Context?): Intent {
            return Intent(context, RecipientListActivity::class.java)
        }
    }


}