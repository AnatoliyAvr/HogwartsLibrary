package com.loftblog.hogwartslibrary.ui.scenes.students.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loftblog.hogwartslibrary.R

class StudentsAdapter : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

  private val mDataList = ArrayList<StudentCellModel>()
  private val mDisplayList = ArrayList<StudentCellModel>()

  fun setData(newData: List<StudentCellModel>) {
    mDataList.clear()
    mDataList.addAll(newData)
    filter(query = "")
  }

  fun filter(query: String) {
    mDisplayList.clear()

    if (query.isEmpty()) {
      mDisplayList.addAll(mDataList)
      notifyDataSetChanged()
      return
    }

    mDisplayList.addAll(mDataList.filter {
      it.name.contains(query, true) ||
          it.facultyName.contains(query, true)
    })

    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return StudentViewHolder(item = inflater.inflate(R.layout.cell_student, parent, false))
  }

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    holder.bind(mDisplayList[position])
  }

  override fun getItemCount(): Int = mDisplayList.count()

  class StudentViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val txtName: TextView = item.findViewById(R.id.txtStudentName)
    private val txtFaculty: TextView = item.findViewById(R.id.txtStudentFaculty)
    private val txtSpecies: TextView = item.findViewById(R.id.txtStudentSpecies)

    fun bind(model: StudentCellModel) {
      txtName.text = model.name
      txtFaculty.text = model.facultyName
      txtSpecies.text = model.species
    }
  }
}