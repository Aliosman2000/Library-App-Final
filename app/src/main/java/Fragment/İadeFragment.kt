package Fragment

import Adapter.IadeAdapter
import Entity.İade
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R

class LoanFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loanAdapter: IadeAdapter
    private lateinit var ıadelıst: MutableList<İade>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_iade, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewIade)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        ıadelıst = mutableListOf()
        loanAdapter = IadeAdapter(ıadelıst)
        recyclerView.adapter = loanAdapter

        loanlariYukle()

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loanlariYukle() {
        // Örnek tarih: Şu anki zaman (milisaniye olarak)
        val suAn = System.currentTimeMillis()

        ıadelıst.add(
            İade(
                uyeid = 101,
                kitapId = 201,
                odunc_tarihi = suAn - (5 * 24 * 60 * 60 * 1000L), // 5 gün önce
                iade_tarihi = suAn + (10 * 24 * 60 * 60 * 1000L), // 10 gün sonra
                iadekontrol = "Devam Ediyor"
            )
        )

        ıadelıst.add(
            İade(
                uyeid = 102,
                kitapId = 202,
                odunc_tarihi = suAn - (15 * 24 * 60 * 60 * 1000L), // 15 gün önce
                iade_tarihi = suAn - (2 * 24 * 60 * 60 * 1000L), // 2 gün önce teslim edilmiş
                iadekontrol = "Teslim Edildi",
            )
        )

        ıadelıst.add(
            İade(
                uyeid = 103,
                kitapId = 203,
                odunc_tarihi  = suAn - (20 * 24 * 60 * 60 * 1000L), // 20 gün önce
                iade_tarihi  = null, // Henüz teslim edilmemiş
                iadekontrol = "Gecikmiş"
            )
        )

        loanAdapter.notifyDataSetChanged()
    }
}