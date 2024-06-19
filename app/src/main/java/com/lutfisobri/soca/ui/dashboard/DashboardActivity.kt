package com.lutfisobri.soca.ui.dashboard

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.models.ItemMenuModel
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityDashboardBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.favorite.FavoriteActivity
import com.lutfisobri.soca.ui.history.HistoryActivity
import com.lutfisobri.soca.ui.login.LoginActivity
import com.lutfisobri.soca.utils.gone
import com.lutfisobri.soca.ui.canvas.CanvasActivity

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {
    private lateinit var itemMenus: List<ItemMenuModel>
    private val authPreference by lazy { AuthPreference(this) }
    private val viewModel by lazy { DashboardViewModel(authPreference) }
    private var loadingDialog: AlertDialog? = null

    override fun init() {
        itemMenus = listOf(
            //            ItemMenuModel(
            //                icon = R.drawable.camera,
            //                title = getString(R.string.camera),
            //                action = { onCameraClick() }
            //            ),
            //            ItemMenuModel(
            //                icon = R.drawable.image,
            //                title = getString(R.string.gallery),
            //                action = { onGalleryClick() }
            //            ),

            ItemMenuModel(
                icon = R.drawable.pencil,
                title = getString(R.string.canvas),
                action = { onCanvasClick() }
            ),
            ItemMenuModel(
                icon = R.drawable.history,
                title = getString(R.string.history),
                action = { onHistoryClick() }
            ),
            ItemMenuModel(
                icon = R.drawable.star,
                title = getString(R.string.favorite),
                action = { onFavoriteClick() }
            )
        )

        with(binding) {
            rvCarousel.gone()
            rvMenu.adapter = ItemMenuAdapter(itemMenus)
            tvName.text = viewModel.getUser().fullName
            toolbar.menu.findItem(R.id.action_logout).setOnMenuItemClickListener {
                showLogoutDialog()
                true
            }
        }
    }

    private fun showLoadingDialog(message: String) {
        loadingDialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .create()
        loadingDialog?.show()
    }

    private fun hideLoadingDialog() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    private fun onCameraClick() {}

    private fun onGalleryClick() {}

    private fun onCanvasClick() {
        navTo(CanvasActivity::class.java)
    }

    private fun onHistoryClick() {
        navTo(HistoryActivity::class.java)
    }

    private fun onFavoriteClick() {
        navTo(FavoriteActivity::class.java)
    }

    private fun showLogoutDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_logout)
        dialog.show()

        dialog.findViewById<Button>(R.id.btnLogout)?.setOnClickListener {
            viewModel.logout()
            navToFinish(LoginActivity::class.java)
        }
        dialog.findViewById<Button>(R.id.btnCancel)?.setOnClickListener {
            dialog.dismiss()
        }
    }
}