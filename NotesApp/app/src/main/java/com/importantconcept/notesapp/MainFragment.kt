package com.importantconcept.notesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.importantconcept.notesapp.api.NotesAPI
import com.importantconcept.notesapp.databinding.FragmentMainBinding
import com.importantconcept.notesapp.utils.Constants.TAG
import com.importantconcept.notesapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<NoteViewModel>()//fragment extension

    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = NoteAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        noteViewModel.getNotes()
        binding.rvNoteList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvNoteList.adapter = adapter
    }

    private fun bindObservers() {
        //jasa hi notesLiveData mai kuch bhi change hoga, toh humara ya Observer{} call hoga
        noteViewModel.notesLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            //Now yaha par NetworkResult ayaga
            when(it){
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}