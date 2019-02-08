package com.project.countries.ui.uibase
/**
 * Created by Geeta on 04/02/19.
 */
interface BasePresenter<T > {
    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    fun attachView(view: T)

    /**
     * Drops the reference to the view when destroyed
     */
    fun detachView()

}