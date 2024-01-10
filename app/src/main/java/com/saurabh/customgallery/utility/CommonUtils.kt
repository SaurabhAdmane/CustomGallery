package com.saurabh.customgallery.utility

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object CommonUtils {
    fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, id: Int) {
        fragmentManager.beginTransaction().add(id, fragment, fragment.javaClass.simpleName).addToBackStack(null).commit()
    }

    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, id: Int) {
        fragmentManager.beginTransaction().replace(id, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null).commit()
    }

}