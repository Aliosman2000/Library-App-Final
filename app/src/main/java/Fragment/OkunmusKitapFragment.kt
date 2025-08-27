package Fragment

import Adapter.OkunmusKitapAdapter
import Database.KutuphaneDatabase
import Repository.OkunanKitapRepository
import ViewModel.OkunanKitapViewModel
import ViewModel.OkunanKitapViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R


class OkunmusKitapFragment : Fragment() {

    // Görünüm bileşenleri için lateinit değişkenler tanımlıyoruz.
    private lateinit var recyclerViewOkunanKitaplar: RecyclerView
    private lateinit var emptyListTextView: TextView

    // ViewModel ve Adapter tanımlamaları.
    private lateinit var okunanKitapViewModel: OkunanKitapViewModel
    private lateinit var okunanKitapAdapter: OkunmusKitapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment'ın görünümünü şişirme (inflate).
        // R.layout.fragment_okunmus_kitap'a erişmek için doğru R sınıfı içe aktarılmalıdır.
        val view = inflater.inflate(R.layout.okunmus_kitap_fragment, container, false)

        // Görünüm bileşenlerini findViewById ile tanımlıyoruz.
        // findViewById() metodu, görünüm nesnesinden (view) çağrılmalıdır.
        recyclerViewOkunanKitaplar = view.findViewById(R.id.recyclerViewOkunanKitaplar)
        emptyListTextView = view.findViewById(R.id.emptyListTextView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Repository ve ViewModel'ı Başlatma
        val application = requireNotNull(this.activity).application
        val dataSource = KutuphaneDatabase.getDatabase(application).okunanKitapDao()
        val repository = OkunanKitapRepository(dataSource)
        val viewModelFactory = OkunanKitapViewModelFactory(repository)

        okunanKitapViewModel = ViewModelProvider(this, viewModelFactory)[OkunanKitapViewModel::class.java]

        // Gerçek uygulamada buraya Firebase Auth'dan alınan dinamik bir kullanıcı ID'si gelmeli.
        val currentUserId = "test_user_id"
        okunanKitapViewModel.getOkunanKitaplar(currentUserId)

        // 2. RecyclerView ve Adapter'ı Kurma
        recyclerViewOkunanKitaplar.layoutManager = LinearLayoutManager(context)

        // Adaptörü başlatma ve RecyclerView'a atama.
        okunanKitapAdapter = OkunmusKitapAdapter(mutableListOf())
        recyclerViewOkunanKitaplar.adapter = okunanKitapAdapter

        // 3. ViewModel'dan Veriyi Gözlemleme
        okunanKitapViewModel.okunanKitaplar.observe(viewLifecycleOwner) { kitapListesi ->
            kitapListesi?.let {
                // Adaptörü yeni liste ile güncelleme.
                okunanKitapAdapter.updateData(it)

                // Liste boşsa kullanıcıya bilgi ver.
                if (it.isEmpty()) {
                    emptyListTextView.visibility = View.VISIBLE
                } else {
                    emptyListTextView.visibility = View.GONE
                }
            }
        }
    }
}