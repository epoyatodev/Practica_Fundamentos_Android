package com.poyatodev.practicaandroidfundamentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.poyatodev.practicaandroidfundamentos.databinding.FragmentFightBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FightFragment : Fragment() {

    private lateinit var binding: FragmentFightBinding
    val fightViewModel: FightViewModel by viewModels()
    val coreViewModel: CoreViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFightBinding.inflate(inflater)


        viewLifecycleOwner.lifecycleScope.launch {
            coreViewModel.uiState.collect{
                //TODO Preguntar por que eso funciona
                //  binding.tvFigterName.text = (it as CoreViewModel.UiStateCA.OnHeroeSelectedToFight).heroe.name
                binding.textViewNameHeroeFight.text = coreViewModel.selectedHeroe.name
                Picasso.get().load(coreViewModel.selectedHeroe.photo).into(binding.imageHeroeFight)
                binding.progressBarFight.max = coreViewModel.selectedHeroe.totalHitPoints
                binding.progressBarFight.progress = coreViewModel.selectedHeroe.currentHitPoints
                binding.numberProgress.text = coreViewModel.selectedHeroe.currentHitPoints.toString()




            }
        }
        setFightButtonsOnClickMethods()
        return binding.root
    }

    private fun setFightButtonsOnClickMethods(){
        binding.bnHeal.setOnClickListener {
            coreViewModel.fightOnClickMethod(binding.bnHeal.tag.toString())
        }
        binding.bnAtack.setOnClickListener {
            coreViewModel.fightOnClickMethod(binding.bnAtack.tag.toString())
        }
    }


}