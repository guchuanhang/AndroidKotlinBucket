package com.wangjie.androidkotlinbucket.example

import com.wangjie.androidkotlinbucket.example.base.BasePresenter
import com.wangjie.androidkotlinbucket.example.ext.bindPresenter
import com.wangjie.androidkotlinbucket.example.ext.doOnNextOrError
import com.wangjie.androidkotlinbucket.example.ext.observeOnMain
import com.wangjie.androidkotlinbucket.example.ext.subscribeSafeNext
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */
class MainPresenter(viewer: MainViewer) : BasePresenter<MainViewer>(viewer) {

    fun test() {

        viewer.dialog("title", "Load data ?", positiveClickListener = { dialog, int ->
            viewer.showLoading("I'm loading...")
            Observable.create<String> {
                Thread.sleep(2000)
                it.onNext("load success")
                it.onCompleted()
            }
                    .subscribeOn(Schedulers.newThread())
                    .observeOnMain()
                    .doOnNextOrError { viewer.cancelLoading() }
                    .subscribeSafeNext {
                        viewer.toast("succeed: $it")
                    }
                    .bindPresenter(this)
        })

    }

}