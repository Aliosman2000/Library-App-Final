package Adapter

import Entity.İade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IadeAdapter(loanList: MutableList<İade>) : ListAdapter<İade, IadeAdapter.IadeViewHolder>(DiffCallback()) {

    class IadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val UyeId: TextView = itemView.findViewById(R.id.UyeId)
        private val KitapId: TextView = itemView.findViewById(R.id.KitapId)
        private val OduncTarihi: TextView = itemView.findViewById(R.id.OduncTarihi)
        private val IadeTarihi: TextView = itemView.findViewById(R.id.IadeTarihi)
        private val IadeKontrol: TextView = itemView.findViewById(R.id.IadeKontrol)


        fun bind(iade: İade) {
            UyeId.text = "Üye ID: ${iade.uyeid}"
            KitapId.text = "Kitap ID: ${iade.kitapId}"
            OduncTarihi.text = "Ödünç Tarihi: ${convertLongToDate(iade.odunc_tarihi)}"
            IadeTarihi.text = "İade Tarihi: ${iade.iade_tarihi?.let { convertLongToDate(it) } ?: "Henüz iade edilmedi"}"
            IadeKontrol.text = "İade Durumu: ${iade.iadekontrol}"
        }

        // Yardımcı fonksiyon: Long tipindeki tarihi String’e çevir
        private fun convertLongToDate(time: Long): String {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            return sdf.format(Date(time))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IadeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_iade, parent, false)
        return IadeViewHolder(view)
    }

    override fun onBindViewHolder(holder: IadeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<İade>() {
        override fun areItemsTheSame(oldItem: İade, newItem: İade) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: İade, newItem: İade) = oldItem == newItem
    }
}