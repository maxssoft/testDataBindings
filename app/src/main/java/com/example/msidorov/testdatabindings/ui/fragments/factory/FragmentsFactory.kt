package com.example.msidorov.testdatabindings.ui.fragments.factory

import android.os.Bundle
import com.example.msidorov.testdatabindings.ui.fragments.factory.ss.FragmentFactory
import com.example.msidorov.testdatabindings.ui.fragments.FragmentType

/**
 * @author m.sidorov
 */
object FragmentsFactory {

    private val factoryMap: MutableMap<FragmentType, FragmentFactory> = hashMapOf()

    fun register(fragmentType: FragmentType, fragmentFactory: FragmentFactory){
        factoryMap.put(fragmentType, fragmentFactory)
    }

    fun newInstance(fragmentType: FragmentType, data: Bundle): BaseFragment{
        val factory = factoryMap.get(fragmentType)
        if (factory == null){
            throw Exception(this::class.java.simpleName + ".newInstance(): unknown fragment type [${fragmentType.name}]")
        }

        return factory.newInstance(data)
    }

}