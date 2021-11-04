package ru.nsu.fit.sokolova.homework3.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.nsu.fit.sokolova.homework3.R
import ru.nsu.fit.sokolova.homework3.rv.TasksListAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity()
{
    private val tasks = ArrayList<TasksListAdapter.Task>()
    private lateinit var adapter: TasksListAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        prepareButtons()

        adapter = TasksListAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
        rvTasks.scrollToPosition(adapter.itemCount -1 )
    }

    private fun prepareButtons()
    {
        btnAll.setOnClickListener {
            adapter.replaceList(tasks)
        }

        btnImportant.setOnClickListener {
            val importantTasks = tasks.filter { it is TasksListAdapter.ImportantTask }
            adapter.replaceList(importantTasks)
        }

        btnRegular.setOnClickListener {
            val regularTasks = tasks.filter { it is TasksListAdapter.RegularTask }
            adapter.replaceList(regularTasks)
        }

        btnMinor.setOnClickListener {
            val minorTasks = tasks.filter { it is TasksListAdapter.MinorTask }
            adapter.replaceList(minorTasks)
        }
    }

    private fun initData()
    {
        tasks.add(TasksListAdapter.ImportantTask("Write diploma", false))
        tasks.add(TasksListAdapter.RegularTask("Go for a walk", true))
        tasks.add(TasksListAdapter.RegularTask("Talk with friends", false))
        tasks.add(TasksListAdapter.MinorTask("Watch movie", false))
        tasks.add(TasksListAdapter.ImportantTask("Do android homework", true))
        tasks.add(TasksListAdapter.MinorTask("Draw smth", true))
    }
}