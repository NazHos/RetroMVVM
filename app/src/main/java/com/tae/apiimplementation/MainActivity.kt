package com.tae.apiimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tae.apiimplementation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = PeopleAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)


        viewModel.peopleList.observe(this, {
            adapter.setPeople(it)
        })

        viewModel.getAllPeople()

    }
}