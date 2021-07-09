package com.loftblog.hogwartslibrary.ui.scenes.spells.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loftblog.hogwartslibrary.R

class SpellAdapter : RecyclerView.Adapter<SpellAdapter.SpellViewHolder>() {

  private val mDataList = ArrayList<SpellCellModel>()

  fun setData(newData: List<SpellCellModel>) {
    mDataList.clear()
    mDataList.addAll(newData)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return SpellViewHolder(item = inflater.inflate(R.layout.cell_spell, parent, false))
  }

  override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
    holder.bind(mDataList[position])
  }

  override fun getItemCount(): Int = mDataList.count()

  class SpellViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val txtName: TextView = item.findViewById(R.id.txtSpellName)
    private val txtType: TextView = item.findViewById(R.id.txtSpellType)

    fun bind(model: SpellCellModel) {
      txtName.text = model.name
      txtType.text = model.type
    }
  }
}