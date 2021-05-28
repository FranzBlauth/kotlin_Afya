package com.wolppi.ktivo.ui.lastTemp

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wolppi.ktivo.R
import com.wolppi.ktivo.service.constants.KtivoConstants


class LastTempFragment : Fragment() {

    private lateinit var mLastTempViewModel: LastTempViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val mAdapter = LastTempAdapter()
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {

        mLastTempViewModel = ViewModelProvider(this).get(LastTempViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_lasttemp, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_lastTemp)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter
        (activity as AppCompatActivity).supportActionBar?.show()
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.visibility = View.VISIBLE; //to show

        swipeRefreshLayout.setOnRefreshListener {
            if (mLastTempViewModel.isConnectionAvailable()){
                 loadLastTempRemote()
            }else{
                swipeRefreshLayout.isRefreshing = false
                dialogMessage(getString(R.string.NO_ONLINE_ROWS))
            }
        }
        setHasOptionsMenu(true)
        observe()
        return root
    }

    override fun onResume() {
        super.onResume()
        if (mLastTempViewModel.isConnectionAvailable()){
            loadArea()
            loadPlace()
            loadProvider()
            loadContact()
            loadSensor()
            loadLastTempRemote()
        }else{
            loadLastTempLocal()
            dialogMessage(getString(R.string.NO_ONLINE_ROWS))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_all_temperature) {
            loadLastTempLocal()
            return true
        }

        if (item.itemId == R.id.action_normal_temperature) {
            loadLastTempLocalFilter(KtivoConstants.TEMPERATURE.NORMAL)
            return true
        }

        if (item.itemId == R.id.action_hot_temperature) {
            loadLastTempLocalFilter(KtivoConstants.TEMPERATURE.HOT)
            return true
        }

        if (item.itemId == R.id.action_cold_temperature) {
            loadLastTempLocalFilter(KtivoConstants.TEMPERATURE.COLD)
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun observe() {

        mLastTempViewModel.loadLastTempRemote.observe(viewLifecycleOwner, Observer {
            if(it){
                loadLastTempLocal()
            }
        })

        mLastTempViewModel.loadLastTempLocal.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE;
            swipeRefreshLayout.isRefreshing = false
            if (it.count() > 0) {
                 mAdapter.updateList(it)
            }else{
                dialogMessage(getString(R.string.NO_ROWS_TO_SHOW))
            }
        })
    }


    private fun loadArea() {
        mLastTempViewModel.loadArea()
    }

    private fun loadPlace() {
        mLastTempViewModel.loadPlace()
    }

    private fun loadContact() {
        mLastTempViewModel.loadContact()
    }

    private fun loadProvider() {
        mLastTempViewModel.loadProvider()
    }

    private fun loadSensor() {
        mLastTempViewModel.loadSensor()
    }

    private fun loadLastTempRemote() {
        mLastTempViewModel.loadLastTempRemote()
    }

    private fun loadLastTempLocal() {
        mLastTempViewModel.loadLastTempLocal()
    }

    private fun loadLastTempLocalFilter(filter: String) {
        mLastTempViewModel.loadLastTempLocalFilter(filter)
    }

    private fun dialogMessage(message: String){

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("KTiVO")
        builder.setMessage(message)
        builder.setIcon(R.mipmap.ic_launcher)

        builder.setPositiveButton(R.string.ok) { dialog, which ->
            Toast.makeText(activity, R.string.null_msg, Toast.LENGTH_SHORT)
        }

        builder.show()
    }
}
