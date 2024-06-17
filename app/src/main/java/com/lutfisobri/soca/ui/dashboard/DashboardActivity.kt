package com.lutfisobri.soca.ui.dashboard

import android.os.Handler
import android.os.Looper
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
        showLoadingDialog(getString(R.string.loading_canvas))

        Handler(Looper.getMainLooper()).postDelayed({
            navTo(CanvasActivity::class.java)
            hideLoadingDialog()
        }, 1500)
    }

    private fun onHistoryClick() {
        showLoadingDialog(getString(R.string.loading_history))

        Handler(Looper.getMainLooper()).postDelayed({
            navTo(HistoryActivity::class.java)
            hideLoadingDialog()
        }, 1500)
    }

    private fun onFavoriteClick() {
        showLoadingDialog(getString(R.string.loading_favorite))

        Handler(Looper.getMainLooper()).postDelayed({
            navTo(FavoriteActivity::class.java)
            hideLoadingDialog()
        }, 1500)
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.logout_message))
            .setPositiveButton(getString(R.string.logout_yes)) { dialog, _ ->
                dialog.dismiss()
                performLogout()
            }
            .setNegativeButton(getString(R.string.logout_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun performLogout() {
        showLoadingDialog(getString(R.string.loading_logout))

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.logout()
            hideLoadingDialog()
            navToFinish(LoginActivity::class.java)
        }, 1500)
    }
}