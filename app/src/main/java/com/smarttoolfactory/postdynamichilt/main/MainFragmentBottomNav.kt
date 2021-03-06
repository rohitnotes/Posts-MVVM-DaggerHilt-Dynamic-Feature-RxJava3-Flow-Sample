package com.smarttoolfactory.postdynamichilt.main

import android.os.Bundle
import android.view.View
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.core.ui.fragment.DynamicNavigationFragment
import com.smarttoolfactory.core.ui.fragment.navhost.BaseDynamicNavHostFragment
import com.smarttoolfactory.core.util.setupWithNavController
import com.smarttoolfactory.postdynamichilt.R
import com.smarttoolfactory.postdynamichilt.databinding.FragmentMainBottomNavBinding

/**
 * Alternative of MainFragment with only [BottomNavigationView]
 * that has [DynamicNavHostFragment]s as root fragment of each
 * tab with [BaseDynamicNavHostFragment]s that extend [DynamicNavHostFragment].
 *
 *
 */
class MainFragmentBottomNav : DynamicNavigationFragment<FragmentMainBottomNavBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_main_bottom_nav

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val bottomNavigationView = dataBinding!!.bottomNav

        val navGraphIds = listOf(
            R.navigation.nav_graph_dfm_home_start,
            R.navigation.nav_graph_dfm_dashboard_start,
            R.navigation.nav_graph_dfm_notification_start,
            R.navigation.nav_graph_dfm_account_start
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )
    }
}
