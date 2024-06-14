package com.lutfisobri.soca.ui.history

import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityHistoryBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity

class HistoryActivity : BaseActivity<ActivityHistoryBinding>() {
    private val viewModel by lazy { HistoryViewModel(AuthPreference(this)) }

    override fun init() {
        appBar(getString(R.string.history))
        viewModel.history()

        val adapter = getAdapter()

        viewModel.history.observe(this) {
            adapter.submitData(lifecycle, it)
        }

        binding.rvHistory.adapter = adapter
    }

    private fun getAdapter(): HistoryAdapter {
        val adapter = HistoryAdapter()
        adapter.onItemClick = { item ->
            navTo(ResultActivity::class.java, item)
        }

        return adapter
    }
}