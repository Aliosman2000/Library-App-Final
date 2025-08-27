package Adapter

import Entity.OkunmusKitapEntity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R

class OkunmusKitapAdapter(private val kitaplar: MutableList<OkunmusKitapEntity>) :
    RecyclerView.Adapter<OkunmusKitapAdapter.KitapViewHolder>() {

    // Her bir liste öğesinin görünümünü tutan ViewHolder sınıfı
    class KitapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // XML layout dosyasındaki ID'leri doğru bir şekilde buluyoruz.
        val kitapAdiTextView: TextView = itemView.findViewById(R.id.kitap_adi_textview)
        val yazarTextView: TextView = itemView.findViewById(R.id.yazar_adi_textview)
        val kategoriTextView: TextView = itemView.findViewById(R.id.kategori_textview)
    }

    // Her bir ViewHolder oluşturulduğunda çağrılır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitapViewHolder {
        // Liste elemanı için oluşturduğunuz XML dosyasını kullanın.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_okunmus_kitap_satiri, parent, false)
        return KitapViewHolder(view)
    }

    // ViewHolder'a veri bağlama işlemi
    override fun onBindViewHolder(holder: KitapViewHolder, position: Int) {
        val kitap = kitaplar[position]
        holder.kitapAdiTextView.text = "Kitap Adı: ${kitap.kitapadi}"
        holder.yazarTextView.text = "Yazar: ${kitap.yazar}"
        holder.kategoriTextView.text = "Kategori: ${kitap.category}"
    }

    // Listedeki eleman sayısını döndürür
    override fun getItemCount(): Int = kitaplar.size

    // RecyclerView'e yeni veri eklemek için kullanışlı bir fonksiyon
    // Bu fonksiyon, ViewModel'dan gelen güncel listeyi adapter'a atar.
    fun updateData(newKitaplar: List<OkunmusKitapEntity>) {
        kitaplar.clear()
        kitaplar.addAll(newKitaplar)
        notifyDataSetChanged()
    }
}

