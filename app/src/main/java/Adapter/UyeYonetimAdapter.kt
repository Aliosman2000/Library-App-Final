package Adapter

import Entity.Uye
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R

class UyeYonetimAdapter(
    private val onUpdateClicked: (Uye) -> Unit,
    private val onDeleteClicked: (Uye) -> Unit
) : ListAdapter<Uye, UyeYonetimAdapter.UyeViewHolder>(UyeDiffCallback()) {

    // Her bir liste öğesinin görünümünü tutar.
    class UyeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAdiSoyadi: TextView = view.findViewById(R.id.tvUyeAdiSoyadi)
        val tvEposta: TextView = view.findViewById(R.id.tvUyeEposta)
        val btnGuncelle: ImageButton = view.findViewById(R.id.btnUyeGuncelle)
        val btnSil: ImageButton = view.findViewById(R.id.btnUyeSil)
    }

    // Yeni bir ViewHolder oluşturur.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UyeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.admin_uye_yon_recyclerview, parent, false)
        return UyeViewHolder(view)
    }

    // ViewHolder'a veri bağlar ve buton tıklamalarını yönetir.
    override fun onBindViewHolder(holder: UyeViewHolder, position: Int) {
        val uye = getItem(position)

        holder.tvAdiSoyadi.text = "${uye.uyeadi} ${uye.uyesoyadi}"
        holder.tvEposta.text = uye.eposta

        // Güncelleme butonuna tıklama dinleyicisi ekler
        holder.btnGuncelle.setOnClickListener {
            onUpdateClicked(uye)
        }

        // Silme butonuna tıklama dinleyicisi ekler
        holder.btnSil.setOnClickListener {
            onDeleteClicked(uye)
        }
    }

    // Liste güncellemelerini daha verimli hale getirmek için DiffUtil kullanır.
    class UyeDiffCallback : DiffUtil.ItemCallback<Uye>() {
        override fun areItemsTheSame(oldItem: Uye, newItem: Uye): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Uye, newItem: Uye): Boolean {
            return oldItem == newItem
        }
    }
}