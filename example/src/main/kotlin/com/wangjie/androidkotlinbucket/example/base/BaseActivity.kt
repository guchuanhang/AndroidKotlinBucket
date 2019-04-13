package com.wangjie.androidkotlinbucket.example.base

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wangjie.androidkotlinbucket.library.KIViewer
import com.wangjie.androidkotlinbucket.library.KPresenter
import com.wangjie.androidkotlinbucket.library.KViewer

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */
abstract class BaseActivity : AppCompatActivity(), KViewer {

    protected open val presenter: KPresenter<*>? = null
    override val mContext: Context? = this

    private val loadingDialog: ProgressDialog by lazy { ProgressDialog(this) }

    override fun showLoading(message: String) {
        loadingDialog.setMessage(message)
        loadingDialog.show()
    }

    override fun cancelLoading() {
        if (loadingDialog.isShowing) {
            loadingDialog.cancel()
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        presenter?.closeAll()
    }

}