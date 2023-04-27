package com.example.taskhumanassignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskhumanassignment.R
import com.example.taskhumanassignment.databinding.SkillListItemBinding
import com.example.taskhumanassignment.domain.models.Skill
import com.example.taskhumanassignment.utils.Util
import java.util.*

class SkillAdapter : RecyclerView.Adapter<SkillAdapter.ViewHolder>() {
    private var skills = ArrayList<Skill>()

    @SuppressLint("NotifyDataSetChanged")
    fun setSkillsList(skillsList : List<Skill>?){
        skillsList?.let {
            this.skills = skillsList as ArrayList<Skill>
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SkillListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = skills[position]
        holder.binding.skillName.text = skill.tileName
        holder.binding.skillStartTime.text = Util.getTimeFromMilliseconds(skill.availability?.startTime)
        holder.binding.skillEndTime.text = Util.getTimeFromMilliseconds(skill.availability?.endTime)
//        when(skill.isFavorite) {
//            true -> {
//                holder.binding.swipeTvMessage.text = holder.itemView.context.getString(R.string.added)
//                Glide.with(holder.itemView)
//                    .load(R.drawable.ic_heart_added)
//                    .into(holder.binding.swipeImage)
//            }
//            false -> {
//                holder.binding.swipeTvMessage.text = holder.itemView.context.getString(R.string.add_to_favorite)
//                Glide.with(holder.itemView)
//                    .load(R.drawable.ic_heart_shape)
//                    .into(holder.binding.swipeImage)
//            }
//        }
    }
    override fun getItemCount(): Int {
        return skills.size
    }

    class ViewHolder(val binding : SkillListItemBinding) : RecyclerView.ViewHolder(binding.root)  {}
}