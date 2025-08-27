package Adapter

import Entity.Kitap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R

class KitapAdapter : RecyclerView.Adapter<KitapAdapter.KitapViewHolder>() {

    private var kitapList: List<Kitap> = emptyList()

    fun setData(list: List<Kitap>) {
        kitapList = list
        notifyDataSetChanged()
    }

    class KitapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kitapAdi: TextView = itemView.findViewById(R.id.textViewKitapAdi)
        val yazar: TextView = itemView.findViewById(R.id.textViewYazarAdi)
        val kategori: TextView = itemView.findViewById(R.id.textViewKategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitapViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kitap_arama_item, parent, false)
        return KitapViewHolder(view)
    }

    override fun getItemCount(): Int = kitapList.size

    override fun onBindViewHolder(holder: KitapViewHolder, position: Int) {
        val kitap = kitapList[position]
        holder.kitapAdi.text = kitap.kitapadi
        holder.yazar.text = kitap.yazar
        holder.kategori.text = kitap.category
    }
}




