package Adapter

import Entity.Kitap
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R


class KitapYonetimAdapter(
    private val onUpdateClicked: (Kitap) -> Unit,
    private val onDeleteClicked: (Kitap) -> Unit
) : ListAdapter<Kitap, KitapYonetimAdapter.KitapYonetimViewHolder>(KitapYonetimDiffCallback()) {

    // Her bir kitap elemanının görünümünü tutan sınıf
    class KitapYonetimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvKitapAdi: TextView = itemView.findViewById(R.id.tvKitapAdi)
        val tvYazar: TextView = itemView.findViewById(R.id.tvYazar)
        val tvStok: TextView = itemView.findViewById(R.id.tvStok)
        val btnGuncelle: ImageButton = itemView.findViewById(R.id.btnGuncelle)
        val btnSil: ImageButton = itemView.findViewById(R.id.btnSil)
    }

    // Yeni ViewHolder oluşturulduğunda çağrılır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitapYonetimViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kitap_yon_recyclerview, parent, false)
        return KitapYonetimViewHolder(view)
    }

    // ViewHolder'a veri bağlandığında çağrılır
    override fun onBindViewHolder(holder: KitapYonetimViewHolder, position: Int) {
        val kitap = getItem(position)
        holder.tvKitapAdi.text = kitap.kitapadi
        holder.tvYazar.text = kitap.yazar

        val stokText = "Stok: ${kitap.stokadedi}"
        holder.tvStok.text = stokText

        // Güncelle ve sil butonlarına tıklama dinleyicilerini ekle
        holder.btnGuncelle.setOnClickListener { onUpdateClicked(kitap) }
        holder.btnSil.setOnClickListener { onDeleteClicked(kitap) }
    }
}

// DiffUtil sınıfı ile listeyi verimli bir şekilde güncelliyoruz
class KitapYonetimDiffCallback : DiffUtil.ItemCallback<Kitap>() {
    override fun areItemsTheSame(oldItem: Kitap, newItem: Kitap): Boolean {
        // Kitapların aynı olup olmadığını ID'lerine göre kontrol et
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Kitap, newItem: Kitap): Boolean {
        // Kitapların içeriğinin aynı olup olmadığını kontrol et
        return oldItem == newItem
    }
}