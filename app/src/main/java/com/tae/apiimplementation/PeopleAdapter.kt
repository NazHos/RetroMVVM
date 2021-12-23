package com.tae.apiimplementation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tae.apiimplementation.databinding.PeopleItemBinding
class PeopleAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var peopleList = mutableListOf<PeopleModelItem>()

    fun setPeople(people: List<PeopleModelItem>) {
        this.peopleList = people.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeopleItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val people = peopleList[position]
        holder.binding.name.text = people.firstName
        holder.binding.subject.text = people.email
        holder.binding.body.text = people.jobTitle
        Glide.with(holder.itemView.context).load(people.avatar).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }
}

class MainViewHolder(val binding:PeopleItemBinding) : RecyclerView.ViewHolder(binding.root) {

}