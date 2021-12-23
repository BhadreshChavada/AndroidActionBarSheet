package com.actionsheetandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.actionsheet.ActionSheet
import com.actionsheet.MenuClickListener
import com.actionsheet.MenuList
import com.actionsheetandroid.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuList = ArrayList<MenuList>()
        menuList.add(MenuList("Option 1","#FF0000"))
        menuList.add(MenuList("Option 2"))
        menuList.add(MenuList("Option 3"))

        binding.buttonFirst.setOnClickListener {
            ActionSheet.showActionSheet(requireActivity(), menuList,object :MenuClickListener{
                override fun menuItemClicked(index: Int) {
                    Log.d("POS",index.toString())
                }

            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}