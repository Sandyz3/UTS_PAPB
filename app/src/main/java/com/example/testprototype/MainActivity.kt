package com.example.testprototype

import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainActivity : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var gunplaList: ArrayList<Gunpla>
    private lateinit var GunplaAdapter: GunplaAdapter
    private lateinit var deskripsi: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val fragmentHome = inflater.inflate(R.layout.activity_main, container, false)
        deskripsi = resources.getStringArray(R.array.decs_item)

        var rView = fragmentHome.findViewById<RecyclerView>(R.id.rvGunpla)
        rView.setHasFixedSize(true)
        rView.layoutManager = LinearLayoutManager(requireContext())

        val username = requireArguments().getString("usn")

        val gunplaList = arrayListOf(
            Gunpla(
                "LabZero 1/100 Gundam Barbatos Lupus REX",
                R.drawable.gbarbatos,
                R.string.desc,
                1 / 100,
                "Gundam Resin/Conversion Kits",
                listOf("New Arrivals", "Pre Orders", "Real New Arrivals")
            ),
            Gunpla(
                "AEther 1/100 MG Dynasty Warrior",
                R.drawable.aether,
                R.string.desc,
                1 / 100,
                "Third Party Model Kits",
                listOf("New Arrivals", "Pre Orders", "Real New Arrivals")
            ),
            Gunpla(
                "Infinity Nova (In Era+) Thunderbolt",
                R.drawable.thunderbolt,
                R.string.desc,
                1 / 100,
                "Gundam Resin/Conversion Kits",
                listOf("New Arrivals", "Pre Orders", "Real New Arrivals")
            )
        )

        GunplaAdapter = GunplaAdapter(gunplaList) { clickedGunpla ->
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra("gunplaData", clickedGunpla)
            startActivity(intent)
        }

        rView.adapter = GunplaAdapter

        return fragmentHome
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainActivity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
