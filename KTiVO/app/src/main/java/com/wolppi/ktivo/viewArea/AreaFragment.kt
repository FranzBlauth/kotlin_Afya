package com.wolppi.ktivo.viewArea

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.KtivoListener
import com.wolppi.ktivo.service.constants.KtivoConstants

class AreaFragment : Fragment() {

    companion object {
        fun newInstance() = AreaFragment()
    }

    private lateinit var mAreaViewModel: AreaViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var mListener: KtivoListener
    private val mAdapter = AreaAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_lasttemp, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_lastTemp)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter
        (activity as AppCompatActivity).supportActionBar?.show()

        mAreaViewModel = ViewModelProvider(this).get(AreaViewModel::class.java)
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.visibility = View.VISIBLE; //to show

        swipeRefreshLayout.setOnRefreshListener {
            if (mAreaViewModel.isConnectionAvailable()){
                loadAreaRemote()
            }else{
                swipeRefreshLayout.isRefreshing = false
                dialogMessage(getString(R.string.NO_ONLINE_ROWS))
            }
        }

        // Eventos disparados ao clicar nas linhas da RecyclerView
        mListener = object : KtivoListener {

            override fun onListClick(uuid: String) {
                val intent = Intent(context, AreaFormActivity::class.java)
                val bundle = Bundle()
                bundle.putString(KtivoConstants.BUNDLE.AREA, uuid)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDeleteClick(uuid: String) {
                mAreaViewModel.deleteArea(uuid)
            }
        }

        observe()
        setHasOptionsMenu(true)
        return root

    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        if (mAreaViewModel.isConnectionAvailable()){
            loadAreaRemote()
            val a = ""
        }else{
            dialogMessage(getString(R.string.NO_ONLINE_ROWS))
        }

    }
    private fun observe() {

        mAreaViewModel.loadAReaRemote.observe(viewLifecycleOwner, Observer {
            mAreaViewModel.loadContactRemote()
        })

        mAreaViewModel.loadContactRemote.observe(viewLifecycleOwner, Observer {
            loadAreaLocal()
        })

        mAreaViewModel.loadAreaLocal.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE;
            swipeRefreshLayout.isRefreshing = false
            if (it.count() > 0) {
                mAdapter.updateList(it)
            }else{
                dialogMessage(getString(R.string.NO_ROWS_TO_SHOW))
            }
        })
    }


    private fun loadAreaRemote(){
        val model = mAreaViewModel.loadAreaRemote()
        val a = ""
    }

    private fun loadAreaLocal() {
        mAreaViewModel.loadAreaLocal()
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