package Fragment

import Adapter.KitapAdapter
import Database.KutuphaneDatabase
import Entity.Kitap
import Repository.KitapRepository
import ViewModel.KitapViewModel
import ViewModel.KitapViewModelFactory
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R


class KitapFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var kitapAdapter: KitapAdapter
    private lateinit var viewModel: KitapViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.kitap_arama_item, container, false)

        recyclerView = view.findViewById(R.id.recyclerviewkitaparamasonuc)
        kitapAdapter = KitapAdapter()
        recyclerView.adapter = kitapAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dao = KutuphaneDatabase.getDatabase(requireContext()).KitapDao()
        val repository = KitapRepository(dao)
        viewModel = ViewModelProvider(
            this,
            KitapViewModelFactory(repository)
        )[KitapViewModel::class.java]

        val searchView = view.findViewById<SearchView>(R.id.searchview)
        val btnAra = view.findViewById<Button>(R.id.kitapAra)

        val cbYazar = view.findViewById<CheckBox>(R.id.ygsırala)
        val cbKategori = view.findViewById<CheckBox>(R.id.category)

        btnAra.setOnClickListener {
            val query = searchView.query.toString()
            if (query.isNotEmpty()) {
                val filtre = when {
                    cbYazar.isChecked -> "yazar"
                    cbKategori.isChecked -> "kategori"
                    else -> "kitapadi"
                }

                viewModel.kitapAra(query, filtre).observe(viewLifecycleOwner) { kitaplar ->
                    kitapAdapter.setData(kitaplar)
                }
            } else {
                Toast.makeText(requireContext(), "Lütfen arama metni giriniz", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}