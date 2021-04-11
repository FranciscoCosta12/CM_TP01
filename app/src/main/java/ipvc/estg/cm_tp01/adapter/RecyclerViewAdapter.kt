package ipvc.estg.cm_tp01.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.cm_tp01.R
import ipvc.estg.cm_tp01.db.UserEntity
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewAdapter(val listener: RowClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()  {
    var items = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View, val listener: RowClickListener):RecyclerView.ViewHolder(view){

        val tvTitle = view.tvTitle
        val tvDesc = view.tvDesc
        val deleteUserID = view.deleteUserID
        
        fun bind(data: UserEntity){
            tvTitle.text = data.title
            tvDesc.text = data.desc

            deleteUserID.setOnClickListener{
                listener.onDeleteUserClickListener(data)
            }

        }
    }

    interface RowClickListener{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }
}