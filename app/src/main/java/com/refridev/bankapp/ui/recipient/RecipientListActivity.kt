package com.refridev.bankapp.ui.recipient

import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun initView() {
        adapter = RecipientListAdapter()
        setRecycleView()
    }

    override fun setRecycleView(){
        getViewBinding().rvRecipient.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RecipientListActivity.adapter
        }

        val mockRecipients: List<RecipientListResponse> = listOf(
            RecipientListResponse("Aditya Putra (BRI)", "BRI 0892389207231"),
            RecipientListResponse("Aditya Putra (Mandiri)", "Mandiri 875957832929"),
            RecipientListResponse("Aditya Putra (BNI)", "BNI 84932043024"),
            // Add more items as needed
        )
        adapter.setOnItemClickListener(this)
        adapter.setItems(mockRecipients)
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
            }
        }
    }

    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}
    override fun showError(isErrorEnabled: Boolean, msg: String?) {}

    override fun onItemClicked(recipientListResponse: RecipientListResponse) {
        val name = recipientListResponse.recipientName.toString()
        val account = recipientListResponse.accountNumber.toString()

        startActivity(InputAmountActivity.getStartIntent(this, name, account))
    }


}