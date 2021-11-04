package ru.nsu.fit.sokolova.homework3.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nsu.fit.sokolova.homework3.R

class TasksListAdapter(tasks: List<Task>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var tasks: List<Task> = tasks

    companion object {
        const val TASK_TYPE_IMPORTANT = 1
        const val TASK_TYPE_REGULAR = 2
        const val TASK_TYPE_MINOR = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (tasks[position]) {
            is ImportantTask -> TASK_TYPE_IMPORTANT
            is RegularTask -> TASK_TYPE_REGULAR
            is MinorTask -> TASK_TYPE_MINOR
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        title.text = tasks[position].text
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            tasks[position].isChecked = isChecked
        }
        checkBox.isChecked = tasks[position].isChecked
    }

    sealed class Task(val text: String, var isChecked: Boolean
    )

    data class ImportantTask(val newText: String, val newIsChecked: Boolean
    ) : Task(newText, newIsChecked)

    data class RegularTask(val newText: String, val newIsChecked: Boolean
    ) : Task(newText, newIsChecked)

    data class MinorTask(val newText: String, val newIsChecked: Boolean
    ) : Task(newText, newIsChecked)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TASK_TYPE_IMPORTANT -> LayoutInflater.from(parent.context)
                .inflate(R.layout.task_important, parent, false).let(::ImportantViewHolder)
            TASK_TYPE_REGULAR -> LayoutInflater.from(parent.context)
                .inflate(R.layout.task_regular, parent, false).let(::RegularViewHolder)
            TASK_TYPE_MINOR -> LayoutInflater.from(parent.context)
                .inflate(R.layout.task_minor, parent, false).let(::MinorViewHolder)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.task_minor, parent, false)
                .let(::MinorViewHolder)
        }
    }

    fun replaceList(newTasks: List<TasksListAdapter.Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    inner class ImportantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class RegularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class MinorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}