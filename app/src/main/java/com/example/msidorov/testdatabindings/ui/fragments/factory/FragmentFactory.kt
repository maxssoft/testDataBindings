package com.example.msidorov.testdatabindings.ui.fragments.factory.ss

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.msidorov.testdatabindings.ui.fragments.factory.BaseFragment
import com.example.msidorov.testdatabindings.ui.navigation.OpenFragmentData

/**
 * @author m.sidorov
 */
interface FragmentFactory {

    fun newInstance(data: Bundle): BaseFragment;

}