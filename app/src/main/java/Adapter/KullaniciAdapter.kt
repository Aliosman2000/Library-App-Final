package Adapter
import Entity.Kullanici
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egitim.kutuphee_yazilimi.R


class KullaniciAdapter : RecyclerView.Adapter<KullaniciAdapter.KullaniciViewHolder>() {

    private var kullaniciList: List<Kullanici> = emptyList()

    fun setData(list: List<Kullanici>) {
        kullaniciList = list
        notifyDataSetChanged()
    }

    class KullaniciViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ad: TextView = itemView.findViewById(R.id.ad)
        val soyad: TextView = itemView.findViewById(R.id.soyad)
        val kullaniciAdi: TextView = itemView.findViewById(R.id.kullaniciadi)
        val eposta: TextView = itemView.findViewById(R.id.eposta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KullaniciViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_kullanici, parent, false)
        return KullaniciViewHolder(view)
    }

    override fun getItemCount(): Int = kullaniciList.size

    override fun onBindViewHolder(holder: KullaniciViewHolder, position: Int) {
        val kullanici = kullaniciList[position]
        holder.ad.text = kullanici.ad
        holder.soyad.text = kullanici.soyad
        holder.kullaniciAdi.text = kullanici.kullaniciAdi
        holder.eposta.text = kullanici.eposta
    }
}