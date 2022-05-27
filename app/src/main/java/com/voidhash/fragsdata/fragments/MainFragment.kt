package com.voidhash.fragsdata.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.voidhash.fragsdata.R
import com.voidhash.fragsdata.models.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fm = parentFragmentManager
        ft = fm.beginTransaction()
        ft.addToBackStack(null)
    }

    private fun serializableButton() {
        var btnSerializable = btnSerializable
        var item01 = Item_Serializable("The One Ring")
        var item02 = Item_Serializable("Sting")
        var model = Hero_Serializable("Frodo", "Hobbit", "Serializable Fragment",
            listOf(item01, item02)
        )

        val serializableFrag = SerializableFragment()
        serializableFrag.apply {
            arguments = Bundle().apply {
                putSerializable("model", model)
            }
        }

        btnSerializable.setOnClickListener {
            ft.replace(R.id.fragmentContainerView, serializableFrag)
            ft.commit()
        }
    }

    private fun parcelableButton() {
        val btnParcelable = btnParcelable
        val item01 = Item_Parcelable("Bow and Arrow")
        val item02 = Item_Parcelable("Sword")
        val model = Hero_Parcelable("Legolas", "Elf", "Parcelable Fragment",
        listOf(item01, item02))

        val parcelableFrag = ParcelableFragment()
        parcelableFrag.apply {
            arguments?.apply {
                putParcelable("model", model)
            }
        }

        btnParcelable.setOnClickListener {
            ft.replace(R.id.fragmentContainerView, parcelableFrag)
            ft.commit()
        }
    }

    private fun singletonButton() {
        val btnSingleton = btnSingleton
        val item01 = Item("Sword")
        val item02 = Item("Shield")
        val model = Hero("Aragorn", "Human", "Singleton Fragment",
        listOf(item01, item02))

        Hero.instance = model

        btnSingleton.setOnClickListener {
            ft.replace(R.id.fragmentContainerView, SingletonFragment())
            ft.commit()
        }
    }

    private fun eventButton() {
        val btnEventManager = btnEvents
        val item01 = Item_Event("Staff")
        val item02 = Item_Event("Sword")
        val model = Hero_Event("Gandalf", "Maia", "Event Fragment",
        listOf(item01, item02))
        EventManager.getInstance().publish(model)

        btnEventManager.setOnClickListener {
            ft.replace(R.id.fragmentContainerView, EventFragment())
            ft.commit()
        }
    }


}