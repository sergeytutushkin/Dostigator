package com.example.dostigator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_navigation, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

//        val application = requireNotNull(this).application
//        val db = DostigatorDatabase.getInstance(application)
//
//        val goalDao = db.goalDao
//        val goal = Goal()
//        goal.title = "Цель 1"
//        goal.description = "Первая тестовая цель"
//        val goalId = goalDao.insert(goal)
//        println("Test1: ${goalDao.getAll()}")
//
//        val planDao = db.planDao
//        val plan = Plan(goalId = goalId)
//        plan.title = "План 1"
//        plan.description = "Первый тестовый план"
//        val planId = planDao.insert(plan)
//        println("Test2: ${planDao.getAll()}")
//
//        val taskDao = db.taskDao
//        val task = Task(planId = planId)
//        task.title = "Задача 1"
//        task.description = "Первая тестовая задача"
//        taskDao.insert(task)
//        println("Test 3: ${taskDao.getAll()}")

//
//        val goals: LiveData<List<Goal>> = goalDao.getAll()
//        goals.observe(this, Observer { goals ->
//            goals?.let {
//
//            }
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.onNavDestinationSelected(item, navController)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_overflow_menu, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.getItemId()) {
//            android.R.id.home -> {
//                val navController = this.findNavController(R.id.nav_host_fragment)
//                navController.popBackStack()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

}
