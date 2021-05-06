package com.example.rickandmorty.activity

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.ViewModel.HeroesApplication
import com.example.rickandmorty.ViewModel.HeroesViewModel
import com.example.rickandmorty.adapter.MyAdapter
import com.example.rickandmorty.ViewModel.WordViewModelFactory
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.pojo.Heroes
import com.example.rickandmorty.pojo.HeroesResponse
import com.example.rickandmorty.retrofit2.Common
import com.example.rickandmorty.retrofit2.RetrofitServices
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var layoutManager: GridLayoutManager
    var adapter: MyAdapter? = null
    lateinit var nullServices: RetrofitServices
    lateinit var dialog: AlertDialog
    var pageId: Int = 1
    lateinit var next_page: Button
    lateinit var prev_page: Button

    val viewModel: HeroesViewModel by viewModels {
        WordViewModelFactory((application as HeroesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        nullServices = Common.retrofitServices
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        adapter = MyAdapter(this)
        layoutManager = GridLayoutManager(this, 2)
        val rc_view = findViewById<RecyclerView>(R.id.rc_view)
        next_page = findViewById(R.id.next_page)
        prev_page = findViewById(R.id.back_page)

        rc_view.adapter = adapter
        rc_view.layoutManager = layoutManager

        for (i in 1..34) {
            getNewPage(i)
        }
        if(pageId == 1){
            runSwitch(pageId)
        }

        next_page?.setOnClickListener {
            if (pageId < 34) {
                pageId += 1
                runSwitch(pageId)
                Toast.makeText(this, pageId.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        prev_page?.setOnClickListener {
            if (pageId > 1) {
                pageId--
                runSwitch(pageId)
            }
        }

    }

    fun runSwitch(id: Int){
        GlobalScope.launch {
            switchPage(id)
        }
    }

    private fun switchPage(id: Int) {
        viewModel.getById(id).let { characters ->
            characters?.let {
                adapter?.submitList(it.results)
            }
        }
    }

    fun getNewPage(page: Int) {
        dialog.show()
        nullServices.getHeroesList(page).enqueue(object : Callback<HeroesResponse> {
            override fun onFailure(call: Call<HeroesResponse>, t: Throwable) {

            }

            override fun onResponse(
                    call: Call<HeroesResponse>,
                    response: Response<HeroesResponse>
            ) {
                viewModel.insertAllHeroes(response.body() as HeroesResponse)
                dialog.dismiss()
            }
        })

    }
}
