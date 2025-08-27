package Fragment

import Adapter.KullaniciAdapter
import Database.KutuphaneDatabase
import Repository.KullaniciRepository
import ViewModel.KullaniciFactory
import ViewModel.KullaniciViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R



class KullaniciFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: KullaniciAdapter
    private lateinit var viewModel: KullaniciViewModel

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSifreGonder: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kullanici, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewKullanici)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = KullaniciAdapter()
        recyclerView.adapter = adapter


        val dao = KutuphaneDatabase.getDatabase(requireContext()).kullaniciDao()
        val repository = KullaniciRepository(dao)
        val factory = KullaniciFactory(repository)
        viewModel = ViewModelProvider(this, factory)[KullaniciViewModel::class.java]

        // LiveData observer
        viewModel.tumUyeler.observe(viewLifecycleOwner) { uyeler ->
            if (uyeler.isEmpty()) {
                Toast.makeText(requireContext(), "Hiç kullanıcı bulunamadı", Toast.LENGTH_SHORT).show()
            }
            adapter.setData(uyeler)
        }


        etUsername = view.findViewById(R.id.KullaniciAdiSu)
        etEmail = view.findViewById(R.id.Eposta)
        btnSifreGonder = view.findViewById(R.id.btnGonder)

        btnSifreGonder.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            if (username.isEmpty() || email.isEmpty()) {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.sifreSifirla(username, email,
                    onSuccess = { yeniSifre ->
                        Toast.makeText(requireContext(), "Yeni şifre: $yeniSifre", Toast.LENGTH_SHORT).show()
                        // İsteğe bağlı: Başarılı ekranına yönlendir
                    },
                    onError = { mesaj ->
                        Toast.makeText(requireContext(), mesaj, Toast.LENGTH_SHORT).show()
                    })
            }
        }

        return view
    }
}








